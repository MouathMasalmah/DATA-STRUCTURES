package application;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Random;
import java.util.Scanner;
import Lab0.MyList;
import Lab0.Name;
import Lab1.Recursion;
import Lab2.linkedList;
import Lab3.DLinkedList;
import Lab4.CursorArray;
import Lab5.CursorStack;
import Lab6.Squeue;
import Lab7.BSTreeNew;
import Lab7.District;
import Lab7.LinkedStack;
import Lab7.Location;
import Lab7.Martyr;
import Lab7.TNode;
import Lab8.AVLTree;
import javafx.application.Application;
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
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;

public class Main extends Application {
	private MyList<Name> names = new MyList<Name>(10);
	private linkedList<Integer> Lab2_List = new linkedList<>();
	private DLinkedList<Integer> Lab3_List = new DLinkedList<>();
	private CursorArray<Integer> Lab4_List = new CursorArray<>(20);
	private CursorStack<Integer> Lab5_List = new CursorStack<>(20);
	private Squeue<Integer> Lab6_List = new Squeue<>();
	private AVLTree<Integer> Lab8_List = new AVLTree<>();
	private int min = 0;
	private int totalIn = 0;
	private int totalOut = 0;
	private int totalWaitingTime = 0;
	private TextArea textArea = new TextArea();
	private Button startSimulation = new Button("Start Simulation");
	private TextField timeInput = new TextField();
	private Label lblresult = new Label();
	private ScrollPane scroll = new ScrollPane();
	private BSTreeNew<District> distractList = new BSTreeNew<>();
	private FileChooser filechooser = new FileChooser();
	private File file;

	@Override
	public void start(Stage primaryStage) {
		TabPane tapPane = new TabPane();
		Tab MainTap = new Tab("Menu");
		tapPane.getTabs().add(MainTap);
		Tab Lap_0 = new Tab("Lab 0");

		Lap_0.setContent(Lap0());

		Tab Lap_1 = new Tab("Lab 1");
		Lap_1.setContent(Lap1());

		Tab Lap_2 = new Tab("Lab 2");
		Lap_2.setContent(Lap2());

		Tab Lap_3 = new Tab("Lab 3");
		Lap_3.setContent(Lap3());

		Tab Lap_4 = new Tab("Lab 4");
		Lap_4.setContent(Lap4());

		Tab Lap_5 = new Tab("Lab 5");
		Lap_5.setContent(Lap5());
		Tab Lap_6 = new Tab("Lab 6");
		Lap_6.setContent(Lap6());

		Tab Lap_7 = new Tab("Lab 7");
		Lap_7.setContent(Lap7(primaryStage));
		
		Tab Lap_8 = new Tab("Lab 8");
		Lap_8.setContent(Lap8());
		
		Button butTap0 = new Button("     Lab 0     ");

		butTap0.setOnAction(e -> {
			if (!(tapPane.getTabs().contains(Lap_0))) {
				tapPane.getTabs().add(Lap_0);
			}
		});

		Button butTap1 = new Button("     Lab 1     ");

		butTap1.setOnAction(e -> {
			if (!(tapPane.getTabs().contains(Lap_1))) {
				tapPane.getTabs().add(Lap_1);
			}
		});

		Button butTap2 = new Button("     Lab 2     ");

		butTap2.setOnAction(e -> {
			if (!(tapPane.getTabs().contains(Lap_2))) {
				tapPane.getTabs().add(Lap_2);
			}
		});

		Button butTap3 = new Button("     Lab 3     ");

		butTap3.setOnAction(e -> {
			if (!(tapPane.getTabs().contains(Lap_3))) {
				tapPane.getTabs().add(Lap_3);
			}
		});

		Button butTap4 = new Button("     Lab 4     ");

		butTap4.setOnAction(e -> {
			if (!(tapPane.getTabs().contains(Lap_4))) {
				tapPane.getTabs().add(Lap_4);
			}
		});

		Button butTap5 = new Button("     Lab 5     ");

		butTap5.setOnAction(e -> {
			if (!(tapPane.getTabs().contains(Lap_5))) {
				tapPane.getTabs().add(Lap_5);
			}
		});
		Button butTap6 = new Button("     Lab 6     ");

		butTap6.setOnAction(e -> {
			if (!(tapPane.getTabs().contains(Lap_6))) {
				tapPane.getTabs().add(Lap_6);
			}
		});
		Button butTap7 = new Button("     Lab 7     ");

		butTap7.setOnAction(e -> {
			if (!(tapPane.getTabs().contains(Lap_7))) {
				tapPane.getTabs().add(Lap_7);
			}
		});
		Button butTap8 = new Button("     Lab 8     ");

		butTap8.setOnAction(e -> {
			if (!(tapPane.getTabs().contains(Lap_8))) {
				tapPane.getTabs().add(Lap_8);
			}
		});
		GridPane ButMenu = new GridPane();
		ButMenu.add(butTap0,0,0);
		ButMenu.add(butTap1,1,0);
		ButMenu.add(butTap2,2,0);
		ButMenu.add(butTap3,0,1);
		ButMenu.add(butTap4,1,1);
		ButMenu.add(butTap5,2,1);
		ButMenu.add(butTap6,0,2);
		ButMenu.add(butTap7,1,2);
		ButMenu.add(butTap8,2,2);
		ButMenu.setVgap(20);
		ButMenu.setHgap(20);
		ButMenu.setAlignment(Pos.CENTER);

		BorderPane MeanPane = new BorderPane();
		MeanPane.setCenter(ButMenu);

		Text Title = new Text("Welcom In My Lap Solution");
		Title.setFont(new Font(20));
		Title.setFill(Color.BLACK);
		MeanPane.setTop(Title);
		MeanPane.setPadding(new Insets(30));
		MeanPane.setAlignment(Title, Pos.CENTER);
		MainTap.setContent(MeanPane);
		Scene scene = new Scene(tapPane, 400, 400);
		primaryStage.setScene(scene);
		primaryStage.show();
	}

