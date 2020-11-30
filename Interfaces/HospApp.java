package Interfaces;

import java.sql.Timestamp;

import Entities.Beteg;
import Entities.Helyisegek;

public interface HospApp {
	
	void betegekList�z�sa();
	
	void helyis�gekList�z�sa();
	
//	void betegRokonai(int betegId);
	
	void betegFelv�tele(Beteg b);
	
	void helyis�gFelv�tele(Helyisegek h);
	
	void betegFelv�teleOrvoshoz(String szemIgSzam,String email);
	
	void �jBeoszt�s(String szemIgSzam, Integer szobaszam, Timestamp mKezdes, Timestamp mVege);

}
