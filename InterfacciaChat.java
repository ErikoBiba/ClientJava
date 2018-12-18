package progetto;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.Writer;
import java.net.UnknownHostException;
import java.time.LocalDate;
import java.time.LocalTime;

import javafx.application.Platform;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

public class InterfacciaChat extends Stage {
	TextArea chat = new TextArea();
	TextField messaggio = new TextField();
	BufferedReader ingresso;
	Writer uscita;
	
	public InterfacciaChat(BufferedReader ingresso, Writer uscita) {
		this.ingresso = ingresso;
		this.uscita = uscita;
		
		
		Button Invia = new Button("Invia messaggio");
		Button bChiudi= new Button ("Disconnetti Biba dal mondo");
		
		GridPane gr = new GridPane();
		
		gr.add(chat, 0, 0, 2, 1);
		gr.add(messaggio, 0, 1);
		gr.add(Invia, 1, 1);
		gr.add(bChiudi, 1, 2);
		
		Invia.setOnAction(e->InviaMessaggio());
		bChiudi.setOnAction(e-> DisconnettiBiba());
		
		gr.setPadding(new Insets(10, 10, 10, 10));
		gr.setHgap(10);
		gr.setVgap(10);

		Scene scene = new Scene(gr, 300, 250);
		this.setTitle("Chat");
		this.setScene(scene);
		
		ThreadRicevitore scriviLeggi  = new ThreadRicevitore(ingresso, chat);
		scriviLeggi.start();
	}
	
	public void InviaMessaggio() {
		LocalDate data = LocalDate.now();
		LocalTime ora =  LocalTime.now();
	

		
		try {
			
			uscita.write("M#"+ InterfacciaLogin.User  + "#"+ messaggio.getText() + "#" + data+ "#" + ora+ "\n");

			uscita.flush();
			
			
		} catch (UnknownHostException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}
	 public void DisconnettiBiba() {
		 Stage uscita=null;
		 Platform.exit();
		 System.exit(0);
		 uscita.close();
		 System.out.println("Biba chiuso");
	 }
	
}