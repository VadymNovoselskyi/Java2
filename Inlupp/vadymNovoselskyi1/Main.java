package vadymNovoselskyi1;

import java.util.ArrayList;
import java.util.HashMap;
import java.time.format.DateTimeFormatter;  
import java.time.LocalDateTime;    

public class Main {
	private static HashMap<String, Product> products = new HashMap<>();
	private static ArrayList<Product> productsArray;
	private static int productCount = 0;

	private static DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");  

	private static GUI gui;

	public static void main(String[] args) {
		productsArray = FileManagement.readProducts();

		//omvandla ArrayList till HashMap
		for(Product product : productsArray) {
			products.put(product.getName(), product);
			productCount++;
		}

		gui = new GUI(products, productCount);
		
//		FileManagement.readCSV("refill01.csv", products);
//		FileManagement.writeProducts(products.values());
	}


	public static void buyItem(Product item) throws IllegalStateException {
		item.buy();

		//spara resultater
		FileManagement.writeProducts(products.values());

		String log = "Date" + dtf.format(LocalDateTime.now()) +" --- VAT: " + Math.floor(Double.valueOf(100 * (item.getCost() - item.getCost() / (1 + item.getVat())))) / 100 +"kr";
		FileManagement.writeToFile("logs.txt", log);
	}
}
