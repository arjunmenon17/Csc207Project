package interface_adapter.shopping_list;

import entity.Product;

import java.util.ArrayList;
import java.util.List;

public class ShoppingListState {
    private List<Product> productList = new ArrayList<>();
    private String errorMessage;
    private Product product;

    private float total_price = 0.00F;

    public ShoppingListState(ShoppingListState copy) {
        productList = copy.productList;
        errorMessage = copy.errorMessage;
        product = copy.product;
        total_price = copy.total_price;
    }
    public ShoppingListState() {
    }

    public List<Product> getProductList() {
        return productList;
    }//

    public void addProduct(Product product) {
        this.productList.add(product);
    }

    public void removeProduct(Product product) {this.productList.remove(product);}

    public void clearList() {this.productList.clear();}

    public Product getProduct() {return product;};

    public void setErrorMessage(String errorMessage) {
        this.errorMessage = errorMessage;
    }

    public String getErrorMessage() {
        return errorMessage;
    }

    public void set_total_price(float total_cost) {
        this.total_price = total_cost;
    }

    public float get_total_price() {
        return total_price;
    }
}
