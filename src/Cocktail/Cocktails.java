package Cocktail;
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

import util.Content;

public class Cocktails implements Serializable {

	private static final long serialVersionUID = 1L;
	private static File fileName = new File(".\\save\\Bar.txt");
	private static List<Cocktail> cocktails = new LinkedList<Cocktail>();

	public Cocktails() {
		laden();
	}

	public Cocktail getCocktailFromId(int id) {
		for (Cocktail c : cocktails) {
			if (c.getId() == id) {
				return c;
			}
		}
		return null;
	}

	public static boolean addCocktail(Cocktail cocktail) {
		if (!cocktails.stream().anyMatch(x -> x.equals(cocktail))) {
			cocktails.add(cocktail);
			speichern();
			return true;
		}
		return false;
	}

	public static void changeContent(Content old, Content upd) {
		for (Cocktail c : cocktails) {
			c.updateContent(old, upd);
		}
		speichern();
	}

	public static void addCocktailList(List<Cocktail> list) {
		cocktails = Stream.of(cocktails, list).flatMap(Collection::stream).collect(Collectors.toList());
	}

	public static boolean removeCocktail(String s) {
		for (Cocktail c : cocktails) {
			if (c.getName().equalsIgnoreCase(s)) {
				cocktails.remove(cocktails.indexOf(c));
				return true;
			}
		}

		return false;
	}

	public static List<Cocktail> getCocktailList() {
		return cocktails;
	}

	public static Cocktail getCocktail(String s) {
		for (Cocktail c : cocktails) {
			if (c.getName().equalsIgnoreCase(s)) {
				return c;
			}
		}
		return null;
	}

	public static Cocktail getCocktail(int i) {
		return cocktails.get(i);
	}

	public static int getSize() {
		return cocktails.size();
	}

	public static void zeigeCocktails() {
		for (Cocktail c : cocktails) {
			System.out.println(c.toString());
		}
	}

	@SuppressWarnings("resource")
	public static void speichern() {
		try {
			new ObjectOutputStream(new FileOutputStream(fileName)).writeObject(cocktails);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	@SuppressWarnings({ "resource", "unchecked" })
	public static void laden() {
		try {
			addCocktailList((List<Cocktail>) new ObjectInputStream(new FileInputStream(fileName)).readObject());
			cocktails = cocktails.stream().unordered().distinct().collect(Collectors.toList());
			cocktails.stream().forEach(x -> x.upd());
			Collections.sort(cocktails, new Comparator<Cocktail>(){
				   @Override
				   public int compare(Cocktail o1, Cocktail o2){
				        if(o1.getId() < o2.getId()){
				           return -1; 
				        }
				        if(o1.getId() > o2.getId()){
				           return 1; 
				        }
				        return 0;
				   }
				});
			cocktails.get(cocktails.size()-1).setCounter(cocktails.get(cocktails.size() - 1).getId() + 1);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static void test() {
		List<Cocktail> test = new LinkedList<Cocktail>();
		for (Cocktail c : cocktails) {
			if (c.getName().equalsIgnoreCase("clover club")) {
				test.add(c);
			}
		}
		System.out.println(test.get(0).equals(test.get(1)));
		if (test.isEmpty()) {
			System.out.println("No bupes c:");
		}
	}

}