package modelo.dao;

import java.util.List;
import modelo.entidades.Departamento;

public interface DepartamentoDao {

	void insert(Departamento objeto);
	void update(Departamento objeto);
	void deleteById(Integer id);
	Departamento findById(Integer id);
	List<Departamento> findAll();
}
