package es.loyola.iitv.ptv.DAO;

import java.io.IOException;
import java.security.SecureRandom;
import java.util.Base64;
import java.util.Base64.Encoder;

import org.json.JSONObject;

public class User {
	private String dni, firstname, lastname, email, password, rpassword;
	private String token, lastAction, rol;
	
	public User() {
		this.setDni("");
		this.setFirstname("");
		this.setLastname("");
		this.setEmail("");
		this.setPassword("");
		this.setRpassword("");
		this.setToken("");
		this.setLastAction("");
		this.setRol("");
	}
	
	public User(String data) throws ClassCastException{
		if(data != null) {
			System.out.println("Creando usuario with: " + data);
			JSONObject json = new JSONObject(data);
			String dni="";
			String password= "";
			String repPassword= "";
			String email= "";
			String firstname= "";
			String lastname= "";
			String token= "";
			String lastAction= "";
			String rol="";

			if(json.has("usuario")) {
				dni = json.getString("usuario");
				System.out.println(dni);
				if(!dni.isEmpty()) {
					this.setDni(dni);
				}else {
					throw new ClassCastException("Error al copiar el dni");
				}
			}
			if(json.has("password")) {
				password = json.getString("password");
				if(!password.isEmpty()) {
					this.setPassword(password);
				}else {
					throw new ClassCastException("Error al copiar el password");
				}
			}
			if(json.has("rpassword")) {
				repPassword= json.getString("rpassword");
				if(!repPassword.isEmpty()) {
					this.setRpassword(repPassword);
				}else {
					throw new ClassCastException("Error al copiar el repPassword");
				}
			}
			if(json.has("email")) {
				email=  json.getString("email");
				if(!email.isEmpty()) {
					this.setEmail(email);
				}else {
					throw new ClassCastException("Error al copiar el email");
				}
			}
			if(json.has("firstName")) {
				firstname=  json.getString("firstName");
				if(!firstname.isEmpty()) {
					this.setFirstname(firstname);
				}else {
					throw new ClassCastException("Error al copiar el email");
				}
			}
			if(json.has("lastName")) {
				lastname=  json.getString("lastName");
				if(!lastname.isEmpty()) {
					this.setLastname(lastname);
				}else {
					throw new ClassCastException("Error al copiar el email");
				}
			}
			if(json.has("token")) {
				token= json.getString("token");
				if(!token.isEmpty()) {
					this.setToken(token);
				}else {
					throw new ClassCastException("Error al copiar el token");
				}
			}
			if(json.has("lastAction")) {
				lastAction= json.getString("lastAction");
				if(!lastAction.isEmpty()) {
					this.setLastAction(lastAction);
				}else {
					throw new ClassCastException("Error al copiar el token");
				}
			}
			if(json.has("rol")) {
				rol= json.getString("rol");
				if(!rol.isEmpty()) {
					this.setRol(rol);
				}else {
					throw new ClassCastException("Error al copiar el token");
				}
			}
			System.out.println("Usuario creado");
		}else {
			throw new ClassCastException("data is empty");
		}
	}

	public User(String email, String password) {
		if(email != null) {
			this.setEmail(email);
		}else {
			throw new ClassCastException("Error al copiar la repeticion del password");
		}
		if(password != null) {
			this.setPassword(password);
		}else {
			throw new ClassCastException("Error al copiar el password");
		}
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

	public String getRpassword() {
		return rpassword;
	}

	public void setRpassword(String rpassword) {
		this.rpassword = rpassword;
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
