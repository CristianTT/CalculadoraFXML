package dad.javafx.calculadora;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;

public class CalculadoraController implements Initializable {

	private Scene scene;

	@FXML
	private GridPane root;
	@FXML
	private TextField pantalla;

	private int indiceButton;
	private Button[] numerosButtons;

	private CalculadoraModel calculadora = new CalculadoraModel();

	public CalculadoraController() throws IOException {
		FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/CalculadoraView.fxml"));
		loader.setController(this);
		loader.load();
	}

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		pantalla.textProperty().bind(calculadora.pantallaProperty());
	}

	public void setScene(Scene scene) {
		this.scene = scene;
		numerosButtons = new Button[10];
		for (indiceButton = 0; indiceButton < 10; indiceButton++) {
			numerosButtons[indiceButton] = (Button) scene.lookup("#numero" + indiceButton);
			numerosButtons[indiceButton].setOnAction(e -> {
				calculadora.insertar((char) ('0' + Integer.parseInt(((Button) (e.getSource())).getText())));
			});
		}
	}

	public GridPane getView() {
		return root;
	}

	@FXML
	void sumar(ActionEvent event) {
		calculadora.operar(CalculadoraModel.SUMAR);
	}

	@FXML
	void restar(ActionEvent event) {
		calculadora.operar(CalculadoraModel.RESTAR);
	}

	@FXML
	void multiplicar(ActionEvent event) {
		calculadora.operar(CalculadoraModel.MULTIPLICAR);
	}

	@FXML
	void dividir(ActionEvent event) {
		calculadora.operar(CalculadoraModel.DIVIDIR);
	}

	@FXML
	void agregarComa(ActionEvent event) {
		calculadora.insertarComa();
	}

	@FXML
	void teclaC(ActionEvent event) {
		calculadora.borrarTodo();
	}

	@FXML
	void teclaCE(ActionEvent event) {
		calculadora.borrar();
	}

	@FXML
	void calcular(ActionEvent event) {
		calculadora.operar(CalculadoraModel.IGUAL);
	}
}
