package br.pucrio.inf1802.dao;

import java.sql.Connection;
import java.util.List;


import br.pucrio.inf1802.modelo.VeiculoImpl;

public interface VeiculoDAO {

	/**
	 * Lista todas as inst�ncias de {@link VeiculoImpl veiculos} presentes no banco de dados
	 * @param con conex�o com o banco de dados
	 * @return Lista com todas as inst�ncias de {@link VeiculoImpl} presente no banco de dados
	 * @throws DALException caso algum erro ocorra
	 */
	List<VeiculoImpl> listarTodos(Connection con) throws DALException;
	
	/**
	 * Recupera um veiculo dado o seu id
	 * @param con conex�o com o banco de dados
	 * @param id da {@link VeiculoImpl} que queremos buscar
	 * @return uma instancia de {@link VeiculoImpl}
	 * @throws DALException caso algum erro ocorra
	 */
	VeiculoImpl buscaPorId(Connection con,String id) throws DALException;
	
	/**
	 * Atualiza uma instancia de VeiculoImpl no banco de dados
	 * @param con conex�o com o banco de dados
	 * @param pessoa inst�ncia de {@link VeiculoImpl} a ser atualizada no banco de dados
	 * @throws DALException caso algum erro ocorra
	 */
	void atualiza(Connection con, VeiculoImpl veiculo)throws DALException;
	
	/**
	 * Insere uma instancia de veiculo no banco de dados e atribui um novo id a essa inst�ncia
	 * @param con Conex�o com o banco de dados
	 * @param veiculo Inst�ncia de {@link VeiculoImpl} a ser inserida no banco de dados.
	 * @return Inst�ncia de {@link VeiculoImpl} atualziada com o id gerado.
	 * @throws DALException caso algum erro ocorra
	 */
	VeiculoImpl insere(Connection con, VeiculoImpl veiculo)throws DALException;
	
	/**
	 * Apaga uma Inst�ncia espec�fica de {@link VeiculoImpl} do banco de dados, especificada por seu id
	 * @param con Conex�o com o banco de dados
	 * @param id da {@link VeiculoImpl} a ser apagada
	 * @throws DALException caso algum erro ocorra
	 */
	void apaga(Connection con,String id)throws DALException;
	
	/**
	 * Apaga todas as Inst�ncia de {@link VeiculoImpl} do banco de dados
	 * @param con Conex�o com o banco de dados
	 * @throws DALException caso algum erro ocorra
	 */
	void apagaTodos(Connection con)throws DALException;
}
