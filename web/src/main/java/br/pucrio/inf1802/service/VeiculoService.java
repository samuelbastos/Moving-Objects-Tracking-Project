package br.pucrio.inf1802.service;

import java.util.List;

import br.pucrio.inf1802.modelo.VeiculoImpl;

public interface VeiculoService {

	
	/**
	 * Lista todas as intâncias de {@link VeiculoImpl veiculos} presentes no banco de dados
	 * @return Lista com todas as instâncias de {@link VeiculoImpl} presente no banco de dados
	 * @throws ServiceException caso algum erro ocorra
	 */
	List<VeiculoImpl> listarTodos() throws ServiceException;
	
	/**
	 * Recupera um veiculo dado o seu id
	 * @param con Conexão com o banco de dados
	 * @param id da {@link VeiculoImpl} que queremos buscar
	 * @return uma instancia de {@link VeiculoImpl}
	 * @throws ServiceException caso algum erro ocorra
	 */
	VeiculoImpl buscaPorId(String id) throws ServiceException;
	
	/**
	 * Atualiza uma instancia de VeiculoImpl no banco de dados
	 * @param pessoa Instância de {@link VeiculoImpl} a ser atualizada no banco de dados
	 * @throws ServiceException caso algum erro ocorra
	 */
	void atualiza(VeiculoImpl veiculo)throws ServiceException;
	
	/**
	 * Insere uma instancia de veiculo no banco de dados e atribui um novo id a essa instância
	 * @param pessoa Instância de {@link VeiculoImpl} a ser inserida no banco de dados.
	 * @return Instância de {@link VeiculoImpl} atualziada com o id gerado.
	 * @throws ServiceException caso algum erro ocorra
	 */
	VeiculoImpl insere(VeiculoImpl veiculo)throws ServiceException;
	
	/**
	 * Apaga uma instância específica de {@link VeiculoImpl} do banco de dados, especificada por seu id
	 * @param id da {@link VeiculoImpl} a ser apagada
	 * @throws ServiceException caso algum erro ocorra
	 */
	void apaga(String id)throws ServiceException;
	
	/**
	 * Apaga todas as instâncias de {@link VeiculoImpl} do banco de dados
	 * @throws ServiceException caso algum erro ocorra
	 */
	void apagaTodos()throws ServiceException;
	
}
