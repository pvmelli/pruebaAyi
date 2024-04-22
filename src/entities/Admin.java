package entities;

import java.io.Serializable;
import java.util.ArrayList;

import app.Controller;

public class Admin extends Usuario implements Serializable {
	
	private ArrayList<Pedido> pedidosGestionables;
	
	
	public Admin() {
		
	}

	public Admin(String username, String password) {
		this.username = username;
		this.password = password;
	}
	
	public ArrayList<Pedido> verPedidosPendientes() {		
		ArrayList<Pedido> pedidosPendientes = new ArrayList<Pedido>();
		pedidosPendientes = Controller.devolverPedidosPendientes();
		
		pedidosGestionables = pedidosPendientes;
		
		return pedidosPendientes;
	}
	
	public boolean gestionarPedidoPendiente(Pedido pedidoPendiente, String status) {
		
		if(status.contains("APROBADO")) {
			pedidoPendiente.getState().pasajeAProcesado(true);
			Controller.procesarPedido(pedidoPendiente);
			
			return true;
		} else if(status.contains("RECHAZADO")) {
			pedidoPendiente.getState().pasajeAProcesado(false);
			Controller.procesarPedido(pedidoPendiente);
			
			return true;
		} else {
			return false;
		}
		
	}
	
	

}
