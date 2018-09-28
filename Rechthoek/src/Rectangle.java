/**
 * Calculates the surface and circumference from 2 given dimensions
 *
 * @author Ruben Bos
 */

import java.util.Locale;
import java.util.Scanner;

public class Rectangle {

    public static void main(String[] args) {
        Scanner reader = new Scanner(System.in).useLocale(new Locale("nl", "NL"));

        System.out.print("Lengte: ");
        double lengte = reader.nextDouble();

        System.out.print("Breedte: ");
        double breedte = reader.nextDouble();

        double oppervlakte = lengte * breedte;
        double omtrekt = (lengte + breedte) * 2;

        System.out.println();
        System.out.println("Oppervlakte: " + oppervlakte);
        System.out.println("Omtrek: " + omtrekt);
    }
}
