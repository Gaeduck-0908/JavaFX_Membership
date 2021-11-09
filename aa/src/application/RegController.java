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
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.PasswordField;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class RegController implements Initializable 
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
		conn = db.dbconnect();
		ChoMajor.getItems().addAll(Major);
		ChoMajor.setStyle("-fx-font: 15px \"돋움\";");
	}
	
	public void btnRegAction(ActionEvent event)
	{
		String sql = "INSERT INTO SMEMBER "
				+ "(USERID, USERPWD, UNAME, UNUMBER, UPHONE, UMAJOR, UADDRESS) "
				+ "VALUES "
				+ "(?,?,?,?,?,?,?)";
		
		
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
				ps.setString(7, Radiocheck());
				
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
