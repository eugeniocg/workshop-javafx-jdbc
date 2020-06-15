package gui;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;
import application.Main;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import model.entities.Departamento;
import model.services.DepartamentoServico;

public class DepartamentoListaController implements Initializable{

	private DepartamentoServico servico;
	
	@FXML
	private TableView<Departamento> tableViewDepartamento;
	
	@FXML
	private TableColumn<Departamento, Integer> tableColumnId;
	
	@FXML
	private TableColumn<Departamento, String> tableColumnNome;
	
	@FXML
	private Button btNovo;
	
	private ObservableList<Departamento> obsLista;
	
	@FXML
	public void onBtNovoAction() {
		System.out.println("Testando o Bot�o Novo");
	}
	
	public void setDepartamentoServico(DepartamentoServico servico) {
		this.servico=servico;
	}
	@Override
	public void initialize(URL url, ResourceBundle rb) {
		initializeNodes();
	}
	
	private void initializeNodes() {
		
		//Inicializa
		tableColumnId.setCellValueFactory(new PropertyValueFactory<>("id"));
		tableColumnNome.setCellValueFactory(new PropertyValueFactory<>("nome"));
		
		//Ajusta a janela 
		Stage stage = (Stage) Main.getMainScene().getWindow();
		tableViewDepartamento.prefHeightProperty().bind(stage.heightProperty());
	}
	
	public void updateTableView() {
		if(servico == null) {
			throw new IllegalStateException("Servi�o Nulo");
		}
		List<Departamento> lista = servico.findAll();
		obsLista =FXCollections.observableArrayList(lista);
		tableViewDepartamento.setItems(obsLista);
	}
}
