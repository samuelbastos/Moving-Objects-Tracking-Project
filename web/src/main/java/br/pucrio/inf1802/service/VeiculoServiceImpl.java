package br.pucrio.inf1802.service;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import br.pucrio.inf1802.dao.ConnectionPool;
import br.pucrio.inf1802.dao.DALException;
import br.pucrio.inf1802.dao.VeiculoDAO;
import br.pucrio.inf1802.dao.VeiculoDAOImpl;
import br.pucrio.inf1802.modelo.VeiculoImpl;

public class VeiculoServiceImpl implements VeiculoService {
	
	private static VeiculoDAO dao = new VeiculoDAOImpl();

	@Override
	public List<VeiculoImpl> listarTodos() throws ServiceException {
		Connection con = null;
		try{
			con = ConnectionPool.getConnection();
			return dao.listarTodos(con);
		} catch (DALException e) {
			throw new ServiceException("Erro ao listar todos os Veiculos",e);
		}finally{
			try {
				ConnectionPool.releaseConnection(con);
			} catch (DALException e) {
				throw new ServiceException("Erro ao liberar conexao quando tentava listar todas os Veiculos",e);
			}
		}
	}

	@Override
	public VeiculoImpl buscaPorId(String id) throws ServiceException {
		Connection con = null;
		try{
			con = ConnectionPool.getConnection();
			return dao.buscaPorId(con, id);
		} catch (DALException e) {
			throw new ServiceException("Erro ao buscar veiculo com o id "+id,e);
		}finally{
			try {
				ConnectionPool.releaseConnection(con);
			} catch (DALException e) {
				throw new ServiceException("Erro ao liberar conexao quando tentava buscar veiculo com o id "+id,e);
			}
		}
	}

	@Override
	public void atualiza(VeiculoImpl veiculo) throws ServiceException {
		Connection con = null;
		try{
			con = ConnectionPool.getConnection();
			//Inicia transação
			con.setAutoCommit(false);
			
			dao.atualiza(con, veiculo);
			
			//Finaliza transação
			con.commit();
			
		} catch (Exception e) {
			try {
				con.rollback();
			} catch (SQLException e1) {
				throw new ServiceException("Erro ao dar rollback apos erro ao atualizar veiculo "+veiculo,e1);
			}
			throw new ServiceException("Erro ao atualizar veiculo "+veiculo,e);
			
		} finally{
			try {
				ConnectionPool.releaseConnection(con);
			} catch (DALException e) {
				throw new ServiceException("Erro ao liberar conexao quando tentava atualizar veiculo "+veiculo,e);
			}
		}

	}

	@Override
	public VeiculoImpl insere(VeiculoImpl veiculo) throws ServiceException {
		Connection con = null;
		try{
			con = ConnectionPool.getConnection();
			//Inicia transação
			con.setAutoCommit(false);
			
			VeiculoImpl veiculoAtualizado = dao.insere(con, veiculo);
			
			//Finaliza transação
			con.commit();
			
			return veiculoAtualizado;
			
		} catch (Exception e) {
			try {
				con.rollback();
			} catch (SQLException e1) {
				throw new ServiceException("Erro ao dar rollback apos erro ao inserir veiculo "+veiculo,e1);
			}
			throw new ServiceException("Erro ao iserir veiculo "+veiculo,e);
			
		} finally{
			try {
				ConnectionPool.releaseConnection(con);
			} catch (DALException e) {
				throw new ServiceException("Erro ao liberar conexao quando tentava inseir veiculo "+veiculo,e);
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
				throw new ServiceException("Erro ao dar rollback apos erro ao apagar veiculo de id "+id,e1);
			}
			throw new ServiceException("Erro ao apagar veiculo de id "+id,e);
			
		} finally{
			try {
				ConnectionPool.releaseConnection(con);
			} catch (DALException e) {
				throw new ServiceException("Erro ao liberar conexao quando tentava apagar veiculo de id "+id,e);
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
				throw new ServiceException("Erro ao dar rollback apos erro ao apagar todas os veiculos ",e1);
			}
			throw new ServiceException("Erro ao apagar todas os veiculos",e);
			
		} finally{
			try {
				ConnectionPool.releaseConnection(con);
			} catch (DALException e) {
				throw new ServiceException("Erro ao liberar conexao quando tentava apagar todas os veiculos",e);
			}
		}

	}

}