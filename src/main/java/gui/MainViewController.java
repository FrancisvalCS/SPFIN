package gui;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;



import application.Main;
import dao.UsuarioDAO;
import gui.util.Alerts;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.layout.Pane;
import javafx.scene.control.Button;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class MainViewController implements Initializable{
	
	@FXML
	private TextField user;
	
	@FXML
	private PasswordField password;
	
	@FXML 
	private Hyperlink cadastro;
	
	@FXML
	private Button enter;
	
	
	public void initialize(URL url, ResourceBundle rb) {
	}
	
	@FXML
	public void cadastroSelected() {
		loadView("/gui/CadastroView.fxml");
	}
	
	@FXML
	public void logar() throws IOException {
		String usuario = user.getText();
		String senha = password.getText();
		
		UsuarioDAO usr = new UsuarioDAO();
		usr.getUsuario(usuario, senha);
		if(usr != null) {
			Stage spfinStage = new Stage();
			Scene spfinScene;
			
			FXMLLoader msgLoad = new FXMLLoader(getClass().getResource("/gui/SpfinView.fxml"));
			Pane pane = msgLoad.load();
			
			spfinScene = new Scene(pane);
			spfinStage.setScene(spfinScene);
			spfinStage.setTitle("GPFIN");
			spfinStage.setResizable(false);
			spfinStage.show();
		} 

	}
	

	private void loadView(String content) {
		try {

			FXMLLoader cadastro = new FXMLLoader(getClass().getResource(content));
			
			Parent root = (Parent) cadastro.load();
			Stage stage = new Stage();
			stage.setTitle("GPFIN");
			stage.setResizable(false);
			stage.setScene(new Scene(root));
			Main.closeMainScene();
			stage.show();
			
		}catch(IOException e) {
			Alerts.showAlert("IOException","Eroor loadView",e.getMessage(),AlertType.ERROR);
		}
		
	}
}
