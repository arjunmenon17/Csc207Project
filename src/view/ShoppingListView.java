package view;

import entity.CommonProduct;
import entity.Product;
import interface_adapter.shopping_list.ShoppingListObserver;
import interface_adapter.shopping_list.ShoppingListState;
import interface_adapter.shopping_list.ShoppingListViewModel;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class ShoppingListView implements ShoppingListObserver {

    JFrame frame = new JFrame("Shopping List");
    JList<CommonProduct> list = new JList<>();
    DefaultListModel<CommonProduct> model = new DefaultListModel<>();

    JButton clearButton = new JButton(ShoppingListViewModel.CLEAR_BUTTON_LABEL);
    JButton checkoutButton = new JButton(ShoppingListViewModel.CHECKOUT_BUTTON_LABEL);

    JLabel label = new JLabel();
    JPanel panel = new JPanel();
    JSplitPane splitPane = new JSplitPane();

    private ShoppingListViewModel viewModel;
    public void updateShoppingList(Product product){
        if (product != null) {
            model.addElement((CommonProduct) product);
        }
    }

    private void displaySelectedProductDetails(int selectedIndex) {
        if (selectedIndex >= 0 && selectedIndex < model.getSize()) {
            Product selectedProduct = model.getElementAt(selectedIndex);
            String details = "Name: " + selectedProduct.getName() + "\n"
                    + "Price: $" + selectedProduct.getPrice();
            label.setText(details);
        }
    }
    private void initializeListSelectionListener() {
        list.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                int selectedIndex = list.getSelectedIndex();
                if (selectedIndex != -1) {
                    displaySelectedProductDetails(selectedIndex);
                }
            }
        });
    }


    public ShoppingListView(ShoppingListViewModel viewModel) {
        this.viewModel = viewModel;

        list.setModel(model);

        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        clearButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                viewModel.firePropertyChanged();
            }
        });

        checkoutButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                viewModel.firePropertyChanged();
            }
        });

        splitPane.setLeftComponent(new JScrollPane(list));
        panel.add(label);
        panel.add(clearButton);
        panel.add(checkoutButton);
        splitPane.setRightComponent(panel);

        initializeListSelectionListener();

        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.add(splitPane);
        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }
}
