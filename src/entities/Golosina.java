package entities;

import java.io.Serializable;

public class Golosina implements Serializable {
	
	private String nombre;
	
	public Golosina() {}

	public Golosina(String nombre) {
		this.nombre = nombre;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	@Override
	public String toString() {
		return nombre;
	}
	
	

}
