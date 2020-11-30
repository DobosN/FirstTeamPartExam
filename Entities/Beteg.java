package Entities;

public class Beteg {

	private Integer betegId;
	private String nev;
	private String cim;
	private String telefonszam;
	private String email;
	private String kezeles;
	
	public Beteg() {
		
	}
	
	public Beteg(String nev, String cim, String telefonszam, String email, String kezeles) {
		
		this.nev = nev;
		this.cim = cim;
		this.telefonszam = telefonszam;
		this.email = email;
		this.kezeles = kezeles;
	}
	public Integer getBetegId() {
		return betegId;
	}
	public void setBetegId(Integer betegId) {
		this.betegId = betegId;
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
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getKezeles() {
		return kezeles;
	}
	public void setKezeles(String kezeles) {
		this.kezeles = kezeles;
	}

	@Override
	public String toString() {
		return "Beteg [betegId=" + betegId + ", nev=" + nev + ", cim=" + cim + ", telefonszam=" + telefonszam
				+ ", email=" + email + ", kezeles=" + kezeles + "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((betegId == null) ? 0 : betegId.hashCode());
		result = prime * result + ((cim == null) ? 0 : cim.hashCode());
		result = prime * result + ((email == null) ? 0 : email.hashCode());
		result = prime * result + ((kezeles == null) ? 0 : kezeles.hashCode());
		result = prime * result + ((nev == null) ? 0 : nev.hashCode());
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
		Beteg other = (Beteg) obj;
		if (betegId == null) {
			if (other.betegId != null)
				return false;
		} else if (!betegId.equals(other.betegId))
			return false;
		if (cim == null) {
			if (other.cim != null)
				return false;
		} else if (!cim.equals(other.cim))
			return false;
		if (email == null) {
			if (other.email != null)
				return false;
		} else if (!email.equals(other.email))
			return false;
		if (kezeles == null) {
			if (other.kezeles != null)
				return false;
		} else if (!kezeles.equals(other.kezeles))
			return false;
		if (nev == null) {
			if (other.nev != null)
				return false;
		} else if (!nev.equals(other.nev))
			return false;
		if (telefonszam == null) {
			if (other.telefonszam != null)
				return false;
		} else if (!telefonszam.equals(other.telefonszam))
			return false;
		return true;
	}
	
	
	
}
