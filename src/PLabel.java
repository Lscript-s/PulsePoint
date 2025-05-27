import javax.swing.*;
import java.awt.*;

public class PLabel extends JLabel {
    public static final String FONTSTYLE = "Poppins";

    public static final int HEADING1 = 35;
    public static final int HEADING2 = 20;
    public static final int HEADING3 = 15;

    // Constructors
    PLabel(){
        super();
        setFont(new Font(FONTSTYLE, Font.PLAIN, HEADING3));
    }

    PLabel(String text){
        super(text);
        setFont(new Font(FONTSTYLE, Font.PLAIN, HEADING3));
    }

    PLabel(String text, int heading){
        super(text);
        if(heading == HEADING1){
            setFont(new Font(FONTSTYLE, Font.BOLD, HEADING1));
        }else if(heading == HEADING2){
            setFont(new Font(FONTSTYLE, Font.BOLD, HEADING2));
        }else if(heading == HEADING3){
            setFont(new Font(FONTSTYLE, Font.PLAIN, HEADING3));
        }else{
            throw new IllegalArgumentException("Invalid Heading at argument 2.");
        }
    }

    PLabel(String text, Color color){
        super(text);
        setFont(new Font(FONTSTYLE, Font.PLAIN, HEADING3));
        this.setForeground(color);
    }

    PLabel(String text, int heading, Color color){
        super(text);
        if(heading == HEADING1){
            setFont(new Font(FONTSTYLE, Font.BOLD, HEADING1));
        }else if(heading == HEADING2){
            setFont(new Font(FONTSTYLE, Font.BOLD, HEADING2));
        }else if(heading == HEADING3){
            setFont(new Font(FONTSTYLE, Font.PLAIN, HEADING3));
        }else{
            throw new IllegalArgumentException("Invalid Heading at argument 2.");
        }

        this.setForeground(color);
    }

    PLabel(ImageIcon img){
        super(img);
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

    public void setAlignmentCenter(){
        this.setHorizontalAlignment(JLabel.CENTER);
        this.setVerticalAlignment(JLabel.CENTER);
    }

    public void setDebugColor(boolean isDebug){
        this.setBackground(Color.RED);
        this.setOpaque(true);
    }

    public void setDebugColor(Color color){
        this.setBackground(color);
        this.setOpaque(true);

    }

}
