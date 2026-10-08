/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import model.PiesaAuto;
import util.DBConnection;
/**
 *
 * @author Asus
 */
public class PiesaDAO {
    public void adaugaPiesa(PiesaAuto piesa){
        String sql="INSERT INTO piese_auto"+"(nume,producator,pret,stoc,categorie_id)"+"VALUES(?,?,?,?,?)";
        try{
            Connection con=DBConnection.getConnection();
            PreparedStatement ps=con.prepareStatement(sql);
            ps.setString(1,piesa.getNume());
            ps.setString(2,piesa.getProducator());
            ps.setDouble(3,piesa.getPret());
            ps.setInt(4,piesa.getStoc());
            ps.setInt(5,piesa.getCategorieId());
            ps.executeUpdate();
        }catch(Exception e){
            e.printStackTrace();
        }
    }
    public List<PiesaAuto>getAllPiese(){
        List<PiesaAuto> lista=new ArrayList();
        String sql="SELECT*FROM piese_auto";
        try{
            Connection con=DBConnection.getConnection();
            Statement st=con.createStatement();
            ResultSet rs=st.executeQuery(sql);
            while(rs.next()){
                PiesaAuto p=new PiesaAuto();
                p.setId(rs.getInt("id"));
                p.setNume(rs.getString("nume"));
                p.setProducator(rs.getString("producator"));
                p.setPret(rs.getDouble("pret"));
                p.setStoc(rs.getInt("stoc"));
                p.setCategorieId(rs.getInt("categorie_id"));
                lista.add(p);
            }
        }catch(Exception e){
            e.printStackTrace();
        }
        return lista;
    }
    public void stergePiesa(int id){
        String sql="DELETE FROM piese_auto WHERE id=?";
        try{
            Connection con=DBConnection.getConnection();
            PreparedStatement ps=con.prepareStatement(sql);
            ps.setInt(1,id);
            ps.executeUpdate();
        }catch(Exception e){
            e.printStackTrace();
        }
    }
    public void modificaPiesa(PiesaAuto piesa){
        String sql="UPDATE piese_auto SET nume=?, producator=?, pret=?, stoc=?, categorie_id=? WHERE id=?";
        try{
            Connection con=DBConnection.getConnection();
            PreparedStatement ps=con.prepareStatement(sql);
            ps.setString(1,piesa.getNume());
            ps.setString(2, piesa.getProducator());
            ps.setDouble(3,piesa.getPret());
            ps.setInt(4,piesa.getStoc());
            ps.setInt(5,piesa.getCategorieId());
            ps.setInt(6, piesa.getId());
            ps.executeUpdate();
        }catch(Exception e){
            e.printStackTrace();
        }
    }
    public List<PiesaAuto> cautaPiese(String nume){
        List<PiesaAuto> lista=new ArrayList<>();
        String sql="SELECT*FROM piese_auto WHERE nume LIKE?";
        try{
            Connection con=DBConnection.getConnection();
            PreparedStatement ps=con.prepareStatement(sql);
            ps.setString(1, "%"+nume+"%");
            ResultSet rs=ps.executeQuery();
            while(rs.next()){
                PiesaAuto p=new PiesaAuto();
                p.setId(rs.getInt("id"));
                p.setNume(rs.getString("nume"));
                p.setProducator(rs.getString("producator"));
                p.setPret(rs.getDouble("pret"));
                p.setStoc(rs.getInt("stoc"));
                p.setCategorieId(rs.getInt("categorie_id"));
                lista.add(p);
            }
        }catch(Exception e){
            e.printStackTrace();
        }
        return lista;
    }
    public List<PiesaAuto>filtreazaDupaCategorie(int categorieId){
        List<PiesaAuto> lista=new ArrayList<>();      
        String sql="SELECT*FROM piese_auto"+" WHERE categorie_id=?";
        try{
            Connection con=DBConnection.getConnection();
            PreparedStatement ps=con.prepareStatement(sql);
            ps.setInt(1,categorieId);
            ResultSet rs=ps.executeQuery();
            while(rs.next()){
                PiesaAuto p=new PiesaAuto();
                p.setId(rs.getInt("Id"));
                 p.setNume(rs.getString("nume"));
                p.setProducator(rs.getString("producator"));
                p.setPret(rs.getDouble("pret"));
                p.setStoc(rs.getInt("stoc"));
                p.setCategorieId(rs.getInt("categorie_id"));
                lista.add(p);
            }
        }catch(Exception e){
            e.printStackTrace();
        }
        return lista;
    }
}