	public static void main(String[] args) {
		launch(args);
	}

	/*******************************************************************************************************/
	public Pane Lap0() {

		Label Tmasseg = new Label();
		Tmasseg.setFont(new Font(15));

		Button Load = new Button("Load");
		Load.setOnAction(e -> {
			try {
				File file = new File("Amarica List.txt");
				Scanner scan = new Scanner(file);

				while (scan.hasNext()) {
					String line = scan.nextLine();

					String[] S = line.split(",");
					String name = S[0];
					String gender = S[1];
					int freq = Integer.parseInt(S[2]);
					Name saveName = new Name(name, gender, freq);
					names.add(saveName);

				}
				Tmasseg.setText("Well Done");
				Tmasseg.setTextFill(Color.BLACK);
				scan.close();
			} catch (FileNotFoundException ex) {
				System.out.println(ex.toString());
			}
		});

		TextField TextBox1 = new TextField("");
		TextBox1.setPromptText("Name,gender(M,F),freqouncy");
		TextField TextBox2 = new TextField("");
		TextBox2.setPromptText("(Name,gender(M,F))for delete or search");
		Button Badd = new Button("add");

		Badd.setOnAction(e -> {
			String[] De = TextBox1.getText().split(",");

			if (De.length == 3) {
				int count = 0;
				for (int i = 0; i < De[2].length(); i++) {

					if (De[2].charAt(i) > 57 || De[2].charAt(i) < 48) {
						count++;
					}
				}
				if ((De[1].equals("M") || De[1].equals("F")) && count == 0) {
					Name newName = new Name(De[0], De[1], Integer.parseInt(De[2]));

					if (names.find(newName) == -1) {
						names.add(newName);
						Tmasseg.setText("Well Done");
						Tmasseg.setTextFill(Color.BLACK);

					} else {
						Tmasseg.setText("Wrong!, The Name Is already added");
						Tmasseg.setTextFill(Color.RED);
					}
				} else {
					Tmasseg.setText("Wrong!, Enter Correct Gender or freqouncy");
					Tmasseg.setTextFill(Color.RED);
				}
			} else {
				Tmasseg.setText("Wrong!, Enter Correct Value");
				Tmasseg.setTextFill(Color.RED);
			}

		});

		Button Bdelete = new Button("delete");
		Bdelete.setOnAction(e -> {
			int Number = 0;
			String[] SplitText = TextBox2.getText().split(",");
			if (SplitText.length == 2) {
				Name SearchName = new Name(SplitText[0], SplitText[1], 0);
				Number = names.find(SearchName);
				if (Number == -1) {
					Tmasseg.setText("Sorry, We dont found your the name");
					Tmasseg.setTextFill(Color.RED);
				} else {
					names.delete(SearchName);
					Tmasseg.setText("well done delete");
					Tmasseg.setTextFill(Color.BLACK);
				}
			} else {

				Tmasseg.setText("Wrong!, Enter Correct Value");
				Tmasseg.setTextFill(Color.RED);
			}
		});

		Button Bsearch = new Button("search");
		Bsearch.setOnAction(e -> {
			int Number = 0;
			String[] SplitText = TextBox2.getText().split(",");
			if (SplitText.length == 2) {
				Name SearchName = new Name(SplitText[0], SplitText[1], 0);
				Number = names.find(SearchName);
				if (Number == -1) {
					Tmasseg.setText("Sorry, We dont found your the name");
					Tmasseg.setTextFill(Color.RED);
				} else {

					Tmasseg.setText(names.get(Number).toString());
					Tmasseg.setTextFill(Color.BLACK);
				}
			} else {

				Tmasseg.setText("Wrong!, Enter Correct Value");
				Tmasseg.setTextFill(Color.RED);
			}
		});

		GridPane newPane = new GridPane();

		VBox VBlo = new VBox(20, Tmasseg, Load);

		BorderPane root = new BorderPane();
		newPane.add(Badd, 0, 0);
		newPane.add(TextBox1, 1, 0);
		newPane.add(TextBox2, 1, 1);
		newPane.add(Bsearch, 0, 1);
		newPane.add(Bdelete, 0, 2);

		newPane.setAlignment(Pos.CENTER);

		VBlo.setAlignment(Pos.CENTER);
		newPane.setHgap(10);
		newPane.setVgap(10);

		root.setCenter(newPane);
		root.setBottom(VBlo);

		return root;
	}

