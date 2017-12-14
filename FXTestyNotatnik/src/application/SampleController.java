package application;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.swing.plaf.synth.SynthSeparatorUI;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.stage.FileChooser;
import javafx.stage.FileChooser.ExtensionFilter;
import javafx.stage.Stage;

public class SampleController {
	@FXML
	private Label label1;
	
	@FXML
	private Label label2;
	
	@FXML
	private TextField tytulPole;
	
	@FXML
	private TextArea trescPole;
	
	@FXML
	private Button zapiszBtn;
	
	@FXML
	private Button wczytajBtn;
	
	@FXML
	private Button resetujBtn;
	
	
//	public void zapisz(ActionEvent event) {
//	}
	
	
	public void initialize() {
		
		zapiszBtn.setOnAction(new EventHandler<ActionEvent>() {
			@Override public void handle(ActionEvent e) {
				try {
					String czasZapisu = new SimpleDateFormat("dd-MM-yyyy HH-mm-ss").format(new Date());
					if (tytulPole.getText().isEmpty()) {	
						tytulPole.setText(czasZapisu);
					}
					String nazwaPliku = tytulPole.getText() + ".txt";
					String sciezkaPliku = "C:/Users/dd/Desktop/Java pliki/Notatnik/" + nazwaPliku;
					BufferedWriter bw = new BufferedWriter(new FileWriter(sciezkaPliku));
					bw.write(trescPole.getText());
					bw.close();
				} catch (IOException e1) {
					e1.printStackTrace();
				}
			}
		});
		
		wczytajBtn.setOnAction(new EventHandler<ActionEvent>() {
			@Override public void handle(ActionEvent e) {
				FileChooser fc = new FileChooser();
//				File miejscePliku = new File("C:/Users/dd/Desktop/Java pliki/Notatnik/cos");
//				fc.setInitialDirectory(miejscePliku);
				fc.getExtensionFilters().add(new ExtensionFilter("Plik tekstowy", "*.txt"));
				fc.setTitle("Wybierz plik");
				File selectedFile = fc.showOpenDialog(new Stage());
				
				try {
					BufferedReader br = new BufferedReader(new FileReader(selectedFile));
					String wierszZPliku = null;
					String tekstZPliku = null;
					while ((wierszZPliku = br.readLine()) != null) {
						if (tekstZPliku == null) {
							tekstZPliku = wierszZPliku;
						} else {
							tekstZPliku = tekstZPliku + "\n" + wierszZPliku;
						}
					}
					String nazwaPliku = selectedFile.getName();
					String czasAktualny = new SimpleDateFormat("dd-MM-yyyy HH-mm-ss").format(new Date());
					tytulPole.setText(nazwaPliku + " - wczytano: " + czasAktualny + ".txt");
					trescPole.setText(tekstZPliku);
					br.close();
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				
			}
		});
		
		resetujBtn.setOnAction(new EventHandler<ActionEvent>() {
			@Override public void handle(ActionEvent e) {
				String czasAktualny = new SimpleDateFormat("dd-MM-yyyy HH-mm-ss").format(new Date());
				trescPole.setText("");
				tytulPole.setText(czasAktualny);
			}
		});
	}
	
}
