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
public class User {

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getUsername() {
        return username;
    }

    public int getAge() {
        return age;
    }

    public int getId() {
        return id;
    }

    

   
   private String firstName;
    private String lastName;
    private String username;
    private String password;
    public static int momentId;

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
   private int age;
  private int id;

  private Post post;
  
    public User(){
        setPost(new Post());
    }
  
 public static void triggerId(int ids){
     momentId = ids;
 }
public static int collectId(){
    
        return momentId;
}
    
    public void setPost(Post postData){
        this.post = postData;
//        post.setUser(this);
    
    }
   
    
    
 public Post getPost() {
        return post;
    }
}
