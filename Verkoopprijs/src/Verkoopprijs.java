/**
 * Script to calculate purchase prices with VAT
 *
 * @author Ruben Bos
 */

import java.util.Locale;
import java.util.Scanner;

public class Verkoopprijs {

    public static void main(String[] args) {

        //Define scanner
        Scanner reader = new Scanner(System.in).useLocale(Locale.GERMANY);

        System.out.print("Inkoopprijs: ");
        //Read input as double
        double purchasePrice = reader.nextDouble();

        System.out.print("Winstmarge (in %): ");
        //Read second input as double
        double margin = reader.nextDouble();
        //Close the reader
        reader.close();

        //Define the taxes
        var vatLow = 6;
        var vatHigh = 21;

        //Calculate the taxes
        var purchasePriceExcl = (purchasePrice / 100) * (100 + margin);
        var purchasePriceVatLow = (purchasePriceExcl / 100) * (100 + vatLow);
        var purchasePriceVatHigh = (purchasePriceExcl / 100) * (100 + vatHigh);

        //Print the output with the prices
        System.out.println("Verkoopprijs exclusief BTW : " + purchasePriceExcl);
        System.out.println("Verkoopprijs inclusief " + vatLow + "% BTW: " + purchasePriceVatLow);
        System.out.println("Verkoopprijs inclusief " + vatHigh + "% BTW: " + purchasePriceVatHigh);
    }
}
