import java.awt.*;
import javax.swing.*;

public class LeafFunctionDrawer extends JFrame {
    private static final int WIDTH = 800;
    private static final int HEIGHT = 800;
    private static final int SCALE_FACTOR = 100;
    private static final double STEP_SIZE = 0.1;

    private LeafFunction leafFunction;

    public LeafFunctionDrawer() {
        setTitle("Leaf Function Drawer");
        setSize(WIDTH, HEIGHT);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        leafFunction = new LeafFunction(SCALE_FACTOR, STEP_SIZE);
    }

    @Override
    public void paint(Graphics g) {
        super.paint(g);

        g.setColor(Color.BLACK);

        Point[] points = leafFunction.getPoints();

        for (int i = 0; i < points.length - 1; i++) {
            int x1 = (int) (points[i].getX() + WIDTH / 2);
            int y1 = (int) (HEIGHT / 2 - points[i].getY());
            int x2 = (int) (points[i + 1].getX() + WIDTH / 2);
            int y2 = (int) (HEIGHT / 2 - points[i + 1].getY());

            g.drawLine(x1, y1, x2, y2);
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            LeafFunctionDrawer drawer = new LeafFunctionDrawer();
            drawer.setVisible(true);
        });
    }
}

class LeafFunction {
    private int scaleFactor;
    private double stepSize;
    private Point[] points;

    public LeafFunction(int scaleFactor, double stepSize) {
        this.scaleFactor = scaleFactor;
        this.stepSize = stepSize;
        calculatePoints();
    }

    private void calculatePoints() {
        int numPoints = (int) (2 * Math.PI / stepSize) + 1;
        points = new Point[numPoints];

        for (int i = 0; i < numPoints; i++) {
            double t = i * stepSize;
            double x = scaleFactor * Math.cos(t) * Math.sin(t);
            double y = scaleFactor * Math.cos(t);
            points[i] = new Point(x, y);
        }
    }

    public Point[] getPoints() {
        return points;
    }
}

class Point {
    private double x;
    private double y;

    public Point(double x, double y) {
        this.x = x;
        this.y = y;
    }

    public double getX() {
        return x;
    }

    public double getY() {
        return y;
    }
}
