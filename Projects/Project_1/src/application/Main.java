package application;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.Scanner;
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.NodeOrientation;
import javafx.geometry.Pos;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;

public class Main extends Application {
	private District dummy = new District("dummy");
	private DLinkedList<District> distractList = new DLinkedList(dummy);
	private FileChooser filechooser = new FileChooser();
	private File file;
	private DNode<District> selectedDis;
	private Node<Location> selectedLoca;
	private Label labDistrictName = new Label();

	@Override
	public void start(Stage stage) {
		Label Tmasseg = new Label();
		Tmasseg.setStyle("-fx-font-family: 'Cairo'; -fx-font-size: 20px; -fx-font-weight: bold;");
		Tmasseg.setPadding(new Insets(15));
		Button load = new Button("Load From File");
		load.setStyle("-fx-font-family: 'Cairo'; -fx-font-size: 15px;");
		BorderPane root = new BorderPane();
		Scene scene = new Scene(root, 400, 400);
		load.setOnAction(e -> {

			filechooser.setTitle("Choose your file");
			filechooser.setInitialDirectory(new File("c:\\"));
			filechooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("File.csv", "*.csv"));
			file = filechooser.showOpenDialog(stage);
			if (file != null) {
				if (file.exists()) {
					try {

						Scanner scan = new Scanner(file);
						while (scan.hasNext()) {
							try {
								String line = scan.nextLine();

								String[] splitLine = line.split(",");

								String name = splitLine[0];
								String dateDeath = splitLine[1];
								int age = 0;
								if (splitLine[2].equals("")) {
									age = -1;
								} else {
									age = Integer.parseInt(splitLine[2]);
								}
								String location = splitLine[3];
								String distirct = splitLine[4];
								String gender = splitLine[5];
								if (gender.equals("")) {
									gender = "N/A";
								}
								Martyr InsertMartyr = new Martyr(name, dateDeath, age, location, distirct, gender);
								District insertDistrict = new District(distirct);
								DNode<District> newDistrict = distractList.find(insertDistrict);
								if (newDistrict == null) {
									insertDistrict.insertMartyr(InsertMartyr);
									distractList.insert(insertDistrict);
								} else if (location == null) {
									insertDistrict = newDistrict.getData();
									insertDistrict.insertMartyr(InsertMartyr);
									distractList.update(newDistrict.getData(), insertDistrict);
								} else {
									newDistrict.getData().insertMartyr(InsertMartyr);
								}

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

					stage.close();
					distrctScreen(stage);
				}
			} else {
				Tmasseg.setText("the system did not find your file");
			}
		});

		root.setCenter(load);
		root.setBottom(Tmasseg);
		root.setAlignment(Tmasseg, Pos.CENTER);
		stage.setScene(scene);
		stage.show();
	}

	public void distrctScreen(Stage stage) {
		selectedDis = distractList.getHead();
		Label labTitle = new Label("District Screen");
		labTitle.setStyle("-fx-font-family: 'Cairo'; -fx-font-size: 20px; -fx-font-weight: bold;");
		labTitle.setPadding(new Insets(15));
		labTitle.setAlignment(Pos.CENTER);
		labDistrictName.setText("Distract Name: " + selectedDis);
		labDistrictName.setStyle("-fx-font-family: 'Cairo'; -fx-font-size: 13px; -fx-font-weight: bold;");
		labDistrictName.setAlignment(Pos.CENTER);
		Label labResult = new Label("");
		Label lblResultStatistics = new Label("");
		lblResultStatistics.setStyle("-fx-font-family: 'Cairo'; -fx-font-size: 13px; -fx-font-weight: bold;");
		Label lblForSize = new Label("قال رسول الله صلى الله عليه وسلم:\r\n" + " للشهيد عند الله سبع خصال: \r\n"
				+ "1- يغفر له في أول دفعة من دمه. \r\n" + "2- ويرى مقعده من الجنة. \r\n" + "3- ويحلى حلة الإيمان. \r\n"
				+ "4- ويزوج اثنتين وسبعين زوجة من الحور العين. \r\n" + "5- ويجار من عذاب القبر. \r\n"
				+ "6- ويأمن من الفزع الأكبر. \r\n" + "7- ويوضع على رأسه تاج الوقار، الياقوتة منه خير \r\n"
				+ "من الدنيا وما فيها. \r\n" + "8- ويشفع في سبعين إنسانًا من أهل بيته. \r\n");
		lblForSize.setNodeOrientation(NodeOrientation.RIGHT_TO_LEFT);
		lblForSize.setPadding(new Insets(20));
		lblForSize.setStyle("-fx-font-family: 'Cairo'; -fx-font-size: 13px; -fx-font-weight: bold;");
		labResult.setAlignment(Pos.CENTER);
		Button butInsertDistric = new Button("insert new district");
		Button butUpdateDistric = new Button("update a district");
		Button butDeleteDistric = new Button("delete a district");
		Button butGoPreviousDistric = new Button("Prev District");
		Button butGoNextDistric = new Button("Next District");
		Button butNumberOfMartyrs = new Button("number of martyrs for a date");
		Button butLocationScreen = new Button("load the location screen");
		butInsertDistric.setOnAction(e -> {
			stage.close();
			insertDistrict(stage);
		});
		butUpdateDistric.setOnAction(e -> {
			stage.close();
			UpdateDistrict(stage);
		});
		butDeleteDistric.setOnAction(e -> {
			stage.close();
			deleteDistrict(stage);
		});
		butNumberOfMartyrs.setOnAction(e -> {
			stage.close();
			searchByDate(stage);
		});
		butLocationScreen.setOnAction(e -> {
			if (selectedDis != null && selectedDis != distractList.getDummy()) {
				stage.close();
				locationScreen(stage);
			} else {
				labResult.setText("the List is Empty");
			}
		});
		butGoNextDistric.setOnAction(e -> {
			if (distractList.getHead() == distractList.getDummy()) {
				labResult.setText("the List is Empty");
			} else if (selectedDis.getNext() != null) {
				selectedDis = selectedDis.getNext();
				labDistrictName.setText("Distract Name: " + selectedDis);

				if (selectedDis != null && selectedDis != distractList.getDummy()) {
					lblResultStatistics.setText("\nThe total number of martyr: "
							+ selectedDis.getData().totalNumberOfMartyr() + "\nThe total number of male: "
							+ selectedDis.getData().totalNumberByGenderMale() + "\nThe total number of female: "
							+ selectedDis.getData().totalNumberByGenderFamale() + "\nThe average: "
							+ selectedDis.getData().averageAge() + "\n" + selectedDis.getData().getTheMaximumDate());
					labResult.setText("");
				}
			} else {
				labResult.setText("This is the last Distract");
				if (selectedDis.getPrev().equals(distractList.getDummy()) == true) {
					labResult.setText("This is the First Distract");
					if (selectedDis != null && selectedDis != distractList.getDummy()) {
						lblResultStatistics.setText("\nThe total number of martyr: "
								+ selectedDis.getData().totalNumberOfMartyr() + "\nThe total number of male: "
								+ selectedDis.getData().totalNumberByGenderMale() + "\nThe total number of female: "
								+ selectedDis.getData().totalNumberByGenderFamale() + "\nThe average: "
								+ selectedDis.getData().averageAge() + "\n" + selectedDis.getData().getTheMaximumDate());
					}
				}
			}
		});
		butGoPreviousDistric.setOnAction(e -> {
			if (distractList.getHead() == distractList.getDummy()) {
				labResult.setText("the List is Empty");
			} else if (selectedDis.getPrev().equals(distractList.getDummy()) == true) {
				labResult.setText("This is the First Distract");
				if (selectedDis != null && selectedDis != distractList.getDummy()) {
					lblResultStatistics.setText("\nThe total number of martyr: "
							+ selectedDis.getData().totalNumberOfMartyr() + "\nThe total number of male: "
							+ selectedDis.getData().totalNumberByGenderMale() + "\nThe total number of female: "
							+ selectedDis.getData().totalNumberByGenderFamale() + "\nThe average: "
							+ selectedDis.getData().averageAge() + "\n" + selectedDis.getData().getTheMaximumDate());
				}
			} else {
				selectedDis = selectedDis.getPrev();
				labDistrictName.setText("Distract Name: " + selectedDis);
				if (selectedDis != null && selectedDis != distractList.getDummy()) {
					lblResultStatistics.setText("\nThe total number of martyr: "
							+ selectedDis.getData().totalNumberOfMartyr() + "\nThe total number of male: "
							+ selectedDis.getData().totalNumberByGenderMale() + "\nThe total number of female: "
							+ selectedDis.getData().totalNumberByGenderFamale() + "\nThe average: "
							+ selectedDis.getData().averageAge() + "\n" + selectedDis.getData().getTheMaximumDate());
				}
				labResult.setText("");
			}
		});

		butInsertDistric.setPrefWidth(200);
		butUpdateDistric.setPrefWidth(200);
		butDeleteDistric.setPrefWidth(200);
		butNumberOfMartyrs.setPrefWidth(200);
		butLocationScreen.setPrefWidth(200);
		butGoNextDistric.setPrefWidth(100);
		butGoPreviousDistric.setPrefWidth(100);
		lblResultStatistics.setPrefWidth(350);
		lblResultStatistics.setPadding(new Insets(15));
		lblForSize.setPrefWidth(350);
		VBox butBox = new VBox(10, labDistrictName, butInsertDistric, butUpdateDistric, butDeleteDistric,
				butNumberOfMartyrs, butLocationScreen, labResult);
		HBox nextAndPrevBox = new HBox(10, butGoPreviousDistric, butGoNextDistric);
		butBox.setAlignment(Pos.CENTER);
		nextAndPrevBox.setAlignment(Pos.CENTER);
		labDistrictName.setAlignment(Pos.CENTER);
		labTitle.setAlignment(Pos.CENTER);
		nextAndPrevBox.setPadding(new Insets(10));
		BorderPane root = new BorderPane();
		root.setCenter(butBox);
		root.setTop(labTitle);
		root.setRight(lblForSize);
		root.setLeft(lblResultStatistics);
		root.setBottom(nextAndPrevBox);
		root.setAlignment(labTitle, Pos.CENTER);
		root.setAlignment(nextAndPrevBox, Pos.CENTER);
		root.setAlignment(lblForSize, Pos.CENTER);
		Scene scene = new Scene(root, 1100, 450);
		stage.setTitle("District Screen");
		stage.setScene(scene);
		stage.show();
	}

	public void insertDistrict(Stage stage) {
		Label LblResult = new Label("");

		TextField tfEnterDis = new TextField();
		tfEnterDis.setPromptText("Enter The District");
		Button butInsert = new Button("Insert");

		butInsert.setOnAction(e -> {
			if (tfEnterDis.getText().equals("")) {
				LblResult.setText("Enter a true value");
			} else {
				String Name = tfEnterDis.getText();
				District newDistract = new District(Name);
				int counterOfNumber = 0;
				for (int i = 0; i < Name.length(); i++) {
					if (Name.charAt(i) > 48 && Name.charAt(i) < 57) {
						counterOfNumber++;
					}
				}
				if (counterOfNumber == 0) {
					if (distractList.find(newDistract) == null) {
						distractList.insert(newDistract);
						LblResult.setText("Done");
					} else {
						LblResult.setText("The Distract is already added");
					}
				} else {
					LblResult.setText("Enter a true value");
				}
			}

		});
		Button back = new Button("Back");
		back.setOnAction(e -> {
			stage.close();
			distrctScreen(stage);
		});
		VBox buttVBox = new VBox(10, LblResult, back);
		buttVBox.setAlignment(Pos.CENTER);
		GridPane gpPane = new GridPane();
		gpPane.add(tfEnterDis, 1, 2);
		gpPane.add(butInsert, 0, 2);
		gpPane.setAlignment(Pos.CENTER);
		gpPane.setHgap(10);
		gpPane.setVgap(10);
		LblResult.setAlignment(Pos.CENTER);
		LblResult.setPadding(new Insets(15));
		BorderPane InsertPane = new BorderPane();
		InsertPane.setCenter(gpPane);
		InsertPane.setBottom(buttVBox);
		Scene scene = new Scene(InsertPane, 400, 200);
		stage.setTitle("Insert District");
		stage.setScene(scene);
		stage.show();

	}

	public void UpdateDistrict(Stage stage) {
		Label LblResult = new Label("");

		TextField tfEnterOldDis = new TextField();
		tfEnterOldDis.setPromptText("Enter The Old District Name");
		TextField tfEnterNewDis = new TextField();
		tfEnterNewDis.setPromptText("Enter The New District Name");
		Button butUpdate = new Button("Update");

		butUpdate.setOnAction(e -> {
			String oldName = tfEnterOldDis.getText();
			String newName = tfEnterNewDis.getText();
			if (oldName.equals("") || newName.equals("")) {
				LblResult.setText("Enter true value");
			} else {
				District oldDistractName = new District(oldName);
				DNode<District> oldDistrict = distractList.find(oldDistractName);
				if (oldDistrict != null) {
					int counterOfNumber = 0;
					for (int i = 0; i < newName.length(); i++) {
						if (newName.charAt(i) > 48 && newName.charAt(i) < 57) {
							counterOfNumber++;
						}
					}
					District newDistractName = new District(newName);
					DNode<District> newDistract = distractList.find(newDistractName);
					if (counterOfNumber == 0 && newDistract == null) {
						oldDistrict.getData().setDistrictName(newName);
						oldDistrict.getData().updateMartyrDistrict();
						LblResult.setText("Done");
					} else if (newDistract != null) {
						LblResult.setText("The new Name is in the system");
					} else {

						LblResult.setText("The new Name is not true");
					}
				}else {
					LblResult.setText("The Name is not in the system");
				}
			}
		});
		Button back = new Button("Back");
		back.setOnAction(e -> {
			stage.close();
			distrctScreen(stage);
		});
		VBox buttVBox = new VBox(10, LblResult, back);
		buttVBox.setAlignment(Pos.CENTER);
		GridPane gpPane = new GridPane();
		gpPane.add(tfEnterOldDis, 1, 2);
		gpPane.add(tfEnterNewDis, 2, 2);
		gpPane.add(butUpdate, 0, 2);
		gpPane.setAlignment(Pos.CENTER);
		gpPane.setHgap(10);
		gpPane.setVgap(10);
		LblResult.setAlignment(Pos.CENTER);
		LblResult.setPadding(new Insets(15));
		BorderPane InsertPane = new BorderPane();
		InsertPane.setCenter(gpPane);
		InsertPane.setBottom(buttVBox);
		Scene scene = new Scene(InsertPane, 400, 200);
		stage.setTitle("Update District");
		stage.setScene(scene);
		stage.show();

	}

	public void searchByDate(Stage stage) {
		Label LblResult = new Label("");

		TextField tfEnterDate = new TextField();
		tfEnterDate.setPromptText("Enter The Date");
		Button butSearch = new Button("Search");

		butSearch.setOnAction(e -> {

			try {
				String[] splitDate = tfEnterDate.getText().split("/");
				int day = Integer.parseInt(splitDate[1]);
				int month = Integer.parseInt(splitDate[0]) - 1;
				int year = Integer.parseInt(splitDate[2]);
				Calendar dateObject1 = new GregorianCalendar(year, month, day);
				LblResult.setText("The Number Of Martyr: "
						+ selectedDis.getData().totalNumberOfMartyrByDate(tfEnterDate.getText()));
			} catch (Exception ex) {
				LblResult.setText("Enter a true value");
			}

		});
		Button back = new Button("Back");
		back.setOnAction(e -> {
			stage.close();
			distrctScreen(stage);
		});
		VBox buttVBox = new VBox(10, LblResult, back);
		buttVBox.setAlignment(Pos.CENTER);
		GridPane gpPane = new GridPane();
		gpPane.add(tfEnterDate, 1, 2);
		gpPane.add(butSearch, 0, 2);
		gpPane.setAlignment(Pos.CENTER);
		gpPane.setHgap(10);
		gpPane.setVgap(10);
		LblResult.setAlignment(Pos.CENTER);
		LblResult.setPadding(new Insets(15));
		BorderPane InsertPane = new BorderPane();
		InsertPane.setCenter(gpPane);
		InsertPane.setBottom(buttVBox);
		Scene scene = new Scene(InsertPane, 400, 200);
		stage.setTitle("Search By Date");
		stage.setScene(scene);
		stage.show();

	}

	public void deleteDistrict(Stage stage) {
		Label LblResult = new Label("");

		TextField tfEnterDis = new TextField();
		tfEnterDis.setPromptText("Enter The District");
		Button butDelete = new Button("Delete");

		butDelete.setOnAction(e -> {
			District newDistract = new District(tfEnterDis.getText());

			if (distractList.delete(newDistract) != null) {
				LblResult.setText("Done");
			} else {
				LblResult.setText("The Distract is not in the system");
			}

		});
		Button back = new Button("Back");
		back.setOnAction(e -> {
			stage.close();
			distrctScreen(stage);
		});
		VBox buttVBox = new VBox(10, LblResult, back);
		buttVBox.setAlignment(Pos.CENTER);
		GridPane gpPane = new GridPane();
		gpPane.add(tfEnterDis, 1, 2);
		gpPane.add(butDelete, 0, 2);
		gpPane.setAlignment(Pos.CENTER);
		gpPane.setHgap(10);
		gpPane.setVgap(10);
		LblResult.setAlignment(Pos.CENTER);
		LblResult.setPadding(new Insets(15));
		BorderPane InsertPane = new BorderPane();
		InsertPane.setCenter(gpPane);
		InsertPane.setBottom(buttVBox);
		Scene scene = new Scene(InsertPane, 400, 200);
		stage.setTitle("Insert District");
		stage.setScene(scene);
		stage.show();

	}

	public void locationScreen(Stage stage) {
		selectedLoca = selectedDis.getData().getList().getHead();
		Label labTitle = new Label("Location Screen");
		labTitle.setStyle("-fx-font-family: 'Cairo'; -fx-font-size: 20px; -fx-font-weight: bold;");
		labTitle.setPadding(new Insets(15));
		labTitle.setAlignment(Pos.CENTER);
		Label labLocationName = new Label("Location Name: " + selectedLoca);
		labLocationName.setStyle("-fx-font-family: 'Cairo'; -fx-font-size: 13px; -fx-font-weight: bold;");
		labLocationName.setAlignment(Pos.CENTER);
		Label labResult = new Label("");
		labResult.setAlignment(Pos.CENTER);
		Button butInsertLocation = new Button("insert new location");
		Button butUpdateLocation = new Button("update a location");
		Button butDeleteLocation = new Button("delete a location");
		Button butStatisticsLocations = new Button("locations statistics");
		Button butGoNextLocation = new Button("Next location");
		Button butInsertANewMartyr = new Button("insert a new martyr");
		Button butUpdateAMartyr = new Button("update a martyr");
		Button butDeleteAMartyr = new Button("delete a martyr");
		Button butSearchForMartyr = new Button("search for martyr");
		Button butGoPreviousPage = new Button("Back to District");
		butInsertANewMartyr.setOnAction(e -> {

		});
		butInsertLocation.setOnAction(e -> {
			stage.close();
			insertLocation(stage);
		});
		butUpdateLocation.setOnAction(e -> {
			stage.close();
			UpdateLocation(stage);
		});
		butDeleteLocation.setOnAction(e -> {
			stage.close();
			deleteLocation(stage);
		});
		butStatisticsLocations.setOnAction(e -> {
			stage.close();
			locationStatistics(stage);
		});
		butInsertANewMartyr.setOnAction(e -> {
			stage.close();
			insetrMartyrScrean(stage);
		});
		butDeleteAMartyr.setOnAction(e -> {
			stage.close();
			deleteMartyr(stage);
		});
		butUpdateAMartyr.setOnAction(e -> {
			stage.close();
			UpdateMartyrScrean(stage);
		});
		butSearchForMartyr.setOnAction(e -> {
			stage.close();
			searchByPartOfName(stage);
		});
		butGoNextLocation.setOnAction(e -> {
			if (selectedDis.getData().getList().getHead() == selectedDis.getData().getList().getDummy()) {
				labResult.setText("the List is Empty");
			} else if (selectedLoca != null && selectedLoca.getNext() != null) {
				selectedLoca = selectedLoca.getNext();
				labLocationName.setText("Location Name: " + selectedLoca);
			} else {
				selectedLoca = selectedDis.getData().getList().getHead();
				labLocationName.setText("Location Name: " + selectedLoca);
			}
		});
		butGoPreviousPage.setOnAction(e -> {
			stage.close();
			distrctScreen(stage);
		});
		butInsertLocation.setPrefWidth(200);
		butUpdateLocation.setPrefWidth(200);
		butDeleteLocation.setPrefWidth(200);
		butStatisticsLocations.setPrefWidth(200);
		butInsertANewMartyr.setPrefWidth(200);
		butDeleteAMartyr.setPrefWidth(200);
		butUpdateAMartyr.setPrefWidth(200);
		butSearchForMartyr.setPrefWidth(200);
		butGoNextLocation.setPrefWidth(100);
		butGoPreviousPage.setPrefWidth(100);
		VBox butBox = new VBox(10, labLocationName, butInsertLocation, butUpdateLocation, butDeleteLocation,
				butStatisticsLocations, butInsertANewMartyr, butDeleteAMartyr, butUpdateAMartyr, butSearchForMartyr,
				labResult);
		butBox.setAlignment(Pos.CENTER);
		HBox nextAndPrevBox = new HBox(10, butGoPreviousPage, butGoNextLocation);
		nextAndPrevBox.setAlignment(Pos.CENTER);

		nextAndPrevBox.setPadding(new Insets(10));
		BorderPane root = new BorderPane();
		root.setCenter(butBox);
		root.setTop(labTitle);
		root.setBottom(nextAndPrevBox);
		root.setAlignment(labTitle, Pos.CENTER);
		root.setAlignment(labResult, Pos.CENTER);
		Scene scene = new Scene(root, 400, 500);
		stage.setTitle("Location Screen");
		stage.setScene(scene);
		stage.show();
	}

	public void insertLocation(Stage stage) {
		selectedLoca = selectedDis.getData().getList().getHead();
		Label LblResult = new Label("");

		TextField tfEnterLoca = new TextField();
		tfEnterLoca.setPromptText("Enter The Location");
		Button butInsert = new Button("Insert");

		butInsert.setOnAction(e -> {
			Location newLocation = new Location(tfEnterLoca.getText());
			if (tfEnterLoca.getText().equals("")) {
				LblResult.setText("Enter a true value");
			} else {
				boolean testName = true;
				for (int i = 0; i < tfEnterLoca.getText().length(); i++) {
					if (tfEnterLoca.getText().charAt(i) > 48 && tfEnterLoca.getText().charAt(i) < 57) {
						testName = false;
						break;
					}
				}
				if (selectedDis.getData().getList().find(newLocation) == null && testName == true) {
					selectedDis.getData().getList().insert(newLocation);
					LblResult.setText("Done");
				} else if (testName == false) {
					LblResult.setText("Enter a true value");
				} else {

					LblResult.setText("The Location is already added");
				}
			}
		});
		Button back = new Button("Back");
		back.setOnAction(e -> {
			stage.close();
			locationScreen(stage);
		});
		VBox buttVBox = new VBox(10, LblResult, back);
		buttVBox.setAlignment(Pos.CENTER);
		GridPane gpPane = new GridPane();
		gpPane.add(tfEnterLoca, 1, 2);
		gpPane.add(butInsert, 0, 2);
		gpPane.setAlignment(Pos.CENTER);
		gpPane.setHgap(10);
		gpPane.setVgap(10);
		LblResult.setAlignment(Pos.CENTER);
		LblResult.setPadding(new Insets(15));
		BorderPane InsertPane = new BorderPane();
		InsertPane.setCenter(gpPane);
		InsertPane.setBottom(buttVBox);
		Scene scene = new Scene(InsertPane, 400, 200);
		stage.setTitle("Insert Location");
		stage.setScene(scene);
		stage.show();

	}

	public void UpdateLocation(Stage stage) {
		Label LblResult = new Label("");

		TextField tfEnterOldLocation = new TextField();
		tfEnterOldLocation.setPromptText("Enter The Old Location Name");
		TextField tfEnterNewLocation = new TextField();
		tfEnterNewLocation.setPromptText("Enter The New Location Name");
		Button butUpdate = new Button("Update");

		butUpdate.setOnAction(e -> {
			boolean testName = true;
			if (tfEnterNewLocation.getText().equals("") || tfEnterOldLocation.getText().equals("")) {
				LblResult.setText("Enter a true value");
			} else {
				for (int i = 0; i < tfEnterNewLocation.getText().length(); i++) {
					if (tfEnterNewLocation.getText().charAt(i) > 48 && tfEnterNewLocation.getText().charAt(i) < 57) {
						testName = false;
						break;
					}
				}
				if (testName == true) {
					Location oldLocation = new Location(tfEnterOldLocation.getText());
					if (selectedDis.getData().updateMartyrLocation(oldLocation, tfEnterNewLocation.getText()) == true)
						LblResult.setText("Done");
					else
						LblResult.setText("The new Name is in the system");
				} else {
					LblResult.setText("Enter a true value");
				}
			}
		});
		Button back = new Button("Back");
		back.setOnAction(e -> {
			stage.close();
			locationScreen(stage);
		});
		VBox buttVBox = new VBox(10, LblResult, back);
		buttVBox.setAlignment(Pos.CENTER);
		GridPane gpPane = new GridPane();
		gpPane.add(tfEnterOldLocation, 1, 2);
		gpPane.add(tfEnterNewLocation, 2, 2);
		gpPane.add(butUpdate, 0, 2);
		gpPane.setAlignment(Pos.CENTER);
		gpPane.setHgap(10);
		gpPane.setVgap(10);
		LblResult.setAlignment(Pos.CENTER);
		LblResult.setPadding(new Insets(15));
		BorderPane InsertPane = new BorderPane();
		InsertPane.setCenter(gpPane);
		InsertPane.setBottom(buttVBox);
		Scene scene = new Scene(InsertPane, 400, 200);
		stage.setTitle("updete Location");
		stage.setScene(scene);
		stage.show();

	}

	public void deleteLocation(Stage stage) {
		Label LblResult = new Label("");

		TextField tfEnterDis = new TextField();
		tfEnterDis.setPromptText("Enter The Location");
		Button butDelete = new Button("Delete");

		butDelete.setOnAction(e -> {
			Location newLocation = new Location(tfEnterDis.getText());

			if (selectedDis.getData().getList().delete(newLocation) != false) {
				LblResult.setText("Done");
			} else {
				LblResult.setText("The Location is not in the system");
			}

		});
		Button back = new Button("Back");
		back.setOnAction(e -> {
			stage.close();
			locationScreen(stage);
		});
		VBox buttVBox = new VBox(10, LblResult, back);
		buttVBox.setAlignment(Pos.CENTER);
		GridPane gpPane = new GridPane();
		gpPane.add(tfEnterDis, 1, 2);
		gpPane.add(butDelete, 0, 2);
		gpPane.setAlignment(Pos.CENTER);
		gpPane.setHgap(10);
		gpPane.setVgap(10);
		LblResult.setAlignment(Pos.CENTER);
		LblResult.setPadding(new Insets(15));
		BorderPane InsertPane = new BorderPane();
		InsertPane.setCenter(gpPane);
		InsertPane.setBottom(buttVBox);
		Scene scene = new Scene(InsertPane, 400, 200);
		stage.setTitle("delete Location");
		stage.setScene(scene);
		stage.show();

	}

	public void insetrMartyrScrean(Stage stage) {
		Label LblResult = new Label("");

		TextField tfName = new TextField();
		tfName.setPromptText("Enter The Name");

		TextField tfDate = new TextField();
		tfDate.setPromptText("Enter The Date of death");

		TextField tfAge = new TextField();
		tfAge.setPromptText("Enter The Age");

		TextField tfGender = new TextField();
		tfGender.setPromptText("Enter The Gender");

		Button butInsert = new Button("Insert");
		butInsert.setOnAction(e -> {
			try {
				boolean testName = true;
				String nameMartyr = tfName.getText();
				String[] splitName = nameMartyr.split(" ");
				if (splitName.length >= 3) {
					for (int i = 0; i < nameMartyr.length(); i++) {
						if (nameMartyr.charAt(i) > 48 && nameMartyr.charAt(i) < 57) {
							testName = false;
							break;
						}
					}
				} else {
					testName = false;
				}
				String date = tfDate.getText();
				String[] Split1 = date.split("/");

				boolean testDate = true;

				for (int i = 0; i < date.length(); i++) {
					if (Split1.length != 3) {
						testDate = false;
						break;
					} else if ((date.charAt(i) >= 65 && date.charAt(i) <= 90)
							|| (date.charAt(i) >= 97 && date.charAt(i) <= 122)) {
						testDate = false;
						break;
					} else if (Integer.parseInt(Split1[1]) > 31 || Integer.parseInt(Split1[1]) <= 0
							|| (Integer.parseInt(Split1[2]) == 2024 && Integer.parseInt(Split1[1]) > 30)) {
						testDate = false;
						break;
					} else if (Integer.parseInt(Split1[0]) > 12 || Integer.parseInt(Split1[0]) <= 0
							|| (Integer.parseInt(Split1[2]) == 2024 && Integer.parseInt(Split1[0]) > 4)) {
						testDate = false;
						break;
					} else if (Integer.parseInt(Split1[2]) > 2024 || Integer.parseInt(Split1[2]) <= 0) {
						testDate = false;
						break;
					}

				}

				int Age;
				if (tfAge.getText().equals("")) {
					Age = -1;
				} else {
					Age = Integer.parseInt(tfAge.getText());
				}

				boolean testAge = false;
				if (Age >= 0 && Age <= 120) {
					testAge = true;
				} else if (tfAge.getText().equals("")) {
					Age = -1;
					testAge = true;
				}
				boolean testgender = false;
				if (tfGender.getText().equalsIgnoreCase("M") || tfGender.getText().equalsIgnoreCase("F")
						|| tfGender.getText().equalsIgnoreCase("N/A")) {
					testgender = true;
				}

				if (testDate == true && testAge == true && testgender == true && testName == true) {
					Martyr newMartyr = new Martyr(tfName.getText(), date, Age, selectedLoca.getData().getLocationName(),
							selectedDis.getData().getDistrictName(), tfGender.getText().toUpperCase());
					if (selectedLoca.getData().getMartyrList().find(newMartyr) == null) {
						selectedLoca.getData().getMartyrList().insert(newMartyr);

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
		Button butBack = new Button("Back");
		butBack.setOnAction(e -> {
			stage.close();
			locationScreen(stage);
		});
		VBox lblAndButBackBox = new VBox(10, LblResult, butBack);
		lblAndButBackBox.setAlignment(Pos.CENTER);
		GridPane gpPane = new GridPane();
		gpPane.add(tfName, 1, 0);
		gpPane.add(tfAge, 1, 2);
		gpPane.add(tfDate, 1, 1);
		gpPane.add(tfGender, 1, 3);
		gpPane.add(butInsert, 1, 4);
		gpPane.setAlignment(Pos.CENTER);
		gpPane.setHgap(10);
		gpPane.setVgap(10);
		gpPane.setAlignment(Pos.CENTER);
		LblResult.setAlignment(Pos.CENTER);
		LblResult.setPadding(new Insets(15));
		BorderPane InsertPane = new BorderPane();
		InsertPane.setCenter(gpPane);
		InsertPane.setBottom(lblAndButBackBox);
		InsertPane.setAlignment(lblAndButBackBox, Pos.CENTER);
		Scene scene = new Scene(InsertPane, 400, 250);
		stage.setTitle("insert Martyr");
		stage.setScene(scene);
		stage.show();
	}

	public void locationStatistics(Stage stage) {
		Label LblResult = new Label("");

		TextField tfEnterLocation = new TextField();
		tfEnterLocation.setPromptText("Enter The location name");
		Button butSearch = new Button("Search");

		butSearch.setOnAction(e -> {
			Node<Location> searchLocation = selectedDis.getData().getList().getHead();
			for (; searchLocation != null
					&& searchLocation != selectedDis.getData().getList().getDummy(); searchLocation = searchLocation
							.getNext()) {
				if (searchLocation.getData().getLocationName().equalsIgnoreCase(tfEnterLocation.getText())) {
					LblResult.setText("\nThe total number of martyr: " + searchLocation.getData().totalNumberOfMartyr()
							+ "\nThe total number of male: " + searchLocation.getData().totalNumberByGenderMale()
							+ "\nThe total number of female: " + searchLocation.getData().totalNumberByGenderFamale()
							+ "\nThe average: " + searchLocation.getData().averageAge() + "\n"
							+ searchLocation.getData().youngAndOldMartyr());
					break;
				}
			}
		});
		Button back = new Button("Back");
		back.setOnAction(e -> {
			stage.close();
			locationScreen(stage);
		});

		GridPane gpPane = new GridPane();
		gpPane.add(tfEnterLocation, 1, 2);
		gpPane.add(butSearch, 0, 2);
		gpPane.setAlignment(Pos.CENTER);
		gpPane.setHgap(10);
		gpPane.setVgap(10);
		VBox buttVBox = new VBox(10, gpPane, back);
		buttVBox.setAlignment(Pos.CENTER);
		LblResult.setAlignment(Pos.CENTER);
		LblResult.setPadding(new Insets(15));
		BorderPane SearchPane = new BorderPane();
		SearchPane.setCenter(LblResult);
		SearchPane.setBottom(buttVBox);
		Scene scene = new Scene(SearchPane, 800, 400);
		stage.setTitle("Location Statistics");
		stage.setScene(scene);
		stage.show();
	}

	public void searchByPartOfName(Stage stage) {
		Label LblResult = new Label("");

		TextField tfEnterDis = new TextField();
		tfEnterDis.setPromptText("Enter The name or part of the name");
		Button butSearch = new Button("Search");

		butSearch.setOnAction(e -> {
			if (selectedLoca != null) {
				String result = selectedLoca.getData().searchByPartOfName(tfEnterDis.getText());
				LblResult.setText(result);
			} else {
				LblResult.setText("their is no location in the district");
			}
		});
		Button back = new Button("Back");
		back.setOnAction(e -> {
			stage.close();
			locationScreen(stage);
		});
		ScrollPane scpData = new ScrollPane();
		scpData.setContent(LblResult);
		GridPane gpPane = new GridPane();
		gpPane.add(tfEnterDis, 1, 2);
		gpPane.add(butSearch, 0, 2);
		gpPane.setAlignment(Pos.CENTER);
		gpPane.setHgap(10);
		gpPane.setVgap(10);
		VBox buttVBox = new VBox(10, gpPane, back);
		buttVBox.setAlignment(Pos.CENTER);
		LblResult.setAlignment(Pos.CENTER);
		LblResult.setPadding(new Insets(15));
		BorderPane SearchPane = new BorderPane();
		SearchPane.setCenter(scpData);
		SearchPane.setBottom(buttVBox);
		Scene scene = new Scene(SearchPane, 900, 450);
		stage.setTitle("search by martyr name");
		stage.setScene(scene);
		stage.show();
	}

	public void deleteMartyr(Stage stage) {
		Label LblResult = new Label("");

		TextField tfEnterName = new TextField();
		tfEnterName.setPromptText("Enter The Martyr Name");
		Button butDelete = new Button("Delete");

		butDelete.setOnAction(e -> {

			if (selectedLoca.getData().deleteMartyer(tfEnterName.getText()) == true) {
				LblResult.setText("Done");
			} else {
				LblResult.setText("The Martyr is not in the system");
			}

		});
		Button back = new Button("Back");
		back.setOnAction(e -> {
			stage.close();
			locationScreen(stage);
		});
		VBox buttVBox = new VBox(10, LblResult, back);
		buttVBox.setAlignment(Pos.CENTER);
		GridPane gpPane = new GridPane();
		gpPane.add(tfEnterName, 1, 2);
		gpPane.add(butDelete, 0, 2);
		gpPane.setAlignment(Pos.CENTER);
		gpPane.setHgap(10);
		gpPane.setVgap(10);
		LblResult.setAlignment(Pos.CENTER);
		LblResult.setPadding(new Insets(15));
		BorderPane InsertPane = new BorderPane();
		InsertPane.setCenter(gpPane);
		InsertPane.setBottom(buttVBox);
		Scene scene = new Scene(InsertPane, 400, 200);
		stage.setTitle("delete Martyer");
		stage.setScene(scene);
		stage.show();

	}

	public void UpdateMartyrScrean(Stage stage) {
		Label LblResult = new Label("");
		TextField tfEnterSelection = new TextField();
		tfEnterSelection.setPromptText("Enter The update");
		TextField tfEnterName = new TextField();
		tfEnterName.setPromptText("Enter The Martyr Name");
		Button butUpdate = new Button("Update");
		ComboBox<String> selectComBox = new ComboBox<>();
		selectComBox.getItems().setAll("Name", "Date", "Age", "Gender");
		butUpdate.setOnAction(e -> {
			Node<Martyr> martyr = selectedLoca.getData().findMartyerByName(tfEnterName.getText());
			if (martyr != null) {
				try {
					switch (selectComBox.getValue()) {
					case "Name":
						boolean testName = true;
						String nameMartyr = tfEnterSelection.getText();
						String[] splitName = nameMartyr.split(" ");
						if (splitName.length >= 3) {
							for (int i = 0; i < nameMartyr.length(); i++) {
								if (nameMartyr.charAt(i) > 48 && nameMartyr.charAt(i) < 57) {
									testName = false;
									break;
								}
							}
						} else {
							testName = false;
						}
						if (testName == true) {
							martyr.getData().setName(nameMartyr);
							LblResult.setText("Done");
						} else {
							LblResult.setText("We the new Name is not true");
						}
						break;
					case "Date":
						String date = tfEnterSelection.getText();
						String[] Split1 = date.split("/");

						boolean testDate = true;
						for (int i = 0; i < date.length(); i++) {
							if (Split1.length != 3) {
								testDate = false;
								break;
							} else if ((date.charAt(i) >= 65 && date.charAt(i) <= 90)
									|| (date.charAt(i) >= 97 && date.charAt(i) <= 122)) {
								testDate = false;
								break;
							} else if (Integer.parseInt(Split1[1]) > 31 || Integer.parseInt(Split1[1]) <= 0
									|| (Integer.parseInt(Split1[2]) == 2024 && Integer.parseInt(Split1[1]) > 30)) {
								testDate = false;
								break;
							} else if (Integer.parseInt(Split1[0]) > 12 || Integer.parseInt(Split1[0]) <= 0
									|| (Integer.parseInt(Split1[2]) == 2024 && Integer.parseInt(Split1[0]) > 4)) {
								testDate = false;
								break;
							} else if (Integer.parseInt(Split1[2]) > 2024 || Integer.parseInt(Split1[2]) <= 0) {
								testDate = false;
								break;
							}
						}

						if (testDate == true) {
							martyr.getData().setDate(date);
							LblResult.setText("Done");
						} else {
							LblResult.setText("We the new Date is not true");
						}
						break;
					case "Age":
						int Age = Integer.parseInt(tfEnterSelection.getText());

						boolean testAge = false;
						if (Age >= 0 && Age <= 120) {
							testAge = true;
						} else if (tfEnterSelection.getText().equals("")) {
							Age = -1;
							testAge = true;
						}
						if (testAge == true) {
							martyr.getData().setAge(Age);
							LblResult.setText("Done");
						} else {
							LblResult.setText("We the new Age is not true");
						}
						break;
					case "Gender":
						boolean testgender = false;
						if (tfEnterSelection.getText().equalsIgnoreCase("M")
								|| tfEnterSelection.getText().equalsIgnoreCase("F")
								|| tfEnterSelection.getText().equalsIgnoreCase("N/A")) {
							testgender = true;
						}
						if (testgender == true) {
							martyr.getData().setGender(tfEnterSelection.getText().toUpperCase());
							LblResult.setText("Done");
						} else {
							LblResult.setText("We the new Gender is not true");
						}
						break;
					default:
						LblResult.setText("Error");
					}
				} catch (Exception ex) {
					LblResult.setText("Error");
				}
			} else {
				LblResult.setText("The Martyr is not in the system");
			}

		});
		Button back = new Button("Back");
		back.setOnAction(e -> {
			stage.close();
			locationScreen(stage);
		});
		VBox buttVBox = new VBox(10, LblResult, back);
		buttVBox.setAlignment(Pos.CENTER);
		GridPane gpPane = new GridPane();
		gpPane.add(selectComBox, 0, 1);
		gpPane.add(tfEnterSelection, 1, 1);
		gpPane.add(tfEnterName, 1, 2);
		gpPane.add(butUpdate, 0, 2);
		gpPane.setAlignment(Pos.CENTER);
		gpPane.setHgap(10);
		gpPane.setVgap(10);
		LblResult.setAlignment(Pos.CENTER);
		LblResult.setPadding(new Insets(15));
		BorderPane InsertPane = new BorderPane();
		InsertPane.setCenter(gpPane);
		InsertPane.setBottom(buttVBox);
		Scene scene = new Scene(InsertPane, 400, 200);
		stage.setTitle("update Martyer");
		stage.setScene(scene);
		stage.show();

	}

	public boolean updateMartyer(Martyr martyer) {
		Node<Martyr> updateMartyrNode = selectedLoca.getData().getMartyrList().find(martyer);
		if (updateMartyrNode == null) {
			return false;
		}
		selectedLoca.getData().getMartyrList().delete(updateMartyrNode.getData());
		selectedLoca.getData().getMartyrList().insert(martyer);
		return true;
	}

	public static void main(String[] args) {
		launch(args);
	}
}
