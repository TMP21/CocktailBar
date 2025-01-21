package Cocktail;

import Ingredient.Ingredient;
import Ingredient.Ingredients;
import util.Content;

import java.io.Serial;
import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.stream.Collectors;

public class Cocktail implements Serializable {

	@Serial
	private static final long serialVersionUID = 1L;
	private List<Content> content = new LinkedList<Content>();
	private List<String> recipe = new LinkedList<String>();
	private String name;
	private String comment;
	private Double rating;
	private Double abv = 0.0;
	private String glassWare;
	private String method;
	private Date addDate;
	private int id = 0;
	private static int counter = 0;
	private boolean available;
	private boolean alcoholic;
	SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");

	public Cocktail(String name, Double rating, List<Content> content, String comment) {
		this.name = name;
		this.comment = comment;
		this.content = content;
		this.rating = rating;
		this.available = testAvailable();
		this.id = counter;
		counter++;
		this.addDate = new Date();
	}

	public Cocktail(String name, Double rating, String comment) {
		this.name = name;
		this.rating = rating;
		this.comment = comment;
		this.id = counter;
		counter++;
		this.addDate = new Date();
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getName() {
		return name;
	}

	public Double getRating() {
		return this.rating;
	}

	public void setRating(double rating) {
		this.rating = rating;
	}

	public String getComment() {
		return comment;
	}

	public void setComment(String comment) {
		this.comment = comment;
	}

	public boolean testAvailable() {
		return content.stream().allMatch(x -> Ingredients.getIngredientFromId(x.getIngrId()).getAvailable());
	}

	public boolean getAvailable() {
		return available;
	}

	public void setAvailable(boolean b) {
		available = b;
	}

	public List<Content> getContent() {
		return content;
	}

	public Content getContent(int i) {
		return content.get(i);
	}

	public List<Ingredient> getAllIngr() {
		List<Ingredient> ingr = new LinkedList<Ingredient>();
		for (Content c : content) {
			ingr.add(Ingredients.getIngredientFromId(c.getIngrId()));
		}
		return ingr;
	}

	public boolean getAlcoholic() {
		return alcoholic;
	}

	public void setAlcoholic(boolean alcoholic) {
		this.alcoholic = alcoholic;
	}

	public boolean addContent(Content c) {
		return content.add(c);
	}

	public boolean removeContent(Content c) {
		return content.remove(c);
	}

	@Override
	public boolean equals(final Object other) {

		if (other == null) {
			return false;
		}
		if (getClass() != other.getClass()) {
			return false;
		}

		Cocktail comp = (Cocktail) other;

		int ratingEqu = this.getRating().compareTo(comp.getRating());

		if (this.getName().equals(comp.getName()) && (ratingEqu == 0)) {
			if (testCont(this.getContent(), comp.getContent())) {
				return true;
			}
		}
		return false;
	}

	public boolean testCont(List<Content> ori, List<Content> comp) {
		int a = 0;
		for (Content c : ori) {
			if (!c.equals(comp.get(a))) {
				return false;
			}
			a++;
		}
		return true;
	}

	@Override
	public int hashCode() {
		return 1;
	}

	public String getContentString() {
		String contents = "";
		for (Content c : content) {
			contents = contents + Ingredients.getIngredientFromId(c.getIngrId()).getName() + "\n";
		}
		return contents;
	}

	public String getContentStringWithColour() {
		String contents = "";
		for (Content c : content) {
			if (Ingredients.getIngredientFromId(c.getIngrId()).getAvailable()) {
				contents = contents + Ingredients.getIngredientFromId(c.getIngrId()).getName() + "\n";
			} else {
				contents = contents + Ingredients.getIngredientFromId(c.getIngrId()).getName() + "\n";
			}
		}
		return contents;
	}

	public String toString() {
		@SuppressWarnings("resource")
		Formatter f = new Formatter();
		f.format("Name: %s | Rating: %s \nContents: \n%s\n", getName(), getRating(), getContentString());
		return f.toString();
	}

	public String toStringShort() {
		@SuppressWarnings("resource")
		Formatter f = new Formatter();
		f.format("%s | Rating: %s \n", getName(), getRating());
		return f.toString();
	}

	public boolean updateContent(Content old, Content upd) {
		for (Content c : content) {
			if (c.equals(old)) {
				content.remove(old);
				content.add(upd);
				return true;
			}
		}
		return false;
	}

	public String printContents() {
		String print = toStringShort() + "\n";

		for (Content c : content) {
			print = print + Ingredients.getIngredientFromId(c.getIngrId()).getName() + " : " + c.getAmnt() + " "
					+ c.getUnit() + "\n";
		}

		return print;
	}

	public String printRecipe() {

		String print = "";
		int c = 1;
		int cutPoint = 50;

		for (String s : recipe) {
			print = print + c + ": ";
			for (int i = 0; i < s.length(); i = i + cutPoint) {
				if (s.length() > i + cutPoint) {
					if (i == 0) {
						print = print + s.substring(i, i + cutPoint) + "\n";
					} else {
						print = print + "   " + s.substring(i, i + cutPoint) + "\n";
					}
				} else {
					if (i == 0) {
						print = print + s.substring(i, s.length()) + "\n";
					} else {
						print = print + "   " + s.substring(i, s.length()) + "\n";
					}
				}
			}
			c++;
		}

		return print;
	}

	public void upd() {
		try {
			content = content.stream()
					.sorted((x, y) -> Double.compare(Ingredients.getIngredientFromId(x.getIngrId()).getAbv(),
							Ingredients.getIngredientFromId(y.getIngrId()).getAbv()))
					.collect(Collectors.toList());
		} catch (Exception e) {
			System.out.println(name);
		}
		Collections.reverse(content);
		abv = calcAbv();
		available = testAvailable();
	}

	public Double getAbv() {
		return abv;
	}

	public void setAbv(Double abv) {
		this.abv = abv;
	}

	public Double calcAbv() {
		double tempAmountAlc = 0.0;
		double tempAmountTotal = 0.0;
		for (Content c : content) {
			if (Ingredients.getIngredientFromId(c.getIngrId()).getAlcoholic()) {
				tempAmountAlc = tempAmountAlc + c.getAmnt() * (Ingredients.getIngredientFromId(c.getIngrId()).getAbv());
				tempAmountTotal = tempAmountTotal + c.getAmnt();
			} else {
				tempAmountTotal = tempAmountTotal + c.getAmnt();
			}
		}
		return tempAmountAlc / (tempAmountTotal);
	}

	public String getGlassWare() {
		return glassWare;
	}

	public void setGlassWare(String glassWare) {
		this.glassWare = glassWare;
	}

	public String getMethod() {
		return method;
	}

	public void setMethod(String method) {
		this.method = method;
	}

	public void addRecipeStep(String rs) {
		recipe.add(rs);
	}

	public void updateRecipeStep(String o, String n) {
		recipe.set(recipe.indexOf(o), n);
	}

	public List<String> getRecipe() {
		return recipe;
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
		Cocktail.counter = counter;
	}

	public Date getAddDate1() {
		return addDate;
	}

	public String getAddDate2() {
		return formatter.format(addDate);
	}

	public void setAddDate(Date addDate) {
		this.addDate = addDate;
	}

}
