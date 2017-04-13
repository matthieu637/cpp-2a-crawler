package cpp2017.database;

import java.sql.Connection;
import java.sql.DriverManager;

/**
 * @author MatthieuDEVALLE
 * Classe permettant de se connecter à une base de données via JDBC
 *
 */
public class Connexion {
	public void connect() {
		try {
			Class.forName("org.sqlite.JDBC");
			Connection con = DriverManager.getConnection("jdbc:sqlite:test.db");
			System.out.println("Connexion OK");

		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("Erreur de connexion");
		}
	}
}
