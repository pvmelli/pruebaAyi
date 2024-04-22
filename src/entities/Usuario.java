package entities;

import java.io.Serializable;

import app.Controller;

public abstract class Usuario implements Serializable {
	
	protected String username;
	
	protected String password;
	
	
	protected Usuario() {
		
	}
	
	public void registrarse() {
		Controller.registrarUsuarioEnDB(this);
	}


	public String getUsername() {
		return this.username;
	}
	
	public String getPassword() {
		return this.password;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	@Override
	public String toString() {
		return username;
	}
	
	

}
