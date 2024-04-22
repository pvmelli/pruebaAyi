package dbMockups;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.ArrayList;
import entities.Pedido;
import entities.Pendiente;
import entities.Usuario;

public class PedidoDBMockup implements Serializable {
	
	private static ArrayList<Pedido> allPedidos = new ArrayList<Pedido>();
	private static ArrayList<Pedido> pendingPedidos = new ArrayList<Pedido>();
	private static ArrayList<Pedido> currentUserPedidos = new ArrayList<Pedido>();
	
	public static void regenerarDBMockup() {
		
		try {
			FileInputStream fileIn;
			fileIn = new FileInputStream("Pedidos.ser");
			ObjectInputStream in = new ObjectInputStream(fileIn);
			allPedidos = (ArrayList<Pedido>) in.readObject();
			clasificarPedidosPendientes();
			in.close();
			fileIn.close();
		} catch (FileNotFoundException e) {
			actualizarSerDePedidos();
			
		} catch (IOException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		
	}
	
	public static void clasificarPedidosPendientes() {
		for(Pedido i : allPedidos) {
			if(i.getState() instanceof Pendiente) {
				pendingPedidos.add(i);
			}
		}
	}
	
	public static void agregarPedidoADB(Pedido order) {
		allPedidos.add(order);
		actualizarSerDePedidos();
		
	}
	
	public static void enviarPedidoParaAprobacion(Pedido order) {
		if(!pendingPedidos.contains(order)) {
			pendingPedidos.add(order);
			actualizarSerDePedidos();
		}
		
	}
	
	public static ArrayList<Pedido> devolverTodosLosPedidos() {
		return allPedidos;
	}
	
	public static ArrayList<Pedido> devolverPedidosPendientes() {
		return pendingPedidos;
	}
	
	public static ArrayList<Pedido> devolverPedidosDeUsuario(Usuario user) {
		
		for (Pedido i : allPedidos) {
			if (i.getCreator().getUsername().contains(user.getUsername())  && !currentUserPedidos.contains(i)) {
				currentUserPedidos.add(i);
			}
		}
		
		return currentUserPedidos;
	}
	
	public static Pedido devolverPedidoPorId(int id) throws Exception {
		
		for (Pedido i : allPedidos) {
			if (i.getId() == id) {
				return i;
			}
		}
		
		System.out.println("El pedido requerido no existe");
		return null;
		
		
	}
	
	public static void eliminarPedidoComoPendiente(Pedido pedidoAProcesar) {
		pendingPedidos.remove(pedidoAProcesar);
		actualizarSerDePedidos();
	}
	
	private static void actualizarSerDePedidos() {
		
		
		try {
			FileOutputStream fileOut;
			fileOut = new FileOutputStream("Pedidos.ser");
			ObjectOutputStream out = new ObjectOutputStream(fileOut);
			out.writeObject(allPedidos);
			out.close();
			fileOut.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

	}
	

}
