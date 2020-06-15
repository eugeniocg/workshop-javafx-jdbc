package modelo.dao;

import db.DB;
import modelo.dao.impl.DepartamentoDaoJDBC;
import modelo.dao.impl.FuncionarioDaoJDBC;

public class FabricaDao {

	public static FuncionarioDao createFuncionarioDao() {
		return new FuncionarioDaoJDBC(DB.abreConexao());
	}
	
	public static DepartamentoDao createDepartamentoDao() {
		return new DepartamentoDaoJDBC(DB.abreConexao());
	}
	
	
}
