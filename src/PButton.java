import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class PButton extends JButton {
    private Color objOrigBgColor, objHoverBgColor, objClickBgColor;
    private Color objOrigTextColor, objHoverTextColor, objClickTextColor;
    private Color objBorderColor;
    private boolean boolIsPressed = false;
    private int radius = 0;
    private double radiusPercent = -1;
    private int borderThickness = 2;

    PButton(){
        super();
        initialize();
    }

    PButton(String text){
        super(text);
        initialize();
    }

    PButton(String text, ImageIcon icon){
        super(text);
        setIcon(icon);
        initialize();
    }

    PButton(String text, ImageIcon icon, int width, int height){
        super(text);
        setIcon(icon);
        resizeIcon(width, height);
        initialize();
    }

    PButton(String text, int radius){
        super(text);
        initialize();
        this.radius = radius;
    }

    PButton(String text, int radius, Color borderColor){
        super(text);
        initialize();
        this.radius = radius;
        this.objBorderColor = borderColor;
    }

    PButton(ImageIcon img){
        super(img);
        initialize();
    }

    private void initialize(){
        this.setTransparent();
        this.setFocusPainted(false);
        this.setBorderPainted(false);
        this.setContentAreaFilled(false);
        this.setOpaque(false);
        this.setMouseListener();
        this.setBorderColor(new Color(0,0,0,0));
        this.setFont(new Font(PLabel.FONTSTYLE, Font.PLAIN, PLabel.HEADING3));
        this.setPointCursor(true);

    }

    public void setTransparent(){
        this.setBackground(new Color(0,0,0,0));
    }

    private void setMouseListener(){
        this.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                boolIsPressed = true;
                if (objClickBgColor != null) {
                    setBackground(objClickBgColor);
                }
                if(objClickTextColor != null){
                    setForeground(objClickTextColor);
                }
            }

            @Override
            public void mouseReleased(MouseEvent e) {
                boolIsPressed = false;

                // If still hovering, show hover color
                if (objHoverBgColor != null && contains(e.getPoint())) {
                    setBackground(objHoverBgColor);
                } else {
                    setBackground(objOrigBgColor);
                }

                if(objHoverTextColor != null && contains(e.getPoint())) {
                    setForeground(objHoverTextColor);
                } else {
                    setForeground(objOrigTextColor);
                }
            }

            @Override
            public void mouseEntered(MouseEvent e) {
                objOrigBgColor = getBackground();
                if (!boolIsPressed && objHoverBgColor != null) {
                    setBackground(objHoverBgColor);
                }
                objOrigTextColor = getForeground();
                if (!boolIsPressed && objHoverTextColor != null) {
                    setForeground(objHoverTextColor);
                }
            }

            @Override
            public void mouseExited(MouseEvent e) {
                if (!boolIsPressed && objOrigBgColor != null) {
                    setBackground(objOrigBgColor);
                }
                if (!boolIsPressed && objOrigTextColor != null) {
                    setForeground(objOrigTextColor);
                }
            }

        });
    }

    public void setBackgroundHoverColor(Color color){
        this.objHoverBgColor = color;
    }


    public void setBackgroundClickedColor(Color color){
        this.objClickBgColor = color;
    }

    public void setForegroundHoverColor(Color color){
        this.objHoverTextColor = color;
    }

    public void setForegroundClickedColor(Color color){
        this.objClickTextColor = color;
    }

    public void setRadius(int radius){
        this.radius = radius;
        this.radiusPercent = -1;
        repaint();
    }

    public void setBorderColor(Color color){
        this.objBorderColor = color;
    }

    public void setRadiusPercent(double percent) {
        if(percent >= 0 && percent <= 100) {
            this.radiusPercent = percent;
            repaint();
        }
    }

    public void setBorderThickness(int thickness) {
        this.borderThickness = thickness;
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

    public void setHoverClick(){
        setBackgroundClickedColor(PulsePointConstants.PINK);
        setBackgroundHoverColor(PulsePointConstants.BLUE);
    }

    public void setPointCursor(boolean isPointCursor){
        if(isPointCursor){
            this.setCursor(new Cursor(Cursor.HAND_CURSOR));
        }else{
            this.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
        }
    }

    public void resizeIcon(int width, int height) {
        Icon icon = this.getIcon();
        if (icon instanceof ImageIcon) {
            ImageIcon imgIcon = (ImageIcon) icon;
            Image img = imgIcon.getImage();
            Image resizedImg = img.getScaledInstance(width, height, Image.SCALE_SMOOTH);
            this.setIcon(new ImageIcon(resizedImg));
        }
    }


    @Override
    protected void paintComponent(Graphics grphcs) {
        Color borderColor = objBorderColor;
        int arc = radius;

        if (radiusPercent >= 0) {
            int w = getWidth();
            int h = getHeight();
            int minDim = Math.min(w, h);
            arc = (int) (minDim * radiusPercent);
        }

        Graphics2D g2 = (Graphics2D) grphcs.create();
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

        // Draw outer border
        g2.setColor(borderColor);
        g2.fillRoundRect(0, 0, getWidth(), getHeight(), arc, arc);

        // Draw inner background
        g2.setColor(getBackground());
        g2.fillRoundRect(borderThickness, borderThickness,
                getWidth() - 2 * borderThickness,
                getHeight() - 2 * borderThickness,
                arc, arc);

        g2.dispose();

        // Paint label/text/icons
        super.paintComponent(grphcs);
    }


    public void setDebugColor(boolean isDebug){
        this.setBackground(Color.RED);
    }

    public void setDebugColor(Color color){
        this.setBackground(color);
    }


}
