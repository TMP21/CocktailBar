package util;

import java.io.Serializable;

public class Content implements Serializable {

	private static final long serialVersionUID = 1L;
	private int ingrId;
	private Double amnt;
	private String unit;

	public Content(int ingrId, Double amnt, String unit) {
		this.ingrId = ingrId;
		this.amnt = amnt;
		this.unit = unit;
	}

	public int getIngrId() {
		return ingrId;
	}

	public void setIngr(int ingrId) {
		this.ingrId = ingrId;
	}

	public boolean equals(Content comp) {
		if (this.getClass().equals(comp.getClass())) {

			int amntEqu = this.getAmnt().compareTo(comp.getAmnt());
			if ((amntEqu == 0) && this.getUnit().equalsIgnoreCase(comp.getUnit())
					&& this.getIngrId() == comp.getIngrId()) {
				return true;
			} else {
				return false;
			}
		} else {
			return false;
		}
	}

	public Double getAmnt() {
		return amnt;
	}

	public void setAmnt(Double amnt) {
		this.amnt = amnt;
	}

	public String getUnit() {
		return unit;
	}

	public void setUnit(String unit) {
		this.unit = unit;
	}
}
