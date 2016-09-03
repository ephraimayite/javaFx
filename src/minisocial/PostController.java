/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package minisocial;

import java.io.File;
import java.io.FileInputStream;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

/**
 *
 * @author Ephraim
 */
public class PostController implements Initializable{
    
    @FXML
    TextArea description;
    @FXML
    ComboBox residence;
    @FXML
    Button chooser;
    @FXML
    Button  addPost;
    @FXML
    Label confirmMessage;
    @FXML
    Label descriptionError;
    @FXML
    Label pictureError;
    @FXML
    Label countryError;
    String descriptionText;
    String  residenceText;
    File file;
    Post mypost;
   
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        //To change body of generated methods, choose Tools | Templates.
        residence.getItems().add("Togo");
        residence.getItems().add("Benin");
        residence.getItems().add("Burkina");
        residence.getItems().add("Senegal");
        residence.getItems().add("Ghana");
        residence.getItems().add("Nigeria");
    }
    
    public void insertPicture(){
        Stage stage = (Stage) addPost.getScene().getWindow();
        //if(!description.getText().isEmpty()){
        
      //  }
        FileChooser fileChooser = new FileChooser();
        chooser.setOnAction(p->{
          file = fileChooser.showOpenDialog(stage);
                    if (file == null) {
                        System.out.println("no file found");
                    } else{
                    System.out.println("File found my friend");
                    System.out.println(file.getPath());
                    }
        });
        
    
    
    }
    
    public void addArticle(){
        
        try{
        descriptionText = description.getText();
        }catch(Exception e){
        System.out.println("Description not entered");
        descriptionError.setText("Description not entered");
        }
        try{
        residenceText = (String)residence.getValue();
        }catch(Exception e){
        System.out.println("Description not entered");
        countryError.setText("Description not entered");
        }
        
        if(residenceText==null){
        System.out.println("Did not selected file");
      countryError.setText("Did not select country");
        }
         if(descriptionText.isEmpty()){
        System.out.println("Did not selected file");
      descriptionError.setText("Did not enter text");
        }
          if(file==null){
        System.out.println("Did not selected file");
      pictureError.setText("Did not selected file");
        }
        
        if(!descriptionText.isEmpty()&&file.exists()&&!residenceText.isEmpty()){
            byte[] bFile = new byte[(int) file.length()];
            FileInputStream fileInputStream;
        try {
            //convert file into array of bytes
	    fileInputStream = new FileInputStream(file);
	    fileInputStream.read(bFile);
	    fileInputStream.close();
            for (int i = 0; i < bFile.length; i++) {
	       	System.out.print((char)bFile[i]);
            }
        } catch(Exception e){
        
        }
            MainControl<Post> control= new MainControl();
        //    User user = new User();
        //    user.setAge(2);
         //   user.setFirstName("qqq");
         //   user.setLastName("eee");
        //    user.setPassword("dddd");
        //    user.setUsername("dd");
            
            control.begin();
            mypost = new Post();
           mypost.setCountry(residenceText);
           mypost.setDescription(descriptionText);
           mypost.setImage(bFile);
           mypost.setFid(User.collectId());
           mypost.setId(3);
           boolean go=true;
         //  mypost.setUser(user);
           try{
            control.save(mypost);}
           catch(Exception e){
                confirmMessage.setText("Duplicate Entry");
           System.out.println("Duplicate entry");
           go=false;
           }
           control.commit();
           control.closeSession();
          if(go){  confirmMessage.setText("Information added");}
            
        
        }
        
        
    
    
    
    
    
    }
    
}
