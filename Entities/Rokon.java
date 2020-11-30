package Entities;

public class Rokon {

	private Integer rokonId;
	private String nev;
	private String cim;
	private String telefonszam;
	
	public Rokon() {
	
	}
	
	public Rokon(String nev, String cim, String telefonszam) {
		
		this.nev = nev;
		this.cim = cim;
		this.telefonszam = telefonszam;
	}

	public Integer getRokonId() {
		return rokonId;
	}

	public void setRokonId(Integer rokonId) {
		this.rokonId = rokonId;
	}

	public String getNev() {
		return nev;
	}

	public void setNev(String nev) {
		this.nev = nev;
	}

	public String getCim() {
		return cim;
	}

	public void setCim(String cim) {
		this.cim = cim;
	}

	public String getTelefonszam() {
		return telefonszam;
	}

	public void setTelefonszam(String telefonszam) {
		this.telefonszam = telefonszam;
	}

	@Override
	public String toString() {
		return "Rokon [rokonId=" + rokonId + ", nev=" + nev + ", cim=" + cim + ", telefonszam=" + telefonszam + "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((cim == null) ? 0 : cim.hashCode());
		result = prime * result + ((nev == null) ? 0 : nev.hashCode());
		result = prime * result + ((rokonId == null) ? 0 : rokonId.hashCode());
		result = prime * result + ((telefonszam == null) ? 0 : telefonszam.hashCode());
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
		Rokon other = (Rokon) obj;
		if (cim == null) {
			if (other.cim != null)
				return false;
		} else if (!cim.equals(other.cim))
			return false;
		if (nev == null) {
			if (other.nev != null)
				return false;
		} else if (!nev.equals(other.nev))
			return false;
		if (rokonId == null) {
			if (other.rokonId != null)
				return false;
		} else if (!rokonId.equals(other.rokonId))
			return false;
		if (telefonszam == null) {
			if (other.telefonszam != null)
				return false;
		} else if (!telefonszam.equals(other.telefonszam))
			return false;
		return true;
	}
	
	
	
}
