package customerFinder;

import java.io.File;
import java.net.URL;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
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

public class customerfinderViewController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private ComboBox<String> area;

    @FXML
    private TextField txtName;

    @FXML
    private TableView<CustomerBean> tbl;

    @FXML
    void fetchAll(ActionEvent event) {
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
    	TableColumn<CustomerBean, String> mob=new TableColumn<CustomerBean, String>("Mobile No.");
    	mob.setCellValueFactory(new PropertyValueFactory<>("mob"));
    	mob.setMinWidth(100);
    	
    	TableColumn<CustomerBean, String> cname=new TableColumn<CustomerBean, String>("Customer Name");
    	cname.setCellValueFactory(new PropertyValueFactory<>("cname"));
    	cname.setMinWidth(100);
    	
    	
    	TableColumn<CustomerBean, String> cdd=new TableColumn<CustomerBean, String>("Address");
    	cdd.setCellValueFactory(new PropertyValueFactory<>("cdd"));
    	cdd.setMinWidth(100);
    	
    	
    	TableColumn<CustomerBean, String> carea=new TableColumn<CustomerBean, String>("Area");
    	carea.setCellValueFactory(new PropertyValueFactory<>("carea"));
    	carea.setMinWidth(100);
    	
    	TableColumn<CustomerBean, String> city=new TableColumn<CustomerBean, String>("City");
    	city.setCellValueFactory(new PropertyValueFactory<>("city"));
    	city.setMinWidth(100);
    	
    	TableColumn<CustomerBean, Float> qtyc=new TableColumn<CustomerBean, Float>("Cow Quant.");
    	qtyc.setCellValueFactory(new PropertyValueFactory<>("qtyc"));
    	qtyc.setMinWidth(100);
    	
    	TableColumn<CustomerBean, Float> ratec=new TableColumn<CustomerBean, Float>("Cow Rate");
    	ratec.setCellValueFactory(new PropertyValueFactory<>("ratec"));
    	ratec.setMinWidth(100);
    	
    	TableColumn<CustomerBean, Float> qtyb=new TableColumn<CustomerBean, Float>("Buffallo Quant.");
    	qtyb.setCellValueFactory(new PropertyValueFactory<>("qtyb"));
    	qtyb.setMinWidth(100);
    	
    	TableColumn<CustomerBean, Float> rateb=new TableColumn<CustomerBean, Float>("Buffallo Rate");
    	rateb.setCellValueFactory(new PropertyValueFactory<>("rateb"));
    	rateb.setMinWidth(100);
    	
    	TableColumn<CustomerBean, String> dos=new TableColumn<CustomerBean, String>("Date of Start");
    	dos.setCellValueFactory(new PropertyValueFactory<>("dos"));
    	dos.setMinWidth(100);
    	
    	tbl.getColumns().clear();
    	
    	tbl.getColumns().addAll(mob, cname, cdd, carea, city, qtyc, ratec, qtyb, rateb, dos);
    	
    	ObservableList<CustomerBean> list=getRecordsFromTable();
    						//tbl.setItems(null);
    	tbl.setItems(list);
    }
    
    ObservableList<CustomerBean> getRecordsFromTable()
   	{
   		ObservableList<CustomerBean> list=FXCollections.observableArrayList();
   		
   		try {
   			  pst=con.prepareStatement("select * from customer");
   			 
   			ResultSet rs=  pst.executeQuery();
   			while(rs.next())
   			{
   				String mob = rs.getString("mob");
   				String cname = rs.getString("cname");
   				String cdd = rs.getString("cdd");
   				String carea = rs.getString("carea");
   				String city = rs.getString("city");
   				float qtyc = rs.getFloat("qtyc");
   				float ratec = rs.getFloat("ratec");
   				float qtyb = rs.getFloat("qtyb");
   				float rateb = rs.getFloat("rateb");
   				Date d = rs.getDate("dos");
   				String dos = d.toString();
   				
   				CustomerBean bean=new CustomerBean(mob, cname, cdd, carea, city, qtyc,ratec, qtyb, rateb, dos);
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
    void fetchArea(ActionEvent event) {
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
    	
    	if (area.getValue() == null) { 
        	doAlert("Select Area plz...");
        	area.requestFocus();
        	return;
        }
    	TableColumn<CustomerBean, String> mob=new TableColumn<CustomerBean, String>("Mobile No.");
    	mob.setCellValueFactory(new PropertyValueFactory<>("mob"));
    	mob.setMinWidth(100);
    	
    	TableColumn<CustomerBean, String> cname=new TableColumn<CustomerBean, String>("Customer Name");
    	cname.setCellValueFactory(new PropertyValueFactory<>("cname"));
    	cname.setMinWidth(100);
    	
    	
    	TableColumn<CustomerBean, String> cdd=new TableColumn<CustomerBean, String>("Address");
    	cdd.setCellValueFactory(new PropertyValueFactory<>("cdd"));
    	cdd.setMinWidth(100);
    	
    	
    	TableColumn<CustomerBean, String> carea=new TableColumn<CustomerBean, String>("Area");
    	carea.setCellValueFactory(new PropertyValueFactory<>("carea"));
    	carea.setMinWidth(100);
    	
    	TableColumn<CustomerBean, String> city=new TableColumn<CustomerBean, String>("City");
    	city.setCellValueFactory(new PropertyValueFactory<>("city"));
    	city.setMinWidth(100);
    	
    	TableColumn<CustomerBean, Float> qtyc=new TableColumn<CustomerBean, Float>("Cow Quant.");
    	qtyc.setCellValueFactory(new PropertyValueFactory<>("qtyc"));
    	qtyc.setMinWidth(100);
    	
    	TableColumn<CustomerBean, Float> ratec=new TableColumn<CustomerBean, Float>("Cow Rate");
    	ratec.setCellValueFactory(new PropertyValueFactory<>("ratec"));
    	ratec.setMinWidth(100);
    	
    	TableColumn<CustomerBean, Float> qtyb=new TableColumn<CustomerBean, Float>("Buffallo Quant.");
    	qtyb.setCellValueFactory(new PropertyValueFactory<>("qtyb"));
    	qtyb.setMinWidth(100);
    	
    	TableColumn<CustomerBean, Float> rateb=new TableColumn<CustomerBean, Float>("Buffallo Rate");
    	rateb.setCellValueFactory(new PropertyValueFactory<>("rateb"));
    	rateb.setMinWidth(100);
    	
    	TableColumn<CustomerBean, String> dos=new TableColumn<CustomerBean, String>("Date of Start");
    	dos.setCellValueFactory(new PropertyValueFactory<>("dos"));
    	dos.setMinWidth(100);
    	
    	tbl.getColumns().clear();
    	
    	tbl.getColumns().addAll(mob, cname, cdd, carea, city, qtyc, ratec, qtyb, rateb, dos);
    	
    	ObservableList<CustomerBean> list=getRecordsFromTableSome(area.getValue());
    						//tbl.setItems(null);
    	tbl.setItems(list);
    }
    
    ObservableList<CustomerBean> getRecordsFromTableSome(String areaa)
   	{
   		ObservableList<CustomerBean> list=FXCollections.observableArrayList();
   		
   		try {
   			  pst=con.prepareStatement("select * from customer where carea=?");
   			  pst.setString(1, areaa);
   			 
   			ResultSet rs=  pst.executeQuery();
   			while(rs.next())
   			{
   				String mob = rs.getString("mob");
   				String cname = rs.getString("cname");
   				String cdd = rs.getString("cdd");
   				String carea = rs.getString("carea");
   				String city = rs.getString("city");
   				float qtyc = rs.getFloat("qtyc");
   				float ratec = rs.getFloat("ratec");
   				float qtyb = rs.getFloat("qtyb");
   				float rateb = rs.getFloat("rateb");
   				Date d = rs.getDate("dos");
   				String dos = d.toString();
   				
   				CustomerBean bean=new CustomerBean(mob, cname, cdd, carea, city, qtyc,ratec, qtyb, rateb, dos);
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
    void fetchName(ActionEvent event) {
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
            if (txtName.getText().equals("")) { 
            	doAlert("Fill name plz...");
            	return;
            }
    	TableColumn<CustomerBean, String> mob=new TableColumn<CustomerBean, String>("Mobile No.");
    	mob.setCellValueFactory(new PropertyValueFactory<>("mob"));
    	mob.setMinWidth(100);
    	
    	TableColumn<CustomerBean, String> cname=new TableColumn<CustomerBean, String>("Customer Name");
    	cname.setCellValueFactory(new PropertyValueFactory<>("cname"));
    	cname.setMinWidth(100);
    	
    	
    	TableColumn<CustomerBean, String> cdd=new TableColumn<CustomerBean, String>("Address");
    	cdd.setCellValueFactory(new PropertyValueFactory<>("cdd"));
    	cdd.setMinWidth(100);
    	
    	
    	TableColumn<CustomerBean, String> carea=new TableColumn<CustomerBean, String>("Area");
    	carea.setCellValueFactory(new PropertyValueFactory<>("carea"));
    	carea.setMinWidth(100);
    	
    	TableColumn<CustomerBean, String> city=new TableColumn<CustomerBean, String>("City");
    	city.setCellValueFactory(new PropertyValueFactory<>("city"));
    	city.setMinWidth(100);
    	
    	TableColumn<CustomerBean, Float> qtyc=new TableColumn<CustomerBean, Float>("Cow Quant.");
    	qtyc.setCellValueFactory(new PropertyValueFactory<>("qtyc"));
    	qtyc.setMinWidth(100);
    	
    	TableColumn<CustomerBean, Float> ratec=new TableColumn<CustomerBean, Float>("Cow Rate");
    	ratec.setCellValueFactory(new PropertyValueFactory<>("ratec"));
    	ratec.setMinWidth(100);
    	
    	TableColumn<CustomerBean, Float> qtyb=new TableColumn<CustomerBean, Float>("Buffallo Quant.");
    	qtyb.setCellValueFactory(new PropertyValueFactory<>("qtyb"));
    	qtyb.setMinWidth(100);
    	
    	TableColumn<CustomerBean, Float> rateb=new TableColumn<CustomerBean, Float>("Buffallo Rate");
    	rateb.setCellValueFactory(new PropertyValueFactory<>("rateb"));
    	rateb.setMinWidth(100);
    	
    	TableColumn<CustomerBean, String> dos=new TableColumn<CustomerBean, String>("Date of Start");
    	dos.setCellValueFactory(new PropertyValueFactory<>("dos"));
    	dos.setMinWidth(100);
    	
    	tbl.getColumns().clear();
    	
    	tbl.getColumns().addAll(mob, cname, cdd, carea, city, qtyc, ratec, qtyb, rateb, dos);
    	
    	ObservableList<CustomerBean> list=getRecordsFromTableSomeNames(txtName.getText());
    						//tbl.setItems(null);
    	tbl.setItems(list);
    }
    
    ObservableList<CustomerBean> getRecordsFromTableSomeNames(String nme)
   	{
   		ObservableList<CustomerBean> list=FXCollections.observableArrayList();
   		
   		try {
   			  pst=con.prepareStatement("select * from customer where cname like ?");
   			  pst.setString(1, "%" + nme + "%");
   			 
   			ResultSet rs=  pst.executeQuery();
   			while(rs.next())
   			{
   				String mob = rs.getString("mob");
   				String cname = rs.getString("cname");
   				String cdd = rs.getString("cdd");
   				String carea = rs.getString("carea");
   				String city = rs.getString("city");
   				float qtyc = rs.getFloat("qtyc");
   				float ratec = rs.getFloat("ratec");
   				float qtyb = rs.getFloat("qtyb");
   				float rateb = rs.getFloat("rateb");
   				Date d = rs.getDate("dos");
   				String dos = d.toString();
   				
   				CustomerBean bean=new CustomerBean(mob, cname, cdd, carea, city, qtyc,ratec, qtyb, rateb, dos);
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
    
    ArrayList<String> addl;
    void dofillarea() throws SQLException
    {
    	addl = new ArrayList<String>();
    	pst = con.prepareStatement("select carea from customer");
    	ResultSet rs = pst.executeQuery();
    	while(rs.next())
    	{
    		String r = rs.getString("carea");
    		addl.add(r);
    	}
    	area.getItems().addAll(addl);
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
        assert area != null : "fx:id=\"area\" was not injected: check your FXML file 'customerfinderView.fxml'.";
        assert txtName != null : "fx:id=\"txtName\" was not injected: check your FXML file 'customerfinderView.fxml'.";
        assert tbl != null : "fx:id=\"tbl\" was not injected: check your FXML file 'customerfinderView.fxml'.";

        con = MySQLConnection.doConnect();
        try {
			dofillarea();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }
}
