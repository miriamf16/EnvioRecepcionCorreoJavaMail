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

		System.out.println(mensaje + ":");

		return teclado.next().toString();

	}

	public static void main(String[] args) {

		correo = "cruz.miriam@uabc.edu.mx";
		password = "password";
		remitente = "Miriam F. Cruz Sanchez";
		// INICIO DE SESION
		Correo.createSession(correo, password);

		// CREACION DE MENSAJE
		corrDes = "cruz.miriam@uabc.edu.mx";// getInformation("Ingrese correo destino"); //
		asunto = "Hola";// getInformation("Cual sera el asunto de su correo");
		Correo.createMail(corrDes, asunto, remitente);

	}

}
