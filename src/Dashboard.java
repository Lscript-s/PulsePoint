import javax.swing.*;
import javax.swing.border.LineBorder;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.RingPlot;
import org.jfree.data.general.DefaultPieDataset;

public class Dashboard extends PPanel {
    private PPanel objBodyPanel;

    // Interactable components
    private PButton btnAddExaminee = new PButton("Add Examinee +");
    private PButton btnViewAll = new PButton("View All >");

    // Labels for Examinees
    private PLabel lblMaleCount;
    private PLabel lblFemaleCount;

    // Labels for Employees and Students
    private PLabel lblEmployeeCount;
    private PLabel lblEntity1Count;
    private PLabel lblStudentCount;
    private PLabel lblEntity2Count;

    int intMaleCount = 0;
    int intFemaleCount = 0;
    int intEmployeeCount = 0;
    int intStudentCount = 0;
    int intExamineeCount = 0;

    public Dashboard() {
        countExamineeSex();
        countExaminee();
        countExamineeRole();
        initializeDashboard();
    }

    private Connection connection;
    private PreparedStatement statement;
    private ResultSet result;
    private void countExamineeSex(){
        String query = "SELECT sex, COUNT(*) AS count FROM examinee WHERE sex IN ('M', 'F') GROUP BY sex";

        try{
            connection = DriverManager.getConnection(PulsePointConstants.URL,
                                                                PulsePointConstants.USERNAME,
                                                                PulsePointConstants.PASSWORD);
            statement = connection.prepareStatement(query);
            result = statement.executeQuery();

            while(result.next()) {
                String sex = result.getString("sex");
                int count = result.getInt("count");
                if ("M".equalsIgnoreCase(sex)) {
                    this.intMaleCount = count;
                } else if ("F".equalsIgnoreCase(sex)) {
                    this.intFemaleCount = count;
                }
            }
        }catch(Exception e){
            System.out.println("Error: " + e);
        }finally {
            try{result.close();}catch(Exception ignored){}
            try{statement.close();}catch(Exception ignored){}
            try{connection.close();}catch(Exception ignored){}
        }
    }

    private void countExaminee(){
        String query = "SELECT COUNT(*) AS count FROM examinee";

        try{
            connection = DriverManager.getConnection(PulsePointConstants.URL,
                    PulsePointConstants.USERNAME,
                    PulsePointConstants.PASSWORD);
            statement = connection.prepareStatement(query);
            result = statement.executeQuery();

            while(result.next()) {
                int count = result.getInt("count");
                this.intExamineeCount = count;
            }
        }catch(Exception e){
            System.out.println("Error: " + e);
        }finally {
            try{result.close();}catch(Exception ignored){}
            try{statement.close();}catch(Exception ignored){}
            try{connection.close();}catch(Exception ignored){}
        }

    }

    private void countExamineeRole(){
        String query = "SELECT role, COUNT(*) AS count FROM examinee WHERE role IN ('Student', 'Employee') GROUP BY role";

        try{
            connection = DriverManager.getConnection(PulsePointConstants.URL,
                    PulsePointConstants.USERNAME,
                    PulsePointConstants.PASSWORD);
            statement = connection.prepareStatement(query);
            result = statement.executeQuery();

            while(result.next()) {
                String role = result.getString("role");
                int count = result.getInt("count");
                if ("Student".equalsIgnoreCase(role)) {
                    this.intStudentCount = count;
                } else if ("Employee".equalsIgnoreCase(role)) {
                    this.intEmployeeCount = count;
                }
            }
        }catch(Exception e){
            System.out.println("Error: " + e);
        }finally {
            try{result.close();}catch(Exception ignored){}
            try{statement.close();}catch(Exception ignored){}
            try{connection.close();}catch(Exception ignored){}
        }
    }

