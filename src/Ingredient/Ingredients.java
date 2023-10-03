package Ingredient;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Ingredients implements Serializable {

	private static final long serialVersionUID = 1L;
	private static List<Ingredient> ingredients = new LinkedList<Ingredient>();
	private static String fileName = ".\\save\\Ingredients.txt";

	public Ingredients() {
		laden();
	}

	public static boolean addIngredient(Ingredient add) {
		return ingredients.add(add);
	}

	public static Ingredient getIngredientFromId(int ingrId) {
		for (Ingredient i : ingredients) {
			if (i.getId() == ingrId) {
				return i;
			}
		}
		return null;
	}

	public static List<Ingredient> getBlocked() {
		List<Ingredient> blocked = new LinkedList<Ingredient>();
		for (Ingredient i : ingredients) {
			if (!i.getAvailable()) {
				blocked.add(i);
			}
		}
		return blocked;
	}

	public static List<Ingredient> getIngredients() {
		return ingredients;
	}

	public static void setIngredients(List<Ingredient> ingredient) {
		ingredients = ingredient;
	}

	@SuppressWarnings("resource")
	public static void speichern() {
		try {
			new ObjectOutputStream(new FileOutputStream(new File(fileName))).writeObject(ingredients);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	@SuppressWarnings({ "resource", "unchecked" })
	public static void laden() {
		try {
			ingredients = Stream.of(ingredients,
					(List<Ingredient>) new ObjectInputStream(new FileInputStream(new File(fileName))).readObject())
					.flatMap(Collection::stream).distinct().collect(Collectors.toList());
			Collections.sort(ingredients, new Comparator<Ingredient>(){
				   @Override
				   public int compare(Ingredient o1, Ingredient o2){
				        if(o1.getId() < o2.getId()){
				           return -1; 
				        }
				        if(o1.getId() > o2.getId()){
				           return 1; 
				        }
				        return 0;
				   }
				});
			ingredients.get(ingredients.size()-1).setCounter(ingredients.get(ingredients.size() - 1).getId() + 1);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static boolean removeIngredient(String del) {
		for (Ingredient i : ingredients) {
			if (i.getName().equalsIgnoreCase(del)) {
				return ingredients.remove(i);
			}
		}
		return false;
	}

	public static Ingredient getIngredient(String name) throws IngredientNotFoundException {
		for (Ingredient i : ingredients) {
			if (i.getName().toLowerCase().equals(name.toLowerCase())) {
				return i;
			}
		}
		throw new IngredientNotFoundException(name + " was not found");
	}

}
