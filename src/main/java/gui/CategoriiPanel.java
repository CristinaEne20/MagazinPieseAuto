/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package gui;
import dao.CategorieDAO;
import model.Categorie;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.List;
/**
 *
 * @author Asus
 */
public class CategoriiPanel extends JPanel {
    private JTextField txtNume;
    private JTable table;
    private DefaultTableModel model;
    private CategorieDAO dao=new CategorieDAO();
    
    public CategoriiPanel(){
        setLayout(new BorderLayout());
        JPanel topPanel=new JPanel();
        topPanel.add(new JLabel("Nume"));
        txtNume=new JTextField(20);
        topPanel.add(txtNume);
        JButton btnAdauga=new JButton("Adauga");
        JButton btnModifica=new JButton("Modifica");
        JButton btnSterge=new JButton("Sterge");
        topPanel.add(btnAdauga);
        topPanel.add(btnModifica);
        topPanel.add(btnSterge);
        add(topPanel,BorderLayout.NORTH);
        model=new DefaultTableModel();
        model.setColumnIdentifiers(new String[]{
            "ID","Nume"
        });
        table=new JTable(model);
        javax.swing.table.DefaultTableCellRenderer centerRenderer =
        new javax.swing.table.DefaultTableCellRenderer();
        centerRenderer.setHorizontalAlignment(SwingConstants.CENTER);
        for(int i = 0; i < table.getColumnCount(); i++){
        table.getColumnModel().getColumn(i).setCellRenderer(centerRenderer);
}
        JScrollPane scroll=new JScrollPane(table);
        add(scroll,BorderLayout.CENTER);
        btnAdauga.addActionListener(e->adaugaCategorie());
        btnModifica.addActionListener(e->modificaCategorie());
        btnSterge.addActionListener(e->stergeCategorie());
        incarcaDate();
    }
    private void incarcaDate(){
        model.setRowCount(0);
        List<Categorie> lista=dao.getAllCategorii();
        for(Categorie c: lista){
            model.addRow(new Object[]{
                c.getId(),c.getNume()
            });
        }
        
    }
    private void adaugaCategorie() {
    if(dao.existaCategorie(txtNume.getText())){
        JOptionPane.showMessageDialog(this,"Categoria exista deja!","Atentie",JOptionPane.WARNING_MESSAGE);
        return;
    }
    Categorie c =new Categorie();
    c.setNume(txtNume.getText());
    dao.adaugaCategorie(c);
    incarcaDate();
    txtNume.setText("");
}
    private void modificaCategorie(){
        int row=table.getSelectedRow();
        if(row==-1){
            JOptionPane.showMessageDialog(this, "Selecteaza o categorie!");
            return;
        }
        int id=(int)model.getValueAt(row, 0);
        Categorie c=new Categorie();
        c.setId(id);
        c.setNume(txtNume.getText());
        dao.modificaCategorie(c);
        incarcaDate();
    }
    private void stergeCategorie(){
        int row=table.getSelectedRow();
        if(row==-1){
            JOptionPane.showMessageDialog(this, "Selecteaza o categorie!");
            return;
        }
        int id=(int)model.getValueAt(row, 0);
        dao.stergeCategorie(id);
        incarcaDate();
    }
    
    
}
