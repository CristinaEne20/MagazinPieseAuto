/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package gui;
import dao.UserDAO;
import model.user;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.List;
/**
 *
 * @author Asus
 */
public class UtilizatoriPanel extends JPanel {
    private JTextField txtUsername;
    private JTextField txtPassword;
    private JComboBox<String> comboRole;
    private JTable table;
    private DefaultTableModel model;
    private UserDAO dao =new UserDAO();
    public UtilizatoriPanel() {
        setLayout(new BorderLayout());
        JPanel top =new JPanel();
        txtUsername =new JTextField(10);
        txtPassword =new JTextField(10);
        comboRole =new JComboBox<>();
        comboRole.addItem("ADMIN");
        comboRole.addItem("EMPLOYEE");
        JButton btnAdd = new JButton("Adauga");
        top.add(new JLabel("Username"));
        top.add(txtUsername);
        top.add(new JLabel("Password"));
        top.add(txtPassword);
        top.add(comboRole);
        top.add(btnAdd);
        add(top, BorderLayout.NORTH);
        model =new DefaultTableModel();
        model.setColumnIdentifiers(new String[]{
                        "ID","Username","Role"
                });
        table =new JTable(model);
        add(new JScrollPane(table),BorderLayout.CENTER);
        btnAdd.addActionListener(e -> adaugaUser());
        incarcaDate();
    }
    private void incarcaDate() {
        model.setRowCount(0);
        List<user> users =dao.getAllUsers();
        for(user u : users) {
            model.addRow( new Object[]{
                            u.getId(),
                            u.getUsername(),
                            u.getRole()
                    });
        }
    }
    private void adaugaUser() {
        user u =new user();
        u.setUsername(txtUsername.getText());
        u.setPassword(txtPassword.getText());
        u.setRole( comboRole.getSelectedItem().toString());
        dao.adaugaUser(u);
        incarcaDate();
    }
}
