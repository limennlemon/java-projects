/***************************
Purpose: Project2 (main class) reads the student information from the "students.txt" file 
and displays to the console the threshold for the honor society membership (midpoint of 
the average GPA with the highest GPA being 4.0) and the list of students eligible for
membership to the honor society. If the "students.txt" file is missing, an error message
is displayed to the console and the program terminates.
***************************/

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class Project2 {
	
    public static void main(String[] args) {
    	
    	// create Student ArrayList
        ArrayList<Student> students = new ArrayList<>();
        double totalGpa = 0.0;
        
        // Read in data from student.txt file
        // try-catch in place to show an error message for missing students.txt file  
        try {
            File file = new File("students.txt"); // reads data from file
            Scanner scanner = new Scanner(file);

            // parse data from file
            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                String[] data = line.split(" ");
                String name = data[0];
                int creditHours = Integer.parseInt(data[1]);
                int qualityPoints = Integer.parseInt(data[2]);
                String yearOrDegree = data[3];

                // add Juniors or Seniors to student list
                if (yearOrDegree.equals("Junior") || yearOrDegree.equals("Senior")) {
                    Undergraduate student = new Undergraduate(name, creditHours, qualityPoints, yearOrDegree);
                    students.add(student);
                } 
                else // add graduate students to student list
                	{ 
                    Graduate student = new Graduate(name, creditHours, qualityPoints, yearOrDegree);
                    students.add(student);
                }
                // calculate total GPA
                totalGpa = totalGpa + students.get(students.size() - 1).gpa();
            }

        } 
        // if file missing show error message
        catch (FileNotFoundException e) {
            System.out.println("Error: File not found."); // error message
            return;
        }
        
        // calculate average GPA and GPA threshold  
        double averageGpa = totalGpa / students.size();
        Student.setGpaThreshold((4.0 + averageGpa) / 2);
        System.out.println("GPA minimum threshold: " + Student.getGpaThreshold());

        // display students eligible for honor society membership
        System.out.println("Eligible for honor society membership: ");
        for (int i = 0; i < students.size(); i++)  {  
           	Student student = students.get(i);
            if (student.eligibleForHonorSociety()) {
                System.out.println(student);
            }
        }
    }
}

