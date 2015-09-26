package br.pucrio.inf1802.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import br.pucrio.inf1802.modelo.SinalUIImpl;

public class SinalDAOImpl implements SinalDAO {

	public List<SinalUIImpl> listarTodos(Connection con) throws DALException {
		
		List<SinalUIImpl> sinais = new ArrayList<SinalUIImpl>();
		
		try(Statement st = con.createStatement()){
			ResultSet rs = st.executeQuery("SELECT * from sinal");
			
			while(rs.next()){
				SinalUIImpl sinal = leSinal(rs);
				
				sinais.add(sinal);
			}

		} catch (SQLException e) {
			throw new DALException("Erro ao listar todos os Sinais", e);
		}

		return sinais;
	}

	private SinalUIImpl leSinal(ResultSet rs) throws SQLException {
		SinalUIImpl sinal = new SinalUIImpl();

		String id  = rs.getString("id");
		Double lon = rs.getDouble("lon");
		Double lat = rs.getDouble("lat");
		String idObjetoMovel = rs.getString("idObjetoMovel");
		String data = rs.getString("data");
			
		sinal.setId(id);
		sinal.setLon(lon);
		sinal.setLat(lat);
		sinal.setData(data);
		sinal.setIdObjetoMovel(idObjetoMovel);
		
		return sinal;
	}

	public SinalUIImpl buscaPorId(Connection con, String id) throws DALException {
		try(Statement st = con.createStatement()){
			ResultSet rs = st.executeQuery("SELECT * from sinal where id="+id);
			SinalUIImpl sinal = null;
			if(rs.next()){
				sinal = leSinal(rs);
			}
			return sinal;
		} catch (SQLException e) {
			throw new DALException("Erro ao recuperar sinal com o id "+id, e);
		}
		
	}

	public void atualiza(Connection con, SinalUIImpl sinal) throws DALException {
		try(PreparedStatement st = con.prepareStatement("Update sinal set lon=?, lat=?, data=?, idObjetoMovel=?, processado=? where id=?")){
			
			st.setDouble(1, sinal.getLon());
			st.setDouble(2, sinal.getLat());
			st.setString(3, sinal.getData());
			st.setString(4, sinal.GetIdObjetoMovel());
			st.setBoolean(5, true);
			st.setString(6, sinal.getId());
			
			st.execute();
			
		} catch (SQLException e) {
			throw new DALException("Erro ao executar o update de Sinal:"+sinal,e);
		}
	}

	public SinalUIImpl insere(Connection con, SinalUIImpl sinal) throws DALException {
		try(PreparedStatement st = con.prepareStatement("insert into sinal(lon,lat,data,idObjetoMovel) values(?,?,?,?)",PreparedStatement.RETURN_GENERATED_KEYS)){
			
			SimpleDateFormat fmt = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
			Date data = new Date();
			try {
				 data = fmt.parse(sinal.getData());
			} catch (ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			long dataToDb = data.getTime();
			
			st.setDouble(1, sinal.getLon());
			st.setDouble(2, sinal.getLat());
			st.setLong(3, dataToDb);
			st.setString(4, sinal.GetIdObjetoMovel());
			st.execute();
			ResultSet rs = st.getGeneratedKeys();
			if(rs.next()){
				int id = rs.getInt(1);
				String idSinal = Integer.toString(id);
				sinal.setId(idSinal);
				return sinal;
			}else{
				throw new DALException("Não foi possível inserir sinal"+sinal+". Nenhum id foi retornado");
			}
			
		} catch (SQLException e) {
			throw new DALException("Erro ao inserir novo sinal "+sinal,e);
		}
		
	}

	public void apaga(Connection con, String id) throws DALException {
		try(PreparedStatement st = con.prepareStatement("Delete from sinal where id=?")){
			
			st.setString(1, id);
			st.execute();

		} catch (SQLException e) {
			throw new DALException("Erro ao tentar apagar sinal com o id"+id,e);
		}
	}

	public void apagaTodos(Connection con) throws DALException {
		try(PreparedStatement st = con.prepareStatement("Delete from sinal")){
			
			st.execute();

		} catch (SQLException e) {
			throw new DALException("Erro ao tentar apagar todos os sinais ",e);
		}

	}

	public List<SinalUIImpl> listarNaoProcessados(Connection con) throws DALException {
		
		List<SinalUIImpl> naoProcessados = new ArrayList<SinalUIImpl>();
		
		try(Statement st = con.createStatement()){
			ResultSet rs = st.executeQuery("SELECT * from sinal where processado IS NULL");
			
			while(rs.next()){
				SinalUIImpl sinal = leSinal(rs);
				
				naoProcessados.add(sinal);
			}

		} catch (SQLException e) {
			throw new DALException("Erro ao listar todos os Sinais nao processados", e);
		}

		return naoProcessados;
	}
		
	
}
