package app;

import java.io.File;
import java.util.ArrayList;

import dbMockups.GolosinaDBMockup;
import dbMockups.PedidoDBMockup;
import dbMockups.UserDBMockup;
import entities.Golosina;
import entities.Pedido;
import entities.Usuario;

public class Controller {
	
	public static boolean evaluarPrimeraVez() {
		boolean primeraVez = chequearRegistros();
		
		return primeraVez;
	}
	
	public static boolean chequearRegistros() {
		boolean primeraVez;
		if(new File("Pedidos.ser").isFile() || new File("Usuarios.ser").isFile()) {
			primeraVez = false;
		} else {
			primeraVez = true;
		}
		
		return primeraVez;
	}
	
	public static void generarDBs() {
		GolosinaDBMockup.populateDBGolosina();
	}
	
	public static void regenerarDBs() {
		PedidoDBMockup.regenerarDBMockup();
		UserDBMockup.regenerarDBMockup();
	}
	
	public static Usuario pedirBusquedaDeUsuario(String username, String password) {
		
		Usuario userFound = UserDBMockup.buscarUsuarioEnDB(username, password);
		
		return userFound;
		
	}
	
	public static void registrarUsuarioEnDB(Usuario user) {
		UserDBMockup.agregarUsuarioADB(user);
	}
	
	public static void registrarPedidoEnDB(Pedido pedido) {
		PedidoDBMockup.agregarPedidoADB(pedido);
		
	}
	
	public static void registrarPedidoParaAprobacionEnDB(Pedido pedido) {
		PedidoDBMockup.enviarPedidoParaAprobacion(pedido);
	}
	
	public static ArrayList<Golosina> requerirDBGolosinas(String tipoPedido) {
		return GolosinaDBMockup.devolverDatosGolosinas(tipoPedido);
	}
	
	public static ArrayList<Pedido> requerirListaPedidosUsuario(Usuario user) {
		return PedidoDBMockup.devolverPedidosDeUsuario(user);
	}
	
	public static Pedido buscarPedidoPorId(int id) {
		try {
			return PedidoDBMockup.devolverPedidoPorId(id);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
	
	public static ArrayList<Pedido> devolverPedidosPendientes() {
		ArrayList<Pedido> pedidosPendientes = new ArrayList<Pedido>();
		pedidosPendientes = PedidoDBMockup.devolverPedidosPendientes();
		return pedidosPendientes;
	}
	
	public static void procesarPedido(Pedido pedidoAProcesar) {
		PedidoDBMockup.eliminarPedidoComoPendiente(pedidoAProcesar);
	}
	
	
	

	
	
}
