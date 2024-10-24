package Main;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.InputMismatchException;
import java.util.Iterator;
import java.util.Scanner;
import javafx.application.Application;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.control.TableCell;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;

public class Main extends Application {
	TheList<Martyr> names = new TheList<Martyr>(16);

	@Override
	public void start(Stage stage) {
		TabPane tapPane = new TabPane();
		Tab MainTap = new Tab("Menu");
		tapPane.getTabs().add(MainTap);
		Tab insertTap = new Tab("Insert");
		Tab deleteTap = new Tab("Delete");
		Tab searchTap = new Tab("Search by name");
		Label Tmasseg = new Label();
		Tmasseg.setFont(new Font(15));
		Button display = new Button("Display the number of martyrs");
		Button insert = new Button("Insert a new martyr");
		Button delete = new Button("Delete a martyr using name");
		Button search = new Button("Search by name");
		Button load = new Button("Load the file");
		insert.setPrefWidth(200);
		delete.setPrefWidth(200);
		search.setPrefWidth(200);
		display.setPrefWidth(200);

		display.setOnAction(e -> {
			if (!(tapPane.getTabs().contains(Display(names)))) {
				tapPane.getTabs().add(Display(names));

			}
		});
		Label TheTitle = new Label("MARTYR MENU");
		TheTitle.setFont(new Font(15));
		TheTitle.setPadding(new Insets(50));
		;

		load.setOnAction(e -> {

			FileChooser filechooser = new FileChooser();
			filechooser.setTitle("Choose your file");
			filechooser.setInitialDirectory(new File("c:\\"));
			filechooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("File.csv", "*.csv"));
			File TheFile = filechooser.showOpenDialog(stage);
			if (TheFile.exists()) {

				try {
					Scanner scan = new Scanner(TheFile);

					while (scan.hasNext()) {
						try {
							String line = scan.nextLine();

							String[] Split = line.split(",");

							String name = Split[0];
							int age = 0;
							if(Split[1]==null){
								age =-1;
							}else{
								age	=Integer.parseInt(Split[1]);
							}

							String eventLocation = Split[2];
							String dateDeath = Split[3];
							String gender = Split[4];

							Martyr saveName = new Martyr(name, age, eventLocation, dateDeath, gender);
							Tmasseg.setText("Well Done");
							Tmasseg.setTextFill(Color.BLACK);

						} catch (NumberFormatException ex) {
							Tmasseg.setText("We Can't find the file!");
							Tmasseg.setTextFill(Color.RED);
						}
					}
					scan.close();
				} catch (NumberFormatException ex) {
					Tmasseg.setText("We Can't find the file!");
					Tmasseg.setTextFill(Color.RED);
				} catch (FileNotFoundException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}

		});
		BorderPane MainPane = new BorderPane();
		VBox MainButton = new VBox(10, insert, delete, search, display);
		MainButton.setAlignment(Pos.CENTER);
		VBox LoadAndMasseg = new VBox(10, Tmasseg, load);

		MainPane.setCenter(MainButton);
		MainPane.setAlignment(MainButton, Pos.CENTER);

		LoadAndMasseg.setPadding(new Insets(10));
		LoadAndMasseg.setAlignment(Pos.CENTER);

		MainPane.setBottom(LoadAndMasseg);
		MainPane.setAlignment(LoadAndMasseg, Pos.CENTER);
		MainPane.setTop(TheTitle);
		MainPane.setAlignment(TheTitle, Pos.CENTER);
		MainTap.setContent(MainPane);
		Scene scene = new Scene(tapPane, 400, 400);
		stage.setScene(scene);
		stage.show();
	}

	public static void main(String[] args) {
		launch(args);
	}

	public Tab Display(TheList<Martyr> names) {

		int Age = 0;
		String Date = null;
		String Gender = null;
		Tab displayTap = new Tab("Display");
		Label LblResult = new Label();
		Button butDisplay = new Button("Display The Number");
		TextField tfAge = new TextField();
		TextField tfGender = new TextField();
		TextField tfDate = new TextField();
		GridPane gpPane = new GridPane();
		tfAge.setPromptText("Enter The Age");
		tfGender.setPromptText("Enter The Gender (M,F)");
		tfDate.setPromptText("Enter The Date (d/m/y)");
		try {

			Age = Integer.parseInt(tfAge.getText());
			Date = tfDate.getText();
			Gender = tfGender.getText();
			if (Age >= 0) {
				if (Age != 0 && Date != null && Gender != null) {
					String[] spliString = Date.split("/");
					if (spliString.length == 3) {

					}

				} else if (Age != 0 && Date != null && Gender == null) {

				} else if (Age != 0 && Date == null && Gender != null) {

				} else if (Age != 0 && Date == null && Gender == null) {

				} else if (Age == 0 && Date != null && Gender != null) {

				} else if (Age == 0 && Date != null && Gender == null) {

				} else if (Age == 0 && Date == null && Gender != null) {

				} else if (Age == 0 && Date == null && Gender == null) {

				} else {

				}

			} else {
				LblResult.setText("Enter A true Age");
			}
			gpPane.add(tfAge, 1, 0);
			gpPane.add(tfGender, 1, 1);
			gpPane.add(tfDate, 1, 2);
			gpPane.add(butDisplay, 0, 1);
			gpPane.setAlignment(Pos.CENTER);
			gpPane.setHgap(10);
			gpPane.setVgap(10);
			LblResult.setAlignment(Pos.CENTER);
			LblResult.setPadding(new Insets(15));
			BorderPane displayPane = new BorderPane();
			displayPane.setCenter(gpPane);
			displayPane.setBottom(LblResult);
			displayTap.setContent(displayPane);

		} catch (InputMismatchException e) {
			LblResult.setText("");
		}
		return displayTap;
	}

	public Tab Delete(TheList<Martyr> names) {
		Tab deleteTap = new Tab("Display");
		Label LblResult = new Label();
		Button butDisplay = new Button("Display The Number");
		TextField tfAge = new TextField();
		TextField tfGender = new TextField();
		TextField tfDate = new TextField();
		GridPane gpPane = new GridPane();
		tfAge.setPromptText("Enter The Age");
		tfGender.setPromptText("Enter The Gender (M,F)");
		tfDate.setPromptText("Enter The Date (d/m/y)");

		return deleteTap;
	}

}
