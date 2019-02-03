package com.student.view;

import java.awt.Color;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.regex.Pattern;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import com.student.AppConstants;
import com.student.DAO;
import com.student.base.BaseDAO;
import com.student.dao.StudentDAO;
import com.student.model.Student;

/**
 * @Description: LoginView
 * @ClassName: LoginView
 * 
 */
public class LoginView extends JFrame {

    private static final long serialVersionUID = 1L;
    private JPanel contentPane;
    private JTextField userField;
    private JPasswordField passwordField;

    public LoginView() {
        setResizable(false);
        setTitle(AppConstants.LOGIN_TITLE);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setBounds(100, 100, 300, 200);
        contentPane = new JPanel();
        setContentPane(contentPane);
        contentPane.setLayout(new GridLayout(4, 1));

        contentPane.add(new JPanel());

        JPanel userPanel = new JPanel();
        contentPane.add(userPanel);

        JLabel userLabel = new JLabel(AppConstants.LOGIN_USERNAME);
        userPanel.add(userLabel);

        userField = new JTextField(10);
        userPanel.add(userField);

        JPanel passwordPanel = new JPanel();
        contentPane.add(passwordPanel);

        JLabel passwordLael = new JLabel(AppConstants.LOGIN_PASSWORD);
        passwordPanel.add(passwordLael);

        passwordField = new JPasswordField();
        passwordField.setColumns(10);
        passwordPanel.add(passwordField);

        JPanel btnPanel = new JPanel();
        contentPane.add(btnPanel);

        JButton loginbtn = new JButton(AppConstants.LOGIN);
        btnPanel.add(loginbtn);
        loginbtn.addActionListener(new LoginListener());
        getRootPane().setDefaultButton(loginbtn);

        JButton exitbtn = new JButton(AppConstants.EXIT);
        exitbtn.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
            }
        });
        btnPanel.add(exitbtn);
        setVisible(true);
    }

    private class LoginListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {

            String username = userField.getText();
            String password = String.valueOf(passwordField.getPassword());

            boolean isValid = true;
            if (Pattern.matches(AppConstants.REGEX_USERNAME, username) == false) {
                userField.setBackground(Color.PINK);
                isValid = false;
            }
            if (Pattern.matches(AppConstants.REGEX_PASSWORD, password) == false) {
                passwordField.setBackground(Color.PINK);
                isValid = false;
            }

            if (isValid == false) {
                return;
            } else {
                userField.setBackground(Color.WHITE);
                passwordField.setBackground(Color.WHITE);
            }

            if (username.equals("admin")) {
                if (password.equals("admin1234")) {
                    System.out.println("Admin Login Success.");
                    dispose();
                    // TODO: Admin Login
                } else {
                    userField.setBackground(Color.PINK);
                    passwordField.setBackground(Color.PINK);
                    JOptionPane.showMessageDialog(null, AppConstants.LOGIN_ERROR);
                }
            } else {
                StudentDAO studentDAO = (StudentDAO) BaseDAO.getAbilityDAO(DAO.StudentDAO);
                String sno = studentDAO.queryForLogin(username, password);
                if (sno != null) {
                    System.out.println("Student " + sno + " Login Success.");
                    dispose();
                    // TODO: Student Login
                    new StudentView(new Student(sno));
                } else {
                    userField.setBackground(Color.PINK);
                    passwordField.setBackground(Color.PINK);
                    JOptionPane.showMessageDialog(null, AppConstants.LOGIN_ERROR);
                }
            }
        }
    }
}
