package application;


import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.time.LocalDate;
import java.time.Period;
import java.util.ResourceBundle;

import Staff.Reservation_DB;
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
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

public class employeeController implements Initializable {
	@FXML
    private Label txt_employee;

	  @FXML
	  private ComboBox<String> department_box;

    @FXML
    private TextField txt_name;

    @FXML
    private TextField txt_age;

    @FXML
    private TextField txt_nrc;

    @FXML
    private DatePicker dp_dob;

    @FXML
    private TextField txt_ph;

    @FXML
    private ComboBox<String> gender_box;

    @FXML
    private DatePicker dp_startdate;

    @FXML
    private TextField txt_salary;
    
    @FXML
    private TableView<employee> employee_table;


    @FXML
    private TableColumn<employee, Integer> col_employee;

    @FXML
    private TableColumn<employee, Integer> col_department;

    @FXML
    private TableColumn<employee, String> col_name;

    @FXML
    private TableColumn<employee, Integer> col_age;

    @FXML
    private TableColumn<employee, String> col_nrc;

    @FXML
    private TableColumn<employee, String> col_dob;

    @FXML
    private TableColumn<employee, String> col_ph;

    @FXML
    private TableColumn<employee, String> col_gender;

    @FXML
    private TableColumn<employee, String> col_startdate;

    @FXML
    private TableColumn<employee, String> col_salary;

    @FXML
    private Button add;

    @FXML
    private Button update;

    @FXML
    private Button delete;

    @FXML
    private Button refresh;
    
    @FXML
    private Button btnHome;
    
	ObservableList<employee> listM;
	ObservableList<String> listd;

	int index = -1;
	int I_D = 0;

	static Connection conn = null;
	ResultSet rs = null;
	PreparedStatement pst = null;
	
	
	public void Add() {
		String sql = "insert into employee (employee_id,department_id,name,age,nrc,dob,ph_no,gender,start_date,salary)values(?,?,?,?,?,?,?,?,?,?)";

			try {
				conn = Employee_DB.ConnectDb();
				pst = conn.prepareStatement(sql);
				pst.setInt(1, I_D);
				pst.setInt(2, Integer.parseInt(department_box.getValue().toString()));
				pst.setString(3, txt_name.getText());
				pst.setInt(4, Integer.parseInt(txt_age.getText()));
				pst.setString(5, txt_nrc.getText());
				pst.setString(6, dp_dob.getValue().toString());
				pst.setString(7, txt_ph.getText());
				pst.setString(8, gender_box.getValue().toString());
				pst.setString(9, dp_startdate.getValue().toString());
				pst.setDouble(10,Double.parseDouble(txt_salary.getText()));
				pst.execute();
				new Alert(AlertType.INFORMATION,"Successfully added",ButtonType.OK).showAndWait();
				refreshdata();
			} catch (Exception e) {
				new Alert(AlertType.ERROR,"Invalid Input"+e,ButtonType.OK).showAndWait();
			}
	}
	
	public void Update() {
		String sql = "update employee set employee_id = '" + Integer.parseInt(txt_employee.getText()) + "',department_id= '" +Integer.parseInt(department_box.getValue().toString()) + "',name= '" + txt_name.getText()
				+ "',age = '"+ Integer.parseInt(txt_age.getText()) +"',nrc= '"+txt_nrc.getText() +"',dob= '"+dp_dob.getValue().toString()+"',ph_no= '"+txt_ph.getText()+"',gender= '"+gender_box.getValue().toString()
				+"',start_date= '"+dp_startdate.getValue().toString()+"',salary= '"+Double.parseDouble(txt_salary.getText()) + "'where employee_id='" + txt_employee.getText() + "' ";	
		
		try {
			conn = Employee_DB.ConnectDb();
			pst = conn.prepareStatement(sql);
			pst.execute();
			
			new Alert(AlertType.INFORMATION,"Successfully updated",ButtonType.OK).showAndWait();
			System.out.println("good");
			refreshdata();
			
		}catch(Exception e) {
			new Alert(AlertType.ERROR,"Invalidinput",ButtonType.OK).showAndWait();
		}
	
	}
	
	public void Delete() {
		String sql = "delete from employee where employee_id='" + txt_employee.getText() + "' ";
		
		try {
			conn = Employee_DB.ConnectDb();
			pst = conn.prepareStatement(sql);
			pst.execute();
			new Alert(AlertType.INFORMATION,"Successfully Deleted",ButtonType.OK).showAndWait();
			refreshdata();
			
		} catch (Exception e) {
			new Alert(AlertType.ERROR,""+e,ButtonType.OK).showAndWait();
		}
	}
	
