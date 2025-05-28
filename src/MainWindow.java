import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Timer;
import java.util.TimerTask;

public class MainWindow {
    JFrame frmMainWindow = new JFrame("PulsePoint");
    JPanel pnlHeader = new JPanel(new GridBagLayout());
    JPanel pnlSidebar = new JPanel(new GridBagLayout());
    JPanel pnlBody = new JPanel(new GridBagLayout());
    PGridBagConstraints gbcCons = new PGridBagConstraints();

    String strCurrentBodyDisplay = "Dashboard";

    Header header = new Header();
    Dashboard dashboard = new Dashboard();
    Sidebar sidebar = new Sidebar();

    MainWindow(){
        initializeFrame();

        pnlHeader.setBackground(Color.RED);
        pnlHeader.setOpaque(true);

        pnlSidebar.setBackground(Color.BLUE);
        pnlSidebar.setOpaque(true);

        pnlBody.setBackground(Color.YELLOW);
        pnlBody.setOpaque(true);
        addPanels();

        addBodyFiller();

        addHeader();
        setHeaderButtons();

        addSidebar();
        setSidebarButton();
        setDashboardButton();
        setBodyPanel();
//        testFunc();

        frmMainWindow.setVisible(true);

        Timer timer = new Timer();
        timer.scheduleAtFixedRate(new TimerTask() {
            @Override
            public void run() {
                dashboard.refreshCounts();
            }
        }, 0, 20000);

    }

    private void testFunc(){
        header.getLogoutButton().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println("Hi");
            }
        });
    }

    private void initializeFrame(){
        frmMainWindow.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frmMainWindow.setSize(1000,800);
        frmMainWindow.setMinimumSize(new Dimension(1000,800));
        frmMainWindow.setLayout(new GridBagLayout());
    }

    private void addPanels(){
        gbcCons.setConstraints(-1,-1,0.1,0,GridBagConstraints.HORIZONTAL);
        gbcCons.setWidth(2);
        frmMainWindow.add(pnlHeader, gbcCons);

        gbcCons.reset();
        gbcCons.setConstraints(-1,1,0,0.2, 1);
        frmMainWindow.add(pnlSidebar, gbcCons);

        gbcCons.setConstraints(-1,1,0.5,0.2,1);
        frmMainWindow.add(pnlBody,gbcCons);
    }

    private void addBodyFiller(){
        pnlBody.removeAll();
        gbcCons.reset();
        gbcCons.setConstraints(-1,-1,1,1,1);
        pnlBody.add(new JPanel(){{setBackground(Color.WHITE);}}, gbcCons);
        gbcCons.reset();
        pnlBody.repaint();
        pnlBody.revalidate();
    }

    private void addHeader(){
        gbcCons.setConstraints(-1,-1,1,1,1);
        pnlHeader.add(header, gbcCons);
        pnlHeader.add(Box.createVerticalStrut(75));
        gbcCons.reset();
    }

    private void setHeaderButtons(){
        // Header Search Button
        header.getSearchButton().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

            }
        });

        // Header Logout Button
        header.getLogoutButton().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
            }
        });

        // Header Search Button
        header.getSearchButton().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

            }
        });

        // Header User Button
        header.getUserButton().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

            }
        });
    }

    private void addSidebar(){
        gbcCons.reset();
        gbcCons.setConstraints(-1,-1,1,1,1);
        pnlSidebar.add(sidebar, gbcCons);
        gbcCons.reset();
    }

    private void setSidebarButton(){
        sidebar.getDashboard().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                strCurrentBodyDisplay = "Dashboard";
                setBodyPanel();
            }
        });
        sidebar.getAddExaminee().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                strCurrentBodyDisplay = "Add Examinee";
                setBodyPanel();
            }
        });
        sidebar.getExamineeCatalog().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                strCurrentBodyDisplay = "Examinee Catalog";
                setBodyPanel();
            }
        });
        sidebar.getSettings().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                strCurrentBodyDisplay = "Settings";
                setBodyPanel();
            }
        });
        sidebar.getAboutUs().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                strCurrentBodyDisplay = "About Us";
                setBodyPanel();
            }
        });


    }

    private void addDashboard(){
        gbcCons.reset();
        gbcCons.setConstraints(-1,-1,1,1,1);
        dashboard.refreshCounts();
        pnlBody.add(dashboard, gbcCons);
        gbcCons.reset();
    }

    private void setDashboardButton(){
        dashboard.getAddExamineeButton().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                strCurrentBodyDisplay = "Add Examinee";
                setBodyPanel();
            }
        });

        dashboard.getViewAllButton().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                strCurrentBodyDisplay = "Examinee Catalog";
                setBodyPanel();
            }
        });
    }

    private void addAddExaminee(){
        pnlBody.add(new PLabel("Add Examinee", PLabel.HEADING1));
    }

    private void addSettings(){
        pnlBody.add(new PLabel("Settings", PLabel.HEADING1));
    }

    private void addExamineeCatalog(){
        pnlBody.add(new PLabel("Examinee Catalog", PLabel.HEADING1));
    }

    private void addAboutUs(){
        pnlBody.add(new PLabel("About Us", PLabel.HEADING1));
    }

    // Sets the body panel and changes the appearance of the button
    private void setBodyPanel(){
        sidebar.setFocus(strCurrentBodyDisplay);
        pnlBody.removeAll();
        if(strCurrentBodyDisplay.equals("Dashboard")){
            addDashboard();
        }else if(strCurrentBodyDisplay.equals("Add Examinee")) {
            addAddExaminee();
        }else if(strCurrentBodyDisplay.equals("Examinee Catalog")){
            addExamineeCatalog();
        }else if(strCurrentBodyDisplay.equals("Settings")) {
            addSettings();
        }else if(strCurrentBodyDisplay.equals("About Us")) {
            addAboutUs();
        } else{
            addBodyFiller();
        }

        pnlBody.repaint();
        pnlBody.revalidate();
    }


    public static void main(String[] args) {
        new MainWindow();
    }

}
