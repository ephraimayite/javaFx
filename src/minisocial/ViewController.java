/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package minisocial;

//import java.awt.Image;
import java.awt.image.BufferedImage;
import java.awt.image.RenderedImage;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;
import javafx.embed.swing.SwingFXUtils;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.image.PixelWriter;
import javafx.scene.image.WritableImage;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javax.imageio.ImageIO;
import org.hibernate.Query;
import static sun.security.krb5.Confounder.bytes;

/**
 *
 * @author Ephraim-THE-Anointed
 */
public class ViewController implements Initializable {
    @FXML
    ImageView imageView;
    @FXML
    Label countryView;
    @FXML
    TextArea descriptionView;
    @FXML
    Button changeButton;
    @FXML
    ComboBox countryChooser;
  private File file;
   String newCountry;
   String newDescription;
   byte[] newImage;
   private static  List<Post> post;
    @Override
    public void initialize(URL location, ResourceBundle resources) {
       // throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
     countryChooser.getItems().add("Togo");
       countryChooser.getItems().add("Benin");
        countryChooser.getItems().add("Burkina");
        countryChooser.getItems().add("Senegal");
        countryChooser.getItems().add("Ghana");
        countryChooser.getItems().add("Nigeria");
        try{
        show();}
    catch(IOException e){
    
    }
    }
    public void show() throws IOException{
        MainControl control = new MainControl();
        control.begin();
        System.out.println("ddeded");
        Query query = control.query("from Post where fid="+User.collectId());
        
         post=query.list();
       // System.out.print(post);
        String country;
        post.stream().forEach(t->System.out.println(t.getCountry()));
        
        countryView.setText(post.get(0).getCountry());
        newCountry = post.get(0).getCountry();
        countryChooser.setValue(post.get(0).getCountry());
        byte[] image = (byte[])post.get(0).getImage();
        Image images;
        
        BufferedImage img = ImageIO.read(new ByteArrayInputStream(image));
        //images =img.getScaledInstance(50, 50, 3);
         WritableImage wr = null;
        if (img != null) {
            wr = new WritableImage(img.getWidth(), img.getHeight());
            PixelWriter pw = wr.getPixelWriter();
            for (int x = 0; x < img.getWidth(); x++) {
                for (int y = 0; y < img.getHeight(); y++) {
                    pw.setArgb(x, y, img.getRGB(x, y));
                }
            }
        }
        imageView.setImage(wr);
        RenderedImage renderedImage = SwingFXUtils.fromFXImage(wr, null);
        file=new File("image.png");
                    ImageIO.write(
                            renderedImage, 
                            "png",
                            file);
                    if(file==null){System.err.print("file could not convert");}
        descriptionView.setText(post.get(0).getDescription());
        newDescription = post.get(0).getDescription();
       System.out.println( User.collectId()+" is the Id");
    }
    public void saveChanges(){
        MainControl control = new MainControl();
        control.begin();
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
        changeCountry();
        changeDescription();
        Post newpost= new Post();
        newpost.setCountry(newCountry);
        newpost.setDescription(newDescription);
        newpost.setImage(bFile);
        newpost.setFid(post.get(0).getFid());
        newpost.setId(post.get(0).getId());
        control.update(newpost);
        control.commit();
        control.closeSession();
        
   try{ 
       show();}
   catch(IOException e){ System.out.println("Could not show after update");}
    }
    
    public void changePicture(){
        Stage stage = (Stage) changeButton.getScene().getWindow();
        //if(!description.getText().isEmpty()){
        
      //  }
        FileChooser fileChooser = new FileChooser();
        changeButton.setOnAction(p->{
          file = fileChooser.showOpenDialog(stage);
                    if (file == null) {
                        System.out.println("no file found");
                    } else{
                    System.out.println("File found my friend");
                    System.out.println(file.getPath());
                    }
        });
    
    }
    
    public void changeCountry(){
    newCountry =(String) countryChooser.getValue();
   // newCountry
    }
    public void changeDescription(){
    newDescription=descriptionView.getText();
    }
}
