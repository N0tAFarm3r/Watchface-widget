import javafx.application.Application;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.control.Button;
import javafx.scene.layout.VBox;
import javafx.geometry.Pos;
import javafx.event.EventHandler;
import javafx.event.ActionEvent;
import javax.swing.*;
import java.awt.*;
import java.util.Calendar;

public class WatchFace extends JFrame {

    public WatchFace() {
        setTitle("Analog Watch");
        setSize(400, 420);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null); // Center the window
        add(new WatchPanel());
        setVisible(true);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(AnalogWatch::new);
    }
}

class WatchPanel extends JPanel {

    public WatchPanel() {
        // Redraw every second
        Timer timer = new Timer(1000, e -> repaint());
        timer.start();
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        // Setup graphics
        Graphics2D g2d = (Graphics2D) g;
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        int w = getWidth();
        int h = getHeight();

        // Center and radius
        int centerX = w / 2;
        int centerY = h / 2;
        int radius = Math.min(w, h) / 2 - 20;

        // Draw clock face
        g2d.setColor(Color.WHITE);
        g2d.fillOval(centerX - radius, centerY - radius, radius * 2, radius * 2);
        g2d.setColor(Color.BLACK);
        g2d.drawOval(centerX - radius, centerY - radius, radius * 2, radius * 2);

        // Draw tick marks
        for (int i = 0; i < 60; i++) {
            double angle = Math.toRadians(i * 6);
            int inner = i % 5 == 0 ? radius - 15 : radius - 5;
            int x1 = (int) (centerX + inner * Math.sin(angle));
            int y1 = (int) (centerY - inner * Math.cos(angle));
            int x2 = (int) (centerX + radius * Math.sin(angle));
            int y2 = (int) (centerY - radius * Math.cos(angle));
            g2d.drawLine(x1, y1, x2, y2);
        }

        // Get current time
        Calendar calendar = Calendar.getInstance();
        int sec = calendar.get(Calendar.SECOND);
        int min = calendar.get(Calendar.MINUTE);
        int hour = calendar.get(Calendar.HOUR);

        // Calculate angles
        double secAngle = Math.toRadians(sec * 6);
        double minAngle = Math.toRadians(min * 6 + sec * 0.1);
        double hourAngle = Math.toRadians((hour % 12) * 30 + min * 0.5);

        // Draw hour hand
        drawHand(g2d, centerX, centerY, hourAngle, radius * 0.5, 6, Color.BLACK);
        // Draw minute hand
        drawHand(g2d, centerX, centerY, minAngle, radius * 0.75, 4, Color.BLUE);
        // Draw second hand
        drawHand(g2d, centerX, centerY, secAngle, radius * 0.9, 2, Color.RED);

        // Center circle
        g2d.setColor(Color.BLACK);
        g2d.fillOval(centerX - 5, centerY - 5, 10, 10);
    }

    private void drawHand(Graphics2D g2d, int x, int y, double angle, double length, int width, Color color) {
        int x2 = (int) (x + length * Math.sin(angle));
        int y2 = (int) (y - length * Math.cos(angle));
        g2d.setColor(color);
        g2d.setStroke(new BasicStroke(width));
        g2d.drawLine(x, y, x2, y2);
    }
}