package Interfaces;

import java.util.List;

import Entities.Doktor;
import Entities.Rokon;

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

	
	void doktorokListazasa();

	void ujDokiHozzaadasa(Doktor doktor);
	
	void kezelesekListazasa();

    void rokonokListazasa();
    
    void ujKezelesHozzaadasaMeglevoBeteghez(String k, String email);
     
    void beteghezArokonokHozzaadasa( Integer rokonId ,Integer betegId);
    
    void kezelesLemondasa(String table, String column, int id);


	
}
