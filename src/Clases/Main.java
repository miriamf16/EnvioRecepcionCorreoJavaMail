package Clases;

import java.util.Scanner;

public class Main {

	static String correo = "";
	static String password = "";
	static String corrDes = "";
	static String asunto = "";
	static String remitente = "";

	public static String getInformation(String mensaje) {
		Scanner teclado = new Scanner(System.in);
		String cadena;
		
		System.out.print(mensaje + ": ");
		cadena = teclado.nextLine();
		
		return cadena;
	}
	
	public static void loginAcc() {
		correo = getInformation("Correo electronico");
		password = getInformation("contraseña");
	}
	
	public static void getData() {
		corrDes = getInformation("correo destino");
		asunto = getInformation("asunto");
		remitente = getInformation("remitente");
	}

	public static void main(String[] args) {

		//correo = "a1253534@uabc.edu.mx";
		//password = "taylorswift1848* (te la creiste)";
		//remitente = "yo";
		
		// INICIO DE SESION
		loginAcc();
		Correo.createSession(correo, password);
		
		// CREACION DE MENSAJE
		getData();
		Correo.createMail(corrDes, asunto, remitente);

	}

}
