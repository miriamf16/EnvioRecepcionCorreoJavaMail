package Clases;

import java.util.Properties;

import javax.activation.CommandMap;
import javax.activation.MailcapCommandMap;
import javax.mail.Folder;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.NoSuchProviderException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Store;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

public class Correo {

	
	// gmail
	static Properties props = new Properties();
	private static Session session;
	private static Message message;

	static String correoRemitente;
	static String password;

	public static void createSession(String correo, String password) {

		correoRemitente = correo;

		/**** 1: CONFIGURACION DE PROPIEDADES DEL server SMTP de gmail *****/

		props.put("mail.smtp.auth", "true"); // Requiere autenticaciï¿½n
		props.put("mail.smtp.starttls.enable", "true");
		props.put("mail.smtp.host", "smtp.gmail.com"); // Direcciï¿½n del servidor SMTP de Gmail
		props.put("mail.smtp.port", "587"); // Puerto SMTP de Gmail (TLS): 587

		/**** 2:CREACION DE SESION Y LLAMADO DE METODO DE AUTENTIFICACION ****/

		session = Session.getDefaultInstance(props, new javax.mail.Authenticator() {
			protected PasswordAuthentication getPasswordAuthentication() {
				return new PasswordAuthentication(correo, password);
			}
		});

	}

	public static void createMail(String correoDestino, String asunto, String nombreRemitente) {

		/**** 3: CREACION DE ASUNTO ,CUERPO Y CONFIGURACION DE REMITENTE *****/

		try {
			String textBody = "<h2> <center> CORREO INSTITUCIONAL</center> </h2> </br> "
					+ "<h2> <center>UNIVERSIDAD AUTONOMA DE BAJA CALIFORNIA </center> </h2> </br> " + "<p>  </p>"
					+ "<h3>Atentamente</h3>" + nombreRemitente // cambiar por tu nombre
					+ "<h4> SALUDOS </h4> </hr>";

			message = new MimeMessage(session);
			message.setSubject(asunto); // Asunto
			message.setFrom(new InternetAddress(correoRemitente));// Correo Remitente
			message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(correoDestino)); // correo destino

			MailcapCommandMap mc = (MailcapCommandMap) CommandMap.getDefaultCommandMap();
			mc.addMailcap("text/html;; x-Java-content-handler=com.Sun.mail.handlers.text_html");
			mc.addMailcap("text/xml;; x-Java-content-handler=com.Sun.mail.handlers.text_xml");
			mc.addMailcap("text/plain;; x-Java-content-handler=com.Sun.mail.handlers.text_plain");
			mc.addMailcap("multipart/*;; x-Java-content-handler=com.Sun.mail.handlers.multipart_mixed");
			mc.addMailcap("message/rfc822;; x-Java-content-handler=com.Sun.mail.handlers.message_rfc822");

			CommandMap.setDefaultCommandMap(mc);

			message.setContent(textBody, "text/html");
			// message contiene todo el contenido de nuestro correo
		} catch (MessagingException e) {
			e.printStackTrace();
		}
		try {

			// ENVIO DE CORREO POR EL Mï¿½TODO SEND
			// DE LA CLASE TRANSPORT
			// DE LA API JavaMail

			Transport.send(message);

			System.out.println("Correo enviado existosamente");

		} catch (MessagingException e) {
			e.printStackTrace();
		}

	}
	
	
	public static void receiveEmail(String host, String protocol, String user, String password) {
		try {
			//crea el objeto store y se conecta con el servidor
			Store store = session.getStore(protocol);

		    store.connect(host, user, password);

		    //crea el objeto de carpeta y lo abre
		    Folder emailFolder = store.getFolder("INBOX");
		    emailFolder.open(Folder.READ_ONLY);

		    // recuperar los mensajes de la carpeta en una matriz e imprimirlos
		    Message[] messages = emailFolder.getMessages();

		    for (int i = 0, n = messages.length; i < n; i++) {
		    	Message message = messages[i];
		        System.out.println("\nEmail Number " + (i + 1));
		        System.out.println("Subject: " + message.getSubject());
		        System.out.println("From: " + message.getFrom()[0]);
		        System.out.println("Text: " + message.getContent().toString());
		      }

		    //Cerrar store y objeto carpeta
		    emailFolder.close(false);
		    store.close();

		    } catch (NoSuchProviderException e) {
		    	e.printStackTrace();
		    } catch (MessagingException e) {
		        e.printStackTrace();
		    } catch (Exception e) {
		       e.printStackTrace();
		    }
	}

}
