package modelo.dao;

import java.util.List;

import modelo.entidades.Departamento;
import modelo.entidades.Funcionario;

public interface FuncionarioDao {

	void insert(Funcionario objeto);
	void update(Funcionario objeto);
	void deleteById(Integer id);
	Funcionario findById(Integer id);
	List<Funcionario> findAll();
	List<Funcionario> findByDepartamento(Departamento departamento);
}
