/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package gui;
import model.user;
import javax.swing.*;
import java.awt.*;
import gui.PiesePanel;
import gui.CategoriiPanel;
/**
 *
 * @author Asus
 */
public class MainFrame extends JFrame {
    private JPanel menuPanel;
    private JPanel contentPanel;
    public MainFrame(user user){
        setTitle("Magazin Piese Auto");
        setSize(1000,600);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());
        menuPanel=new JPanel();
        menuPanel.setLayout(new GridLayout(10,1));
        JButton btnPiese=new JButton("Piese");
        JButton btnCategorii=new JButton("Categorii");
        JButton btnUtilizatori=new JButton("Utilizatori");
        JButton btnVanzari=new JButton("Vanzari");
        JButton btnLogout=new JButton("Logout");
        menuPanel.add(btnPiese);
        menuPanel.add(btnCategorii);
        menuPanel.add(btnUtilizatori);
        menuPanel.add(btnVanzari);
        menuPanel.add(btnLogout);
        contentPanel=new JPanel();
        PiesePanel piesePanel=new PiesePanel();
        CategoriiPanel categoriiPanel=new CategoriiPanel();
        VanzariPanel vanzariPanel=new VanzariPanel();
        UtilizatoriPanel utilizatoriPanel=new UtilizatoriPanel();
        add(menuPanel, BorderLayout.WEST);
        add(contentPanel,BorderLayout.CENTER);
        if(user.getRole().equals("EMPLOYEE")){
            btnUtilizatori.setVisible(false);
        }
        btnPiese.addActionListener(e->{
            contentPanel.removeAll();
            contentPanel.setLayout(new BorderLayout());
            contentPanel.add(piesePanel,BorderLayout.CENTER);
            contentPanel.revalidate();
            contentPanel.repaint();
        });
        btnCategorii.addActionListener(e->{
           contentPanel.removeAll();
           contentPanel.setLayout(new BorderLayout());
           contentPanel.add(categoriiPanel,BorderLayout.CENTER);
           contentPanel.revalidate();
           contentPanel.repaint();
        });
        btnVanzari.addActionListener(e->{
            vanzariPanel.incarcaPiese();
            contentPanel.removeAll();
            contentPanel.setLayout(new BorderLayout());
            contentPanel.add(vanzariPanel,BorderLayout.CENTER);
            contentPanel.revalidate();
            contentPanel.repaint();
        });
        btnUtilizatori.addActionListener(e->{
           contentPanel.removeAll();
           contentPanel.setLayout(new BorderLayout());
           contentPanel.add(utilizatoriPanel,BorderLayout.CENTER);
           contentPanel.revalidate();
           contentPanel.repaint();
        });
        btnLogout.addActionListener(e->{
            dispose();
            new LoginFrame();
        });
        setVisible(true);
        
    }
}