	public void refreshdata() {
		listd = FXCollections.observableArrayList("Department Id");
		department_box.setItems(listd);
		department_box.getSelectionModel().selectFirst();

		department_box.setOnMouseClicked(e -> {
			department_box.getSelectionModel().getSelectedItem().toString();

		});
		I_D = Main.ID_INC("employee", "employee");
		txt_employee.setText(I_D+"");
		
		listM = Employee_DB.getEmployee();
		employee_table.setItems(listM);
		
		add.setDisable(false);
		update.setDisable(true);
		
		
		col_employee.setCellValueFactory(new PropertyValueFactory<employee, Integer>("e_id"));
		col_department.setCellValueFactory(new PropertyValueFactory<employee, Integer>("d_id"));
		col_name.setCellValueFactory(new PropertyValueFactory<employee, String>("Name"));
		col_age.setCellValueFactory(new PropertyValueFactory<employee, Integer>("Age"));
		col_nrc.setCellValueFactory(new PropertyValueFactory<employee, String>("Nrc"));
		col_dob.setCellValueFactory(new PropertyValueFactory<employee, String>("Dob"));
		col_ph.setCellValueFactory(new PropertyValueFactory<employee, String>("phone_no"));
		col_gender.setCellValueFactory(new PropertyValueFactory<employee, String>("Gender"));
		col_startdate.setCellValueFactory(new PropertyValueFactory<employee, String>("Start_date"));
		col_salary.setCellValueFactory(new PropertyValueFactory<employee, String>("Salary"));
		
		conn = Employee_DB.ConnectDb();
		try {

			PreparedStatement ps = (PreparedStatement) conn.prepareStatement("select department_id from employee_department");
			ResultSet rs = (ResultSet) ps.executeQuery();
			while (rs.next()) {

				listd.add(rs.getInt("department_id") + "");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private void sceneHome(ActionEvent event)throws IOException{
		Parent root = FXMLLoader.load(getClass().getResource("adminOption.fxml"));

		Scene scene2 = new Scene(root);
		
		Stage window = (Stage) btnHome.getScene().getWindow();
		window.setScene(scene2);
		window.setTitle("Admin Option");
		window.sizeToScene();
		window.centerOnScreen();
	}
	
	public boolean validation() {
		LocalDate tdy = LocalDate.now();
		LocalDate dob = dp_dob.getValue();
		LocalDate startdate = dp_startdate.getValue();	
		Period dob_age = Period.between(dob, tdy);
		Period S_date = Period.between(startdate,tdy);
		System.out.println(S_date.getYears());
		boolean valid = false;
		String nrc = txt_nrc.getText();
		
		
		
		
		
		
		if(!nrc.matches("\\d[1,2]/[a-zA-Z]{5,10}\\([A-Z]\\)[0-9]{6}")){
			new Alert(AlertType.ERROR,"Invalid Nrc",ButtonType.OK).showAndWait();
			
		}else if(dob_age.getYears()<18 || (dob_age.getYears() != Integer.parseInt(txt_age.getText()))) {
			new Alert(AlertType.ERROR,"Invalid Age",ButtonType.OK).showAndWait();
			
			
		}else if(startdate.isAfter(LocalDate.now())){
			
			new Alert(AlertType.ERROR,"Invalid Start Date",ButtonType.OK).showAndWait();
		}else {
			valid =  true;
		}
		
		return valid;
	}

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		// TODO Auto-generated method stub	
		refreshdata();
		ObservableList<String> listg = FXCollections.observableArrayList("Male", "Female");
		gender_box.setItems(listg);
		gender_box.getSelectionModel().selectFirst();
		
		gender_box.setOnMouseClicked(e -> {
		gender_box.getSelectionModel().getSelectedItem().toString();
			
		});
		
		update.setOnAction(e->{
			try {
				double sal = Double.parseDouble(txt_salary.getText());
				
				if(validation() == true) {
					Update();
				}
			}catch(Exception e1) {
				new Alert(AlertType.ERROR,"Invalidnput",ButtonType.OK).showAndWait();
			}
		});
		
		add.setOnAction(e->{
			if(txt_name.getText().isEmpty()&& txt_age.getText().isEmpty() && txt_nrc.getText().isEmpty() && dp_dob.getValue() == null && txt_ph.getText().isEmpty() && dp_startdate.getValue() == null && txt_salary.getText().isEmpty()) {
				
				new Alert(AlertType.WARNING,"Please Fill Employee Information",ButtonType.OK).showAndWait();
			
			}else if(department_box.getValue() == "Department Id") {
					new Alert(AlertType.WARNING,"Please Select Department Id",ButtonType.OK).showAndWait();
			}else if(txt_name.getText().isEmpty()) {
				new Alert(AlertType.WARNING,"Please Fill Name",ButtonType.OK).showAndWait();

			}else if(txt_age.getText().isEmpty()) {
				new Alert(AlertType.WARNING,"Please Fill Age",ButtonType.OK).showAndWait();

			}else if(txt_nrc.getText().isEmpty()) {
				new Alert(AlertType.WARNING,"Please Fill Nrc",ButtonType.OK).showAndWait();

			}else if(dp_dob.getValue()==null) {
				new Alert(AlertType.WARNING,"Please Select Date Of Birth",ButtonType.OK).showAndWait();

			}else if(txt_ph.getText().isEmpty()){
				new Alert(AlertType.WARNING,"Please Fill Phone Number",ButtonType.OK).showAndWait();

			}else if(dp_startdate.getValue()==null){
				new Alert(AlertType.WARNING,"Please Select Date",ButtonType.OK).showAndWait();

			}else if(txt_salary.getText().isEmpty()) {
				new Alert(AlertType.WARNING,"Please Fill Salary",ButtonType.OK).showAndWait();

			}else {

				if(validation()==true) {
					Add();
				}else {
					
				}
			}
		});
		
		
		employee_table.setOnMouseClicked(e -> {
			
			index = employee_table.getSelectionModel().getSelectedIndex();
			if (index <= -1) {
				return;
			}
			
			String salary = col_salary.getCellData(index).toString();
			if(salary.contains("$")) {
				salary = Main.charRemoveAt(salary, salary.indexOf("$"));
			}
			
			txt_employee.setText(col_employee.getCellData(index).toString());
			department_box.setValue(col_department.getCellData(index).toString());
			txt_name.setText(col_name.getCellData(index).toString());
			txt_age.setText(col_age.getCellData(index).toString());
			txt_nrc.setText(col_nrc.getCellData(index).toString());
			dp_dob.setValue(LocalDate.parse(col_dob.getCellData(index).toString()));
			txt_ph.setText(col_ph.getCellData(index).toString());
			dp_startdate.setValue(LocalDate.parse(col_startdate.getCellData(index).toString()));
			txt_salary.setText(salary);
			gender_box.setValue(col_gender.getCellData(index).toString());
			
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
