package vadymNovoselskyi1;

public abstract class Product {
	private String name;
	private int cost;
	private int quantity;
	private int vat;
	private ProductType type;
	
	public Product(String name, int cost, int quantity, int vat, ProductType type) {
		this.name = name;
		this.cost = cost;
		this.quantity = quantity;
		this.vat = vat;
		this.type = type;
	}

	public void buy() {
		quantity--;
	}
	public void buy(int purchaseQuantity) {
		this.quantity -= purchaseQuantity;
	}
	
	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	public int getCost() {
		return cost;
	}

	public int getVat() {
		return vat;
	}
	
	public String getName() {
		return name;
	}

	public ProductType getType() {
		return type;
	}

	@Override
	public String toString() {
		return name + "," + cost + "," + quantity + "," + vat + "," + type;
	}
}
