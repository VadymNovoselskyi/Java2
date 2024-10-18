package vadymNovoselskyi1;

public class Drink extends Product {
	public static final int COST = 20;
	public static final double VAT = 0.12;
	public static final ProductType TYPE = ProductType.DRINK;
	
	public Drink(String name, int quantity) {
		super(name, COST, quantity, VAT, TYPE);
	}
}
