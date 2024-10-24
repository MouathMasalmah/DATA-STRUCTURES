package application;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Optional;
import java.util.Scanner;
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.NodeOrientation;
import javafx.geometry.Pos;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;

public class Main extends Application {
	private QuadraticOHash<MartyrDate> martyrDateTable = new QuadraticOHash<>(5);
	private FileChooser filechooser = new FileChooser();
	private File file;
	private HNode<MartyrDate> selectedMartyrDate;
	private Label labMartyrDate = new Label();
	private String changeTheFirstString;
	private String changeTheSecondString;
	private Martyr changeMartyr;
	private linkedList<String> allDistrict = new linkedList<>();

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
						String line = scan.nextLine().trim();
						while (scan.hasNext()) {
							try {
								line = scan.nextLine().trim();

								String[] splitLine = line.split(",");

								String name = splitLine[0];
								String dateDeath = splitLine[1];
								int age = 0;
								if (splitLine[2].equals("") || splitLine[2] == null) {
									age = -1;
								} else {
									age = Integer.parseInt(splitLine[2].trim());
								}
								String location = splitLine[3];
								String distirct = splitLine[4];
								String gender = splitLine[5];
								Martyr InsertMartyr = new Martyr(name, age, location, distirct, gender);
								MartyrDate insertDate = new MartyrDate(dateDeath.trim());
								HNode<MartyrDate> newDate = martyrDateTable.find(insertDate);
								if (newDate == null) {
									insertDate.getMartyrTree().insert(InsertMartyr);
									martyrDateTable.add(insertDate);
									if (allDistrict.find(distirct.trim()) == null)
										allDistrict.insert(distirct.trim());
								} else {
									newDate.getData().getMartyrTree().insert(InsertMartyr);
									if (allDistrict.find(distirct.trim()) == null)
										allDistrict.insert(distirct.trim());
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
					martyrDateScreen(stage);
				}
			} else {
				Tmasseg.setText("the system did not find your file");
			}

		});

		root.setCenter(load);
		root.setBottom(Tmasseg);
		BorderPane.setAlignment(Tmasseg, Pos.CENTER);
		stage.setScene(scene);
		stage.show();
	}

	public void martyrDateScreen(Stage stage) {
		int sum = 0;
		for (int i = 0; i < martyrDateTable.getTable().length; i++) {
			if (martyrDateTable.getTable()[i].getFlag() != Flag.EMPTY) {
				sum++;
			}
		}
		;
		System.out.println(sum);
		System.out.println(martyrDateTable.getTable().length);
		System.out.println(martyrDateTable.getSize());
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

		Label labTitle = new Label("Date Screen");
		labTitle.setStyle("-fx-font-family: 'Cairo'; -fx-font-size: 20px; -fx-font-weight: bold;");
		labTitle.setPadding(new Insets(15));
		labTitle.setAlignment(Pos.CENTER);
		if (selectedMartyrDate == null)
			selectedMartyrDate = martyrDateTable.getNextNode();
		if (selectedMartyrDate != null) {
			if (selectedMartyrDate.getData().getMartyrTree().Size() != 0) {
				labMartyrDate.setText("date: " + selectedMartyrDate.getData());
				lblResultStatistics
						.setText("\nThe number of martyr: " + selectedMartyrDate.getData().getMartyrTree().Size()
								+ "\nThe average: " + selectedMartyrDate.getData().avgAge() + "\nThe youngest: "
								+ selectedMartyrDate.getData().youngest().getName() + "\nThe oldest: "
								+ selectedMartyrDate.getData().oldest().getName() + "\nThe max district: "
								+ maxDistrict() + "\nThe max location: " + maxLocation());
			} else {
				labMartyrDate.setText("date: " + selectedMartyrDate.getData());
				lblResultStatistics.setText("\nThe number of martyr: "
						+ selectedMartyrDate.getData().getMartyrTree().Size() + "\nThe average: "
						+ selectedMartyrDate.getData().avgAge() + "\nThe youngest: No One" + "\nThe oldest: No One"
						+ "\nThe max district: No any District" + "\nThe max location: No any Location");
			}
		}
		labMartyrDate.setStyle("-fx-font-family: 'Cairo'; -fx-font-size: 13px; -fx-font-weight: bold;");
		labMartyrDate.setAlignment(Pos.CENTER);
		Button butInsertDate = new Button("insert new Date");
		Button butUpdateDate = new Button("update a date");
		Button butDeleteDate = new Button("delete a date");
		Button butPrintDate = new Button("print the dates");
		Button butGoPrefDate = new Button("Up date");
		Button butGoNextDate = new Button("Down date");
		Button butMartyrScreen = new Button("load the Martyr screen");
		butInsertDate.setOnAction(e -> {
			stage.close();
			insertDateScrean(stage);
		});
		butUpdateDate.setOnAction(e -> {
			stage.close();
			UpdateDate(stage);
		});
		butDeleteDate.setOnAction(e -> {
			stage.close();
			deleteDate(stage);
		});
		butPrintDate.setOnAction(e -> {
			stage.close();
			printTaple(stage);
		});
		butMartyrScreen.setOnAction(e -> {
			if (selectedMartyrDate != null) {
				stage.close();
				martyrScreen(stage);
			}
		});
		butGoNextDate.setOnAction(e -> {
			if (martyrDateTable.getSize() > 0) {
				HNode<MartyrDate> testNull = martyrDateTable.getNextNode();
				if (testNull == null) {
					labResult.setText("is Last Date");
				} else {
					selectedMartyrDate = testNull;
					if (selectedMartyrDate.getData().getMartyrTree().Size() != 0) {
						labMartyrDate.setText("date: " + selectedMartyrDate.getData());
						lblResultStatistics.setText(
								"\nThe number of martyr: " + selectedMartyrDate.getData().getMartyrTree().Size()
										+ "\nThe average: " + selectedMartyrDate.getData().avgAge() + "\nThe youngest: "
										+ selectedMartyrDate.getData().youngest().getName() + "\nThe oldest: "
										+ selectedMartyrDate.getData().oldest().getName() + "\nThe max district: "
										+ maxDistrict() + "\nThe max location: " + maxLocation());
					} else {
						labMartyrDate.setText("date: " + selectedMartyrDate.getData());
						lblResultStatistics.setText("\nThe number of martyr: "
								+ selectedMartyrDate.getData().getMartyrTree().Size() + "\nThe average: "
								+ selectedMartyrDate.getData().avgAge() + "\nThe youngest: No One"
								+ "\nThe oldest: No One" + "\nThe max district: No any District"
								+ "\nThe max location: No any Location");
					}
					labResult.setText("");
				}
			} else {
				labResult.setText("The Taple is Empty");
			}

		});
		butGoPrefDate.setOnAction(e -> {
			if (martyrDateTable.getSize() > 0) {
				HNode<MartyrDate> testNull = martyrDateTable.getPrefNode();

				if (testNull == null) {

					labResult.setText("is first Date");
				} else {
					selectedMartyrDate = testNull;
					if (selectedMartyrDate.getData().getMartyrTree().Size() != 0) {
						labMartyrDate.setText("date: " + selectedMartyrDate.getData());
						lblResultStatistics.setText(
								"\nThe number of martyr: " + selectedMartyrDate.getData().getMartyrTree().Size()
										+ "\nThe average: " + selectedMartyrDate.getData().avgAge() + "\nThe youngest: "
										+ selectedMartyrDate.getData().youngest().getName() + "\nThe oldest: "
										+ selectedMartyrDate.getData().oldest().getName() + "\nThe max district: "
										+ maxDistrict() + "\nThe max location: " + maxLocation());
					} else {
						labMartyrDate.setText("date: " + selectedMartyrDate.getData());
						lblResultStatistics.setText("\nThe number of martyr: "
								+ selectedMartyrDate.getData().getMartyrTree().Size() + "\nThe average: "
								+ selectedMartyrDate.getData().avgAge() + "\nThe youngest: No One"
								+ "\nThe oldest: No One" + "\nThe max district: No any District"
								+ "\nThe max location: No any Location");
					}
					labResult.setText("");
				}
			} else {
				labResult.setText("The Taple is Empty");
			}

		});
		stage.setOnCloseRequest(event -> {
			event.consume();

			Alert exitAlert = new Alert(Alert.AlertType.CONFIRMATION);
			exitAlert.setTitle("Confirm Exit");
			exitAlert.setHeaderText("Are you sure you want to exit?");
			exitAlert.setContentText("Choose your option.");
			Optional<ButtonType> result = exitAlert.showAndWait();

			if (result.isPresent() && result.get() == ButtonType.OK) {

				Alert saveAlert = new Alert(Alert.AlertType.CONFIRMATION);
				saveAlert.setTitle("Confirm Save");
				saveAlert.setHeaderText("Do you want to save the data in a file?");
				saveAlert.setContentText("Choose your option.");
				saveAlert.getButtonTypes().setAll(ButtonType.YES, ButtonType.NO);

				result = saveAlert.showAndWait();
				if (result.isPresent() && result.get() == ButtonType.YES) {
					insertToFile();
				}
				stage.close();
			}
		});
		butInsertDate.setPrefWidth(200);
		butUpdateDate.setPrefWidth(200);
		butDeleteDate.setPrefWidth(200);
		butPrintDate.setPrefWidth(200);
		butMartyrScreen.setPrefWidth(200);
		butGoNextDate.setPrefWidth(100);
		butGoPrefDate.setPrefWidth(100);
		lblResultStatistics.setPrefWidth(350);
		lblResultStatistics.setPadding(new Insets(15));
		lblForSize.setPrefWidth(350);
		VBox butBox = new VBox(10, labMartyrDate, butInsertDate, butUpdateDate, butDeleteDate, butPrintDate,
				butMartyrScreen, labResult);
		HBox nextAndPrevBox = new HBox(10, butGoPrefDate, butGoNextDate);
		butBox.setAlignment(Pos.CENTER);
		nextAndPrevBox.setAlignment(Pos.CENTER);
		labMartyrDate.setAlignment(Pos.CENTER);
		labTitle.setAlignment(Pos.CENTER);
		nextAndPrevBox.setPadding(new Insets(10));
		BorderPane root = new BorderPane();
		root.setCenter(butBox);
		root.setTop(labTitle);
		root.setRight(lblForSize);
		root.setLeft(lblResultStatistics);
		root.setBottom(nextAndPrevBox);
		BorderPane.setAlignment(labTitle, Pos.CENTER);
		BorderPane.setAlignment(nextAndPrevBox, Pos.CENTER);
		BorderPane.setAlignment(lblForSize, Pos.CENTER);
		Scene scene = new Scene(root, 1100, 450);
		stage.setTitle("Date Screen");
		stage.setScene(scene);
		stage.show();
	}

	public void insertDateScrean(Stage stage) {
		Label LblResult = new Label("");
		Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
		DatePicker datePicker = new DatePicker();
		datePicker.setEditable(false);
		datePicker.setOnAction(event -> {
			try {
				LocalDate date = datePicker.getValue();
				DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MM/dd/yyyy");
				String formattedDate = date.format(formatter);
				setTheFirstChangeString(formattedDate);
			} catch (DateTimeParseException ex) {
				LblResult.setText("Enter True Date");
			}
		});
		Button butInsert = new Button("Insert");
		butInsert.setOnAction(e -> {
			alert.setTitle("Confirmation Dialog");
			alert.setHeaderText("Are you sure you want to add the Date?");
			Optional<ButtonType> result = alert.showAndWait();
			if (result.isPresent() && result.get() == ButtonType.OK) {
				if (getTheFirstChangeString() == null) {
					LblResult.setText("Enter a true value");
				} else {
					String date = getTheFirstChangeString();
					MartyrDate newDate = new MartyrDate(date);

					if (date != null && martyrDateTable.find(newDate) == null) {
						if (cheackTheDate() == true) {
							martyrDateTable.add(newDate);
							LblResult.setText("Done");
						} else
							LblResult.setText("Wrong, Enter The Date less than or equal 6/30/2024");
					} else if (date == null) {
						LblResult.setText("Wrong, Enter The Date!");
					} else {

						LblResult.setText("The Date is already added");
					}

				}
			}

		});
		Button back = new Button("Back");
		back.setOnAction(e -> {
			alert.setTitle("Confirmation Dialog");
			alert.setHeaderText("Are you sure you want to close the window?");
			alert.setContentText("You will lose all the data you entered");
			Optional<ButtonType> result = alert.showAndWait();
			if (result.isPresent() && result.get() == ButtonType.OK) {
				stage.close();
				martyrDateScreen(stage);
			}

		});
		stage.setOnCloseRequest(event -> {
			event.consume();

			Alert exitAlert = new Alert(Alert.AlertType.CONFIRMATION);
			exitAlert.setTitle("Confirm Exit");
			exitAlert.setHeaderText("Are you sure you want to exit?");
			exitAlert.setContentText("Choose your option.");
			Optional<ButtonType> result = exitAlert.showAndWait();

			if (result.isPresent() && result.get() == ButtonType.OK) {

				Alert saveAlert = new Alert(Alert.AlertType.CONFIRMATION);
				saveAlert.setTitle("Confirm Save");
				saveAlert.setHeaderText("Do you want to save the data in a file?");
				saveAlert.setContentText("Choose your option.");
				saveAlert.getButtonTypes().setAll(ButtonType.YES, ButtonType.NO);

				result = saveAlert.showAndWait();
				if (result.isPresent() && result.get() == ButtonType.YES) {
					insertToFile();
				}
				stage.close();
			}
		});
		VBox buttVBox = new VBox(10, LblResult, back);
		buttVBox.setAlignment(Pos.CENTER);
		GridPane gpPane = new GridPane();
		gpPane.add(datePicker, 1, 2);
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
		stage.setTitle("Insert Date");
		stage.setScene(scene);
		stage.show();

	}

	public void UpdateDate(Stage stage) {
		setTheFirstChangeString("");
		setTheSecondChangeString("");
		Label LblResult = new Label("");
		Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
		ComboBox<String> comboList = new ComboBox<>();

		for (int i = 0; i < martyrDateTable.getTable().length; i++) {
			if (martyrDateTable.getTable()[i].getFlag() == Flag.FULL) {
				comboList.getItems().add(martyrDateTable.getTable()[i].getData().getDate());
			}
		}
		DatePicker datePicker = new DatePicker();
		datePicker.setEditable(false);
		datePicker.setOnAction(event -> {
			try {
				LocalDate date = datePicker.getValue();
				DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MM/dd/yyyy");
				String formattedDate = date.format(formatter);
				setTheSecondChangeString(formattedDate);
			} catch (DateTimeParseException ex) {
				LblResult.setText("Enter True Date");
			}
		});
		Button butUpdate = new Button("Update");

		comboList.setOnAction(e -> {
			if (comboList.getValue() != null)
				setTheFirstChangeString(comboList.getValue().trim());
		});
		butUpdate.setOnAction(e -> {
			alert.setTitle("Confirmation Dialog");
			alert.setHeaderText("Are you sure you want to update the this Date?");
			Optional<ButtonType> result = alert.showAndWait();
			if (result.isPresent() && result.get() == ButtonType.OK) {
				String oldName = getTheFirstChangeString();
				String newName = getTheSecondChangeString();
				if (oldName == null || oldName.equals("")) {
					LblResult.setText("select Date");
				} else if (newName == null || newName.equals("")) {
					LblResult.setText("Enter a true value for the new date");
				} else {

					MartyrDate oldDate = new MartyrDate(oldName);
					HNode<MartyrDate> oldDateNode = martyrDateTable.find(oldDate);
					if (oldDateNode != null) {
						if (cheackTheDate() == true) {
							MartyrDate newDate = new MartyrDate(newName);
							HNode<MartyrDate> newDateNode = martyrDateTable.find(newDate);
							if (newDateNode != null) {
								if (newDateNode.getFlag() == Flag.FULL) {
									oldDateNode.getData().getMartyrTree().traverseInOrder();
									QStack<TNode<Martyr>> tempStack = oldDateNode.getData().getMartyrTree()
											.getNextStack();
									while (!tempStack.isEmpty()) {
										newDateNode.getData().getMartyrTree().insert(tempStack.pop().getData());
									}
									martyrDateTable.delete(oldDateNode.getData());
									comboList.getItems().clear();
									for (int i = 0; i < martyrDateTable.getTable().length; i++) {
										if (martyrDateTable.getTable()[i].getFlag() == Flag.FULL) {
											comboList.getItems().add(martyrDateTable.getTable()[i].getData().getDate());
										}
									}

								} else if (newDateNode.getFlag() == Flag.DELETED) {
									newDateNode.getData().setMartyrTree(oldDateNode.getData().getMartyrTree());
									newDateNode.setFlag(Flag.FULL);
									martyrDateTable.delete(oldDateNode.getData());
								}
								LblResult.setText("Done");
							} else if (!getTheSecondChangeString().equals("") && newDateNode == null) {
								newDate.setMartyrTree(oldDateNode.getData().getMartyrTree());
								martyrDateTable.add(newDate);
								martyrDateTable.delete(oldDateNode.getData());
								comboList.getItems().clear();
								for (int i = 0; i < martyrDateTable.getTable().length; i++) {
									if (martyrDateTable.getTable()[i].getFlag() == Flag.FULL) {
										comboList.getItems().add(martyrDateTable.getTable()[i].getData().getDate());
									}
								}
								LblResult.setText("Done");
							} else {

								LblResult.setText("The new Date is not true");
							}
						} else {
							LblResult.setText("Wrong, Enter The Date less than or equal 6/30/2024");
						}
					} else {
						LblResult.setText("The Date is not in the system");
					}
				}
			}
		});
		Button back = new Button("Back");
		back.setOnAction(e -> {
			alert.setTitle("Confirmation Dialog");
			alert.setHeaderText("Are you sure you want to close the window?");
			alert.setContentText("You will lose all the data you entered");
			Optional<ButtonType> result = alert.showAndWait();
			if (result.isPresent() && result.get() == ButtonType.OK) {
				stage.close();
				martyrDateScreen(stage);
			}
		});
		stage.setOnCloseRequest(event -> {
			event.consume();

			Alert exitAlert = new Alert(Alert.AlertType.CONFIRMATION);
			exitAlert.setTitle("Confirm Exit");
			exitAlert.setHeaderText("Are you sure you want to exit?");
			exitAlert.setContentText("Choose your option.");
			Optional<ButtonType> result = exitAlert.showAndWait();

			if (result.isPresent() && result.get() == ButtonType.OK) {

				Alert saveAlert = new Alert(Alert.AlertType.CONFIRMATION);
				saveAlert.setTitle("Confirm Save");
				saveAlert.setHeaderText("Do you want to save the data in a file?");
				saveAlert.setContentText("Choose your option.");
				saveAlert.getButtonTypes().setAll(ButtonType.YES, ButtonType.NO);

				result = saveAlert.showAndWait();
				if (result.isPresent() && result.get() == ButtonType.YES) {
					insertToFile();
				}
				stage.close();
			}
		});
		VBox buttVBox = new VBox(10, LblResult, back);
		buttVBox.setAlignment(Pos.CENTER);
		GridPane gpPane = new GridPane();
		gpPane.add(comboList, 1, 2);
		gpPane.add(datePicker, 2, 2);
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
		stage.setTitle("Update Date");
		stage.setScene(scene);
		stage.show();

	}

	public void deleteDate(Stage stage) {
		setTheFirstChangeString("");
		Label LblResult = new Label("");
		Button butDelete = new Button("Delete");
		ComboBox<String> comboList = new ComboBox<>();
		Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
		for (int i = 0; i < martyrDateTable.getTable().length; i++) {
			if (martyrDateTable.getTable()[i].getFlag() == Flag.FULL) {
				comboList.getItems().add(martyrDateTable.getTable()[i].getData().getDate());
			}
		}
		comboList.setOnAction(e -> {
			if (comboList.getValue() != null)
				setTheFirstChangeString(comboList.getValue().trim());
		});
		butDelete.setOnAction(e -> {
			if (!getTheFirstChangeString().equals("")) {
				MartyrDate newDate = new MartyrDate(getTheFirstChangeString().trim());
				HNode<MartyrDate> selectedDate = martyrDateTable.find(newDate);
				alert.setTitle("Confirmation Dialog");
				alert.setHeaderText("Are you sure you want to delete the Date?");
				Optional<ButtonType> result = alert.showAndWait();
				if (result.isPresent() && result.get() == ButtonType.OK) {
					if (selectedDate != null && martyrDateTable.delete(selectedDate.getData()) != null) {
						LblResult.setText("Done");
						comboList.getItems().clear();
						for (int i = 0; i < martyrDateTable.getTable().length; i++) {
							if (martyrDateTable.getTable()[i].getFlag() == Flag.FULL) {
								comboList.getItems().add(martyrDateTable.getTable()[i].getData().getDate());
							}
						}

					} else {
						LblResult.setText("The Date is not in the system");
					}
				}
			} else
				LblResult.setText("Select Date");
		});
		Button back = new Button("Back");
		back.setOnAction(e -> {
			alert.setTitle("Confirmation Dialog");
			alert.setHeaderText("Are you sure you want to close the window?");
			alert.setContentText("You will lose all the data you entered");
			Optional<ButtonType> result = alert.showAndWait();
			if (result.isPresent() && result.get() == ButtonType.OK) {
				stage.close();
				martyrDateScreen(stage);
			}
		});
		stage.setOnCloseRequest(event -> {
			event.consume();

			Alert exitAlert = new Alert(Alert.AlertType.CONFIRMATION);
			exitAlert.setTitle("Confirm Exit");
			exitAlert.setHeaderText("Are you sure you want to exit?");
			exitAlert.setContentText("Choose your option.");
			Optional<ButtonType> result = exitAlert.showAndWait();

			if (result.isPresent() && result.get() == ButtonType.OK) {

				Alert saveAlert = new Alert(Alert.AlertType.CONFIRMATION);
				saveAlert.setTitle("Confirm Save");
				saveAlert.setHeaderText("Do you want to save the data in a file?");
				saveAlert.setContentText("Choose your option.");
				saveAlert.getButtonTypes().setAll(ButtonType.YES, ButtonType.NO);

				result = saveAlert.showAndWait();
				if (result.isPresent() && result.get() == ButtonType.YES) {
					insertToFile();
				}
				stage.close();
			}
		});
		VBox buttVBox = new VBox(10, LblResult, back);
		buttVBox.setAlignment(Pos.CENTER);
		GridPane gpPane = new GridPane();
		gpPane.add(comboList, 1, 2);
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
		stage.setTitle("delete Date");
		stage.setScene(scene);
		stage.show();

	}

	public void printTaple(Stage stage) {
		setTheFirstChangeString("");
		Label LblResult = new Label("");
		RadioButton rbIncludingEmpty = new RadioButton("print including Empty");
		RadioButton rbExcludingEmpty = new RadioButton("print excluding Empty");

		Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
		rbIncludingEmpty.setOnAction(e -> {
			String dateArea = "";
			if (rbIncludingEmpty.isSelected())
				rbExcludingEmpty.setSelected(false);
			for (int i = 0; i < martyrDateTable.getTable().length; i++) {
					if (martyrDateTable.getTable()[i].getFlag() != Flag.EMPTY)
						dateArea += "                            " + i + "  - - - - - -  "
								+ martyrDateTable.getTable()[i].getFlag() + "  - - - - - -  "
								+ martyrDateTable.getTable()[i].getData().getDate() + "\n";
					else
						dateArea += "                            " + i + "  - - - - - -  "
								+ martyrDateTable.getTable()[i].getFlag() + "  - - - - - -  " + null + "\n";
				
			}
			LblResult.setText(dateArea);
		});
		rbExcludingEmpty.setOnAction(e -> {
			String dateArea = "";
			if (rbExcludingEmpty.isSelected())
				rbIncludingEmpty.setSelected(false);
			for (int i = 0; i < martyrDateTable.getTable().length; i++) {
				if (martyrDateTable.getTable()[i].getFlag() != Flag.DELETED
						&& martyrDateTable.getTable()[i].getFlag() != Flag.EMPTY) {
					dateArea += "                            " + i + "  - - - - - -  "
							+ martyrDateTable.getTable()[i].getFlag() + "  - - - - - -  "
							+ martyrDateTable.getTable()[i].getData().getDate() + "\n";
				}
			}
			LblResult.setText(dateArea);
		});
		Button back = new Button("Back");
		back.setOnAction(e -> {
			alert.setTitle("Confirmation Dialog");
			alert.setHeaderText("Are you sure you want to close the window?");
			alert.setContentText("You will lose all the data you entered");
			Optional<ButtonType> result = alert.showAndWait();
			if (result.isPresent() && result.get() == ButtonType.OK) {
				stage.close();
				martyrDateScreen(stage);
			}

		});
		stage.setOnCloseRequest(event -> {
			event.consume();

			Alert exitAlert = new Alert(Alert.AlertType.CONFIRMATION);
			exitAlert.setTitle("Confirm Exit");
			exitAlert.setHeaderText("Are you sure you want to exit?");
			exitAlert.setContentText("Choose your option.");
			Optional<ButtonType> result = exitAlert.showAndWait();

			if (result.isPresent() && result.get() == ButtonType.OK) {

				Alert saveAlert = new Alert(Alert.AlertType.CONFIRMATION);
				saveAlert.setTitle("Confirm Save");
				saveAlert.setHeaderText("Do you want to save the data in a file?");
				saveAlert.setContentText("Choose your option.");
				saveAlert.getButtonTypes().setAll(ButtonType.YES, ButtonType.NO);

				result = saveAlert.showAndWait();
				if (result.isPresent() && result.get() == ButtonType.YES) {
					insertToFile();
				}
				stage.close();
			}
		});
		HBox redioHBox = new HBox(10, rbExcludingEmpty, rbIncludingEmpty);
		VBox buttVBox = new VBox(10, redioHBox, LblResult, back);
		redioHBox.setAlignment(Pos.CENTER);
		buttVBox.setAlignment(Pos.CENTER);
		LblResult.setAlignment(Pos.CENTER);

		ScrollPane allDate = new ScrollPane(LblResult);
		BorderPane InsertPane = new BorderPane();
		InsertPane.setCenter(allDate);
		InsertPane.setBottom(buttVBox);
		Scene scene = new Scene(InsertPane, 800, 400);
		stage.setTitle("print Date");
		stage.setScene(scene);
		stage.show();

	}

	public void martyrScreen(Stage stage) {
		Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
		TextArea martyrArea = new TextArea();
		martyrArea.setEditable(false);
		Label labTitle = new Label("Matryr Screen");
		labTitle.setStyle("-fx-font-family: 'Cairo'; -fx-font-size: 20px; -fx-font-weight: bold;");
		labTitle.setPadding(new Insets(15));
		labTitle.setAlignment(Pos.CENTER);
		Label labLocationName = new Label("Date: " + selectedMartyrDate.getData().getDate());
		labLocationName.setStyle("-fx-font-family: 'Cairo'; -fx-font-size: 13px; -fx-font-weight: bold;");
		labLocationName.setAlignment(Pos.CENTER);
		Label labResult = new Label("");
		labResult.setAlignment(Pos.CENTER);
		Label lblForSize = new Label("قال رسول الله صلى الله عليه وسلم:\r\n" + " للشهيد عند الله سبع خصال: \r\n"
				+ "1- يغفر له في أول دفعة من دمه. \r\n" + "2- ويرى مقعده من الجنة. \r\n" + "3- ويحلى حلة الإيمان. \r\n"
				+ "4- ويزوج اثنتين وسبعين زوجة من الحور العين. \r\n" + "5- ويجار من عذاب القبر. \r\n"
				+ "6- ويأمن من الفزع الأكبر. \r\n" + "7- ويوضع على رأسه تاج الوقار، الياقوتة منه خير \r\n"
				+ "من الدنيا وما فيها. \r\n" + "8- ويشفع في سبعين إنسانًا من أهل بيته. \r\n");
		lblForSize.setNodeOrientation(NodeOrientation.RIGHT_TO_LEFT);
		lblForSize.setPadding(new Insets(20));
		lblForSize.setStyle("-fx-font-family: 'Cairo'; -fx-font-size: 13px; -fx-font-weight: bold;");
		Label lblResultStatistics = new Label("");
		Button butInsertANewMartyr = new Button("insert a new martyr");
		Button butUpdateAMartyr = new Button("update a martyr");
		Button butDeleteAMartyr = new Button("delete a martyr");
		Button butSortedMartyrTable = new Button("table sorted by age");
		Button butGoPreviousPage = new Button("Back to Date Screan");
		lblResultStatistics.setText("The Tree High is : " + selectedMartyrDate.getData().getMartyrTree().height()
				+ "\nThe Tree Size is : " + selectedMartyrDate.getData().getMartyrTree().Size() + "\n");
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
		butSortedMartyrTable.setOnAction(e -> {
			stage.close();
			tableSortedByAgeScrean(stage);
		});

		butGoPreviousPage.setOnAction(e -> {
			alert.setTitle("Confirmation Dialog");
			alert.setHeaderText("Are you sure you want to close the window?");
			alert.setContentText("You will lose all the data you entered");
			Optional<ButtonType> result = alert.showAndWait();
			if (result.isPresent() && result.get() == ButtonType.OK) {
				stage.close();
				martyrDateScreen(stage);
			}
		});

		stage.setOnCloseRequest(event -> {
			event.consume();

			Alert exitAlert = new Alert(Alert.AlertType.CONFIRMATION);
			exitAlert.setTitle("Confirm Exit");
			exitAlert.setHeaderText("Are you sure you want to exit?");
			exitAlert.setContentText("Choose your option.");
			Optional<ButtonType> result = exitAlert.showAndWait();

			if (result.isPresent() && result.get() == ButtonType.OK) {

				Alert saveAlert = new Alert(Alert.AlertType.CONFIRMATION);
				saveAlert.setTitle("Confirm Save");
				saveAlert.setHeaderText("Do you want to save the data in a file?");
				saveAlert.setContentText("Choose your option.");
				saveAlert.getButtonTypes().setAll(ButtonType.YES, ButtonType.NO);

				result = saveAlert.showAndWait();
				if (result.isPresent() && result.get() == ButtonType.YES) {
					insertToFile();
				}
				stage.close();
			}
		});

		lblResultStatistics.setText("The Tree High is : " + selectedMartyrDate.getData().getMartyrTree().height()
				+ "\nThe Tree Size is : " + selectedMartyrDate.getData().getMartyrTree().Size() + "\n");
		selectedMartyrDate.getData().getMartyrTree().traverseLevelByLevel();
		QStack<TNode<Martyr>> tempStack = selectedMartyrDate.getData().getMartyrTree().getNextStack();
		String result = "print the tree level-by-level and from right to left: \n\n";

		while (!tempStack.isEmpty()) {
			result += tempStack.pop().getData() + "\n";
		}
		martyrArea.insertText(0, result);
		butInsertANewMartyr.setPrefWidth(200);
		butDeleteAMartyr.setPrefWidth(200);
		butUpdateAMartyr.setPrefWidth(200);
		butSortedMartyrTable.setPrefWidth(200);
		butGoPreviousPage.setPrefWidth(150);
		VBox butBox = new VBox(10, labLocationName, butInsertANewMartyr, butDeleteAMartyr, butUpdateAMartyr,
				butSortedMartyrTable, labResult);
		butBox.setAlignment(Pos.CENTER);
		VBox statVBox = new VBox(10, lblResultStatistics, martyrArea);
		BorderPane root = new BorderPane();
		lblResultStatistics.setPrefWidth(350);
		lblResultStatistics.setPadding(new Insets(15));
		lblForSize.setPrefWidth(450);
		root.setCenter(butBox);
		root.setTop(labTitle);
		root.setBottom(butGoPreviousPage);
		root.setLeft(statVBox);
		root.setRight(lblForSize);
		BorderPane.setAlignment(labTitle, Pos.CENTER);
		BorderPane.setAlignment(labResult, Pos.CENTER);
		BorderPane.setAlignment(butGoPreviousPage, Pos.CENTER);
		Scene scene = new Scene(root, 1400, 500);
		stage.setTitle("Location Screen");
		stage.setScene(scene);
		stage.show();
	}

	public void insetrMartyrScrean(Stage stage) {
		setTheFirstChangeString("");
		setTheSecondChangeString("");
		Label LblResult = new Label("");
		Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
		TextField tfName = new TextField();
		tfName.setPromptText("Enter The Name");

		TextField tfAge = new TextField();
		tfAge.setPromptText("Enter The Age");
		ComboBox<String> DestrectComboBox = new ComboBox<>();
		ComboBox<String> locationComboBox = new ComboBox<>();
		Label LblGender = new Label("select the gender: ");
		RadioButton rbMale = new RadioButton("Male");
		RadioButton rbFemale = new RadioButton("Female");
		Button butInsert = new Button("Insert");
		LinkedListQueue<String> destrictInComboBoxQueue = allDistrict.traverse();
		while (!destrictInComboBoxQueue.isEmpty()) {
			DestrectComboBox.getItems().add(destrictInComboBoxQueue.dequeue());
		}
		DestrectComboBox.setOnAction(e -> {
			locationComboBox.getItems().clear();
			setTheFirstChangeString(DestrectComboBox.getValue().trim());
			MartyrDate date;
			for (int i = 0; i < martyrDateTable.getTable().length; i++) {
				date = martyrDateTable.getTable()[i].getData();
				if (date != null) {
					date.getMartyrTree().traverseInOrder();
					QStack<TNode<Martyr>> martyrStack = date.getMartyrTree().getNextStack();
					Martyr selectMartyr = new Martyr();
					while (!martyrStack.isEmpty()) {
						selectMartyr = martyrStack.pop().getData();
						if (selectMartyr.getDistrict().compareTo(getTheFirstChangeString().trim()) == 0
								&& !locationComboBox.getItems().contains(selectMartyr.getLocation())) {
							locationComboBox.getItems().add(selectMartyr.getLocation());
						}
					}
				}
			}
		});
		locationComboBox.setOnAction(e -> {
			setTheSecondChangeString(locationComboBox.getValue().trim());
		});
		rbMale.setOnAction(e -> {
			rbFemale.setSelected(false);
		});
		rbFemale.setOnAction(e -> {
			rbMale.setSelected(false);
		});
		butInsert.setOnAction(e -> {
			alert.setTitle("Confirmation Dialog");
			alert.setHeaderText("Are you sure you want to add the martyr?");
			Optional<ButtonType> result = alert.showAndWait();
			if (result.isPresent() && result.get() == ButtonType.OK) {
				try {
					boolean testName = true;
					String nameMartyr = tfName.getText();
					String[] splitName = nameMartyr.split(" ");
					

					int Age;
					if (tfAge.getText().equals("")) {
						Age = -1;
					} else {
						Age = Integer.parseInt(tfAge.getText());
					}
					boolean testDistrict = false;
					if (!getTheFirstChangeString().equals("")) {
						testDistrict = true;
					}
					boolean testLocation = false;
					if (!getTheSecondChangeString().equals("")) {
						testLocation = true;
					}
					boolean testAge = false;
					if (Age >= 0 && Age <= 120) {
						testAge = true;
					} else if (tfAge.getText().equals("")) {
						Age = -1;
						testAge = true;
					}
					boolean testgender = false;
					if (rbMale.isSelected() || rbFemale.isSelected()) {
						testgender = true;
					}

					if (testAge == true && testgender == true && testName == true && testDistrict == true
							&& testLocation == true) {
						Martyr insertMartyr;
						if (rbMale.isSelected()) {
							insertMartyr = new Martyr(nameMartyr, Age, getTheSecondChangeString(),
									getTheFirstChangeString(), "M");

						} else {
							insertMartyr = new Martyr(nameMartyr, Age, getTheSecondChangeString(),
									getTheFirstChangeString(), "F");
						}
						if (selectedMartyrDate.getData().searchByName(nameMartyr) == null) {
							selectedMartyrDate.getData().getMartyrTree().insert(insertMartyr);
							LblResult.setText("Add Successfuly");
						} else {
							LblResult.setText("Add not Successfuly, the martyr is already in the system");
						}
					} else if (testName == false) {
						LblResult.setText("Enter a true name");
					} else if (testAge == false) {
						LblResult.setText("Enter a true Age [ 0 - 120 ]");
					} else if (testDistrict == false) {
						LblResult.setText("select District");
					} else if (testLocation == false) {
						LblResult.setText("select Location");
					} else {
						LblResult.setText("select the gender");
					}
				} catch (Exception ex) {
					LblResult.setText("Enter a true Age [ 0 - 120 ]");
				}

			}
		});
		Button butBack = new Button("Back");
		butBack.setOnAction(e -> {
			alert.setTitle("Confirmation Dialog");
			alert.setHeaderText("Are you sure you want to close the window?");
			alert.setContentText("You will lose all the data you entered");
			Optional<ButtonType> result = alert.showAndWait();
			if (result.isPresent() && result.get() == ButtonType.OK) {
				stage.close();
				martyrScreen(stage);
			}
		});
		stage.setOnCloseRequest(event -> {
			event.consume();

			Alert exitAlert = new Alert(Alert.AlertType.CONFIRMATION);
			exitAlert.setTitle("Confirm Exit");
			exitAlert.setHeaderText("Are you sure you want to exit?");
			exitAlert.setContentText("Choose your option.");
			Optional<ButtonType> result = exitAlert.showAndWait();

			if (result.isPresent() && result.get() == ButtonType.OK) {

				Alert saveAlert = new Alert(Alert.AlertType.CONFIRMATION);
				saveAlert.setTitle("Confirm Save");
				saveAlert.setHeaderText("Do you want to save the data in a file?");
				saveAlert.setContentText("Choose your option.");
				saveAlert.getButtonTypes().setAll(ButtonType.YES, ButtonType.NO);

				result = saveAlert.showAndWait();
				if (result.isPresent() && result.get() == ButtonType.YES) {
					insertToFile();
				}
				stage.close();
			}
		});
		HBox lblAndcComboHBox = new HBox(10, LblGender, rbMale, rbFemale);
		VBox lblAndButBackBox = new VBox(10, LblResult, butBack);
		lblAndButBackBox.setAlignment(Pos.CENTER);
		GridPane gpPane = new GridPane();
		gpPane.add(tfName, 1, 0);
		gpPane.add(tfAge, 1, 1);
		gpPane.add(DestrectComboBox, 1, 2);
		gpPane.add(locationComboBox, 1, 3);
		gpPane.add(lblAndcComboHBox, 1, 4);
		gpPane.add(butInsert, 1, 5);
		gpPane.setAlignment(Pos.CENTER);
		gpPane.setHgap(10);
		gpPane.setVgap(10);
		gpPane.setAlignment(Pos.CENTER);
		LblResult.setAlignment(Pos.CENTER);
		LblResult.setPadding(new Insets(15));
		BorderPane InsertPane = new BorderPane();
		InsertPane.setCenter(gpPane);
		InsertPane.setBottom(lblAndButBackBox);
		BorderPane.setAlignment(lblAndButBackBox, Pos.CENTER);
		Scene scene = new Scene(InsertPane, 400, 300);
		stage.setTitle("insert Martyr");
		stage.setScene(scene);
		stage.show();
	}

	public void deleteMartyr(Stage stage) {
		setTheFirstChangeString("");
		Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
		Label LblResult = new Label("");
		ComboBox<Martyr> comboList = new ComboBox<>();
		Button butDelete = new Button("Delete");
		selectedMartyrDate.getData().getMartyrTree().traverseInOrder();
		QStack<TNode<Martyr>> temp = selectedMartyrDate.getData().getMartyrTree().getNextStack();

		while (!temp.isEmpty()) {
			comboList.getItems().add(temp.pop().getData());
		}

		comboList.setOnAction(e -> {
			if (comboList.getValue() != null)
				setChangeMartyr(comboList.getValue());
		});

		butDelete.setOnAction(e -> {
			alert.setTitle("Confirmation Dialog");
			alert.setHeaderText("Are you sure you want to delete the martyr?");
			alert.setContentText("You will lose all the data you entered");
			Optional<ButtonType> result = alert.showAndWait();
			if (result.isPresent() && result.get() == ButtonType.OK) {
				TNode<Martyr> martyr = selectedMartyrDate.getData().getMartyrTree().find(getChangeMartyr());
				if (martyr != null && selectedMartyrDate.getData().deleteMartyer(martyr) == true) {
					LblResult.setText("Done");
					comboList.getItems().clear();
					selectedMartyrDate.getData().getMartyrTree().traverseInOrder();
					QStack<TNode<Martyr>> newTemp = selectedMartyrDate.getData().getMartyrTree().getNextStack();
					while (!newTemp.isEmpty()) {
						comboList.getItems().add(newTemp.pop().getData());
					}
				} else if (martyr == null) {
					LblResult.setText("Choose currect value");
				} else {
					LblResult.setText("The Martyr is not in the system");
				}

			}
		});
		Button back = new Button("Back");
		back.setOnAction(e -> {
			alert.setTitle("Confirmation Dialog");
			alert.setHeaderText("Are you sure you want to close the window?");
			alert.setContentText("You will lose all the data you entered");
			Optional<ButtonType> result = alert.showAndWait();
			if (result.isPresent() && result.get() == ButtonType.OK) {
				stage.close();
				martyrScreen(stage);
			}
		});
		stage.setOnCloseRequest(event -> {
			event.consume();

			Alert exitAlert = new Alert(Alert.AlertType.CONFIRMATION);
			exitAlert.setTitle("Confirm Exit");
			exitAlert.setHeaderText("Are you sure you want to exit?");
			exitAlert.setContentText("Choose your option.");
			Optional<ButtonType> result = exitAlert.showAndWait();

			if (result.isPresent() && result.get() == ButtonType.OK) {

				Alert saveAlert = new Alert(Alert.AlertType.CONFIRMATION);
				saveAlert.setTitle("Confirm Save");
				saveAlert.setHeaderText("Do you want to save the data in a file?");
				saveAlert.setContentText("Choose your option.");
				saveAlert.getButtonTypes().setAll(ButtonType.YES, ButtonType.NO);

				result = saveAlert.showAndWait();
				if (result.isPresent() && result.get() == ButtonType.YES) {
					insertToFile();
				}
				stage.close();
			}
		});
		VBox buttVBox = new VBox(10, LblResult, back);
		buttVBox.setAlignment(Pos.CENTER);
		GridPane gpPane = new GridPane();
		gpPane.add(comboList, 1, 2);
		gpPane.add(butDelete, 0, 2);
		gpPane.setAlignment(Pos.CENTER);
		gpPane.setHgap(10);
		gpPane.setVgap(10);
		LblResult.setAlignment(Pos.CENTER);
		LblResult.setPadding(new Insets(15));
		BorderPane InsertPane = new BorderPane();
		InsertPane.setCenter(gpPane);
		InsertPane.setBottom(buttVBox);
		Scene scene = new Scene(InsertPane, 700, 200);
		stage.setTitle("delete Martyer");
		stage.setScene(scene);
		stage.show();

	}

	public void UpdateMartyrScrean(Stage stage) {
		setTheFirstChangeString("");
		setTheSecondChangeString("");
		GridPane gpPane = new GridPane();
		Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
		Label LblResult = new Label("");
		TextField tfEnterSelection = new TextField();
		tfEnterSelection.setPromptText("Enter The update");
		Button butUpdate = new Button("Update");
		RadioButton rbMale = new RadioButton("Male");
		RadioButton rbFemale = new RadioButton("Female");
		ComboBox<String> destrictsComBox = new ComboBox<>();
		ComboBox<String> locationsComBox = new ComboBox<>();
		LinkedListQueue<String> destrictInComboBoxQueue = allDistrict.traverse();
		while (!destrictInComboBoxQueue.isEmpty()) {
			destrictsComBox.getItems().add(destrictInComboBoxQueue.dequeue());
		}
		destrictsComBox.setOnAction(e -> {
			locationsComBox.getItems().clear();
			setTheFirstChangeString(destrictsComBox.getValue().trim());
			MartyrDate date;
			for (int i = 0; i < martyrDateTable.getTable().length; i++) {
				date = martyrDateTable.getTable()[i].getData();
				if (date != null) {
					date.getMartyrTree().traverseInOrder();
					QStack<TNode<Martyr>> martyrStack = date.getMartyrTree().getNextStack();
					Martyr selectMartyr = new Martyr();
					while (!martyrStack.isEmpty()) {
						selectMartyr = martyrStack.pop().getData();
						if (selectMartyr.getDistrict().compareTo(getTheFirstChangeString().trim()) == 0
								&& !locationsComBox.getItems().contains(selectMartyr.getLocation())) {
							locationsComBox.getItems().add(selectMartyr.getLocation());
						}
					}
				}
			}
		});
		locationsComBox.setOnAction(e -> {
			setTheSecondChangeString(locationsComBox.getValue().trim());
		});
		rbMale.setOnAction(e -> {
			rbFemale.setSelected(false);
		});
		rbFemale.setOnAction(e -> {
			rbMale.setSelected(false);
		});
		ComboBox<Martyr> comboList = new ComboBox<>();
		selectedMartyrDate.getData().getMartyrTree().traverseInOrder();
		QStack<TNode<Martyr>> temp = selectedMartyrDate.getData().getMartyrTree().getNextStack();
		comboList.setOnAction(e -> {
			if (comboList.getValue() != null)
				setChangeMartyr(comboList.getValue());

		});
		rbMale.setOnAction(e -> {
			rbFemale.setSelected(false);
		});
		rbFemale.setOnAction(e -> {
			rbMale.setSelected(false);
		});
		while (!temp.isEmpty()) {
			comboList.getItems().add(temp.pop().getData());
		}

		ComboBox<String> selectComBox = new ComboBox<>();
		selectComBox.getItems().setAll("Name", "location and destrict", "Age", "Gender");
		selectComBox.setOnAction(e -> {

			try {

				if (selectComBox.getValue() != null && selectComBox.getValue().equals("location and destrict")) {
					gpPane.getChildren().remove(tfEnterSelection);
					gpPane.add(destrictsComBox, 1, 1);
					gpPane.add(locationsComBox, 1, 2);
				} else if (selectComBox.getValue() != null && selectComBox.getValue().equals("Gender")) {
					if (gpPane.getChildren().contains(destrictsComBox)) {
						gpPane.getChildren().remove(destrictsComBox);
						gpPane.getChildren().remove(locationsComBox);
						gpPane.add(rbMale, 1, 1);
						gpPane.add(rbFemale, 1, 2);
					} else {
						gpPane.getChildren().remove(tfEnterSelection);
						gpPane.add(rbMale, 1, 1);
						gpPane.add(rbFemale, 1, 2);
					}
				} else {
					gpPane.getChildren().remove(destrictsComBox);
					gpPane.getChildren().remove(locationsComBox);
					gpPane.getChildren().remove(rbMale);
					gpPane.getChildren().remove(rbFemale);
					gpPane.add(tfEnterSelection, 1, 1);
				}
			} catch (Exception e2) {
			}
		});
		butUpdate.setOnAction(e -> {
			alert.setTitle("Confirmation Dialog");
			alert.setHeaderText("Are you sure you want to delete the martyr?");
			alert.setContentText("You will lose all the data you entered");
			Optional<ButtonType> result = alert.showAndWait();
			if (result.isPresent() && result.get() == ButtonType.OK) {
				if (getChangeMartyr() != null) {
					TNode<Martyr> martyr = selectedMartyrDate.getData().getMartyrTree().find(getChangeMartyr());
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
									Martyr updateMartyr = martyr.getData();
									selectedMartyrDate.getData().getMartyrTree().delete(martyr.getData());
									updateMartyr.setName(nameMartyr);
									selectedMartyrDate.getData().getMartyrTree().insert(updateMartyr);
									LblResult.setText("Done");
									setChangeMartyr(null);
								} else {
									LblResult.setText("We the new Name is not true");
								}
								break;
							case "location and destrict":
								boolean testDistrict = false;
								if (!getTheFirstChangeString().equals("")) {
									testDistrict = true;
								}
								boolean testLocation = false;
								if (!getTheSecondChangeString().equals("")) {
									testLocation = true;
								}
								if (testDistrict == true && testLocation == true) {
									Martyr updateMartyr = martyr.getData();
									selectedMartyrDate.getData().getMartyrTree().delete(martyr.getData());
									updateMartyr.setDistrict(getTheFirstChangeString());
									updateMartyr.setLocation(getTheSecondChangeString());
									selectedMartyrDate.getData().getMartyrTree().insert(updateMartyr);
									LblResult.setText("Done");
									setChangeMartyr(null);
								} else {
									LblResult.setText("wrong!, select [ district and location ]");
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

									setChangeMartyr(null);
								} else {
									LblResult.setText("We the new Age is not true");
								}
								break;
							case "Gender":
								boolean testgender = false;
								if (rbMale.isSelected() || rbFemale.isSelected()) {
									testgender = true;
								}
								if (testgender == true) {
									Martyr updateMartyr = martyr.getData();
									selectedMartyrDate.getData().getMartyrTree().delete(martyr.getData());
									if (rbMale.isSelected())
										updateMartyr.setGender("M");
									else
										updateMartyr.setGender("F");
									selectedMartyrDate.getData().getMartyrTree().insert(updateMartyr);
									LblResult.setText("Done");

								} else {
									LblResult.setText("We the new Gender is not true");
								}
								break;
							default:
								LblResult.setText("select your chooise from comboBox");
							}
							setChangeMartyr(null);
							comboList.getItems().clear();
							selectedMartyrDate.getData().getMartyrTree().traverseInOrder();
							QStack<TNode<Martyr>> tempList = selectedMartyrDate.getData().getMartyrTree()
									.getNextStack();
							while (!tempList.isEmpty()) {
								comboList.getItems().add(tempList.pop().getData());
							}
						} catch (Exception ex) {
							LblResult.setText("Enter a true number!");
						}
					} else {
						LblResult.setText("The Martyr is not in the system");
					}

				} else {
					LblResult.setText("select Martyr!");
				}
			}
		});

		Button back = new Button("Back");
		back.setOnAction(e -> {
			alert.setTitle("Confirmation Dialog");
			alert.setHeaderText("Are you sure you want to close the window?");
			alert.setContentText("You will lose all the data you entered");
			Optional<ButtonType> result = alert.showAndWait();
			if (result.isPresent() && result.get() == ButtonType.OK) {
				stage.close();
				martyrScreen(stage);
			}
		});
		stage.setOnCloseRequest(event -> {
			event.consume();

			Alert exitAlert = new Alert(Alert.AlertType.CONFIRMATION);
			exitAlert.setTitle("Confirm Exit");
			exitAlert.setHeaderText("Are you sure you want to exit?");
			exitAlert.setContentText("Choose your option.");
			Optional<ButtonType> result = exitAlert.showAndWait();

			if (result.isPresent() && result.get() == ButtonType.OK) {

				Alert saveAlert = new Alert(Alert.AlertType.CONFIRMATION);
				saveAlert.setTitle("Confirm Save");
				saveAlert.setHeaderText("Do you want to save the data in a file?");
				saveAlert.setContentText("Choose your option.");
				saveAlert.getButtonTypes().setAll(ButtonType.YES, ButtonType.NO);

				result = saveAlert.showAndWait();
				if (result.isPresent() && result.get() == ButtonType.YES) {
					insertToFile();
				}
				stage.close();
			}
		});
		VBox buttVBox = new VBox(10, LblResult, back);
		buttVBox.setAlignment(Pos.CENTER);
		gpPane.add(selectComBox, 0, 1);
		gpPane.add(comboList, 1, 3);
		gpPane.add(butUpdate, 0, 2);
		gpPane.setAlignment(Pos.CENTER);
		gpPane.setHgap(10);
		gpPane.setVgap(10);
		LblResult.setAlignment(Pos.CENTER);
		LblResult.setPadding(new Insets(15));
		BorderPane InsertPane = new BorderPane();
		InsertPane.setCenter(gpPane);
		InsertPane.setBottom(buttVBox);
		Scene scene = new Scene(InsertPane, 700, 200);
		stage.setTitle("update Martyer");
		stage.setScene(scene);
		stage.show();

	}

	public void tableSortedByAgeScrean(Stage stage) {
		Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
		Label LblResult = new Label("");
		TextArea martyrTextArea = new TextArea();
		martyrTextArea.setEditable(false);
		selectedMartyrDate.getData().getMartyrTree().traverseInOrder();
		Martyr[] martyrArray = new Martyr[selectedMartyrDate.getData().getMartyrTree().Size()];
		QStack<TNode<Martyr>> martyrStack = selectedMartyrDate.getData().getMartyrTree().getNextStack();
		for (int i = 0; i < martyrArray.length && !martyrStack.isEmpty(); i++) {
			martyrArray[i] = martyrStack.pop().getData();
		}
		Heap<Martyr> sortObject = new Heap<>();
		MartyrCompareByAge sortMartyr = new MartyrCompareByAge();
		sortObject.heapSort(martyrArray, sortMartyr);
		String martyrs = "";
		for (int i = 0; i < martyrArray.length; i++) {
			martyrs += martyrArray[i].toString();
		}
		martyrTextArea.insertText(0, martyrs);
		Button back = new Button("Back");
		back.setOnAction(e -> {
			alert.setTitle("Confirmation Dialog");
			alert.setHeaderText("Are you sure you want to close the window?");
			alert.setContentText("You will lose all the data you entered");
			Optional<ButtonType> result = alert.showAndWait();
			if (result.isPresent() && result.get() == ButtonType.OK) {
				stage.close();
				martyrScreen(stage);
			}
		});
		stage.setOnCloseRequest(event -> {
			event.consume();

			Alert exitAlert = new Alert(Alert.AlertType.CONFIRMATION);
			exitAlert.setTitle("Confirm Exit");
			exitAlert.setHeaderText("Are you sure you want to exit?");
			exitAlert.setContentText("Choose your option.");
			Optional<ButtonType> result = exitAlert.showAndWait();

			if (result.isPresent() && result.get() == ButtonType.OK) {

				Alert saveAlert = new Alert(Alert.AlertType.CONFIRMATION);
				saveAlert.setTitle("Confirm Save");
				saveAlert.setHeaderText("Do you want to save the data in a file?");
				saveAlert.setContentText("Choose your option.");
				saveAlert.getButtonTypes().setAll(ButtonType.YES, ButtonType.NO);

				result = saveAlert.showAndWait();
				if (result.isPresent() && result.get() == ButtonType.YES) {
					insertToFile();
				}
				stage.close();
			}
		});
		GridPane gpPane = new GridPane();
		gpPane.setAlignment(Pos.CENTER);
		gpPane.setHgap(10);
		gpPane.setVgap(10);
		VBox buttVBox = new VBox(10, gpPane, back);
		buttVBox.setAlignment(Pos.CENTER);
		LblResult.setAlignment(Pos.CENTER);
		LblResult.setPadding(new Insets(15));
		BorderPane SearchPane = new BorderPane();
		SearchPane.setCenter(martyrTextArea);
		SearchPane.setBottom(buttVBox);
		Scene scene = new Scene(SearchPane, 900, 450);
		stage.setTitle("search by martyr name");
		stage.setScene(scene);
		stage.show();
	}

	public void insertToFile() {
		Stage stage = new Stage();
		Label Tmasseg = new Label();
		Tmasseg.setStyle("-fx-font-family: 'Cairo'; -fx-font-size: 20px; -fx-font-weight: bold;");
		Tmasseg.setPadding(new Insets(15));
		Button load = new Button("Load From File");
		load.setStyle("-fx-font-family: 'Cairo'; -fx-font-size: 15px;");
		BorderPane root = new BorderPane();
		Scene scene = new Scene(root, 400, 400);
		load.setOnAction(e -> {
			String result = "";
			filechooser.setTitle("Choose your file");
			filechooser.setInitialDirectory(new File("c:\\"));
			filechooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("File.csv", "*.csv"));
			file = filechooser.showOpenDialog(stage);
			if (file != null) {
				if (file.exists()) {
					try {
						PrintWriter fileWriter = new PrintWriter(file);
						fileWriter.println("Name,event,Age,location,District,Gender");
						try {
							HNode<MartyrDate> theSelectedMartyrDate;
							for (int i = 0; i < martyrDateTable.getTable().length; i++) {
								theSelectedMartyrDate = martyrDateTable.getTable()[i];
								if (theSelectedMartyrDate.getFlag() == Flag.FULL) {
									theSelectedMartyrDate.getData().getMartyrTree().traverseInOrder();
									QStack<TNode<Martyr>> martyrStack = theSelectedMartyrDate.getData().getMartyrTree()
											.getNextStack();
									while (!martyrStack.isEmpty()) {
										Martyr theSelectedMartyr = martyrStack.pop().getData();
										String name = "";
										String date = "";
										String age = "";
										String location = "";
										String district = "";
										String gender = "";
										name = theSelectedMartyr.getName();
										date = theSelectedMartyrDate.getData().getDate();
										if (theSelectedMartyr.getAge() > -1)
											age = Integer.toString(theSelectedMartyr.getAge());
										else
											age = "";
										location = theSelectedMartyr.getLocation();
										district = theSelectedMartyr.getDistrict();
										gender = theSelectedMartyr.getGender();
										result = name.trim() + "," + date.trim() + "," + age.trim() + ","
												+ location.trim() + "," + district.trim() + "," + gender.trim();
										fileWriter.println(result);
									}
								}

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

						fileWriter.close();
					} catch (IOException ex) {
						Tmasseg.setText("We Can't find the file!");
						Tmasseg.setTextFill(Color.RED);
					}

					stage.close();
				}
			} else {
				Tmasseg.setText("the system did not find your file");
			}

		});
		stage.setOnCloseRequest(event -> {
			event.consume();

			Alert exitAlert = new Alert(Alert.AlertType.CONFIRMATION);
			exitAlert.setTitle("Confirm Exit");
			exitAlert.setHeaderText("Are you sure you want to exit?");
			exitAlert.setContentText("All modifications will be lost!");
			Optional<ButtonType> result = exitAlert.showAndWait();

			if (result.isPresent() && result.get() == ButtonType.OK) {
				stage.close();
			}
		});
		root.setCenter(load);
		root.setBottom(Tmasseg);
		BorderPane.setAlignment(Tmasseg, Pos.CENTER);
		stage.setScene(scene);
		stage.show();
	}

	public static void main(String[] args) {
		launch(args);
	}

	public String getTheFirstChangeString() {
		return changeTheFirstString;
	}

	public void setTheFirstChangeString(String changeString) {
		this.changeTheFirstString = changeString;
	}

	public String getTheSecondChangeString() {
		return changeTheSecondString;
	}

	public void setTheSecondChangeString(String changeString) {
		this.changeTheSecondString = changeString;
	}

	public Martyr getChangeMartyr() {
		return changeMartyr;
	}

	public void setChangeMartyr(Martyr changeMartyr) {
		this.changeMartyr = changeMartyr;
	}

	public String maxDistrict() {
		QuadraticOHash<String> maxDistrictHash = new QuadraticOHash<>(7);
		String district;
		selectedMartyrDate.getData().getMartyrTree().traverseInOrder();
		QStack<TNode<Martyr>> currMartyrStack = selectedMartyrDate.getData().getMartyrTree().getNextStack();
		while (!currMartyrStack.isEmpty()) {
			district = currMartyrStack.pop().getData().getDistrict();
			HNode<String> findDistrict = maxDistrictHash.find(district);
			if (findDistrict == null)
				maxDistrictHash.add(district);
			else {
				findDistrict.setCount(findDistrict.getCount() + 1);
			}

		}
		String result = "";
		HNode<String> maxDistrict = new HNode<String>("");
		for (int i = 0; i < maxDistrictHash.getTable().length; i++) {
			if (maxDistrict.getData().equals("") && maxDistrictHash.getTable()[i].getFlag() == Flag.FULL) {
				maxDistrict = maxDistrictHash.getTable()[i];
			} else if (maxDistrictHash.getTable()[i].getFlag() == Flag.FULL
					&& maxDistrictHash.getTable()[i].getCount() > maxDistrict.getCount()) {
				maxDistrict = maxDistrictHash.getTable()[i];
			}
		}
		if (maxDistrict != null)
			result = maxDistrict.getData();
		return result;
	}

	public String maxLocation() {
		QuadraticOHash<String> maxLocationHash = new QuadraticOHash<>(7);
		String location;
		selectedMartyrDate.getData().getMartyrTree().traverseInOrder();
		QStack<TNode<Martyr>> currMartyrStack = selectedMartyrDate.getData().getMartyrTree().getNextStack();
		while (!currMartyrStack.isEmpty()) {
			location = currMartyrStack.pop().getData().getLocation();
			maxLocationHash.add(location);
			HNode<String> findLocation = maxLocationHash.find(location);
			if (findLocation == null)
				maxLocationHash.add(location);
			else
				findLocation.setCount(findLocation.getCount() + 1);
		}
		String result = "";
		HNode<String> maxLocation = null;
		for (int i = 0; i < maxLocationHash.getTable().length; i++) {
			if (maxLocation == null && maxLocationHash.getTable()[i].getFlag() == Flag.FULL)
				maxLocation = maxLocationHash.getTable()[i];
			else if (maxLocationHash.getTable()[i].getFlag() == Flag.FULL
					&& maxLocationHash.getTable()[i].getCount() > maxLocation.getCount())
				maxLocation = maxLocationHash.getTable()[i];
		}
		if (maxLocation != null)
			result = maxLocation.getData();
		return result;
	}

	public boolean cheackTheDate() {
		String date = getTheFirstChangeString();
		String[] Split1 = date.split("/");

		for (int i = 0; i < date.length(); i++) {
			if (Split1.length != 3) {
				return false;
			} else if ((date.charAt(i) >= 65 && date.charAt(i) <= 90)
					|| (date.charAt(i) >= 97 && date.charAt(i) <= 122)) {
				return false;
			} else if (Integer.parseInt(Split1[1]) > 31 || Integer.parseInt(Split1[1]) <= 0
					|| (Integer.parseInt(Split1[2]) == 2024 && Integer.parseInt(Split1[1]) > 30)) {
				return false;

			} else if (Integer.parseInt(Split1[0]) > 12 || Integer.parseInt(Split1[0]) <= 0
					|| (Integer.parseInt(Split1[2]) == 2024 && Integer.parseInt(Split1[0]) > 6)) {
				return false;

			} else if (Integer.parseInt(Split1[2]) > 2024 || Integer.parseInt(Split1[2]) <= 0) {
				return false;

			}
		}
		return true;
	}

}