package Francisco.MinkNotas.MinkNotas.Model.Dao;

import java.util.List;

public class User {
	protected int id;
	protected String nombre;
	protected int edad;
	protected String dni;
	protected List<Notas> notas;

	public User(int id, String nombre, int edad, String dni, List<Notas> notas) {
		super();
		this.id = id;
		this.nombre = nombre;
		this.edad = edad;
		this.dni = dni;
		this.notas = notas;
	}
	

	public User(String nombre, int edad, String dni) {
		super();
		this.nombre = nombre;
		this.edad = edad;
		this.dni = dni;
	}


	public User(int id, String nombre, int edad, String dni) {
		super();
		this.id = id;
		this.nombre = nombre;
		this.edad = edad;
		this.dni = dni;
	}
	

	public User(String nombre, int edad, String dni, List<Notas> notas) {
		super();
		this.nombre = nombre;
		this.edad = edad;
		this.dni = dni;
		this.notas = notas;
	}

	public User() {
		// super();
		this(-1, "", -1, "");
	}

	public User(int id) {
		super();
		this.id = id;
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

	public int getEdad() {
		return edad;
	}

	public void setEdad(int edad) {
		this.edad = edad;
	}

	public String getDni() {
		return dni;
	}

	public void setDni(String dni) {
		this.dni = dni;
	}

	public List<Notas> getNotas() {
		return notas;
	}

	public void setNotas(List<Notas> notas) {
		this.notas = notas;
	}

	@Override
	public String toString() {
		return "User [id=" + id + ", nombre=" + nombre + ", edad=" + edad + ", dni=" + dni + ", notas=" + notas + "]";
	}


	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + id;
		return result;
	}


	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		User other = (User) obj;
		if (id != other.id)
			return false;
		return true;
	}
	

}