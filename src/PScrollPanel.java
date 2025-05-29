import javax.swing.*;
import java.awt.*;

public class PScrollPanel extends PPanel{
    private final JScrollPane scrlPane;

    /*
    ---USAGE---
        PScrollPanel scrpnl = new PScrollPanel();
        frm.add(scrpnl.getScrollPane());
        scrpnl.append(COMPONENT);
    */
    PScrollPanel(){
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        scrlPane = new JScrollPane(this);
        scrlPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        scrlPane.getVerticalScrollBar().setUnitIncrement(16);
    }

    public JScrollPane getScrollPane() {
        return scrlPane;
    }

    public void append(Component component){
        this.add(component);
        this.add(Box.createVerticalStrut(10));
        this.revalidate();
        this.repaint();
    }
}
