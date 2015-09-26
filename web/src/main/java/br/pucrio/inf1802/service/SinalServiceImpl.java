package br.pucrio.inf1802.service;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import br.pucrio.inf1802.dao.ConnectionPool;
import br.pucrio.inf1802.dao.DALException;
import br.pucrio.inf1802.dao.SinalDAO;
import br.pucrio.inf1802.dao.SinalDAOImpl;
import br.pucrio.inf1802.modelo.SinalUIImpl;

public class SinalServiceImpl implements SinalService {
	
	private static SinalDAO dao = new SinalDAOImpl();

	@Override
	public List<SinalUIImpl> listarTodos() throws ServiceException {
		Connection con = null;
		try{
			con = ConnectionPool.getConnection();
			return dao.listarTodos(con);
		} catch (DALException e) {
			throw new ServiceException("Erro ao listar todos os Sinais",e);
		}finally{
			try {
				ConnectionPool.releaseConnection(con);
			} catch (DALException e) {
				throw new ServiceException("Erro ao liberar conexao quando tentava listar todas os Sinais",e);
			}
		}
	}

	@Override
	public SinalUIImpl buscaPorId(String id) throws ServiceException {
		Connection con = null;
		try{
			con = ConnectionPool.getConnection();
			return dao.buscaPorId(con, id);
		} catch (DALException e) {
			throw new ServiceException("Erro ao buscar sinal com o id "+id,e);
		}finally{
			try {
				ConnectionPool.releaseConnection(con);
			} catch (DALException e) {
				throw new ServiceException("Erro ao liberar conexao quando tentava buscar sinal com o id "+id,e);
			}
		}
	}

	@Override
	public void atualiza(SinalUIImpl sinal) throws ServiceException {
		Connection con = null;
		try{
			con = ConnectionPool.getConnection();
			//Inicia transação
			con.setAutoCommit(false);
			
			dao.atualiza(con, sinal);
			
			//Finaliza transação
			con.commit();
			
		} catch (Exception e) {
			try {
				con.rollback();
			} catch (SQLException e1) {
				throw new ServiceException("Erro ao dar rollback apos erro ao atualizar sinal "+sinal,e1);
			}
			throw new ServiceException("Erro ao atualizar sinal "+sinal,e);
			
		} finally{
			try {
				ConnectionPool.releaseConnection(con);
			} catch (DALException e) {
				throw new ServiceException("Erro ao liberar conexao quando tentava atualizar sinal "+sinal,e);
			}
		}

	}

	@Override
	public SinalUIImpl insere(SinalUIImpl sinal) throws ServiceException {
		Connection con = null;
		try{
			con = ConnectionPool.getConnection();
			//Inicia transação
			con.setAutoCommit(false);
			
			SinalUIImpl sinalAtualizado = dao.insere(con, sinal);
			
			//Finaliza transação
			con.commit();
			
			return sinalAtualizado;
			
		} catch (Exception e) {
			try {
				con.rollback();
			} catch (SQLException e1) {
				throw new ServiceException("Erro ao dar rollback apos erro ao inserir sinal "+sinal,e1);
			}
			throw new ServiceException("Erro ao iserir sinal "+sinal,e);
			
		} finally{
			try {
				ConnectionPool.releaseConnection(con);
			} catch (DALException e) {
				throw new ServiceException("Erro ao liberar conexao quando tentava inseir sinal "+sinal,e);
			}
		}
	}

	@Override
	public void apaga(String id) throws ServiceException {
		Connection con = null;
		try{
			con = ConnectionPool.getConnection();
			//Inicia transação
			con.setAutoCommit(false);
			
			dao.apaga(con, id);
			
			//Finaliza transação
			con.commit();
			
		} catch (Exception e) {
			try {
				con.rollback();
			} catch (SQLException e1) {
				throw new ServiceException("Erro ao dar rollback apos erro ao apagar sinal de id "+id,e1);
			}
			throw new ServiceException("Erro ao apagar sinal de id "+id,e);
			
		} finally{
			try {
				ConnectionPool.releaseConnection(con);
			} catch (DALException e) {
				throw new ServiceException("Erro ao liberar conexao quando tentava apagar sinal de id "+id,e);
			}
		}

	}

	@Override
	public void apagaTodos() throws ServiceException {
		Connection con = null;
		try{
			con = ConnectionPool.getConnection();
			//Inicia transação
			con.setAutoCommit(false);
			
			dao.apagaTodos(con);
			
			//Finaliza transação
			con.commit();
			
		} catch (Exception e) {
			try {
				con.rollback();
			} catch (SQLException e1) {
				throw new ServiceException("Erro ao dar rollback apos erro ao apagar todas os sinais ",e1);
			}
			throw new ServiceException("Erro ao apagar todas os sinais",e);
			
		} finally{
			try {
				ConnectionPool.releaseConnection(con);
			} catch (DALException e) {
				throw new ServiceException("Erro ao liberar conexao quando tentava apagar todas os sinais",e);
			}
		}

	}
	
	public List<SinalUIImpl> listarNaoProcessados()throws ServiceException {
		Connection con = null;
		try{
			con = ConnectionPool.getConnection();
			return dao.listarNaoProcessados(con);
		} catch (DALException e) {
			throw new ServiceException("Erro ao listar todos os Sinais nao processados",e);
		}finally{
			try {
				ConnectionPool.releaseConnection(con);
			} catch (DALException e) {
				throw new ServiceException("Erro ao liberar conexao quando tentava listar todas os Sinais nao processados",e);
			}
		}
		
	}

}