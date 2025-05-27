import javax.swing.*;
import java.awt.*;

public class PPanel extends JPanel {
    private Color objBorderColor = new Color(0, 0, 0, 0);
    private int borderThickness = 2;
    private int radius = 0;
    private float radiusPercent = -1f;

    private boolean borderTop = true;
    private boolean borderBottom = true;
    private boolean borderLeft = true;
    private boolean borderRight = true;

    private Insets padding = new Insets(0, 0, 0, 0); // Default padding

    PPanel() {
        super();
    }

    PPanel(LayoutManager layout) {
        super(layout);
    }

    PPanel(LayoutManager layout, Color color) {
        super(layout);
        setOpaque(false);
        setBackground(color);
    }

    PPanel(LayoutManager layout, Color color, int radius) {
        super(layout);
        setOpaque(false);
        setBackground(color);
        this.radius = radius;
    }

    public void setBorderColor(Color color) {
        this.objBorderColor = color;
        repaint();
    }

    public void setBorderThickness(int thickness) {
        this.borderThickness = thickness;
        repaint();
    }

    public void setRadius(int radius) {
        this.radius = radius;
        repaint();
    }

    public void setRadiusPercent(float radius) {
        this.radiusPercent = radius;
        repaint();
    }

    // New setters for individual borders
    public void setBorderSide(boolean value){
        borderTop = value;
        borderBottom = value;
        borderLeft = value;
        borderRight = value;
    }

    public void setBorderTop(boolean value) {
        borderTop = value;
        repaint();
    }

    public void setBorderBottom(boolean value) {
        borderBottom = value;
        repaint();
    }

    public void setBorderLeft(boolean value) {
        borderLeft = value;
        repaint();
    }

    public void setBorderRight(boolean value) {
        borderRight = value;
        repaint();
    }

    // Method to set padding
    public void setPadding(Insets padding) {
        this.padding = padding;
        revalidate();
        repaint();
    }

    public void setPadding(int top, int left, int bottom, int right) {
        this.padding = new Insets(top, left, bottom, right);
        revalidate(); // Revalidate the layout
        repaint(); // Repaint the panel
    }

    @Override
    public Insets getInsets() {
        return padding;
    }

    @Override
    protected void paintComponent(Graphics grphcs) {
        super.paintComponent(grphcs);
        int arc = radius;

        if (radiusPercent >= 0) {
            int w = getWidth();
            int h = getHeight();
            int minDim = Math.min(w, h);
            arc = (int) (minDim * radiusPercent);
        }

        Graphics2D g2 = (Graphics2D) grphcs.create();
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

        int half = borderThickness / 2;

        // ⬅️ Fill background inside border area
        g2.setColor(getBackground());
        g2.fillRoundRect(
                borderThickness, borderThickness,
                getWidth() - 2 * borderThickness,
                getHeight() - 2 * borderThickness,
                arc, arc
        );

        // ➡️ Draw individual borders after background
        g2.setColor(objBorderColor);
        g2.setStroke(new BasicStroke(borderThickness));

        if (borderTop) {
            g2.drawLine(half, half, getWidth() - half - 1, half);
        }
        if (borderBottom) {
            g2.drawLine(half, getHeight() - half - 1, getWidth() - half - 1, getHeight() - half - 1);
        }
        if (borderLeft) {
            g2.drawLine(half, half, half, getHeight() - half - 1);
        }
        if (borderRight) {
            g2.drawLine(getWidth() - half - 1, half, getWidth() - half - 1, getHeight() - half - 1);
        }

        g2.dispose();
    }

}