    private void initializeDashboard() {
        setLayout(new GridBagLayout());
        setPadding(10,10,10,10);
        setBackground(PulsePointConstants.WHITE);

        PGridBagConstraints gbc = new PGridBagConstraints();
        gbc.insets = new Insets(8, 8, 8, 8);
        gbc.fill = GridBagConstraints.BOTH;
        gbc.weightx = 1;
        gbc.gridx = 0;

        // Title Label
        PLabel lblTitle = new PLabel("Dashboard");
        lblTitle.setFontSize(32);
        lblTitle.setForeground(Color.BLACK);
        lblTitle.setHorizontalAlignment(SwingConstants.LEFT);
        gbc.gridy = 0;
        gbc.weighty = 0;
        add(lblTitle, gbc);

        // Cards
        PPanel card1 = createCardPanel("Total Registered Examinees:", true);
        gbc.gridy = 1;
        gbc.weighty = 0.5;
        add(card1, gbc);

        PPanel card2 = createCardPanel("Total Registered Employees & Students", false);
        gbc.gridy = 2;
        gbc.weighty = 0.5;
        add(card2, gbc);


        revalidate();
        repaint();
    }

    private PPanel createCardPanel(String title, boolean isExamineesCard) {
        PPanel card = new PPanel(new GridBagLayout());
        card.setPadding(5,5,5,5);
        card.setBackground(Color.WHITE);
        card.setBorder(new LineBorder(new Color(243, 243, 243), 1, true));

        PGridBagConstraints gbc = new PGridBagConstraints();
        gbc.insets = new Insets(8, 12, 8, 12);
        gbc.fill = GridBagConstraints.NONE;
        gbc.anchor = GridBagConstraints.WEST;

        // Top panel
        PPanel topPanel = new PPanel(new BorderLayout());
        topPanel.setBackground(Color.WHITE);

        PLabel lblCardTitle = new PLabel(title);
        lblCardTitle.setFontSize(24);
        topPanel.add(lblCardTitle, BorderLayout.WEST);

        if (isExamineesCard) {
            PPanel addPanel = new PPanel(new FlowLayout(FlowLayout.RIGHT, 4, 0));
            addPanel.setBackground(Color.WHITE);
            btnAddExaminee.setForeground(new Color(14, 165, 233));
            btnAddExaminee.setBorderPainted(false);
            btnAddExaminee.setContentAreaFilled(false);
            btnAddExaminee.setFocusPainted(false);
            addPanel.add(btnAddExaminee);
            topPanel.add(addPanel, BorderLayout.EAST);
        }

        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 3;
        gbc.weightx = 1;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        card.add(topPanel, gbc);

        // Pie chart

        String[] strAttributeName = new String[2];
        int[] intAttributeCount = new int[2];
        if(isExamineesCard) {
            strAttributeName[0] = "Male";
            intAttributeCount[0] = intMaleCount;
            strAttributeName[1] = "Female";
            intAttributeCount[1] = intFemaleCount;
        }else{
            strAttributeName[0] = "Student";
            intAttributeCount[0] = intStudentCount;
            strAttributeName[1] = "Employee";
            intAttributeCount[1] = intEmployeeCount;
        }

        PPanel pieChart = new PPanel();
        pieChart.setBackground(new Color(14, 165, 233));
        pieChart.setLayout(new GridBagLayout());
        pieChart.setOpaque(true);

        PLabel lblPie = createChart(strAttributeName[0], intAttributeCount[0],strAttributeName[1], intAttributeCount[1]);
        //PLabel lblPie = new PLabel("Test", PLabel.HEADING1);
        lblPie.setForeground(Color.BLUE);

        gbc.gridwidth = 1;
        gbc.weightx = 0;
        gbc.fill = GridBagConstraints.NONE;
        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.anchor = GridBagConstraints.SOUTH;
        card.add(lblPie, gbc);


        // Info labels
        PPanel infoPanel = new PPanel();
        infoPanel.setBackground(Color.WHITE);
        infoPanel.setBorder(BorderFactory.createEmptyBorder(0, 12, 0, 12));

        if (isExamineesCard) {
            infoPanel.setLayout(new GridLayout(2, 1, 0, 6));
            lblMaleCount = new PLabel("No. of Male Examinees: " + this.intMaleCount);
            lblMaleCount.setFontSize(20);
            infoPanel.add(lblMaleCount);

            lblFemaleCount = new PLabel("No. of Female Examinees: " + this.intFemaleCount);
            lblFemaleCount.setFontSize(20);
            infoPanel.add(lblFemaleCount);
        } else {
            infoPanel.setLayout(new GridLayout(2, 2, 20, 6));
            lblEmployeeCount = new PLabel("No. of Employees: " + this.intEmployeeCount);
            lblEmployeeCount.setFontSize(20);
            infoPanel.add(lblEmployeeCount);

            lblEntity1Count = new PLabel("No. of Examinees: " + this.intExamineeCount);
            lblEntity1Count.setFontSize(20);
            infoPanel.add(lblEntity1Count);

            lblStudentCount = new PLabel("No. of Students: " + this.intStudentCount);
            lblStudentCount.setFontSize(20);
            infoPanel.add(lblStudentCount);

        }

        gbc.gridx = 1;
        gbc.gridy = 1;
        gbc.weightx = 1;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.anchor = GridBagConstraints.WEST;
        card.add(infoPanel, gbc);

        if (isExamineesCard) {
            PPanel viewAllPanel = new PPanel(new FlowLayout(FlowLayout.RIGHT, 4, 0));
            viewAllPanel.setBackground(Color.WHITE);

            btnViewAll.setForeground(new Color(14, 165, 233));
            btnViewAll.setBorderPainted(false);
            btnViewAll.setContentAreaFilled(false);
            btnViewAll.setFocusPainted(false);
            viewAllPanel.add(btnViewAll);
            gbc.gridx = 2;
            gbc.gridy = 1;
            gbc.weightx = 0;
            gbc.fill = GridBagConstraints.NONE;
            gbc.anchor = GridBagConstraints.EAST;
            card.add(viewAllPanel, gbc);
        }

        return card;
    }

