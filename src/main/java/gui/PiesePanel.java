/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package gui;
import dao.PiesaDAO;
import model.PiesaAuto;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.List;

/**
 *
 * @author Asus
 */
public class PiesePanel extends JPanel {
private JTextField txtNume;
private JTextField txtProducator;
private JTextField txtPret;
private JTextField txtStoc;
private JTextField txtCategorie;
private JTextField txtCautare;
private JTable table;
private DefaultTableModel model;
private PiesaDAO dao=new PiesaDAO();

public PiesePanel(){
    setLayout(new BorderLayout());
    JPanel topPanel=new JPanel(new GridLayout(7,2));
    topPanel.add(new JLabel("Nume"));
    txtNume=new JTextField();
    topPanel.add(txtNume);
    topPanel.add(new JLabel("Producator"));
    txtProducator=new JTextField();
    topPanel.add(txtProducator);
    topPanel.add(new JLabel("Pret"));
    txtPret=new JTextField();
    topPanel.add(txtPret);
    topPanel.add(new JLabel("Stoc"));
    txtStoc=new JTextField();
    topPanel.add(txtStoc);
    topPanel.add(new JLabel("Categorie ID"));
    txtCategorie=new JTextField();
    topPanel.add(txtCategorie);
    JButton btnAdauga=new JButton("Adauga");
    JButton btnModifica=new JButton("Modifica");
    JButton btnSterge=new JButton("Sterge");
    topPanel.add(btnAdauga);
    topPanel.add(btnModifica);
    topPanel.add(btnSterge);
    add(topPanel,BorderLayout.NORTH);
    
    JPanel searchPanel=new JPanel();
    txtCautare=new JTextField(20);
    JButton btnCauta=new JButton("Cauta");
    searchPanel.add(new JLabel("Cauta"));
    searchPanel.add(txtCautare);
    searchPanel.add(btnCauta);
    add(searchPanel,BorderLayout.SOUTH);
    
    model=new DefaultTableModel();
    model.setColumnIdentifiers(new String[]{
        "ID","Nume","Producator","Pret","Stoc","Categorie"
    });
    table=new JTable(model);
    table.getSelectionModel().addListSelectionListener(e->{
       int row=table.getSelectedRow();
       if(row>=0){
           txtNume.setText(model.getValueAt(row, 1).toString());
           txtProducator.setText(model.getValueAt(row, 2).toString());
           txtPret.setText(model.getValueAt(row, 3).toString());
           txtStoc.setText(model.getValueAt(row, 4).toString());
           txtCategorie.setText(model.getValueAt(row, 5).toString());
       }
    });
    JScrollPane scroll=new JScrollPane(table);
    add(scroll,BorderLayout.CENTER);
    
    btnAdauga.addActionListener(e-> adaugaPiesa());
    btnModifica.addActionListener(e->modificaPiesa());
    btnSterge.addActionListener(e->stergePiesa());
    btnCauta.addActionListener(e->cautaPiese());
    txtCautare.addKeyListener(new java.awt.event.KeyAdapter() {
    @Override
    public void keyReleased(java.awt.event.KeyEvent evt) {
        cautaPiese();
    }
});
    incarcaDate();
}
private void incarcaDate(){
    model.setRowCount(0);
    List<PiesaAuto> lista=dao.getAllPiese();
    for(PiesaAuto p: lista){
        model.addRow(new Object[]{
            p.getId(),p.getNume(),p.getProducator(),p.getPret(),p.getStoc(),p.getCategorieId()
        });
    }
}
private void adaugaPiesa(){
    PiesaAuto p=new PiesaAuto();
    p.setNume(txtNume.getText());
    p.setProducator(txtProducator.getText());
    p.setPret(Double.parseDouble(txtPret.getText()));
    p.setStoc(Integer.parseInt(txtStoc.getText()));
    p.setCategorieId(Integer.parseInt(txtCategorie.getText()));
    dao.adaugaPiesa(p);
    incarcaDate();
    txtNume.setText("");
    txtProducator.setText("");
    txtPret.setText("");
    txtStoc.setText("");
    txtCategorie.setText("");
    txtNume.requestFocus();
}
private void stergePiesa(){
    int row=table.getSelectedRow();
    if(row==-1){
        JOptionPane.showMessageDialog(this, "Selecteaza o piesa!");
        return;
    }
    int id=(int)model.getValueAt(row, 0);
    dao.stergePiesa(id);
    incarcaDate();
}
private void modificaPiesa(){
    int row=table.getSelectedRow();
    if(row==-1){
        JOptionPane.showMessageDialog(this, "Selecteaza o piesa!");
        return;
    }
    int id=(int)model.getValueAt(row, 0);
    PiesaAuto p=new PiesaAuto();
    p.setId(id);
    p.setNume(txtNume.getText());
    p.setProducator(txtProducator.getText());
    p.setPret(Double.parseDouble(txtPret.getText()));
    p.setStoc(Integer.parseInt(txtStoc.getText()));
    p.setCategorieId(Integer.parseInt(txtCategorie.getText()));
    dao.modificaPiesa(p);
    incarcaDate();
    JOptionPane.showMessageDialog(this,"Piesa modificata!");
}
private void cautaPiese(){
    String text=txtCautare.getText().trim();
    if(text.isEmpty()){
        incarcaDate();
        return;
    }
    model.setRowCount(0);
    List<PiesaAuto> lista=dao.cautaPiese(text);
    for(PiesaAuto p: lista){
        model.addRow(new Object[]{
            p.getId(),p.getNume(),p.getProducator(),p.getPret(),p.getStoc(),p.getCategorieId()
        });
    }
}

}
