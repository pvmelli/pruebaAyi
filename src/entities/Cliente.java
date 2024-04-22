package entities;

import java.io.Serializable;
import java.util.ArrayList;

import app.Controller;

public class Cliente extends Usuario implements Serializable {

	private ArrayList<Pedido> pedidos = new ArrayList<Pedido>();
	
	public Cliente() {
		
	}
	
	public Cliente(String username, String password) {
		this.username = username;
		this.password = password;
	}
	
	public Pedido crearPedido(String tipoPedido, ArrayList<Golosina> compra) {
		Pedido pedido = new Pedido(this, tipoPedido, compra);
		pedidos.add(pedido);
		return pedido;
	}
	
	public ArrayList<Pedido> verPedidos() {
		
		ArrayList<Pedido> pedidos = Controller.requerirListaPedidosUsuario(this);
		
		return pedidos;
		
	}
	
	public boolean enviarPedidoParaAprobacion(int id) {
		for (Pedido i : pedidos) {
			if (i.getId() == id) {
				i.getState().pasajeAPendiente(i.getId());
				Controller.registrarPedidoParaAprobacionEnDB(i);
				return true;
			}
		}
		
		return false;
	}
	
	
}
