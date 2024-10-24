//Mouath Masalmah,1220179 , mamoun nawahda
package Main;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
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
	protected TheList<Martyr> names = new TheList<Martyr>(16);
	private FileChooser filechooser = new FileChooser();
	private File file;

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

		insert.setOnAction(e -> {
			if (!(tapPane.getTabs().contains(Insert()))) {
				tapPane.getTabs().add(Insert());

			}
		});
		delete.setOnAction(e -> {
			if (!(tapPane.getTabs().contains(Delete()))) {
				tapPane.getTabs().add(Delete());

			}
		});
		display.setOnAction(e -> {
			if (!(tapPane.getTabs().contains(Display()))) {
				tapPane.getTabs().add(Display());

			}
		});

		search.setOnAction(e -> {
			if (!(tapPane.getTabs().contains(Search()))) {
				tapPane.getTabs().add(Search());

			}
		});
		Label TheTitle = new Label("MARTYR MENU");
		TheTitle.setFont(new Font(15));
		TheTitle.setPadding(new Insets(50));
		;

		load.setOnAction(e -> {

			filechooser.setTitle("Choose your file");
			filechooser.setInitialDirectory(new File("c:\\"));
			filechooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("File.csv", "*.csv"));
			file = filechooser.showOpenDialog(stage);
			if (file.exists()) {

				try {

					Scanner scan = new Scanner(file);
					names.clear();

					while (scan.hasNext()) {
						try {
							String line = scan.nextLine();

							String[] Split = line.split(",");

							String name = Split[0];
							int age = 0;
							if (Split[1] == null) {
								age = -1;
							} else {
								age = Integer.parseInt(Split[1]);
							}

							String eventLocation = Split[2];
							String dateDeath = Split[3];
							String gender = Split[4];

							Martyr saveName = new Martyr(name, age, eventLocation, dateDeath, gender);
							names.add(saveName);
							Tmasseg.setText("Well Done");
							Tmasseg.setTextFill(Color.BLACK);

						} catch (NumberFormatException ex) {
							Tmasseg.setText("We Can't find the file!");
							Tmasseg.setTextFill(Color.RED);
						} catch (Exception ex) {
							Tmasseg.setText("Done,with some problem");
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

	public Tab Insert() {
		Label LblResult = new Label("");

		TextField tfName = new TextField();
		tfName.setPromptText("Enter The Name");

		TextField tfAge = new TextField();
		tfAge.setPromptText("Enter The Age");

		TextField tfDate = new TextField();
		tfDate.setPromptText("Enter The Date of death");
		
		TextField tfEventLocation = new TextField();
		tfEventLocation.setPromptText("Enter The Event Location");
		
		TextField tfGender = new TextField();
		tfGender.setPromptText("Enter The Gender");

		Button butInsert = new Button("Insert");
		butInsert.setOnAction(e -> {
			try {
				int Age = Integer.parseInt(tfAge.getText());
				String DATE = tfDate.getText();
				String[] Split1 = DATE.split("/");
				boolean testName = true;
				for (int i = 0; i < names.getLength(); i++) {
					if (names.get(i).getName().compareTo(tfName.getText()) == 0) {
						testName = false;
					}
				}
				boolean testDate = true;
				for (int i = 0; i < DATE.length(); i++) {
					if (Split1.length != 3) {
						testDate = false;
						break;
					} else if ((DATE.charAt(i) >= 65 && DATE.charAt(i) <= 90)
							|| (DATE.charAt(i) >= 97 && DATE.charAt(i) <= 122)) {
						testDate = false;
						break;
					} else if (Integer.parseInt(Split1[0]) > 31 || Integer.parseInt(Split1[0]) <= 0
							|| (Integer.parseInt(Split1[2]) == 2024 && Integer.parseInt(Split1[0]) > 27)) {
						testDate = false;
						break;
					} else if (Integer.parseInt(Split1[1]) > 12 || Integer.parseInt(Split1[1]) <= 0
							|| (Integer.parseInt(Split1[2]) == 2024 && Integer.parseInt(Split1[1]) > 1)) {
						testDate = false;
						break;
					} else if (Integer.parseInt(Split1[2]) > 2024 || Integer.parseInt(Split1[2]) <= 0) {
						testDate = false;
						break;
					}

				}
				boolean testAge = false;
				if (Age >= 0 || Age <= 120) {
					testAge = true;
				}
				boolean testgender = false;
				if (tfGender.getText().equalsIgnoreCase("M") || tfGender.getText().equalsIgnoreCase("F")
						|| tfGender.getText().equalsIgnoreCase("NA")) {
					testgender = true;
				}

				if (testDate == true && testAge == true && testgender == true && testName == true) {
					Martyr newMartyr = new Martyr(tfName.getText(), Age, tfEventLocation.getText(), DATE,
							tfGender.getText());
					if (names.find(newMartyr) == -1) {
						names.add(newMartyr);
						printToFile();
						LblResult.setText("Add Successfuly");
					} else {
						LblResult.setText("Add not Successfuly");
					}
				} else {
					LblResult.setText("Add not Successfuly");
				}
			} catch (Exception ex) {
				LblResult.setText("Erorr");
			}

		});

		GridPane gpPane = new GridPane();
		gpPane.add(tfName, 1, 0);
		gpPane.add(tfAge, 1, 1);
		gpPane.add(tfEventLocation, 1, 2);
		gpPane.add(tfDate, 1, 3);
		gpPane.add(tfGender, 1, 4);
		gpPane.add(butInsert, 0, 2);
		gpPane.setAlignment(Pos.CENTER);
		gpPane.setHgap(10);
		gpPane.setVgap(10);
		LblResult.setAlignment(Pos.CENTER);
		LblResult.setPadding(new Insets(15));
		BorderPane InsertPane = new BorderPane();
		InsertPane.setCenter(gpPane);
		InsertPane.setBottom(LblResult);
		Tab InsertTap = new Tab("Insert");
		InsertTap.setContent(InsertPane);
		return InsertTap;
	}

	public Tab Display() {

		String Date = null;
		String Gender = null;
		Tab displayTap = new Tab("Display");
		Label LblResult = new Label();
		Button butDisplayByAge = new Button("Display The Number By Age");
		Button butDisplayByGender = new Button("Display The Number By Gender");
		Button butDisplayByDate = new Button("Display The Number By Date");
		TextField tfAge = new TextField();
		TextField tfGender = new TextField();
		TextField tfDate = new TextField();
		GridPane gpPane = new GridPane();
		tfAge.setPromptText("Enter The Age");
		tfGender.setPromptText("Enter The Gender (M,F)");
		tfDate.setPromptText("Enter The Date (d/m/y)");
		butDisplayByAge.setOnAction(e -> {
			try {
				int Age = 0;
				if (tfAge.getText() == null || tfAge.getText().length() == 0)
					Age = -1;

				else if (Integer.parseInt(tfAge.getText()) >= 0) {
					Age = Integer.parseInt(tfAge.getText());
					LblResult.setText("the display number is " + displayByAge(Age));
				} else
					LblResult.setText("Enter A true Age");

			} catch (Exception ex) {
				LblResult.setText("Enter a true value");
			}
		});

		butDisplayByDate.setOnAction(e -> {
			String DATE = tfDate.getText();
			if (DATE == null || DATE.isEmpty()) {
				LblResult.setText("Erorr");
			} else {
				LblResult.setText("the display number is" + displayByDate(DATE));
			}
		});
		butDisplayByGender.setOnAction(e -> {
			String GENDER = tfGender.getText();
			if (GENDER == null || GENDER.isEmpty()) {
				LblResult.setText("Erorr");
			} else {
				LblResult.setText("the display number is " + displayByGender(GENDER));
			}
		});

		gpPane.add(tfAge, 1, 0);
		gpPane.add(tfGender, 1, 1);
		gpPane.add(tfDate, 1, 2);
		gpPane.add(butDisplayByAge, 0, 0);
		gpPane.add(butDisplayByGender, 0, 1);
		gpPane.add(butDisplayByDate, 0, 2);
		gpPane.setAlignment(Pos.CENTER);
		gpPane.setHgap(10);
		gpPane.setVgap(10);
		LblResult.setAlignment(Pos.CENTER);
		LblResult.setPadding(new Insets(15));
		BorderPane displayPane = new BorderPane();
		displayPane.setCenter(gpPane);
		displayPane.setBottom(LblResult);
		displayTap.setContent(displayPane);
		return displayTap;

	}

	public Tab Delete() {
		Tab deleteTap = new Tab("Delete");
		Label LblResult = new Label();
		Button butDelete = new Button("Delete");
		TextField tfGender = new TextField();
		TextField tfName = new TextField();
		GridPane gpPane = new GridPane();
		tfGender.setPromptText("Enter The Gender ");
		tfName.setPromptText("Enter The Name");

		butDelete.setOnAction(e -> {
			try {
				String Name = tfName.getText();
				boolean result = false;
				for (int i = 0; i < names.getLength(); i++) {
					if (names.get(i).getName().compareTo(Name) == 0) {
						names.delete(i);
						result = true;
						LblResult.setText("Delete sucsseful");
						printToFile();
					}
				}
				if (result == false) {
					LblResult.setText("Delete not sucsseful");
				}
			} catch (InputMismatchException ex) {
				LblResult.setText("Enter a true age");
			} catch (NumberFormatException ex) {
				LblResult.setText("Enter a true values");

			} catch (NullPointerException ex) {
				LblResult.setText("Enter a true age");
			}
		});
		gpPane.add(tfName, 0, 0);
		gpPane.add(tfGender, 0, 1);
		gpPane.add(butDelete, 0, 2);
		gpPane.setHgap(10);
		gpPane.setVgap(10);
		gpPane.setAlignment(Pos.CENTER);
		BorderPane deletePane = new BorderPane();
		deletePane.setCenter(gpPane);
		deletePane.setBottom(LblResult);
		deletePane.setPadding(new Insets(15));
		deletePane.setAlignment(LblResult, Pos.CENTER);
		butDelete.setAlignment(Pos.CENTER);
		deleteTap.setContent(deletePane);
		return deleteTap;

	}

	public Tab Search() {
		Label LblResult = new Label("");

		TextField tfName = new TextField();
		tfName.setPromptText("Enter The Name");

		Button butSearch = new Button("Search");

		butSearch.setOnAction(e -> {
			boolean testSearch = false;
			for (int i = 0; i < names.getLength(); i++) {
				if (names.get(i).getName().equalsIgnoreCase(tfName.getText())) {
					LblResult.setText(names.get(i).toString());
					testSearch = true;
					break;
				}
			}
			if (testSearch == false) {
				LblResult.setText("we dot find it");
			}
		});

		GridPane gpPane = new GridPane();
		gpPane.add(tfName, 1, 0);
		gpPane.add(butSearch, 0, 0);
		gpPane.setAlignment(Pos.CENTER);
		gpPane.setHgap(10);
		gpPane.setVgap(10);
		LblResult.setAlignment(Pos.CENTER);
		LblResult.setPadding(new Insets(15));
		BorderPane SearchPane = new BorderPane();
		SearchPane.setCenter(gpPane);
		SearchPane.setBottom(LblResult);
		Tab SearchTap = new Tab("Search");
		SearchTap.setContent(SearchPane);
		return SearchTap;
	}

	public int displayByAge(int Age) {
		int countAge = 0;
		for (int i = 0; i < names.getLength(); i++) {
			if (Age == names.get(i).getAge()) {
				countAge++;
			}
		}
		return countAge;
	}

	public int displayByGender(String Gender) {
		int countGender = 0;
		for (int i = 0; i < names.getLength(); i++) {
			if (names.get(i).getGender().compareTo(Gender) == 0) {
				countGender++;
			}
		}
		return countGender;
	}

	public int displayByDate(String Date) {
		int countGender = 0;
		for (int i = 0; i < names.getLength(); i++) {
			if (names.get(i).compareToDate(Date) == 0) {
				countGender++;
			}

		}
		return countGender;
	}

	private void printToFile() {
		FileWriter File;
		try {
			File = new FileWriter(file);
			PrintWriter write = new PrintWriter(File);
			for (int i = 0; i < names.getLength(); i++) {
				write.println(
						names.get(i).getName() + "," + names.get(i).getAge() + "," + names.get(i).getEventLocation()
								+ "," + names.get(i).getDateDeath() + "," + names.get(i).getGender());

			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
}