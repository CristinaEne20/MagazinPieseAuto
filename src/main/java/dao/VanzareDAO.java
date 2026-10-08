/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import model.Vanzare;
import util.DBConnection;
/**
 *
 * @author Asus
 */
public class VanzareDAO {
    public void adaugaVanzare(Vanzare vanzare){
        String sql="INSERT INTO vanzari"+"(piesa_id,cantitate,data_vanzare)"+"VALUES(?,?,?)";
        try{
            Connection con=DBConnection.getConnection();
            PreparedStatement ps=con.prepareStatement(sql);
            ps.setInt(1,vanzare.getPiesaId());
            ps.setInt(2,vanzare.getCantitate());
            ps.setDate(3, vanzare.getDataVanzare());
            ps.executeUpdate();
        }catch(Exception e){
            e.printStackTrace();
        }
    }
    public void actualizeazaStoc(int piesaId,int cantitate){
        String sql="UPDATE piese_auto"+" SET stoc=stoc-?"+" WHERE id=?";
        try{
            Connection con=DBConnection.getConnection();
            PreparedStatement ps=con.prepareStatement(sql);
            ps.setInt(1,cantitate);
            ps.setInt(2, piesaId);
            ps.executeUpdate();
        }catch(Exception e){
            e.printStackTrace();
        }
    }
}
