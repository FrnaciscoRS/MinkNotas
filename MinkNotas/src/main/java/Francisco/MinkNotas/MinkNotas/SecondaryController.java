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

	@FXML
	protected void initialize() {
		// muestrainfo(null);
		configuraTabla();
		List<Notas> todas = NotasDao.listarTodas();
		tablanotas.setItems(FXCollections.observableArrayList(todas));
		tablanotas.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
			muestrainfo(newValue);
		});

		// muestrainfo(newValue);
		// });

	}

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

	private void muestrainfo(Notas u) {
		if (u != null) {
			contenidoTF.setText(u.getContenido());
			nombreTF.setText(u.getNombre());
			fechaDP.setPromptText(String.valueOf(u.getFechaCreacion()));
		} else {
			contenidoTF.setText("");
			nombreTF.setText("");
			fechaDP.setPromptText(String.valueOf(""));
		}
	}

	
	public void aniadir(ActionEvent event) {
		NotasDao u = new NotasDao();
		u.setNombre(nombreTF.getText());
		u.setFechaCreacion(fechaDP.getValue());
		u.setContenido(contenidoTF.getText());
		u.setUsuario(new UserDao(Integer.parseInt(idusuarioTF.getText())));
		u.guardarNota();
	}

	public void eliminar(ActionEvent event) {
		NotasDao u = new NotasDao();
		int a = tablanotas.getSelectionModel().getSelectedItem().getId();
		u.setId(a);
		u.eliminarNotas();

	}

	@FXML
	private void switchToPrimary() throws IOException {
		App.setRoot("primary");
	}

	@FXML
	private void switchToSecondary() throws IOException {
		App.setRoot("secondary");
	}
}