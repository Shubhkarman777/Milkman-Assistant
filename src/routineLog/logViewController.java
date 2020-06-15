package routineLog;

import java.io.File;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.ResourceBundle;

import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;

import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.CheckBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.ListView;
import javafx.scene.control.SelectionMode;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.input.MouseEvent;
import milkMan.MySQLConnection;

public class logViewController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private ListView<String> list1;

    @FXML
    private ListView<String> list2;

    @FXML
    private TextField cq1;

    @FXML
    private TextField bq1;

    @FXML
    private TextField mob;

    @FXML
    private TextField cq2;

    @FXML
    private TextField bq2;

    @FXML
    private CheckBox skip;

    @FXML
    private DatePicker dtp;

    @FXML
    void dodelete(ActionEvent event) {
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
    	ObservableList<String> all = list1.getSelectionModel().getSelectedItems();
    	ObservableList<Integer> indc = list1.getSelectionModel().getSelectedIndices();
    	ArrayList<String> all1 = new ArrayList<String>();
    	all1.addAll(all);
    	for(Integer i : indc)
    	{
    	    list2.getSelectionModel().select(i);
    	}
    	ObservableList<String> all2 = list2.getSelectionModel().getSelectedItems();
    	ArrayList<String> all3 = new ArrayList<String>();
    	all3.addAll(all2);
    	list1.getItems().clear();
    	list1.getItems().addAll(all1);
    	list2.getItems().clear();
    	list2.getItems().addAll(all3);
    	cq1.setText(String.valueOf(0));
    	bq1.setText(String.valueOf(0));
    	mob.setText("");
    }
    
    @FXML
    void dofill(MouseEvent event) throws SQLException {
    	list2.getSelectionModel().clearSelection();
    	ObservableList<Integer> indc = list1.getSelectionModel().getSelectedIndices();
    	for(Integer i : indc)
    	{
    	    list2.getSelectionModel().select(i);
    	}
    	String str = list1.getSelectionModel().getSelectedItem();
    	String str1 = list2.getSelectionModel().getSelectedItem();
    	pst = con.prepareStatement("select * from customer where cname=? and cdd=?");
    	pst.setString(1, str);
    	pst.setString(2, str1);
    	ResultSet rs = pst.executeQuery();
    	while(rs.next())
    	{
    		float r1 = rs.getFloat("qtyc");
    		float r2 = rs.getFloat("qtyb");
    		String s = rs.getString("mob");
    		cq1.setText(String.valueOf(r1));
    		bq1.setText(String.valueOf(r2));
    		mob.setText(s);
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
    	if (dtp.getValue() == null) { 
        	doAlert("Fill date plz...");
        	dtp.requestFocus();
        	return;
        }
    	if(skip.isSelected() == true)
    	{
        	pst = con.prepareStatement("insert into dailylogin values(?, ?, ?, ?, ?, ?)");
        	pst.setString(1, mob.getText());
        	float c = Float.parseFloat(cq1.getText());
        	if(c != 0)
        		c = c * (-1);
        	pst.setString(2, String.valueOf(c));
        	float d = Float.parseFloat(bq1.getText());
        	if(d != 0)
        		d = d * (-1);
        	pst.setString(3, String.valueOf(d));
        	pst.setInt(4, dtp.getValue().getMonthValue());
        	pst.setInt(5, dtp.getValue().getYear());
        	pst.setInt(6, dtp.getValue().getDayOfMonth());
        	pst.executeUpdate();
        	Alert al = new Alert(AlertType.INFORMATION);
        	al.setTitle("Title");
            al.setContentText("Data Updated Successfully!!");
    		al.show();
    	}
    	else
    	{
    		pst = con.prepareStatement("insert into dailylogin values(?, ?, ?, ?, ?, ?)");
        	pst.setString(1, mob.getText());
        	float c = Float.parseFloat(cq2.getText());
        	pst.setString(2, String.valueOf(c));
        	float d = Float.parseFloat(bq2.getText());
        	pst.setString(3, String.valueOf(d));
        	pst.setInt(4, dtp.getValue().getMonthValue());
        	pst.setInt(5, dtp.getValue().getYear());
        	pst.setInt(6, dtp.getValue().getDayOfMonth());
        	pst.executeUpdate();
        	Alert al = new Alert(AlertType.INFORMATION);
        	al.setTitle("Title");
            al.setContentText("Data Updated Successfully!!");
    		al.show();
    	}
    }
    ArrayList<String> names;
    void dofillList1() throws SQLException
    {
    	names = new ArrayList<String>();
    	pst = con.prepareStatement("select distinct cname from customer");
    	ResultSet rs = pst.executeQuery();
    	while(rs.next())
    	{
    		String r = rs.getString("cname");
    		names.add(r);
    	}
    	list1.getItems().addAll(names);
    }
    
    ArrayList<String> addl;
    void dofillList2() throws SQLException
    {
    	addl = new ArrayList<String>();
    	pst = con.prepareStatement("select distinct cdd from customer");
    	ResultSet rs = pst.executeQuery();
    	while(rs.next())
    	{
    		String r = rs.getString("cdd");
    		addl.add(r);
    	}
    	list2.getItems().addAll(addl);
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
        assert list1 != null : "fx:id=\"list1\" was not injected: check your FXML file 'logView.fxml'.";
        assert list2 != null : "fx:id=\"list2\" was not injected: check your FXML file 'logView.fxml'.";
        assert cq1 != null : "fx:id=\"cq1\" was not injected: check your FXML file 'logView.fxml'.";
        assert bq1 != null : "fx:id=\"bq1\" was not injected: check your FXML file 'logView.fxml'.";
        assert mob != null : "fx:id=\"mob\" was not injected: check your FXML file 'logView.fxml'.";
        assert cq2 != null : "fx:id=\"cq2\" was not injected: check your FXML file 'logView.fxml'.";
        assert bq2 != null : "fx:id=\"bq2\" was not injected: check your FXML file 'logView.fxml'.";
        assert skip != null : "fx:id=\"skip\" was not injected: check your FXML file 'logView.fxml'.";
        assert dtp != null : "fx:id=\"dtp\" was not injected: check your FXML file 'logView.fxml'.";


        list1.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
        list2.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
        con = MySQLConnection.doConnect();
        try {
			dofillList1();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        try {
			dofillList2();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }
}
