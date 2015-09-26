package br.pucrio.inf1802.service;

import java.util.List;

import br.pucrio.inf1802.modelo.EventoUIImpl;

public interface EventoService {

	
	/**
	 * Lista todas as intâncias de {@link EventoUIImpl eventos} presentes no banco de dados
	 * @return Lista com todas as instâncias de {@link EventoUIImpl} presente no banco de dados
	 * @throws ServiceException caso algum erro ocorra
	 */
	List<EventoUIImpl> listarTodos() throws ServiceException;
	
	/**
	 * Recupera um evento dado o seu id
	 * @param con Conexão com o banco de dados
	 * @param id da {@link EventoUIImpl} que queremos buscar
	 * @return uma instancia de {@link EventoUIImpl}
	 * @throws ServiceException caso algum erro ocorra
	 */
	EventoUIImpl buscaPorId(String id) throws ServiceException;
	
	/**
	 * Atualiza uma instancia de EventoUIImpl no banco de dados
	 * @param pessoa Instância de {@link EventoUIImpl} a ser atualizada no banco de dados
	 * @throws ServiceException caso algum erro ocorra
	 */
	void atualiza(EventoUIImpl evento)throws ServiceException;
	
	/**
	 * Insere uma instancia de evento no banco de dados e atribui um novo id a essa instância
	 * @param pessoa Instância de {@link EventoUIImpl} a ser inserida no banco de dados.
	 * @return Instância de {@link EventoUIImpl} atualziada com o id gerado.
	 * @throws ServiceException caso algum erro ocorra
	 */
	EventoUIImpl insere(EventoUIImpl evento)throws ServiceException;
	
	/**
	 * Apaga uma instância específica de {@link EventoUIImpl} do banco de dados, especificada por seu id
	 * @param id da {@link EventoUIImpl} a ser apagada
	 * @throws ServiceException caso algum erro ocorra
	 */
	void apaga(String id)throws ServiceException;
	
	/**
	 * Apaga todas as instâncias de {@link EventoUIImpl} do banco de dados
	 * @throws ServiceException caso algum erro ocorra
	 */
	void apagaTodos()throws ServiceException;
	
}
