package br.pucrio.inf1802.dao;

import java.sql.Connection;
import java.util.List;


import br.pucrio.inf1802.modelo.EventoUIImpl;

public interface EventoDAO {

	/**
	 * Lista todas as inst�ncias de {@link EventoUIImpl eventos} presentes no banco de dados
	 * @param con conex�o com o banco de dados
	 * @return Lista com todas as inst�ncias de {@link EventoUIImpl} presente no banco de dados
	 * @throws DALException caso algum erro ocorra
	 */
	List<EventoUIImpl> listarTodos(Connection con) throws DALException;
	
	/**
	 * Recupera um evento dado o seu id
	 * @param con conex�o com o banco de dados
	 * @param id da {@link EventoUIImpl} que queremos buscar
	 * @return uma instancia de {@link EventoUIImpl}
	 * @throws DALException caso algum erro ocorra
	 */
	EventoUIImpl buscaPorId(Connection con,String id) throws DALException;
	
	/**
	 * Atualiza uma instancia de EventoUIImpl no banco de dados
	 * @param con conex�o com o banco de dados
	 * @param evento inst�ncia de {@link EventoUIImpl} a ser atualizada no banco de dados
	 * @throws DALException caso algum erro ocorra
	 */
	void atualiza(Connection con, EventoUIImpl evento)throws DALException;
	
	/**
	 * Insere uma instancia de evento no banco de dados e atribui um novo id a essa inst�ncia
	 * @param con Conex�o com o banco de dados
	 * @param pessoa Inst�ncia de {@link EventoUIImpl} a ser inserida no banco de dados.
	 * @return Inst�ncia de {@link EventoUIImpl} atualziada com o id gerado.
	 * @throws DALException caso algum erro ocorra
	 */
	EventoUIImpl insere(Connection con, EventoUIImpl evento)throws DALException;
	
	/**
	 * Apaga uma Inst�ncia espec�fica de {@link EventoUIImpl} do banco de dados, especificada por seu id
	 * @param con Conex�o com o banco de dados
	 * @param id da {@link EventoUIImpl} a ser apagada
	 * @throws DALException caso algum erro ocorra
	 */
	void apaga(Connection con,String id)throws DALException;
	
	/**
	 * Apaga todas as Inst�ncia de {@link EventoUIImpl} do banco de dados
	 * @param con Conex�o com o banco de dados
	 * @throws DALException caso algum erro ocorra
	 */
	void apagaTodos(Connection con)throws DALException;
}
