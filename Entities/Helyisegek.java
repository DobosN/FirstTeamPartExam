package Entities;

import java.sql.Date;
import java.sql.Timestamp;
import java.time.LocalDateTime;


public class Helyisegek {

	private Integer blokacioId;
	private Integer szobaszam;
	private boolean muto;

	
	public Helyisegek() {
	}
	
	public Helyisegek( Integer szobaszam, boolean muto) {
		
		this.szobaszam = szobaszam;
		this.muto = muto;
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


	@Override
	public String toString() {
		return "Helyisegek [blokacioId=" + blokacioId + ", szobaszam=" + szobaszam + ", muto=" + muto;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((blokacioId == null) ? 0 : blokacioId.hashCode());
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
