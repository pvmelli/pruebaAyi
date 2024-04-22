package dbMockups;

import java.util.ArrayList;

import entities.Golosina;

public class GolosinaDBMockup {
	
	private static ArrayList<Golosina> golosinasMayorista = new ArrayList<Golosina>();
	private static ArrayList<Golosina> golosinasMinorista = new ArrayList<Golosina>();
	
	public static void populateDBGolosina() {
		
		populateGolosinaMayorista();
		populateGolosinaMinorista();

		
	}
	
	public static void populateGolosinaMayorista() {
		Golosina golosina1 = new Golosina("CHOCOLATE CAJAX50");
		golosinasMayorista.add(golosina1);
		Golosina golosina2 = new Golosina("GOMITAS PACKX500");
		golosinasMayorista.add(golosina2);
		Golosina golosina3 = new Golosina("BOMBONES CAJAX100");
		golosinasMayorista.add(golosina3);
		Golosina golosina4 = new Golosina("CHICLES CAJAX30");
		golosinasMayorista.add(golosina4);
		Golosina golosina5 = new Golosina("CARAMELOS PACKX100");
		golosinasMayorista.add(golosina5);
		Golosina golosina6 = new Golosina("CHUPETIN PACKX100");
		golosinasMayorista.add(golosina6);
		
	}
	
	public static void populateGolosinaMinorista() {
		Golosina golosina1 = new Golosina("CHOCOLATE 500GR");
		golosinasMinorista.add(golosina1);
		Golosina golosina2 = new Golosina("GOMITAS 250GR");
		golosinasMinorista.add(golosina2);
		Golosina golosina3 = new Golosina("BOMBONES 300GR");
		golosinasMinorista.add(golosina3);
		Golosina golosina4 = new Golosina("CHICLES 30GR");
		golosinasMinorista.add(golosina4);
		Golosina golosina5 = new Golosina("CARAMELOS 300GR");
		golosinasMinorista.add(golosina5);
		Golosina golosina6 = new Golosina("CHUPETIN 300GR");
		golosinasMinorista.add(golosina6);
		
	}
	
	public static ArrayList<Golosina> devolverDatosGolosinas(String tipoPedido) {
		if (tipoPedido.contains("MAYORISTA")) {
			return golosinasMayorista;
		}else if (tipoPedido.contains("MINORISTA")) {
			return golosinasMinorista;
		}else {
			return null;
		}
	}

}
