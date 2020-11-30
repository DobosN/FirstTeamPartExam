package BuisnessLogic;

import java.sql.Timestamp;
import java.util.List;

import Entities.Beteg;
import Entities.Helyisegek;
import Entities.Rokon;
import Interfaces.HospApp;
import Repository.Repository;

public class HospAppImpl implements HospApp{

	@Override
	public void betegekList�z�sa() {
	Repository repo = new Repository();
		List<Beteg> b = repo.findBeteg();
		b.forEach(System.out::println);
		repo.closeConnection();
	}

	

	@Override
	public void helyis�gekList�z�sa() {
		Repository repo = new Repository();
		List<Helyisegek> h = repo.findHelyisegek();
		h.forEach(System.out::println);
		repo.closeConnection();
	}

//	@Override
//	public void betegRokonai(int betegId) {
//		Repository repo = new Repository();
//		List<Rokon> r = repo.findRokon(betegId);
//		r.forEach(System.out::println);
//		repo.closeConnection();
//	}



	@Override
	public void betegFelv�tele(Beteg b) {
		Repository repo = new Repository();		
		if(b != null) {
		repo.insertBeteg(b);
		}
		repo.closeConnection();
	}


	@Override
	public void helyis�gFelv�tele(Helyisegek h) {
		Repository repo = new Repository();
		if(h != null) {
			repo.insertHelyisegek(h);
		}
	}
	
	@Override
	public void betegFelv�teleOrvoshoz(String szemIgSzam, String email) {
		Repository repo = new Repository();
		
		boolean valiDr = repo.validator("doktor", "dr_id",repo.universalId("dr_id", "doktor", "SzemIgSz�m", szemIgSzam));
		boolean validBeteg= repo.validator("beteg", "beteg_id", repo.universalId("beteg_id", "beteg", "email", email));
//		boolean valiDr = repo.validator("doktor", "dr_id", repo.dokiId(szemIgSzam));
//		boolean validBeteg= repo.validator("beteg", "beteg_id", repo.betegId(email));
		if(valiDr && validBeteg) {
			repo.betegToDoki(szemIgSzam, email);
		}else {
			System.out.println("Hiba a hozz�ad�s k�zben!");
		}
	}



	@Override
	public void �jBeoszt�s(String szemIgSzam, Integer szobaszam, Timestamp mKezdes, Timestamp mVege) {
		
		
	}
	
	
	
	
}
