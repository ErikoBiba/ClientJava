package progetto;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.Writer;
import java.net.UnknownHostException;
import java.sql.Date;

import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

public class InterfacciaChat extends Stage {
	ListView <String> chat = new ListView<>();
	TextField messaggio = new TextField();
	BufferedReader ingresso;
	Writer uscita;
	
	public InterfacciaChat(BufferedReader ingresso, Writer uscita) {
		this.ingresso = ingresso;
		this.uscita = uscita;
		
		
		Button Invia = new Button("Invia messaggio");
		
		GridPane gr = new GridPane();
		
		gr.add(chat, 0, 0, 2, 1);
		gr.add(messaggio, 0, 1);
		gr.add(Invia, 1, 1);
		
		Invia.setOnAction(e->InviaMessaggio());

		gr.setPadding(new Insets(10, 10, 10, 10));
		gr.setHgap(10);
		gr.setVgap(10);

		Scene scene = new Scene(gr, 300, 250);
		this.setTitle("Chat");
		this.setScene(scene);
		
		ThreadRicevitore ScriviLeggi  = new ThreadRicevitore(ingresso, chat);
		ScriviLeggi.start();
	}
	
	public void InviaMessaggio() {
		Date date2 = new Date(System.currentTimeMillis());

		
		try {
			
			uscita.write("M#"+ InterfacciaLogin.User + ": " + "#"+ messaggio.getText() + "#" + date2+ "\n");

			uscita.flush();
			
			
		} catch (UnknownHostException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}
}