/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package minisocial;

import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import org.hibernate.Query;

/**
 *
 * @author Ephraim
 */
public class HomeController implements Initializable{
    
  @FXML
  private TextField username2;
   @FXML
  private PasswordField password2;
   @FXML
   private Button Login;
   @FXML
   private Button Register;
   @FXML
    private Label ErrorMessage1;
   @FXML
    private Label ErrorMessage2;
   @FXML
    private Label ErrorMessage3;
   String userText;
   String passText;
    MainControl control;
     MainControl control2;
     Scene scene;
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        System.out.println("hello");//To change body of generated methods, choose Tools | Templates.
    }
   
    
     public void Login(){
       try{ 
         userText= username2.getText();   
       }
       catch(Exception e){
        ErrorMessage1.setText("Please enter your username");
       }
       try{ 
         passText= password2.getText();   
       }
       catch(Exception e){
        ErrorMessage2.setText("Please enter your password");
       }
       if(!userText.isEmpty()&&!passText.isEmpty()){
       control =new MainControl();
       control.begin();
       Query query;
       query = control.query("from User where username='"+userText+"' AND "+"password='"+passText+"'");
       System.out.println("hey hey hey");
       
       System.out.println(query.list());
       if(query.list().isEmpty()){System.out.println("no user validated");
       ErrorMessage3.setText("Invalid login, user not found");
       }else{
       ErrorMessage3.setText("User found");
       List<User> users = query.list();
       int myId =users.get(0).getId();
       User.triggerId(myId);
        Stage stage = new Stage();
       stage.setTitle("Shop Management");
       Pane myPane = null;
       boolean exist=false;
       AnchorPane anchor=null;
       try{
           Post poster = new Post();
           Query secondQuery = control.query("from Post where fid="+User.collectId());
           if(!secondQuery.list().isEmpty()){
           System.out.println("Post available");    
       myPane = FXMLLoader.load(getClass().getResource("View.fxml"));}
           else{
                  anchor = FXMLLoader.load(getClass().getResource("PostAdd.fxml"));     
           System.out.println("Post not available");
           }
               }
       catch(IOException e){}
       
         if(myPane!=null){ scene = new Scene(myPane);}else{
             
             scene = new Scene(anchor);
         }
       stage.setScene(scene);
 Stage currentstage = (Stage) username2.getScene().getWindow();
       currentstage.close();

       stage.show();
       
       }
       

       List<User> users = query.list();
       users.stream().forEach((user) -> {
           System.out.println("User: "+user.getFirstName());
       });
       }else{
       System.out.println("empty ph");
       }
     }
     
    // public void Register(){}
}
