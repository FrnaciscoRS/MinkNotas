package Francisco.MinkNotas.MinkNotas.Utils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;


	public class Conexion {
		private static Connection con;
		
		public static Connection getConextion() {
			
			if(con==null) {
				try {
					con=DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/minknotas","root","");
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}

			}
			
			return con;
		}
	}

