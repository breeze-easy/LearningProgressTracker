package tracker;

import java.util.regex.Pattern;

public class Verifier {
    static void verifyUser(String newUserInfo)  {
        String [] parts = newUserInfo.split(" ");
        // verify first name
        String firstName = parts[0];
        // first & last chars are not '-'
        if(firstName.charAt(0) == '-' || firstName.charAt(firstName.length()-1) == '-')
        {
          System.out.println("First name can't begin or end with '-");
          return;
        }


        boolean resFName = Pattern.matches("[a-zA-Z]+", parts[0]);
        if (resFName == true) System.out.println("First name is Okay");
        else System.out.println("Error in Fname ");



        // verify email
        boolean resEmail = Pattern.matches("[a-zA-Z]+", parts[2]);
        if (resEmail == true) System.out.println("EMAIL is Okay");
        else System.out.println("Error in EMAIL");
    }

    public static void verifyLastName(String lastName) {
        // verify last name
        boolean resLastName = Pattern.matches("[a-zA-Z]+", lastName);
        if (resLastName == true) System.out.println("LAST name is Okay");
        else System.out.println("Error in LAST name ");
    }
}
