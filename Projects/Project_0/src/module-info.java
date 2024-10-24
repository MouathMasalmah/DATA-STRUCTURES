module Project_0 {
	requires javafx.controls;
	requires javafx.graphics;
	
	opens Main to javafx.graphics, javafx.fxml;
}
