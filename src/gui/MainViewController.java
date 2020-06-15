package gui;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import application.Main;
import gui.util.Alerts;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.MenuItem;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.VBox;
import model.services.DepartamentoServico;

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
		loadView2("/gui/DepartamentoLista.fxml");
	}

	@FXML
	public void onMenuItemAboutAction() {
		loadView("/gui/About.fxml");
	}

	private synchronized void  loadView(String absoluteNome) {
		try {
			FXMLLoader loader = new FXMLLoader(getClass().getResource(absoluteNome));
			VBox newVBox = loader.load();
			
			Scene mainScene = Main.getMainScene();
			VBox mainVBox =(VBox)((ScrollPane) mainScene.getRoot()).getContent();
			
			Node mainMenu = mainVBox.getChildren().get(0);
			mainVBox.getChildren().clear();
			mainVBox.getChildren().add(mainMenu);
			mainVBox.getChildren().addAll(newVBox.getChildren());
			
		} catch (IOException ex) {
			Alerts.showAlert("Excessão", "Erro na Abertura", ex.getMessage(), AlertType.ERROR);
		}
	}

	@Override
	public void initialize(URL url, ResourceBundle rb) {
	}
	
	private synchronized void  loadView2(String absoluteNome) {
		try {
			FXMLLoader loader = new FXMLLoader(getClass().getResource(absoluteNome));
			VBox newVBox = loader.load();
			
			Scene mainScene = Main.getMainScene();
			VBox mainVBox =(VBox)((ScrollPane) mainScene.getRoot()).getContent();
			
			Node mainMenu = mainVBox.getChildren().get(0);
			mainVBox.getChildren().clear();
			mainVBox.getChildren().add(mainMenu);
			mainVBox.getChildren().addAll(newVBox.getChildren());
			
			DepartamentoListaController controller = loader.getController();
			controller.setDepartamentoServico(new DepartamentoServico());
			controller.updateTableView();
		}
		catch (IOException ex) {
			Alerts.showAlert("Excessão", "Erro na Abertura", ex.getMessage(), AlertType.ERROR);
		}
	}

}
