package tracker;

import java.util.Scanner;

public class Main {
    private Session session;

    public static void main(String[] args) {
        Session session = new Session();
        UI.printProgramTitle();
        session.processUserInput();
        end();
    }

    static void end() {
        UI.printGoodBye();
        System.exit(0);
    }
}
