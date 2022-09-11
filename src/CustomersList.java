import java.util.ArrayList;

public class CustomersList  {

    private ArrayList<Customer> list = new ArrayList<>();

    public CustomersList(Customer c) {
          list.add(c);
    }
    public CustomersList( ) {

    }

    public void addCustomer(Customer c)
    {
         this.list.add(c);

    }

    public void displayCustomerList()
    {

        for(int i=0;i<this.list.size();i++)
        {
            System.out.println(this.list.get(i).getCustomerId());
        }
    }



}
