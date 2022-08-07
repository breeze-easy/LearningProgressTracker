package tracker;

import java.util.regex.Pattern;

public class Verifier {
    private static String firstName;
    private static String lastName;
    private static String email;
    // patterns
    private static String REGEX_NAME = "[^-][a-z' -]+['?][^-$]";
    private static String REGEX_EMAIL = "[^-][a-z' -]+[^-$]";

    static Student verifyAndCreateNewStudent(String newUserInfo)  {
        Student student = null;

        try {
            parseUserInfo(newUserInfo);
        } catch (IllegalArgumentException e) { UI.println(e.getMessage()); return student; }

        if     (!isValidFirstName())    { UI.println("Incorrect first name"); }
        else if(!isValidLastName())     { UI.println("Incorrect last name"); }
        else if(!isValidEmail())        { UI.println("Incorrect email"); }
        else /* all fields are valid */ { student = new Student(firstName, lastName, email); }

        return student;
    }

    private static boolean isValidEmail() {
        String regExPattern = "[a-zA-Z@.]+";
        return matchesThePattern(regExPattern, email);
    }

    // get first, last name and email
    private static void parseUserInfo(String newUserInfo) throws IllegalArgumentException {
        String[] parts = newUserInfo.split(" ");
        int numOfTokens = parts.length;

        if(numOfTokens < 3) {
            throw new IllegalArgumentException("Incorrect credentials");
        }

        firstName = parts[0];
        email = parts[numOfTokens -1]; // always last token
        lastName = extractLastName(parts, numOfTokens);

    }

    private static String extractLastName(String[] parts, int numOfTokens) {
        // last name could consist of 2 or more tokens
        String lname = "";
        if(numOfTokens == 3) {
            lname = parts[1];
        }
        else {
            for(int i=1; i < numOfTokens - 1; i++) {
                lname += parts[i] + " ";
            }
            lname.trim();
        }
        return lname;
    }

    private static boolean isValidFirstName() {
        String regExPattern = "[a-zA-Z]+";
        return !beginsOrEndsWithHyphens(firstName) && matchesThePattern(regExPattern, firstName);
    }

    private static boolean isValidLastName() {
        String regExPattern = "[a-zA-Z ]+"; // may include " "
        return !beginsOrEndsWithHyphens(lastName) && matchesThePattern(regExPattern, lastName);
    }

    private static boolean matchesThePattern(String regExPattern, String s) {
        return Pattern.matches(regExPattern, s);
    }

    private static boolean beginsOrEndsWithHyphens(String s) {
        // hyphen '-' is not allowed as the first or the last chars
        return (s.charAt(0) == '-') || (s.charAt(s.length()-1) == '-') ;
    }

}