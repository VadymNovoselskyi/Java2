package vadymNovoselskyi1;

import javax.swing.*;
import java.util.HashMap;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class Main {

	public static void main(String[] args) {
		HashMap<String, Product> products = new HashMap<>();
		ArrayList<Product> productsArray = FileManagement.readProducts();
		int productCount = 0;
		for(Product product : productsArray) {
			products.put(product.getName(), product);
			productCount++;
		}

		//JFrame init
		JFrame jframe = new JFrame("Varuautomat");
		jframe.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		jframe.setSize(500, 500);
		jframe.setLayout(null);


		//labels
		JLabel nameLabel = new JLabel("Varuautomat");  
		nameLabel.setBounds(200, 0, 100, 30);  
		jframe.add(nameLabel);
		
		JLabel itemBoughtLabel = new JLabel();  
		itemBoughtLabel.setBounds(25, 100, 250, 50);
		jframe.add(itemBoughtLabel);

		//combobox
		String[] itemNames = new String[productCount];
		int i = 0;
		for(Product product : products.values()) {
			itemNames[i] = product.getName() +" --- "+ product.getCost()+"kr";
			i++;
		}

		JComboBox comboBox = new JComboBox(itemNames);    
		comboBox.setBounds(25, 50, 175, 25);  
		jframe.add(comboBox);


		//köp button
		JButton buyButton = new JButton("Buy selected item");  
		buyButton.setBounds(300, 50, 150, 25);  
		jframe.add(buyButton);  

		//visa jframe
		jframe.revalidate();
		jframe.repaint();
		jframe.setVisible(true);


		//event listeners
		buyButton.addActionListener(new ActionListener() {  
			public void actionPerformed(ActionEvent e) {  
				String[] itemInfo = ((String) comboBox.getItemAt(comboBox.getSelectedIndex())).split(" --- ");
				itemBoughtLabel.setText("Bought '" +itemInfo[0] +"' for " +itemInfo[1]);
			}  
		});  

		//spara resultater
		FileManagement.writeProducts(products.values());
	}

}
