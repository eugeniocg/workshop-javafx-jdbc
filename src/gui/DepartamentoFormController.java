package gui;

import java.net.URL;
import java.util.ResourceBundle;

import db.DbException;
import gui.util.Alerts;
import gui.util.Constraints;
import gui.util.utils;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import modelo.entidades.Departamento;
import modelo.servicos.DepartamentoServico;

public class DepartamentoFormController implements Initializable {
	
	private Departamento entidade; 
	
	private DepartamentoServico servico;
	
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
	
	public void setDepartamentoServico(DepartamentoServico servico) {
		this.servico = servico;
	}
	
	@FXML
	public void onBtSalvaAction(ActionEvent evento) {
		if (entidade == null) {
			throw new IllegalStateException("Entidade Nula");
		}
		if (servico == null) {
			throw new IllegalStateException("Servicoe Nulo");
		}
		try {
			entidade = getFormData();
			servico.saveOrUpdate(entidade);
			utils.currentStage(evento).close();
		}
		catch(DbException ex) {
			Alerts.showAlert("Erro ao Salvar", null, ex.getMessage(), AlertType.ERROR);
		}
	}

	private Departamento getFormData() {
		Departamento obj = new Departamento();
		
		obj.setId(utils.tryParseToInt(txtId.getText()));
		obj.setNome(txtNome.getText());
		return obj;
	}

	public void onBTCancelaAction(ActionEvent evento) {
		utils.currentStage(evento).close();
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