	/*******************************************************************************************************/
	public Pane Lap1() {
		Recursion recursion = new Recursion();
		TextField tfBinarySearch = new TextField();
		tfBinarySearch.setPromptText("Enter to search about the Number");
		TextField tfInput = new TextField();
		tfInput.setPromptText("Enter a value");
		Label LebResult = new Label();
		Button butReverse = new Button("reverse the statement and her words");
		Button butSumDigits = new Button("Sum the number digits");
		Button butBinarySearch = new Button("Search about number");
		butReverse.setPrefWidth(300);
		butSumDigits.setPrefWidth(300);
		butBinarySearch.setPrefWidth(300);
		tfInput.setPrefWidth(300);
		tfBinarySearch.setPrefWidth(300);

		butReverse.setOnAction(e -> {
			if (tfInput.getText() == null || tfInput.getText().length() == 0)
				LebResult.setText("Enter a true value");
			else
				LebResult.setText(recursion.reverse(tfInput.getText()));
		});
		butSumDigits.setOnAction(e -> {
			try {
				int i = Integer.parseInt(tfInput.getText());
				LebResult.setText("The sum of digits = " + recursion.SumNumberDigits(i));
			} catch (NumberFormatException ex) {
				LebResult.setText("Enter a true value");
			}
		});
		butBinarySearch.setOnAction(e -> {
			try {
				String[] split = tfInput.getText().split(" ");
				int Length = 0;

				int[] array = new int[Length];
				Length = split.length;
				array = new int[Length];
				for (int i = 0; i < split.length; i++) {
					array[i] = Integer.parseInt(split[i]);
					System.out.println("soso");
				}
				int Num = Integer.parseInt(tfBinarySearch.getText());
				int result = recursion.binarySearch(array, Num);
				if (result != -1)
					LebResult.setText("The number is : " + result + " index in the array");
				else
					LebResult.setText("The number is not in the array");
			} catch (Exception ex) {
				LebResult.setText("Enter a group of numbers like in the first culomen this -> 1 2 3 4 ........ ");
			}
		});

		GridPane newPane = new GridPane();

		newPane.add(tfInput, 0, 0);
		newPane.add(tfBinarySearch, 0, 1);
		newPane.add(butReverse, 0, 2);
		newPane.add(butSumDigits, 0, 3);
		newPane.add(butBinarySearch, 0, 4);
		newPane.setAlignment(Pos.CENTER);
		newPane.setVgap(10);
		BorderPane root = new BorderPane();
		root.setCenter(newPane);
		LebResult.setPadding(new Insets(10));

		root.setBottom(LebResult);
		root.setAlignment(LebResult, Pos.CENTER);
		return root;

	}

