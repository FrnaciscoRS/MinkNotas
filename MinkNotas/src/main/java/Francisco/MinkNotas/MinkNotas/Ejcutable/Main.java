package Francisco.MinkNotas.MinkNotas.Ejcutable;

import Francisco.MinkNotas.MinkNotas.Model.Dao.User;
import Francisco.MinkNotas.MinkNotas.Model.Dao.UserDao;
import java.util.List;
public class Main {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		/*List<User> todas=UserDao.listarTodos();
		for(User cadauna:todas) {
			System.out.println(cadauna);
		}*/
		
		List<User> b=UserDao.buscaPorNombre("paco");
		
			System.out.println(b);
		
	}

}
