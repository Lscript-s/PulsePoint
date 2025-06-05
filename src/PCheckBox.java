import javax.swing.*;
import java.awt.*;

public class PCheckBox extends JCheckBox {
    PCheckBox(){
        super();
        setFont(PulsePointConstants.HEADING3);
        this.setFocusPainted(false);
        this.setBorderPainted(false);
        this.setContentAreaFilled(false);
        this.setOpaque(false);
    }

    PCheckBox(String text){
        super(text);
        setFont(PulsePointConstants.HEADING3);
        this.setFocusPainted(false);
        this.setBorderPainted(false);
        this.setContentAreaFilled(false);
        this.setOpaque(false);

    }

    PCheckBox(String text, Font font){
        super(text);
        setFont(font);
        this.setFocusPainted(false);
        this.setBorderPainted(false);
        this.setContentAreaFilled(false);
        this.setOpaque(false);

    }
}
