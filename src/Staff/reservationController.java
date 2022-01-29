package Staff;

import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.ResourceBundle;

import javax.swing.JOptionPane;

import org.omg.DynamicAny.DynAnySeqHelper;

import java.sql.*;
import com.sun.org.apache.bcel.internal.generic.GETSTATIC;

import application.Main;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TablePosition;
import javafx.scene.control.TableRow;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import jdk.net.NetworkPermission;

public class reservationController implements Initializable {

	@FXML
	private ComboBox<String> booking_status_box;

	@FXML
	private ComboBox<String> room_box;

	 @FXML
	 private ComboBox<String> employee_box;

	@FXML
	private TextField txt_resID;

	 @FXML
	private ComboBox<String> customer_box;

	@FXML
	private DatePicker txt_check_in_date;

	@FXML
	private DatePicker txt_check_out_date;

	@FXML
	private Label txt_reservation_cost;

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

	@FXML
	private Button btn_cost;

	@FXML
	private TableView<reservation> reservation_table;

	@FXML
	private TableColumn<reservation, Integer> col_resID;

	@FXML
	private TableColumn<reservation, Integer> Col_eID;

	@FXML
	private TableColumn<reservation, Integer> Col_cID;

	@FXML
	private TableColumn<reservation, Integer> Col_roomID;

	@FXML
	private TableColumn<reservation, String> col_check_in_date;

	@FXML
	private TableColumn<reservation, String> col_check_out_date;

	@FXML
	private TableColumn<reservation, String> col_booking_status;

	@FXML
	private TableColumn<reservation, String> col_reservation_cost;
	@FXML

	ObservableList<reservation> listM;
	ObservableList<String> listr;
	ObservableList<String> listc;
	ObservableList<String> liste;
	int index = -1;

	Connection conn = null;
	ResultSet rs = null;
	PreparedStatement pst = null;

	public void Add_User() {
		conn = Reservation_DB.ConnectDb();
		String sql = "insert into reservation (reservation_id,employee_id,customer_id,room_id,check_in_date,check_out_date,booking_status,cost)values(?,?,?,?,?,?,?,?)";
		try {
			pst = (PreparedStatement) conn.prepareStatement(sql);
			pst.setInt(1, Integer.parseInt(txt_resID.getText()));
			pst.setInt(2, Integer.parseInt(employee_box.getValue().toString()));
			pst.setInt(3, Integer.parseInt(customer_box.getValue().toString()));
			pst.setInt(4, Integer.parseInt(room_box.getValue().toString()));
			pst.setString(5, txt_check_in_date.getValue().toString());
			pst.setString(6, txt_check_out_date.getValue().toString());
			pst.setString(7, booking_status_box.getValue().toString());
			pst.setString(8, txt_reservation_cost.getText());
			pst.execute();

			new Alert(AlertType.INFORMATION, "Successfully added", ButtonType.OK).showAndWait();
			refreshdata();
		} catch (Exception e) {
			new Alert(AlertType.ERROR, "" + e, ButtonType.OK).showAndWait();
		}
	}

