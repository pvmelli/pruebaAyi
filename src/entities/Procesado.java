package entities;

import java.io.Serializable;
import java.util.Scanner;

import app.App;

public class Procesado extends EstadoPedido  implements Serializable {
	
	private boolean resultadoProcesamiento;
	
	public Procesado(Pedido pedido, boolean resultado) {
		super(pedido);
		this.resultadoProcesamiento = resultado;
	}

	@Override
	public void pasajeAPendiente(int id) {
		System.out.println("ATENCIÓN: El pedido ya ha sido procesado. No puede enviarse a aprobación nuevamente.");
		Scanner userInput = new Scanner(System.in);
		App.mostrarComandosDeAccion(userInput);
		
	}

	@Override
	public void pasajeAProcesado(boolean result) {
		System.out.println("ATENCIÓN: El pedido ya ha sido procesado.");
		Scanner userInput = new Scanner(System.in);
		App.mostrarComandosDeAccion(userInput);
		
	}

	@Override
	public String toString() {
		return "Procesado [" + resultadoToString() + "]";
	}
	
	public String resultadoToString() {
		
		if(this.resultadoProcesamiento) {
			return "APROBADO";
		}else {
			return "RECHAZADO";
		}
	}
}
