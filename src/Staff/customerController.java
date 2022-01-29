package Staff;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.time.LocalDate;
import java.util.ResourceBundle;

import application.Employee_DB;
import application.Main;
import application.employee;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

public class customerController implements Initializable {
	

    @FXML
    private Label txt_customer;

    @FXML
    private TextField txt_name;

    @FXML
    private TextField txt_age;

    @FXML
    private TextField txt_nrc;

    @FXML
    private TextField txt_ph;
    @FXML
    private TextField txt_passport;

    @FXML
    private ComboBox<String> gender_box;

    @FXML
    private Button btnHome;

    @FXML
    private TableView<customer> customer_table;

    @FXML
    private TableColumn<customer, Integer> col_customer;

    @FXML
    private TableColumn<customer,String> col_name;

    @FXML
    private TableColumn<customer,Integer> col_age;

    @FXML
    private TableColumn<customer, String> col_gender;

    @FXML
    private TableColumn<customer, String> col_nrc;

    @FXML
    private TableColumn<customer, String> col_passport;

    @FXML
    private TableColumn<customer, String> col_ph;

    @FXML
    private Button add;

    @FXML
    private Button update;

    @FXML
    private Button delete;

    @FXML
    private Button refresh;
    
    ObservableList<customer> listM;

	int index = -1;
	int I_D= 0;
	static Connection conn = null;
	ResultSet rs = null;
	PreparedStatement pst = null;

	public void Add() {
		String sql = "insert into customer (customer_id,name,age,gender,nrc,passport,ph_no)values(?,?,?,?,?,?,?)";

			try {
				conn = Customer_DB.ConnectDb();
				pst = conn.prepareStatement(sql);
				pst.setInt(1, I_D);
				pst.setString(2, txt_name.getText());
				pst.setInt(3, Integer.parseInt(txt_age.getText()));
				pst.setString(4, gender_box.getValue().toString());
				pst.setString(5, txt_nrc.getText());
				pst.setString(6, txt_passport.getText());
				pst.setString(7, txt_ph.getText());
				
				pst.execute();
				new Alert(AlertType.INFORMATION,"Successfully added",ButtonType.OK).showAndWait();
				refreshdata();
			} catch(Exception e1) {
				new Alert(AlertType.ERROR,"Invalid Input",ButtonType.OK).showAndWait();
			}
	}
	public void Update() {
		String sql = "update customer set customer_id = '" + Integer.parseInt(txt_customer.getText()) + "',name= '" + txt_name.getText() + "',age= '" + Integer.parseInt(txt_age.getText())
				+ "',gender = '"+ gender_box.getValue().toString() +"',nrc= '"+txt_nrc.getText() +"',passport= '"+txt_passport.getText()+"',ph_no= '"+txt_ph.getText()+"'where customer_id='" + txt_customer.getText() + "' ";;
		
		try {
			conn = Customer_DB.ConnectDb();
			pst = conn.prepareStatement(sql);
			pst.execute();
			new Alert(AlertType.INFORMATION,"Successfully updated",ButtonType.OK).showAndWait();
			refreshdata();
			
		} catch (Exception e) {
			new Alert(AlertType.ERROR,""+e,ButtonType.OK).showAndWait();
		}
	
	}
	
