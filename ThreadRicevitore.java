package progettoTpst;

import java.io.BufferedReader;
import java.io.IOException;
import javafx.application.Platform;
import javafx.scene.control.ListView;

public class ThreadRicevitore extends Thread {
  BufferedReader br;
  ListView<String> elenco;
  String letto=null;
	
  public ThreadRicevitore(BufferedReader br, ListView<String> elenco) {
    this.br = br;
    this.elenco = elenco;
  }
  public void run() {
    do{
      try {
        letto = br.readLine();
        // letto sarà null se è stato raggiunta la fine dello stream
        // questo nel caso della comunicazione client/server succede non quando 
        // non ci sono ulteriori righe da leggere (nel qual caso aspetta), 
        // ma quando lo stream viene chiuso
        if(letto!=null){
	        Platform.runLater( ()->{
	          elenco.getItems().add(letto);
	        });
        }
      }catch (IOException e) {
        e.printStackTrace();
      }
    }while( letto!=null ); 
  }
}
