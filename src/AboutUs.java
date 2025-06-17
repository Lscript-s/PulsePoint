import javax.swing.*;
import java.awt.*;

public class AboutUs extends PScrollPanel{

    AboutUs(){
        setPadding(5,5,5,5);
        setBackground(PulsePointConstants.WHITE);

        PLabel lblTitle = new PLabel("Pulsepoint", PLabel.HEADING1);
        lblTitle.setAlignmentCenter();
        append(wrapLabel(lblTitle));

        JTextArea txtAbout = new JTextArea(
                "       PulsePoint is a centralized health information system that we developed for our Information Management course. "
                        + "A team of Bachelor of Science in Information Technology (BSIT) students created the system to provide a practical and reliable way to manage personal, medical, and immunization records for examinees, whether they are students or employees.\n\n"
                        + "       The main goal of PulsePoint is to simplify the process of storing and accessing important health data. "
                        + "It helps institutions keep up-to-date records of individuals' medical conditions, ongoing medications, and immunization history all in one secure and organized platform. "
                        + "This improves data access, supports quick decision-making, and helps better coordination between administrative and medical staff.\n\n"
                        + "       PulsePoint also focuses on accuracy, security, and efficiency. "
                        + "It reduces the risks associated with manual record-keeping, ensures that only authorized users can access sensitive information, "
                        + "and cuts down the time needed to retrieve records during checkups, emergencies, or routine assessments.\n\n"
                        + "       As aspiring IT professionals, we developed this project not just to fulfill academic requirements but also to create a system that can be used in real-world situations. "
                        + "Through this project, we applied key concepts in database design, user interface development, and information management. "
                        + "This experience reflects our skills, teamwork, and dedication to solving practical problems with technology."
        );
        txtAbout.setLineWrap(true);
        txtAbout.setWrapStyleWord(true);
        txtAbout.setOpaque(false);
        txtAbout.setBorder(null);
        txtAbout.setEditable(false);
        txtAbout.setFocusable(false);
        txtAbout.setFont(PulsePointConstants.HEADING3);
        append(txtAbout);

        append(Box.createVerticalStrut(30));

        PLabel lblMembers = new PLabel("Project Team", PLabel.HEADING1);
        lblMembers.setAlignmentCenter();
        append(wrapLabel(lblMembers));

        append(createRowPanel(
                new PPanel[]{
                        createMemberPanel(new ImageIcon("src/images/Abasola.jpg"), "Abasola, Knurl Randel B."),
                        createMemberPanel(new ImageIcon("src/images/Aranas.jpg"), "Aranas, Micah Joy D."),
                        createMemberPanel(new ImageIcon("src/images/Gultiano.jpg"), "Gultiano, Lancelot A.")}
                ));
        append(createRowPanel(
                new PPanel[]{
                        createMemberPanel(new ImageIcon("src/images/Morante.jpg"), "Morante, C J Heart L."),
                        createMemberPanel(new ImageIcon("src/images/Pinga.jpg"), "Piñga, Andrei Joaquin V.")}
                ));




        revalidate();
        repaint();
    }

    private PPanel wrapLabel(PLabel lbl){
        PPanel pnl = new PPanel();
        pnl.setBackground(null);
        PGridBagConstraints gbc = new PGridBagConstraints();

        pnl.setLayout(new GridBagLayout());

        gbc.setConstraints(-1,0,1,1,1);
        pnl.add(lbl, gbc);

        return pnl;
    }

    private PPanel createRowPanel(PPanel[] arrPanels){
        PPanel pnlRow = new PPanel(new GridBagLayout());
        pnlRow.setBackground(null);

        PGridBagConstraints gbc = new PGridBagConstraints();
        gbc.setInsets(new Insets(0,10,0,10));
        for(PPanel pnl : arrPanels){
            pnlRow.add(pnl, gbc);
        }
        return pnlRow;
    };

    private PPanel createMemberPanel(ImageIcon image, String name){
        PPanel pnl = new PPanel();
        pnl.setBackground(null);
        PGridBagConstraints gbc = new PGridBagConstraints();

        pnl.setLayout(new GridBagLayout());

        gbc.setConstraints(-1,0,1,1,1);
        PLabel lblMemberPicture = new PLabel(image, 250,250);
        pnl.add(lblMemberPicture, gbc);
        gbc.gridy++;

        PLabel lblMemberName = new PLabel(name, PLabel.HEADING2);
        lblMemberName.setAlignmentCenter();
        pnl.add(lblMemberName, gbc);

        return pnl;
    }

    public static void main(String[] args) {
        JFrame frm = new JFrame();
        frm.setSize(500,500);
        frm.setDefaultCloseOperation(3);
        frm.setLayout(new BorderLayout());
        AboutUs abt = new AboutUs();
        frm.add(abt.getScrollPane());

        frm.setVisible(true);

        frm.revalidate();
        frm.repaint();
    }
}
