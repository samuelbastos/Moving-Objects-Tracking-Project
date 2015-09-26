package br.pucrio.inf1802.servlet;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;




import br.pucrio.inf1802.modelo.SinalUIImpl;

import com.google.gson.Gson;




import br.pucrio.inf1802.service.SinalService;
import br.pucrio.inf1802.service.SinalServiceImpl;
import br.pucrio.inf1802.service.ServiceException;

@SuppressWarnings("serial")
public class SinalServlet extends HttpServlet {

	private static SinalService sinalService = new SinalServiceImpl();

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

			SinalUIImpl requisitada = sinalService.buscaPorId(idString);

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

			Map<String, String> queryParams = extrairQueryParams(req);

			if(queryParams.containsKey("p") && queryParams.containsValue("false") ) {
				
				Gson gson = new Gson();

				String json = gson.toJson(sinalService.listarNaoProcessados());

				PrintWriter writer = new PrintWriter(resp.getOutputStream());

				writer.write(json);
				writer.flush();

				writer.close();
			}
			
			else {
				
			Gson gson = new Gson();

			String json = gson.toJson(sinalService.listarTodos());

			PrintWriter writer = new PrintWriter(resp.getOutputStream());

			writer.write(json);
			writer.flush();

			writer.close();
			
			}
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
			
			SinalUIImpl sinal = gson.fromJson(json, SinalUIImpl.class);

			SinalUIImpl sinalAtualizado;
			try {
				//sem id gson
				sinalAtualizado = sinalService.insere(sinal);
			} catch (ServiceException e) {
				e.printStackTrace();
				return;
			}

			reader.close();

			json = gson.toJson(sinalAtualizado);

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

					SinalUIImpl sinal = gson.fromJson(json, SinalUIImpl.class);
					sinal.setId(idString);

					try {
						sinalService.atualiza(sinal);
					} catch (ServiceException e) {
						e.printStackTrace();
						return;
					}

					json = gson.toJson(sinal);

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

			SinalUIImpl removida = sinalService.buscaPorId(idString);
			
			sinalService.apaga(idString);
			
			Gson gson = new Gson();

			String json = gson.toJson(removida);

			PrintWriter writer = new PrintWriter(resp.getOutputStream());

			writer.write(json);

			writer.flush();

			writer.close();

		} else {
			sinalService.apagaTodos();
			
			Gson gson = new Gson();

			String json = gson.toJson(sinalService.listarTodos());

			PrintWriter writer = new PrintWriter(resp.getOutputStream());

			writer.write(json);

			writer.flush();

			writer.close();
		}
		}catch(ServiceException e){
			e.printStackTrace();
		}	
	}
	
	private Map<String, String> extrairQueryParams(HttpServletRequest req) {
		Map<String, String> result = new HashMap<String, String>();
		String query = req.getQueryString();
		if (query != null && query.trim().length() != 0) {
			String[] queryParams = query.split("&");
			if (queryParams != null && queryParams.length != 0) {
				for (String queryParam : queryParams) {
					String[] keyValue = queryParam.split("=");
					if (keyValue != null && keyValue.length == 2) {
						result.put(keyValue[0], keyValue[1]);
					}
				}
			}
		}
		return result;
	}

}
