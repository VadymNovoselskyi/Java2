package vadymNovoselskyi1;

import java.util.ArrayList;
import java.util.Collection;
import java.io.File;
import java.io.FileWriter;
import java.util.Scanner;
import java.io.IOException;
import java.io.FileNotFoundException;

public class FileManagement {
	private static String fileName = "products.txt";

	public static void main(String[] args) {
		//init
		ArrayList<Product> products = new ArrayList<>();
		Drink cola = new Drink("Cola", 10);
		Drink orange = new Drink("Orange", 10);
		Drink lime = new Drink("Lime", 10);

		Snack snickers = new Snack("Snickers", 10);
		Snack nuts = new Snack("Nuts", 10);
		Snack gum = new Snack("Gum", 10);

		Pocketbook seaOfTranquility = new Pocketbook("Sea of Tranqulity", 5);
		Pocketbook stationEleven = new Pocketbook("Station Eleven", 5);
		Pocketbook theGlassHotel = new Pocketbook("The Glass Hotel", 5);

		products.add(cola);
		products.add(orange);
		products.add(lime);
		products.add(snickers);
		products.add(nuts);
		products.add(gum);
		products.add(seaOfTranquility);
		products.add(stationEleven);
		products.add(theGlassHotel);

		//skapa filen
//		initFile();
		
		//skriva till filen
//		writeProducts(products);
		
		//läsa från filen
//		ArrayList<Product> products2 = readProducts();
//		System.out.println("  --- PRODUCTS ---  ");
//		for (Product product : products2) {
//			System.out.println(product);
//		}
		
	}

	public static void writeProducts(ArrayList<Product> products) {
		try {
			FileWriter writer = new FileWriter(fileName);
			for (Product product : products) writer.write(product.toString() +"\n");
			writer.close();
			System.out.println("Successfully wrote to the file.");
		} catch (IOException e) {
			System.out.println("An error occurred.");
			e.printStackTrace();
		}
	}
	public static void writeProducts(Collection<Product> products) {
		try {
			FileWriter writer = new FileWriter(fileName);
			for (Product product : products) writer.write(product.toString() +"\n");
			writer.close();
			System.out.println("Successfully wrote to the file.");
		} catch (IOException e) {
			System.out.println("An error occurred.");
			e.printStackTrace();
		}
	}

	public static ArrayList<Product> readProducts() {
		ArrayList<Product> products = new ArrayList<>();

		try {
			File result = new File(fileName);
			Scanner reader = new Scanner(result);
			while (reader.hasNextLine()) {
				products.add(stringToProduct(reader.nextLine()));
			}
			reader.close();
		} catch (FileNotFoundException e) {
			System.out.println("An error occurred.");
			e.printStackTrace();
		}
		return  products;
	}

	public static Product stringToProduct(String productString) {
		String[] productProperties = productString.split(",");
		String name = productProperties[0];
		int quantity = Integer.valueOf(productProperties[2]);
		String type = productProperties[4];
		
		switch(type) {
			case "DRINK": {			
				Drink product = new Drink(name, quantity);
				return product;
			}
			case "SNACK": {			
				Snack product = new Snack(name, quantity);
				return product;
			}
			case "POCKETBOOK": {			
				Pocketbook product = new Pocketbook(name, quantity);
				return product;
			}
		}
		return null;
	}

	
	public static void initFile() {
		try {
			File productsFile = new File(fileName);
			if(productsFile.exists()) productsFile.delete();
			if (productsFile.createNewFile()) {
				System.out.println("File created: " + productsFile.getName());
			} else {
				System.out.println("File already exists.");
			}
		} catch (IOException e) {
			System.out.println("An error occurred.");
			e.printStackTrace();
		}
	}
	
}
