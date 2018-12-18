package progetto;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.Socket;
import java.net.UnknownHostException;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

public class InterfacciaLogin extends Application {
	Label eUsername = new Label("Username:");
	Label ePassword = new Label("Password:");
	TextField cUsername = new TextField();
	PasswordField cPassword = new PasswordField();
	public static String User;


	BufferedReader ricevuta;

	@Override
	public void start(Stage primaryStage) {

		Button pInvia = new Button("Invia");

		GridPane gr = new GridPane();

		gr.add(eUsername, 0, 0);
		gr.add(ePassword, 0, 1);

		gr.add(cUsername, 1, 0);
		gr.add(cPassword, 1, 1);

		gr.add(pInvia, 1, 3);

		pInvia.setOnAction(e -> invia());

		gr.setPadding(new Insets(10, 10, 10, 10));
		gr.setHgap(10);
		gr.setVgap(10);

		Scene scene = new Scene(gr, 300, 250);
		primaryStage.setTitle("Login");
		primaryStage.setScene(scene);
		primaryStage.show();

	}

	public void invia() {
		Socket connessione;
		User = cUsername.getText();
		String Password = cPassword.getText();

		try {
			connessione = new Socket("192.168.1.82", 7813);
			OutputStream uscitaByte;
			uscitaByte = connessione.getOutputStream();
			OutputStreamWriter uscita;
			uscita = new OutputStreamWriter(uscitaByte, "UTF-8");
			uscita.write("R#" + User + "#" + Password + "\n");
			uscita.flush();
			InputStream entrataByte;
			entrataByte = connessione.getInputStream();
			InputStreamReader entrata;
			entrata = new InputStreamReader(entrataByte, "UTF-8");
			BufferedReader bEntrata = new BufferedReader(entrata);
			String b = bEntrata.readLine();
			System.out.println(b + "\n");

		
			System.out.println("Credenziali inviate");

			if (b.equals("L#Si")) {
				InterfacciaChat finestra = new InterfacciaChat(bEntrata, uscita);
				finestra.show();

			} else {
				Alert errorAlert = new Alert(AlertType.ERROR);
				errorAlert.setHeaderText("Login errato");
				errorAlert.setContentText("Utente o password sbagliata");
				errorAlert.showAndWait();
			}

		} catch (UnknownHostException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public static void main(String[] args) {
		launch(args);
	}

}
