/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import model.Categorie;
import util.DBConnection;
/**
 *
 * @author Asus
 */
public class CategorieDAO {
    public void adaugaCategorie(Categorie categorie){
        String sql="INSERT INTO categorii(nume)VALUES(?)";
        try{
            Connection con=DBConnection.getConnection();
            PreparedStatement ps=con.prepareStatement(sql);
            ps.setString(1,categorie.getNume());
            ps.executeUpdate();
        }catch(Exception e){
            e.printStackTrace();
        }
    }

public List<Categorie> getAllCategorii(){
List<Categorie>lista=new ArrayList<>();
String sql="SELECT*FROM categorii";
try{
    Connection con=DBConnection.getConnection();
    Statement st=con.createStatement();
    ResultSet rs=st.executeQuery(sql);
    while(rs.next()){
        Categorie c=new Categorie();
        c.setId(rs.getInt("id"));
        c.setNume(rs.getString("nume"));
        lista.add(c);
    }
}catch(Exception e){
    e.printStackTrace();
}
return lista;
}
public void stergeCategorie(int id){
    String sql="DELETE FROM categorii WHERE id=?";
    try{
        Connection con=DBConnection.getConnection();
        PreparedStatement ps=con.prepareStatement(sql);
        ps.setInt(1,id);
        ps.executeUpdate();
    }catch(Exception e){
        e.printStackTrace();
    }
}
public void modificaCategorie(Categorie categorie){
    String sql="UPDATE categorii SET nume=? WHERE id=?";
    try{
        Connection con=DBConnection.getConnection();
        PreparedStatement ps=con.prepareStatement(sql);
        ps.setString(1,categorie.getNume());
        ps.setInt(2,categorie.getId());
        ps.executeUpdate();
    }catch(Exception e){
        e.printStackTrace();
    }
}
public boolean existaCategorie(String nume){
    String sql ="SELECT * FROM categorii WHERE LOWER(nume)=LOWER(?)";
    try{
        Connection con =DBConnection.getConnection();
        PreparedStatement ps =con.prepareStatement(sql);
        ps.setString(1, nume);
        ResultSet rs =ps.executeQuery();
        return rs.next();
    }catch(Exception e){
        e.printStackTrace();
    }
    return false;
}
}