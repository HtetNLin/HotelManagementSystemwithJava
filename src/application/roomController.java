package application;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ResourceBundle;

import javax.swing.JOptionPane;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

public class roomController implements Initializable {
	@FXML
	private Label txt_id;

	@FXML
    private ComboBox<String> type_box;

	@FXML
	private TextField txt_price;

	@FXML
	private Button add;
	
	@FXML
    private Button update;

	@FXML
	private Button refresh;

	@FXML
	private TableView<rooms> table_rooms;
	

    @FXML
    private ComboBox<String> status_box;

	@FXML
	private TableColumn<rooms, Integer> col_id;

	@FXML
	private TableColumn<rooms, String> col_type;

	@FXML
	private TableColumn<rooms, String> col_price;

	@FXML
	private TableColumn<rooms, String> col_status;

    @FXML
    private Button btnHome;

	  
	ObservableList<rooms> listM;

	int index = -1;
	
	int I_D = 0;

	static Connection conn = null;
	ResultSet rs = null;
	PreparedStatement pst = null;

	
		
	public void Add_rooms() {
		conn = Room_DB.ConnectDb();
		String sql = "insert into room (room_id,type,price,status)values(?,?,?,?)";
		try {
			pst = conn.prepareStatement(sql);
			pst.setInt(1, I_D);
			pst.setString(2, type_box.getValue().toString());
			pst.setDouble(3, Double.parseDouble(txt_price.getText()));
			pst.setString(4, status_box.getValue().toString());
			pst.execute();
			new Alert(AlertType.INFORMATION,"Rooms added successfully",ButtonType.OK).showAndWait();
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
			String typevalue = type_box.getValue().toString();
			Double pricevalue = Double.parseDouble(txt_price.getText());
			String statusvalue = status_box.getValue().toString();
			String sql = "update room set room_id = '" + idvalue + "',type= '" + typevalue + "',price= '" + pricevalue
					+ "',status = '" + statusvalue + "'where room_id='" + idvalue + "' ";
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
			String sql = "delete from room where room_id=" + idvalue;
			pst = conn.prepareStatement(sql);
			pst.execute();
			new Alert(AlertType.INFORMATION,"Delete successfully",ButtonType.OK).showAndWait();
			refreshdata();
		} catch (Exception e) {
			new Alert(AlertType.ERROR,""+e,ButtonType.OK).showAndWait();
		}
	}

	public void refreshdata() {
		I_D = Main.ID_INC("room", "room");
		txt_id.setText("Room no - " + String.format("%03d", I_D));
		
		add.setDisable(false);
		update.setDisable(true);
	

		col_id.setCellValueFactory(new PropertyValueFactory<rooms, Integer>("id"));
		col_type.setCellValueFactory(new PropertyValueFactory<rooms, String>("type"));
		col_price.setCellValueFactory(new PropertyValueFactory<rooms, String>("price"));
		col_status.setCellValueFactory(new PropertyValueFactory<rooms, String>("status"));
		
		listM = Room_DB.getDatarooms();
		table_rooms.setItems(listM);
	}

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
				refreshdata();
				
				ObservableList<String> listt = FXCollections.observableArrayList("Single room", "Double room","Triple room","Family room");
				type_box.setItems(listt);
				type_box.getSelectionModel().selectFirst();

				ObservableList<String> lists = FXCollections.observableArrayList("Available", "Reserved");
				status_box.setItems(lists);
				status_box.getSelectionModel().selectFirst();
		
			
			add.setOnAction(e->{
				if(txt_price.getText().isEmpty()) {
					new Alert(AlertType.WARNING,"Please Fill Room's Price",ButtonType.OK).showAndWait();
				}else {
					Add_rooms();
				}
			});
			
			update.setOnAction(e->{
				if(txt_price.getText().isEmpty()) {
					new Alert(AlertType.WARNING,"Please Fill Room's Price",ButtonType.OK).showAndWait();
				}else {
					update();
				}
			});
				
			table_rooms.setOnMouseClicked(e -> {
			index = table_rooms.getSelectionModel().getSelectedIndex();
			if (index <= -1) {
				return;
			}
			
			String price = col_price.getCellData(index).toString();
			if(price.contains("$")) {
				price = Main.charRemoveAt(price, price.indexOf("$"));
				
			}
			
			txt_id.setText("Room no - " + String.format("%03d", Integer.parseInt(col_id.getCellData(index).toString())));
			type_box.setValue(col_type.getCellData(index).toString());
			txt_price.setText(price);
			status_box.setValue(col_status.getCellData(index).toString());
			
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
