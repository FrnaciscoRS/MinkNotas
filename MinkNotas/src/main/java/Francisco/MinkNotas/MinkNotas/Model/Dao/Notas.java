package Francisco.MinkNotas.MinkNotas.Model.Dao;

import java.time.LocalDateTime;

public class Notas {
	protected int id;
	protected String nombre;
	protected LocalDateTime fechaCreacion;
	protected String contenido;

	public Notas(int id, String nombre, LocalDateTime fechaCreacion, String contenido) {
		super();
		this.id = id;
		this.nombre = nombre;
		this.fechaCreacion = fechaCreacion;
		this.contenido = contenido;
	}

	public Notas() {
		// super();
		this(-1, "", LocalDateTime.now(), "");
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public LocalDateTime getFechaCreacion() {
		return fechaCreacion;
	}

	public void setFechaCreacion(LocalDateTime fechaCreacion) {
		this.fechaCreacion = fechaCreacion;
	}

	public String getContenido() {
		return contenido;
	}

	public void setContenido(String contenido) {
		this.contenido = contenido;
	}

	@Override
	public String toString() {
		return "Notas [id=" + id + ", nombre=" + nombre + ", fechaCreacion=" + fechaCreacion + ", contenido="
				+ contenido + "]";
	}

}
