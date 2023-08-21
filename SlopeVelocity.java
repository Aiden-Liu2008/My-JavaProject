import java.util.Scanner;

public class SlopeVelocity {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter the angle of the slope (in degrees): ");
        double theta = Math.toRadians(scanner.nextDouble());
        System.out.print("Enter the height of the slope (in meters): ");
        double h = scanner.nextDouble();
        System.out.print("Enter the coefficient of kinetic friction: ");
        double mu_k = scanner.nextDouble();
        double g = 9.8;
        double v = Math.sqrt(2 * g * h * (Math.sin(theta) - mu_k * Math.cos(theta)));
        System.out.println("The instantaneous velocity of the object is " + v + " m/s");
    }
}
