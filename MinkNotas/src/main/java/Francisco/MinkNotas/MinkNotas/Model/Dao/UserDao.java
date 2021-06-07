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
	private final static String SELECTBYNAME ="SELECT id,nombre,edad,dni FROM user WHERE nombre LIKE ?";

	public UserDao(int id, String nombre, int edad, String dni) {
		super(id, nombre, edad, dni);
	}

	public UserDao() {
		super();
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
	
	public static List<User> buscaPorNombre(String nombre) {
		List<User> result=new ArrayList<User>();
		Connection con = Conexion.getConextion();
		if (con != null) {
			try {
				PreparedStatement q=con.prepareStatement(SELECTBYNAME);
				q.setString(1, "%"+nombre+"%");
				ResultSet rs=q.executeQuery();
				while(rs.next()) {
					//es que hay al menos un resultado
					User a=new User();
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

}
