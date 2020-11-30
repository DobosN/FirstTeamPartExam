package Entities;

import java.sql.Timestamp;

public class MunkaKapcsolat {

	private Integer id;
	private Integer drBetegId;
	private Integer szobaszam;
	private Timestamp munkaKezdes;
	private Timestamp munkaVege;
	

	public MunkaKapcsolat() {

	}
	
	public MunkaKapcsolat(Integer drBetegId, Integer szobaszam, Timestamp munkaKezdes,
			Timestamp munkaVege) {

		this.drBetegId = drBetegId;
		this.szobaszam = szobaszam;
		this.munkaKezdes = munkaKezdes;
		this.munkaVege = munkaVege;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getDrBetegId() {
		return drBetegId;
	}

	public void setDrBetegId(Integer drBetegId) {
		this.drBetegId = drBetegId;
	}

	public Integer getSzobaszam() {
		return szobaszam;
	}

	public void setSzobaszam(Integer szobaszam) {
		this.szobaszam = szobaszam;
	}

	public Timestamp getMunkaKezdes() {
		return munkaKezdes;
	}

	public void setMunkaKezdes(Timestamp munkaKezdes) {
		this.munkaKezdes = munkaKezdes;
	}

	public Timestamp getMunkaVege() {
		return munkaVege;
	}

	public void setMunkaVege(Timestamp munkaVege) {
		this.munkaVege = munkaVege;
	}

	@Override
	public String toString() {
		return "MunkaKapcsolat [id=" + id + ", drBetegId=" + drBetegId + ", szobaszam=" + szobaszam + ", munkaKezdes="
				+ munkaKezdes + ", munkaVege=" + munkaVege + "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((drBetegId == null) ? 0 : drBetegId.hashCode());
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((munkaKezdes == null) ? 0 : munkaKezdes.hashCode());
		result = prime * result + ((munkaVege == null) ? 0 : munkaVege.hashCode());
		result = prime * result + ((szobaszam == null) ? 0 : szobaszam.hashCode());
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
		MunkaKapcsolat other = (MunkaKapcsolat) obj;
		if (drBetegId == null) {
			if (other.drBetegId != null)
				return false;
		} else if (!drBetegId.equals(other.drBetegId))
			return false;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (munkaKezdes == null) {
			if (other.munkaKezdes != null)
				return false;
		} else if (!munkaKezdes.equals(other.munkaKezdes))
			return false;
		if (munkaVege == null) {
			if (other.munkaVege != null)
				return false;
		} else if (!munkaVege.equals(other.munkaVege))
			return false;
		if (szobaszam == null) {
			if (other.szobaszam != null)
				return false;
		} else if (!szobaszam.equals(other.szobaszam))
			return false;
		return true;
	}
	
	
	
	
	
}
