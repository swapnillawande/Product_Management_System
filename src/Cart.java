import java.util.Map;

public class Cart {
    private Map myCart;

    public Cart(Map myCart) {
        this.myCart = myCart;
    }

    public Map getMyCart() {
        return myCart;
    }

    public void setMyCart(Map myCart) {
        this.myCart = myCart;
    }

    public int getLen(){
        int c=0;
        while (!myCart.isEmpty())
        {
            c++;
        }
        return c;
    }
}
