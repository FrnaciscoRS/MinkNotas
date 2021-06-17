package Francisco.MinkNotas.MinkNotas;

import java.io.IOException;
import java.time.LocalDate;
import java.util.List;

import Francisco.MinkNotas.MinkNotas.Model.Dao.Notas;
import Francisco.MinkNotas.MinkNotas.Model.Dao.NotasDao;
import Francisco.MinkNotas.MinkNotas.Model.Dao.User;
import Francisco.MinkNotas.MinkNotas.Model.Dao.UserDao;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;

public class SecondaryController {
	// Declaramos los Label
	@FXML
	private Label nombreLAbel;
	@FXML
	private Label fechaLAbel;
	@FXML
	private Label contenidoLAbel;
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
	@FXML
	private Button verusuariosBT;
	@FXML
	private Button refresthBT;

	// Declaramos los textfileds
	@FXML
	private TextField nombreTF;
	@FXML
	private TextField contenidoTF;
	@FXML
	private TextField idusuarioTF;
	@FXML
	private TextField idTF;

	// Declaramos los textfileds
	@FXML
	private DatePicker fechaDP;

	@FXML
	private TableView<Notas> tablanotas;
	@FXML
	private TableColumn<Notas, String> nombreColumna;
	@FXML
	private TableColumn<Notas, String> ContenidoColumna;
	@FXML
	private TableColumn<Notas, String> FechaCreacionColumna;
	@FXML
	private TableColumn<Notas, String> idColumna;
	@FXML
	private TableColumn<Notas, String> id_UsuarioColumna;

	/**
	 * Metodo que inicializa la segunda vista
	 */
	@FXML
	protected void initialize() {
		// muestrainfo(null);
		configuraTabla();
		List<Notas> todas = NotasDao.listarTodas();
		tablanotas.setItems(FXCollections.observableArrayList(todas));
		tablanotas.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
			muestrainfo(newValue);
		});

	}

	/**
	 * Metodo para mostrar informacion en el tableview
	 */
	private void configuraTabla() {
		nombreColumna.setCellValueFactory(cadapersona -> {
			SimpleStringProperty v = new SimpleStringProperty();
			v.setValue(cadapersona.getValue().getNombre());
			return v;
		});
		ContenidoColumna.setCellValueFactory(cadapersona -> {
			SimpleStringProperty v = new SimpleStringProperty();
			v.setValue(cadapersona.getValue().getContenido());
			return v;
		});
		idColumna.setCellValueFactory(cadapersona -> {
			SimpleObjectProperty v = new SimpleObjectProperty();
			v.setValue(cadapersona.getValue().getId());
			return v;
		});
		FechaCreacionColumna.setCellValueFactory(cadapersona -> {
			SimpleObjectProperty v = new SimpleObjectProperty();
			v.setValue(cadapersona.getValue().getFechaCreacion());
			return v;
		});
		id_UsuarioColumna.setCellValueFactory(cadapersona -> {
			SimpleObjectProperty v = new SimpleObjectProperty();
			v.setValue(cadapersona.getValue().getUsuario().getId());
			return v;
		});

	}

	/**
	 * Metodo que me permite mostrar toda la informacion de las notas en los
	 * TEXTFIELD
	 * 
	 * @param u
	 */
	private void muestrainfo(Notas u) {
		if (u != null) {
			contenidoTF.setText(u.getContenido());
			nombreTF.setText(u.getNombre());
			fechaDP.setPromptText(String.valueOf(u.getFechaCreacion()));
			idusuarioTF.setText(String.valueOf(u.getUsuario().getId()));
			idTF.setText(String.valueOf(u.getId()));
		} else {
			contenidoTF.setText("");
			nombreTF.setText("");
			fechaDP.setPromptText(String.valueOf(""));
			idusuarioTF.setText(String.valueOf(""));
			idTF.setText(String.valueOf(""));
		}
	}

	/**
	 * Metodo para añadir nuevas notas
	 * 
	 * @param event
	 */
	public void aniadir(ActionEvent event) {
		NotasDao u = new NotasDao();
		u.setNombre(nombreTF.getText());
		u.setFechaCreacion(fechaDP.getValue());
		u.setContenido(contenidoTF.getText());
		u.setUsuario(new UserDao(Integer.parseInt(idusuarioTF.getText())));
		u.guardarNota();
	}

	/**
	 * Metodo para eliminar notas
	 * 
	 * @param event
	 */
	public void eliminar(ActionEvent event) {
		NotasDao u = new NotasDao();
		int a = tablanotas.getSelectionModel().getSelectedItem().getId();
		u.setId(a);
		u.eliminarNotas();

	}

	/**
	 * Metodo para modificar las notas
	 * 
	 * @param event
	 */
	public void ModificarNotas(ActionEvent event) {
		NotasDao u = new NotasDao();
		u.setNombre(nombreTF.getText());
		u.setFechaCreacion(fechaDP.getValue());
		u.setContenido(contenidoTF.getText());
		u.setUsuario(new UserDao(Integer.parseInt(idusuarioTF.getText())));
		u.setId(Integer.parseInt(idTF.getText()));
		u.actualizarNotas();

	}

	/**
	 * Metodo QUe me permite cambiar a la vista de usuarios
	 * 
	 * @throws IOException
	 */
	@FXML
	private void switchToPrimary() throws IOException {
		App.setRoot("primary");
	}

	/**
	 * Metodo QUe me permite refrescar la pagina
	 * 
	 * @throws IOException
	 */

	@FXML
	private void switchToSecondary() throws IOException {
		App.setRoot("secondary");
	}
}