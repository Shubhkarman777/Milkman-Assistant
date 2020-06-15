package GenerateBill;

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

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.ComboBox;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.input.MouseEvent;
import milkMan.MySQLConnection;

public class GenerateBillViewController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private ListView<String> list1;

    @FXML
    private ListView<String> list2;

    @FXML
    private ComboBox<String> month;

    @FXML
    private ComboBox<String> year;

    @FXML
    private ComboBox<String> date;

    @FXML
    private TextField crate;

    @FXML
    private TextField cqty;

    @FXML
    private TextField cbill;

    @FXML
    private TextField brate;

    @FXML
    private TextField bqty;

    @FXML
    private TextField bbill;

    @FXML
    private TextField total;

    @FXML
    void doFill(MouseEvent event) {
    	list2.getSelectionModel().clearSelection();
    	int indc = list1.getSelectionModel().getSelectedIndex();
    	list2.getSelectionModel().select(indc);
    }

    @FXML
    void findBill(ActionEvent event) throws SQLException {
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
    	if (list1.getSelectionModel().isEmpty()) { 
        	doAlert("Select Mobile No. plz...");
        	month.requestFocus();
        	return;
        }
    	
    	if (month.getValue() == null) { 
        	doAlert("Select Month plz...");
        	month.requestFocus();
        	return;
        }
    	if (year.getValue() == null) { 
        	doAlert("Select Year plz...");
        	year.requestFocus();
        	return;
        }
    	if (date.getValue() == null) { 
        	doAlert("Select Date plz...");
        	date.requestFocus();
        	return;
        }
    	pst = con.prepareStatement("select * from billHistory where mobile=? and monthh=? and yearr=?");
    	pst.setString(1, list1.getSelectionModel().getSelectedItem());
    	String s = month.getSelectionModel().getSelectedItem();
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
    	pst.setInt(2, m-1);
    	pst.setInt(3, Integer.parseInt(year.getSelectionModel().getSelectedItem()));
    	ResultSet rs = pst.executeQuery();
    	boolean jasus = false;
    	while(rs.next())
    	{
    		jasus = true;
    	}
    	if(jasus == false)
    	{
    		pst = con.prepareStatement("select * from customer where mob=?");
        	pst.setString(1, list1.getSelectionModel().getSelectedItem());
        	ResultSet rs1 = pst.executeQuery();
        	while(rs1.next())
        	{
        		String d = rs1.getString("dos");
        		int dt = d.lastIndexOf("-");
        		int dtt = Integer.parseInt(d.substring(dt+1));
        		int mt = d.indexOf("-");
        		int mtt = Integer.parseInt(d.substring(mt+1, dt));
        		if(mtt < m)
        		{
        			doAlert("Fill correct month plz...");
                	return;
        		}
        		if(mtt == m)
        		{
        			if(Integer.parseInt(date.getSelectionModel().getSelectedItem()) < dtt)
        			{
        				{
                			doAlert("Fill correct date plz...");
                        	return;
                		}
        			}
        		}
        		int count = Integer.parseInt(date.getSelectionModel().getSelectedItem());
        		count = count - dtt + 1;
        		float qtc = rs1.getFloat("qtyc");
        		float qtb = rs1.getFloat("qtyb");
        		float rtc = rs1.getFloat("ratec");
        		float rtb = rs1.getFloat("rateb");
        		crate.setText(String.valueOf(rtc));
        		brate.setText(String.valueOf(rtb));
        		pst = con.prepareStatement("select sum(cq), sum(bq) from dailylogin where mobile=? and dyear=? and dmonth=?");
            	pst.setString(1, list1.getSelectionModel().getSelectedItem());
            	pst.setInt(3, m);
            	pst.setInt(2, Integer.parseInt(year.getSelectionModel().getSelectedItem()));
            	ResultSet rs2 = pst.executeQuery();
            	float sum1=0, sum2=0;
            	while(rs2.next())
            	{
            		sum1 = rs2.getFloat(1);
            		sum2 = rs2.getFloat(2);
            	}
            	float tqc = sum1 + qtc * count;
            	float tqb = sum2 + qtb * count;
            	float cbl = tqc * rtc;
            	float bbl = tqb * rtb;
            	cqty.setText(String.valueOf(tqc));
            	bqty.setText(String.valueOf(tqb));
            	cbill.setText(String.valueOf(tqc * rtc));
            	bbill.setText(String.valueOf(tqb * rtb));
            	float tot = Float.parseFloat(cbill.getText()) + Float.parseFloat(bbill.getText());
            	total.setText("Rs. " + tot + "/-");
            	pst = con.prepareStatement("insert into billhistory values(?, ?, ?, ?, ?, ?, ?, ?)");
            	pst.setString(1, list1.getSelectionModel().getSelectedItem());
            	pst.setFloat(2, tqc);
            	pst.setFloat(3, tqb);
            	pst.setFloat(4,cbl);
            	pst.setFloat(5, bbl);
            	pst.setInt(6, 0);
            	pst.setInt(7, m);
            	pst.setInt(8, Integer.parseInt(year.getSelectionModel().getSelectedItem()));
            	pst.executeUpdate();
        	}
    	}
    	else
    	{
    		pst = con.prepareStatement("select * from customer where mob=?");
        	pst.setString(1, list1.getSelectionModel().getSelectedItem());
        	ResultSet rs1 = pst.executeQuery();
        	while(rs1.next())
        	{
        		int count = Integer.parseInt(date.getSelectionModel().getSelectedItem());
        		float qtc = rs1.getFloat("qtyc");
        		float qtb = rs1.getFloat("qtyb");
        		float rtc = rs1.getFloat("ratec");
        		float rtb = rs1.getFloat("rateb");
        		crate.setText(String.valueOf(rtc));
        		brate.setText(String.valueOf(rtb));
        		pst = con.prepareStatement("select sum(cq), sum(bq) from dailylogin where mobile=? and dyear=? and dmonth=?");
            	pst.setString(1, list1.getSelectionModel().getSelectedItem());
            	pst.setInt(3, m);
            	pst.setInt(2, Integer.parseInt(year.getSelectionModel().getSelectedItem()));
            	ResultSet rs2 = pst.executeQuery();
            	float sum1=0, sum2=0;
            	while(rs2.next())
            	{
            		sum1 = rs2.getFloat(1);
            		sum2 = rs2.getFloat(2);
            	}
            	float tqc = sum1 + qtc * count;
            	float tqb = sum2 + qtb * count;
            	float cbl = tqc * rtc;
            	float bbl = tqb * rtb;
            	cqty.setText(String.valueOf(tqc));
            	bqty.setText(String.valueOf(tqb));
            	cbill.setText(String.valueOf(tqc * rtc));
            	bbill.setText(String.valueOf(tqb * rtb));
            	float tot = Float.parseFloat(cbill.getText()) + Float.parseFloat(bbill.getText());
            	total.setText("Rs. " + tot + "/-");
            	pst = con.prepareStatement("insert into billhistory values(?, ?, ?, ?, ?, ?, ?, ?)");
            	pst.setString(1, list1.getSelectionModel().getSelectedItem());
            	pst.setFloat(2, tqc);
            	pst.setFloat(3, tqb);
            	pst.setFloat(4,cbl);
            	pst.setFloat(5, bbl);
            	pst.setInt(6, 0);
            	pst.setInt(7, m);
            	pst.setInt(8, Integer.parseInt(year.getSelectionModel().getSelectedItem()));
            	pst.executeUpdate();
        	}
    	}
    	String msg = "Your Bill for month: " + s + " is: " + total.getText();
    	
    	String resp=SST_SMS.bceSunSoftSend(list1.getSelectionModel().getSelectedItem(), msg);
    	System.out.println(resp);
    	
    	if(resp.indexOf("Exception")!=-1)
    		System.out.println("Check Internet Connection");
    	
    	else
    		if(resp.indexOf("successfully")!=-1)
        		System.out.println("Sent");
    		else
    			System.out.println( "Invalid Number");
    }

    ArrayList<String> names;
    void dofilllist2() throws SQLException
    {
    	names = new ArrayList<String>();
    	pst = con.prepareStatement("select distinct cname from customer");
    	ResultSet rs = pst.executeQuery();
    	while(rs.next())
    	{
    		String r = rs.getString("cname");
    		names.add(r);
    	}
    	list2.getItems().addAll(names);
    }
    
    ArrayList<String> addl;
    void dofilllist1() throws SQLException
    {
    	addl = new ArrayList<String>();
    	pst = con.prepareStatement("select distinct mob from customer");
    	ResultSet rs = pst.executeQuery();
    	while(rs.next())
    	{
    		String r = rs.getString("mob");
    		addl.add(r);
    	}
    	list1.getItems().addAll(addl);
    }
    
    void dofillmonth()
    {
    	month.getItems().add("January");
    	month.getItems().add("Feburary");
    	month.getItems().add("March");
    	month.getItems().add("April");
    	month.getItems().add("May");
    	month.getItems().add("June");
    	month.getItems().add("July");
    	month.getItems().add("August");
    	month.getItems().add("September");
    	month.getItems().add("October");
    	month.getItems().add("November");
    	month.getItems().add("December");
    }
    
    void dofillyear()
    {
    	/*year.getItems().add("2013");
    	year.getItems().add("2014");
    	year.getItems().add("2015");
    	year.getItems().add("2016");
    	year.getItems().add("2017");
    	year.getItems().add("2018");*/
    	Calendar now = Calendar.getInstance();
    	String str = String.valueOf(now.get(Calendar.YEAR)); 
    	year.getItems().add(str);
    }
    
    void dofilldate()
    {
    	date.getItems().add("1"); date.getItems().add("2"); date.getItems().add("3"); date.getItems().add("4");
    	date.getItems().add("5"); date.getItems().add("6"); date.getItems().add("7"); date.getItems().add("8");
    	date.getItems().add("9"); date.getItems().add("10"); date.getItems().add("11"); date.getItems().add("12");
    	date.getItems().add("13"); date.getItems().add("14"); date.getItems().add("15"); date.getItems().add("16");
    	date.getItems().add("17"); date.getItems().add("18"); date.getItems().add("19"); date.getItems().add("20");
    	date.getItems().add("21"); date.getItems().add("22"); date.getItems().add("23"); date.getItems().add("24");
    	date.getItems().add("25"); date.getItems().add("26"); date.getItems().add("27"); date.getItems().add("28");
    	date.getItems().add("29"); date.getItems().add("30"); date.getItems().add("31"); 
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
        assert list1 != null : "fx:id=\"list1\" was not injected: check your FXML file 'GenerateBillView.fxml'.";
        assert list2 != null : "fx:id=\"list2\" was not injected: check your FXML file 'GenerateBillView.fxml'.";
        assert month != null : "fx:id=\"month\" was not injected: check your FXML file 'GenerateBillView.fxml'.";
        assert year != null : "fx:id=\"year\" was not injected: check your FXML file 'GenerateBillView.fxml'.";
        assert date != null : "fx:id=\"date\" was not injected: check your FXML file 'GenerateBillView.fxml'.";
        assert crate != null : "fx:id=\"crate\" was not injected: check your FXML file 'GenerateBillView.fxml'.";
        assert cqty != null : "fx:id=\"cqty\" was not injected: check your FXML file 'GenerateBillView.fxml'.";
        assert cbill != null : "fx:id=\"cbill\" was not injected: check your FXML file 'GenerateBillView.fxml'.";
        assert brate != null : "fx:id=\"brate\" was not injected: check your FXML file 'GenerateBillView.fxml'.";
        assert bqty != null : "fx:id=\"bqty\" was not injected: check your FXML file 'GenerateBillView.fxml'.";
        assert bbill != null : "fx:id=\"bbill\" was not injected: check your FXML file 'GenerateBillView.fxml'.";
        assert total != null : "fx:id=\"total\" was not injected: check your FXML file 'GenerateBillView.fxml'.";
        
        con = MySQLConnection.doConnect();
        try {
			dofilllist1();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        try {
			dofilllist2();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        dofillmonth();
        dofillyear();
        dofilldate();
    }
}
