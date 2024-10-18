package vadymNovoselskyi1;

import javax.swing.*;
import java.util.HashMap;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class GUI {

	public GUI(HashMap<String, Product> products, int productCount) {
		//JFrame init
		JFrame jframe = new JFrame("Varuautomat");
		jframe.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		jframe.setSize(500, 500);
		jframe.setLayout(null);


		//labels
		JLabel nameLabel = new JLabel("Varuautomat");  
		nameLabel.setBounds(200, 0, 100, 30);  
		jframe.add(nameLabel);

		JLabel errorLabel = new JLabel();  
		errorLabel.setBounds(200, 100, 300, 30);  
		errorLabel.setForeground(Color.RED);
		jframe.add(errorLabel);

		JLabel boughtItemsLabel = new JLabel("Bought items:");  
		boughtItemsLabel.setBounds(25, 100, 100, 30);  
		jframe.add(boughtItemsLabel);

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

		// JList för köpta varor
		DefaultListModel<String> listModel = new DefaultListModel<>();
		JList<String> boughtList = new JList<>(listModel);

		JScrollPane scrollPane = new JScrollPane(boughtList);
		scrollPane.setBounds(25, 130, 250, 200);
		jframe.add(scrollPane);

		//visa jframe
		jframe.revalidate();
		jframe.repaint();
		jframe.setVisible(true);


		//event listeners
		buyButton.addActionListener(new ActionListener() {  
			public void actionPerformed(ActionEvent e) {  
				String[] itemInfo = ((String) comboBox.getItemAt(comboBox.getSelectedIndex())).split(" --- ");
				try {
					Main.buyItem(products.get(itemInfo[0]));
					String purchaseInfo = "'" +itemInfo[0] +"' --- " +itemInfo[1];
					listModel.addElement(purchaseInfo);
				} catch (IllegalStateException eror) {
					errorLabel.setText("Couldn't buy " +itemInfo[0] +", isn't in stock");
				}
			}  
		});  
	}

}
