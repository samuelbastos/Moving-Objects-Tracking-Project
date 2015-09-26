package br.pucrio.inf1802.dao;

import java.sql.Connection;
import java.util.List;


import br.pucrio.inf1802.modelo.SinalUIImpl;

public interface SinalDAO {

	/**
	 * Lista todas as instâncias de {@link SinalUIImpl sinais} presentes no banco de dados
	 * @param con conexão com o banco de dados
	 * @return Lista com todas as instâncias de {@link SinalUIImpl} presente no banco de dados
	 * @throws DALException caso algum erro ocorra
	 */
	List<SinalUIImpl> listarTodos(Connection con) throws DALException;
	
	/**
	 * Recupera um sinal dado o seu id
	 * @param con conexão com o banco de dados
	 * @param id da {@link SinalUIImpl} que queremos buscar
	 * @return uma instancia de {@link SinalUIImpl}
	 * @throws DALException caso algum erro ocorra
	 */
	SinalUIImpl buscaPorId(Connection con,String id) throws DALException;
	
	/**
	 * Atualiza uma instancia de SinalUIImpl no banco de dados
	 * @param con conexão com o banco de dados
	 * @param pessoa instância de {@link SinalUIImpl} a ser atualizada no banco de dados
	 * @throws DALException caso algum erro ocorra
	 */
	void atualiza(Connection con, SinalUIImpl sinal)throws DALException;
	
	/**
	 * Insere uma instancia de sinal no banco de dados e atribui um novo id a essa instância
	 * @param con Conexão com o banco de dados
	 * @param pessoa Instância de {@link SinalUIImpl} a ser inserida no banco de dados.
	 * @return Instância de {@link SinalUIImpl} atualziada com o id gerado.
	 * @throws DALException caso algum erro ocorra
	 */
	SinalUIImpl insere(Connection con, SinalUIImpl sinal)throws DALException;
	
	/**
	 * Apaga uma Instância específica de {@link SinalUIImpl} do banco de dados, especificada por seu id
	 * @param con Conexão com o banco de dados
	 * @param id da {@link SinalUIImpl} a ser apagada
	 * @throws DALException caso algum erro ocorra
	 */
	void apaga(Connection con,String id)throws DALException;
	
	/**
	 * Apaga todas as Instância de {@link SinalUIImpl} do banco de dados
	 * @param con Conexão com o banco de dados
	 * @throws DALException caso algum erro ocorra
	 */
	void apagaTodos(Connection con)throws DALException;
	
	List<SinalUIImpl> listarNaoProcessados(Connection con)throws DALException;
}
