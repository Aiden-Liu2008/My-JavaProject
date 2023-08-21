import java.awt.*;
import java.util.ArrayList;
import javax.swing.*;

public class PrimeRegression extends JPanel {

    private ArrayList<Integer> primes = new ArrayList<>();
    private double slope;
    private double intercept;

    public PrimeRegression() {
        generatePrimes(100);
        calculateLinearRegression();
        JFrame frame = new JFrame("Prime Numbers and Linear Regression");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.add(this);
        frame.setSize(800, 600);
        frame.setVisible(true);
    }

    private void generatePrimes(int n) {
        for (int i = 2; i <= n; i++) {
            if (isPrime(i)) {
                primes.add(i);
            }
        }
    }

    private boolean isPrime(int num) {
        if (num <= 1) {
            return false;
        }
        if (num % 2 == 0) {
            return num == 2;
        }
        for (int i = 3; i <= Math.sqrt(num); i += 2) {
            if (num % i == 0) {
                return false;
            }
        }
        return true;
    }

    private void calculateLinearRegression() {
        int n = primes.size();
        int sumX = 0;
        int sumY = 0;
        int sumXY = 0;
        int sumX2 = 0;

        for (int i = 0; i < n; i++) {
            int x = primes.get(i) * 6;
            int y = getHeight() - primes.get(i) * 5;
            sumX += x;
            sumY += y;
            sumXY += x * y;
            sumX2 += x * x;
        }

        slope = (n * sumXY - sumX * sumY) / (n * sumX2 - sumX * sumX);
        intercept = (sumY - slope * sumX) / n;
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        // Draw the prime number points
        g.setColor(Color.blue);
        for (Integer prime : primes) {
            int x = prime * 6;
            int y = getHeight() - prime * 5;
            g.fillOval(x, y, 5, 5);
        }

        // Draw the regression line
        int x1 = 0;
        int y1 = getHeight() - (int) intercept;
        int x2 = getWidth();
        int y2 = (int) (slope * x2 + intercept);

        g.setColor(Color.red);
        g.drawLine(x1, y1, x2, y2);

        // Draw labels
        g.setColor(Color.black);
        g.drawString("Prime Numbers", 10, 20);
        g.drawString("Linear Regression", 10, 35);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new PrimeRegression());
    }
}
