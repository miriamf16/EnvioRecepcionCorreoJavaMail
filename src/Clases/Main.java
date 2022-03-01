package Clases;

import java.util.Scanner;

public class Main {

	static String correo = "";
	static String password = "";
	static String corrDes = "";
	static String asunto = "";
	static String remitente = "";
    static String host = "pop.gmail.com";// change accordingly
    static String protocolo = "pop3s";
    static Scanner teclado = new Scanner(System.in);

	public static String getInformation(String mensaje) {
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

		int opcion = 0;
		
		// INICIO DE SESION
		loginAcc();
		Correo.createSession(correo, password); // correo: correoprueba.daw@gmail.com contra: prueba 22
		
		while(opcion != 3)
		{
			System.out.println("\n\n --- MENU ---");
			System.out.println("1) Enviar correo ");
			System.out.println("2) Revisar correos ");
			System.out.println("3) Salir ");
			System.out.print("Seleccione una opcion: ");
			opcion = teclado.nextInt();
			
			if (opcion == 1) {
				// CREACION DE MENSAJE
				getData();
				Correo.createMail(corrDes, asunto, remitente);
			}
			
			if (opcion == 2) {
				// RECIBIR CORREOS
				Correo.receiveEmail(host, protocolo, correo, password); 
			}
		}
	}

}
