import javax.swing.*;
import java.awt.*;

public class PPasswordField extends JPasswordField {
    private Insets padding = new Insets(8, 10, 8, 10);
    private Color borderColor = new Color(0, 0, 0, 0);
    private int radius;
    private float radiusPercent = -1f;
    private String placeholder;

    public PPasswordField() {
        super();
        setBorder(null);
        setOpaque(false);
        setFont(new Font(PLabel.FONTSTYLE, Font.PLAIN, PLabel.HEADING3));
    }

    public PPasswordField(int columns) {
        super(columns);
        setBorder(null);
        setOpaque(false);
        setFont(new Font(PLabel.FONTSTYLE, Font.PLAIN, PLabel.HEADING3));
    }

    @Override
    public Insets getInsets() {
        return padding;
    }

    public void setPadding(Insets insets) {
        this.padding = insets;
        revalidate();
        repaint();
    }

    public void setBorderColor(Color color) {
        this.borderColor = color;
        repaint();
    }

    public void setRadius(int radius) {
        this.radius = radius;
        repaint();
    }

    public void setRadiusPercent(float percent) {
        this.radiusPercent = percent;
        repaint();
    }

    public void setBold(boolean isBold) {
        setFont(getFont().deriveFont(isBold ? Font.BOLD : Font.PLAIN));
    }

    public void setItalic(boolean isItalic) {
        setFont(getFont().deriveFont(isItalic ? Font.ITALIC : Font.PLAIN));
    }

    public void setBoldItalic(boolean isBoldItalic) {
        setFont(getFont().deriveFont(isBoldItalic ? Font.ITALIC | Font.BOLD : Font.PLAIN));
    }

    public void setFontSize(float fontSize) {
        setFont(getFont().deriveFont(fontSize));
    }

    public void setPlaceholder(String placeholder) {
        this.placeholder = placeholder;
        repaint();
    }

    @Override
    protected void paintComponent(Graphics g) {
        int arc = radius;
        if (radiusPercent >= 0) {
            int minDim = Math.min(getWidth(), getHeight());
            arc = (int) (minDim * radiusPercent);
        }

        Graphics2D g2 = (Graphics2D) g.create();
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

        // Draw border
        g2.setColor(borderColor);
        g2.fillRoundRect(0, 0, getWidth(), getHeight(), arc, arc);

        // Draw inner background
        g2.setColor(getBackground());
        g2.fillRoundRect(2, 2, getWidth() - 4, getHeight() - 4, arc, arc);

        g2.dispose();

        // Call super AFTER painting background
        super.paintComponent(g);

        // Draw placeholder text only if field is empty
        if (getPassword().length == 0 && placeholder != null) {
            Graphics2D g2d = (Graphics2D) g.create();
            g2d.setFont(getFont());
            g2d.setColor(Color.GRAY);
            FontMetrics fm = g2d.getFontMetrics();

            int x = padding.left + 2;
            int y = (getHeight() - fm.getHeight()) / 2 + fm.getAscent();

            g2d.drawString(placeholder, x, y);
            g2d.dispose();
        }
    }
}
