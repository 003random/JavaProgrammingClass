/**
 * Script to calculate study points from grades
 *
 * @author Ruben Bos
 */

import java.lang.reflect.Array;
        import java.util.*;

public class BsaMonitor2 {

    /**
     *
     * @param args This argument is not being used
     */
    public static void main(String[] args) {
        //Init the arrays
        String[] vakNamen = new String[7];
        Integer[] vakPunten = new Integer[7];
        double[] vakCijfers = new double[7];

        //Add subjects to the array
        vakNamen[0] = "Faster Your Seatbelts";
        vakNamen[1] = "Programming";
        vakNamen[2] = "Databases";
        vakNamen[3] = "Personal Skills";
        vakNamen[4] = "Project Skills";
        vakNamen[5] = "Infrastructure";
        vakNamen[6] = "Network Engineering 1";

        //Add the study points to the studypoints array. Match the indexes with the ones from the subjects array
        vakPunten[0] = 12;
        vakPunten[1] = 3;
        vakPunten[2] = 3;
        vakPunten[3] = 2;
        vakPunten[4] = 2;
        vakPunten[5] = 3;
        vakPunten[6] = 3;

        //Define scanner
        Scanner reader = new Scanner(System.in).useLocale(new Locale("nl", "NL"));

        System.out.println("Voer behaalde cijfers in:");

        //Print the prefixes for the user input,
        //and read the user input, while saving it in the list
        for (int i = 0; i < vakNamen.length; i++) {
            var valid = false;
            //While the inut is not valid, keep looping
            while(!valid) {
                System.out.printf("%s: ", vakNamen[i]);
                //Read the user input
                var input = reader.nextDouble();
                //If the given grade is or is between 0 and 10
                if(input <= 10 && input >= 0) {
                    //Add the input to the grade array
                    vakCijfers[i] = input;
                    //Set valid to true so the loop stops
                    valid = true;
                } else {
                    //Print message that the input was wrong
                    System.out.println("Geef een cijfer tussen de 0 en 10 aub");
                }
            }
        }

        //Close the reader
        reader.close();

        System.out.println();
        //Formatted output string
        String format = "Vak/project: %-25s Cijfer: %-8s Behaalde punten: %s%n";
        //Loop through the list again. This time to print the formatted output with the user supplied input
        for (int i = 0; i < vakNamen.length; i++) {
            System.out.format(format, vakNamen[i], vakCijfers[i], vakCijfers[i] >= 5.5 ? vakPunten[i] : 0);
        }

        //Calculate the max amount of study points you can earn, together with the earned ones
        var earnedStudyPoints = 0;
        var totalStudyPoints = 0;

        for (int i = 0; i < vakNamen.length; i++) {
            if (vakCijfers[i] >= 5.5) {
                earnedStudyPoints += vakPunten[i];
            }
            totalStudyPoints += vakPunten[i];
        }

        //Print a new line with the last line which shows how much of the total points you earned
        System.out.println();
        System.out.println(String.format("Totaal behaalde studiepunten: %s/%s", earnedStudyPoints, totalStudyPoints));

        //If the earned count of the earned points is below a certain amount of points (6/5 of the max),
        // then print this warning message
        if (earnedStudyPoints <= (totalStudyPoints / 6.0) * 5.0)
            System.out.println("PAS OP: je ligt op schema voor een negatief BSA!");
    }
}