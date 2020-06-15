package modelo.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import db.DB;
import db.DbException;
import modelo.dao.DepartamentoDao;
import modelo.entidades.Departamento;

public class DepartamentoDaoJDBC implements DepartamentoDao {

	private Connection conexao;

	public DepartamentoDaoJDBC(Connection conexao) {
		this.conexao = conexao;
	}

	@Override
	public void insert(Departamento objeto) {
		PreparedStatement st = null;
		try {
			st = conexao.prepareStatement("INSERT INTO departamentos (Nome) VALUES (?)",Statement.RETURN_GENERATED_KEYS);
			
			st.setString(1, objeto.getNome());
						
			int linhasAfetadas=st.executeUpdate();
			
			if(linhasAfetadas>0) {
				ResultSet rs =st.getGeneratedKeys();
				if(rs.next()) {
					int id = rs.getInt(1);
					objeto.setId(id);
				}
				DB.fechaResultSet(rs);
			}
			else {
				throw new DbException("Erro inesperado, nenhuma linha foi incluída");
			}
		} 
		catch(SQLException ex){
			throw new DbException(ex.getMessage());
			
		}finally {
			DB.fechaStatement(st);
		}
	}

	@Override
	public void update(Departamento objeto) {
		PreparedStatement st = null;
		try {
			st = conexao.prepareStatement("UPDATE departamentos  SET Nome=? WHERE Id=?");
			
			st.setString(1, objeto.getNome());
			st.setInt(2, objeto.getId());
			
			st.executeUpdate();
			
		} 
		catch(SQLException ex){
			throw new DbException(ex.getMessage());
			
		}finally {
			DB.fechaStatement(st);
		}
	}

	@Override
	public void deleteById(Integer id) {
		PreparedStatement st = null;
		try {
			st = conexao.prepareStatement("DELETE FROM departamentos WHERE Id = ?");
			
			st.setInt(1, id);
			int linha = st.executeUpdate();
			if (linha==0) {
				System.out.println("Código Inexistente");
			}
			else {
				System.out.println("Código foi Excluído com SUCESSO!");
			}
		}
		catch(SQLException ex) {
			throw new DbException(ex.getMessage());
		}
		finally {
			DB.fechaStatement(st);
		}

	}

	@Override
	public Departamento findById(Integer id) {
		PreparedStatement st = null;
		ResultSet rs = null;
		try {
			st = conexao.prepareStatement("SELECT * FROM departamentos WHERE departamentos.Id = ?");

			st.setInt(1, id);
			rs = st.executeQuery();
			
			if (rs.next()) {
				Departamento dep = new Departamento();	
				dep.setId(rs.getInt("Id"));
				dep.setNome(rs.getString("Nome"));
				return dep;
			}
			return null;
		} catch (SQLException ex) {
			throw new DbException(ex.getMessage());
		} finally {
			DB.fechaStatement(st);
			DB.fechaResultSet(rs);
		}
	}

	
	@Override
	public List<Departamento> findAll() {
		PreparedStatement st = null;
		ResultSet rs = null;
		try {
			st = conexao.prepareStatement("SELECT * FROM departamentos ORDER BY Nome");

			rs = st.executeQuery();

			List<Departamento> lista = new ArrayList<>();
			
			while (rs.next()) {
		        Departamento dep = new Departamento();
		        dep.setId(rs.getInt("Id"));
		        dep.setNome(rs.getString("Nome"));
				lista.add(dep);
			}
			return lista;
		} catch (SQLException ex) {
			throw new DbException(ex.getMessage());
		} finally {
			DB.fechaStatement(st);
			DB.fechaResultSet(rs);
		}
	}

}
