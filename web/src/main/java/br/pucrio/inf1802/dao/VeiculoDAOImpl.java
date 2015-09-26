package br.pucrio.inf1802.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;


import br.pucrio.inf1802.modelo.VeiculoImpl;

public class VeiculoDAOImpl implements VeiculoDAO {

public List<VeiculoImpl> listarTodos(Connection con) throws DALException {
		
		List<VeiculoImpl> veiculos = new ArrayList<VeiculoImpl>();
		
		try(Statement st = con.createStatement()){
			ResultSet rs = st.executeQuery("SELECT * from veiculo");
			
			while(rs.next()){
				VeiculoImpl veiculo = leVeiculo(rs);
				
				veiculos.add(veiculo);
			}

		} catch (SQLException e) {
			throw new DALException("Erro ao listar todos os Veiculos", e);
		}
		
		
		return veiculos;
	}

	private VeiculoImpl leVeiculo(ResultSet rs) throws SQLException {
		VeiculoImpl veiculo = new VeiculoImpl();

	
		String id = rs.getString("id");
		String idUltimoSinal = rs.getString("idUltimoSinal");
		String placa = rs.getString("placa");
		String modelo = rs.getString("modelo");
		
		veiculo.setId(id);
		veiculo.setModelo(modelo);
		veiculo.setPlaca(placa);
		veiculo.setUltimoId(idUltimoSinal);
		
		return veiculo;
	}

	public VeiculoImpl buscaPorId(Connection con, String id) throws DALException {
		try(Statement st = con.createStatement()){
			ResultSet rs = st.executeQuery("SELECT * from veiculo where id="+id);
			VeiculoImpl veiculo = null;
			if(rs.next()){
				veiculo = leVeiculo(rs);
			}
			return veiculo;
		} catch (SQLException e) {
			throw new DALException("Erro ao recuperar veiculo com o id "+id, e);
		}
		
	}

	public void atualiza(Connection con, VeiculoImpl veiculo) throws DALException {
		try(PreparedStatement st = con.prepareStatement("Update veiculo set placa=?, modelo=?, idUltimoSinal=? where id=?")){
			

			st.setString(1, veiculo.getPlaca());
			st.setString(2, veiculo.getModelo());
			st.setString(3, veiculo.getUltimoId());
			st.setString(4, veiculo.getId());
			st.execute();
			
		} catch (SQLException e) {
			throw new DALException("Erro ao executar o update de Veiculo:"+veiculo,e);
		}
	}

	public VeiculoImpl insere(Connection con, VeiculoImpl veiculo) throws DALException {
		try(PreparedStatement st = con.prepareStatement("insert into veiculo(placa, modelo, ultimoId) values(?,?,?)",PreparedStatement.RETURN_GENERATED_KEYS)){
			
			st.setString(1, veiculo.getPlaca());
			st.setString(2, veiculo.getModelo());
			st.setString(3, veiculo.getUltimoId());
			st.execute();
			ResultSet rs = st.getGeneratedKeys();
			if(rs.next()){
				String id = rs.getString(1);
				veiculo.setId(id);
				return veiculo;
			}else{
				throw new DALException("Não foi possível inserir veiculo"+veiculo+". Nenhum id foi retornado");
			}
			
		} catch (SQLException e) {
			throw new DALException("Erro ao inserir novo veiculo "+veiculo,e);
		}
	}

	public void apaga(Connection con, String id) throws DALException {
		try(PreparedStatement st = con.prepareStatement("Delete from veiculo where id=?")){
			
			st.setString(1, id);
			st.execute();

		} catch (SQLException e) {
			throw new DALException("Erro ao tentar apagar veiculo com o id"+id,e);
		}
	}

	public void apagaTodos(Connection con) throws DALException {
		try(PreparedStatement st = con.prepareStatement("Delete from veiculo")){
			
			st.execute();

		} catch (SQLException e) {
			throw new DALException("Erro ao tentar apagar todos os veiculos ",e);
		}

	}

}
