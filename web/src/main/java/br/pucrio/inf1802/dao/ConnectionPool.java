package br.pucrio.inf1802.dao;

import java.sql.Connection;
import java.sql.SQLException;

import org.apache.commons.dbcp2.BasicDataSource;

public class ConnectionPool {
	
	private ConnectionPool(){
		
	}
	
	private static BasicDataSource dataSource;
	
	private static synchronized BasicDataSource getDataSource(){
		if(dataSource==null){
			dataSource = new BasicDataSource();
			dataSource.setDriverClassName("com.mysql.jdbc.Driver");
			dataSource.setUrl("jdbc:mysql://localhost:3306/inf1802?user=root&password=pass");
		}
		return dataSource;
	}
	
	public static Connection getConnection() throws DALException{
		try {
			return getDataSource().getConnection();
		} catch (SQLException e) {
			throw new DALException("Erro ao buscar uma conexão no pool",e);
		}
	}
	
	public static void releaseConnection(Connection connection) throws DALException{
		if(connection!=null){
			try {
				connection.setAutoCommit(true);
				connection.close();
			} catch (SQLException e) {
				throw new DALException("Erro ao retornat uma conexão para o pool",e);
			}
		}
	}

}
