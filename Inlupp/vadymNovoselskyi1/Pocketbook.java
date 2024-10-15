package vadymNovoselskyi1;

public class Pocketbook extends Product {
	public static final int COST = 50, VAT = 6;
	public static final ProductType TYPE = ProductType.POCKETBOOK;
	
	public Pocketbook(String name, int quantity) {
		super(name, COST, quantity, VAT, TYPE);
	}
}
