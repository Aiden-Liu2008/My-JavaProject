class VariableExample 
{
    public static void main(String[] args) 
    {
        int myNumber;

        myNumber = 66;

        double pi = 3.1415926535897932;

        String message;

        message = "Hello, world!";

        System.out.println("myNumber: " + myNumber);
        System.out.println("Pie: " + pi);
        System.out.println("message: " + message);

        int result = myNumber * 2;
        System.out.println("Result of myNumber * 2: " + result);

        double circleArea = pi * (myNumber * myNumber);
        System.out.println("Area of a circle with myNumber as radius: " + circleArea);
    }
}
