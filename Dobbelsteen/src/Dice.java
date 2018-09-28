/**
 * Script to spin a dice randomly until it hits 6.
 * Prints the dice number in ascii art as output
 *
 * @author Ruben Bos
 */


import java.util.HashMap;
import java.util.Locale;
import java.util.Map;
import java.util.Scanner;

public class Dice {

    /**
     *
     * @param min the minimum of the return value
     * @param max the maximum the return value can be
     * @return a random int between the supplied min and max parameters
     */
    private static int getRandomInt(int min, int max) {
        int range = (max - min) + 1;
        return (int) (Math.random() * range) + min;
    }

    /**
     *
     * @param args This argument is not being used
     */
    public static void main(String[] args) {
        //Declare and initialize the dice dictionary
        Map<String, String> dice = new HashMap<>();
        dice.put("1", "\n o \n");
        dice.put("2", "o\n\n  o");
        dice.put("3", "o\n o \n  o");
        dice.put("4", "o o\n\no o");
        dice.put("5", "o o\n o \no o");
        dice.put("6", "o o\no o\no o");

        System.out.print("Welk karakter moet ik gebruiken voor het oog: ");

        //Get the user input as string and get the first char. This is used to create the dice ascii art with.
        char diceChar = new Scanner(System.in).nextLine().charAt(0);

        int randomDice;

        //Do while loop to at least go through it once. If then the dice is 6, stop. otherwise continue until it is 6
        do {
            //Initialize randomDice with a 'random' number between 0 and 7 (count of the dic + 1).
            randomDice = getRandomInt(1, dice.size());

            //Print the ascii art of the dice belonging to the randomDice number,
            //and replace the default char with the user supplied one
            System.out.println("\n" +
                    dice.get(Integer.toString(randomDice))
                            .replaceAll("o", Character.toString(diceChar)));
        } while (randomDice != 6);
    }
}
