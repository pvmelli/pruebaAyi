package entities;

import java.io.Serializable;
import java.util.Scanner;

import app.App;

public class Borrador extends EstadoPedido implements Serializable {
	public Borrador(Pedido pedido) {
		super(pedido);
	}

	@Override
	public void pasajeAPendiente(int id) {
		pedido.cambioDeEstado(new Pendiente(pedido));
		
	}

	@Override
	public void pasajeAProcesado(boolean result) {
		System.out.println("ATENCIÓN: No es posible procesar un pedido en el estado Borrador. Por favor, envíelo a aprobación por medio del proceso correspondiente");
		Scanner userInput = new Scanner(System.in);
		App.mostrarComandosDeAccion(userInput);
		
	}

	@Override
	public String toString() {
		return "Borrador";
	}
	

}
