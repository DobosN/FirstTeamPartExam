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
			System.out.println("Hiba az adatbázis kapcsolat létrehozásában! " + e);
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
				doktor.setSzemIgSzam(result.getString("SzemIgSzám"));
				doktor.setNev(result.getString("név"));
				doktor.setSzakkepesites(result.getString("szakképesítés"));
				doktor.setMuthet(result.getBoolean("mûthet"));

				resultList.add(doktor);

			}
		} catch (SQLException e) {

			System.err.println("Hiba a lekérdezés közben [findDoktor] " + e);

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

				rokon.setNev(result.getString("név"));
				rokon.setCim(result.getString("cím"));
				rokon.setTelefonszam(result.getString("telefonszám"));

				resultList.add(rokon);

			}
		} catch (SQLException e) {
			System.err.println("Hiba a lekérdezés közben [findRokon] " + e);
			e.printStackTrace();
		}
		return resultList;

	}

	public List<Kezelesek> findKezelesek() {

		String query = "SELECT * FROM kezelések ";
		List<Kezelesek> resultList = new ArrayList<>();

		try {
			Statement stmt = conn.createStatement();

			ResultSet result = stmt.executeQuery(query);

			while (result.next()) {
				Kezelesek kezelesek = new Kezelesek();

				kezelesek.setKezelesek(result.getString("kezelés"));
				resultList.add(kezelesek);
			}

		} catch (SQLException e) {
			System.err.println("Hiba a lekérdezés közben [findKezlések] " + e);
			e.printStackTrace();
		}
		return resultList;

	}

	public String ujkezeles(String k, String email) {
		String query = "UPDATE beteg SET kezelés = ? WHERE email = ?";
		PreparedStatement stmt;
		try {
			stmt = conn.prepareStatement(query);

			stmt.setString(1, k);
			stmt.setString(2, email);
			stmt.executeUpdate();
		} catch (SQLException e) {
			System.err.println("Hiba a hozzáadás közben [ujkezelés] " + e);
			e.printStackTrace();
		}
		return null;

	}

	public void betegToRokon(int rokonId, int betegId) {
		// TODO VALIDÁTOROK

		String query = "insert into " + "beteg_rokon (rokon_id, beteg_id)" + "values(?,?)";
		PreparedStatement pstmt;

		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setInt(1, rokonId);
			pstmt.setInt(2, betegId);
			pstmt.execute();
		} catch (SQLException e) {
			System.err.println("Hiba a hozzáadás közben [betegtoRokon] " + e);
			e.printStackTrace();
		}

	}

	public Integer insertDoktor(Doktor doktor) {
		String query = "INSERT INTO doktor (SzemIgSzám,név,szakképesítés,mûthet) " + "VALUES (?,?,?,?)";

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
			System.err.println("Hiba a hozzáadás közben [insertDoktor] " + e);
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
			System.out.println("Hiba a kapcsolat lezárásában " + ex);

		}
	}

}
