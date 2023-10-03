package Ingredient;

import java.io.Serializable;
import java.util.Formatter;

public class Ingredient implements Serializable {

	private static final long serialVersionUID = 1L;
	private String name;
	private String brand;
	private String type;
	private String secType;
	private double abv = 0;
	private int id;
	private static int counter = 0;
	private boolean available = true;
	private boolean alcoholic = true;

	public Ingredient(String name, String type, String secType, double abv, boolean available) {
		this.name = name;
		this.type = type;
		this.secType = secType;
		this.abv = abv;
		this.available = available;
		this.id=counter;
		counter++;
	}

	public Ingredient() {
	}

	public String getName() {
		return this.name;
	}

	public String getType() {
		return this.type;
	}

	public String getAvailableString() {
		if (available) {
			return "Available";
		} else {
			return "Not Available";
		}
	}

	public boolean getAvailable() {
		return this.available;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setType(String type) {
		this.type = type;
	}

	public void setAvailable(boolean available) {
		this.available = available;
	}

	@Override
	public boolean equals(final Object other) {

		if (other == null) {
			return false;
		}
		if (getClass() != other.getClass()) {
			return false;
		}

		Ingredient comp = (Ingredient) other;

		if (this.getName().equals(comp.getName())) {
			if (this.getSecType().equals(comp.getSecType())) {
				return true;
			}
		}
		return false;
	}

	@Override
	public int hashCode() {
		return 0;
	}

	public void toStringShort() {
		@SuppressWarnings("resource")
		Formatter f = new Formatter();
		f.format("Name: %s | Rating: %s \n", getName(), getType());
		System.out.println(f.toString());
	}

	public void toogleAvailable() {
		available = !available;
	}

	public double getAbv() {
		return abv;
	}

	public String getAbvString() {
		return Double.toString(abv);
	}

	public void setAbv(double abv) {
		this.abv = abv;
	}

	public boolean getAlcoholic() {
		return alcoholic;
	}

	public void setAlcoholic(boolean alcoholic) {
		this.alcoholic = alcoholic;
	}

	public String getSecType() {
		return secType;
	}

	public void setSecType(String secType) {
		this.secType = secType;
	}

	public String getBrand() {
		return brand;
	}

	public void setBrand(String brand) {
		this.brand = brand;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}
	
	public int getCounter() {
		return counter;
	}

	public void setCounter(int counter) {
		Ingredient.counter = counter;
	}

}
