package entities;

import java.io.Serializable;
import java.util.Scanner;

import app.App;

public class Pendiente extends EstadoPedido  implements Serializable {
	public Pendiente(Pedido pedido) {
		super(pedido);
	}

	@Override
	public void pasajeAPendiente(int id) {
		System.out.println("ATENCIÓN: El pedido ya ha sido marcado como pendiente de aprobación");
		Scanner userInput = new Scanner(System.in);
		App.mostrarComandosDeAccion(userInput);
		
	}

	@Override
	public void pasajeAProcesado(boolean result) {
		pedido.cambioDeEstado(new Procesado(pedido, result));
		
	}

	@Override
	public String toString() {
		return "Pendiente";
	}
	
} 
