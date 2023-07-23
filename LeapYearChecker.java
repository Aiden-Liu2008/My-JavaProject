import java.util.InputMismatchException;
import java.util.Scanner;

public class LeapYearChecker 
{
    public static void main(String[] args) 
    {
        Scanner scanner = new Scanner(System.in);

        int year = 0;

        try 
        {
            // Input year from the user
            System.out.print("Enter a year: ");
            year = scanner.nextInt();
        } catch (InputMismatchException e) 
        {
            System.out.println("Invalid input. Please enter a valid year as a whole number.");
            scanner.close();
            return;
        }

        boolean isLeapYear = false;

        if (year % 4 == 0) 
        {
            if (year % 100 != 0 || year % 400 == 0) 
            {
                isLeapYear = true;
            }
        }

        if (isLeapYear) 
        {
            System.out.println(year + " is a Leap Year.");
        } else {
            System.out.println(year + " is Not a Leap Year.");
        }

        scanner.close();
    }
}

