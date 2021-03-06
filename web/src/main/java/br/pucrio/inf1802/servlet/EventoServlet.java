package br.pucrio.inf1802.servlet;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;


import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import br.pucrio.inf1802.modelo.EventoUIImpl;


import com.google.gson.Gson;

import br.pucrio.inf1802.service.EventoService;
import br.pucrio.inf1802.service.EventoServiceImpl;
import br.pucrio.inf1802.service.ServiceException;

@SuppressWarnings("serial")
public class EventoServlet extends HttpServlet {

	private static EventoService eventoService = new EventoServiceImpl();

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {

		try {
		String url = req.getRequestURI();

		String contextPath = req.getContextPath();

		String servletPath = req.getServletPath();

		String idString = url.replace(contextPath, "").replace(servletPath, "")
				.replace("/", "");

		if (idString.length() != 0) {

			EventoUIImpl requisitada = eventoService.buscaPorId(idString);

			if (requisitada != null) {

				Gson gson = new Gson();

				String json = gson.toJson(requisitada);

				PrintWriter writer = new PrintWriter(resp.getOutputStream());

				writer.write(json);

				writer.flush();

				writer.close();
			} else {
				PrintWriter writer = new PrintWriter(resp.getOutputStream());

				writer.write("null");

				writer.flush();

				writer.close();
			}

		} else {

			Gson gson = new Gson();

			String json = gson.toJson(eventoService.listarTodos());

			PrintWriter writer = new PrintWriter(resp.getOutputStream());

			writer.write(json);

			writer.flush();

			writer.close();

		}
	}catch(ServiceException e){
		e.printStackTrace();
	}
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {

		int tamanho = req.getContentLength();

		if (tamanho != 0) {

			char[] chars = new char[tamanho];

			BufferedReader reader = new BufferedReader(new InputStreamReader(
					req.getInputStream()));

			reader.read(chars);

			String json = new String(chars);

			Gson gson = new Gson();

			EventoUIImpl evento = gson.fromJson(json, EventoUIImpl.class);

			EventoUIImpl eventoAtualizado;
			try {
				eventoAtualizado = eventoService.insere(evento);
			} catch (ServiceException e) {
				e.printStackTrace();
				return;
			}

			reader.close();

			json = gson.toJson(eventoAtualizado);

			PrintWriter writer = new PrintWriter(resp.getOutputStream());

			writer.write(json);

			writer.flush();

			writer.close();

		}

	}

	@Override
	protected void doPut(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {

		String url = req.getRequestURI();

		String contextPath = req.getContextPath();

		String servletPath = req.getServletPath();

		String idString = url.replace(contextPath, "").replace(servletPath, "")
				.replace("/", "");

		if (idString.length() != 0) {

				int tamanho = req.getContentLength();

				if (tamanho != 0) {

					char[] chars = new char[tamanho];

					BufferedReader reader = new BufferedReader(
							new InputStreamReader(req.getInputStream()));

					reader.read(chars);

					String json = new String(chars);

					Gson gson = new Gson();

					EventoUIImpl evento = gson.fromJson(json, EventoUIImpl.class);
					evento.setId(idString);

					try {
						eventoService.atualiza(evento);
					} catch (ServiceException e) {
						e.printStackTrace();
						return;
					}

					json = gson.toJson(evento);

					PrintWriter writer = new PrintWriter(resp.getOutputStream());

					writer.write(json);

					writer.flush();

					writer.close();

					reader.close();

				}

			}
	}

	@Override
	protected void doDelete(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {

		try {

		String url = req.getRequestURI();

		String contextPath = req.getContextPath();

		String servletPath = req.getServletPath();

		String idString = url.replace(contextPath, "").replace(servletPath, "")
				.replace("/", "");

		if (idString.length() != 0) {

			EventoUIImpl removida = eventoService.buscaPorId(idString);
			
			eventoService.apaga(idString);
			
			Gson gson = new Gson();

			String json = gson.toJson(removida);

			PrintWriter writer = new PrintWriter(resp.getOutputStream());

			writer.write(json);

			writer.flush();

			writer.close();

		} else {
			eventoService.apagaTodos();
			
			Gson gson = new Gson();

			String json = gson.toJson(eventoService.listarTodos());

			PrintWriter writer = new PrintWriter(resp.getOutputStream());

			writer.write(json);

			writer.flush();

			writer.close();
		}
		}catch(ServiceException e){
			e.printStackTrace();
		}	
	}

}
