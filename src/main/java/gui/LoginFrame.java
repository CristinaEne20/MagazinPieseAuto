/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package gui;
import dao.UserDAO;
import model.user;
import javax.swing.*;
import java.awt.*;
/**
 *
 * @author Asus
 */
public class LoginFrame extends JFrame{
    private JTextField txtUsername;
    private JPasswordField txtPassword;
    private JButton btnLogin;
    public LoginFrame(){
        setTitle("Login");
        setSize(350,200);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new GridLayout(3,2));
        add(new JLabel("Username"));
        txtUsername=new JTextField();
        add(txtUsername);
        add(new JLabel("Password"));
        txtPassword=new JPasswordField();
        add(txtPassword);
        btnLogin=new JButton("Login");
        add(new JLabel());
        add(btnLogin);
        btnLogin.addActionListener(e->login());
        txtUsername.addActionListener(e -> login());
        txtPassword.addActionListener(e -> login());
        setVisible(true);
    }
    private void login(){
        String username=txtUsername.getText();
        String password=new String(txtPassword.getPassword());
        UserDAO dao=new UserDAO();
        user user=dao.login(username, password);
        if(user!=null){
        new MainFrame(user);
        dispose();
        }else{
            JOptionPane.showMessageDialog(this, "Date incorecte!");
        }
    }
    
}