	/*******************************************************************************************************/
	public Pane Lap2() {
		TextField tfInput = new TextField();
		Button butAdd = new Button("Add Number");
		Button butDelete = new Button("Delete Number");
		Button butFind = new Button("Find Number");
		Button butReverse = new Button("Reverse");
		Button butDisplay = new Button("traverse");
		Label lblResult = new Label();
		butAdd.setOnAction(e -> {
			try {
				int Num = Integer.parseInt(tfInput.getText());
				Lab2_List.insert(Num);
				lblResult.setText("Done");
			} catch (NumberFormatException ex) {
				lblResult.setText("Enter true value");
			}
		});
		butDelete.setOnAction(e -> {
			try {
				int Num = Integer.parseInt(tfInput.getText());
				if (Lab2_List.delete(Num) == true)
					lblResult.setText("Done");
				else
					lblResult.setText("We the number does not exist");
			} catch (NumberFormatException ex) {
				lblResult.setText("Enter true value");
			}
		});
		butFind.setOnAction(e -> {
			try {
				int Num = Integer.parseInt(tfInput.getText());
				if (Lab2_List.find(Num) == true)
					lblResult.setText("The number : " + Num + " is in the list");
				else
					lblResult.setText("We the number does not exist");
			} catch (NumberFormatException ex) {
				lblResult.setText("Enter true value");
			}
		});
		butDisplay.setOnAction(e -> {
			lblResult.setText(Lab2_List.traverse());
		});
		butReverse.setOnAction(e -> {
			Lab2_List.reverseRec();
			lblResult.setText("Done");
		});
		lblResult.setFont(new Font(15));
		lblResult.setAlignment(Pos.CENTER);
		lblResult.setPadding(new Insets(15));
		GridPane buttomsGp = new GridPane();
		buttomsGp.add(butAdd, 0, 2);
		buttomsGp.add(butDelete, 4, 2);
		buttomsGp.add(butFind, 0, 3);
		buttomsGp.add(butReverse, 4, 3);
		buttomsGp.add(butDisplay, 2, 1);
		buttomsGp.add(tfInput, 2, 0);
		buttomsGp.setVgap(10);
		buttomsGp.setHgap(10);
		buttomsGp.setAlignment(Pos.CENTER);
		BorderPane MainPane = new BorderPane();
		MainPane.setCenter(buttomsGp);
		MainPane.setBottom(lblResult);
		butAdd.setPrefWidth(200);
		butDelete.setPrefWidth(200);
		butDisplay.setPrefWidth(200);
		butFind.setPrefWidth(200);
		butReverse.setPrefWidth(200);
		MainPane.setAlignment(lblResult, Pos.CENTER);
		return MainPane;

	}

