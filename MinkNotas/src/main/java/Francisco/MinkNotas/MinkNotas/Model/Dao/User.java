package Francisco.MinkNotas.MinkNotas.Model.Dao;

public class User {
	protected int id;
	protected String nombre;
	protected int edad;
	protected String dni;
	public User(int id, String nombre, int edad, String dni) {
		//super();
		this.id = id;
		this.nombre = nombre;
		this.edad = edad;
		this.dni = dni;
	}
	public User() {
		//super();
		this(-1,"",-1,"");
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
	@Override
	public String toString() {
		return "User [id=" + id + ", nombre=" + nombre + ", edad=" + edad + ", dni=" + dni + "]";
	}

}