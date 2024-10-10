package Testing;

import Backend.PasswordEvaluator;

public class Password_Tester {

    static int numPassed = 0;
    static int numFailed = 0;
    public static void main(String[] args) {
        System.out.println("____________________________________________________________________________");
        System.out.println("\nTesting Automation");

        performTestCase(1 ,"MyPassword$1",true);
        performTestCase(2 ,"mypassword$1",false);
        performTestCase(3 ,"MyPassword1",false);
        performTestCase(4 ,"MyPassword$",false);
        performTestCase(5 ,"MyP$1",false);
        performTestCase(6 ,"MYPASSWORD$1",false);
        performTestCase(7 ,"Password$1",true);
        performTestCase(8 ,"My_Password1",true);
        performTestCase(9 ,"Mypassword-123",true);

        System.out.println("____________________________________________________________________________");
        System.out.println();
        System.out.println("Number of tests passed: "+ numPassed);
        System.out.println("Number of tests failed: "+ numFailed);
    }

    private static void performTestCase(int testCase, String inputText, boolean expectedPass) {
        System.out.println("____________________________________________________________________________\n\nTest case: " + testCase);
        System.out.println("Input: \"" + inputText + "\"");
        System.out.println("______________");
        System.out.println("\nFinite state machine execution trace:");

        String resultText= Password_Checker.evaluatePassword(inputText);

        System.out.println();

        if (resultText != "") {
            if (expectedPass) {
                System.out.println("***Failure*** The password <" + inputText + "> is invalid." +
                        "\nBut it was supposed to be valid, so this is a failure!\n");
                System.out.println("Error message: " + resultText);
                numFailed++;
            }
            else {
                System.out.println("***Success*** The password <" + inputText + "> is invalid." +
                        "\nBut it was supposed to be invalid, so this is a pass!\n");
                System.out.println("Error message: " + resultText);
                numPassed++;
            }
        }

        else {
            if (expectedPass) {
                System.out.println("***Success*** The password <" + inputText +
                        "> is valid, so this is a pass!");
                numPassed++;
            }
            else {
                System.out.println("***Failure*** The password <" + inputText +
                        "> was judged as valid" +
                        "\nBut it was supposed to be invalid, so this is a failure!");
                numFailed++;
            }
        }
        displayEvaluation();
    }

    private static void displayEvaluation() {
        if (Password_Checker.foundUpperCase)
            System.out.println("At least one upper case letter - Satisfied");
        else
            System.out.println("At least one upper case letter - Not Satisfied");

        if (Password_Checker.foundLowerCase)
            System.out.println("At least one lower case letter - Satisfied");
        else
            System.out.println("At least one lower case letter - Not Satisfied");


        if (Password_Checker.foundNumericDigit)
            System.out.println("At least one digit - Satisfied");
        else
            System.out.println("At least one digit - Not Satisfied");

        if (Password_Checker.foundSpecialChar)
            System.out.println("At least one special character - Satisfied");
        else
            System.out.println("At least one special character - Not Satisfied");

        if (Password_Checker.foundLongEnough)
            System.out.println("At least 8 characters - Satisfied");
        else
            System.out.println("At least 8 characters - Not Satisfied");
    }
}
