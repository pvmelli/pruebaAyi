package dbMockups;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.ArrayList;

import entities.Admin;
import entities.Cliente;
import entities.Usuario;

public class UserDBMockup implements Serializable {
	
	private static ArrayList<Usuario> users = new ArrayList<Usuario>();
	
	public static void regenerarDBMockup() {
		try {
			FileInputStream fileIn;
			fileIn = new FileInputStream("Usuarios.ser");
			ObjectInputStream in = new ObjectInputStream(fileIn);
			users = (ArrayList<Usuario>) in.readObject();
			in.close();
			fileIn.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		
	}

	
	public static void agregarUsuarioADB(Usuario user) {
		UserDBMockup.users.add(user);
		actualizarDBMockup();
	}
	
	public static Usuario buscarUsuarioEnDB(String username, String password) {
		for (Usuario user : users) {
			  if (user.getUsername().equals(username) && user.getPassword().equals(password)) {
				  return user;
			  }
		}
		return null;
	}
	
	public static void actualizarDBMockup() {
		
		FileOutputStream fileOut;
		try {
			
			fileOut = new FileOutputStream("Usuarios.ser");
			ObjectOutputStream out = new ObjectOutputStream(fileOut);
			out.writeObject(users);
			out.close();
			fileOut.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}
		
}
