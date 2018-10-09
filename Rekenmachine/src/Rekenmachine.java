/**
 * Script to calculate the sum of 2 given numbers.
 * Uses a user supplied operator
 *
 * @author Ruben Bos
 */

import java.util.*;

public class Rekenmachine {

    //Array of valid operators, as chars
    private static char[] operators = "+-*/%".toCharArray();

    /**
     * @param operator The operator to check
     * @return returns if the supplied operator is valid
     */
    private static boolean isValidOperator(char operator) {
        return (new String(operators).contains(String.valueOf(operator)));
    }

    /**
     * Prints the sum of 2 numbers. Uses a given operator
     *
     * @param operator The operator to be used for the sum
     * @param number1  The number in front of the operator
     * @param number2  The number after the operator
     */
    private static void printSum(char operator, double number1, double number2) {
        double sum;
        switch (operator) {
            case '+':
                sum = number1 + number2;
                break;
            case '-':
                sum = number1 - number2;
                break;
            case '*':
                sum = number1 * number2;
                break;
            case '/':
                sum = number1 / number2;
                break;
            case '%':
                sum = number1 % number2;
                break;
            default:
                sum = 0;
                break;
        }

        //Print the sum with the output
        System.out.println(String.format("%s %s %s = %.2f %n", number1, operator, number2, sum));
    }

    /**
     * @param args This argument is not being used
     */
    public static void main(String[] args) {
        final char STOP_CHAR = 'S';
        char operator;
        boolean isNotStopChar;

        //Do while loop to get the users input at least once. Keeps looping until the stop command (S) is given
        do {
            Scanner inputScanner = new Scanner(System.in).useLocale(new Locale("nl", "NL"));
            //Read the operator
            System.out.print("Operator ("+STOP_CHAR+" = stoppen): ");
            operator = inputScanner.nextLine().charAt(0);

            isNotStopChar = operator != STOP_CHAR;

            //If the stop command is given. skip all the other stuff
            if (isNotStopChar) {
                //Checks if the given operator is in the array of allowed operators
                if (isValidOperator(operator)) {
                    //Read the first number
                    System.out.print("Eerste getal: ");
                    double number1 = inputScanner.nextDouble();

                    //Read the second number
                    System.out.print("Tweede getal: ");
                    double number2 = inputScanner.nextDouble();

                    //Prints the sum of 2 numbers with the given operator
                    printSum(operator, number1, number2);
                } else {
                    System.out.println("Operator is ongeldig \n");
                }
            }
        } while (isNotStopChar); //Keep looping until the input is 'S'
    }
}
