package vadymNovoselskyi1;

public class Snack extends Product {
	public static final int COST = 10, VAT = 12;
	public static final ProductType TYPE = ProductType.SNACK;
	
	public Snack(String name, int quantity) {
		super(name, COST, quantity, VAT, TYPE);
	}
}
