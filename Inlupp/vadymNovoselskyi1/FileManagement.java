package vadymNovoselskyi1;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileWriter;
import java.util.Scanner;
import java.io.IOException;
import java.io.FileNotFoundException;
import java.io.FileReader;

public class FileManagement {
	private static String fileName = "products.txt";

	public static void main(String[] args) {
		//init
		ArrayList<Product> products = new ArrayList<>();
		Drink cola = new Drink("Cola", 10);
		Drink orange = new Drink("Orange", 10);
		Drink lime = new Drink("Lime", 10);

		Snack chips = new Snack("Chips", 10);
		Snack nuts = new Snack("Nuts", 10);
		Snack gum = new Snack("Gum", 10);

		Pocketbook seaOfTranquility = new Pocketbook("Sea of Tranqulity", 5);
		Pocketbook stationEleven = new Pocketbook("Station eleven", 5);
		Pocketbook theGlassHotel = new Pocketbook("The glass hotel", 5);

		products.add(cola);
		products.add(orange);
		products.add(lime);
		products.add(chips);
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
	
	public static void writeToFile(String fileName, String data) {
		try {
			File file = new File(fileName);
			if(!file.exists()) file.createNewFile();
			FileWriter writer = new FileWriter(file, true);
			writer.write(data +"\n");
			writer.close();
//			System.out.println("Successfully wrote to the file.");
		} catch (IOException e) {
//			System.out.println("An error occurred.");
			e.printStackTrace();
		}
	}
	
	public static void readCSV(String fileName, HashMap<String, Product> products) {
		try (BufferedReader br = new BufferedReader(new FileReader(fileName))) {
            String line = br.readLine();  // Skip the header
            
            while ((line = br.readLine()) != null) {
                String[] values = line.split(",");
                String product = values[0].trim();
                int quantity = Integer.valueOf(values[1].trim());
                products.get(product).refill(quantity);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
	}

	public static void writeProducts(ArrayList<Product> products) {
		try {
			FileWriter writer = new FileWriter(fileName);
			for (Product product : products) writer.write(product.toString() +"\n");
			writer.close();
//			System.out.println("Successfully wrote to the file.");
		} catch (IOException e) {
//			System.out.println("An error occurred.");
			e.printStackTrace();
		}
	}
	public static void writeProducts(Collection<Product> products) {
		try {
			FileWriter writer = new FileWriter(fileName);
			for (Product product : products) writer.write(product.toString() +"\n");
			writer.close();
//			System.out.println("Successfully wrote to the file.");
		} catch (IOException e) {
//			System.out.println("An error occurred.");
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
//			System.out.println("An error occurred.");
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
