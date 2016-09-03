/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package minisocial;

import java.awt.TextField;
import java.io.IOException;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 *
 * @author Ephraim
 */
public class MiniSocial extends Application {
    
    @Override
    public void start(Stage primaryStage)  {
        
       try{ 
           FXMLLoader load = new FXMLLoader(getClass().getResource("Login.fxml"));
           Parent root = load.load();
                    Scene newScene = new Scene(root,800,400);  
                    primaryStage.setScene(newScene);
                    primaryStage.setTitle("Social App");
                    primaryStage.show();
                     
                        
       }
       catch(IOException e){
       System.out.println(e.getMessage());
       }
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
      //  System.out.println(MyController.text());
    }
    
}