    private PButton createButton(String text) {
        PButton button = new PButton(text);
        button.setForeground(new Color(14, 165, 233));
        button.setBorderPainted(false);
        button.setContentAreaFilled(false);
        button.setFocusPainted(false);


        return button;
    }

    // --- GETTERS ---

    public PButton getAddExamineeButton() {
        return btnAddExaminee;
    }

    public PButton getViewAllButton() {
        return btnViewAll;
    }

    public PLabel getLblMaleCount() {
        return lblMaleCount;
    }

    public PLabel getLblFemaleCount() {
        return lblFemaleCount;
    }

    public PLabel getLblEmployeeCount() {
        return lblEmployeeCount;
    }

    public PLabel getLblEntity1Count() {
        return lblEntity1Count;
    }

    public PLabel getLblStudentCount() {
        return lblStudentCount;
    }

    public PLabel getLblEntity2Count() {
        return lblEntity2Count;
    }

    public PLabel createChart(String name1, int count1, String name2, int count2) {
        // Dataset
        DefaultPieDataset dataset = new DefaultPieDataset();
        dataset.setValue(name1, count1);
        dataset.setValue(name2, count2);


        // Chart using RingPlot
        JFreeChart chart = ChartFactory.createRingChart(
                "",
                dataset,
                true,
                true,
                false
        );

        RingPlot plot = (RingPlot) chart.getPlot();
        plot.setLabelGenerator(null);
        plot.setSectionDepth(0.35);
        plot.setBackgroundPaint(Color.WHITE);
        plot.setOutlineVisible(false);
        plot.setOutlinePaint(null);
        // Render chart to BufferedImage
        int width = 150;
        int height = 150;
        BufferedImage chartImage = chart.createBufferedImage(width, height);

        // Create ImageIcon from BufferedImage
        ImageIcon chartIcon = new ImageIcon(chartImage);

        // Create JLabel with icon
        PLabel chartLabel = new PLabel(chartIcon);
        // Add label to JFrame content pane
        add(chartLabel);
        return chartLabel;
    }

    public void refreshCounts() {
        countExamineeSex();
        countExaminee();
        countExamineeRole();
        removeAll();
        initializeDashboard();

        revalidate();
        repaint();
    }


    public static void main(String[] args) {
        JFrame frm = new JFrame("Dashboard");
        Dashboard dashboard = new Dashboard();
        frm.setSize(700,800);
        frm.setDefaultCloseOperation(3);
        frm.add(dashboard);

        frm.setVisible(true);
    }
}
