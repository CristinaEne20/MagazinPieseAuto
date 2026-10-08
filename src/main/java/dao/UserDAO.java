/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import model.user;
import util.DBConnection;
import java.util.List;
import java.util.ArrayList;
/**
 *
 * @author Asus
 */
public class UserDAO {
    public user login(String username, String password){
        String sql="SELECT*FROM users WHERE username=? AND password=?";
        try{
            Connection con=DBConnection.getConnection();
            PreparedStatement ps=con.prepareStatement(sql);
            ps.setString(1,username);
            ps.setString(2,password);
            ResultSet rs=ps.executeQuery();
            System.out.println("Interogare executata");
            if(rs.next()){
                System.out.println("Utilizator gasit");
                user user=new user();
                user.setId(rs.getInt("id"));
                user.setUsername(rs.getString("username"));
                user.setPassword(rs.getString("password"));
                user.setRole(rs.getString("role"));
                return user;
            }
        }catch(Exception e){
            e.printStackTrace();
        }
        return null;
    }
    public List<user>getAllUsers(){
        List<user> lista=new ArrayList<>();
        String sql="SELECT*FROM users";
        try{
            Connection con=DBConnection.getConnection();
            Statement st=con.createStatement();
            ResultSet rs=st.executeQuery(sql);
            while(rs.next()){
                user u=new user();
                u.setId(rs.getInt("id"));
                u.setUsername(rs.getString("username"));
                u.setPassword(rs.getString("password"));
                u.setRole(rs.getString("role"));
                lista.add(u);
            }
        }catch(Exception e){
            e.printStackTrace();
        }
        return lista;
    }
    public void adaugaUser(user user){
        String sql="INSERT INTO users(username,password,role) VALUES(?,?,?)";
        try{
            Connection con=DBConnection.getConnection();
            PreparedStatement ps=con.prepareStatement(sql);
            ps.setString(1,user.getUsername());
            ps.setString(2, user.getPassword());
            ps.setString(3,user.getRole());
            ps.executeUpdate();
        }catch(Exception e){
            e.printStackTrace();
        }
    }
}
