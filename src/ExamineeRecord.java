import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

public class ExamineeRecord extends PPanel {
    private PLabel lblID = new PLabel(), lblName = new PLabel(), lblRole = new PLabel();
    private PButton btnView = new PButton("View Record");
    private String strRecordId;
    private PPanel pnlRole = new PPanel();
    ExamineeRecord(String id, String name, String role) {
        strRecordId = id;
        lblID.setText(id);
        lblName.setText(name);
        lblRole.setText(role);
        setBackground(PulsePointConstants.WHITE);

        setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.fill = GridBagConstraints.BOTH;
        gbc.insets = new Insets(10, 10, 10, 10);

        //
        setBorderColor(PulsePointConstants.BLUE);
        setBorderThickness(2);

        //
        PPanel pnlID = new PPanel();
        pnlID.setBackground(PulsePointConstants.PINK);
        lblID.setBold(true);
        pnlID.add(lblID);
        pnlID.setPreferredSize(new Dimension(120, 30));

        PPanel pnlName = new PPanel();
        pnlName.setBorderColor(PulsePointConstants.GRAY);
        pnlName.add(lblName);
        pnlName.setPreferredSize(new Dimension(250, 30)); // fixed width


        pnlRole.setBorderColor(PulsePointConstants.GRAY);
        pnlRole.add(lblRole);
        pnlRole.setPreferredSize(new Dimension(120, 30)); // fixed width

        // View Button
        btnView.setBackground(PulsePointConstants.GREEN);
        btnView.setBackgroundHoverColor(PulsePointConstants.BLUE);
        btnView.setBackgroundClickedColor(PulsePointConstants.PINK);
        btnView.setBorderThickness(2);
        btnView.setRadius(15);
//        btnView.setPreferredSize(new Dimension(150, 30)); // fixed width

        btnView.addActionListener(e -> {
            System.out.println("View Button Clicked");
        });

        // Add components
        gbc.gridx = 0;
        gbc.fill = 2;
        gbc.weightx = 1;
        add(pnlID, gbc);

        gbc.gridx = 1;
        add(pnlName, gbc);

        gbc.gridx = 2;
        add(pnlRole, gbc);

        gbc.gridx = 3;

        add(btnView, gbc);
    }

    public String getID(){
        return strRecordId;
    }

    public PButton getButton(){
        return btnView;
    }

    public void setActionListener(ActionListener e){
        this.btnView.addActionListener(e);
    }

    public static void main(String[] args) {
        JFrame frm = new JFrame();
        frm.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frm.setSize(800, 500);
        frm.setLayout(new GridLayout(5,1));

        ExamineeRecord rec1 = new ExamineeRecord("2023-12345", "Doe, John A.", "Student");
        ExamineeRecord rec2 = new ExamineeRecord("2023-12346", "Jane, Mary B.", "Employee");
        ExamineeRecord rec3 = new ExamineeRecord("2025-12347", "Aldous, Gusion", "Student");
        ExamineeRecord rec4 = new ExamineeRecord("2024-15468", "Bashful Doc Dopey Grumpy Happy Sleepy Sneezy", "Student");

        frm.add(rec1);
        frm.add(rec2);
        frm.add(rec3);
        frm.add(rec4);

        frm.setVisible(true);
    }
}
