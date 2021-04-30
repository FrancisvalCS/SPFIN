package gui;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import application.Main;
import dao.UsuarioDAO;
import entities.Usuario;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

public class CadastroViewController implements Initializable{
	private UsuarioDAO usrDao;
	
	EntityManagerFactory emf = Persistence.createEntityManagerFactory("gpfin");
	EntityManager em = emf.createEntityManager();
	
	@FXML
	private TextField userName;
	
	@FXML
	private PasswordField passOne;
	
	@FXML
	private PasswordField passTwo;
	
	@FXML
	private Label msgAlert;
	
	@FXML
	private Button cancela;
	
	@FXML
	private Button cadastra;
	
	

	public void initialize(URL url, ResourceBundle rb) {
	}
		
	@FXML
	public void cancelaInsercao() {
		 Stage stage = (Stage) cancela.getScene().getWindow(); //Obtendo a janela atual
		 stage.close();
		 Main.getPrimaryStage().show();
	}
	
	@FXML
	public void cadastrarUsuario() throws IOException {
		String name = userName.getText();
		String senha = passOne.getText();
		String valid = passTwo.getText();
		
		if(name == null || name.trim().equals("")) {
			msgAlert.setText("Campo nome vazio!");
		}else if(senha == null || senha.trim().equals("")) {
			msgAlert.setText("Campo senha vazio!");
		}else if(valid == null || valid.trim().equals("")) {
			msgAlert.setText("Repita a senha!");
		}else if(valid.trim().equals(senha)){
				Usuario usr = new Usuario();
				usrDao = new UsuarioDAO();

				Stage msgStage = new Stage();
				Scene msgScene;
				
				FXMLLoader msgLoad = new FXMLLoader(getClass().getResource("/gui/alerts/AlertConfirm.fxml"));
				Pane pane = msgLoad.load();
		
				usr.setNome(name);
				usr.setSenha(senha);
				
				usrDao.persist(usr);
				
				msgAlert.setText("");
				userName.clear();
				passOne.clear();
				passTwo.clear();
				
				msgScene = new Scene(pane);
				msgStage.setScene(msgScene);
				msgStage.setTitle("GPFIN");
				msgStage.setResizable(false);
				msgStage.show();
				
		}else {
				msgAlert.setText("Senhas não conferem!");
		}
		

	}
	
}
