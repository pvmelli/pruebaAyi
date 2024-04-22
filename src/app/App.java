package app;

import java.util.ArrayList;
import java.util.Scanner;
import entities.Admin;
import entities.Cliente;
import entities.Golosina;
import entities.Pedido;
import entities.Usuario;

public class App {
	
	private static Usuario usuarioActual;
	
	public static void boot() {
		boolean primeraVez = Controller.evaluarPrimeraVez();
		Scanner userInput = crearScannerparaInput();
		if(primeraVez) {
			Controller.generarDBs();
		} else {
			Controller.generarDBs();
			Controller.regenerarDBs();
		}
		darBienvenidaInicial(userInput);		
		
	}
	
	private static Scanner crearScannerparaInput() {
		Scanner userInput = new Scanner(System.in);
		return userInput;
	}

	private static void darBienvenidaInicial(Scanner userInput) {
		System.out.println("************************************************************");
		System.out.println("¡BIENVENIDO A PEDIDOS AYIACADEMY!");
		System.out.println("************************************************************");
		presentarComandosDeSesion(userInput);
		
	}
	
	private static void presentarComandosDeSesion(Scanner userInput) {
		System.out.println("Para comenzar, usted debe registrarse o iniciar sesión");
		System.out.println("Si desea registrarse, oprima 1. Si desea iniciar sesión, oprima 2.");
		
		int answer = userInput.nextInt();
		userInput.nextLine();
		
		switch(answer) {
		case 1:
			registrarUsuario(userInput);
			break;
			
		case 2:
			loggearUsuario(userInput);
			break;
		
		default:
			System.out.println("Por favor ingrese una opción válida");
			presentarComandosDeSesion(userInput);
		}
	}
	
	private static void registrarUsuario(Scanner userInput) {
		System.out.println("¿Es su rol de administrador o de cliente?");
		String rol = userInput.nextLine().toUpperCase();
		
		System.out.println("¿Cuál será su nombre de usuario?");
		String username = userInput.nextLine();
		
		System.out.println("¿Cuál será su contraseña?");
		String password = userInput.nextLine();
		
		if(rol.contains("CLIENTE")) {
			Cliente clienteARegistrar = new Cliente(username, password);
			clienteARegistrar.registrarse();
			System.out.println("¡SE HA REGISTRADO EXITOSAMENTE!");
		} else if (rol.contains("ADMINISTRADOR")) {
			Admin adminARegistrar = new Admin(username, password);
			adminARegistrar.registrarse();
			System.out.println("¡SE HA REGISTRADO EXITOSAMENTE!");
		}else {
			System.out.println("Alguno de los datos ingresados no es válido, por favor intentelo nuevamente");
		}
		presentarComandosDeSesion(userInput);
	
	}
	

	private static void loggearUsuario(Scanner userInput) {
		System.out.println("Para iniciar sesión, provea los siguientes datos");
		
		System.out.println("¿Cuál es su nombre de usuario?");
		String username = userInput.nextLine();
		
		System.out.println("¿Cuál es su contraseña?");
		String password = userInput.nextLine();
		
		Usuario foundUser = Controller.pedirBusquedaDeUsuario(username, password);
		
		if(foundUser == null) {
			System.out.println("Alguno de los datos introducidos es incorrecto. Por favor, intentelo nuevamente");
			loggearUsuario(userInput);
		} else {
			usuarioActual = foundUser;
			System.out.println("¡USTED HA INICIADO SESIÓN CORRECTAMENTE!");
			mostrarComandosDeAccion(userInput);
		}
		
	}

	public static void mostrarComandosDeAccion(Scanner userInput) {
		if(usuarioActual instanceof Admin) {
			Admin adminActual = (Admin) usuarioActual;
			System.out.println("************************************************************");
			System.out.println("Por favor, indique qué acción desea realizar por medio de su número correspondiente");
			System.out.println("1 Ver todos los pedidos pendientes");
			System.out.println("2 Gestionar un pedido");
			System.out.println("3 Cerrar sesión");
			System.out.println("************************************************************");
			
			int answer = userInput.nextInt();
			userInput.nextLine();
			
			switch(answer) {
			case 1:
				verPedidosPendientes(adminActual);
				mostrarComandosDeAccion(userInput);
				break;
			case 2:
				gestionarPedido(userInput, adminActual);
				mostrarComandosDeAccion(userInput);
				break;
			case 3:
				usuarioActual = null;
				darBienvenidaInicial(userInput);
			default:
				System.out.println("Usted no ha ingresado una opción válida");
				mostrarComandosDeAccion(userInput);
			}
			
		}else if (usuarioActual instanceof Cliente) {
			Cliente clienteActual = (Cliente) usuarioActual;
			System.out.println("************************************************************");
			System.out.println("Por favor, indique qué acción desea realizar por medio de su número correspondiente");
			System.out.println("1 Crear un pedido");
			System.out.println("2 Ver mis pedidos");
			System.out.println("3 Enviar un pedido a aprobación");
			System.out.println("4 Cerrar sesión");
			System.out.println("************************************************************");
			
			int answer = userInput.nextInt();
			userInput.nextLine();
			
			switch(answer) {
			case 1:
				crearPedido(userInput, clienteActual);
				break;
			case 2:
				verPedidosDeUsuario(userInput, clienteActual);
				break;
			case 3:
				enviarPedidoParaAprobacion(userInput, clienteActual);
				break;
			case 4:
				usuarioActual = null;
				darBienvenidaInicial(userInput);
				break;
				
			default:
				System.out.println("Usted no ha ingresado una opción válida");
				mostrarComandosDeAccion(userInput);
			}
			
			
		
		}
	}
	
