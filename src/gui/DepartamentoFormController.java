package gui;

import java.net.URL;
import java.util.ResourceBundle;

import gui.util.Constraints;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import modelo.entidades.Departamento;

public class DepartamentoFormController implements Initializable {
	
	private Departamento entidade; 
	
	@FXML
	private TextField txtId;
	
	@FXML
	private TextField txtNome;
	
	@FXML
	private Label labelErroNome;
	
	@FXML
	private Button btSalva;
	
	@FXML
	private Button btCancela;
	
	public void setDepartamento(Departamento entidade) {
		this.entidade = entidade;
	}
	
	@FXML
	public void onBtSalvaAction() {
		System.out.println("Botão Salvar");
	}

	public void onBTCancelaAction() {
		System.out.println("Botão Cancelar");
	}
	
	@Override
	public void initialize(URL url, ResourceBundle rb) {
		initializeNodes();
	}
	
	private void initializeNodes() {
		Constraints.setTextFieldInteger(txtId);
		Constraints.setTextFieldMaxLength(txtNome, 30);
	}
	
	public void updateFormData() {
		if(entidade ==null) {
			throw new IllegalStateException("Entidade nula");
		}
		txtId.setText(String.valueOf(entidade.getId()));
		txtNome.setText(entidade.getNome());
	}
	

}
