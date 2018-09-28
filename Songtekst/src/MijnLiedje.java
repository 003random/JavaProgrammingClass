/**
 * Script to get the lyrics of any song.
 * uses "https://www.azlyrics.com/lyrics/" as an 'api'.
 *
 * @author Ruben Bos
 */


import java.net.*;
import java.util.*;
import java.io.*;
import java.util.concurrent.TimeUnit;

public class MijnLiedje {

    ///This method returns an array with each line of the repsonse, joined to a string by using -- as a delimiter
    ///Parameters: String urlPar: the url which should be fetched
    private static List<String> getHTML(String urlPar) throws Exception {
        //Create new empty list
        List<String> result = new ArrayList<String>();
        //url string to URL obj
        URL url = new URL(urlPar);
        //Create http connection
        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
        //Set the method to a GET request
        conn.setRequestMethod("GET");

        if (conn.getResponseCode() == 404 || conn.getResponseCode() == 500) {
            return Collections.emptyList();
        }

        //Create a BufferedReader obj to read the response
        BufferedReader rd = new BufferedReader(new InputStreamReader(conn.getInputStream()));
        //Initialize the line var so we can use it in the other scopes as well
        String line;
        //Loop through the lines
        while ((line = rd.readLine()) != null) {
            //Add each line to the results array
            result.add(line);
        }
        //Close the BufferedReader
        rd.close();
        //Return the list
        return result;
    }

    ///Returns a horizontal line made up by the chars " -".
    ///Parameter: length: the length of the line. The line needs to be odd, so if the length of the line isn't odd. it will add one char to it
    private static String getHorizontalLine(int length) {
        //Return a string multiplied by length
        return String.join("", Collections.nCopies(((length % 2 == 0 ? length : length + 1) / 2) + 1, " -")) + " ";
    }

    ///Returns the length of the difference of 2 strings as an integer.
    ///Parameter: first: the first string
    ///Parameter: second: the second string
    private static int getCharDifference(int first, int second) {
        //Return a string multiplied by length
        return second % 2 == 0 ? second - first : (second - first) + 1;
    }

    ///Returns a string of spaces * the amount
    ///Parameter: amount: the amount of spaces that needs to be returned
    private static String getSpaces(int amount) {
        //Return a string multiplied by length
        return String.join("", Collections.nCopies(amount, " "));
    }

    private static void printLyrics(List<String> html, String artist, String song) {
        //Split the array on the given string to remove the source code in front of the lyrics
        for (int i = 0; i < html.size(); i++) {
            if (html.get(i).contains("Sorry about that. -->")) {
                html.subList(0, i + 1).clear();
                break;
            }
        }

        //Split the array on the given text to remove the source code after the lyrics
        for (int i = 0; i < html.size(); i++) {
            if (html.get(i).contains("<br><br>")) {
                html.subList(i, html.size()).clear();
                break;
            }
        }

        //Remove the last two elements from the list since those are always empty
        html.subList(html.size() - 2, html.size()).clear();

        //Get the length of the longest line in the lyrics
        int longestLine = Arrays.stream(html.toArray(new String[0])).max(Comparator.comparingInt(String::length)).get().length();

        //System.out.println("\n");

        //Print the first horizontal line of the lyrics box
        System.out.println(getHorizontalLine(longestLine));
        //Print the song info
        System.out.println("| Song: " + song + getSpaces(getCharDifference(("Song: " + song).length(), longestLine)) + "|");
        System.out.println("| Artist: " + artist + getSpaces(getCharDifference(("Artist: " + artist).length(), longestLine)) + "|");
        //Print a horizontal line to split the song info from the lyrics
        System.out.println("| " + getHorizontalLine(longestLine - 4) + " |");

        //Loop through each line in the html list
        for (var i = 0; i < html.size(); i++) {
            //Replace tags and html chars with either nothing or the char itself
            var line = html.get(i).replace("<br>", "").replace("</div>", "").replace("&quot;", "\"");
            //Print the line with an "|" pre and appended. For the appended char, calculate the difference so the box is a square
            System.out.println("| " + line + getSpaces(getCharDifference(line.length(), longestLine)) + "|");
        }
        //Print the last horizontal line of the lyrics box
        System.out.println(getHorizontalLine(longestLine));
    }

    ///Constructor. Returns nothing
    ///Parameters: String[] args: the arguments passed to the file
    public static void main(String[] args) throws Exception {
        //Bool for the while loop. Set true if song is valid and found
        boolean valid = false;
        //Loop while the song is not found
        while (!valid) {
            //Ask the user the artist and the song name
            System.out.println(" - - - - - - - - - - - - - - ");
            System.out.println("| Which artist and song name?");
            //Prepend this text for the user input to clear things up
            System.out.print("| [artist] : ");
            //Read the user input of the first line and store it in the var artists
            String artist = new Scanner(System.in).nextLine();
            //Prepend this text for the user input to clear things up
            System.out.print("| [song] : ");
            //Read the user input of the second line and store it in the var song
            String song = new Scanner(System.in).nextLine();
            System.out.println(" - - - - - - - - - - - - - - \n");
            //Initialize the html and htmlAsString variables here so we can access them later on inside a different scope
            List<String> html;

            //Try catch to handle requests errors
            try {
                //Construct the url by adding the artist and the song name
                var url = "https://www.azlyrics.com/lyrics/" + artist.replace(" ", "").toLowerCase() + "/" + song.replace(" ", "").toLowerCase() + ".html";
                //Call the function which fetches the response
                html = getHTML(url);
            } catch (ArithmeticException e) {
                //Print a user friendly error message
                System.out.println(artist + " with the song " + song + " was not found");
                return;
            }

            //If the html is empty. retry
            if (html.isEmpty()) {
                //Print 'error' message
                System.out.println(artist + " with the song " + song + " was not found");

                //Sleep for half a second
                TimeUnit.MILLISECONDS.sleep(500);
                System.out.print("Restarting");
                System.out.print(".");
                //Sleep for half a second
                TimeUnit.MILLISECONDS.sleep(500);
                System.out.print(".");
                //Sleep for half a second
                TimeUnit.MILLISECONDS.sleep(500);
                System.out.print(".");
                //Sleep for half a second
                TimeUnit.MILLISECONDS.sleep(500);

                //'clear' the output
                for (int i = 0; i < 80; i++) System.out.print("\n");
            } else {
                //Since the song was found. stop looping
                valid = true;
                //Call the method to print the lyrics with the html list, the artist and the song as parameters
                printLyrics(html, artist, song);
            }
        }
    }
}