	private static void verPedidosPendientes(Admin adminActual) {
		ArrayList<Pedido> pedidosPendientes = adminActual.verPedidosPendientes();
		
		if(pedidosPendientes.isEmpty()) {
			System.out.println("NO HAY PEDIDOS PENDIENTES");
		}else {
			for (Pedido i : pedidosPendientes) {
			      System.out.println(i.toString());
			    }
		}
		

		
		
	}
	
	private static void gestionarPedido(Scanner userInput, Admin adminActual) {
		System.out.println("Por favor ingrese el id del pedido que desea procesar");
		int id = userInput.nextInt();
		userInput.nextLine();
		
		Pedido orderToSend = Controller.buscarPedidoPorId(id);
		
		if(orderToSend != null) {
			System.out.println("Por favor ingrese APROBADO para aprobar el pedido, o RECHAZADO para rechazarlo");
			
			String respuesta = userInput.nextLine().toUpperCase();
			
			boolean resultado = adminActual.gestionarPedidoPendiente(orderToSend, respuesta);
			
			if(resultado) {
				System.out.println("EL PEDIDO HA SIDO PROCESADO CON ÉXITO");
				
			} else {
				System.out.println("Usted no ha introducido una opción válida. Por favor, vuelva a intentarlo.");
			}
			
		} else {
			System.out.println("El pedido ingresado no existe.");
		}
		
	}
	
	private static String preguntarTipoPedido(Scanner userInput) {
		System.out.println("¿Desea realizar un pedido mayorista o minorista?");
		
		String tipoPedido = userInput.nextLine();
		
		if( tipoPedido.toUpperCase().contains("MAYORISTA") || tipoPedido.toUpperCase().contains("MINORISTA")) {
			return tipoPedido.toUpperCase();
			
		}else {
			System.out.println("Usted no ha introducido una opción válida. Por favor, vuelva a intentarlo.");
			return null;
		}
	}
	
	private static void crearPedido(Scanner userInput, Cliente clienteActual) {
		

		String tipoPedido = preguntarTipoPedido(userInput);
		
		System.out.println("Actualmente, contamos con el siguiente stock:");
		ArrayList<Golosina> stock = new ArrayList<Golosina>();
		stock = Controller.requerirDBGolosinas(tipoPedido);
		
		for (Golosina i : stock) {
		      System.out.println(i.getNombre());
		    }
		
		System.out.println("Por favor, ingrese los nombres de las golosinas que desea agregar, separados por una coma sin espacios");
		
		String userList = userInput.nextLine().toUpperCase();
		String[] userArray = userList.split(",");
		
		ArrayList<Golosina> listaCompra = new ArrayList<Golosina>();
		
		for (String i : userArray) {
			Golosina newGolosina = new Golosina(i);
			listaCompra.add(newGolosina);
		}
		
		Pedido pedido = clienteActual.crearPedido(tipoPedido, listaCompra);
		
		Controller.registrarPedidoEnDB(pedido);
		
		System.out.println("¡Su pedido ha sido registrado con éxito! El código del mismo es " + pedido.getId());
		
		mostrarComandosDeAccion(userInput);
	}
	
	private static void verPedidosDeUsuario(Scanner userInput, Cliente clienteActual) {
		ArrayList<Pedido> userPedidos = clienteActual.verPedidos();
		
		if(userPedidos.isEmpty()) {
			System.out.println("USTED NO HA REALIZADO NINGÚN PEDIDO");
		} else {
			for (Pedido i : userPedidos) {
				System.out.println(i.toString());
			}
		}
		
		mostrarComandosDeAccion(userInput);
		
	}
	
	private static void enviarPedidoParaAprobacion(Scanner userInput, Cliente clienteActual) {
		System.out.println("Por favor ingrese el id del pedido que desea enviar al administrador");
		int id = userInput.nextInt();
		userInput.nextLine();
		
		boolean result = clienteActual.enviarPedidoParaAprobacion(id);
		
		if(result) {
			System.out.println("Su pedido ha sido enviado para su aprobación");
		} else {
			System.out.println("El pedido requerido no existe");
		}
		
		mostrarComandosDeAccion(userInput);
		
	}
	
	
	
}
