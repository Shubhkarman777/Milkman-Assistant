package billCollection;
import java.io.File;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.ResourceBundle;

import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;

import GenerateBill.SST_SMS;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import milkMan.MySQLConnection;

public class BillColllectionViewController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private ComboBox<String> cmonth;

    @FXML
    private TextField txtyear;

    @FXML
    private ComboBox<String> cmob;

    @FXML
    private TextField txtBill;

    @FXML
    void findTot(ActionEvent event) throws SQLException {
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
    	
    	if (cmonth.getValue() == null) { 
        	doAlert("Fill select Month plz...");
        	cmonth.requestFocus();
        	return;
        }
    	
    	if (cmob.getValue() == null) { 
        	doAlert("Fill select Mobile no. plz...");
        	cmob.requestFocus();
        	return;
        }
    	
    	pst=con.prepareStatement("select cbill, bbill from billhistory where mobile = ? and monthh = ? and yearr = ? and sstatus = ?");
    	pst.setString(1, cmob.getSelectionModel().getSelectedItem());
    	String s = cmonth.getSelectionModel().getSelectedItem();
    	int m=0;
    	if(s == "January")
    		m = 1;
    	else if(s == "Feburary")
    		m = 2;
    	else if(s == "March")
    		m = 3;
    	else if(s == "April")
    		m = 4;
    	else if(s == "May")
    		m = 5;
    	else if(s == "June")
    		m = 6;
    	else if(s == "July")
    		m = 7;
    	else if(s == "August")
    		m = 8;
    	else if(s == "September")
    		m = 9;
    	else if(s == "October")
    		m = 10;
    	else if(s == "November")
    		m = 11;
    	else if(s == "December")
    		m = 12;
    	pst.setInt(2, m);
    	pst.setInt(3, Integer.parseInt(txtyear.getText()));
    	pst.setInt(4, 0);
    	ResultSet rs=  pst.executeQuery();
    	boolean jasus = false;
    	float c=0, b=0;
    	while(rs.next())
    	{
    		jasus = true;
    		c = rs.getFloat(1);
    		b = rs.getFloat(2);	
    	}
    	if(jasus == true)
    	{
    		txtBill.setText(String.valueOf((b + c)));
    	}
    	else
    	{
    		Alert al = new Alert(AlertType.INFORMATION);
	    	al.setTitle("Title");
	    	al.setContentText("The bill of this month doesn't exist!!");
			al.show();
    	}
    }

    @FXML
    void updateStatus(ActionEvent event) throws SQLException {
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
    	
    	if (cmonth.getValue() == null) { 
        	doAlert("Select Month plz...");
        	cmonth.requestFocus();
        	return;
        }
    	
    	if (cmob.getValue() == null) { 
        	doAlert("Select Mobile no. plz...");
        	cmob.requestFocus();
        	return;
        }
    	
    	if (txtBill.getText().equals("")) { 
        	doAlert("Find total amount first..");
        	txtBill.requestFocus();
        	return;
        }
    	
    	pst=con.prepareStatement("update billhistory set sstatus = ? where mobile = ? and monthh = ? and yearr = ?");
    	pst.setInt(1, 1);
    	pst.setString(2, cmob.getSelectionModel().getSelectedItem());
    	String s = cmonth.getSelectionModel().getSelectedItem();
    	int m=0;
    	if(s == "January")
    		m = 1;
    	else if(s == "Feburary")
    		m = 2;
    	else if(s == "March")
    		m = 3;
    	else if(s == "April")
    		m = 4;
    	else if(s == "May")
    		m = 5;
    	else if(s == "June")
    		m = 6;
    	else if(s == "July")
    		m = 7;
    	else if(s == "August")
    		m = 8;
    	else if(s == "September")
    		m = 9;
    	else if(s == "October")
    		m = 10;
    	else if(s == "November")
    		m = 11;
    	else if(s == "December")
    		m = 12;
    	pst.setInt(3, m);
    	pst.setInt(4, Integer.parseInt(txtyear.getText()));
    	pst.executeUpdate();
    	Alert al = new Alert(AlertType.INFORMATION);
    	al.setTitle("Title");
		al.setContentText("Status Updated Successfully!!");
		al.show();
        String msg = "Your Bill for month: " + s + " has been received!";
    	
    	String resp=SST_SMS.bceSunSoftSend(cmob.getSelectionModel().getSelectedItem(), msg);
    	System.out.println(resp);
    	
    	if(resp.indexOf("Exception")!=-1)
    		System.out.println("Check Internet Connection");
    	
    	else
    		if(resp.indexOf("successfully")!=-1)
        		System.out.println("Sent");
    		else
    			System.out.println( "Invalid Number");
    }

    void dofillcmonth()
    {
    	cmonth.getItems().add("January");
    	cmonth.getItems().add("Feburary");
    	cmonth.getItems().add("March");
    	cmonth.getItems().add("April");
    	cmonth.getItems().add("May");
    	cmonth.getItems().add("June");
    	cmonth.getItems().add("July");
    	cmonth.getItems().add("August");
    	cmonth.getItems().add("September");
    	cmonth.getItems().add("October");
    	cmonth.getItems().add("November");
    	cmonth.getItems().add("December");
    }
    
    ArrayList<String> addl;
    void dofillmob() throws SQLException
    {
    	addl = new ArrayList<String>();
    	pst = con.prepareStatement("select distinct mob from customer");
    	ResultSet rs = pst.executeQuery();
    	while(rs.next())
    	{
    		String r = rs.getString("mob");
    		addl.add(r);
    	}
    	cmob.getItems().addAll(addl);
    }
    
    void dofillyear()
    {
    	Calendar now = Calendar.getInstance();
    	txtyear.setText(String.valueOf(now.get(Calendar.YEAR)));
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
        assert cmonth != null : "fx:id=\"cmonth\" was not injected: check your FXML file 'BillColllectionView.fxml'.";
        assert txtyear != null : "fx:id=\"txtyear\" was not injected: check your FXML file 'BillColllectionView.fxml'.";
        assert cmob != null : "fx:id=\"cmob\" was not injected: check your FXML file 'BillColllectionView.fxml'.";
        assert txtBill != null : "fx:id=\"txtBill\" was not injected: check your FXML file 'BillColllectionView.fxml'.";
        
        con = MySQLConnection.doConnect();
        dofillcmonth();
        dofillyear();
        try {
			dofillmob();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }
}
