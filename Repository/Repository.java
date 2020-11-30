package Repository;

import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import Entities.Beteg;
import Entities.Doktor;
import Entities.Kezelesek;
import Entities.Rokon;

public class Repository {

	private final String DP_URL = "jdbc:mysql://localhost:3306/klinika";
	private final String DP_PASS = "";
	private final String DP_USER = "root";
	private Connection conn;

	public Repository() {
		initDatabase();

	}

	private void initDatabase() {
		try {
			conn = DriverManager.getConnection(DP_URL, DP_USER, DP_PASS);
		} catch (SQLException e) {
			System.out.println("Hiba az adatb�zis kapcsolat l�trehoz�s�ban! " + e);
		}

	}

	public List<Doktor> findDoktor() {
		String query = "SELECT * FROM doktor";
		List<Doktor> resultList = new ArrayList<>();

		try {
			Statement stmt = conn.createStatement();
			ResultSet result = stmt.executeQuery(query);

			while (result.next()) {
				Doktor doktor = new Doktor();
				doktor.setDrId(result.getInt("dr_id"));
				doktor.setSzemIgSzam(result.getString("SzemIgSz�m"));
				doktor.setNev(result.getString("n�v"));
				doktor.setSzakkepesites(result.getString("szakk�pes�t�s"));
				doktor.setMuthet(result.getBoolean("m�thet"));

				resultList.add(doktor);

			}
		} catch (SQLException e) {

			System.err.println("Hiba a lek�rdez�s k�zben [findDoktor] " + e);

			e.printStackTrace();
		}
		return resultList;

	}

	public List<Rokon> findRokon() {
		String query = "SELECT * FROM rokon";
		List<Rokon> resultList = new ArrayList<>();

		try {
			Statement stmt = conn.createStatement();
			ResultSet result = stmt.executeQuery(query);

			while (result.next()) {
				Rokon rokon = new Rokon();

				rokon.setNev(result.getString("n�v"));
				rokon.setCim(result.getString("c�m"));
				rokon.setTelefonszam(result.getString("telefonsz�m"));

				resultList.add(rokon);

			}
		} catch (SQLException e) {
			System.err.println("Hiba a lek�rdez�s k�zben [findRokon] " + e);
			e.printStackTrace();
		}
		return resultList;

	}

	public List<Kezelesek> findKezelesek() {

		String query = "SELECT * FROM kezel�sek ";
		List<Kezelesek> resultList = new ArrayList<>();

		try {
			Statement stmt = conn.createStatement();

			ResultSet result = stmt.executeQuery(query);

			while (result.next()) {
				Kezelesek kezelesek = new Kezelesek();

				kezelesek.setKezelesek(result.getString("kezel�s"));
				resultList.add(kezelesek);
			}

		} catch (SQLException e) {
			System.err.println("Hiba a lek�rdez�s k�zben [findKezl�sek] " + e);
			e.printStackTrace();
		}
		return resultList;

	}

	public String ujkezeles(String k, String email) {
		String query = "UPDATE beteg SET kezel�s = ? WHERE email = ?";
		PreparedStatement stmt;
		try {
			stmt = conn.prepareStatement(query);

			stmt.setString(1, k);
			stmt.setString(2, email);
			stmt.executeUpdate();
		} catch (SQLException e) {
			System.err.println("Hiba a hozz�ad�s k�zben [ujkezel�s] " + e);
			e.printStackTrace();
		}
		return null;

	}

	public void betegToRokon(int rokonId, int betegId) {
		// TODO VALID�TOROK

		String query = "insert into " + "beteg_rokon (rokon_id, beteg_id)" + "values(?,?)";
		PreparedStatement pstmt;

		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setInt(1, rokonId);
			pstmt.setInt(2, betegId);
			pstmt.execute();
		} catch (SQLException e) {
			System.err.println("Hiba a hozz�ad�s k�zben [betegtoRokon] " + e);
			e.printStackTrace();
		}

	}

	public Integer insertDoktor(Doktor doktor) {
		String query = "INSERT INTO doktor (SzemIgSz�m,n�v,szakk�pes�t�s,m�thet) " + "VALUES (?,?,?,?)";

		try {
			PreparedStatement pstmt = conn.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);

			pstmt.setString(1, doktor.getSzemIgSzam());
			pstmt.setString(2, doktor.getNev());
			pstmt.setString(3, doktor.getSzakkepesites());
			pstmt.setBoolean(4, doktor.isMuthet());

			pstmt.execute();
			ResultSet rs = pstmt.getGeneratedKeys();
			rs.next();
			return rs.getInt(1);
		} catch (SQLException e) {
			System.err.println("Hiba a hozz�ad�s k�zben [insertDoktor] " + e);
			e.printStackTrace();
		}
		return null;
	}

	public int deleteRecord(String table, String column, int id) {
		String query = "DELETE FROM " + table + " WHERE " + column + " = ?";
		try {
			PreparedStatement stmt = conn.prepareStatement(query);
			stmt.setInt(1, id);
			return stmt.executeUpdate();
		} catch (SQLException e) {

			e.printStackTrace();
		}
		return 0;
	}

	public void closeConnection() {

		try {
			if (conn != null) {
				conn.close();
			}
		} catch (SQLException ex) {
			System.out.println("Hiba a kapcsolat lez�r�s�ban " + ex);

		}
	}

}
