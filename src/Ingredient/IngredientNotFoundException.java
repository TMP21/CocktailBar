package Ingredient;
public class IngredientNotFoundException extends Exception {
	
	private static final long serialVersionUID = 1L;

	public IngredientNotFoundException(String errorMessage) {
		super(errorMessage);
	}
	
}