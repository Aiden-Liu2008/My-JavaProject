import javax.swing.*;
import java.awt.*;
import java.awt.geom.GeneralPath;

public class CalculusFunctionDrawer extends JFrame 
{

    private final int scale = 50;
    private final double step = 0.1;

    public CalculusFunctionDrawer() 
    {
        setTitle("Calculus Function Drawer");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(800, 600);
        setLocationRelativeTo(null);
        setVisible(true);
    }

    @Override
    public void paint(Graphics g) 
    {
        super.paint(g);
        drawFunction(g, "exp(x)"); 
    }

    private void drawFunction(Graphics g, String function) 
    {
        g.setColor(Color.BLUE);

        GeneralPath path = new GeneralPath();

        for (int i = 0; i < getWidth() / step; i++) {
            double x = (i - getWidth() / 2.0) / scale;
            double y = evaluateFunction(function, x);
            int pixelX = (int) (x * scale + getWidth() / 2.0);
            int pixelY = (int) (getHeight() / 2.0 - y * scale);

            if (i == 0) {
                path.moveTo(pixelX, pixelY);
            } else {
                path.lineTo(pixelX, pixelY);
            }
        }

        g.setColor(Color.RED);
        ((Graphics2D) g).draw(path);
    }

    private double evaluateFunction(String function, double x) {
        switch (function) {
            case "sin(x)":
                return Math.sin(x);
            case "cos(x)":
                return Math.cos(x);
            case "tan(x)":
                return Math.tan(x);
            case "x^2":
                return x * x;
            case "sqrt(x)":
                return Math.sqrt(Math.abs(x));
            case "exp(x)": // Exponential function: e^x
                return Math.exp(x);
            case "log(x)": // Natural logarithm function: ln(x)
                return Math.log(x);
            case "abs(x)": // Absolute value function: |x|
                return Math.abs(x);
            case "1/x": // Reciprocal function: 1/x
                return 1.0 / x;
            case "arcsin(x)": // Inverse sine function: arcsin(x)
                return Math.asin(x);
            case "arccos(x)": // Inverse cosine function: arccos(x)
                return Math.acos(x);
            case "arctan(x)": // Inverse tangent function: arctan(x)
                return Math.atan(x);
            case "sinh(x)": // Hyperbolic sine function: sinh(x)
                return Math.sinh(x);
            case "cosh(x)": // Hyperbolic cosine function: cosh(x)
                return Math.cosh(x);
            case "tanh(x)": // Hyperbolic tangent function: tanh(x)
                return Math.tanh(x);
            // Add more functions here as needed
            default:
                throw new IllegalArgumentException("Unknown function: " + function);
        }
    }
    
    private double evaluateDerivative(String function, double x) 
    {
        switch (function) 
        {
            case "sin(x)":
                return Math.cos(x);
            case "cos(x)":
                return -Math.sin(x);
            case "tan(x)":
                return 1.0 / (Math.cos(x) * Math.cos(x));
            case "x^2":
                return 2 * x;
            case "sqrt(x)":
                return 1.0 / (2 * Math.sqrt(Math.abs(x))); 
            case "exp(x)": 
                return Math.exp(x);
            case "log(x)": 
                return 1.0 / x;
            case "abs(x)": 
                return Math.signum(x);
            case "1/x": 
                return -1.0 / (x * x);
            case "arcsin(x)": 
                return 1.0 / Math.sqrt(1 - x * x);
            case "arccos(x)": 
                return -1.0 / Math.sqrt(1 - x * x);
            case "arctan(x)":
                return 1.0 / (1 + x * x);
            case "sinh(x)": 
                return Math.cosh(x);
            case "cosh(x)": 
                return Math.sinh(x);
            case "tanh(x)": 
                double coshSquared = Math.cosh(x) * Math.cosh(x);
                return 1.0 / (coshSquared);
            default:
                throw new IllegalArgumentException("Unknown function: " + function);
        }
    }    

    public static void main(String[] args) {
        SwingUtilities.invokeLater(CalculusFunctionDrawer::new);
    }
}
