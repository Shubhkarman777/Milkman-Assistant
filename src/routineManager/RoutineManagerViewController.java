package routineManager;

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

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.cell.PropertyValueFactory;
import milkMan.MySQLConnection;

public class RoutineManagerViewController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private ComboBox<String> cmob;

    @FXML
    private ComboBox<String> cmonth;

    @FXML
    private TextField txtyr;

    @FXML
    private TableView<RoutineBean> tbl;
    
    ObservableList<RoutineBean> list; 

    @FXML
    void doShow(ActionEvent event) {
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
    	if (cmob.getValue() == null) { 
        	doAlert("Select Mobile No. plz...");
        	cmob.requestFocus();
        	return;
        }
    	if (cmonth.getValue() == null) { 
        	doAlert("Select Month plz...");
        	cmonth.requestFocus();
        	return;
        }
    	TableColumn<RoutineBean, String> mobile=new TableColumn<RoutineBean, String>("Mobile No.");
    	mobile.setCellValueFactory(new PropertyValueFactory<>("mobile"));
    	mobile.setMinWidth(100);
    	
    	TableColumn<RoutineBean, Float> cq=new TableColumn<RoutineBean, Float>("Cow Quant.");
    	cq.setCellValueFactory(new PropertyValueFactory<>("cq"));
    	cq.setMinWidth(100);
    	
    	
    	TableColumn<RoutineBean, Float> bq=new TableColumn<RoutineBean, Float>("Buffallo Quant.");
    	bq.setCellValueFactory(new PropertyValueFactory<>("bq"));
    	bq.setMinWidth(100);
    	
    	
    	TableColumn<RoutineBean, Integer> dmonth=new TableColumn<RoutineBean, Integer>("Month");
    	dmonth.setCellValueFactory(new PropertyValueFactory<>("dmonth"));
    	dmonth.setMinWidth(100);
    	
    	TableColumn<RoutineBean, Integer> dyear=new TableColumn<RoutineBean, Integer>("Year");
    	dyear.setCellValueFactory(new PropertyValueFactory<>("dyear"));
    	dyear.setMinWidth(100);
    	
    	TableColumn<RoutineBean, Integer> dday=new TableColumn<RoutineBean, Integer>("Day of Month");
    	dday.setCellValueFactory(new PropertyValueFactory<>("dday"));
    	dday.setMinWidth(100);
    	
tbl.getColumns().clear();
    	
    	tbl.getColumns().addAll(mobile, cq, bq,dmonth, dyear, dday);
    	
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
    	
    	list=getRecordsFromTableMonthAndYear(txtyr.getText(), m, cmob.getSelectionModel().getSelectedItem());
    						//tbl.setItems(null);
    	tbl.setItems(list);
    }
    
    ObservableList<RoutineBean> getRecordsFromTableMonthAndYear(String yr, int m, String mobl)
   	{
   		ObservableList<RoutineBean> list=FXCollections.observableArrayList();
   		
   		try {
   			  pst=con.prepareStatement("select * from dailylogin where dmonth = ? and dyear = ? and mobile = ?");
   			  pst.setInt(1, m);
   			  pst.setInt(2, Integer.parseInt(yr));
   			  pst.setString(3, mobl);
   			 
   			ResultSet rs=  pst.executeQuery();
   			while(rs.next())
   			{
   				String mobile = rs.getString("mobile");
   				float cq = rs.getFloat("cq");
   				float bq = rs.getFloat("bq");
   				int dmonth = rs.getInt("dmonth");
   				int dyear = rs.getInt("dyear");
   				int dday = rs.getInt("dday");
   				
   				RoutineBean bean=new RoutineBean(mobile,cq,bq,dmonth,dyear,dday);
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
    	txtyr.setText(String.valueOf(now.get(Calendar.YEAR)));
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
        assert cmob != null : "fx:id=\"cmob\" was not injected: check your FXML file 'RoutineManagerView.fxml'.";
        assert cmonth != null : "fx:id=\"cmonth\" was not injected: check your FXML file 'RoutineManagerView.fxml'.";
        assert txtyr != null : "fx:id=\"txtyr\" was not injected: check your FXML file 'RoutineManagerView.fxml'.";
        assert tbl != null : "fx:id=\"tbl\" was not injected: check your FXML file 'RoutineManagerView.fxml'.";

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
