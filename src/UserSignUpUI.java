import javax.swing.*;
import java.awt.*;

public class UserSignUpUI extends JPanel {
    private final PTextField firstNameField;
    private final PTextField lastNameField;
    private final PTextField monthField;
    private final PTextField dayField;
    private final PTextField yearField;
    private final PTextField genderField;
    private final PTextField mobileField;
    private final PTextField emailField;
    private final PTextField passwordField;
    private final PButton signUpButton;

    public UserSignUpUI() {
        setLayout(new GridBagLayout());
        setBackground(new Color(255, 248, 248)); // Light pink background

        GridBagConstraints gbc = new GridBagConstraints();

        // Left Logo Panel
        JPanel logoPanel = new JPanel();
        logoPanel.setBackground(new Color(255, 248, 248));
        JLabel logoLabel = new JLabel("PulsePoint");
        logoLabel.setFont(new Font("SansSerif", Font.BOLD, 40));
        logoLabel.setForeground(new Color(0, 174, 255));
        logoPanel.add(logoLabel);
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.insets = new Insets(0, 40, 0, 40);
        add(logoPanel, gbc);

        // Right Sign Up Panel
        JPanel signUpPanel = new PPanel(new GridBagLayout(), Color.WHITE, 30);
        signUpPanel.setLayout(new GridBagLayout());
        signUpPanel.setPreferredSize(new Dimension(500, 0)); // width constrained, height flexible

        GridBagConstraints innerGbc = new GridBagConstraints();
        innerGbc.insets = new Insets(10, 10, 10, 10);
        innerGbc.fill = GridBagConstraints.HORIZONTAL;

        // Title
        PLabel titleLabel = new PLabel("Create a New Account", PLabel.HEADING2);
        titleLabel.setForeground(new Color(0x1E88E5));
        titleLabel.setHorizontalAlignment(SwingConstants.CENTER);
        innerGbc.gridx = 0;
        innerGbc.gridy = 0;
        innerGbc.gridwidth = 2;
        signUpPanel.add(titleLabel, innerGbc);
        innerGbc.anchor = GridBagConstraints.CENTER;

        // First + Last Name
        firstNameField = new PTextField(20);
        firstNameField.setPlaceholder("First Name");
        lastNameField = new PTextField(20);
        lastNameField.setPlaceholder("Last Name");
        Dimension halfField = new Dimension(230, 40);
        firstNameField.setPreferredSize(halfField);
        lastNameField.setPreferredSize(halfField);

        innerGbc.gridy++;
        innerGbc.gridwidth = 1;
        innerGbc.gridx = 0;
        signUpPanel.add(firstNameField, innerGbc);
        innerGbc.gridx = 1;
        signUpPanel.add(lastNameField, innerGbc);

        // Birthday title
        PLabel birthdayLabel = new PLabel("Birthday", PLabel.HEADING3);
        birthdayLabel.setForeground(new Color(0x1E88E5));
        birthdayLabel.setHorizontalAlignment(SwingConstants.CENTER);
        innerGbc.gridy++;
        innerGbc.gridx = 0;
        innerGbc.gridwidth = 2;
        innerGbc.anchor = GridBagConstraints.CENTER;
        signUpPanel.add(birthdayLabel, innerGbc);

        // Birthday Fields: Month - Day - Year
        monthField = new PTextField(10);
        monthField.setPlaceholder("Month");
        dayField = new PTextField(10);
        dayField.setPlaceholder("Day");
        yearField = new PTextField(10);
        yearField.setPlaceholder("Year");
        Dimension thirdField = new Dimension(145, 40);
        monthField.setPreferredSize(thirdField);
        dayField.setPreferredSize(thirdField);
        yearField.setPreferredSize(thirdField);

        JPanel birthdayPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 5, 0));
        birthdayPanel.setOpaque(false);
        birthdayPanel.add(monthField);
        birthdayPanel.add(dayField);
        birthdayPanel.add(yearField);

        innerGbc.gridy++;
        innerGbc.gridx = 0;
        innerGbc.gridwidth = 2;
        innerGbc.anchor = GridBagConstraints.CENTER;
        signUpPanel.add(birthdayPanel, innerGbc);

        // Gender title
        PLabel genderLabel = new PLabel("Gender", PLabel.HEADING3);
        genderLabel.setForeground(new Color(0x1E88E5));
        innerGbc.gridy++;
        innerGbc.gridx = 0;
        innerGbc.gridwidth = 1;
        innerGbc.anchor = GridBagConstraints.WEST;
        signUpPanel.add(genderLabel, innerGbc);

        PLabel mobileLabel = new PLabel("Mobile No.", PLabel.HEADING3);
        mobileLabel.setForeground(new Color(0x1E88E5));
        innerGbc.gridx = 1; // second column (same row)
        innerGbc.gridwidth = 1;
        signUpPanel.add(mobileLabel, innerGbc);

        // Gender + Mobile
        genderField = new PTextField(20);
        genderField.setPlaceholder("Gender");
        mobileField = new PTextField(20);
        mobileField.setPlaceholder("Mobile Number");
        genderField.setPreferredSize(halfField);
        mobileField.setPreferredSize(halfField);

        innerGbc.gridy++;
        innerGbc.gridwidth = 1;
        innerGbc.gridx = 0;
        signUpPanel.add(genderField, innerGbc);
        innerGbc.gridx = 1;
        signUpPanel.add(mobileField, innerGbc);

        // Email + Password
        emailField = new PTextField(20);
        emailField.setPlaceholder("Email");
        passwordField = new PTextField(20);
        passwordField.setPlaceholder("Password");
        Dimension fullField = new Dimension(480, 40);
        emailField.setPreferredSize(fullField);
        passwordField.setPreferredSize(fullField);

        innerGbc.gridy++;
        innerGbc.gridx = 0;
        innerGbc.gridwidth = 2;
        signUpPanel.add(emailField, innerGbc);

        innerGbc.gridy++;
        signUpPanel.add(passwordField, innerGbc);

        // Sign Up Button
        signUpButton = new PButton("Sign Up");
        signUpButton.setBackground(new Color(0, 174, 255));
        signUpButton.setForeground(Color.WHITE);
        signUpButton.setFont(new Font("SansSerif", Font.BOLD, 14));
        signUpButton.setPreferredSize(new Dimension(180, 40));

        innerGbc.gridy++;
        innerGbc.insets = new Insets(20, 0, 0, 0);
        innerGbc.fill = GridBagConstraints.NONE;
        innerGbc.anchor = GridBagConstraints.CENTER;
        signUpPanel.add(signUpButton, innerGbc);

        signUpButton.addActionListener(e -> {
            JFrame frame = (JFrame) SwingUtilities.getWindowAncestor(this);
            frame.dispose(); // close sign-up window
            UserLoginUI.main(null); // open login window
        });

        // Add Right Panel to Frame
        gbc.gridx = 1;
        gbc.gridy = 0;
        gbc.insets = new Insets(0, 0, 0, 40);
        gbc.fill = GridBagConstraints.VERTICAL;
        gbc.weighty = 1;
        add(signUpPanel, gbc);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            JFrame frame = new JFrame("PulsePoint - User Sign Up");
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.setSize(1000, 700);
            frame.add(new UserSignUpUI());
            frame.setLocationRelativeTo(null); // Center on screen
            frame.setVisible(true);
        });
    }

    // Getters for components
    public PTextField getFirstNameField() {
        return firstNameField;
    }

    public PTextField getLastNameField() {
        return lastNameField;
    }

    public PTextField getMonthField() {
        return monthField;
    }

    public PTextField getDayField() {
        return dayField;
    }

    public PTextField getYearField() {
        return yearField;
    }

    public PTextField getGenderField() {
        return genderField;
    }

    public PTextField getMobileField() {
        return mobileField;
    }

    public PTextField getEmailField() {
        return emailField;
    }

    public PTextField getPasswordField() {
        return passwordField;
    }

    public PButton getSignUpButton() {
        return signUpButton;
    }
}
