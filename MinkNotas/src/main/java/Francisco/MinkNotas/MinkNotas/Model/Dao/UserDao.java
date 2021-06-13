package Francisco.MinkNotas.MinkNotas.Model.Dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import Francisco.MinkNotas.MinkNotas.Utils.Conexion;

public class UserDao extends User {
	private static final String SELECTALL = "SELECT id,nombre,edad,dni FROM `user`";
	private static final String GETBYID = "SELECT id,nombre,edad,dni FROM user WHERE id=";
	private final static String SELECTBYDNI = "SELECT id,nombre,edad,dni FROM user WHERE dni LIKE ?";
	private static final String DELETE = "DELETE FROM user WHERE id=?";
	private final static String INSERTUPDATE = "INSERT INTO user (nombre,edad,dni) " + "VALUES (?,?,?) ";

	public UserDao(int id, String nombre, int edad, String dni, List<Notas> notas) {
		super(id, nombre, edad, dni, notas);
		// TODO Auto-generated constructor stub
	}

	public UserDao(int id, String nombre, int edad, String dni) {
		super(id, nombre, edad, dni);
	}
	

	public UserDao(String nombre, int edad, String dni) {
		super(nombre, edad, dni);
		// TODO Auto-generated constructor stub
	}

	public UserDao(String nombre, int edad, String dni, List<Notas> notas) {
		super(nombre, edad, dni, notas);
		// TODO Auto-generated constructor stub
	}

	public UserDao() {
		super();
	}

	public UserDao(User a) {
		this.id = a.id;
		this.nombre = a.nombre;
		this.edad = a.edad;
		this.dni = a.dni;
		this.notas = a.notas;

	}

	public UserDao(int id) {
		// getByID from mariaDB
		// Conexion
		super();
		Connection con = Conexion.getConextion();
		// Stament
		if (con != null) {
			try {
				Statement st = con.createStatement();
				String q = GETBYID + id;
				ResultSet rs = st.executeQuery(q);
				while (rs.next()) {
					this.id = rs.getInt("id");
					this.nombre = rs.getString("nombre");
					this.edad = rs.getInt("edad");
					this.dni = rs.getString("dni");
				}
				//this.notas=NotasDao.getNotasbyUser(this.id);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

	public static List<User> listarTodos() {
		List<User> result = new ArrayList<User>();
		try {
			Connection con = Conexion.getConextion();
			Statement st = con.createStatement();
			ResultSet rs = st.executeQuery(SELECTALL);

			while (rs.next()) {
				int id = rs.getInt("id");
				String nombre = rs.getString("nombre");
				int edad = rs.getInt("edad");
				String dni = rs.getString("dni");

				User u = new User(id, nombre, edad, dni);
				result.add(u);
			}

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return result;
	}

	public static List<User> buscaPorDNI(String dni) {
		List<User> result = new ArrayList<User>();
		Connection con = Conexion.getConextion();
		if (con != null) {
			try {
				PreparedStatement q = con.prepareStatement(SELECTBYDNI);
				q.setString(1, "%" + dni + "%");
				ResultSet rs = q.executeQuery();
				while (rs.next()) {
					// es que hay al menos un resultado
					User a = new User();
					a.setId(rs.getInt("id"));
					a.setDni(rs.getString("dni"));
					a.setNombre(rs.getString("nombre"));
					a.setEdad(rs.getInt("edad"));
					result.add(a);
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

		return result;
	}

	public boolean eliminarUser() {
		boolean result = false;
		Connection con = Conexion.getConextion();
		if (con != null) {
			try {
				System.out.println(this.id);
				PreparedStatement q = con.prepareStatement(DELETE);
				q.setInt(1, this.id);
				q.executeUpdate();
				result = true;
				this.id = -1;
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

		return result;
	}

	/**
	 * Método que inserta la instancia
	 * 
	 * @return 0 si no se consiguió insertar o un numero positivo en función de las
	 *         instancias insertadas, lo normal es que devuelva 1 si todo ha ido
	 *         bien.
	 */
	public int guardarUser() {
		int rs = 0;
		Connection con = Conexion.getConextion();
		if (con != null) {
			try {
				PreparedStatement q = con.prepareStatement(INSERTUPDATE);
				//q.setInt(1, this.id);
				q.setString(1, this.nombre);
				q.setInt(2, this.edad);
				q.setString(3, this.dni);
				rs = q.executeUpdate();

			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return rs;
	}

}
