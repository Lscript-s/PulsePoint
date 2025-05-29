import javax.swing.*;
import java.awt.*;

public class PRadioButton extends JRadioButton {
    PRadioButton(){
        super();
        setFont(PulsePointConstants.HEADING3);
        this.setFocusPainted(false);
        this.setBorderPainted(false);
        this.setContentAreaFilled(false);
        this.setOpaque(false);
    }
    PRadioButton(String text){
        super(text);
        setFont(PulsePointConstants.HEADING3);
        this.setFocusPainted(false);
        this.setBorderPainted(false);
        this.setContentAreaFilled(false);
        this.setOpaque(false);
    }

}
