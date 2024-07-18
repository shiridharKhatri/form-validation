package Auth;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import javax.swing.border.*;

public class AuthForm {

    private static ArrayList<UserData> data = new ArrayList<>();

    public static void signup(boolean visibility) {
        GridBagLayout layout = new GridBagLayout();
        GridBagConstraints gbc = new GridBagConstraints();
        Border border = new MatteBorder(0,0,2,0,new Color(3, 231, 246));

        JFrame frame = new JFrame();
        frame.setTitle("Signup");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(500, 600);

        JPanel panelMessage = new JPanel();
        panelMessage.setBorder(new EmptyBorder(20, 20, 20, 20));
        panelMessage.setBackground(new Color(1, 19, 29));
        JPanel formPanel = new JPanel();
        formPanel.setLayout(layout);
        formPanel.setBackground(new Color(1, 19, 29));
        JPanel panelSignup = new JPanel();

        JLabel topHeaderLabel = new JLabel("Create new account!!");
        topHeaderLabel.setFont(new Font("Arial", Font.BOLD, 25));
        topHeaderLabel.setForeground(Color.WHITE);
        JLabel messageLabel = new JLabel();
        JLabel dontHaveAnAccount = new JLabel("Already have an account?");
        dontHaveAnAccount.setFont(new Font("Arial", Font.BOLD, 14));
        dontHaveAnAccount.setForeground(Color.WHITE);

        JLabel createAccountLabel = new JLabel("Login");
        createAccountLabel.setFont(new Font("Arial", Font.BOLD, 16));
        createAccountLabel.setForeground(Color.WHITE);
        createAccountLabel.setForeground(new Color(3, 231, 246));
        createAccountLabel.setCursor(new Cursor(Cursor.HAND_CURSOR));
        createAccountLabel.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                login(true);
                frame.setVisible(false);
            }
        });
        JLabel usernameLabel = new JLabel("Username");
        usernameLabel.setFont(new Font("Arial", Font.PLAIN, 16));
        usernameLabel.setForeground(Color.WHITE);
        JLabel emailLabel = new JLabel("Email");
        emailLabel.setFont(new Font("Arial", Font.PLAIN, 16));
        emailLabel.setForeground(Color.WHITE);
        JLabel passwordLabel = new JLabel("Password");
        passwordLabel.setFont(new Font("Arial", Font.PLAIN, 16));
        passwordLabel.setForeground(Color.WHITE);

        JButton loginButton = new JButton("Signup");
        loginButton.setFont(new Font("Arial", Font.BOLD, 15));
        loginButton.setBackground(new Color(3, 231, 246));
        loginButton.setBorder(null);
        loginButton.setCursor(new Cursor(Cursor.HAND_CURSOR));

        JTextField usernameTf = new JTextField();
        usernameTf.setBorder(border);
        usernameTf.setBackground(new Color(3, 39,58));
        usernameTf.setForeground(Color.WHITE);
        JTextField emailTf = new JTextField();
        emailTf.setBorder(border);
        emailTf.setBackground(new Color(3, 39,58));
        emailTf.setForeground(Color.WHITE);
        JTextField passwordTf = new JTextField();
        passwordTf.setBorder(border);
        passwordTf.setBackground(new Color(3, 39,58));
        passwordTf.setForeground(Color.WHITE);

        JLabel errorUserName = new JLabel();
        errorUserName.setForeground(Color.RED);
        errorUserName.setFont(new Font("Arial", Font.PLAIN, 10));
        JLabel emailError = new JLabel();
        emailError.setFont(new Font("Arial", Font.PLAIN, 10));
        emailError.setForeground(Color.RED);
        JLabel errorPassword = new JLabel();
        errorPassword.setFont(new Font("Arial", Font.PLAIN, 10));
        errorPassword.setForeground(Color.RED);

        loginButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (usernameTf.getText().isEmpty() && passwordTf.getText().isEmpty() && emailTf.getText().isEmpty()) {
                    errorUserName.setText("Username must not be empty!");
                    errorPassword.setText("Password must not be empty");
                    emailError.setText("Email is required");
                    Border border = new MatteBorder(0,0,2,0,Color.RED);
                    usernameTf.setBorder(border);
                    passwordTf.setBorder(border);
                    emailTf.setBorder(border);
                } else {
                    Border border = new MatteBorder(0,0,2,0, new Color(3, 231, 246));
                    usernameTf.setBorder(border);
                    passwordTf.setBorder(border);
                    emailTf.setBorder(border);
                    if(emailTf.getText().contains("@")){
                        errorUserName.setText("");
                        errorPassword.setText("");
                        emailError.setText("");
                        boolean isExist = false;
                        for (UserData userData : data) {
                            if (userData.getUsername().equals(usernameTf.getText())) {
                                isExist = true;
                                break;
                            }
                        }
                        if (!isExist) {
                            data.add(new UserData(usernameTf.getText(), passwordTf.getText(), emailTf.getText()));
                            usernameTf.setText("");
                            passwordTf.setText("");
                            emailTf.setText("");
                            JOptionPane.showMessageDialog(null, "Account created successfully, now proceed with login!");
                            frame.setVisible(false);
                            login(true);
                        } else {
                            Border newBorder = new MatteBorder(0,0,2,0, Color.RED);
                            usernameTf.setBorder(newBorder);
                            errorUserName.setText("User with this username already exists!");
                        }
                    }else{
                        Border newBorder = new MatteBorder(0,0,2,0, Color.RED);
                        emailTf.setBorder(newBorder);
                        emailError.setText("Please enter valid email!!");
                    }

                }
            }
        });

        panelMessage.add(topHeaderLabel);
        panelSignup.add(dontHaveAnAccount);
        panelSignup.add(createAccountLabel);
        panelSignup.setBackground(new Color(1, 19, 29));

        gbc.gridwidth = 2;
        gbc.insets = new Insets(0, 0, 30, 0);
        formPanel.add(panelMessage, gbc);

        gbc.insets = new Insets(0, 0, 0, 0);
        gbc.gridwidth = 1;
        gbc.gridy = 1;
        formPanel.add(usernameLabel, gbc);

        gbc.gridy = 2;
        gbc.gridwidth = 2;
        gbc.fill = GridBagConstraints.BOTH;
        gbc.ipady = 15;
        gbc.insets = new Insets(5, 0, 0, 0);
        formPanel.add(usernameTf, gbc);

        gbc.gridy = 3;
        gbc.insets = new Insets(-5, 0, -10, 0);
        formPanel.add(errorUserName, gbc);

        gbc.gridy = 4;
        gbc.gridwidth = 1;
        gbc.insets = new Insets(5, 0, -5, 0);
        formPanel.add(emailLabel, gbc);

        gbc.gridy = 5;
        gbc.gridwidth = 2;
        gbc.fill = GridBagConstraints.BOTH;
        gbc.ipady = 15;
        formPanel.add(emailTf, gbc);

        gbc.gridy = 6;
        gbc.insets = new Insets(0, 0, -10, 0);
        formPanel.add(emailError, gbc);

        gbc.gridy = 7;
        gbc.gridwidth = 1;
        gbc.insets = new Insets(5, 0, -5, 0);
        formPanel.add(passwordLabel, gbc);

        gbc.gridy = 8;
        gbc.gridwidth = 2;
        gbc.fill = GridBagConstraints.BOTH;
        gbc.ipady = 15;
        formPanel.add(passwordTf, gbc);

        gbc.gridy = 9;
        gbc.insets = new Insets(0, 0, -5, 0);
        formPanel.add(errorPassword, gbc);

        gbc.gridy = 10;
        gbc.ipady = 22;
        gbc.insets = new Insets(20, 0, 20, 0);
        formPanel.add(loginButton, gbc);

        gbc.gridy = 11;
        gbc.insets = new Insets(10, 20, 20, 20);
        formPanel.add(panelSignup, gbc);

        frame.add(formPanel);
        frame.setVisible(visibility);
    }

    public static void login(boolean visibility) {
        GridBagLayout layout = new GridBagLayout();
        GridBagConstraints gbc = new GridBagConstraints();
        final Border[] border = {new MatteBorder(0, 0, 2, 0, new Color(3, 231, 246))};

        JFrame frame = new JFrame();
        frame.setTitle("Login");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(500, 500);

        JPanel panelMessage = new JPanel();
        panelMessage.setBorder(new EmptyBorder(20, 20, 20, 20));
        panelMessage.setBackground(new Color(1, 19, 29));
        JPanel formPanel = new JPanel();
        formPanel.setLayout(layout);
        formPanel.setBackground(new Color(1, 19, 29));
        JPanel panelSignup = new JPanel();

        JLabel topHeaderLabel = new JLabel("Welcome Back!!");
        topHeaderLabel.setFont(new Font("AppleGothic", Font.BOLD, 25));
        topHeaderLabel.setForeground(Color.WHITE);
        JLabel messageLabel = new JLabel();
        JLabel dontHaveAnAccount = new JLabel("Don't have an account?");
        dontHaveAnAccount.setFont(new Font("Arial", Font.BOLD, 14));
        dontHaveAnAccount.setForeground(Color.WHITE);

        JLabel createAccountLabel = new JLabel("Create Account.");
        createAccountLabel.setCursor(new Cursor(Cursor.HAND_CURSOR));
        createAccountLabel.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                signup(true);
                frame.setVisible(false);
            }
        });
        createAccountLabel.setFont(new Font("Arial", Font.BOLD, 16));
        createAccountLabel.setForeground(new Color(3, 231, 246));
        JLabel usernameLabel = new JLabel("Username");
        usernameLabel.setFont(new Font("Arial", Font.TRUETYPE_FONT, 16));
        usernameLabel.setForeground(Color.WHITE);
        JLabel passwordLabel = new JLabel("Password");
        passwordLabel.setFont(new Font("Arial", Font.TRUETYPE_FONT, 16));
        passwordLabel.setForeground(Color.WHITE);

        JButton loginButton = new JButton("Login");
        loginButton.setFont(new Font("Arial", Font.BOLD, 15));
        loginButton.setBackground(new Color(0, 233, 246));
        loginButton.setBorder(null);
        loginButton.setCursor(new Cursor(Cursor.HAND_CURSOR));

        JTextField usernameTf = new JTextField();
        usernameTf.setBorder(border[0]);
        usernameTf.setOpaque(true);
        usernameTf.setBackground(new Color(3, 39,58));
        usernameTf.setForeground(Color.WHITE);
        JTextField passwordTf = new JTextField();
        passwordTf.setBorder(border[0]);
        passwordTf.setBackground(new Color(3, 39,58));
        passwordTf.setForeground(Color.WHITE);
        JLabel errorUserName = new JLabel();
        errorUserName.setForeground(Color.RED);
        errorUserName.setFont(new Font("Arial", Font.PLAIN, 10));
        JLabel errorPassword = new JLabel();
        errorPassword.setForeground(Color.RED);
        errorPassword.setFont(new Font("Arial", Font.PLAIN, 10));
        loginButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (usernameTf.getText().isEmpty() && passwordTf.getText().isEmpty()) {
                    errorUserName.setText("Username must not be empty!");
                    errorPassword.setText("Password must not be empty!");
                    Border border = new MatteBorder(0,0,2,0,Color.RED);
                    usernameTf.setBorder(border);
                    passwordTf.setBorder(border);
                } else {
                    Border border = new MatteBorder(0,0,2,0,new Color(3, 231, 246));
                    usernameTf.setBorder(border);
                    passwordTf.setBorder(border);
                    System.out.println(data.size());
                    if(data.isEmpty()){
                        Border bordern = new MatteBorder(0,0,2,0,Color.RED);
                        usernameTf.setBorder(bordern);
                        passwordTf.setBorder(bordern);
                        errorUserName.setText("Please enter correct username!");
                        errorPassword.setText("Please enter correct password!");
                    }else{
                        for(UserData user: data){
                            if(usernameTf.getText().equals(user.getUsername())){
                                if(passwordTf.getText().equals(user.getPassword())){
                                    JOptionPane.showMessageDialog(null, "Welcome back "+user.getUsername());
                                   new MatteBorder(0,0,2,0,new Color(3, 231, 246));
                                    usernameTf.setBorder(border);
                                    passwordTf.setBorder(border);
                                    usernameTf.setText("");
                                    passwordTf.setText("");
                                    errorUserName.setText("");
                                    errorPassword.setText("");
                                }else{
                                    Border bordern = new MatteBorder(0,0,2,0,Color.RED);
                                    passwordTf.setBorder(bordern);
                                    errorPassword.setText("");
                                    errorPassword.setText("Incorrect password!");
                                }
                            }else{
                                Border bordern = new MatteBorder(0,0,2,0,Color.RED);
                                usernameTf.setBorder(bordern);
                                errorUserName.setText("");
                                errorUserName.setText("Incorrect Username please recheck!");
                            }
                        }
                    }

                }
            }
        });
        panelMessage.add(topHeaderLabel);
        panelSignup.add(dontHaveAnAccount);
        panelSignup.add(createAccountLabel);
        panelSignup.setBackground(new Color(1, 19, 29));

        gbc.gridwidth = 2;
        gbc.insets = new Insets(0, 0, 30, 0);
        formPanel.add(panelMessage, gbc);
        gbc.insets = new Insets(0, 0, 0, 0);
        gbc.gridwidth = 1;
        gbc.gridy = 1;
        formPanel.add(usernameLabel, gbc);
        gbc.gridy = 2;
        gbc.gridwidth = 2;
        gbc.fill = GridBagConstraints.BOTH;
        gbc.ipady = 15;
        gbc.insets = new Insets(5, 0, 0, 0);
        formPanel.add(usernameTf, gbc);
        gbc.gridy = 3;
        gbc.insets = new Insets(-5, 0, -10, 0);
        formPanel.add(errorUserName, gbc);
        gbc.gridy = 4;
        gbc.gridwidth = 1;
        gbc.insets = new Insets(5, 0, -5, 0);
        formPanel.add(passwordLabel, gbc);
        gbc.gridy = 5;
        gbc.gridwidth = 2;
        formPanel.add(passwordTf, gbc);
        gbc.gridy = 6;
        gbc.insets = new Insets(0, 0, -5, 0);
        formPanel.add(errorPassword, gbc);
        gbc.gridy = 7;
        gbc.ipady = 22;
        gbc.insets = new Insets(20, 0, 20, 0);
        formPanel.add(loginButton, gbc);
        gbc.gridy = 8;
        gbc.insets = new Insets(10, 20, 20, 20);
        formPanel.add(panelSignup, gbc);

        frame.add(formPanel);
        frame.setVisible(visibility);
    }

    public void display() {
        login(true);
    }

    public static void main(String[] args) {
        AuthForm form = new AuthForm();
        form.display();
    }
}