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
import Entities.Helyisegek;
import Entities.MunkaKapcsolat;
import Entities.Doktor;
import Entities.Kezelesek;
import Entities.Rokon;

public class Repository {
	
	private final String DB_URL = "jdbc:mysql://localhost:3306/klinika";
	private final String DB_PASS = "";
	private final String DB_USER = "root";
	
	private Connection conn;
	public Repository() {
		initDatabase();
	}
	
	private void initDatabase() {

		try {
			conn = DriverManager.getConnection(DB_URL, DB_USER, DB_PASS);
		} catch (SQLException e) {
			System.err.println("Hiba az adatbázis kapcsolat létrehozásában" +e);
		}
	
	}
	
	public void closeConnection() {
		try {
		if(conn != null) {
			conn.close();
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public boolean validator(String tableName, String columnName, int id) {
		String query = "SELECT * FROM " + tableName + 
					   " WHERE " + columnName + " = " + id;
		try {
			Statement stmt = conn.createStatement();
			ResultSet rs = stmt.executeQuery(query);
			return rs.next();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return false;
	}
	

	
	public List<Beteg> findBeteg(){
		
		String query = "SELECT * FROM beteg";
		List<Beteg> bList = new ArrayList<>();
		
		try {
			Statement stmt = conn.createStatement();
			ResultSet rs = stmt.executeQuery(query);
			while (rs.next()) {
				Beteg beteg = new Beteg();
				beteg.setBetegId(rs.getInt("beteg_id"));
				beteg.setNev(rs.getString("név"));
				beteg.setCim(rs.getString("cím"));
				beteg.setTelefonszam(rs.getString("telefonszám"));
				beteg.setEmail(rs.getString("email"));
				beteg.setKezeles(rs.getString("kezelés"));
				
				bList.add(beteg);
			}
		} catch (SQLException e) {
			System.err.println("Hiba a lekérdezés közben [findBeteg] " +e);
		}				
		
		return bList;
	}
	
	public List<Helyisegek> findHelyisegek(){
		
		String query = "SELECT * FROM helyiségek";
		List<Helyisegek> hList = new ArrayList<>();
		
		try {
			Statement stmt = conn.createStatement();
			ResultSet rs = stmt.executeQuery(query);
			while (rs.next()) {
				Helyisegek szoba = new Helyisegek();
				szoba.setBlokacioId(rs.getInt("blockáció_id"));
				szoba.setSzobaszam(rs.getInt("szobaszám"));
				szoba.setMuto(rs.getBoolean("mûtõ"));

				
				hList.add(szoba);
			}
		} catch (SQLException e) {
			System.err.println("Hiba a lekérdezés közben [findHelyisegek] " +e);
			e.printStackTrace();
		}				
		
		return hList;	
	}
	
	
	public Integer insertBeteg(Beteg b) {
		
		String query = "INSERT INTO beteg(név,cím,telefonszám,email,kezelés)"
					   +" VALUES(?,?,?,?,?)";
		
		PreparedStatement pstmt;
		try {
			pstmt = conn.prepareStatement(query,Statement.RETURN_GENERATED_KEYS);
			
			pstmt.setString(1, b.getNev());
			pstmt.setString(2, b.getCim());
			pstmt.setString(3, b.getTelefonszam());
			pstmt.setString(4, b.getEmail());
			pstmt.setString(5, b.getKezeles());
			pstmt.execute();
			
			ResultSet rs = pstmt.getGeneratedKeys();
			rs.next();
			return rs.getInt(1);
			
		} catch (SQLException e) {
			System.err.println("Hiba a hozzáadás közben [insertBeteg] "+e );
			e.printStackTrace();
		}

	return null;	
	}
	
	private Integer bolleanToInt(boolean b) {
		
		Integer i;
		
		if (b) {
			return i = 1;
		} else if (!b) {
			return i = 0;
		}
		
		
		return null;
	}
	
	public Integer insertHelyisegek (Helyisegek h) {
	
		String query = "INSERT INTO helyiségek(szobaszám,mûtõ)"
				   +" VALUES(?,?)";
	
	PreparedStatement pstmt;
	try {
		pstmt = conn.prepareStatement(query,Statement.RETURN_GENERATED_KEYS);
		
		pstmt.setInt(1, h.getSzobaszam());
		pstmt.setInt(2, bolleanToInt(h.isMuto()));
		pstmt.execute();
		
		ResultSet rs = pstmt.getGeneratedKeys();
		rs.next();
		return rs.getInt(1);
		
	} catch (SQLException e) {
		System.err.println("Hiba a hozzáadás közben [insertBeteg] "+e );
		e.printStackTrace();
	}
	
	return null;
	}
	

	
	public Integer universalId(String idname, String tableName,String where,String who) {
		

		String query = "SELECT "+idname 
				+" FROM "+tableName +" WHERE " 
				+ where +" = '" +who +"'";
	
		
		
		try {
			Statement stmt = conn.createStatement();
			ResultSet rs = stmt.executeQuery(query);
			while (rs.next()) {
				Integer id = rs.getInt(idname);
			return id;
			}
			
			
		} catch (SQLException e) {
			System.err.println("Hiba a lekérdezés közben [uniID] " +e);
			e.printStackTrace();
		}
		

		return null;
	}
	
	
	public void betegToDoki(String szemIgSzam,String email) {
			

		Integer dId = universalId("dr_id","doktor","SzemIgSzám", szemIgSzam);
		Integer bId = universalId("beteg_id","beteg","email", email);
		
		String query = "INSERT INTO dr_beteg(dr_id,beteg_id)"
					   + " VALUES(?,?)";
						
		PreparedStatement pstmt;
		try {
			pstmt = conn.prepareStatement(query,Statement.RETURN_GENERATED_KEYS);
			pstmt.setInt(1,	dId);
			pstmt.setInt(2, bId);
			pstmt.execute();
		} catch (SQLException e) {
			System.err.println("Hiba a hozzáadás közben [betegToDoki] "+e );
			e.printStackTrace();
		}
	}
	
	public List<MunkaKapcsolat> validLista(){
		
		List<MunkaKapcsolat> time = new ArrayList<>();
		
		String query = "SELECT * FROM munka_kapcsolat";
		
		try {
			Statement stmt = conn.createStatement();
			ResultSet rs = stmt.executeQuery(query);
			while (rs.next()) {
				MunkaKapcsolat mk = new MunkaKapcsolat();
				mk.setId(rs.getInt("id"));
				mk.setDrBetegId(rs.getInt("dr_beteg_id"));
				mk.setSzobaszam(rs.getInt("szobaszám"));
				mk.setMunkaKezdes(rs.getTimestamp("munkakezdés"));
				mk.setMunkaVege(rs.getTimestamp("munkavége"));
				time.add(mk);
			}
		} catch (SQLException e) {
			System.err.println("Hiba a lekérdezés közben [findHelyisegek] " +e);
			e.printStackTrace();
		}				
		return time;
	}
		
	private Integer dr_beteg(String szemIgSzam,String email ) {
		
		Integer dId = universalId("dr_id", "doktor", "SzemIgSzám", szemIgSzam);
		Integer bId = universalId("beteg_id","beteg","email", email);
		
		String query = "SELECT id(*) FROM `dr_beteg` WHERE dr_id = " +dId +" AND beteg_id = "+bId;
		Integer id;
		try {
			Statement stmt = conn.createStatement();
			ResultSet rs = stmt.executeQuery(query);
			while (rs.next()) {
				id = rs.getInt("id");
				return id;
			}
		} catch (SQLException e) {
			System.err.println("Hiba a lekérdezés közben [findHelyisegek] " +e);
			e.printStackTrace();
		}				
		return null;
	}
	
	public void munkaKapcsolat(String szemIgSzam,Integer szobaszam, 
								Timestamp mKezdes, Timestamp mVege) { 
		String dId = universalId("dr_id", "doktor", "SzemIgSzám", szemIgSzam).toString();
		Integer dBI = universalId("id","dr_beteg","dr_id", dId);
		Integer bId = szobaszam;

		
		String query = "INSERT INTO munka_kapcsolat(dr_beteg_id,szobaszám,munkakezdés,munkavége)"
					   + " VALUES(?,?,?,?)";
						
		PreparedStatement pstmt;
		try {
			pstmt = conn.prepareStatement(query,Statement.RETURN_GENERATED_KEYS);
			pstmt.setInt(1,	dBI);
			pstmt.setInt(2, bId);
			pstmt.setTimestamp(3, mKezdes);
			pstmt.setTimestamp(4, mVege);
			pstmt.execute();
		} catch (SQLException e) {
			System.err.println("Hiba a hozzáadás közben [munkaKapcsolat] "+e );
			e.printStackTrace();
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

}
