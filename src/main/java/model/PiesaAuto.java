/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

/**
 *
 * @author Asus
 */
public class PiesaAuto {
    private int id;
    private String nume;
    private String producator;
    private double pret;
    private int stoc;
    private int categorieId;
    
    public PiesaAuto(){
    }
    public PiesaAuto(int id,String nume,String producator,double pret,int stoc,int categorieId){
        this.id=id;
        this.nume=nume;
        this.producator=producator;
        this.pret=pret;
        this.stoc=stoc;
        this.categorieId=categorieId;
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
    public String getProducator(){
        return producator;
    }
    public void setProducator(String producator){
        this.producator=producator;
    }
    public double getPret(){
        return pret;
    }
    public void setPret(double pret){
        this.pret=pret;
    }
    public int getStoc(){
        return stoc;
    }
    public void setStoc(int stoc){
        this.stoc=stoc;
    }
    public int getCategorieId(){
        return categorieId;
    }
    public void setCategorieId(int categorieId){
        this.categorieId=categorieId;
    }
}