	public void Delete() {
		String sql = "delete from customer where customer_id='" + Integer.parseInt(txt_customer.getText()) + "' ";
		
		try {
			conn = Customer_DB.ConnectDb();
			pst = conn.prepareStatement(sql);
			pst.execute();
			new Alert(AlertType.INFORMATION,"Successfully Deleted",ButtonType.OK).showAndWait();
			refreshdata();
		}
			
			catch(Exception e1) {
			new Alert(AlertType.ERROR,""+e1,ButtonType.OK).showAndWait();
		}
	}
	public void refreshdata() {
		I_D = Main.ID_INC("customer", "customer");
		txt_customer.setText(I_D+"");
		listM = Customer_DB.getCustomer();
		customer_table.setItems(listM);
		
		
		col_customer.setCellValueFactory(new PropertyValueFactory<customer, Integer>("c_id"));
		col_name.setCellValueFactory(new PropertyValueFactory<customer, String>("Name"));
		col_age.setCellValueFactory(new PropertyValueFactory<customer, Integer>("Age"));
		col_gender.setCellValueFactory(new PropertyValueFactory<customer, String>("Gender"));
		col_nrc.setCellValueFactory(new PropertyValueFactory<customer, String>("Nrc"));
		col_passport.setCellValueFactory(new PropertyValueFactory<customer, String>("Passport"));
		col_ph.setCellValueFactory(new PropertyValueFactory<customer, String>("Ph_no"));
		
		add.setDisable(false);
		update.setDisable(true);
		
	}
	private void sceneHome(ActionEvent event)throws IOException{
		Parent root = FXMLLoader.load(getClass().getResource("staffOption.fxml"));

		Scene scene2 = new Scene(root);
		
		Stage window = (Stage) btnHome.getScene().getWindow();
		window.setScene(scene2);
		window.setTitle("Staff Option");
		window.sizeToScene();
		window.centerOnScreen();
	}
	
	public boolean validtion() {
		
		String nrc = txt_nrc.getText();
		boolean valid = false;
		
		if(txt_name.getText().isEmpty() && txt_age.getText().isEmpty() && txt_nrc.getText().isEmpty() && txt_ph.getText().isEmpty() && txt_passport.getText().isEmpty()) {
			new Alert(AlertType.WARNING,"Please Fill Cusotmer's Information",ButtonType.OK).showAndWait();

		}
		
		else if(txt_name.getText().isEmpty()) {
			new Alert(AlertType.WARNING,"Please Fill Cusotmer's Name",ButtonType.OK).showAndWait();
		}else if(txt_age.getText().isEmpty()) {
			new Alert(AlertType.WARNING,"Please Fill Age",ButtonType.OK).showAndWait();

		}else if(Integer.parseInt(txt_age.getText())<18) {
			new Alert(AlertType.WARNING,"Only Customer (Over 18 years old) can make reservation",ButtonType.OK).showAndWait();

		}else if(txt_nrc.getText().isEmpty()) {
			new Alert(AlertType.WARNING,"Please Fill Cusotmer's Nrc",ButtonType.OK).showAndWait();

		}
		
		else if(!nrc.matches("\\d[1,2]/[a-zA-Z]{5,10}\\([A-Z]\\)[0-9]{6}")){
			new Alert(AlertType.ERROR,"Invalid Nrc",ButtonType.OK).showAndWait();
			
		}else if (txt_ph.getText().isEmpty()) {
			new Alert(AlertType.WARNING,"Please Fill Cusotmer's Phone Number",ButtonType.OK).showAndWait();

		}else if(txt_passport.getText().isEmpty()) {
			new Alert(AlertType.WARNING,"Please Fill Cusotmer's Passport",ButtonType.OK).showAndWait();

		}
		
		else {
			valid = true;
		}
			
		return valid;
	}
	
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		// TODO Auto-generated method stub
		refreshdata();
		ObservableList<String> listg = FXCollections.observableArrayList("Male", "Female");
		listg.add("Other");
		gender_box.setItems(listg);
		gender_box.getSelectionModel().selectFirst();
		
		gender_box.setOnMouseClicked(e -> {
		gender_box.getSelectionModel().getSelectedItem().toString();
			
		});
		
		add.setOnAction(e->{
			
			if(validtion() == true) {
				Add();
			}
		});
		
		
		customer_table.setOnMouseClicked(e -> {
			
			index = customer_table.getSelectionModel().getSelectedIndex();
			if (index <= -1) {
				return;
			}
			txt_customer.setText(col_customer.getCellData(index).toString());
			txt_name.setText(col_name.getCellData(index).toString());
			txt_age.setText(col_age.getCellData(index).toString());
			gender_box.setValue(col_gender.getCellData(index).toString());
			txt_nrc.setText(col_nrc.getCellData(index).toString());
			txt_passport.setText(col_passport.getCellData(index).toString());
			txt_ph.setText(col_ph.getCellData(index).toString());
			
			add.setDisable(true);
			update.setDisable(false);
			
			
		});
		
		btnHome.setOnAction(e->{
			try {
				sceneHome(e);
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		});
		
	}

}
