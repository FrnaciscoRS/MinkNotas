package Francisco.MinkNotas.MinkNotas;

import java.awt.TextField;
import java.awt.event.ActionEvent;
import java.io.IOException;
import java.util.List;

import Francisco.MinkNotas.MinkNotas.Model.Dao.User;
import Francisco.MinkNotas.MinkNotas.Model.Dao.UserDao;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
//import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.beans.property.SimpleObjectProperty;

public class PrimaryController {
	@FXML
	private Label nombreLAbel;
	@FXML
	private Label edadLAbel;
	@FXML
	private Label dniLAbel;
	@FXML
	private Label idLAbel;

	// Declaramos los botones
	@FXML
	private Button aniadirBT;
	@FXML
	private Button modificarBT;
	@FXML
	private Button eliminarBT;
	@FXML
	private Button nuevoBT;

	// Declaramos los textfileds
	@FXML
	private TextField nombreTF;
	@FXML
	private TextField edadTF;
	@FXML
	private TextField dniTF;

	@FXML
	private TableView<User> tablauser;
	@FXML
	private TableColumn<User, String> nombreColumna;
	@FXML
	private TableColumn<User, String> edadColumna;
	@FXML
	private TableColumn<User, String> dniColumna;
	@FXML
	private TableColumn<User, String> idColumna;

	@FXML
	protected void initialize() {
		System.out.println("Cargando...");
		// muestrainfo(null);
		configuraTabla();
		List<User> todos = UserDao.listarTodos();
		tablauser.setItems(FXCollections.observableArrayList(todos));
		// tablauser.getSelectionModel().selectedItemProperty().addListener((observable,
		// oldValue, newValue) -> {
		// muestrainfo(newValue);
		// });

	}

	private void configuraTabla() {
		dniColumna.setCellValueFactory(cadapersona -> {
			SimpleStringProperty v = new SimpleStringProperty();
			v.setValue(cadapersona.getValue().getDni());
			return v;
		});
		nombreColumna.setCellValueFactory(cadapersona -> {
			SimpleStringProperty v = new SimpleStringProperty();
			v.setValue(cadapersona.getValue().getNombre());
			return v;
		});
		edadColumna.setCellValueFactory(cadapersona -> {
			SimpleObjectProperty v = new SimpleObjectProperty();
			v.setValue(cadapersona.getValue().getEdad());
			return v;
		});
		idColumna.setCellValueFactory(cadapersona -> {
			SimpleObjectProperty v = new SimpleObjectProperty();
			v.setValue(cadapersona.getValue().getId());
			return v;
		});
	}

	private void muestrainfo(User u) {
		if (u != null) {
			idLAbel.setText(String.valueOf(u.getId()));
			dniLAbel.setText(u.getDni());
			nombreLAbel.setText(u.getNombre());
			edadLAbel.setText(String.valueOf(u.getEdad()));
		} else {
			idLAbel.setText(String.valueOf(""));
			dniLAbel.setText("");
			nombreLAbel.setText("");
			edadLAbel.setText(String.valueOf(""));
		}
	}

	/*private void borrauser(ActionEvent event, User u) {
		UserDao a = new UserDao();
		a.setId(c.getId());
		a.eliminarUser();

	}*/

	private void aniadir(ActionEvent event) {
		UserDao u = new UserDao();
		u.setNombre(nombreTF.getText());
		u.setEdad(Integer.parseInt(edadTF.getText()));
		u.setDni(nombreTF.getText());
		u.guardarUser();
	}

	/*
	 * private void borrauser(Button event) {
	 * 
	 * UserDao a = new UserDao(); a.buscaPorID(id); a.setId(); a.eliminarUser();
	 * 
	 * }
	 */
}
