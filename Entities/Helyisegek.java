package Entities;

import java.sql.Date;
import java.time.LocalDateTime;


public class Helyisegek {

	private Integer blokacioId;
	private Integer szobaszam;
	private boolean muto;
	private LocalDateTime munkakezdes;
	private LocalDateTime munkavege;
	
	public Helyisegek() {
	}
	
	public Helyisegek( Integer szobaszam, boolean muto, LocalDateTime munkakezdes,
			LocalDateTime munkavege) {
		this.blokacioId = blokacioId;
		this.szobaszam = szobaszam;
		this.muto = muto;
		this.munkakezdes = munkakezdes;
		this.munkavege = munkavege;
	}

	public Integer getBlokacioId() {
		return blokacioId;
	}

	public void setBlokacioId(Integer blokacioId) {
		this.blokacioId = blokacioId;
	}

	public Integer getSzobaszam() {
		return szobaszam;
	}

	public void setSzobaszam(Integer szobaszam) {
		this.szobaszam = szobaszam;
	}

	public boolean isMuto() {
		return muto;
	}

	public void setMuto(boolean muto) {
		this.muto = muto;
	}

	public LocalDateTime getMunkakezdes() {
		return munkakezdes;
	}

	public void setMunkakezdes(LocalDateTime munkakezdes) {
		this.munkakezdes = munkakezdes;
	}

	public LocalDateTime getMunkavege() {
		return munkavege;
	}

	public void setMunkavege(LocalDateTime munkavege) {
		this.munkavege = munkavege;
	}

	@Override
	public String toString() {
		return "Helyisegek [blokacioId=" + blokacioId + ", szobaszam=" + szobaszam + ", muto=" + muto + ", munkakezdes="
				+ munkakezdes + ", munkavege=" + munkavege + "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((blokacioId == null) ? 0 : blokacioId.hashCode());
		result = prime * result + ((munkakezdes == null) ? 0 : munkakezdes.hashCode());
		result = prime * result + ((munkavege == null) ? 0 : munkavege.hashCode());
		result = prime * result + (muto ? 1231 : 1237);
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
		Helyisegek other = (Helyisegek) obj;
		if (blokacioId == null) {
			if (other.blokacioId != null)
				return false;
		} else if (!blokacioId.equals(other.blokacioId))
			return false;
		if (munkakezdes == null) {
			if (other.munkakezdes != null)
				return false;
		} else if (!munkakezdes.equals(other.munkakezdes))
			return false;
		if (munkavege == null) {
			if (other.munkavege != null)
				return false;
		} else if (!munkavege.equals(other.munkavege))
			return false;
		if (muto != other.muto)
			return false;
		if (szobaszam == null) {
			if (other.szobaszam != null)
				return false;
		} else if (!szobaszam.equals(other.szobaszam))
			return false;
		return true;
	}
	
	
	
}
