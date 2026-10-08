/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
import java.sql.Connection;
import util.DBConnection;
/**
 *
 * @author Asus
 */
public class TestJDBC {
    public static void main(String[]args){
        try{
            Connection con=DBConnection.getConnection();
            System.out.println("Conectare reusita!");
            con.close();
        }
        catch(Exception e){
            e.printStackTrace();
        }
    }
}
