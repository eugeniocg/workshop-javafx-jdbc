package db;

import java.io.FileInputStream;


import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;

public class DB {
	
public static Connection conexao = null;
	
	public static Connection abreConexao() {                         //Abertura da conexão com o BD
		if(conexao ==null) {
			try {
				Properties props = lerPropriedades();                //Instancia o arquivo de Propriedades
				String url = props.getProperty("dburl");             //Cria variável com a url no arquivo de Propriedades
				conexao = DriverManager.getConnection(url, props);   //Abre a conexão com o BD informando o drive e conexão
			}
			catch(SQLException ex) {
				throw new DbException(ex.getMessage());
			}
		}
		return conexao;
	}
	
	public static Properties lerPropriedades() {
		try( FileInputStream fs = new FileInputStream("db.properties")){
			Properties props = new Properties();
			props.load(fs);
			return props;
		}
		catch(IOException ex) {
			throw new DbException(ex.getMessage());
		}
	}
	
	public static void fechaConexao() {                              //Fecha a conexão com o BD
		if(conexao != null) {
			try {
				conexao.close();                                     //Fecha a conexao
			}
			catch(SQLException ex) {
				throw new DbException(ex.getMessage());
			}
		}
	}
	
	public static void fechaStatement(Statement st) {                              //Fecha a conexão com o BD
		if(st != null) {
			try {
				st.close();                                     //Fecha a conexao
			}
			catch(SQLException ex) {
				throw new DbException(ex.getMessage());
			}
		}
	}
	
	public static void fechaResultSet(ResultSet rs) {                              //Fecha a conexão com o BD
		if(rs != null) {
			try {
				rs.close();                                     //Fecha a conexao
			}
			catch(SQLException ex) {
				throw new DbException(ex.getMessage());
			}
		}
	}
	
	
	
	

}
