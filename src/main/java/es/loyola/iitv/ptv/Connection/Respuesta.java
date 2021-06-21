package es.loyola.iitv.ptv.Connection;

import org.json.JSONObject;

public class Respuesta {

	public Respuesta() {
		
	}
	
	public static JSONObject prepMensaje(JSONObject result, String email, String token, String rol) {
		JSONObject respuesta= new JSONObject();
		JSONObject session= new JSONObject();
		
		respuesta.put("status", "ok");
		respuesta.put("result", result);
		session.put("User", email);
		session.put("Token", token);
		session.put("role", rol);
		respuesta.put("session", session);
		
		return respuesta;
	}
	
	public static JSONObject prepMensajeError(String code, String mensaje, String email, String token, String rol) {
		JSONObject respuesta= new JSONObject();
		JSONObject result= new JSONObject();
		JSONObject session= new JSONObject();
		respuesta.put("status", "ERROR");
		result.put("code", code);
		result.put("errormsg", mensaje);
		respuesta.put("result", result);
		session.put("user", email);
		session.put("Token", token);
		session.put("role", rol);
		respuesta.put("session", session);
		return respuesta;
	}
}
