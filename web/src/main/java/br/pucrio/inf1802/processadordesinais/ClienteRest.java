package br.pucrio.inf1802.processadordesinais;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public final class ClienteRest {

	public static String novaRequisicao(String metodo, String caminho,
			String corpo) {
		HttpURLConnection con = null;
		try {
			URL url = new URL(caminho);
			con = (HttpURLConnection) url.openConnection();
			con.setRequestMethod(metodo);

			if (corpo != null && corpo.trim().length() != 0
					&& ("PUT".equals(metodo) || "POST".equals(metodo))) {
				con.setDoOutput(true);
				DataOutputStream wr = new DataOutputStream(
						con.getOutputStream());
				wr.writeBytes(corpo);
				wr.close();
			}

			// Get Response
			InputStream is = con.getInputStream();
			BufferedReader rd = new BufferedReader(new InputStreamReader(is));
			StringBuilder response = new StringBuilder(); // or StringBuffer if
															// not Java 5+
			String line;
			while ((line = rd.readLine()) != null) {
				response.append(line);
				response.append('\r');
			}
			rd.close();
			return response.toString();
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		} finally {
			if (con != null) {
				con.disconnect();
			}
		}

	}
}
