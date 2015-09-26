package br.pucrio.inf1802.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import br.pucrio.inf1802.modelo.EventoUIImpl;

public class EventoDAOImpl implements EventoDAO {

	@Override
	public List<EventoUIImpl> listarTodos(Connection con) throws DALException {
		
		List<EventoUIImpl> eventos = new ArrayList<EventoUIImpl>();
		
		try(Statement st = con.createStatement()){
			ResultSet rs = st.executeQuery("SELECT * from evento");
			
			while(rs.next()){
				EventoUIImpl evento = leEvento(rs);
				
				eventos.add(evento);
			}

		} catch (SQLException e) {
			throw new DALException("Erro ao listar todos os Eventos", e);
		}
		
		
		return eventos;
	}

	private EventoUIImpl leEvento(ResultSet rs) throws SQLException {
		EventoUIImpl evento = new EventoUIImpl();

		String id = rs.getString("id");
		String inicio = rs.getString("inicio");
		String fim = rs.getString("fim");
		String tipo = rs.getString("tipo");
		String valor = rs.getString("valor");
		String idObjetoMovel = rs.getString("idObjetoMovel");
		
		evento.setId(id);
		evento.setInicio(inicio);
		evento.setFim(fim);
		evento.setTipo(tipo);
		evento.setValor(valor);
		evento.setIdObjetoMovel(idObjetoMovel);

		return evento;
	}

	@Override
	public EventoUIImpl buscaPorId(Connection con, String id) throws DALException {
		try(Statement st = con.createStatement()){
			ResultSet rs = st.executeQuery("SELECT * from evento where id="+id);
			EventoUIImpl evento = null;
			if(rs.next()){
				evento = leEvento(rs);
			}
			return evento;
		} catch (SQLException e) {
			throw new DALException("Erro ao recuperar evento com o id "+id, e);
		}
		
	}

	@Override
	public void atualiza(Connection con, EventoUIImpl evento) throws DALException {
		try(PreparedStatement st = con.prepareStatement("Update evento set inicio=?,fim=?,tipo=?, valor=?, idObjetoMovel=? where id=?")){
			
			
			st.setString(1, evento.getInicio());
			st.setString(2, evento.getFim());
			st.setString(3, evento.getTipo());
			st.setString(4, evento.getValor());
			st.setString(5, evento.getIdObjetoMovel());
			st.setString(6, evento.getId());
			st.execute();

		} catch (SQLException e) {
			throw new DALException("Erro ao executar o update de Evento:"+evento,e);
		}
	}

	@Override
	public EventoUIImpl insere(Connection con, EventoUIImpl evento) throws DALException {
		try(PreparedStatement st = con.prepareStatement("insert into evento(inicio,fim,tipo,valor,idObjetoMovel) values(?,?,?,?,?)",PreparedStatement.RETURN_GENERATED_KEYS)){
			
			st.setString(1, evento.getInicio());
			st.setString(2, evento.getFim());
			st.setString(3, evento.getTipo());
			st.setString(4, evento.getValor());
			st.setString(5, evento.getIdObjetoMovel());
			st.execute();
			ResultSet rs = st.getGeneratedKeys();
			if(rs.next()){
				String id = rs.getString(1);
				evento.setId(id);
				return evento;
			}else{
				throw new DALException("Não foi possível inserir pessoa"+evento+". Nenhum id foi retornado");
			}
			
		} catch (SQLException e) {
			throw new DALException("Erro ao inserir novo evento "+evento,e);
		}
	}

	@Override
	public void apaga(Connection con, String id) throws DALException {
		try(PreparedStatement st = con.prepareStatement("Delete from evento where id=?")){
			
			st.setString(1, id);
			st.execute();

		} catch (SQLException e) {
			throw new DALException("Erro ao tentar apagar evento com o id"+id,e);
		}
	}

	@Override
	public void apagaTodos(Connection con) throws DALException {
		try(PreparedStatement st = con.prepareStatement("Delete from evento")){
			
			st.execute();

		} catch (SQLException e) {
			throw new DALException("Erro ao tentar apagar todos os eventos ",e);
		}

	}
}
