//package com.epizootia.sistema;
//
//import java.sql.SQLException;
//import java.util.Date;
//
//import org.json.JSONObject;
//
//import io.jsonwebtoken.Jwts;
//import io.jsonwebtoken.SignatureAlgorithm;
//import io.jsonwebtoken.SignatureException;
//
//public class login {
//	public String gerarToken(String nome) {
//		String secretKey = "35725c901c45f1c13f9e3fe8421a15dd26130118"; // Chave
//																		// privada
//
//		// tempo para expirar token 24h
//		long nowMillis = System.currentTimeMillis();
//		Date now = new Date(nowMillis + 86400000);
//
//		String token = Jwts.builder().setSubject(nome).claim("auth", "usuario")
//				.signWith(SignatureAlgorithm.HS512, secretKey).setExpiration(now).compact();
//
//		return token;
//	}
//
//	public boolean validateToken(String authToken) {
//		String secretKey = "35725c901c45f1c13f9e3fe8421a15dd26130118"; // Chave
//
//		boolean resultado = false;
//
//		if (authToken.equals("naoEncontrado")) {
//			resultado = false;
//		} else {
//			try {
//				Jwts.parser().setSigningKey(secretKey).parseClaimsJws(authToken);
//				resultado = true;
//			} catch (SignatureException e) {
//				resultado = false;
//			}
//		}
//		return resultado;
//	}
//
//	public JSONObject validarToken(String authToken) {
//
//		JSONObject jsonObject = new JSONObject();
//
//		if (validateToken(authToken) == true) {
//			jsonObject.put("resutladoAutenticacao", "permitido");
//		} else {
//			jsonObject.put("resutladoAutenticacao", "negado");
//		}
//		return jsonObject;
//	}
//
//	public JSONObject verificarLogin(long usuario, String senha)
//			throws InstantiationException, IllegalAccessException, ClassNotFoundException, SQLException {
//		JSONObject jsonObject = new JSONObject();
//
//		String nomeUsuario = "adminadmin";
//		String senhaUsuario = "senhasenha";
//
//		if (nomeUsuario.equals(usuario)  && senhaUsuario.equals(senha)) {
//			String token = gerarToken(nomeUsuario);
//			jsonObject.put("resutladoLogin", "true");
//			jsonObject.put("token", token);
//		}else {
//			jsonObject.put("resutladoLogin", "false");
//		}
//		
//		return jsonObject;
//	}
//}