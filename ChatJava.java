package progettoTpst;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.Socket;
import java.net.UnknownHostException;
import java.sql.Date;

import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

public class ChatJava extends Stage{

	TextArea chat = new TextArea();
	TextField messaggio = new TextField();
	OutputStreamWriter uscita;
	
	
	public ChatJava(BufferedReader e, OutputStreamWriter u) {
		uscita=u;
		Button Invia = new Button("Invia messaggio");
		
		GridPane gr = new GridPane();
		
		gr.add(chat, 0, 0, 2, 1);
		gr.add(messaggio, 0, 1);
		gr.add(Invia, 1, 1);
		
		Invia.setOnAction(ex->InviaMessaggio());

		gr.setPadding(new Insets(10, 10, 10, 10));
		gr.setHgap(10);
		gr.setVgap(10);

		Scene scene = new Scene(gr, 300, 250);
		this.setTitle("Chat");
		this.setScene(scene);
		this.show();

	}
	
	public void InviaMessaggio() {
		String testo=null;
		testo = messaggio.getText();
		Date data = new Date(System.currentTimeMillis());
		Socket connessione;	
		
		try {
			connessione = new Socket("192.168.1.31" , 7813); // Creo nuova connessione
			OutputStream uscitaByte;
			uscitaByte = connessione.getOutputStream();
			OutputStreamWriter uscita;
			uscita = new OutputStreamWriter(uscitaByte, "UTF-8");
			
			uscita.write("M#"+ "userName" + "#"+ testo + "#" + data + "\n");
			chat.setText("Eriko" + "#"+ testo + "#" + data);
			uscita.flush();
			
			
		} catch (UnknownHostException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}
}
