import javax.swing.*;
import java.awt.*;
import java.awt.geom.Ellipse2D;
import java.util.Calendar;
import java.util.Random;

public class ZodiacConstellations extends JPanel {

    private static final int WIDTH = 800;
    private static final int HEIGHT = 800;
    private static final int SUN_RADIUS = 30;
    private static final int PLANET_RADIUS = 10;

    private Random random = new Random();
    private boolean isMercuryRetrograde;
    private boolean isJupiterRetrograde;

    private double sunX, sunY;
    private double mercuryX, mercuryY;
    private double jupiterX, jupiterY;

    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        setBackground(Color.BLACK);
        drawStars(g);
        drawZodiac(g);
        drawSun(g);
        drawPlanets(g);
        drawAstrologyZones(g);
        drawTodayFortune(g);
    }

    private void drawStars(Graphics g) {
        g.setColor(Color.WHITE);
        for (int i = 0; i < 300; i++) {
            int x = random.nextInt(WIDTH);
            int y = random.nextInt(HEIGHT);
            g.fillOval(x, y, 2, 2);
        }
    }

    private void drawZodiac(Graphics g) {
        int centerX = WIDTH / 2;
        int centerY = HEIGHT / 2;
        int radius = 300;

        String[] constellations = {"困敦", "赤奋若", "摄提格", "单阏", "执徐", "大荒落", "敦䍧", "协洽", "涒滩", "作鄂", "掩茂", "大渊献"};
        Color[] constellationColors = {Color.RED, Color.ORANGE, Color.YELLOW, Color.GREEN, Color.CYAN, Color.BLUE,
                Color.MAGENTA, Color.PINK, Color.LIGHT_GRAY, Color.WHITE, Color.DARK_GRAY, Color.GRAY};

        for (int i = 0; i < 12; i++) {
            int x = (int) (centerX + radius * Math.cos(Math.toRadians(i * 30)));
            int y = (int) (centerY - radius * Math.sin(Math.toRadians(i * 30)));

            g.setColor(constellationColors[i]);
            g.drawOval(centerX - radius, centerY - radius, radius * 2, radius * 2);
            g.setColor(Color.YELLOW);
            g.drawOval(centerX - radius + 10, centerY - radius + 10, radius * 2 - 20, radius * 2 - 20);
            g.setColor(Color.WHITE);
            g.drawString(constellations[i], x, y);
        }
    }

    private void drawSun(Graphics g) {
        g.setColor(Color.RED);
        g.fillOval((int) (sunX - SUN_RADIUS), (int) (sunY - SUN_RADIUS), SUN_RADIUS * 2, SUN_RADIUS * 2);
    }

    private void drawPlanets(Graphics g) {
        // Draw Mercury's path
        g.setColor(isMercuryRetrograde ? Color.RED : Color.BLUE);
        g.drawOval((int) (sunX - 100), (int) (sunY - 60), 200, 120);

        // Draw Jupiter's path
        g.setColor(isJupiterRetrograde ? Color.RED : Color.YELLOW);
        g.drawOval((int) (sunX - 160), (int) (sunY - 120), 320, 240);

        // Draw Mercury
        g.setColor(Color.BLUE);
        g.fillOval((int) (mercuryX - PLANET_RADIUS), (int) (mercuryY - PLANET_RADIUS), PLANET_RADIUS * 2, PLANET_RADIUS * 2);

        // Draw Jupiter
        g.setColor(Color.YELLOW);
        g.fillOval((int) (jupiterX - PLANET_RADIUS), (int) (jupiterY - PLANET_RADIUS), PLANET_RADIUS * 2, PLANET_RADIUS * 2);
    }

    private void drawAstrologyZones(Graphics g) {
        g.setColor(Color.RED);
        Graphics2D g2d = (Graphics2D) g;
        Stroke dashed = new BasicStroke(1, BasicStroke.CAP_BUTT, BasicStroke.JOIN_BEVEL, 0, new float[]{9}, 0);
        g2d.setStroke(dashed);

        int centerX = WIDTH / 2;
        int centerY = HEIGHT / 2;
        int radius = 300;

        int ellipseWidth = 2 * radius;
        int ellipseHeight = radius;
        g2d.draw(new Ellipse2D.Double(centerX - ellipseWidth / 2, centerY - ellipseHeight / 2, ellipseWidth, ellipseHeight));

        // Draw Mercury retrograde zones
        if (isMercuryRetrograde) {
            g2d.setColor(Color.RED);
            drawRetrogradeZone(g2d, 100, 60, 200, 120);
            drawRetrogradeZone(g2d, 100, 60, 200, 120);
        }

        // Draw Jupiter retrograde zone
        if (isJupiterRetrograde) {
            g2d.setColor(Color.RED);
            drawRetrogradeZone(g2d, 160, 120, 320, 240);
        }
    }

    private void drawRetrogradeZone(Graphics2D g2d, int centerX, int centerY, int width, int height) {
        g2d.draw(new Ellipse2D.Double(sunX - centerX, sunY - centerY, width, height));
    }

    private void drawTodayFortune(Graphics g) {
        g.setColor(Color.WHITE);
        g.drawString("今日时运", WIDTH / 2 - 40, 20);
    }

    private void updatePositions() {
        Calendar now = Calendar.getInstance();
        int secondsSinceMidnight = now.get(Calendar.HOUR_OF_DAY) * 3600 +
                now.get(Calendar.MINUTE) * 60 +
                now.get(Calendar.SECOND);

        double mercuryAngle = Math.toRadians(secondsSinceMidnight * 0.002);
        double jupiterAngle = Math.toRadians(secondsSinceMidnight * 0.001);

        sunX = WIDTH / 2.0;
        sunY = HEIGHT / 2.0;

        // Determine Mercury's retrograde state based on its angle
        isMercuryRetrograde = (Math.toDegrees(mercuryAngle) % 360) > 180;

        // Determine Jupiter's retrograde state based on its angle
        isJupiterRetrograde = (Math.toDegrees(jupiterAngle) % 360) > 180;

        mercuryX = sunX + 100 * Math.cos(mercuryAngle);
        mercuryY = sunY + 60 * Math.sin(mercuryAngle);

        jupiterX = sunX + 160 * Math.cos(jupiterAngle);
        jupiterY = sunY + 120 * Math.sin(jupiterAngle);
    }

    public static void main(String[] args) {
        JFrame frame = new JFrame("Astrology Simulation");
        ZodiacConstellations simulation = new ZodiacConstellations();
        frame.add(simulation);
        frame.setSize(WIDTH, HEIGHT);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);

        Timer timer = new Timer(1000, e -> {
            simulation.updatePositions();
            simulation.repaint();
        });
        timer.start();
    }
}
