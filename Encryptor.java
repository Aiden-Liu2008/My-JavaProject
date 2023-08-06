import java.math.BigDecimal;
import java.math.BigInteger;
import java.math.MathContext;
import java.util.Scanner;

public class Encryptor {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        try {
            // Get user input for the message to be encrypted
            System.out.print("Enter the message to be encrypted: ");
            String message = scanner.nextLine();

            // Convert the message to an array of Unicode values
            int[] unicodeValues = new int[message.length()];
            for (int i = 0; i < message.length(); i++) {
                unicodeValues[i] = message.codePointAt(i);
            }

            // Get user input for p
            System.out.print("Enter your password (p): ");
            int p = scanner.nextInt();

            // Calculate q as the square of p
            int q = findNearestValidQ(p);

            // Calculate the difference between p^2 and the calculated q
            int difference = q - findNearestValidQ(p);

            // Generate p and q times prime numbers
            BigInteger primeP = generatePrime(p);
            BigInteger primeQ = generatePrime(q);

            // Encrypt and print the encrypted coordinates
            for (int unicode : unicodeValues) {
                BigInteger encryptedValue = BigInteger.valueOf(unicode)
                    .multiply(primeP)
                    .multiply(primeQ);

                BigDecimal eulerPhi = calculateEulerPhi(encryptedValue.intValue());
                BigInteger eulerPhiBigInt = eulerPhi.toBigInteger();

                int x = encryptedValue.mod(eulerPhiBigInt.abs()).intValue(); // Use absolute value for modulus
                int y = eulerPhiBigInt.abs().intValue(); // Use absolute value for y

                // Print the coordinates and the difference
                System.out.println(x + ", " + y + ", " + difference);
            }

            // Print the user input key p and an empty line as separator
            System.out.println("User input key: " + p);
            System.out.println("absolute value: 0");
            System.out.println();
        } catch (Exception e) {
            System.out.println("An error occurred: " + e.getMessage());
        } finally {
            scanner.close();
        }
    }

    private static BigInteger generatePrime(int number) {
        BigInteger prime = BigInteger.valueOf(number);
        while (!prime.isProbablePrime(100)) {
            prime = prime.add(BigInteger.ONE);
        }
        return prime;
    }

    private static BigDecimal calculateEulerPhi(int n) {
        BigDecimal result = new BigDecimal(n);

        for (int i = 2; i * i <= n; i++) {
            if (n % i == 0) {
                while (n % i == 0) {
                    n /= i;
                }
                BigDecimal factor = BigDecimal.ONE.subtract(BigDecimal.ONE.divide(new BigDecimal(i), MathContext.DECIMAL128));
                result = result.multiply(factor);
            }
        }

        if (n > 1) {
            BigDecimal factor = BigDecimal.ONE.subtract(BigDecimal.ONE.divide(new BigDecimal(n), MathContext.DECIMAL128));
            result = result.multiply(factor);
        }

        return result.setScale(0, BigDecimal.ROUND_HALF_UP);
    }

    private static int findNearestValidQ(int p) {
        int q = p * p;
        return q;
    }
}
