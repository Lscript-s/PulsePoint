import javax.swing.*;
import java.awt.*;

public class PTextField extends JTextField {
    private Insets padding = new Insets(8, 10, 8, 10);
    private Color borderColor = new Color(0,0,0,0);
    private int radius;
    private float radiusPercent = -1f; // Use -1 if not using percentage
    private String placeholder;

    PTextField(){
        super();
        setBorder(null);
        setOpaque(false);
        setFont(new Font(PLabel.FONTSTYLE, Font.PLAIN,PLabel.HEADING3));
    }

    PTextField(int columns) {
        super(columns);
        setBorder(null);
        setOpaque(false);
        setFont(new Font(PLabel.FONTSTYLE, Font.PLAIN,PLabel.HEADING3));
    }

    PTextField(int columns, int radius) {
        super(columns);
        setBorder(null);
        setOpaque(false);
        setRadius(radius);
        setFont(new Font(PLabel.FONTSTYLE, Font.PLAIN,PLabel.HEADING3));
    }

    PTextField(int columns, int radius, Color borderColor) {
        super(columns);
        setBorder(null);
        setOpaque(false);
        setRadius(radius);
        setBorderColor(borderColor);
        setFont(new Font(PLabel.FONTSTYLE, Font.PLAIN,PLabel.HEADING3));
    }

    PTextField(String placeholder, int radius, Color borderColor) {
        super();
        setPlaceholder(placeholder);
        setBorder(null);
        setOpaque(false);
        setRadius(radius);
        setBorderColor(borderColor);
        setFont(new Font(PLabel.FONTSTYLE, Font.PLAIN,PLabel.HEADING3));
        setName(placeholder);
    }

    PTextField(String placeholder, int radius) {
        super();
        setPlaceholder(placeholder);
        setBorder(null);
        setOpaque(false);
        setRadius(radius);
        setFont(new Font(PLabel.FONTSTYLE, Font.PLAIN,PLabel.HEADING3));
    }

    PTextField(String placeholder) {
        super();
        setPlaceholder(placeholder);
        setBorder(null);
        setOpaque(false);
        setFont(new Font(PLabel.FONTSTYLE, Font.PLAIN,PLabel.HEADING3));
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

    public void setBold(boolean isBold){
        if(isBold){
            this.setFont(this.getFont().deriveFont(Font.BOLD));
        }else{
            this.setFont(this.getFont().deriveFont(Font.PLAIN));
        }
    }

    public void setItalic(boolean isItalic){
        if(isItalic){
            this.setFont(this.getFont().deriveFont(Font.ITALIC));
        }else{
            this.setFont(this.getFont().deriveFont(Font.PLAIN));
        }
    }

    public void setBoldItalic(boolean isBoldItalic){
        if(isBoldItalic){
            this.setFont(this.getFont().deriveFont(Font.ITALIC | Font.BOLD));
        }else{
            this.setFont(this.getFont().deriveFont(Font.PLAIN));
        }
    }

    public void setFontSize(float fontSize){
        this.setFont(this.getFont().deriveFont(fontSize));
    }

    public void setPlaceholder(String placeholder) {
        this.placeholder = placeholder;
        repaint();
    }

    @Override
    protected void paintComponent(Graphics grphcs) {
        int arc = radius;

        if (radiusPercent >= 0) {
            int w = getWidth();
            int h = getHeight();
            int minDim = Math.min(w, h);
            arc = (int) (minDim * radiusPercent);
        }

        Graphics2D g2 = (Graphics2D) grphcs.create();
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

        // Draw border
        g2.setColor(borderColor);
        g2.fillRoundRect(0, 0, getWidth(), getHeight(), arc, arc);

        // Draw inner background
        g2.setColor(getBackground());
        g2.fillRoundRect(2, 2, getWidth() - 4, getHeight() - 4, arc, arc);

        // Draw placeholder text if empty
        if ((getText() == null || getText().isEmpty()) && placeholder != null) {
            g2.setColor(Color.GRAY);
            Insets insets = getInsets();
            FontMetrics fm = g2.getFontMetrics();

            int x = insets.left + 2;
            int y = getHeight() / 2 + fm.getAscent() / 2 - 2;

            g2.drawString(placeholder, x, y);
        }

        g2.dispose();

        super.paintComponent(grphcs);
    }

}
