package Francisco.MinkNotas.MinkNotas.Model.Dao;

import java.time.LocalDate;


public class Notas {
	protected int id;
	protected String nombre;
	protected LocalDate fechaCreacion;
	protected String contenido;
	protected User usuario;

	public Notas(int id, String nombre, LocalDate fechaCreacion, String contenido, User usuario) {
		super();
		this.id = id;
		this.nombre = nombre;
		this.fechaCreacion = fechaCreacion;
		this.contenido = contenido;
		this.usuario = usuario;
	}

	public Notas() {
		super();
	}
	
	

	public Notas(String nombre, LocalDate fechaCreacion, String contenido, User usuario) {
		super();
		this.nombre = nombre;
		this.fechaCreacion = fechaCreacion;
		this.contenido = contenido;
		this.usuario = usuario;
	}

	public Notas(int id, String nombre, LocalDate fechaCreacion, String contenido) {
		super();
		this.id = id;
		this.nombre = nombre;
		this.fechaCreacion = fechaCreacion;
		this.contenido = contenido;
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

	public LocalDate getFechaCreacion() {
		return fechaCreacion;
	}

	public void setFechaCreacion(LocalDate fechaCreacion) {
		this.fechaCreacion = fechaCreacion;
	}

	public String getContenido() {
		return contenido;
	}

	public void setContenido(String contenido) {
		this.contenido = contenido;
	}

	public User getUsuario() {
		return usuario;
	}

	public void setUsuario(User usuario) {
		this.usuario = usuario;
	}

	@Override
	public String toString() {
		return "\n"+"Notas [id=" + id + ", nombre=" + nombre + ", fechaCreacion=" + fechaCreacion + ", contenido="
				+ contenido + ", usuario=" + usuario + "]";
	}

}
