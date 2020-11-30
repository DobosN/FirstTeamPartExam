import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.List;

import Entities.Beteg;
import Entities.Doktor;
import Entities.Kezelesek;
import Entities.Rokon;
import Repository.Repository;
import buisnesslogic.HospAppImpl;
import interfaces.HospApp;

public class Main {

	public static void main(String[] args) {

		HospApp hospApp = new HospAppImpl();
		hospApp.doktorokListazasa();
	//	Doktor doktor = new Doktor("3212321AA", "Béla", "fogász", true);
	//	hospApp.ujDokiHozzaadasa(doktor);

		hospApp.kezelesekListazasa();
		hospApp.rokonokListazasa();
		hospApp.ujKezelesHozzaadasaMeglevoBeteghez("Ortopédia", "béla@gmail.com");
		hospApp.beteghezArokonokHozzaadasa(2, 1);
		hospApp.kezelesLemondasa("munka_kapcsolat", "id", 1);
	}
}
