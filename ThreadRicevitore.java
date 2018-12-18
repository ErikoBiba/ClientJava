package progetto;

import java.io.BufferedReader;
import java.io.IOException;

import javafx.application.Platform;
import javafx.scene.control.TextArea;

public class ThreadRicevitore extends Thread {
	BufferedReader entrata;
	TextArea elenco;
	String letto = null;

	public ThreadRicevitore(BufferedReader entrata, TextArea elenco) {
		this.entrata = entrata;
		this.elenco = elenco;
	}

	public void run() {
		do {
			try {
				letto = entrata.readLine();
				System.out.println("ricevuto " + letto);
				if (letto != null) {
					Platform.runLater(() -> {
						elenco.appendText(letto + "/n");
					});
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
		} while (letto != null);
	}
}