import javax.swing.*;
import java.awt.*;

public class UserLoginUI extends JPanel {
    private final PTextField emailField;
    private final PPasswordField passwordField; // Change to JPasswordField
    private final PButton loginButton;
    private final PButton signUpButton;
    private final Color clrGray = Color.decode("#f3e9e9");

    public UserLoginUI() {
        setLayout(new GridBagLayout());
        setBackground(new Color(255, 248, 248)); // Light pink

        GridBagConstraints gbc = new GridBagConstraints();

        // Left Logo Panel
        JPanel logoPanel = new JPanel();
        logoPanel.setBackground(new Color(255, 248, 248));
        PLabel logoLabel = new PLabel("PulsePoint", PLabel.HEADING1);
        logoLabel.setForeground(new Color(0, 174, 255));
        logoPanel.add(logoLabel);
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.insets = new Insets(0, 40, 0, 40);
        add(logoPanel, gbc);

        // Right Login Panel
        JPanel loginPanel = new PPanel(new GridBagLayout(), Color.WHITE, 30); // Corrected constructor
        loginPanel.setLayout(new GridBagLayout());
        loginPanel.setPreferredSize(new Dimension(500, 0));

        GridBagConstraints innerGbc = new GridBagConstraints();
        innerGbc.insets = new Insets(10, 10, 10, 10);
        innerGbc.fill = GridBagConstraints.HORIZONTAL;

        // Title
        PLabel titleLabel = new PLabel("Login User", PLabel.HEADING2);
        titleLabel.setForeground(new Color(0x1E88E5));
        titleLabel.setHorizontalAlignment(SwingConstants.CENTER);
        innerGbc.gridx = 0;
        innerGbc.gridy = 0;
        innerGbc.gridwidth = 2;
        loginPanel.add(titleLabel, innerGbc);
        innerGbc.anchor = GridBagConstraints.CENTER;

        // Email and Password Fields
        emailField = new PTextField(20);
        emailField.setPlaceholder("Email");
        emailField.setBorderColor(clrGray);
        emailField.setRadius(20);
        passwordField = new PPasswordField();
        passwordField.setBorderColor(clrGray);
        passwordField.setPlaceholder("Password");
        passwordField.setOpaque(false);
        passwordField.setRadius(20);

        emailField.setPreferredSize(new Dimension(480, 40));
        passwordField.setPreferredSize(new Dimension(480, 40));

        innerGbc.gridy++;
        loginPanel.add(emailField, innerGbc);
        innerGbc.gridy++;
        loginPanel.add(passwordField, innerGbc);

        // Login Button
        loginButton = new PButton("Login");
        loginButton.setBackground(new Color(0, 174, 255));
        loginButton.setForeground(Color.WHITE);
        loginButton.setRadius(30);
        loginButton.setPreferredSize(new Dimension(180, 40));


        innerGbc.gridy++;
        innerGbc.insets = new Insets(20, 0, 0, 0);
        innerGbc.fill = GridBagConstraints.NONE;
        innerGbc.anchor = GridBagConstraints.CENTER;
        loginPanel.add(loginButton, innerGbc);

        // Sign Up Prompt
        PLabel signUpPrompt = new PLabel("Don't have an account?", PLabel.HEADING3);
        signUpPrompt.setForeground(Color.GRAY);
        innerGbc.gridy++;
        innerGbc.insets = new Insets(10, 0, 0, 0);
        loginPanel.add(signUpPrompt, innerGbc);

        // Sign Up Button
        signUpButton = new PButton("Sign Up");
        signUpButton.setBackground(new Color(255, 174, 193));
        signUpButton.setForeground(Color.WHITE);

        signUpButton.setPreferredSize(new Dimension(180, 40));
        signUpButton.setRadius(30);

        innerGbc.gridy++;
        loginPanel.add(signUpButton, innerGbc);

        // Action to open SignUpUI
//        signUpButton.addActionListener(e -> {
//            JFrame frame = (JFrame) SwingUtilities.getWindowAncestor(this);
//            frame.dispose(); // Close login window
//            UserSignUpUI.main(null); // Open sign-up window
//        });

        // Add right panel to main panel
        gbc.gridx = 1;
        gbc.gridy = 0;
        gbc.insets = new Insets(0, 0, 0, 40);
        gbc.fill = GridBagConstraints.VERTICAL;
        gbc.weighty = 1;
        add(loginPanel, gbc);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            JFrame frame = new JFrame("PulsePoint - User Login");
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.setSize(1000, 700);
            frame.add(new UserLoginUI());
            frame.setLocationRelativeTo(null);
            frame.setVisible(true);
        });
    }

    // Getters for private components
    public PTextField getEmailField() {
        return emailField;
    }

    public PPasswordField getPasswordField() {
        return passwordField; // Return JPasswordField
    }

    public PButton getLoginButton() {
        return loginButton;
    }

    public PButton getSignUpButton() {
        return signUpButton;
    }
}
