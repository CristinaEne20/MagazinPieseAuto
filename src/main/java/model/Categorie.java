/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

/**
 *
 * @author Asus
 */
public class Categorie {
    private int id;
    private String nume;
    
    public Categorie(){
}
    public Categorie(int id,String nume){
        this.id=id;
        this.nume=nume;
    }
    public int getId(){
        return id;
    }
    public void setId(int id){
        this.id=id;
    }
    public String getNume(){
        return nume;
    }
    public void setNume(String nume){
        this.nume=nume;
    }
}
