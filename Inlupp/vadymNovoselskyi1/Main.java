package vadymNovoselskyi1;

import java.util.ArrayList;
import java.util.HashMap;

public class Main {

	public static void main(String[] args) {
		HashMap<String, Product> products = new HashMap<>();
		ArrayList<Product> productsArray = FileManagement.readProducts();
		for(Product product : productsArray) {
			products.put(product.getName(), product);
		}	
		
		products.get("Cola").buy();
		
		FileManagement.writeProducts(products.values());
	}

}
