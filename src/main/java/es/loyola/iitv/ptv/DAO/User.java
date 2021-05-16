package es.loyola.iitv.ptv.DAO;

import java.io.IOException;
import java.security.SecureRandom;
import java.util.Base64;
import java.util.Base64.Encoder;

import org.json.JSONObject;

public class User {
	private String dni, firstname, lastname, email, password;
	private String token, lastAction, rol;
	
	public User() {
		this.setDni("");
		this.setFirstname("");
		this.setLastname("");
		this.setEmail("");
		this.setPassword("");
		this.setToken("");
		this.setLastAction("");
		this.setRol("");
	}
	
	public User(String data) throws NullPointerException{
		if(data != null) {
			JSONObject json = new JSONObject(data);
			String dni="";
			String password= "";
			String email= "";
			String firstname= "";
			String lastname= "";
			String token= "";
			String lastAction= "";
			String rol="";

			if(json.has("Dni")) {
				dni = json.getString("Dni");
				if(!dni.isEmpty()) {
					//TODO comprobar si dni es valido, modulo 23, formula calcular NIF a%23
					this.setDni(dni);
				}else {
					throw new NullPointerException("Error al copiar el dni");
				}
			}
			if(json.has("password")) {
				password = json.getString("password");
				if(!password.isEmpty()) {
					this.setPassword(password);
				}else {
					throw new NullPointerException("Error al copiar el password");
				}
			}
			if(json.has("user")) {
				email=  json.getString("user");
				if(!email.isEmpty()) {
					this.setEmail(email);
				}else {
					throw new NullPointerException("Error al copiar el email");
				}
			}
			if(json.has("FirstName")) {
				firstname=  json.getString("FirstName");
				if(!firstname.isEmpty()) {
					this.setFirstname(firstname);
				}else {
					throw new NullPointerException("Error al copiar el firstName");
				}
			}
			if(json.has("LastName")) {
				lastname=  json.getString("LastName");
				if(!lastname.isEmpty()) {
					this.setLastname(lastname);
				}else {
					throw new NullPointerException("Error al copiar el LastName");
				}
			}
			if(json.has("token")) {
				token= json.getString("token");
				if(!token.isEmpty()) {
					this.setToken(token);
				}else {
					throw new NullPointerException("Error al copiar el token");
				}
			}
			if(json.has("lastAction")) {
				lastAction= json.getString("lastAction");
				if(!lastAction.isEmpty()) {
					this.setLastAction(lastAction);
				}else {
					throw new NullPointerException("Error al copiar el LastAction");
				}
			}
			if(json.has("rol")) {
				rol= json.getString("rol");
				if(!rol.isEmpty()) {
					this.setRol(rol);
				}else {
					throw new NullPointerException("Error al copiar el role");
				}
			}
			
		}else {
			throw new NullPointerException("data is empty");
		}
	}

	public User(String email, String password, String lastAction, String rol, String token) {
		this.setEmail(email);
		this.setPassword(password);
		this.setLastAction(lastAction);
		this.setRol(rol);
		this.setToken(token);
		this.setDni("");
		this.setFirstname("");
		this.setLastname("");
	}
	
	public User(String dni, String pass, String firstname, String lastname, String email,
			String rol, String lastAction, String token) {
		this.setDni(dni);
		this.setPassword(pass);
		this.setFirstname(firstname);
		this.setLastname(lastname);
		this.setEmail(email);
		this.setRol(rol);
		this.setLastAction(lastAction);
		this.setToken(token);
	}
	
	//TODO Unificamos los users de login y Users
	public User(User userlogin, User userUsers) throws ClassCastException{
		
	}
	
	public String generarToken() {
		SecureRandom random= new SecureRandom();
		byte bytes[]= new byte[20];
		random.nextBytes(bytes);
		Encoder encoder= Base64.getUrlEncoder().withoutPadding();
		String token= encoder.encodeToString(bytes);
		return token;
	}
	
	public String getPassword() {
		return this.password;
	}

	public String getDni() {
		return dni;
	}

	public void setDni(String dni) {
		this.dni = dni;
	}

	public void setPassword(String password) {
		this.password = password;
	}
	
	public String getFirstname() {
		return firstname;
	}

	public void setFirstname(String firstname) {
		this.firstname = firstname;
	}

	public String getLastname() {
		return lastname;
	}

	public void setLastname(String lastname) {
		this.lastname = lastname;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getRol() {
		return rol;
	}

	public void setRol(String rol) {
		this.rol = rol;
	}

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}

	public String getLastAction() {
		return lastAction;
	}

	public void setLastAction(String lastAction) {
		this.lastAction = lastAction;
	}

}
