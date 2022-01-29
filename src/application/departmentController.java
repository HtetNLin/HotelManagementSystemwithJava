package application;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ResourceBundle;

import Staff.reservation;
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
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

public class departmentController implements Initializable {
	

    @FXML
    private Button add;

    @FXML
    private Button update;

    @FXML
    private Button refresh;

    @FXML
    private Label txt_id;

    @FXML
    private TextField txt_name;

    @FXML
    private TableView<department> table_department;

    @FXML
    private TableColumn<department, Integer> col_id;

    @FXML
    private TableColumn<department, String> col_name;

    @FXML
    private Button btnHome;
    
  	ObservableList<department> listM;

  	int index = -1;
  	
  	int I_D = 0;

  	static Connection conn = null;
  	ResultSet rs = null;
  	PreparedStatement pst = null;
  	
	
	public void Add() {
		conn = Room_DB.ConnectDb();
		String sql = "insert into employee_department(department_id,department_name)values(?,?)";
		try {
			pst = conn.prepareStatement(sql);
			pst.setInt(1, I_D);
			pst.setString(2, txt_name.getText());
			pst.execute();
			new Alert(AlertType.INFORMATION,"Department added successfully",ButtonType.OK).showAndWait();
			refreshdata();
		}catch (NumberFormatException e1) {
			new Alert(AlertType.ERROR,"Invalid Input",ButtonType.OK).showAndWait();
		}
		catch (Exception e) {
			new Alert(AlertType.ERROR,e+"",ButtonType.OK).showAndWait();
		}
	}
	
	public void update() {
		try {
			conn = Room_DB.ConnectDb();
			int idvalue = I_D-1;
			String departmentname = txt_name.getText();
			
			String sql = "update employee_department set department_name= '" + departmentname + "'where department_id='" + idvalue + "' ";
			pst = conn.prepareStatement(sql);
			pst.execute();
			new Alert(AlertType.INFORMATION,"Update Successfully",ButtonType.OK).showAndWait();
			refreshdata();
		}catch (NumberFormatException e1) {
			new Alert(AlertType.ERROR,"Invalid Input",ButtonType.OK).showAndWait();
		} 
		catch (Exception e) {
			new Alert(AlertType.ERROR,""+e,ButtonType.OK).showAndWait();
		}
	}
	

	public void delete() {
		try {
			conn = Room_DB.ConnectDb();
			int idvalue =I_D-1;
			String sql = "delete from employee_department where department_id=" + idvalue;
			pst = conn.prepareStatement(sql);
			pst.execute();
			new Alert(AlertType.INFORMATION,"Delete successfully",ButtonType.OK).showAndWait();
			refreshdata();
		} catch (Exception e) {
			new Alert(AlertType.ERROR,""+e,ButtonType.OK).showAndWait();
		}
	}


	public void refreshdata() {
		I_D = Main.ID_INC("employee_department","department");
		txt_id.setText(I_D+"");
		
		add.setDisable(false);
		update.setDisable(true);
	

		col_id.setCellValueFactory(new PropertyValueFactory<department, Integer>("d_id"));
		col_name.setCellValueFactory(new PropertyValueFactory<department,String>("d_name"));
		
		listM = Department_DB.getDatarooms();
		table_department.setItems(listM);
	}
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		// TODO Auto-generated method stub
		refreshdata();
		
		
		
		add.setOnAction(e->{
			if(txt_name.getText().isEmpty()) {
				new Alert(AlertType.WARNING,"Please Fill Department Name",ButtonType.OK).showAndWait();

			}else {
				Add();
			}
		});
		
		table_department.setOnMouseClicked(e -> {
			index = table_department.getSelectionModel().getSelectedIndex();
			if (index <= -1) {
				return;
			}
			txt_id.setText(col_id.getCellData(index).toString());
			txt_name.setText(col_name.getCellData(index).toString());
			
			add.setDisable(true);
			update.setDisable(false);
		});
		

		btnHome.setOnAction(e->{
			try {
				sceneSwitch(e);
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		});
		
	}
	
	private void sceneSwitch(ActionEvent event)throws IOException{
		Parent root = FXMLLoader.load(getClass().getResource("adminOption.fxml"));

		Scene scene2 = new Scene(root);
		
		Stage window = (Stage) btnHome.getScene().getWindow();
		window.setScene(scene2);
		window.setTitle("Admin Option");
		window.sizeToScene();
		window.centerOnScreen();
	}

}
