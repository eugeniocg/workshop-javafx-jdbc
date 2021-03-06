package modelo.servicos;

import java.util.List;
import modelo.dao.DepartamentoDao;
import modelo.dao.FabricaDao;
import modelo.entidades.Departamento;


public class DepartamentoServico {
	
	private DepartamentoDao dao = FabricaDao.createDepartamentoDao();

	public List<Departamento> findAll(){
		return dao.findAll();
	}
	
	public void saveOrUpdate(Departamento obj) {
		if(obj.getId()== null) {
			dao.insert(obj);
		}
		else {
			dao.update(obj);
		}
	}
}
