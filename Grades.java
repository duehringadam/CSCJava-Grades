/**
 * Created by aeduehring on 3/27/2019.
 */
import java.io.File;
import java.io.IOException;
import java.nio.file.Paths;
import java.util.Scanner;

public class Grades {
    public static void main(String [] args) throws IOException {

        //declare our files for names and grades
        File fileNames = Paths.get(".", "resources", "Names.txt").normalize().toFile();
        File fileGrades = Paths.get(".", "resources", "Grades.txt").normalize().toFile();


        if (fileNames.exists() )                          // check that the file exists
        {                                             // before trying to create a
            // Scanner to read the file
            // Create a Scanner from the file.
            // This statement can cause a FileNotFoundException.
            Scanner namesInFile = new Scanner( fileNames );
            Scanner gradesInFile = new Scanner( fileGrades );
            Scanner userInput = new Scanner(System.in);


            // For each line in the file, read in the line and display it with the line number
            int lineNum = 0;

            // Use the results of calling the hasNext method to
            // determine if you are at the end of the file before
            // reading the next line of the file.
            String[] names = new String[20]; //declare our names array
            int[] grades = new int[20];
            while ( namesInFile.hasNext() )
            {
                names[lineNum] = namesInFile.nextLine();   // read the next line
                grades[lineNum] = gradesInFile.nextInt(); //this will read the next int from grades.txt

                // Output the line read to the screen for the user
                System.out.println( lineNum + ": " + names[lineNum]);
                lineNum++; //increment line num after each line is read

            }
            // When we're done reading the file,
            // close the Scanner object attached to the file
            namesInFile.close();
            gradesInFile.close();

            //print out the methods
            System.out.println("The number of students who earned an A is: " + findnumberofAs(grades)); //will print amount of A's earned
            System.out.println("The number of students who earned a B is: " + findnumberofBs(grades));//will print amount of B's earned
            System.out.println("The highest grade is: " + findMaxGrade(grades));
            System.out.println("The lowest grade is: " + findMinGrade(grades));
            System.out.println("The average grade is: " + findAverageGrade(grades));

            //prompt a loop that will allow the user to search for a specific student which will give number grade and letter grade

                System.out.println("Search for a specific student?");
                String validate = userInput.nextLine();
                if (validate.equalsIgnoreCase("No"))
                {
                    System.exit(0);
                }
                while (validate.equalsIgnoreCase("Yes")) {
                    //prompt the user for a specific student name
                        System.out.println("Please enter a student name: ");
                        String searchName = userInput.nextLine();
                        boolean found = false;
                        for (int i = 0; i < names.length; i++)
                        {
                            if (names[i].equalsIgnoreCase(searchName))
                            {
                                //if the user input matches one in the names array, will output the following
                                found = true;
                                System.out.println(searchName + "'s grade is: " + grades[i] + " which is a " + findLetterGrade(grades[i]));

                            }
                        }
                        if (found)
                        {
                            System.out.println("Would you like to search for another? Yes or No");
                            validate = userInput.nextLine();
                        }
                        else if (!found) //if user input does not match, output the following
                        {
                            System.out.println(searchName + " was not found! Try again? Yes or No");
                            validate = userInput.nextLine();
                        }

                }
            }
        }


    //this is our method for finding the average grade
    public static double findAverageGrade (int[] g)
    {
        int sum = 0;
        for(int value: g)
        {
            sum += value; //creates a sum of all the grades

        }
        return (sum / g.length); //returns the value of the sum divided by the array length
    }
    public static int findMaxGrade (int[] g) //this is our method for finding the highest grade
    {
        int maxValue = g[0];
        //for loop that will read the grades in the array and return the biggest
        for(int i=1; i < g.length; i++)
        {
            if(g[i] > maxValue)
            {
                maxValue = g[i];
            }
        }
        return maxValue;
    }

    public static int findMinGrade(int[] g) //this is our method for finding the lowest grade
    {
        int minValue = g[0];
        //for loop that will read the grades in the array and return the smallest
        for(int i=1; i< g.length; i++)
        {
            if(g[i] < minValue)
            {
                minValue = g[i];
            }
        }
        return minValue;
    }

    //this is our method for finding the number of A's earned
    public static int findnumberofAs (int[] g)
    {
        int totalA = 0;
        //for loop that will count the number of A's in the array
        for (int i = 0; i<g.length; i++)
        {
            if (findLetterGrade(g[i])== 'A')
            {
                totalA++;

            }
        }
        return totalA;

    }

    //this is our method for finding the number of B's earned
    public static int findnumberofBs (int[] g)
    {
        int totalB = 0;
        //for loop that will count the number of B's in the array
        for (int i = 0; i<g.length; i++)
        {
            if (findLetterGrade(g[i])== 'B')
            {
                totalB++;

            }
        }
        return totalB;

    }

    //this is our method for finding the letter grades according to score
    public static char findLetterGrade (int score)
    {
        //if else statements to return letter grades according to score
        if (score >= 90)
            return 'A';
        else if (score >= 80)
            return 'B';
        else if (score >= 70)
            return 'C';
        else if (score >= 60)
            return 'D';
        else
            return 'F';

    }
}