	/*******************************************************************************************************/
	public Pane Lap3() {
		TextField tfInput = new TextField();
		Button butAdd = new Button("Add Number");
		Button butDelete = new Button("Delete Number");
		Button butFind = new Button("Find Number");
		Button butReverse = new Button("Reverse");
		Button butDisplay = new Button("traverse");
		Button Duplicate = new Button("Duplicate");
		Label lblResult = new Label();
		butAdd.setOnAction(e -> {
			try {
				int Num = Integer.parseInt(tfInput.getText());
				Lab3_List.insert(Num);
				lblResult.setText("Done");
			} catch (NumberFormatException ex) {
				lblResult.setText("Enter true value");
			}
		});
		butDelete.setOnAction(e -> {
			try {
				int Num = Integer.parseInt(tfInput.getText());
				if (Lab3_List.delete(Num) != null)
					lblResult.setText("Done");
				else
					lblResult.setText("We the number does not exist");
			} catch (NumberFormatException ex) {
				lblResult.setText("Enter true value");
			}
		});
		butFind.setOnAction(e -> {
			try {
				int Num = Integer.parseInt(tfInput.getText());
				if (Lab3_List.find(Num) != null)
					lblResult.setText("The number : " + Num + " is in the list");
				else
					lblResult.setText("We the number does not exist");
			} catch (NumberFormatException ex) {
				lblResult.setText("Enter true value");
			}
		});
		butDisplay.setOnAction(e -> {
			lblResult.setText(Lab3_List.traverse());
		});
		butReverse.setOnAction(e -> {
			Lab3_List.ReverseRec();
			lblResult.setText("Done");
		});
		Duplicate.setOnAction(e -> {
			Lab3_List.RemoveDuplicate();
			lblResult.setText("Done");
		});
		lblResult.setFont(new Font(15));
		lblResult.setAlignment(Pos.CENTER);
		lblResult.setPadding(new Insets(15));
		GridPane buttomsGp = new GridPane();
		buttomsGp.add(butAdd, 0, 2);
		buttomsGp.add(Duplicate, 2, 2);
		buttomsGp.add(butDelete, 4, 2);
		buttomsGp.add(butFind, 0, 3);
		buttomsGp.add(butReverse, 4, 3);
		buttomsGp.add(butDisplay, 2, 1);
		buttomsGp.add(tfInput, 2, 0);
		buttomsGp.setVgap(10);
		buttomsGp.setHgap(10);
		buttomsGp.setAlignment(Pos.CENTER);
		BorderPane MainPane = new BorderPane();
		MainPane.setCenter(buttomsGp);
		MainPane.setBottom(lblResult);
		butAdd.setPrefWidth(200);
		butDelete.setPrefWidth(200);
		butDisplay.setPrefWidth(200);
		butFind.setPrefWidth(200);
		butReverse.setPrefWidth(200);
		Duplicate.setPrefWidth(200);
		MainPane.setAlignment(lblResult, Pos.CENTER);
		return MainPane;

	}

