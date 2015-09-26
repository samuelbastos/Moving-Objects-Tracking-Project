package br.pucrio.inf1802.service;


import java.util.List;


import br.pucrio.inf1802.modelo.SinalUIImpl;

public interface SinalService {

	
	/**
	 * Lista todas as intâncias de {@link SinalUIImpl eventos} presentes no banco de dados
	 * @return Lista com todas as instâncias de {@link SinalUIImpl} presente no banco de dados
	 * @throws ServiceException caso algum erro ocorra
	 */
	List<SinalUIImpl> listarTodos() throws ServiceException;
	
	/**
	 * Recupera um sinal dado o seu id
	 * @param con Conexão com o banco de dados
	 * @param id da {@link SinalUIImpl} que queremos buscar
	 * @return uma instancia de {@link SinalUIImpl}
	 * @throws ServiceException caso algum erro ocorra
	 */
	SinalUIImpl buscaPorId(String id) throws ServiceException;
	
	/**
	 * Atualiza uma instancia de SinalUIImpl no banco de dados
	 * @param pessoa Instância de {@link SinalUIImpl} a ser atualizada no banco de dados
	 * @throws ServiceException caso algum erro ocorra
	 */
	void atualiza(SinalUIImpl sinal)throws ServiceException;
	
	/**
	 * Insere uma instancia de sinal no banco de dados e atribui um novo id a essa instância
	 * @param pessoa Instância de {@link SinalUIImpl} a ser inserida no banco de dados.
	 * @return Instância de {@link SinalUIImpl} atualziada com o id gerado.
	 * @throws ServiceException caso algum erro ocorra
	 */
	SinalUIImpl insere(SinalUIImpl sinal)throws ServiceException;
	
	/**
	 * Apaga uma instância específica de {@link SinalUIImpl} do banco de dados, especificada por seu id
	 * @param id da {@link SinalUIImpl} a ser apagada
	 * @throws ServiceException caso algum erro ocorra
	 */
	void apaga(String id)throws ServiceException;
	
	/**
	 * Apaga todas as instâncias de {@link SinalUIImpl} do banco de dados
	 * @throws ServiceException caso algum erro ocorra
	 */
	void apagaTodos()throws ServiceException;
	
	List<SinalUIImpl> listarNaoProcessados()throws ServiceException;
}
