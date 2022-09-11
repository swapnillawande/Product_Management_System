import java.sql.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.Scanner;

public class IAdminService extends ICustomerService{

    static String dbUrl ="jdbc:oracle:thin:@localhost:1521:xe";
    static String user = "system";
    static String pass = "root";
    Scanner sc = new Scanner(System.in);
    Database db = new Database();

    public boolean addProduct()
    {

        try(Connection con = DriverManager.getConnection(dbUrl,user,pass)){


            String name;
            int quant;
            int price;
            String colour;
            String man;
            String cat;

            System.out.println("Enter product name = ");
            name = sc.nextLine();
            System.out.println("Enter product quantity = ");
            quant = sc.nextInt();
            sc.nextLine();
            System.out.println("Enter product price = ");
            price = sc.nextInt();
            sc.nextLine();
            System.out.println("Enter product colour = ");
            colour = sc.nextLine();
            System.out.println("Enter product manufacturer name = ");
            man= sc.nextLine();
            System.out.println("Enter product category = ");
            cat = sc.nextLine();


            PreparedStatement stmt = con.prepareStatement("insert into product values(p_id.nextval,?,?,?,?,?,?)");

            stmt.setString(1,name);
            stmt.setInt(2,price);
            stmt.setString(3,colour);
            stmt.setString(4,man);
            stmt.setInt(5,quant);
            stmt.setString(6,cat);

            int i =stmt.executeUpdate();
            if(i>0)
            {
                System.out.println("Product added successfully !");
                return true;
            }
            System.out.println("Product not added ..");
            return false;


        }catch (SQLException e){
            e.printStackTrace();
        }

        return false;
    }


    public boolean viewProducts(){

        try(Connection con = DriverManager.getConnection(dbUrl,user,pass)){

            Statement statement = con.createStatement();
            String q = "select * from product";

            ResultSet rs = statement.executeQuery(q);

            while (rs.next()){
                System.out.println("-------------------");
                System.out.println("Product Id = "+rs.getString("p_id"));
                System.out.println("Product name = "+rs.getString("p_name"));
                System.out.println("Product price = "+rs.getString("p_price"));
                System.out.println("Product colour = "+rs.getString("p_color"));
                System.out.println("Product manufacturer = "+rs.getString("P_MANUFACTURER"));
                System.out.println("Product quantity = "+rs.getString("p_quantity"));
                System.out.println("Product category = "+rs.getString("p_category"));
            }
            System.out.println("-------------------");

            con.close();
            return true;

        }catch (SQLException e){
            e.printStackTrace();
        }


        return false;
    }

    public boolean removeProduct(){

        try(Connection con = DriverManager.getConnection(dbUrl,user,pass)){

            Statement statement = con.createStatement();
            int id;
            System.out.println("Enter product id to remove = ");
            id = sc.nextInt();
            String remove = "delete from product where p_id="+"'"+id+"'";

            int i = statement.executeUpdate(remove);

            if (i>0){
                System.out.println("Product removed successfully !");
                return true;
            }
            System.out.println("Invalid product id ..");
            con.close();
            return false;


        }catch (SQLException e){
            e.printStackTrace();
        }


        return false;
    }

    public boolean updateProduct()
    {

        try(Connection con = DriverManager.getConnection(dbUrl,user,pass)){

            int productId;
            System.out.println("Enter product id to update = ");
            productId= sc.nextInt();

            Statement statement = con.createStatement();

            String get ="select * from product where p_id="+productId;

            int i = statement.executeUpdate(get);

            if(i>0){




            }else {
                System.out.println("Invalid product id ..");
            }



        }catch (SQLException e){
            e.printStackTrace();
        }


        return false;
    }

    public boolean getAllOrders(){

        try(Connection con = DriverManager.getConnection(dbUrl,user,pass)){

            String getQuery = "select * from orders";
            Statement statement = con.createStatement();

            ResultSet rs = statement.executeQuery(getQuery);

            while (rs.next()){
                System.out.println("------------------");
                System.out.println("Order id = "+rs.getString("o_id"));
                System.out.println("Order date = "+rs.getString("o_date"));
                System.out.println("Product id = "+rs.getString("p_id"));
                System.out.println("Customer id = "+rs.getString("c_id"));
                System.out.println("Quantity = "+rs.getString("o_quantity"));
                System.out.println("Total price = "+rs.getString("o_total_price"));

            }
            System.out.println("------------------");
            con.close();
            return true;


        }catch (SQLException e){
            e.printStackTrace();
        }

        return false;
    }




}
