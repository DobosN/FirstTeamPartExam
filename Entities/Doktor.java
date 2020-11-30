package Entities;


public class Doktor {

	private Integer drId;
	private String szemIgSzam;
	private String nev;
	private String szakkepesites;
	boolean muthet;

	public Doktor() {
	
	}
	
	public Doktor(String szemIgSzam, String nev, String szakkepesites, boolean muthet) {
		this.szemIgSzam = szemIgSzam;
		this.nev = nev;
		this.szakkepesites = szakkepesites;
		this.muthet = muthet;
	}

	public Integer getDrId() {
		return drId;
	}

	public void setDrId(Integer drId) {
		this.drId = drId;
	}

	public String getSzemIgSzam() {
		return szemIgSzam;
	}

	public void setSzemIgSzam(String szemIgSzam) {
		this.szemIgSzam = szemIgSzam;
	}

	public String getNev() {
		return nev;
	}

	public void setNev(String nev) {
		this.nev = nev;
	}

	public String getSzakkepesites() {
		return szakkepesites;
	}

	public void setSzakkepesites(String szakkepesites) {
		this.szakkepesites = szakkepesites;
	}

	public boolean isMuthet() {
		return muthet;
	}

	public void setMuthet(boolean muthet) {
		this.muthet = muthet;
	}

	@Override
	public String toString() {
		return "Doktor [drId=" + drId + ", szemIgSzam=" + szemIgSzam + ", nev=" + nev + ", szakkepesites="
				+ szakkepesites + ", muthet=" + muthet + "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((drId == null) ? 0 : drId.hashCode());
		result = prime * result + (muthet ? 1231 : 1237);
		result = prime * result + ((nev == null) ? 0 : nev.hashCode());
		result = prime * result + ((szakkepesites == null) ? 0 : szakkepesites.hashCode());
		result = prime * result + ((szemIgSzam == null) ? 0 : szemIgSzam.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Doktor other = (Doktor) obj;
		if (drId == null) {
			if (other.drId != null)
				return false;
		} else if (!drId.equals(other.drId))
			return false;
		if (muthet != other.muthet)
			return false;
		if (nev == null) {
			if (other.nev != null)
				return false;
		} else if (!nev.equals(other.nev))
			return false;
		if (szakkepesites == null) {
			if (other.szakkepesites != null)
				return false;
		} else if (!szakkepesites.equals(other.szakkepesites))
			return false;
		if (szemIgSzam == null) {
			if (other.szemIgSzam != null)
				return false;
		} else if (!szemIgSzam.equals(other.szemIgSzam))
			return false;
		return true;
	}


	
}