	/*******************************************************************************************************/
	public Pane Lap4() {
		Lab4_List.initialization();
		TextField tfInput = new TextField();
		Button butAdd = new Button("Add Number");
		Button butDelete = new Button("Delete Number");
		Button butFind = new Button("Find Number");
		Button butMarge = new Button("Marge two list");
		Button butDisplay = new Button("traverse");
		Button addList = new Button("Add list");
		Label lblResult = new Label();
		butAdd.setOnAction(e -> {
			try {
				String[] result = tfInput.getText().split(" ");
				if (result.length == 2) {
					int Num = Integer.parseInt(result[0].trim());
					int headList = Integer.parseInt(result[1].trim());
					Lab4_List.insertAtHead(Num, headList);
					lblResult.setText("Done");
				} else {
					lblResult.setText("Enter true value: num headList");
				}
			} catch (Exception ex) {
				lblResult.setText("Enter true value");
			}
		});
		butDelete.setOnAction(e -> {
			try {
				String[] result = tfInput.getText().split(" ");
				if (result.length == 2) {
					int Num = Integer.parseInt(result[0].trim());
					int headList = Integer.parseInt(result[1].trim());
					if (Lab4_List.delete(Num, headList) != null)
						lblResult.setText("Done");
					else
						lblResult.setText("We the number does not exist");
				}
			} catch (NumberFormatException ex) {
				lblResult.setText("Enter true value");
			}
		});
		butFind.setOnAction(e -> {
			try {
				String[] result = tfInput.getText().split(" ");
				if (result.length == 2) {
					int Num = Integer.parseInt(result[0].trim());
					int headList = Integer.parseInt(result[1].trim());
					if (Lab4_List.find(Num, headList) != 0)
						lblResult.setText("The number : " + Num + " is in the list");
					else
						lblResult.setText("We the number does not exist");
				}
			} catch (NumberFormatException ex) {
				lblResult.setText("Enter true value");
			}
		});
		butDisplay.setOnAction(e -> {
			try {
				String[] result = tfInput.getText().split(" ");
				if (result.length == 1) {
					int headList = Integer.parseInt(result[0].trim());
					lblResult.setText(Lab4_List.traverse(headList));
				} else {
					lblResult.setText("Enter true value");
				}
			} catch (NumberFormatException ex) {
				lblResult.setText("Enter true value");
			}
		});
		butMarge.setOnAction(e -> {
			try {
				String[] result = tfInput.getText().split(" ");
				if (result.length == 2) {
					int Num = Integer.parseInt(result[0].trim());
					int headList = Integer.parseInt(result[1].trim());
					Lab4_List.merge2Lists(Num, headList);
					lblResult.setText("Done");
				}
			} catch (Exception ex) {
				lblResult.setText("Enter true value");
			}
		});
		addList.setOnAction(e -> {
			Lab4_List.createList();
			lblResult.setText("Done");
		});
		lblResult.setFont(new Font(15));
		lblResult.setAlignment(Pos.CENTER);
		lblResult.setPadding(new Insets(15));
		GridPane buttomsGp = new GridPane();
		buttomsGp.add(butAdd, 0, 2);
		buttomsGp.add(addList, 2, 2);
		buttomsGp.add(butDelete, 4, 2);
		buttomsGp.add(butFind, 0, 3);
		buttomsGp.add(butMarge, 4, 3);
		buttomsGp.add(butDisplay, 2, 1);
		buttomsGp.add(tfInput, 2, 0);
		buttomsGp.setVgap(10);
		buttomsGp.setHgap(10);
		buttomsGp.setAlignment(Pos.CENTER);
		BorderPane MainPane = new BorderPane();
		MainPane.setCenter(buttomsGp);
		MainPane.setBottom(lblResult);
		butAdd.setPrefWidth(200);
		butDelete.setPrefWidth(200);
		butDisplay.setPrefWidth(200);
		butFind.setPrefWidth(200);
		butMarge.setPrefWidth(200);
		addList.setPrefWidth(200);
		MainPane.setAlignment(lblResult, Pos.CENTER);
		return MainPane;

	}

