package buisnesslogic;

import java.util.List;

import Entities.Doktor;
import Entities.Kezelesek;
import Entities.Rokon;
import Repository.Repository;
import interfaces.HospApp;

public class HospAppImpl implements HospApp {

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
	
	
	
}
