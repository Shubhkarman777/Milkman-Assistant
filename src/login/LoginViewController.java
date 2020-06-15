package login;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.util.ResourceBundle;

import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;

import GenerateBill.SST_SMS;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.stage.Stage;
import milkMan.MySQLConnection;

public class LoginViewController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private TextField key;

    @FXML
    void proceed(ActionEvent event) throws IOException {
    	try{
    		File Clap = new File("button-3.wav");
    		Clip clip = AudioSystem.getClip();
    		clip.open(AudioSystem.getAudioInputStream(Clap));
    		clip.start();
    		Thread.sleep(clip.getMicrosecondLength()/1000);
    		}
    		catch(Exception ex)
    		{
    			ex.printStackTrace();
    		}
    	if(key.getText().equals("Shubhkarman"))
    	{
    		Parent root=FXMLLoader.load(getClass().getClassLoader().getResource("Dashboard/MilkmanDashboard.fxml")); 
			Scene scene = new Scene(root);
			
			Stage stage=new Stage();

			stage.setScene(scene);
			
			stage.show();

			/*to hide the opened window*/
		    Scene scene1=(Scene)key.getScene();
			scene1.getWindow().hide();
    	}
    	else
    	{
    		Alert al = new Alert(AlertType.INFORMATION);
	    	al.setTitle("Title");
	    	al.setContentText("Wrong Key!!");
			al.show();
    	}
    }

    @FXML
    void recover(ActionEvent event) {
    	try{
    		File Clap = new File("button-3.wav");
    		Clip clip = AudioSystem.getClip();
    		clip.open(AudioSystem.getAudioInputStream(Clap));
    		clip.start();
    		Thread.sleep(clip.getMicrosecondLength()/1000);
    		}
    		catch(Exception ex)
    		{
    			ex.printStackTrace();
    		}
    	String msg = "Your Key is: Shubhkarman!!" ;
    	
    	String resp=SST_SMS.bceSunSoftSend("9041375155", msg);
    	System.out.println(resp);
    	
    	if(resp.indexOf("Exception")!=-1)
    		System.out.println("Check Internet Connection");
    	
    	else
    		if(resp.indexOf("successfully")!=-1)
        	{
    			Alert al = new Alert(AlertType.INFORMATION);
	  	    	al.setTitle("Title");
	   	    	al.setContentText("Message has been sent to you!!");
	   			al.show();
        	}
    		else
    			System.out.println( "Invalid Number");
    }
    
    Connection con;
    PreparedStatement pst;

    @FXML
    void initialize() {
        assert key != null : "fx:id=\"key\" was not injected: check your FXML file 'LoginView.fxml'.";

        Platform.runLater( () -> key.requestFocus() );
        con = MySQLConnection.doConnect();
    }
}
