/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;
import java.sql.Date;

public class Vanzare {
   private int id;
   private int piesaId;
   private int cantitate;
   private Date dataVanzare;
   
   public Vanzare(){
   }
   public Vanzare(int id,int piesaId,int cantitate,Date dataVanzare){
       this.id=id;
       this.piesaId=piesaId;
       this.cantitate=cantitate;
       this.dataVanzare=dataVanzare;
   }
   public int getId(){
       return id;
   }
   public void setId(int id){
       this.id=id;
   }
   public int getPiesaId(){
       return piesaId;
   }
   public void setPiesaId(int piesaId){
       this.piesaId=piesaId;
   }
   public int getCantitate(){
       return cantitate;
   }
   public void setCantitate(int cantitate){
       this.cantitate=cantitate;
   }
   public Date getDataVanzare(){
       return dataVanzare;
   }
   public void setDataVanzare(Date dataVanzare){
       this.dataVanzare=dataVanzare;
   }
}
