package application;

import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ResourceBundle;
import java.util.concurrent.TimeUnit;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.PasswordField;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class Reg_eController implements Initializable 
{
	@FXML Button btnReg,btnCancel,btnClose;
	@FXML TextField txtId,txtSnum,txtName,txtPhone;
	@FXML PasswordField txtPwd1,txtPwd2;
	@FXML ChoiceBox<String> ChoMajor;
	@FXML RadioButton rdSeongnam, rdGwangju, rdHanam;
	
	public static DBtest db;
	public static Connection conn;
	
	String [] Major = {"뷰티과","부사관","금융과","회계과","창업과","스마트웹과","소프트과"};

	@Override
	public void initialize(URL location, ResourceBundle resources) 
	{
		viewMemberData();
		ChoMajor.getItems().addAll(Major);
		ChoMajor.setStyle("-fx-font: 15px \"돋움\";");
	}

	public void btnReg_eAction(ActionEvent event) 
	{

		String sql = "UPDATE SMEMBER" + " SET" + " USERID=?," + " USERPWD=?," + " UNAME=?," + " UNUMBER=?,"
				+ " UPHONE=?," + " UMAJOR=?," + " WHERE USERID=?";

		try
		{
			if(Check() == true)
			{
				PreparedStatement ps = conn.prepareStatement(sql);
				ps.setString(1, txtId.getText());
				ps.setString(2, txtPwd1.getText());
				ps.setString(3, txtName.getText());
				ps.setString(4, txtSnum.getText());
				ps.setString(5, txtPhone.getText());
				ps.setString(6, ChoMajor.getValue().toString());
				ps.setString(7, Main.global_userid);
				ps.executeUpdate();
				
				ResultSet rs = ps.executeQuery();
				Cancel();
			}
			else
			{
				Cancel();
				System.out.println("오류가 존재해서 데이터 넣기 불가.");
			}
		}
		catch(Exception e)
		{
			
		}

	}

	public void btnCloseAction(ActionEvent event) {
		try {
			Stage stage = (Stage) btnClose.getScene().getWindow();
			Parent root = FXMLLoader.load(getClass().getResource("login.fxml"));
			Scene scene = new Scene(root, 600, 400);
			scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
			stage.setTitle("회원관리PG");
			stage.setScene(scene);
			stage.show();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void btnCancelAction(ActionEvent event) {
		txtId.setText("");
		txtName.setText("");
		txtSnum.setText("");
		txtPhone.setText("");
		txtPwd1.setText("");
		txtPwd2.setText("");
	}

	public void viewMemberData() 
	{
		conn = db.dbconnect();
		System.out.println("불러옴.");
		String tmp_Id = Main.global_userid;

		String sql = "SELECT * FROM SMEMBER where USERID='" + tmp_Id + "'";
		System.out.println(sql);

		try {
			PreparedStatement ps = conn.prepareStatement(sql);
			ResultSet rs = ps.executeQuery();

			if (rs.next()) 
			{
				txtName.setText(rs.getString("UNAME"));
				txtId.setText(rs.getString("USERID"));
				txtPwd1.setText(rs.getString("USERPWD"));
				txtPwd2.setText(rs.getString("USERPWD"));
				txtSnum.setText(rs.getString("UNUMBER"));
				txtPhone.setText(rs.getString("UPHONE"));
			} 
			else 
			{
				
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
    boolean Check()
	{
	    boolean result = false;
		
		if(txtId.getText().equals("")
				|| txtPwd1.getText().equals("")
				|| txtPwd2.getText().equals("")
				|| txtSnum.getText().equals("")
				|| txtName.getText().equals(""))
		{
			result = false;
			System.out.println("오류");
		}
		else
		{
			result = true;
			System.out.println("정상");	
		}
		
		return result;
	}
    
    public void Cancel()
    {
		txtId.setText("");
		txtSnum.setText("");
		txtName.setText("");
		txtPhone.setText("");
		txtPwd1.setText("");
		txtPwd2.setText("");
    }
    
    public String Radiocheck() 
    {
    	String a ="";
        if(rdSeongnam.isSelected()) 
        {
            a = rdSeongnam.getText();
        }
        if(rdGwangju.isSelected()) 
        {
            a = rdGwangju.getText();
        }
        if(rdHanam.isSelected()) 
        {
            a = rdHanam.getText();
        }
        
        return a;
    }

}