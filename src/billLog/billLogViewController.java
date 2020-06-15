package billLog;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.Writer;
import java.net.URL;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.ResourceBundle;

import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;

import customerFinder.CustomerBean;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.ComboBox;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.ToggleGroup;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.FileChooser;
import milkMan.MySQLConnection;

public class billLogViewController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private ComboBox<String> cmonth;

    @FXML
    private ComboBox<String> cyear;

    @FXML
    private RadioButton rad1;

    @FXML
    private ToggleGroup Payment;

    @FXML
    private RadioButton rad2;

    @FXML
    private ComboBox<String> cmobile;

    @FXML
    private TableView<BillBean> tbl;

    ObservableList<BillBean> list; 
    
    @FXML
    void exportExcel(ActionEvent event) {
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
    	try {
			writeExcel();
			Alert al = new Alert(AlertType.INFORMATION);
	    	al.setTitle("Title");
			al.setContentText("Exported To Excel!!");
			al.show();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }
    
    public void writeExcel() throws Exception {
        Writer writer = null;
        try {
        	FileChooser chooser=new FileChooser();
	    	
        	chooser.setTitle("Select Path:");
        	
        	chooser.getExtensionFilters().addAll(
                    new FileChooser.ExtensionFilter("All Files", "*.*")    
                );
        	File file=chooser.showSaveDialog(null);
        	String filePath=file.getAbsolutePath();
        	if(!(filePath.endsWith(".csv")||filePath.endsWith(".CSV")))
        	{
        		Alert al = new Alert(AlertType.INFORMATION);
    	    	al.setTitle("Title");
    			al.setContentText("File name should have .csv extension!!");
    			al.show();
        		return;
        	}
        	file = new File(filePath); 
            writer = new BufferedWriter(new FileWriter(file));
            String text="Mobile,Cow Quantity,Buffallo Quantity,Cow Bill,Buffallo Bill,Month,Year,status\n";
            writer.write(text);
            for (BillBean p : list)
            {
				text = p.getMobile()+ "," + p.getCqt()+ "," + p.getBqt()+ "," + p.getCbill()+ "," + p.getBbill()+ "," + p.getMonthh()+ "," + p.getYearr()+ "," + p.getStatus()+"\n";
                writer.write(text);
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        finally {
           
            writer.flush();
            writer.close();
        }
    }
    
    @FXML
    void fetchMonthYear(ActionEvent event) {
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
    	
    	if (cyear.getValue() == null) { 
        	doAlert("Select Year plz...");
        	cyear.requestFocus();
        	return;
        }
    	TableColumn<BillBean, String> mobile=new TableColumn<BillBean, String>("Mobile No.");
    	mobile.setCellValueFactory(new PropertyValueFactory<>("mobile"));
    	mobile.setMinWidth(100);
    	
    	TableColumn<BillBean, Float> cqt=new TableColumn<BillBean, Float>("Cow Quant.");
    	cqt.setCellValueFactory(new PropertyValueFactory<>("cqt"));
    	cqt.setMinWidth(100);
    	
    	
    	TableColumn<BillBean, Float> bqt=new TableColumn<BillBean, Float>("Buffallo Quant.");
    	bqt.setCellValueFactory(new PropertyValueFactory<>("bqt"));
    	bqt.setMinWidth(100);
    	
    	
    	TableColumn<BillBean, Float> cbill=new TableColumn<BillBean, Float>("Cow Bill");
    	cbill.setCellValueFactory(new PropertyValueFactory<>("cbill"));
    	cbill.setMinWidth(100);
    	
    	TableColumn<BillBean, Float> bbill=new TableColumn<BillBean, Float>("Buffallo Bill");
    	bbill.setCellValueFactory(new PropertyValueFactory<>("bbill"));
    	bbill.setMinWidth(100);
    	
    	TableColumn<BillBean, Integer> monthh=new TableColumn<BillBean, Integer>("Month");
    	monthh.setCellValueFactory(new PropertyValueFactory<>("monthh"));
    	monthh.setMinWidth(100);
    	
    	TableColumn<BillBean, Integer> yearr=new TableColumn<BillBean, Integer>("Year");
    	yearr.setCellValueFactory(new PropertyValueFactory<>("yearr"));
    	yearr.setMinWidth(100);
    	
    	TableColumn<BillBean, Integer> status=new TableColumn<BillBean, Integer>("Status Of Payment");
    	status.setCellValueFactory(new PropertyValueFactory<>("status"));
    	status.setMinWidth(200);
    	
    	tbl.getColumns().clear();
    	
    	tbl.getColumns().addAll(mobile, cqt, bqt, cbill, bbill, monthh, yearr, status);
    	
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
    	
    	list=getRecordsFromTableMonthAndYear(cyear.getValue(), m);
    						//tbl.setItems(null);
    	tbl.setItems(list);
    }
    
    ObservableList<BillBean> getRecordsFromTableMonthAndYear(String yr, int m)
   	{
   		ObservableList<BillBean> list=FXCollections.observableArrayList();
   		
   		try {
   			  pst=con.prepareStatement("select * from billhistory where monthh = ? and yearr = ?");
   			  pst.setInt(1, m);
   			  pst.setInt(2, Integer.parseInt(yr));
   			 
   			ResultSet rs=  pst.executeQuery();
   			while(rs.next())
   			{
   				String mobile = rs.getString("mobile");
   				float cqt = rs.getFloat("cqt");
   				float bqt = rs.getFloat("bqt");
   				float cbill = rs.getFloat("cbill");
   				float bbill = rs.getFloat("bbill");
   				int monthh = rs.getInt("monthh");
   				int yearr = rs.getInt("yearr");
   				int status = rs.getInt("sstatus");
   				
   				BillBean bean=new BillBean(mobile, cqt, bqt, cbill, bbill, monthh,yearr,status);
   				list.add(bean);
   			}
   			
   			} 
   		catch (SQLException e) 
   		{
   			// TODO Auto-generated catch block
   			e.printStackTrace();
   		}
   		
   		return list;
   	}

    @FXML
    void fetchPayment(ActionEvent event) {
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
    	TableColumn<BillBean, String> mobile=new TableColumn<BillBean, String>("Mobile No.");
    	mobile.setCellValueFactory(new PropertyValueFactory<>("mobile"));
    	mobile.setMinWidth(100);
    	
    	TableColumn<BillBean, Float> cqt=new TableColumn<BillBean, Float>("Cow Quant.");
    	cqt.setCellValueFactory(new PropertyValueFactory<>("cqt"));
    	cqt.setMinWidth(100);
    	
    	
    	TableColumn<BillBean, Float> bqt=new TableColumn<BillBean, Float>("Buffallo Quant.");
    	bqt.setCellValueFactory(new PropertyValueFactory<>("bqt"));
    	bqt.setMinWidth(100);
    	
    	
    	TableColumn<BillBean, Float> cbill=new TableColumn<BillBean, Float>("Cow Bill");
    	cbill.setCellValueFactory(new PropertyValueFactory<>("cbill"));
    	cbill.setMinWidth(100);
    	
    	TableColumn<BillBean, Float> bbill=new TableColumn<BillBean, Float>("Buffallo Bill");
    	bbill.setCellValueFactory(new PropertyValueFactory<>("bbill"));
    	bbill.setMinWidth(100);
    	
    	TableColumn<BillBean, Integer> monthh=new TableColumn<BillBean, Integer>("Month");
    	monthh.setCellValueFactory(new PropertyValueFactory<>("monthh"));
    	monthh.setMinWidth(100);
    	
    	TableColumn<BillBean, Integer> yearr=new TableColumn<BillBean, Integer>("Year");
    	yearr.setCellValueFactory(new PropertyValueFactory<>("yearr"));
    	yearr.setMinWidth(100);
    	
    	TableColumn<BillBean, Integer> status=new TableColumn<BillBean, Integer>("Status Of Payment");
    	status.setCellValueFactory(new PropertyValueFactory<>("status"));
    	status.setMinWidth(200);
    	
    	tbl.getColumns().clear();
    	
    	tbl.getColumns().addAll(mobile, cqt, bqt, cbill, bbill, monthh, yearr, status);
    	
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
    	
    	int stts;
    	if(rad1.isSelected() == true)
    		stts = 1;
    	else
    		stts = 0;
    	
    	list=getRecordsFromTablePay(cyear.getValue(), m, stts);
    						//tbl.setItems(null);
    	tbl.setItems(list);
    }
    
    ObservableList<BillBean> getRecordsFromTablePay(String yr, int m, int stts)
   	{
   		ObservableList<BillBean> list=FXCollections.observableArrayList();
   		
   		try {
   			  pst=con.prepareStatement("select * from billhistory where monthh = ? and yearr = ? and sstatus = ?");
   			  pst.setInt(1, m);
   			  pst.setInt(2, Integer.parseInt(yr));
   			  pst.setInt(3, stts);
   			 
   			ResultSet rs=  pst.executeQuery();
   			while(rs.next())
   			{
   				String mobile = rs.getString("mobile");
   				float cqt = rs.getFloat("cqt");
   				float bqt = rs.getFloat("bqt");
   				float cbill = rs.getFloat("cbill");
   				float bbill = rs.getFloat("bbill");
   				int monthh = rs.getInt("monthh");
   				int yearr = rs.getInt("yearr");
   				int status = rs.getInt("sstatus");
   				
   				BillBean bean=new BillBean(mobile, cqt, bqt, cbill, bbill, monthh,yearr,status);
   				list.add(bean);
   			}
   			
   			} 
   		catch (SQLException e) 
   		{
   			// TODO Auto-generated catch block
   			e.printStackTrace();
   		}
   		
   		return list;
   	}

    @FXML
    void fetchTotalBillHistory(ActionEvent event) {
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
    	if (cmobile.getValue() == null) { 
        	doAlert("Select Mobile no. plz...");
        	cmobile.requestFocus();
        	return;
        }
    	TableColumn<BillBean, String> mobile=new TableColumn<BillBean, String>("Mobile No.");
    	mobile.setCellValueFactory(new PropertyValueFactory<>("mobile"));
    	mobile.setMinWidth(100);
    	
    	TableColumn<BillBean, Float> cqt=new TableColumn<BillBean, Float>("Cow Quant.");
    	cqt.setCellValueFactory(new PropertyValueFactory<>("cqt"));
    	cqt.setMinWidth(100);
    	
    	
    	TableColumn<BillBean, Float> bqt=new TableColumn<BillBean, Float>("Buffallo Quant.");
    	bqt.setCellValueFactory(new PropertyValueFactory<>("bqt"));
    	bqt.setMinWidth(100);
    	
    	
    	TableColumn<BillBean, Float> cbill=new TableColumn<BillBean, Float>("Cow Bill");
    	cbill.setCellValueFactory(new PropertyValueFactory<>("cbill"));
    	cbill.setMinWidth(100);
    	
    	TableColumn<BillBean, Float> bbill=new TableColumn<BillBean, Float>("Buffallo Bill");
    	bbill.setCellValueFactory(new PropertyValueFactory<>("bbill"));
    	bbill.setMinWidth(100);
    	
    	TableColumn<BillBean, Integer> monthh=new TableColumn<BillBean, Integer>("Month");
    	monthh.setCellValueFactory(new PropertyValueFactory<>("monthh"));
    	monthh.setMinWidth(100);
    	
    	TableColumn<BillBean, Integer> yearr=new TableColumn<BillBean, Integer>("Year");
    	yearr.setCellValueFactory(new PropertyValueFactory<>("yearr"));
    	yearr.setMinWidth(100);
    	
    	TableColumn<BillBean, Integer> status=new TableColumn<BillBean, Integer>("Status Of Payment");
    	status.setCellValueFactory(new PropertyValueFactory<>("status"));
    	status.setMinWidth(200);
    	
    	tbl.getColumns().clear();
    	
    	tbl.getColumns().addAll(mobile, cqt, bqt, cbill, bbill, monthh, yearr, status);
    	
    	list=getRecordsFromTableMobile(cmobile.getValue());
    						//tbl.setItems(null);
    	tbl.setItems(list);
    }
    
    ObservableList<BillBean> getRecordsFromTableMobile(String mob)
   	{
   		ObservableList<BillBean> list=FXCollections.observableArrayList();
   		
   		try {
   			  pst=con.prepareStatement("select * from billhistory where mobile = ?");
   			  pst.setString(1, mob);
   			 
   			ResultSet rs=  pst.executeQuery();
   			while(rs.next())
   			{
   				String mobile = rs.getString("mobile");
   				float cqt = rs.getFloat("cqt");
   				float bqt = rs.getFloat("bqt");
   				float cbill = rs.getFloat("cbill");
   				float bbill = rs.getFloat("bbill");
   				int monthh = rs.getInt("monthh");
   				int yearr = rs.getInt("yearr");
   				int status = rs.getInt("sstatus");
   				
   				BillBean bean=new BillBean(mobile, cqt, bqt, cbill, bbill, monthh,yearr,status);
   				list.add(bean);
   			}
   			
   			} 
   		catch (SQLException e) 
   		{
   			// TODO Auto-generated catch block
   			e.printStackTrace();
   		}
   		
   		return list;
   	}
    
    void dofillmonth()
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
    
    void dofillyear()
    {
    	/*cyear.getItems().add("2013");
    	cyear.getItems().add("2014");
    	cyear.getItems().add("2015");
    	cyear.getItems().add("2016");
    	cyear.getItems().add("2017");
    	cyear.getItems().add("2018");*/
    	Calendar now = Calendar.getInstance();
    	String str = String.valueOf(now.get(Calendar.YEAR)); 
    	cyear.getItems().add(str);
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
    	cmobile.getItems().addAll(addl);
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
        assert cmonth != null : "fx:id=\"cmonth\" was not injected: check your FXML file 'billLogView.fxml'.";
        assert cyear != null : "fx:id=\"cyear\" was not injected: check your FXML file 'billLogView.fxml'.";
        assert rad1 != null : "fx:id=\"rad1\" was not injected: check your FXML file 'billLogView.fxml'.";
        assert Payment != null : "fx:id=\"Payment\" was not injected: check your FXML file 'billLogView.fxml'.";
        assert rad2 != null : "fx:id=\"rad2\" was not injected: check your FXML file 'billLogView.fxml'.";
        assert cmobile != null : "fx:id=\"cmobile\" was not injected: check your FXML file 'billLogView.fxml'.";
        assert tbl != null : "fx:id=\"tbl\" was not injected: check your FXML file 'billLogView.fxml'.";

        con = MySQLConnection.doConnect();
        dofillmonth();
        dofillyear();
        try {
			dofillmob();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }
}
