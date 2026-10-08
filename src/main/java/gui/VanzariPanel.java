/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package gui;
import dao.PiesaDAO;
import dao.VanzareDAO;
import model.PiesaAuto;
import model.Vanzare;
import javax.swing.*;
import java.awt.*;
import java.sql.Date;
import java.util.List;
/**
 *
 * @author Asus
 */
public class VanzariPanel extends JPanel{
    private JComboBox<String> comboPiese;
    private JTextField txtCantitate;
    private JTextField txtPret;
    private JTextField txtTotal;
    private JTextField txtData;
    private JButton btnVinde;
    private List<PiesaAuto> listaPiese;
    private PiesaDAO piesaDAO=new PiesaDAO();
    private VanzareDAO vanzareDAO=new VanzareDAO();
    
    public VanzariPanel(){
        setLayout(new GridLayout(6,2,5,5));
        add(new JLabel("Piesa"));
        comboPiese=new JComboBox<>();
        add(comboPiese);
        add(new JLabel("Cantitate"));
        txtCantitate=new JTextField();
        add(txtCantitate);
        add(new JLabel("Pret bucata"));
        txtPret = new JTextField();
        txtPret.setEditable(false);
        add(txtPret);
        add(new JLabel("Total"));
        txtTotal = new JTextField();
        txtTotal.setEditable(false);
        add(txtTotal);
        add(new JLabel("Data vanzarii"));
        txtData = new JTextField();     
        txtData.setEditable(false);
        txtData.setText(java.time.LocalDate.now().toString());
        add(txtData);
        btnVinde=new JButton("Inregistreaza vanzare!");
        add(new JLabel());
        add(btnVinde);
        incarcaPiese();
        btnVinde.addActionListener(e->inregistreazaVanzare());
        comboPiese.addActionListener(e->calculeazaTotal());
        txtCantitate.addKeyListener(new java.awt.event.KeyAdapter() {
            @Override
            public void keyReleased(
                    java.awt.event.KeyEvent evt) {

                calculeazaTotal();

        }
    });
    }
    public void incarcaPiese(){
        listaPiese=piesaDAO.getAllPiese();
        comboPiese.removeAllItems();
        comboPiese.addItem("Selecteaza piesa");
        for(PiesaAuto p : listaPiese){
        comboPiese.addItem(p.getNume());
}
        
    }
    private void calculeazaTotal() {
        int index = comboPiese.getSelectedIndex();

if(index <= 0){
    txtPret.setText("");
    txtTotal.setText("");
    return;
}
        try {
        PiesaAuto piesa = listaPiese.get(index - 1);
        double pret =piesa.getPret();
        txtPret.setText( String.valueOf(pret));
        int cantitate = 0;
        if(!txtCantitate.getText().isEmpty()) {
            cantitate =Integer.parseInt(txtCantitate.getText());
        }
        double total =pret * cantitate;
        txtTotal.setText(String.format("%.2f", total));
    } catch(Exception e) {
        txtTotal.setText("");
    }
}
    private void inregistreazaVanzare(){
        int index=comboPiese.getSelectedIndex();
        if(index<=0){
            return;
        }
        PiesaAuto piesa=listaPiese.get(index-1);
        int cantitate=Integer.parseInt(txtCantitate.getText());
        if(cantitate>piesa.getStoc()){
            JOptionPane.showMessageDialog(this, "Stoc insuficient!");
            return;
        }
        Vanzare vanzare=new Vanzare();
        vanzare.setPiesaId(piesa.getId());
        vanzare.setCantitate(cantitate);
        vanzare.setDataVanzare(new Date(System.currentTimeMillis()));
        vanzareDAO.adaugaVanzare(vanzare);
        vanzareDAO.actualizeazaStoc(piesa.getId(), cantitate);
        JOptionPane.showMessageDialog(this, "Vanzare inregistrata!");
        txtCantitate.setText("");
        txtTotal.setText("");
        comboPiese.setSelectedIndex(0);
        txtPret.setText("");
        incarcaPiese();
        
    }
}
