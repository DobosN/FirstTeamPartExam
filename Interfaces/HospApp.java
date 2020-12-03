package Interfaces;

import java.util.List;

import Entities.Doktor;
import Entities.Rokon;

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

	
	void doktorokListazasa();

	void ujDokiHozzaadasa(Doktor doktor);
	
	void kezelesekListazasa();

    void rokonokListazasa();
    
    void ujKezelesHozzaadasaMeglevoBeteghez(String k, String email);
     
    void beteghezArokonokHozzaadasa( Integer rokonId ,Integer betegId);
    
    void kezelesLemondasa(String table, String column, int id);


	
}
