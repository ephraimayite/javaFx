/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package minisocial;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import org.hibernate.Session;
import org.hibernate.exception.ConstraintViolationException;

/**
 * FXML Controller class
 *
 * @author Ephraim
 */
public class MyController implements Initializable {
    @FXML
    private TextField username;
    @FXML
    private TextField password;
    @FXML
    private TextField firstname;
    @FXML
    private TextField lastname;
    @FXML
    private TextField age;
    @FXML
    private Label error1;
    @FXML
    private Label error2;
    @FXML
    private Label error3;
    @FXML
    private Label error4;
    @FXML
    private Label error5;
    @FXML
    private Label duplicate;
     private  MainControl control;
    private static String name;
     private   Session session;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        System.out.println("hello");
    }    
     public void okAction(ActionEvent event){
     System.out.println("This is happening"+username.getText());
     name = username.getText();
     }
     
     public void cancelAction(ActionEvent event){
     System.out.println("This is happening again"+password.getText());
     }
     public static String text(){
       return name;  
     }
     
     public void saveUser(ActionEvent event){
         duplicate.setText("");
         System.out.println("Saving");
         User newuser = new User();
         String firstname_field ="";
         String password_field="" ;
         String username_field="";
         String  lastname_field="";
         int age_field=-1;
try{firstname_field = firstname.getText();} catch(Exception e){}
try{lastname_field = lastname.getText();} catch(Exception e){}
   try{password_field = password.getText();} catch(Exception e){}
   try{username_field = username.getText();} catch(Exception e){}
         try{
         age_field = Integer.parseInt(age.getText());}
         catch(Exception e){
         System.out.println("Please enter a valid number");
         error5.setText("Please enter a valid number");
        
         }
        if(age_field!=-1 && !firstname_field.isEmpty()&& !password_field.isEmpty() && !lastname_field.isEmpty() && !username_field.isEmpty()  ) {
            error5.setText("");
            error4.setText("");
            error3.setText("");
            error2.setText("");
            error1.setText("");
            
            newuser.setAge(age_field);
        
         newuser.setFirstName(firstname_field);
         newuser.setLastName(lastname_field);
         newuser.setPassword(password_field);
         newuser.setUsername(username_field);
         control = new MainControl();
         if(control==null){
         System.out.println("dededed");
         }
         control.begin();
         try{
         control.save(newuser);
         
         } catch(ConstraintViolationException e){
             duplicate.setText("User already exists!!");
         }
         catch(Exception ex){
             System.out.println(ex.getMessage());
         }
         control.commit();
         control.closeSession();
        }else{
        
        if(firstname_field.isEmpty()){
        System.out.println("Please enter your firstname again");
        error1.setText("Please enter your firstname again");
        }else{
            error1.setText(" ");
        }
        if(lastname_field.isEmpty()){
        System.out.println("Please enter your lastname again");
        error2.setText("Please enter your lastname again");
        }else{
        error2.setText(" ");
        }
        if(username_field.isEmpty()){
        System.out.println("Please enter your username again");
        error3.setText("Please enter your username again");
        }else{
            error3.setText("");
        }
        
        if(password_field.isEmpty()){
        System.out.println("Please enter your password again");
        error4.setText("Please enter your password again");
        }else{
        error4.setText("");
        }
        if(age_field==-1){
        System.out.println("Please enter your age again");
        error5.setText("Please enter your age again");
        }else{
        error5.setText("");
        }
        }
         
         
        
        // newuser.setId(1);
         /**
          try{
    session = HibernateUtilities.getSessionFactory().openSession();}
        catch(Exception e){
        System.out.println(e.getMessage()+"is here");
        }
         session.beginTransaction();
         session.save(newuser);
         session.getTransaction().commit();
         session.close();*/

         
     
     }
     
     
   //  public void check
}
