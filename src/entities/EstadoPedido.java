package entities;

import java.io.Serializable;

public abstract class EstadoPedido implements Serializable {

	protected Pedido pedido;
	
	public EstadoPedido(Pedido pedido) {
		this.pedido = pedido;
	}
	
	public abstract void pasajeAPendiente(int id);
	public abstract void pasajeAProcesado(boolean result);

}
