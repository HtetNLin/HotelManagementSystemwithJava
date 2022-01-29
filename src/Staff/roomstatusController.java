package Staff;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.time.LocalDate;
import java.util.ResourceBundle;

import application.Main;
import application.rooms;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;


public class roomstatusController implements Initializable {
		

    @FXML
    private Button btnHome;

    @FXML
    private TableView<rooms> table_room;

    @FXML
    private TableColumn<rooms,Integer> col_id;

    @FXML
    private TableColumn<rooms, String> col_type;

    @FXML
    private TableColumn<rooms, String> col_price;

    @FXML
    private TableColumn<rooms, String> col_status;
    
    @FXML
    private Label txt_id;
    

    @FXML
    private Label txt_table_id;
    

    @FXML
    private TableView<room_status> table_b;

    @FXML
    private TableColumn<room_status, String> col_check_in_date;

    @FXML
    private TableColumn<room_status, String> col_check_out_date;
	
    ObservableList<rooms> listM;
    ObservableList<room_status>listts;

	int index = -1;
	

	static Connection conn = null;
	ResultSet rs = null;
	PreparedStatement pst = null;
	
	public void check_status() {
		
		LocalDate cin;
		LocalDate cout;
		LocalDate tdy = LocalDate.now();
		conn = Room_DB.ConnectDb();
		try {
			PreparedStatement ps = conn.prepareStatement("select check_in_date,check_out_date from reservation");
			ResultSet rs = ps.executeQuery();
			while(rs.next()) {
				cin = LocalDate.parse(rs.getString("check_in_date"));
				cout = LocalDate.parse(rs.getString("check_out_date"));
				System.out.println(cin.toString());
				System.out.println(cout.toString());
				
				
				if(cin.equals(tdy) || tdy.isAfter(cin)==true && tdy.isBefore(cout) == true ) {
					System.out.println("..............................");
					System.out.println(cin.toString());
					System.out.println(cout.toString());

					PreparedStatement ps2 = conn.prepareStatement("select room_id from reservation where check_in_date = '"+cin.toString()+"' AND check_out_date = '" + cout.toString()+"'");
					ResultSet rs2 = ps2.executeQuery();
					while(rs2.next()) {
						
							System.out.println(rs2.getInt("room_id"));
							int rid = rs2.getInt("room_id");
							String reserved = "Reserved";
							PreparedStatement ps3 = conn.prepareStatement("update room set status = '" + reserved +"' where room_id = '"+rid+"'");
							boolean upd = ps3.execute();
							System.out.println(upd);
							
							
					}
				}
				
			}
			
		} catch (Exception e) {

		}
		
	}
	
	public void checkstatus2() {
		
		LocalDate cin;
		LocalDate cout;
		LocalDate tdy = LocalDate.now();
		conn = Room_DB.ConnectDb();
		try {
			PreparedStatement ps = conn.prepareStatement("select room_id from room where status = 'Reserved'");
			ResultSet rs = ps.executeQuery();
			while(rs.next()) {
					
					System.out.println(rs.getInt("room_id"));
					
					PreparedStatement ps2 = conn.prepareStatement("select check_out_date from reservation where room_id = '" + rs.getInt(1) + "'");
					ResultSet rs2 = ps2.executeQuery();
					
						while(rs2.next()) {
							System.out.println("haha");
						System.out.println(rs2.getString("check_out_date"));
						cout = LocalDate.parse(rs2.getString("check_out_date"));
						if(LocalDate.now().isAfter(cout)) {
							System.out.println("yes");
							int rid = rs.getInt("room_id");
							String ava = "Available";
							PreparedStatement ps3 = conn.prepareStatement("update room set status = '" + ava +"' where room_id = '"+rid+"'");
							boolean upd = ps3.execute();
							System.out.println(upd);
						
						}
						
					
				}
				
			}
			
		} catch (Exception e) {
			
		}
	}

	public void refreshdata() { 
		
		check_status();
		checkstatus2();
		
		col_id.setCellValueFactory(new PropertyValueFactory<rooms, Integer>("id"));
		col_type.setCellValueFactory(new PropertyValueFactory<rooms, String>("type"));
		col_price.setCellValueFactory(new PropertyValueFactory<rooms, String>("price"));
		col_status.setCellValueFactory(new PropertyValueFactory<rooms, String>("status"));

		listM = Room_DB.getDatarooms();
		table_room.setItems(listM);
		
		
		
		
	}

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		// TODO Auto-generated method stub
		
		refreshdata();
		
		
		btnHome.setOnAction(e->{
			System.out.println("Hello");
			try {
				sceneSwitch(e);
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		});
		
		table_room.setOnMouseClicked(e -> {
			index = table_room.getSelectionModel().getSelectedIndex();
			if (index <= -1) {
				return;
			}
			
			
			txt_id.setText("Room no - " + String.format("%03d", Integer.parseInt(col_id.getCellData(index).toString())));
			txt_table_id.setText((col_id.getCellData(index).toString()));
			

			col_check_in_date.setCellValueFactory(new PropertyValueFactory<room_status,String>("cin"));
			col_check_out_date.setCellValueFactory(new PropertyValueFactory<room_status,String>("cout"));
			
			
			listts=Room_DB.getBooking(Integer.parseInt(txt_table_id.getText()));
			table_b.setItems(listts);
		});
	}

	private void sceneSwitch(ActionEvent event)throws IOException{
		Parent root = FXMLLoader.load(getClass().getResource("staffOption.fxml"));

		Scene scene2 = new Scene(root);
		
		Stage window = (Stage) btnHome.getScene().getWindow();
		window.setScene(scene2);
		window.setTitle("Staff Option");
		window.sizeToScene();
		window.centerOnScreen();
	}
		
		
	}


