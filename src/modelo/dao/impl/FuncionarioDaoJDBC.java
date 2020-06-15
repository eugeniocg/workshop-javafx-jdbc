package modelo.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import db.DB;
import db.DbException;
import modelo.dao.FuncionarioDao;
import modelo.entidades.Departamento;
import modelo.entidades.Funcionario;

public class FuncionarioDaoJDBC implements FuncionarioDao {

	private Connection conexao;

	public FuncionarioDaoJDBC(Connection conexao) {
		this.conexao = conexao;
	}

	@Override
	public void insert(Funcionario objeto) {
		PreparedStatement st = null;
		try {
			st = conexao.prepareStatement(
				"INSERT INTO funcionarios "
                +"(Nome, Email, DataNascimento, SalarioBase, IdDepartamento) "
                +"VALUES "
                +"(?, ?, ?, ?, ?)",
                Statement.RETURN_GENERATED_KEYS);
			
			st.setString(1, objeto.getNome());
			st.setString(2, objeto.getEmail());
			st.setDate(3, new java.sql.Date(objeto.getDataNascimento().getTime()));
			st.setDouble(4, objeto.getSalarioBase());
			st.setInt(5, objeto.getDepartamento().getId());
			
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
	public void update(Funcionario objeto) {
		PreparedStatement st = null;
		try {
			st = conexao.prepareStatement(
				"UPDATE funcionarios "
                +" SET Nome=?, Email=?, DataNascimento=?, SalarioBase=?, IdDepartamento=? "
                +"WHERE Id=?");
			
			st.setString(1, objeto.getNome());
			st.setString(2, objeto.getEmail());
			st.setDate(3, new java.sql.Date(objeto.getDataNascimento().getTime()));
			st.setDouble(4, objeto.getSalarioBase());
			st.setInt(5, objeto.getDepartamento().getId());
			st.setInt(6, objeto.getId());
			
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
			st = conexao.prepareStatement("DELETE FROM funcionarios WHERE Id = ?");
			
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
	public Funcionario findById(Integer id) {
		PreparedStatement st = null;
		ResultSet rs = null;
		try {
			st = conexao.prepareStatement("SELECT funcionarios.*,departamentos.Nome as DepNome "
					+ "FROM funcionarios INNER JOIN departamentos "
					+ "ON funcionarios.IdDepartamento = departamentos.Id " + "WHERE funcionarios.Id = ?");

			st.setInt(1, id);
			rs = st.executeQuery();
			if (rs.next()) {

				Departamento dep = instanciandoDepartamento(rs);
				Funcionario funci = instanciandoFuncionario(rs, dep);

				return funci;
			}
			return null;
		} catch (SQLException ex) {
			throw new DbException(ex.getMessage());
		} finally {
			DB.fechaStatement(st);
			DB.fechaResultSet(rs);
		}
	}

	private Funcionario instanciandoFuncionario(ResultSet rs, Departamento dep) throws SQLException {
		Funcionario funci = new Funcionario();
		funci.setId(rs.getInt("Id"));
		funci.setNome(rs.getString("Nome"));
		funci.setEmail(rs.getString("Email"));
		funci.setDataNascimento(rs.getDate("DataNascimento"));
		funci.setSalarioBase(rs.getDouble("SalarioBase"));
		funci.setDepartamento(dep);
		return funci;
	}

	private Departamento instanciandoDepartamento(ResultSet rs) throws SQLException {
		Departamento dep = new Departamento();
		dep.setId(rs.getInt("IdDepartamento"));
		dep.setNome(rs.getString("DepNome"));
		return dep;
	}

	@Override
	public List<Funcionario> findAll() {
		PreparedStatement st = null;
		ResultSet rs = null;
		try {
			st = conexao.prepareStatement("SELECT funcionarios.*,departamentos.Nome as DepNome "
					+ "FROM funcionarios INNER JOIN departamentos "
					+ "ON funcionarios.IdDepartamento = departamentos.Id " + "ORDER BY Nome");

			rs = st.executeQuery();

			List<Funcionario> lista = new ArrayList<>();
			Map<Integer, Departamento> map = new HashMap<>();

			while (rs.next()) {
				Departamento dep = map.get(rs.getInt("IdDepartamento"));

				if (dep == null) {
					dep = instanciandoDepartamento(rs);
					map.put(rs.getInt("IdDepartamento"), dep);
				}

				Funcionario funci = instanciandoFuncionario(rs, dep);
				lista.add(funci);
			}
			return lista;
		} catch (SQLException ex) {
			throw new DbException(ex.getMessage());
		} finally {
			DB.fechaStatement(st);
			DB.fechaResultSet(rs);
		}
	}

	@Override
	public List<Funcionario> findByDepartamento(Departamento departamento) {
		PreparedStatement st = null;
		ResultSet rs = null;
		try {
			st = conexao.prepareStatement(
					"SELECT funcionarios.*,departamentos.Nome as DepNome "
					+ "FROM funcionarios INNER JOIN departamentos "
					+ "ON funcionarios.IdDepartamento = departamentos.Id "
					+ "WHERE IdDepartamento = ? "
					+ "ORDER BY Nome");

			st.setInt(1, departamento.getId());
			rs = st.executeQuery();

			List<Funcionario> lista = new ArrayList<>();
			Map<Integer, Departamento> map = new HashMap<>();

			while (rs.next()) {
				Departamento dep = map.get(rs.getInt("IdDepartamento"));
				if (dep == null) {
					dep = instanciandoDepartamento(rs);
					map.put(rs.getInt("IdDepartamento"), dep);
				}

				Funcionario funci = instanciandoFuncionario(rs, dep);
				lista.add(funci);
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
