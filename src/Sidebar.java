import javax.swing.*;
import java.awt.*;

public class Sidebar extends PPanel{
    private final PButton btnDashboard = new PButton("Dashboard",
            new ImageIcon("src/icons/Icon_Dashboard.png"), 24,24);
    private final PButton btnExamineeCatalog = new PButton("Examinee Catalog",
            new ImageIcon("src/icons/Icon_Catalog.png"), 24,24);
    private final PButton btnAddExaminee = new PButton("Add Examinee",
            new ImageIcon("src/icons/Icon_Add.png"), 24,24);
    private final PButton btnAboutUs = new PButton("About Us",
            new ImageIcon("src/icons/Icon_AboutUs.png"), 24,24);
    private final PPanel pnlDashboard = new PPanel(new GridBagLayout());
    private final PPanel pnlExamineeCatalog = new PPanel(new GridBagLayout());
    private final PPanel pnlAddExaminee = new PPanel(new GridBagLayout());
    private final PPanel pnlSettings = new PPanel(new GridBagLayout());
    private final PPanel pnlAboutUs = new PPanel(new GridBagLayout());

    private final Color clrPink = Color.decode("#d7c8c8");
    private final Color clrBlue = Color.decode("#0cc0df");
    private final Color clrGray = Color.decode("#f3e9e9");

    private PGridBagConstraints gbcCons = new PGridBagConstraints();

    Sidebar(){
        setBackground(Color.WHITE);
        setLayout(new GridBagLayout());

        initializePanel();
        initializeButtons();
        addComponents();

        setBorderSide(false);
        setBorderRight(true);
        setBorderThickness(3);
        setBorderColor(clrGray);
        setPadding(new Insets(10,10,10,10));
    }

    private void initializePanel(){
        pnlDashboard.setBackground(Color.WHITE);
        pnlAddExaminee.setBackground(Color.WHITE);
        pnlExamineeCatalog.setBackground(Color.WHITE);
        pnlSettings.setBackground(Color.WHITE);
        pnlAboutUs.setBackground(Color.WHITE);
    }


    private void initializeButtons(){
        gbcCons.setConstraints(-1,-1,1,1,1);
        btnDashboard.setHorizontalAlignment(SwingConstants.LEFT);
        btnDashboard.setHorizontalTextPosition(SwingConstants.RIGHT);
        btnDashboard.setIconTextGap(8);
        btnDashboard.setFont(new Font(PLabel.FONTSTYLE, Font.PLAIN, PLabel.HEADING2));
        btnDashboard.setBackgroundHoverColor(clrBlue);
        btnDashboard.setBackgroundClickedColor(PulsePointConstants.PINK);
        pnlDashboard.add(btnDashboard, gbcCons);

        btnExamineeCatalog.setHorizontalAlignment(SwingConstants.LEFT);
        btnExamineeCatalog.setHorizontalTextPosition(SwingConstants.RIGHT);
        btnExamineeCatalog.setIconTextGap(8);
        btnExamineeCatalog.setFont(new Font(PLabel.FONTSTYLE, Font.PLAIN, PLabel.HEADING2));
        btnExamineeCatalog.setBackgroundHoverColor(clrBlue);
        btnExamineeCatalog.setBackgroundClickedColor(PulsePointConstants.PINK);
        pnlExamineeCatalog.add(btnExamineeCatalog, gbcCons);

        btnAddExaminee.setHorizontalAlignment(SwingConstants.LEFT);
        btnAddExaminee.setHorizontalTextPosition(SwingConstants.RIGHT);
        btnAddExaminee.setIconTextGap(8);
        btnAddExaminee.setFont(new Font(PLabel.FONTSTYLE, Font.PLAIN, PLabel.HEADING2));
        btnAddExaminee.setBackgroundHoverColor(clrBlue);
        btnAddExaminee.setBackgroundClickedColor(PulsePointConstants.PINK);
        pnlAddExaminee.add(btnAddExaminee, gbcCons);

        btnAboutUs.setHorizontalAlignment(SwingConstants.LEFT);
        btnAboutUs.setHorizontalTextPosition(SwingConstants.RIGHT);
        btnAboutUs.setIconTextGap(8);
        btnAboutUs.setFont(new Font(PLabel.FONTSTYLE, Font.PLAIN, PLabel.HEADING2));
        btnAboutUs.setBackgroundHoverColor(clrBlue);
        btnAboutUs.setBackgroundClickedColor(PulsePointConstants.PINK);
        pnlAboutUs.add(btnAboutUs, gbcCons);
    }

    private void addComponents(){
        gbcCons.setConstraints(-1,-1,1,0.1, GridBagConstraints.HORIZONTAL);
        gbcCons.anchor = GridBagConstraints.WEST;

        gbcCons.gridy++;
        add(pnlDashboard, gbcCons);
        gbcCons.gridy++;
        add(pnlAddExaminee, gbcCons);
        gbcCons.gridy++;
        add(pnlExamineeCatalog, gbcCons);
        gbcCons.gridy++;
        gbcCons.setConstraints(-1,gbcCons.gridy+1, 1,0.8,GridBagConstraints.VERTICAL);
        add(Box.createVerticalStrut(100), gbcCons);

        gbcCons.gridy++;
        gbcCons.setConstraints(-1,gbcCons.gridy, 1,0.1,GridBagConstraints.HORIZONTAL);
        add(pnlSettings, gbcCons);
        gbcCons.gridy++;
        add(pnlAboutUs, gbcCons);
    }

    // Accessors
    public PButton getDashboard(){
        return btnDashboard;
    }
    public PButton getAddExaminee(){
        return btnAddExaminee;
    }
    public PButton getAboutUs(){
        return btnAboutUs;
    }
    public PButton getExamineeCatalog(){
        return btnExamineeCatalog;
    }
    // Public Functions
    public void setFocus(String focusedButton){
        pnlDashboard.setBackground(Color.WHITE);
        pnlAddExaminee.setBackground(Color.WHITE);
        pnlExamineeCatalog.setBackground(Color.WHITE);
        pnlAboutUs.setBackground(Color.WHITE);

        if(focusedButton.equals("Dashboard")){
            this.pnlDashboard.setBackground(clrGray);
        }else if (focusedButton.equals("Add Examinee")) {
            pnlAddExaminee.setBackground(clrGray);
        }else if (focusedButton.equals("Examinee Catalog")) {
            pnlExamineeCatalog.setBackground(clrGray);
        }else if(focusedButton.equals("About Us")){
            pnlAboutUs.setBackground(clrGray);
        }
        this.revalidate();
        this.repaint();
    }

    public static void main(String[] args) {
        JFrame frm = new JFrame("Sidebar");
        frm.setSize(100, 600);
        frm.add(new Sidebar());
        frm.setDefaultCloseOperation(3);
        frm.setVisible(true);
    }







}
