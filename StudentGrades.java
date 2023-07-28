import java.util.Scanner;

public class StudentGrades 
{

    public static void main(String[] args)
     {
        Scanner scanner = new Scanner(System.in);
        
        
        int numStudents = 5; 
        int[] grades = new int[numStudents];
        
    
        System.out.println("Enter the grades of " + numStudents + " students:");
        for (int i = 0; i < numStudents; i++) 
        {
            System.out.print("Student " + (i + 1) + ": ");
            grades[i] = scanner.nextInt();
        }
        
        
        int sum = 0;
        for (int grade : grades) 
        {
            sum += grade;
        }
        double average = (double) sum / numStudents;
        System.out.println("Average Grade: " + average);
        
        
        int highest = grades[0];
        int lowest = grades[0];
        for (int i = 1; i < numStudents; i++) 
        {
            if (grades[i] > highest) 
            {
                highest = grades[i];
            }
            if (grades[i] < lowest) 
            {
                lowest = grades[i];
            }
        }
        System.out.println("Highest Grade: " + highest);
        System.out.println("Lowest Grade: " + lowest);
        
        
        System.out.println("Grades of all students:");
        for (int i = 0; i < numStudents; i++) 
        {
            System.out.println("Student " + (i + 1) + ": " + grades[i]);
        }
    }
}
