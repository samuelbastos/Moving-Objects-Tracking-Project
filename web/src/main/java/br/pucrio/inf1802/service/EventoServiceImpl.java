package br.pucrio.inf1802.service;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import br.pucrio.inf1802.dao.ConnectionPool;
import br.pucrio.inf1802.dao.DALException;
import br.pucrio.inf1802.dao.EventoDAO;
import br.pucrio.inf1802.dao.EventoDAOImpl;
import br.pucrio.inf1802.modelo.EventoUIImpl;

public class EventoServiceImpl implements EventoService {
	
	private static EventoDAO dao = new EventoDAOImpl();

	@Override
	public List<EventoUIImpl> listarTodos() throws ServiceException {
		Connection con = null;
		try{
			con = ConnectionPool.getConnection();
			return dao.listarTodos(con);
		} catch (DALException e) {
			throw new ServiceException("Erro ao listar todos os Eventos",e);
		}finally{
			try {
				ConnectionPool.releaseConnection(con);
			} catch (DALException e) {
				throw new ServiceException("Erro ao liberar conexao quando tentava listar todas os Eventos",e);
			}
		}
	}

	@Override
	public EventoUIImpl buscaPorId(String id) throws ServiceException {
		Connection con = null;
		try{
			con = ConnectionPool.getConnection();
			return dao.buscaPorId(con, id);
		} catch (DALException e) {
			throw new ServiceException("Erro ao buscar evento com o id "+id,e);
		}finally{
			try {
				ConnectionPool.releaseConnection(con);
			} catch (DALException e) {
				throw new ServiceException("Erro ao liberar conexao quando tentava buscar evento com o id "+id,e);
			}
		}
	}

	@Override
	public void atualiza(EventoUIImpl evento) throws ServiceException {
		Connection con = null;
		try{
			con = ConnectionPool.getConnection();
			//Inicia transação
			con.setAutoCommit(false);
			
			dao.atualiza(con, evento);
			
			//Finaliza transação
			con.commit();
			
		} catch (Exception e) {
			try {
				con.rollback();
			} catch (SQLException e1) {
				throw new ServiceException("Erro ao dar rollback apos erro ao atualizar evento "+evento,e1);
			}
			throw new ServiceException("Erro ao atualizar evento "+evento,e);
			
		} finally{
			try {
				ConnectionPool.releaseConnection(con);
			} catch (DALException e) {
				throw new ServiceException("Erro ao liberar conexao quando tentava atualizar evento "+evento,e);
			}
		}

	}

	@Override
	public EventoUIImpl insere(EventoUIImpl evento) throws ServiceException {
		Connection con = null;
		try{
			con = ConnectionPool.getConnection();
			//Inicia transação
			con.setAutoCommit(false);
			
			EventoUIImpl eventoAtualizado = dao.insere(con, evento);
			
			//Finaliza transação
			con.commit();
			
			return eventoAtualizado;
			
		} catch (Exception e) {
			try {
				con.rollback();
			} catch (SQLException e1) {
				throw new ServiceException("Erro ao dar rollback apos erro ao inserir evento "+evento,e1);
			}
			throw new ServiceException("Erro ao iserir evento "+evento,e);
			
		} finally{
			try {
				ConnectionPool.releaseConnection(con);
			} catch (DALException e) {
				throw new ServiceException("Erro ao liberar conexao quando tentava inseir evento "+evento,e);
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
				throw new ServiceException("Erro ao dar rollback apos erro ao apagar evento de id "+id,e1);
			}
			throw new ServiceException("Erro ao apagar evento de id "+id,e);
			
		} finally{
			try {
				ConnectionPool.releaseConnection(con);
			} catch (DALException e) {
				throw new ServiceException("Erro ao liberar conexao quando tentava apagar evento de id "+id,e);
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
				throw new ServiceException("Erro ao dar rollback apos erro ao apagar todas os eventos ",e1);
			}
			throw new ServiceException("Erro ao apagar todas os eventos",e);
			
		} finally{
			try {
				ConnectionPool.releaseConnection(con);
			} catch (DALException e) {
				throw new ServiceException("Erro ao liberar conexao quando tentava apagar todas os eventos",e);
			}
		}

	}

}