package interfaces;

import java.util.List;

import Entities.Doktor;
import Entities.Rokon;

public interface HospApp {
	
	
	void doktorokListazasa();

	void ujDokiHozzaadasa(Doktor doktor);
	
	void kezelesekListazasa();

    void rokonokListazasa();
    
    void ujKezelesHozzaadasaMeglevoBeteghez(String k, String email);
     
    void beteghezArokonokHozzaadasa( Integer rokonId ,Integer betegId);
    
    void kezelesLemondasa(String table, String column, int id);

	
}
