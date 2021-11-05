package application;

import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class RegController implements Initializable 
{
	@FXML Button btnReg,btnCancel,btnClose;
	@FXML TextField txtId,txtSnum,txtName,txtPhone,txtMajor,txtAddress;
	@FXML PasswordField txtPwd1,txtPwd2;
	public static DBtest db;
	public static Connection conn;
	
	@Override
	
	public void initialize(URL location, ResourceBundle resources) 
	{
		conn = db.dbconnect();
	}
	
	public void btnRegAction(ActionEvent event)
	{
		String sql = "INSERT INTO SMEMBER "
				+ "(USERID, USERPWD, UNAME, UNUMBER, UPHONE, UMAJOR, UTYPE, UADDRESS) "
				+ "VALUES "
				+ "(?,?,?,?,?,?,?,?)";
		
		
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
				ps.setString(6, txtMajor.getText());
				ps.setString(7, "Null");
				ps.setString(8, txtAddress.getText());
				
				ResultSet rs = ps.executeQuery();
				
				while(rs.next())
				{
					System.out.println(rs.getInt(1) + " " + rs.getString(2));
				}
			}
			else
			{
				System.out.println("오류가 존재해서 데이터 넣기 불가.");
			}
			Cancel();
		}
		catch(Exception e)
		{
			
		}
	}
	public void btnCancelAction(ActionEvent event)
	{
		Cancel();
	}
	public void btnCloseAction(ActionEvent event)
	{
		Stage stage= (Stage) btnClose.getScene().getWindow();
		stage.close();
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
		txtMajor.setText("");
		txtAddress.setText("");
		txtPwd1.setText("");
		txtPwd2.setText("");
    }
}