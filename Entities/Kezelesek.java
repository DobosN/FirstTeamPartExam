package Entities;

public class Kezelesek {
	private int kId;
	private String kezelesek;
	private boolean muthet;

	public int getkId() {
		return kId;
	}

	public void setkId(int kId) {
		this.kId = kId;
	}

	public String getKezelesek() {
		return kezelesek;
	}

	public void setKezelesek(String kezelesek) {
		this.kezelesek = kezelesek;
	}

	public boolean isMuthet() {
		return muthet;
	}

	public void setMuthet(boolean muthet) {
		this.muthet = muthet;
	}

	public Kezelesek() {

	}

	public Kezelesek(String kezelesek, boolean muthet) {
		super();
		this.kezelesek = kezelesek;
		this.muthet = muthet;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + kId;
		result = prime * result + ((kezelesek == null) ? 0 : kezelesek.hashCode());
		result = prime * result + (muthet ? 1231 : 1237);
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
		Kezelesek other = (Kezelesek) obj;
		if (kId != other.kId)
			return false;
		if (kezelesek == null) {
			if (other.kezelesek != null)
				return false;
		} else if (!kezelesek.equals(other.kezelesek))
			return false;
		if (muthet != other.muthet)
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Kezelesek [kId=" + kId + ", kezelesek=" + kezelesek + ", muthet=" + muthet + "]";
	}

}
