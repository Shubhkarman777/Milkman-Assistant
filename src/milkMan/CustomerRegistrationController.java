package milkMan;

import java.io.File;
import java.io.FileNotFoundException;
import java.net.URL;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.Optional;
import java.util.ResourceBundle;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;

public class CustomerRegistrationController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private TextField txtmob;

    @FXML
    private TextField txtname;

    @FXML
    private TextField txtadd;

    @FXML
    private TextField txtarea;

    @FXML
    private TextField txtcity;

    @FXML
    private TextField txtqtyc;

    @FXML
    private TextField txtqtyb;

    @FXML
    private TextField txtpricec;

    @FXML
    private TextField txtpriceb;

    @FXML
    private DatePicker datetimepicker;

    @FXML
    void doclose(ActionEvent event) {
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
    	Alert confirm=new Alert(AlertType.CONFIRMATION);
    	confirm.setTitle("Closing the Window...");
    	confirm.setContentText("Are You sure?");
    	Optional<ButtonType> res= confirm.showAndWait();
    	if(res.get()==ButtonType.OK)
    			System.exit(1);
    }

    @FXML
    void dodelete(ActionEvent event) throws SQLException {
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
    	
    	if (txtmob.getText().equals("")) { 
        	doAlert("Fill Contact no. plz...");
        	txtmob.requestFocus();
        	return;
        }
    	Alert confirm=new Alert(AlertType.CONFIRMATION);
    	confirm.setTitle("Deleting the data...");
    	confirm.setContentText("Are You sure?");
    	Optional<ButtonType> res= confirm.showAndWait();
    	if(res.get() != ButtonType.OK)
    			System.exit(1);
    	else
    	{
	    	pst = con.prepareStatement("delete from customer where mob=?");
	    	pst.setString(1, txtmob.getText());
	    	int count=pst.executeUpdate();
	    	Alert al = new Alert(AlertType.INFORMATION);
	    	al.setTitle("Title");
	    	if(count != 0)
	    		al.setContentText("Data Deleted Successfully!!");
	    	else
	    		al.setContentText("Invalid Mobile No.!!");
			al.show();
    	}
    }

    @FXML
    void dosave(ActionEvent event) throws SQLException {
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
            if (txtmob.getText().equals("")) { 
            	doAlert("Fill Contact no. plz...");
            	txtmob.requestFocus();
            	return;
            }
            
            Pattern p = Pattern.compile("(0|91)?[7-9][0-9]{9}");
            Matcher m = p.matcher(txtmob.getText());
            if(!(m.find() && m.group().equals(txtmob.getText())))
            {
            	doAlert("Fill Valid Contact no. plz...");
            	txtmob.requestFocus();
            	return;
            }
        
            if (txtname.getText().equals("")) { 
            	doAlert("Fill name plz...");
            	txtname.requestFocus();
            	return;
            }
 
            if (txtadd.getText().equals("")) {
            	doAlert("Fill address plz...");
            	txtadd.requestFocus();
            	return;
            }

            if (txtarea.getText().equals("")) {
            	doAlert("Fill address plz...");
            	txtarea.requestFocus();
            	return;
            }

            if (txtcity.getText().equals("")) {
            	doAlert("Fill city plz...");
            	txtcity.requestFocus();
            	return;
            }
  
            if (datetimepicker.getValue() == null) { 
            	doAlert("Fill date plz...");
            	datetimepicker.requestFocus();
            	return;
            }
            
    	pst = con.prepareStatement("insert into customer values(?,?,?,?,?,?,?,?,?,?,?)");
    	pst.setString(1, txtmob.getText());
    	pst.setString(2, txtname.getText());
    	pst.setString(3, txtadd.getText());
    	pst.setString(4, txtarea.getText());
    	pst.setString(5, txtcity.getText());
    	pst.setFloat(6, Float.parseFloat(txtqtyc.getText()));
    	pst.setFloat(7, Float.parseFloat(txtpricec.getText()));
    	pst.setFloat(8, Float.parseFloat(txtqtyb.getText()));
    	pst.setFloat(9, Float.parseFloat(txtpriceb.getText()));
    	LocalDate date = datetimepicker.getValue();
    	java.sql.Date d = java.sql.Date.valueOf(date);
    	pst.setDate(10,d);
    	int status = 1;
    	pst.setInt(11, status);
    	pst.executeUpdate();
    	Alert al = new Alert(AlertType.INFORMATION);
    	al.setTitle("Title");
		al.setContentText("Data Saved Successfully!!");
		al.show();
    }

    @FXML
    void dosearch(ActionEvent event) throws Exception {
    	try{
    		File Clap = new File("button-3.wav");
    		Clip clip = AudioSystem.getClip();
    		clip.open(AudioSystem.getAudioInputStream(Clap));
    		clip.start();
    		Thread.sleep(clip.getMicrosecondLength()/1000);
    		}
    		catch(FileNotFoundException ex)
    		{
    			ex.printStackTrace();
    		}
    	
    	if (txtmob.getText().equals("")) { 
        	doAlert("Fill Contact no. plz...");
        	txtmob.requestFocus();
        	return;
        }
	    	pst = con.prepareStatement("select * from customer where mob=?");
	    	pst.setString(1, txtmob.getText());
	    	ResultSet rs=pst.executeQuery();
	    	Alert al = new Alert(AlertType.INFORMATION);
	    	al.setTitle("Title");
	    	boolean jasus = false;
	    	while(rs.next())
	    	{
	    		jasus = true;
	    		String cname = rs.getString("cname");
	    		String cdd = rs.getString("cdd");
	    		String carea = rs.getString("carea");
	    		String city = rs.getString("city");
	    		float qtyc = rs.getFloat("qtyc");
	    		float ratec = rs.getFloat("ratec");
	    		float qtyb = rs.getFloat("qtyb");
	    		float rateb = rs.getFloat("rateb");
	    		Date dos = rs.getDate("dos");
	    		LocalDate d = dos.toLocalDate();
	    		txtname.setText(cname);
	    		txtadd.setText(cdd);
	    		txtarea.setText(carea);
	    		txtcity.setText(city);
	    		txtqtyc.setText(String.valueOf(qtyc));
	    		txtpricec.setText(String.valueOf(ratec));
	    		txtqtyb.setText(String.valueOf(qtyb));
	    		txtpriceb.setText(String.valueOf(rateb));
	    		datetimepicker.setValue(d);
	    	}
	    	if(jasus == false)
	    	{
	    		al.setContentText("Invalid Mobile No.!!");
				al.show();
	    	}
    }

    @FXML
    void doupdate(ActionEvent event) throws SQLException {
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
    	if (txtmob.getText().equals("")) { 
        	doAlert("Fill Contact no. plz...");
        	txtmob.requestFocus();
        	return;
        }
    
        if (txtname.getText().equals("")) { 
        	doAlert("Fill name plz...");
        	txtname.requestFocus();
        	return;
        }

        if (txtadd.getText().equals("")) {
        	doAlert("Fill address plz...");
        	txtadd.requestFocus();
        	return;
        }

        if (txtarea.getText().equals("")) {
        	doAlert("Fill address plz...");
        	txtarea.requestFocus();
        	return;
        }

        if (txtcity.getText().equals("")) {
        	doAlert("Fill city plz...");
        	txtcity.requestFocus();
        	return;
        }

        if (datetimepicker.getValue().equals("")) { 
        	doAlert("Fill date plz...");
        	datetimepicker.requestFocus();
        	return;
        }

    	pst = con.prepareStatement("update customer set cname=?, cdd=?, carea=?, city=?, qtyc=?, ratec=?, qtyb=?, rateb=?, dos=?, sstatus=? where mob=?");
    	pst.setString(11, txtmob.getText());
    	pst.setString(1, txtname.getText());
    	pst.setString(2, txtadd.getText());
    	pst.setString(3, txtarea.getText());
    	pst.setString(4, txtcity.getText());
    	pst.setFloat(5, Float.parseFloat(txtqtyc.getText()));
    	pst.setFloat(6, Float.parseFloat(txtpricec.getText()));
    	pst.setFloat(7, Float.parseFloat(txtqtyb.getText()));
    	pst.setFloat(8, Float.parseFloat(txtpriceb.getText()));
    	LocalDate date = datetimepicker.getValue();
    	java.sql.Date d = java.sql.Date.valueOf(date);
    	pst.setDate(9,d);
    	int status = 1;
    	pst.setInt(10, status);
    	int count = pst.executeUpdate();
    	Alert al = new Alert(AlertType.INFORMATION);
    	al.setTitle("Title");
    	if(count != 0)
    		al.setContentText("Data Updated Successfully!!");
    	else
    		al.setContentText("Invalid Mobile No.!!");
		al.show();
    }
    
    void doAlert(String msg)
    {
    	Alert alert=new Alert(AlertType.INFORMATION);
    	alert.setTitle("Alert..");
    	alert.setContentText(msg);
    	alert.show();
    }

    Connection con;
    PreparedStatement pst;
    @FXML
    void initialize() {
        assert txtmob != null : "fx:id=\"txtmob\" was not injected: check your FXML file 'CustomerRegistration.fxml'.";
        assert txtname != null : "fx:id=\"txtname\" was not injected: check your FXML file 'CustomerRegistration.fxml'.";
        assert txtadd != null : "fx:id=\"txtadd\" was not injected: check your FXML file 'CustomerRegistration.fxml'.";
        assert txtarea != null : "fx:id=\"txtarea\" was not injected: check your FXML file 'CustomerRegistration.fxml'.";
        assert txtcity != null : "fx:id=\"txtcity\" was not injected: check your FXML file 'CustomerRegistration.fxml'.";
        assert txtqtyc != null : "fx:id=\"txtqtyc\" was not injected: check your FXML file 'CustomerRegistration.fxml'.";
        assert txtqtyb != null : "fx:id=\"txtqtyb\" was not injected: check your FXML file 'CustomerRegistration.fxml'.";
        assert txtpricec != null : "fx:id=\"txtpricec\" was not injected: check your FXML file 'CustomerRegistration.fxml'.";
        assert txtpriceb != null : "fx:id=\"txtpriceb\" was not injected: check your FXML file 'CustomerRegistration.fxml'.";
        assert datetimepicker != null : "fx:id=\"datetimepicker\" was not injected: check your FXML file 'CustomerRegistration.fxml'.";
        
        con = MySQLConnection.doConnect();
    }
}
