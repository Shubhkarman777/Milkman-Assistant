package Dashboard;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.util.ResourceBundle;

import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import milkMan.MySQLConnection;

public class MilkmanDashboardController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;
    
    @FXML
    private Button btn;
    
    @FXML
    void AboutDeveloper(MouseEvent event) throws IOException {
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
			Parent root=FXMLLoader.load(getClass().getClassLoader().getResource("aboutDeveloper/AboutDeveloperView.fxml")); 
			Scene scene = new Scene(root);
			
			Stage stage=new Stage();

			stage.setScene(scene);
			
			stage.show();

			/*to hide the opened window
		    Scene scene1=(Scene)btn.getScene();
			scene1.getWindow().hide();*/
    }
    
    @FXML
    void logIn(ActionEvent event) throws IOException {
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
			Parent root=FXMLLoader.load(getClass().getClassLoader().getResource("login/LoginView.fxml")); 
			Scene scene = new Scene(root);
			
			Stage stage=new Stage();

			stage.setScene(scene);
			
			stage.show();

			/*to hide the opened window*/
		    Scene scene1=(Scene)btn.getScene();
			scene1.getWindow().hide();
    }
    
    @FXML
    void RoutineManager(MouseEvent event) throws IOException {
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
			Parent root=FXMLLoader.load(getClass().getClassLoader().getResource("routineManager/RoutineManagerView.fxml")); 
			Scene scene = new Scene(root);
			
			Stage stage=new Stage();

			stage.setScene(scene);
			
			stage.show();

			/*to hide the opened window
		    Scene scene1=(Scene)btnComboApp.getScene();
			scene1.getWindow().hide();*/
    }

    @FXML
    void billCollection(MouseEvent event) throws IOException {
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
			Parent root=FXMLLoader.load(getClass().getClassLoader().getResource("billCollection/BillColllectionView.fxml")); 
			Scene scene = new Scene(root);
			
			Stage stage=new Stage();

			stage.setScene(scene);
			
			stage.show();

			/*to hide the opened window
		    Scene scene1=(Scene)btnComboApp.getScene();
			scene1.getWindow().hide();*/
    }


    @FXML
    void customerFinder(MouseEvent event) throws IOException {
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
			Parent root=FXMLLoader.load(getClass().getClassLoader().getResource("customerFinder/customerfinderView.fxml")); 
			Scene scene = new Scene(root);
			
			Stage stage=new Stage();

			stage.setScene(scene);
			
			stage.show();

			/*to hide the opened window
		    Scene scene1=(Scene)btnComboApp.getScene();
			scene1.getWindow().hide();*/
    }
    
    @FXML
    void RoutineLog(MouseEvent event) throws IOException {
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
			Parent root=FXMLLoader.load(getClass().getClassLoader().getResource("billLog/billLogView.fxml")); 
			Scene scene = new Scene(root);
			
			Stage stage=new Stage();

			stage.setScene(scene);
			
			stage.show();

			/*to hide the opened window
		    Scene scene1=(Scene)btnComboApp.getScene();
			scene1.getWindow().hide();*/
    }

    @FXML
    void generateBill(MouseEvent event) throws IOException {
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
			Parent root=FXMLLoader.load(getClass().getClassLoader().getResource("GenerateBill/GenerateBillView.fxml")); 
			Scene scene = new Scene(root);
			
			Stage stage=new Stage();

			stage.setScene(scene);
			
			stage.show();

			/*to hide the opened window
		    Scene scene1=(Scene)btnComboApp.getScene();
			scene1.getWindow().hide();*/
    }

    @FXML
    void registration(MouseEvent event) throws IOException {
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
			Parent root=FXMLLoader.load(getClass().getClassLoader().getResource("milkMan/CustomerRegistration.fxml")); 
			Scene scene = new Scene(root);
			
			Stage stage=new Stage();

			stage.setScene(scene);
			
			stage.show();

			/*to hide the opened window
		    Scene scene1=(Scene)btnComboApp.getScene();
			scene1.getWindow().hide();*/
    }

    @FXML
    void routineLogin(MouseEvent event) throws IOException {
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
			Parent root=FXMLLoader.load(getClass().getClassLoader().getResource("routineLog/logView.fxml")); 
			Scene scene = new Scene(root);
			
			Stage stage=new Stage();

			stage.setScene(scene);
			
			stage.show();

			/*to hide the opened window
		    Scene scene1=(Scene)btnComboApp.getScene();
			scene1.getWindow().hide();*/
    }
    Connection con;
    PreparedStatement pst;
    @FXML
    void initialize() {
    	con = MySQLConnection.doConnect();
    }
}
