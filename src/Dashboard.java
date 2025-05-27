import javax.swing.*;
import javax.swing.border.LineBorder;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class Dashboard extends PPanel {
    private PPanel objBodyPanel;

    // Interactable components
    private PButton btnAddExaminee;
    private PButton btnViewAll;

    // Labels for Examinees
    private PLabel lblMaleCount;
    private PLabel lblFemaleCount;

    // Labels for Employees and Students
    private PLabel lblEmployeeCount;
    private PLabel lblEntity1Count;
    private PLabel lblStudentCount;
    private PLabel lblEntity2Count;

    public Dashboard() {
        initializeDashboard();
    }

    private void initializeDashboard() {
        setLayout(new GridBagLayout());
        setBackground(Color.WHITE);

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
            btnAddExaminee = createButton("Add Examinee +");
            btnAddExaminee.addActionListener(e -> System.out.println("Add Examinee button clicked"));
            addPanel.add(btnAddExaminee);
            topPanel.add(addPanel, BorderLayout.EAST);
        }

        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 3;
        gbc.weightx = 1;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        card.add(topPanel, gbc);

        // Pie chart placeholder
        PPanel pieChart = new PPanel();
        pieChart.setPreferredSize(new Dimension(96, 96));
        pieChart.setBackground(new Color(14, 165, 233));
        pieChart.setLayout(new GridBagLayout());
        pieChart.setOpaque(true);

        PLabel lblPie = new PLabel("Pie Chart");
        lblPie.setForeground(Color.WHITE);
        lblPie.setFont(new Font("Roboto", Font.PLAIN, 20));
        pieChart.add(lblPie);

        gbc.gridwidth = 1;
        gbc.weightx = 0;
        gbc.fill = GridBagConstraints.NONE;
        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.anchor = GridBagConstraints.CENTER;
        card.add(pieChart, gbc);

        // Info labels
        PPanel infoPanel = new PPanel();
        infoPanel.setBackground(Color.WHITE);
        infoPanel.setBorder(BorderFactory.createEmptyBorder(0, 12, 0, 12));

        if (isExamineesCard) {
            infoPanel.setLayout(new GridLayout(2, 1, 0, 6));
            lblMaleCount = new PLabel("No. of Male Examinees: --");
            lblMaleCount.setFontSize(20);
            infoPanel.add(lblMaleCount);

            lblFemaleCount = new PLabel("No. of Female Examinees: --");
            lblFemaleCount.setFontSize(20);
            infoPanel.add(lblFemaleCount);
        } else {
            infoPanel.setLayout(new GridLayout(2, 2, 20, 6));
            lblEmployeeCount = new PLabel("No. of Employees: --");
            lblEmployeeCount.setFontSize(20);
            infoPanel.add(lblEmployeeCount);

            lblEntity1Count = new PLabel("No. of Entity 1: --");
            lblEntity1Count.setFontSize(20);
            infoPanel.add(lblEntity1Count);

            lblStudentCount = new PLabel("No. of Students: --");
            lblStudentCount.setFontSize(20);
            infoPanel.add(lblStudentCount);

            lblEntity2Count = new PLabel("No. of Entity 2: --");
            lblEntity2Count.setFontSize(20);
            infoPanel.add(lblEntity2Count);
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

            btnViewAll = createButton("View all >");
            btnViewAll.addActionListener(e -> System.out.println("View All button clicked"));
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
        // RIP SET FONT
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
}
