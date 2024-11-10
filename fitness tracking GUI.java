import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

// Main Class
public class FitnessAppGUI {
    // User class to hold user information
    static class User {
        private String name;
        private String email;
        private String password;
        private String weight;
        private String height;
        private String fitnessPreference;

        public User(String name, String email, String password) {
            this.name = name;
            this.email = email;
            this.password = password;
            this.weight = "";
            this.height = "";
            this.fitnessPreference = "";
        }

        public String getName() {
            return name;
        }

        public String getEmail() {
            return email;
        }

        public String getPassword() {
            return password;
        }

        public String getWeight() {
            return weight;
        }

        public void setWeight(String weight) {
            this.weight = weight;
        }

        public String getHeight() {
            return height;
        }

        public void setHeight(String height) {
            this.height = height;
        }

        public String getFitnessPreference() {
            return fitnessPreference;
        }

        public void setFitnessPreference(String fitnessPreference) {
            this.fitnessPreference = fitnessPreference;
        }
    }

    // List to hold all users (for demo purposes, in a real app, this would be a database)
    private static List<User> users = new ArrayList<>();
    private static User loggedInUser = null;

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            createLoginUI(); // Launch the Login UI
        });
    }

    // Create Login UI
    private static void createLoginUI() {
        JFrame frame = new JFrame("Fitness Tracking Application");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(400, 300);
        frame.setLocationRelativeTo(null); // Center the window

        JPanel loginPanel = new JPanel();
        loginPanel.setLayout(new GridLayout(5, 2));

        JLabel emailLabel = new JLabel("Email:");
        JLabel passwordLabel = new JLabel("Password:");

        JTextField emailField = new JTextField();
        JPasswordField passwordField = new JPasswordField();

        JButton loginButton = new JButton("Login");
        JButton createAccountButton = new JButton("Create Account");

        loginPanel.add(emailLabel);
        loginPanel.add(emailField);
        loginPanel.add(passwordLabel);
        loginPanel.add(passwordField);
        loginPanel.add(new JLabel()); // Empty space
        loginPanel.add(loginButton);
        loginPanel.add(createAccountButton);

        frame.add(loginPanel);
        frame.setVisible(true);

        // Login button action
        loginButton.addActionListener(e -> {
            String email = emailField.getText();
            String password = new String(passwordField.getPassword());

            if (authenticateUser(email, password)) {
                loggedInUser = new User("User", email, password); // Logged in user object
                frame.dispose(); // Close the login window
                createDashboardUI(); // Open the dashboard
            } else {
                JOptionPane.showMessageDialog(frame, "Invalid login credentials.", "Error", JOptionPane.ERROR_MESSAGE);
            }
        });

        // Create Account button action
        createAccountButton.addActionListener(e -> {
            frame.dispose(); // Close login window
            createAccountUI(); // Open account creation form
        });
    }

    // Authenticate user by email and password (for demo purposes, simple check)
    private static boolean authenticateUser(String email, String password) {
        for (User user : users) {
            if (user.getEmail().equals(email) && user.getPassword().equals(password)) {
                return true;
            }
        }
        return false;
    }

    // Create Account UI (For new users to register)
    private static void createAccountUI() {
        JFrame frame = new JFrame("Create New Account");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(400, 300);
        frame.setLocationRelativeTo(null); // Center the window

        JPanel accountPanel = new JPanel();
        accountPanel.setLayout(new GridLayout(4, 2));

        JLabel nameLabel = new JLabel("Name:");
        JLabel emailLabel = new JLabel("Email:");
        JLabel passwordLabel = new JLabel("Password:");

        JTextField nameField = new JTextField();
        JTextField emailField = new JTextField();
        JPasswordField passwordField = new JPasswordField();

        JButton createButton = new JButton("Create Account");
        JButton backButton = new JButton("Back to Login");

        accountPanel.add(nameLabel);
        accountPanel.add(nameField);
        accountPanel.add(emailLabel);
        accountPanel.add(emailField);
        accountPanel.add(passwordLabel);
        accountPanel.add(passwordField);
        accountPanel.add(createButton);
        accountPanel.add(backButton);

        frame.add(accountPanel);
        frame.setVisible(true);

        // Create Account button action
        createButton.addActionListener(e -> {
            String name = nameField.getText();
            String email = emailField.getText();
            String password = new String(passwordField.getPassword());

            if (email.isEmpty() || password.isEmpty() || name.isEmpty()) {
                JOptionPane.showMessageDialog(frame, "All fields are required!", "Error", JOptionPane.ERROR_MESSAGE);
            } else {
                // Add new user to the list
                users.add(new User(name, email, password));
                JOptionPane.showMessageDialog(frame, "Account created successfully! Please log in.");
                frame.dispose();
                createLoginUI(); // Go back to login screen
            }
        });

        // Back to Login button action
        backButton.addActionListener(e -> {
            frame.dispose();
            createLoginUI(); // Return to login screen
        });
    }

    // Create Dashboard UI (After successful login)
    private static void createDashboardUI() {
        JFrame frame = new JFrame("Main Dashboard");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(600, 400);
        frame.setLocationRelativeTo(null); // Center the window

        JPanel panel = new JPanel();
        panel.setLayout(new BorderLayout());

        // Welcome message
        JLabel welcomeLabel = new JLabel("Welcome, " + loggedInUser.getName(), SwingConstants.CENTER);
        welcomeLabel.setFont(new Font("Arial", Font.BOLD, 24));

        // Create buttons for further functionalities
        JButton viewProgressButton = new JButton("View Progress");
        JButton workoutPlanButton = new JButton("View Workout Plan");
        JButton logoutButton = new JButton("Logout");
        JButton editProfileButton = new JButton("Edit Profile");

        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new GridLayout(4, 1));
        buttonPanel.add(viewProgressButton);
        buttonPanel.add(workoutPlanButton);
        buttonPanel.add(editProfileButton);
        buttonPanel.add(logoutButton);

        // Profile Section
        JPanel profilePanel = new JPanel();
        profilePanel.setLayout(new GridLayout(4, 1));

        JLabel weightLabel = new JLabel("Weight: " + (loggedInUser.getWeight().isEmpty() ? "Not set" : loggedInUser.getWeight()));
        JLabel heightLabel = new JLabel("Height: " + (loggedInUser.getHeight().isEmpty() ? "Not set" : loggedInUser.getHeight()));
        JLabel fitnessPreferenceLabel = new JLabel("Fitness Preference: " + (loggedInUser.getFitnessPreference().isEmpty() ? "Not set" : loggedInUser.getFitnessPreference()));

        profilePanel.add(weightLabel);
        profilePanel.add(heightLabel);
        profilePanel.add(fitnessPreferenceLabel);

        frame.add(welcomeLabel, BorderLayout.NORTH);
        frame.add(buttonPanel, BorderLayout.WEST);
        frame.add(profilePanel, BorderLayout.CENTER);
        frame.setVisible(true);

        // Edit Profile Button Action
        editProfileButton.addActionListener(e -> {
            editProfileUI(); // Open the profile editing form
        });

        // Logout Button Action
        logoutButton.addActionListener(e -> {
            loggedInUser = null; // Clear the logged-in user
            frame.dispose(); // Close the dashboard
            createLoginUI(); // Go back to the login screen
        });

        // Placeholder actions for buttons
        viewProgressButton.addActionListener(e -> JOptionPane.showMessageDialog(frame, "Your progress is being tracked."));
        workoutPlanButton.addActionListener(e -> JOptionPane.showMessageDialog(frame, "View your workout plan here."));
    }

    // Edit Profile UI (Allows users to input weight, height, and fitness preferences)
    private static void editProfileUI() {
        JFrame frame = new JFrame("Edit Profile");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(400, 300);
        frame.setLocationRelativeTo(null); // Center the window

        JPanel editPanel = new JPanel();
        editPanel.setLayout(new GridLayout(4, 2));

        JLabel weightLabel = new JLabel("Weight:");
        JLabel heightLabel = new JLabel("Height:");
        JLabel fitnessLabel = new JLabel("Fitness Preference:");

        JTextField weightField = new JTextField(loggedInUser.getWeight());
        JTextField heightField = new JTextField(loggedInUser.getHeight());
        JTextField fitnessField = new JTextField(loggedInUser.getFitnessPreference());

        JButton saveButton = new JButton("Save");
        JButton cancelButton = new JButton("Cancel");

        editPanel.add(weightLabel);
        editPanel.add(weightField);
        editPanel.add(heightLabel);
        editPanel.add(heightField);
        editPanel.add(fitnessLabel);
        editPanel.add(fitnessField);
        editPanel.add(saveButton);
        editPanel.add(cancelButton);

        frame.add(editPanel);
        frame.setVisible(true);

        // Save Button Action
        saveButton.addActionListener(e -> {
            loggedInUser.setWeight(weightField.getText());
            loggedInUser.setHeight(heightField.getText());
            loggedInUser.setFitnessPreference(fitnessField.getText());
            JOptionPane.showMessageDialog(frame, "Profile updated successfully.");
            frame.dispose();
            createDashboardUI(); // Return to the dashboard
        });

        // Cancel Button Action
        cancelButton.addActionListener(e -> {
            frame.dispose(); // Close the edit profile screen
            createDashboardUI(); // Return to the dashboard
        });
    }
}
