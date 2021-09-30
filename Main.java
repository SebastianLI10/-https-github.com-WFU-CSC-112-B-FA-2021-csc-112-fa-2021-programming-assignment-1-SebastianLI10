/*Sebastian Lopez-Ibanez
CSC112 Fall 2021
Programming Assignment 1
September 29, 2021
This program reads a file of students, their scores, and ID numbers. it formats it in a specified way for output
The only special function/command needed to run this program is to declare how many students will be grouped and averaged 10 - 20
*/
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.Random;
import java.util.Scanner;


public class Main {
    public static void main(String[] args) throws IOException {
        Scanner scnr = new Scanner(System.in);
        FileInputStream fis = new FileInputStream("input2.txt");
        Scanner input = null;
        input = new Scanner(fis);

        FileOutputStream fos = new FileOutputStream("output.txt");
        PrintWriter writer = new PrintWriter(fos);
        writer.println("How many Students will we be working with today? "); // special command

        int numStudents = scnr.nextInt();

        String[] middleName = new String[numStudents];
        String[] firstName = new String[numStudents];
        String[] lastName = new String[numStudents];
        int[] studentID = new int[numStudents];
        int[] avgScores = new int[numStudents];
        String[] tempLastNames = new String[numStudents];

        double avgOfAvgs = 0.0;
        int i;
        int j = 0;
        double avg;
        double sum;
        /*
        looping through the input file, assigning firstName, middleName, and lastName, as well as for student ID
        takes the next 5 intergers and computes an average of the 5 integers representing the test scores
         */
        for (i = 0; i < numStudents; i++) {
            firstName[i] = input.next();
            middleName[i] = input.next();
            lastName[i] = input.next();

            studentID[i] = input.nextInt();

            sum = 0;
            for (j = 0; j < 5; j++) {
                sum = sum + input.nextInt();
            }

            avg = sum / 5.0;
            avgScores[i] = (int) avg;
        }
        /*
        looping through numStudents
        assigning tempLastName[i] = lastName[i]
        using Arrays.sort to alphabetize
         */
        for (i = 0; i < numStudents; i++) {
            tempLastNames[i] = lastName[i];
        }
        Arrays.sort(tempLastNames);
        /*
       nested for loop to print the student names and scores in the specified format
         */
        for (i = 0; i < numStudents; i++) {
            for (j = 0; j < numStudents; j++) {
                if (tempLastNames[i].equals(lastName[j])) {
                    writer.println(lastName[j] + ", " + firstName[j] + " " + middleName[j]);
                    writer.println(studentID[j]);
                    writer.println(avgScores[j]);
                }
            }
        }
        writer.println("**************************************");
        /*
        using branches to set a condition for an even number of groups of 4 for all groups
         */
        String groupLeader = null;
        int count = 1;
        int group = numStudents % 4;
        if (group == 0) {
            j = 0;
            /*
            for loop to print the groups, compute avgOfAvgs, making a groupLeader, resetting avgOfAvgs to 0 at the end
            k is used to make sure the names are not repeating.
            primary for loop not needed because groups divide evenly
             */
            for (i = 0; i < numStudents / 4; i++) {
                writer.println("Group " + count);
                ++count;
                int k = j + 4;
                for (; j < k; j++) {
                    writer.println(firstName[j] + " " + middleName[j] + " " + lastName[j]);
                    avgOfAvgs += (avgScores[j] / 4.0);
                    groupLeader = firstName[j] + " " + middleName[j] + " " + lastName[j];
                }
                writer.println("Group Leader: " + groupLeader);
                writer.println("Group average: " + avgOfAvgs);
                avgOfAvgs = 0.0;
            }
        } else if (group == 1) {
            j = 0;
            writer.println("Group " + 1);
             /*
            for loop to print the first group and the first group only, compute avgOfAvgs, making a groupLeader, resetting avgOfAvgs to 0 at the end
            i < 5 because this loop only prints out the first group, which is a group of 5 because there is one extra student
             */
            for (i = 0; i < 5; i++) {
                writer.println(firstName[i] + " " + middleName[i] + " " + lastName[i]);
                avgOfAvgs += avgScores[i] / 5;
                groupLeader = firstName[i] + " " + middleName[i] + " " + lastName[i];
            }
            writer.println("Group Leader: " + groupLeader);
            writer.println("Group average: " + avgOfAvgs);
            avgOfAvgs = 0;
            /*
            for loop to print the remaining groups, compute avgOfAvgs, making a groupLeader, resetting avgOfAvgs to 0 at the end
            k1 is used to make the bounds
            /4 -1 for the bounds of numStudents because we are making one less group of 4
            j + 5 in order to use the information in the array index 5 indices after j; it is 5 indices after j because the first loop uses the names at positions 0 - 4
             */
            for (i = 0; i < numStudents / 4 - 1; i++) {
                writer.println("Group " + (count + 1));
                ++count;
                int k1 = j + 4;
                for (; j < k1; j++) {
                    writer.println(firstName[j + 5] + " " + middleName[j + 5] + " " + lastName[j + 5]);
                    avgOfAvgs += avgScores[j + 5] / 4.0;
                    groupLeader = firstName[j + 5] + " " + middleName[j + 5] + " " + lastName[j + 5];
                }
                writer.println("Group Leader: " + groupLeader);
                writer.println("Group average: " + avgOfAvgs);
                avgOfAvgs = 0;
            }
        }
        if (group == 2) {
            j = 0;
            writer.println("Group " + 1);
            /*
            for loop to print the first group and the first group only, compute avgOfAvgs, making a groupLeader, resetting avgOfAvgs to 0 at the end
            i < 2 because this loop only prints out the first group, which is a group of 2 because there are two extra students
             */
            for (i = 0; i < 2; i++) {
                writer.println(firstName[i] + " " + middleName[i] + " " + lastName[i]);
                avgOfAvgs += avgScores[i] / 2.0;
                groupLeader = firstName[i] + " " + middleName[i] + " " + lastName[i];
            }
            writer.println("Group Leader: " + groupLeader);
            writer.println("Group average: " + avgOfAvgs);
            avgOfAvgs = 0;
            /*
            for loop to print the remaining groups, compute avgOfAvgs, making a groupLeader, resetting avgOfAvgs to 0 at the end
            k2 is used to make the bounds
            j + 2 in order to use the information in the array index 2 indices after j; it is 2 indices after j because the first loop uses the names at positions 0 - 1
             */
            for (i = 0; i < numStudents / 4; i++) {
                writer.println("Group " + (count + 1));
                ++count;
                int k2 = j + 4;
                for (; j < k2; j++) {
                    writer.println(firstName[j + 2] + " " + middleName[j + 2] + " " + lastName[j + 2]);
                    avgOfAvgs += avgScores[j + 2] / 4.0;
                    groupLeader = firstName[j + 2] + " " + middleName[j + 2] + " " + lastName[j + 2];
                }
                writer.println("Group Leader: " + groupLeader);
                writer.println("Group average: " + avgOfAvgs);
                avgOfAvgs = 0;
            }
        } else if (group == 3) {
            j = 0;
            writer.println("Group " + 1);
             /*
            for loop to print the first group and the first group only, compute avgOfAvgs, making a groupLeader, resetting avgOfAvgs to 0 at the end
            i < 3 because this loop only prints out the first group, which is a group of 3 because there are 3 extra students
             */
            for (i = 0; i < 3; i++) {
                writer.println(firstName[i] + " " + middleName[i] + " " + lastName[i]);
                avgOfAvgs += avgScores[i] / 3.0;
                groupLeader = firstName[i] + " " + middleName[i] + " " + lastName[i];
            }
            writer.println("Group Leader: " + groupLeader);
            writer.println("Group average: "+ avgOfAvgs);
            avgOfAvgs = 0;
            /*
            for loop to print the remaining groups, compute avgOfAvgs, making a groupLeader, resetting avgOfAvgs to 0 at the end
            k3 is used to make the bounds
            j + 3 in order to use the information in the array index 3 indices after j; it is 3 indices after j because the first loop uses the names at positions 0 - 2
             */
            for (i = 0; i < numStudents / 4; i++) {
                writer.println("Group " + (count + 1));
                ++count;
                int k3 = j + 4;
                for (; j < k3; j++) {
                    writer.println(firstName[j + 3] + " " + middleName[j + 3] + " " + lastName[j + 3]);
                    avgOfAvgs += avgScores[j + 3] / 4.0;
                    groupLeader = firstName[j + 3] + " " + middleName[j + 3] + " " + lastName[j + 3];
                }
                writer.println("Group Leader: " + groupLeader);
                writer.println("Group average: " + avgOfAvgs);
                avgOfAvgs = 0;
            }
        }
        writer.close();
    }
}





