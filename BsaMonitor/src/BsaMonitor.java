/**
 * Script to calculate study points from grades
 *
 * @author Ruben Bos
 */

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.Locale;

public class BsaMonitor {

    public static void main(String[] args) {
        final double SUFFICIENT_GRADE = 5.5;

        //Initialize subject list
        List<Subject> subjects = new ArrayList<>();

        //Add subjects to the list
        subjects.add(new Subject("Faster Your Seatbelts", 12, 0));
        subjects.add(new Subject("Programming", 3, 0));
        subjects.add(new Subject("Databases", 3, 0));
        subjects.add(new Subject("Personal Skills", 2, 0));
        subjects.add(new Subject("Project Skills", 2, 0));
        subjects.add(new Subject("Infrastructure", 3, 0));
        subjects.add(new Subject("Network Engineering 1", 3, 0));

        //Define scanner
        Scanner reader = new Scanner(System.in).useLocale(new Locale("nl", "NL"));

        System.out.println("Voer behaalde cijfers in:");

        //Print the prefixes for the user input,
        //and read the user input, while saving it in the list
        for (Subject s : subjects) {
            System.out.print(String.format("%s: ", s.name));
            s.grade = reader.nextDouble();
        }

        //Close the reader
        reader.close();

        System.out.println();
        //Formatted output string
        String format = "Vak/project: %-25s Cijfer: %-8s Behaalde punten: %s%n";
        //Loop through the list again. This time to print the formatted output with the user supplied input
        for (Subject s : subjects) {
            System.out.format(format, s.name, s.grade, s.grade >= SUFFICIENT_GRADE ? s.studyPoints : 0);
        }

        //Calculate the max amount of study points you can earn, together with the earned ones
        var earnedStudyPoints = subjects.stream().filter(s -> s.grade >= SUFFICIENT_GRADE).mapToInt(s -> s.studyPoints).sum();
        var totalStudyPoints = subjects.stream().mapToInt(s -> s.studyPoints).sum();

        //Print a new line with the last line which shows how much of the total points you earned
        System.out.println();
        System.out.println(String.format("Totaal behaalde studiepunten: %s/%s", earnedStudyPoints, totalStudyPoints));

        //If the earned count of the earned points is below a certain amount of points (6/5 of the max),
        // then print this warning message
        if (earnedStudyPoints <= (totalStudyPoints / 6.0) * 5.0)
            System.out.println("PAS OP: je ligt op schema voor een negatief BSA!");
    }
}

/**
 *  * Class/model which will be used as object to store school subjects in
 *
 * @author Ruben Bos
 */
class Subject {
    public String name;
    public int studyPoints;
    public double grade;

    public Subject(String n, int s, double g) {
        this.name = n;
        this.grade = g;
        this.studyPoints = s;
    }
}
