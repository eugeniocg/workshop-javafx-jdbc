package gui;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.MenuItem;

public class MainViewController implements Initializable {
	@FXML
	private MenuItem menuItemFuncionario;
	
	@FXML
	private MenuItem menuItemDepartamento;
	
	@FXML
	private MenuItem menuItemAbout;
	
	@FXML
	public void onMenuItemFuncionarioAction() {
		System.out.println("Menu Funcionario");
	}
	
	@FXML
	public void onMenuItemDepartamentoAction() {
		System.out.println("Menu Departamento");
	}
	
	@FXML
	public void onMenuItemAboutAction() {
		System.out.println("Menu About");
	}
	
	
	
	
	@Override
	public void initialize(URL url, ResourceBundle rb) {
		// TODO Auto-generated method stub
		
	}

}
