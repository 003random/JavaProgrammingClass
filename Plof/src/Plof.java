/**
 * A script to print the numbers between a given range,
 * and print a special word if the number is divisible by a given number
 *
 * @author Ruben Bos
 */

import java.util.Scanner;

public class Plof {

    /**
     * @param args Not being used
     */
    public static void main(String[] args) {
        final int STARTING_NUMBER = 1;
        final String PLOF_MESSAGE = "plof";

        String plofNumber = "";
        boolean validInput = false;

        Scanner inputReader = new Scanner(System.in);

        //Keep looping until the users supplies an integer
        while (!validInput) {
            //Print the first message that asks for the division number
            System.out.print("Wat is het \"Plof\" getal (2..9)? ");
            plofNumber = inputReader.nextLine();

            //Checks if the string is an integer
            try {
                int num = Integer.parseInt(plofNumber);

                //If number is bigger then 1. the input is valid.
                if (num > 1)
                    validInput = true;

            } catch (NumberFormatException e) {
                //Not an integer
            }

            //If the input is invalid. Print a warning
            if(!validInput){
                System.out.println("Invalid input. Please try again...");
            }
        }

        //Print the second message which asks the user for the ending number.
        System.out.print("Tot en met welk getal moet ik tellen? ");
        int endingNumber = inputReader.nextInt();

        //Loop from 1 to the given ending number
        for (int i = STARTING_NUMBER; i <= endingNumber; i++) {
            //When the number is divisible by the given 'Plof' number, print the 'Plof' word.
            //Otherwise print the number
            System.out.print(i % Integer.parseInt(plofNumber) == 0 ? PLOF_MESSAGE : i);
            System.out.print(" ");
        }
    }
}
