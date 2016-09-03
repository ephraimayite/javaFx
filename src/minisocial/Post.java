/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package minisocial;

/**
 *
 * @author Ephraim
 */
public class Post {

    public int getFid() {
        return fid;
    }

    public void setFid(int fid) {
        this.fid = fid;
    }
    private   User user ;
    
  private  String description;
    private byte[] image;
 
   private int id;
   private String country;
   private int fid;

    public void setCountry(String country) {
        this.country = country;
    }

    public String getCountry() {
        return country;
    }

  
 
     public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
    

   public void setUser(User user){
       this.user = user;
   
   }
     public User getUser(){
   return this.user;
   }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public byte[] getImage() {
        return image;
    }

    public void setImage(byte[] image) {
        this.image = image;
    }
   public Post(){}
    
}
