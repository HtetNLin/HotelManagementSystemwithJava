package application;

import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ResourceBundle;

import javax.swing.JOptionPane;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;

public class Econtrol implements Initializable{
	@FXML
    private TextField txt_employee;

    @FXML
    private TextField txt_department;

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
    private TableColumn<employee, String> col_age;

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
    private TableColumn<employee, Double> col_salary;

    @FXML
    private Button add;

    @FXML
    private Button update;

    @FXML
    private Button delete;

    @FXML
    private Button refresh;
	ObservableList<employee> listM;

	int index = -1;

	static Connection conn = null;
	ResultSet rs = null;
	PreparedStatement pst = null;

	public void Adde() {
		
		String sql = "insert into employeedetails (employee_id,department_id,name,age,nrc,dob,ph_no,gender,start_date,salary)values(?,?,?,?,?,?,?,?,?,)";
		try {
			conn = Employee_DB.ConnectDb();
			pst = conn.prepareStatement(sql);
			pst.setString(1, txt_employee.getText());
			pst.setString(2, txt_department.getText());
			pst.setString(3, txt_name.getText());
			pst.setString(4, txt_age.getText());
			pst.setString(5, txt_nrc.getText());
			pst.setString(6, dp_dob.getValue().toString());
			pst.setString(7, txt_ph.getText());
			pst.setString(8, gender_box.getValue().toString());
			pst.setString(9, dp_startdate.getValue().toString());
			pst.setString(10, txt_salary.getText());
			pst.execute();
			JOptionPane.showMessageDialog(null, "Employee added successfully!");
			refreshdata();
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, e);
		}
	}
	public void update() {
		try {
			conn = Employee_DB.ConnectDb();
			String employeevalue = txt_employee.getText();
			String departmentvalue = txt_department.getText();
			String namevalue = txt_name.getText();
			String agevalue = txt_age.getText();
			String nrcvalue = txt_nrc.getText();
			String dobvalue = dp_dob.getValue().toString();
			String phvalue = txt_ph.getText();
			String gendervalue = gender_box.getValue().toString();
			String startdatevalue = dp_startdate.getValue().toString();
			String salaryvalue = txt_salary.getText();
			String sql = "update employeedetails set employee_id = '" + employeevalue + "',department_id= '" + departmentvalue + "',name= '" + namevalue
					+ "',age = '"+ agevalue +"',nrc= '"+nrcvalue +"',dob= '"+dobvalue+"'ph_no= '"+phvalue+"'gender= '"+gendervalue+"'start_date= '"+startdatevalue+"'salary= '"+salaryvalue + "'where employee_id='" + employeevalue + "' ";
			pst = conn.prepareStatement(sql);
			pst.execute();
			JOptionPane.showMessageDialog(null, "Updated successfully!");
			refreshdata();
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, e);
		}
	}
	public void delete() {
		try {
			conn = Employee_DB.ConnectDb();
			String idvalue = txt_employee.getText();
			String sql = "delete from employeedetails where id=" + idvalue;
			pst = conn.prepareStatement(sql);
			pst.execute();
			JOptionPane.showMessageDialog(null, "deleted successfully!");
			refreshdata();
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, e);
		}
	}
	public void refreshdata() {
		col_employee.setCellValueFactory(new PropertyValueFactory<employee, Integer>("e_id"));
		col_department.setCellValueFactory(new PropertyValueFactory<employee, Integer>("d_id"));
		col_name.setCellValueFactory(new PropertyValueFactory<employee, String>("name"));
		col_age.setCellValueFactory(new PropertyValueFactory<employee, String>("age"));
		col_nrc.setCellValueFactory(new PropertyValueFactory<employee, String>("nrc"));
		col_dob.setCellValueFactory(new PropertyValueFactory<employee, String>("dob"));
		col_ph.setCellValueFactory(new PropertyValueFactory<employee, String>("ph_no"));
		col_gender.setCellValueFactory(new PropertyValueFactory<employee, String>("gender"));
		col_startdate.setCellValueFactory(new PropertyValueFactory<employee, String>("st_date"));
		col_salary.setCellValueFactory(new PropertyValueFactory<employee, Double>("salary"));
		

		listM = Employee_DB.getEmployee();
		employee_table.setItems(listM);
	}
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		Employee_DB.getEmployee();
		ObservableList<String> listg = FXCollections.observableArrayList("Male", "Female");
		gender_box.setItems(listg);
		gender_box.getSelectionModel().selectFirst();
		gender_box.setOnMouseClicked(e -> {
			gender_box.getSelectionModel().getSelectedItem().toString();
			
		});
		
		employee_table.setOnMouseClicked(e -> {
			System.out.println(employee_table.getSelectionModel().getSelectedIndex());
			index = employee_table.getSelectionModel().getSelectedIndex();
			if (index <= -1) {
				return;
			}
			txt_employee.setText(col_employee.getCellData(index).toString());
			txt_department.setText(col_department.getCellData(index).toString());
			txt_name.setText(col_name.getCellData(index).toString());
			txt_age.setText(col_age.getCellData(index).toString());
			txt_nrc.setText(col_nrc.getCellData(index).toString());
			//dp_dob.setValue(col_dob.getCellData(index).toString());
			txt_ph.setText(col_ph.getCellData(index).toString());
			//dp_startdate.setValue(col_startdate.getCellData(index).toString());
			txt_salary.setText(col_salary.getCellData(index).toString());
			gender_box.setValue(col_gender.getCellData(index).toString());
		});
		
		
	}

}
