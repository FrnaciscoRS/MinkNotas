package Francisco.MinkNotas.MinkNotas.Model.Dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import Francisco.MinkNotas.MinkNotas.Utils.Conexion;

public class NotasDao extends Notas {
	private static final String GETBYID = "SELECT id,nombre,fechaCreacion,contenido,usuario as user FROM notas WHERE id=";
	private static final String SELECTALL = "SELECT nombre,FechaCreacion,contenido,id,id_user FROM `notas`";
	private final static String SELECTBYNAME = "SELECT nombre,FechaCreacion,contenido,id FROM notas WHERE nombre LIKE ?";
	private static final String DELETE = "DELETE FROM notas WHERE id=?";
	private final static String INSERTUPDATE = "INSERT INTO notas (nombre,FechaCreacion,contenido,id_user) "
			+ "VALUES (?,?,?,?) ";

	public NotasDao(int id, String nombre, LocalDate fechaCreacion, String contenido, User usuario) {
		super(id, nombre, fechaCreacion, contenido, usuario);
		// TODO Auto-generated constructor stub
	}

	public NotasDao(int id, String nombre, LocalDate fechaCreacion, String contenido) {
		super(id, nombre, fechaCreacion, contenido);
		// TODO Auto-generated constructor stub
	}

	public NotasDao(String nombre, LocalDate fechaCreacion, String contenido, User usuario) {
		super(nombre, fechaCreacion, contenido, usuario);
		// TODO Auto-generated constructor stub
	}

	public NotasDao() {
		super();
		// TODO Auto-generated constructor stub
	}

	public NotasDao(Notas n) {
		this.id = n.id;
		this.nombre = n.nombre;
		this.fechaCreacion = n.fechaCreacion;
		this.contenido = n.contenido;
		this.usuario = n.usuario;
	}


	public static List<Notas> listarTodas() {
		List<Notas> result = new ArrayList<Notas>();
		try {
			Connection con = Conexion.getConextion();
			Statement st = con.createStatement();
			ResultSet rs = st.executeQuery(SELECTALL);
			// ResultSet rs2 = st.executeQuery(GETBYIDUSER);

			while (rs.next()) {
				int id = rs.getInt("id");
				String nombre = rs.getString("nombre");
				LocalDate fechaCreacion = rs.getDate("fechaCreacion").toLocalDate();
				String contenido = rs.getString("contenido");
				User usuario = new UserDao(rs.getInt("id_user"));

				Notas n = new Notas(id, nombre, fechaCreacion, contenido, usuario);
				result.add(n);
			}

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return result;
	}

	public static List<Notas> buscaPorNombre(String nombre) {
		List<Notas> result = new ArrayList<Notas>();
		Connection con = Conexion.getConextion();
		if (con != null) {
			try {
				PreparedStatement q = con.prepareStatement(SELECTBYNAME);
				q.setString(1, "%" + nombre + "%");
				ResultSet rs = q.executeQuery();
				while (rs.next()) {
					// es que hay al menos un resultado
					Notas a = new Notas();
					a.setId(rs.getInt("id"));
					a.setNombre(rs.getString("nombre"));
					a.setFechaCreacion(rs.getDate("fechaCreacion").toLocalDate());
					a.setContenido(rs.getString("contenido"));
					result.add(a);
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

		return result;
	}

	public boolean eliminarNotas() {
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

	public int guardarNota() {
		int rs = 0;
		Connection con = Conexion.getConextion();
		if (con != null) {
			try {
				PreparedStatement q = con.prepareStatement(INSERTUPDATE);
				//q.setInt(1, this.id);
				q.setString(1, this.nombre);
				q.setObject(2, this.fechaCreacion);
				q.setString(3, this.contenido);
				q.setInt(4, this.usuario.getId());

				rs = q.executeUpdate();

			} catch (SQLException e) { // TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return rs;
	}

	public static List<Notas> getNotasbyUser(int id) {
List<Notas> result=new ArrayList<Notas>();
		
		Connection con = Conexion.getConextion();
		if (con != null) {
			try {
				PreparedStatement q=con.prepareStatement(GETBYID);
				q.setInt(1, id);
				ResultSet rs =q.executeQuery();
				while(rs.next()) {
					//cada row
					Notas l=new Notas();
					l.setId(rs.getInt("id"));
					l.setFechaCreacion(rs.getDate("fechaCreacion").toLocalDate());
					l.setNombre(rs.getString("nombre"));
					
					User a=new User();
					a.setDni(rs.getString("dni"));
					a.setEdad(rs.getInt("edad"));
					a.setNombre(rs.getString("nombre"));
					
					l.setUsuario(a);
					result.add(l);
				}			
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return result;
	}

}
