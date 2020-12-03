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
			System.err.println("Hiba az adatb�zis kapcsolat l�trehoz�s�ban" +e);
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
				beteg.setNev(rs.getString("n�v"));
				beteg.setCim(rs.getString("c�m"));
				beteg.setTelefonszam(rs.getString("telefonsz�m"));
				beteg.setEmail(rs.getString("email"));
				beteg.setKezeles(rs.getString("kezel�s"));
				
				bList.add(beteg);
			}
		} catch (SQLException e) {
			System.err.println("Hiba a lek�rdez�s k�zben [findBeteg] " +e);
		}				
		
		return bList;
	}
	
	public List<Helyisegek> findHelyisegek(){
		
		String query = "SELECT * FROM helyis�gek";
		List<Helyisegek> hList = new ArrayList<>();
		
		try {
			Statement stmt = conn.createStatement();
			ResultSet rs = stmt.executeQuery(query);
			while (rs.next()) {
				Helyisegek szoba = new Helyisegek();
				szoba.setBlokacioId(rs.getInt("block�ci�_id"));
				szoba.setSzobaszam(rs.getInt("szobasz�m"));
				szoba.setMuto(rs.getBoolean("m�t�"));

				
				hList.add(szoba);
			}
		} catch (SQLException e) {
			System.err.println("Hiba a lek�rdez�s k�zben [findHelyisegek] " +e);
			e.printStackTrace();
		}				
		
		return hList;	
	}
	
	
	public Integer insertBeteg(Beteg b) {
		
		String query = "INSERT INTO beteg(n�v,c�m,telefonsz�m,email,kezel�s)"
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
			System.err.println("Hiba a hozz�ad�s k�zben [insertBeteg] "+e );
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
	
		String query = "INSERT INTO helyis�gek(szobasz�m,m�t�)"
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
		System.err.println("Hiba a hozz�ad�s k�zben [insertBeteg] "+e );
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
			System.err.println("Hiba a lek�rdez�s k�zben [uniID] " +e);
			e.printStackTrace();
		}
		

		return null;
	}
	
	
	public void betegToDoki(String szemIgSzam,String email) {
			

		Integer dId = universalId("dr_id","doktor","SzemIgSz�m", szemIgSzam);
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
			System.err.println("Hiba a hozz�ad�s k�zben [betegToDoki] "+e );
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
				mk.setSzobaszam(rs.getInt("szobasz�m"));
				mk.setMunkaKezdes(rs.getTimestamp("munkakezd�s"));
				mk.setMunkaVege(rs.getTimestamp("munkav�ge"));
				time.add(mk);
			}
		} catch (SQLException e) {
			System.err.println("Hiba a lek�rdez�s k�zben [findHelyisegek] " +e);
			e.printStackTrace();
		}				
		return time;
	}
		
	private Integer dr_beteg(String szemIgSzam,String email ) {
		
		Integer dId = universalId("dr_id", "doktor", "SzemIgSz�m", szemIgSzam);
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
			System.err.println("Hiba a lek�rdez�s k�zben [findHelyisegek] " +e);
			e.printStackTrace();
		}				
		return null;
	}
	
	public void munkaKapcsolat(String szemIgSzam,Integer szobaszam, 
								Timestamp mKezdes, Timestamp mVege) { 
		String dId = universalId("dr_id", "doktor", "SzemIgSz�m", szemIgSzam).toString();
		Integer dBI = universalId("id","dr_beteg","dr_id", dId);
		Integer bId = szobaszam;

		
		String query = "INSERT INTO munka_kapcsolat(dr_beteg_id,szobasz�m,munkakezd�s,munkav�ge)"
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
			System.err.println("Hiba a hozz�ad�s k�zben [munkaKapcsolat] "+e );
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

}
