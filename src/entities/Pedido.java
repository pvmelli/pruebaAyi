package entities;

import java.io.Serializable;
import java.util.ArrayList;

public class Pedido implements Serializable {

	private int id;
	private Usuario creator;
	private EstadoPedido estado;

	private enum Tipo {
		MAYORISTA,
		MINORISTA
	}
	
	private Tipo tipo;
	
	private ArrayList<Golosina> itemList = new ArrayList<Golosina>();
	
	
	
	public Pedido(Usuario creator) {
		estado = new Borrador(this);
		this.creator = creator;
		
		id = (int)(Math.random() * 1000001);
	}
	
	public Pedido(Usuario creator, String tipoPedido, ArrayList<Golosina> compra) {
		estado = new Borrador(this);
		this.creator = creator;
		tipo = Tipo.valueOf(tipoPedido);
		this.itemList = compra;
		
		id = (int)(Math.random() * 1000001);
	}
	
	public int getId() {
		return id;
	}
	
	public Usuario getCreator() {
		return creator;
	}
	
	public EstadoPedido getState() {
		return estado;
	}
	
	public ArrayList<Golosina> getItemList() {
		return itemList;
	}
	
	public void setTipo(String tipoPedido) {
		tipo = Tipo.valueOf(tipoPedido);
	}

	public void setListaCompra(ArrayList<Golosina> compra) {
		this.itemList = compra;
	}
	
	public void cambioDeEstado(EstadoPedido estado) {
		this.estado = estado;
		
	}
	

	@Override
	public String toString() {
		return "Pedido [Estado=" + estado.toString() + ", Creador=" + creator.toString() + ", ID=" + id + ", Items=" + itemList.toString() + "]";
	}
	

}
