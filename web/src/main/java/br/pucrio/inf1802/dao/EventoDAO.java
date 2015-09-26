package br.pucrio.inf1802.dao;

import java.sql.Connection;
import java.util.List;


import br.pucrio.inf1802.modelo.EventoUIImpl;

public interface EventoDAO {

	/**
	 * Lista todas as instâncias de {@link EventoUIImpl eventos} presentes no banco de dados
	 * @param con conexão com o banco de dados
	 * @return Lista com todas as instâncias de {@link EventoUIImpl} presente no banco de dados
	 * @throws DALException caso algum erro ocorra
	 */
	List<EventoUIImpl> listarTodos(Connection con) throws DALException;
	
	/**
	 * Recupera um evento dado o seu id
	 * @param con conexão com o banco de dados
	 * @param id da {@link EventoUIImpl} que queremos buscar
	 * @return uma instancia de {@link EventoUIImpl}
	 * @throws DALException caso algum erro ocorra
	 */
	EventoUIImpl buscaPorId(Connection con,String id) throws DALException;
	
	/**
	 * Atualiza uma instancia de EventoUIImpl no banco de dados
	 * @param con conexão com o banco de dados
	 * @param evento instância de {@link EventoUIImpl} a ser atualizada no banco de dados
	 * @throws DALException caso algum erro ocorra
	 */
	void atualiza(Connection con, EventoUIImpl evento)throws DALException;
	
	/**
	 * Insere uma instancia de evento no banco de dados e atribui um novo id a essa instância
	 * @param con Conexão com o banco de dados
	 * @param pessoa Instância de {@link EventoUIImpl} a ser inserida no banco de dados.
	 * @return Instância de {@link EventoUIImpl} atualziada com o id gerado.
	 * @throws DALException caso algum erro ocorra
	 */
	EventoUIImpl insere(Connection con, EventoUIImpl evento)throws DALException;
	
	/**
	 * Apaga uma Instância específica de {@link EventoUIImpl} do banco de dados, especificada por seu id
	 * @param con Conexão com o banco de dados
	 * @param id da {@link EventoUIImpl} a ser apagada
	 * @throws DALException caso algum erro ocorra
	 */
	void apaga(Connection con,String id)throws DALException;
	
	/**
	 * Apaga todas as Instância de {@link EventoUIImpl} do banco de dados
	 * @param con Conexão com o banco de dados
	 * @throws DALException caso algum erro ocorra
	 */
	void apagaTodos(Connection con)throws DALException;
}
