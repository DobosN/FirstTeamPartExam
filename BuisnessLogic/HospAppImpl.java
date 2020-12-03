package Buisnesslogic;

import java.sql.Timestamp;
import java.util.List;

import Entities.Beteg;
import Entities.Helyisegek;
import Entities.Rokon;
import Interfaces.HospApp;
import Repository.Repository;

public class HospAppImpl implements HospApp{

	@Override
	public void betegekListázása() {
	Repository repo = new Repository();
		List<Beteg> b = repo.findBeteg();
		b.forEach(System.out::println);
		repo.closeConnection();
	}

	

	@Override
	public void helyiségekListázása() {
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
	public void betegFelvétele(Beteg b) {
		Repository repo = new Repository();		
		if(b != null) {
		repo.insertBeteg(b);
		}
		repo.closeConnection();
	}


	@Override
	public void helyiségFelvétele(Helyisegek h) {
		Repository repo = new Repository();
		if(h != null) {
			repo.insertHelyisegek(h);
		}
	}
	
	@Override
	public void betegFelvételeOrvoshoz(String szemIgSzam, String email) {
		Repository repo = new Repository();
		
		boolean valiDr = repo.validator("doktor", "dr_id",repo.universalId("dr_id", "doktor", "SzemIgSzám", szemIgSzam));
		boolean validBeteg= repo.validator("beteg", "beteg_id", repo.universalId("beteg_id", "beteg", "email", email));
//		boolean valiDr = repo.validator("doktor", "dr_id", repo.dokiId(szemIgSzam));
//		boolean validBeteg= repo.validator("beteg", "beteg_id", repo.betegId(email));
		if(valiDr && validBeteg) {
			repo.betegToDoki(szemIgSzam, email);
		}else {
			System.out.println("Hiba a hozzáadás közben!");
		}
	}



	@Override
	public void újBeosztás(String szemIgSzam, Integer szobaszam, Timestamp mKezdes, Timestamp mVege) {
		
		
import java.util.List;

import Entities.Doktor;
import Entities.Kezelesek;
import Entities.Rokon;
import Repository.Repository;
import interfaces.HospApp;



	@Override
	public void doktorokListazasa() {
		Repository repo = new Repository();
		

			List<Doktor> resuDoktors = repo.findDoktor();
			resuDoktors.forEach(System.out::println);
		
		repo.closeConnection();
	}

	@Override
	public void ujDokiHozzaadasa(Doktor doktor) {
		Repository repo = new Repository();
		if (repo != null) {
			repo.insertDoktor(doktor);

		}
		repo.closeConnection();
	}

	@Override
	public void kezelesekListazasa() {
		Repository repo = new Repository();
		List<Kezelesek> resultKezelesek = repo.findKezelesek();
		resultKezelesek.forEach(p -> System.out.println(p.getKezelesek()));

		repo.closeConnection();
	}

	@Override
	public void rokonokListazasa() {
		Repository repo = new Repository();
		

			List<Rokon> findRokon = repo.findRokon();
			findRokon.forEach(p -> System.out.println(p));
		
		repo.closeConnection();
	}

	@Override
	public void ujKezelesHozzaadasaMeglevoBeteghez(String k, String email) {
		Repository repo = new Repository();
		if (repo != null) {

			repo.ujkezeles(k, email);
		}
		repo.closeConnection();

	}

	@Override
	public void beteghezArokonokHozzaadasa(Integer rokonId ,Integer betegId) {
		Repository repo = new Repository();
		if (repo != null) {
      repo.betegToRokon(rokonId, betegId);
		
	}
		repo.closeConnection();
	
	}

	@Override
	public void kezelesLemondasa(String table, String column, int id) {
		Repository repo = new Repository();
		if (repo != null) {
			repo.deleteRecord(table, column, id);
		}
		repo.closeConnection();
	}
	