	public void Edit() {
		try {
			conn = Reservation_DB.ConnectDb();
			int value1 = Integer.parseInt(txt_resID.getText());
			int value2 = Integer.parseInt(employee_box.getValue().toString());
			int value3 = Integer.parseInt(customer_box.getValue().toString());
			int value4 = Integer.parseInt(room_box.getValue().toString());
			String value5 = txt_check_in_date.getValue().toString();
			String value6 = txt_check_out_date.getValue().toString();
			String value7 = booking_status_box.getValue().toString();
			String value8 = txt_reservation_cost.getText();

			String sql = "update reservation set reservation_id='" + value1 + "',employee_id= '" + value2
					+ "',customer_id= '" + value3 + "',room_id= '" + value4 + "',check_in_date= '" + value5
					+ "',check_out_date= '" + value6 + "',booking_status= '" + value7 + "',cost = '" + value8
					+ "', where reservation_id='" + value1 + "' ";
			pst = (PreparedStatement) conn.prepareStatement(sql);
			pst.execute();
			new Alert(AlertType.INFORMATION, "Update Successfully", ButtonType.OK).showAndWait();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void delete() {
		try {
			conn = Reservation_DB.ConnectDb();
			String value = txt_resID.getText();
			String sql = "delete from reservation where reservation_id=" + value;
			pst = (PreparedStatement) conn.prepareStatement(sql);
			pst.execute();
			new Alert(AlertType.INFORMATION,"Delete Successfully",ButtonType.OK).showAndWait();
			refreshdata();
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, e);

		}
	}

	public void refreshdata() {

		listr = FXCollections.observableArrayList("Room Id");
		room_box.setItems(listr);
		room_box.getSelectionModel().selectFirst();

		room_box.setOnMouseClicked(e -> {
			room_box.getSelectionModel().getSelectedItem().toString();

		});
		
		listc = FXCollections.observableArrayList("Customer Id");
		customer_box.setItems(listc);
		customer_box.getSelectionModel().selectFirst();
		
		customer_box.setOnMouseClicked(e -> {
			customer_box.getSelectionModel().getSelectedItem().toString();

		});
		
		liste = FXCollections.observableArrayList("Employee Id");
		employee_box.setItems(liste);
		employee_box.getSelectionModel().selectFirst();
		
		employee_box.setOnMouseClicked(e -> {
			employee_box.getSelectionModel().getSelectedItem().toString();
			
		});
		

		col_resID.setCellValueFactory(new PropertyValueFactory<reservation, Integer>("res_id"));
		Col_eID.setCellValueFactory(new PropertyValueFactory<reservation, Integer>("e_id"));
		Col_cID.setCellValueFactory(new PropertyValueFactory<reservation, Integer>("c_id"));
		Col_roomID.setCellValueFactory(new PropertyValueFactory<reservation, Integer>("r_id"));
		col_check_in_date.setCellValueFactory(new PropertyValueFactory<reservation, String>("Check_in_date"));
		col_check_out_date.setCellValueFactory(new PropertyValueFactory<reservation, String>("Check_out_date"));
		col_booking_status.setCellValueFactory(new PropertyValueFactory<reservation, String>("Booking_status"));
		col_reservation_cost.setCellValueFactory(new PropertyValueFactory<reservation, String>("Reservation_cost"));

		listM = Reservation_DB.getDatausers();
		reservation_table.setItems(listM);

		conn = Reservation_DB.ConnectDb();
		try {

			PreparedStatement ps = (PreparedStatement) conn.prepareStatement("select room_id from room");
			ResultSet rs = (ResultSet) ps.executeQuery();
			while (rs.next()) {

				listr.add(rs.getInt("room_id") + "");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		try {
			
			PreparedStatement ps = (PreparedStatement) conn.prepareStatement("select customer_id from customer");
			ResultSet rs = (ResultSet) ps.executeQuery();
			while (rs.next()) {
				
				listc.add(rs.getInt("customer_id") + "");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		try {
			
			PreparedStatement ps = (PreparedStatement) conn.prepareStatement("select employee_id from employee");
			ResultSet rs = (ResultSet) ps.executeQuery();
			while (rs.next()) {
				
				liste.add(rs.getInt("employee_id") + "");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		txt_reservation_cost.setText("0");
		add.setDisable(false);
		update.setDisable(true);

	}

	private void sceneHome(ActionEvent event) throws IOException {
		Parent root = FXMLLoader.load(getClass().getResource("staffOption.fxml"));

		Scene scene2 = new Scene(root);

		Stage window = (Stage) btnHome.getScene().getWindow();
		window.setScene(scene2);
		window.setTitle("Staff Option");
		window.sizeToScene();
		window.centerOnScreen();
	}

	public boolean validation() {
		
	
		
		String cin_date = txt_check_in_date.getValue().toString();
		String cout_date = txt_check_out_date.getValue().toString();
		boolean r = false;
		boolean v = false;
		conn = Reservation_DB.ConnectDb();

		try {

			PreparedStatement ps = (PreparedStatement) conn
					.prepareStatement("select room_id,check_in_date,check_out_date from reservation");
			// "select room_id,check_in_date,check_out_date from reservation where
			// check_in_date OR check_out_date BETWEEN '" + cin_date +"' AND '" + cout_date
			// + "'"
			ResultSet rs = (ResultSet) ps.executeQuery();

			while (rs.next()) {

				if (rs.getInt("room_id") == Integer.parseInt(room_box.getValue())) {
					r = true;
					LocalDate in = LocalDate.parse(rs.getString("check_in_date"));
					LocalDate out = LocalDate.parse(rs.getString("check_out_date"));
					if ((LocalDate.parse(cin_date).isBefore(in) == true
							&& ((LocalDate.parse(cout_date).isBefore(in) == true)
									|| LocalDate.parse(cout_date).equals(in)))
							|| ((LocalDate.parse(cin_date).isAfter(out) == true
									|| LocalDate.parse(cin_date).equals(out))
									&& LocalDate.parse(cout_date).isAfter(out) == true)) {
						v = true;

					} else {
						v = false;
					}
				} else {
					r = false;
					v = false;

				}
				System.out.println(r + "" + v);
				if (r == true && v == false) {
					System.out.println("break");
					break;
				}
			}
			System.out.println("............................");
			if (r == false && v == false) {
				v = true;
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		return v;

	}

	public double caculate_cost() {
		String cin_date = txt_check_in_date.getValue().toString();
		String cout_date = txt_check_out_date.getValue().toString();
		int room_id = Integer.parseInt(room_box.getValue());
		double room_price = 0;
		double cost = 0;
		long temp = 0;
		conn = Reservation_DB.ConnectDb();
		try {

			PreparedStatement ps = (PreparedStatement) conn.prepareStatement("select price from room where room_id = '" + room_id + "'");
			ResultSet rs = (ResultSet) ps.executeQuery();
			while (rs.next()) {
				room_price = rs.getDouble("price");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		temp = ChronoUnit.DAYS.between(LocalDate.parse(cin_date), LocalDate.parse(cout_date));
		System.out.println(temp);
		cost = room_price*temp;
		
		return cost;
		
		
	}
	@Override
	public void initialize(URL url, ResourceBundle rb) {
		refreshdata();

		ObservableList<String> listbs = FXCollections.observableArrayList("Reserved", "Booking");
		booking_status_box.setItems(listbs);
		booking_status_box.getSelectionModel().selectFirst();

		booking_status_box.setOnMouseClicked(e -> {
			booking_status_box.getSelectionModel().getSelectedItem().toString();

		});

		add.setOnAction(e -> {
			
			if(txt_resID.getText().isEmpty() && customer_box.getValue() == "Customer Id" && room_box.getValue() == "Room Id" && txt_check_in_date.getValue() == null && 
					txt_check_out_date.getValue() == null && employee_box.getValue() == "Employee Id") {
				new Alert(AlertType.WARNING,"Please Fill and Select Reservation Informationo",ButtonType.OK).showAndWait();

			}else if(txt_resID.getText().isEmpty()) {
				new Alert(AlertType.WARNING,"Please Fill Reservation Id",ButtonType.OK).showAndWait();

			}else if(customer_box.getValue() == "Customer Id") {
				new Alert(AlertType.WARNING,"Please Select Customer Id",ButtonType.OK).showAndWait();

			}else if(room_box.getValue() == "Room Id") {
				new Alert(AlertType.WARNING,"Please Select Room Id",ButtonType.OK).showAndWait();

			}else if(txt_check_in_date.getValue()==null) {
				new Alert(AlertType.WARNING,"Please Select Check In Date",ButtonType.OK).showAndWait();

			}else if(txt_check_out_date.getValue()==null) {
				new Alert(AlertType.WARNING,"Please Select Check Out Date",ButtonType.OK).showAndWait();

			}else if(employee_box.getValue() == "Employee Id") {
				new Alert(AlertType.WARNING,"Please Select Employee Id",ButtonType.OK).showAndWait();

			}else {
				if (LocalDate.parse(txt_check_in_date.getValue().toString()).isBefore(LocalDate.now())
						|| LocalDate.parse(txt_check_out_date.getValue().toString())
								.isBefore(LocalDate.parse(txt_check_in_date.getValue().toString()))) {
					new Alert(AlertType.ERROR, "Invalid Input", ButtonType.OK).showAndWait();
				} else {
					if (validation() == false) {
						new Alert(AlertType.ERROR, "Invalid Input", ButtonType.OK).showAndWait();
					} else {
						Add_User();
					}
				}
			}

			
		});

		update.setOnAction(e -> {

			if(txt_resID.getText().isEmpty() && customer_box.getValue() == "Customer Id" && room_box.getValue() == "Room Id" && txt_check_in_date.getValue() == null && 
					txt_check_out_date.getValue() == null && employee_box.getValue() == "Employee Id") {
				new Alert(AlertType.WARNING,"Please Fill and Select Reservation Informationo",ButtonType.OK).showAndWait();

			}else if(txt_resID.getText().isEmpty()) {
				new Alert(AlertType.WARNING,"Please Fill Reservation Id",ButtonType.OK).showAndWait();

			}else if(customer_box.getValue() == "Customer Id") {
				new Alert(AlertType.WARNING,"Please Select Customer Id",ButtonType.OK).showAndWait();

			}else if(room_box.getValue() == "Room Id") {
				new Alert(AlertType.WARNING,"Please Select Room Id",ButtonType.OK).showAndWait();

			}else if(txt_check_in_date.getValue()==null) {
				new Alert(AlertType.WARNING,"Please Select Check In Date",ButtonType.OK).showAndWait();

			}else if(txt_check_out_date.getValue()==null) {
				new Alert(AlertType.WARNING,"Please Select Check Out Date",ButtonType.OK).showAndWait();

			}else if(employee_box.getValue() == "Employee Id") {
				new Alert(AlertType.WARNING,"Please Select Employee Id",ButtonType.OK).showAndWait();

			}else {
				if (LocalDate.parse(txt_check_in_date.getValue().toString()).isBefore(LocalDate.now())
						|| LocalDate.parse(txt_check_out_date.getValue().toString())
								.isBefore(LocalDate.parse(txt_check_in_date.getValue().toString()))) {
					new Alert(AlertType.ERROR, "Invalid Input", ButtonType.OK).showAndWait();
				} else {
					if (validation() == false) {
						new Alert(AlertType.ERROR, "Invalid Input", ButtonType.OK).showAndWait();
					} else {
						Edit();
					}
				}
			}
			

		});

		btn_cost.setOnAction(e -> {
			try {
			if (LocalDate.parse(txt_check_in_date.getValue().toString()).isBefore(LocalDate.now())
					|| LocalDate.parse(txt_check_out_date.getValue().toString())
							.isBefore(LocalDate.parse(txt_check_in_date.getValue().toString()))) {
				new Alert(AlertType.ERROR, "Invalid Date", ButtonType.OK).showAndWait();
			} else {
					double cost = caculate_cost();
					txt_reservation_cost.setText(cost+"");
				
			}
			}catch(Exception e1) {
				new Alert(AlertType.ERROR,"Invalid Input",ButtonType.OK).showAndWait();
			}
		});

		
		delete.setOnAction(e->{
			conn = Reservation_DB.ConnectDb();
			try {
				int rid = Integer.parseInt(room_box.getValue());
				PreparedStatement ps = (PreparedStatement) conn.prepareStatement("select status from room where room_id = '" +rid+"'");
				ResultSet rs = (ResultSet) ps.executeQuery();
				while (rs.next()) {

					if(rs.getString("status") .equals("Available")) {
						delete();
					}else {
						new Alert(AlertType.WARNING,"Cannot Delete This Reservation\nThis Room is Now Reserved",ButtonType.OK).showAndWait();
					}
				}
			} catch (Exception e4) {
				e4.printStackTrace();
			}
		});
	
		reservation_table.setOnMouseClicked(e -> {

			index = reservation_table.getSelectionModel().getSelectedIndex();
			if (index <= -1) {
				return;
			}
			String price = col_reservation_cost.getCellData(index).toString();
			if(price.contains("$")) {
				price = Main.charRemoveAt(price, price.indexOf("$"));
				
			}
			txt_resID.setText(col_resID.getCellData(index).toString());
			employee_box.setValue(Col_eID.getCellData(index).toString());
			customer_box.setValue(Col_cID.getCellData(index).toString());
			room_box.setValue(Col_roomID.getCellData(index).toString());
			txt_check_in_date.setValue(LocalDate.parse(col_check_in_date.getCellData(index).toString()));
			txt_check_out_date.setValue(LocalDate.parse(col_check_out_date.getCellData(index).toString()));
			booking_status_box.setValue(col_booking_status.getCellData(index).toString());
			txt_reservation_cost.setText(price);

			add.setDisable(true);
			update.setDisable(false);

		});

		btnHome.setOnAction(e -> {
			try {
				sceneHome(e);
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		});

	}
}
