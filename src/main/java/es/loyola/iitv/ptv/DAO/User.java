package es.loyola.iitv.ptv.DAO;

import org.json.JSONObject;

public class User {
	private String usuario, firstname, lastname, email;
	private String password;
	
	//TODO constructor con casos para error, logindata y registro
	//TODO añadir throws Exception para el constructor
	public User() {
		
	}
	
	public User(String user, String pass){
		this.usuario = user;
		this.password = pass;
	}
	
	public User(String user, String pass, String firstname, String lastname, String email) {
		this.usuario= user;
		this.password= pass;
		this.firstname= firstname;
		this.lastname= lastname;
		this.email= email;
	}
	
	public String getPassword() {
		return this.password;
	}

	public String getUsuario() {
		return usuario;
	}

	public void setUsuario(String usuario) {
		this.usuario = usuario;
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

	public void getLoginData(String data) {
		//TODO pasar esto a constructor
		JSONObject json = new JSONObject(data);
		String dni = json.getString("usuario");
		String password = json.getString("password");
		this.setUsuario(dni);
		this.setPassword(password);
	}
	
	public void makeUser(String data) {
		//TODO pasar a constructor
		JSONObject json = new JSONObject(data);
		String dni = json.getString("usuario");
		String password = json.getString("password");
		String firstname=  json.getString("firstname");
		String lastname=  json.getString("lastname");
		String email=  json.getString("email");
		this.setUsuario(dni);
		this.setPassword(password);
		this.setFirstname(firstname);
		this.setLastname(lastname);
		this.setEmail(email);
	}
}