	/*******************************************************************************************************/
	public Pane Lap5() {
		TextField tfInput = new TextField();
		Button butAdd = new Button("Add Number");
		Button butDelete = new Button("Delete Number");
		Button butFind = new Button("First Number");
		Button butClear = new Button("Clear");
		Button butDisplay = new Button("traverse");
		Label lblResult = new Label();
		butAdd.setOnAction(e -> {
			try {
				int Num = Integer.parseInt(tfInput.getText());

				Lab5_List.push(Num);
				lblResult.setText("Done");
			} catch (Exception ex) {
				lblResult.setText("Enter true value");
			}
		});
		butDelete.setOnAction(e -> {
			try {
				int Num = Integer.parseInt(tfInput.getText());
				if (Lab5_List.pop() != null)
					lblResult.setText("Done");
				else
					lblResult.setText("We the number does not exist");
			} catch (NumberFormatException ex) {
				lblResult.setText("Enter true value");
			}
		});
		butFind.setOnAction(e -> {
			try {
				int Num = Integer.parseInt(tfInput.getText());
				if (Lab5_List.peek() != 0)
					lblResult.setText("The number : " + Num + " is in the list");
				else
					lblResult.setText("We the number does not exist");
			} catch (NumberFormatException ex) {
				lblResult.setText("Enter true value");
			}
		});
		butDisplay.setOnAction(e -> {
			try {
				int Num = Integer.parseInt(tfInput.getText());
				lblResult.setText(Lab5_List.traverse());
			} catch (NumberFormatException ex) {
				lblResult.setText("Enter true value");
			}
		});
		butClear.setOnAction(e -> {

			Lab5_List.clear();
			lblResult.setText("Done");

		});

		lblResult.setFont(new Font(15));
		lblResult.setAlignment(Pos.CENTER);
		lblResult.setPadding(new Insets(15));
		GridPane buttomsGp = new GridPane();
		buttomsGp.add(butAdd, 0, 2);
		buttomsGp.add(butDelete, 4, 2);
		buttomsGp.add(butFind, 0, 3);
		buttomsGp.add(butClear, 4, 3);
		buttomsGp.add(butDisplay, 2, 1);
		buttomsGp.add(tfInput, 2, 0);
		buttomsGp.setVgap(10);
		buttomsGp.setHgap(10);
		buttomsGp.setAlignment(Pos.CENTER);
		BorderPane MainPane = new BorderPane();
		MainPane.setCenter(buttomsGp);
		MainPane.setBottom(lblResult);
		butAdd.setPrefWidth(200);
		butDelete.setPrefWidth(200);
		butDisplay.setPrefWidth(200);
		butFind.setPrefWidth(200);
		butClear.setPrefWidth(200);
		MainPane.setAlignment(lblResult, Pos.CENTER);
		return MainPane;

	}

	/*******************************************************************************************************/
	public Pane Lap6() {

		timeInput.setPromptText("Enter minutes for simulation");

		startSimulation.setOnAction(e -> {
			String result = "";
			textArea.clear();
			Lab6_List.clear();
			min = 0;
			totalIn = 0;
			totalOut = 0;
			totalWaitingTime = 0;
			try {
				int time = Integer.parseInt(timeInput.getText());
				result += ("Min" + "\t\t\t" + "In" + "\t\t\t" + "Total" + "\t\t" + "Out" + "\t\t" + "Waiting Time\n");
				result += ("-----------------------------------------------------------------\n");
				for (int i = 0; i < time; i++) {
					min = i;
					int[] out = processCustomer();
					int in = addCustomer();
					result += (i + "\t\t\t" + in + "\t\t\t" + totalIn + "\t\t\t" + out[0] + "\t\t\t" + out[1] + "\n");
				}
				while (!Lab6_List.isEmpty()) {
					min++;
					processCustomer();
				}
				double averageWaitingTime = (double) totalWaitingTime / totalOut;
				result += ("Average Waiting Time: " + "\t" + averageWaitingTime + "\t minutes\n");

			} catch (NumberFormatException ex) {
				result = ("Error: Please enter a valid number.\n");
			}
			lblresult.setText(result);
		});

		HBox controls = new HBox(10, startSimulation, timeInput);
		controls.setPadding(new Insets(10));

		scroll.setContent(lblresult);

		BorderPane mainPane = new BorderPane();
		mainPane.setCenter(scroll);
		mainPane.setBottom(controls);
		mainPane.setPadding(new Insets(10));
		mainPane.setAlignment(scroll, Pos.CENTER);
		mainPane.setAlignment(controls, Pos.CENTER);
		return mainPane;
	}

	public int addCustomer() {
		Random random = new Random();
		int newCustomers = random.nextInt(4);
		if (newCustomers == 0 || newCustomers == 3) {
			return 0;
		} else if (newCustomers == 2) {
			Lab6_List.equeue(min + 1);
			Lab6_List.equeue(min + 1);
			totalIn += 2;
			return 2;
		} else {
			Lab6_List.equeue(min + 1);
			totalIn++;
			return 1;
		}
	}

