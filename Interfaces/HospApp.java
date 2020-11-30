package Interfaces;

import java.sql.Timestamp;

import Entities.Beteg;
import Entities.Helyisegek;

public interface HospApp {
	
	void betegekListázása();
	
	void helyiségekListázása();
	
//	void betegRokonai(int betegId);
	
	void betegFelvétele(Beteg b);
	
	void helyiségFelvétele(Helyisegek h);
	
	void betegFelvételeOrvoshoz(String szemIgSzam,String email);
	
	void újBeosztás(String szemIgSzam, Integer szobaszam, Timestamp mKezdes, Timestamp mVege);

}