	public int[] processCustomer() {
		int waitingTime = 0;
		int pr = 0;
		if (!Lab6_List.isEmpty()) {
			int processTime = Lab6_List.dequeue();
			if (totalIn != 0) {
				waitingTime = min - processTime + 1;
				totalWaitingTime += waitingTime;
				totalOut++;
				totalIn--;
				pr = 1;
			}
		}
		int[] info = { pr, waitingTime };
		return info;
	}

	/*******************************************************************************************************/
	public Pane Lap7(Stage stage) {
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
								Martyr InsertMartyr = new Martyr(name, dateDeath, age, location, distirct, gender);
								District insertDistrict = new District(distirct.trim());
								TNode<District> newDistrict = distractList.find(insertDistrict);
								if (newDistrict == null) {
									Location insertLocation = new Location(location.trim());
									insertLocation.getMartyrList().push(InsertMartyr);
									insertDistrict.getList().insert(insertLocation);
									distractList.insert(insertDistrict);
								} else {
									Location insertLocation = new Location(location);
									TNode<Location> newLocation = newDistrict.getData().getList().find(insertLocation);
									if (newLocation == null) {
										insertLocation.getMartyrList().push(InsertMartyr);
										newDistrict.getData().getList().insert(insertLocation);
									} else {
										newLocation.getData().getMartyrList().push(InsertMartyr);
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
					Lap7_Details(stage);
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
		return root;
	}

	public void Lap7_Details(Stage stage) {
		int count = 0;
		distractList.traverseInOrder();
		LinkedStack<District> temp = distractList.getNextStack();
		while (!temp.isEmpty()) {
			count += temp.pop().getData().numberOfMartyr();
		}

		Label result = new Label("The number of input Martyr is: " + count);
		BorderPane root = new BorderPane();
		root.setCenter(result);
		Scene scene = new Scene(root, 400, 400);
		stage.setScene(scene);
		stage.show();
	}

	/*******************************************************************************************************/
	public Pane Lap8() {
		TextField tfInput = new TextField();
		Button butAdd = new Button("Add Number");
		Button butDelete = new Button("Delete Number");
		Button butDisplay = new Button("traverse");
		Label lblResult = new Label();
		butAdd.setOnAction(e -> {
			try {
				int Num = Integer.parseInt(tfInput.getText().trim());

				Lab8_List.insert(Num);
				lblResult.setText("Done");
			} catch (Exception ex) {
				lblResult.setText("Enter true value");
			}
		});
		butDelete.setOnAction(e -> {
			try {
				if (!tfInput.getText().isEmpty()) {
				int Num = Integer.parseInt(tfInput.getText());
				
					Lab8_List.delete(Num);
					lblResult.setText("Done");
				}else
					lblResult.setText("We the number does not exist");
			} catch (NumberFormatException ex) {
				lblResult.setText("Enter true value");
			}
		});
		
		butDisplay.setOnAction(e -> {
			
				lblResult.setText(Lab8_List.traverseInOrder());
			
		});

		lblResult.setFont(new Font(15));
		lblResult.setAlignment(Pos.CENTER);
		lblResult.setPadding(new Insets(15));
		GridPane buttomsGp = new GridPane();
		buttomsGp.add(butAdd, 2, 4);
		buttomsGp.add(butDelete, 2, 3);
		buttomsGp.add(butDisplay, 2, 2);
		buttomsGp.add(tfInput, 2, 0);
		buttomsGp.setVgap(10);
		buttomsGp.setHgap(10);
		buttomsGp.setAlignment(Pos.CENTER);
		BorderPane MainPane = new BorderPane();
		MainPane.setCenter(buttomsGp);
		MainPane.setBottom(lblResult);
		butAdd.setPrefWidth(200);
		butDelete.setPrefWidth(200);
		butDisplay.setPrefWidth(200);
		MainPane.setAlignment(lblResult, Pos.CENTER);
		return MainPane;

	}
}