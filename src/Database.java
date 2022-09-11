import java.sql.*;
import java.util.Scanner;

public class Database {

    static String dbUrl ="jdbc:oracle:thin:@localhost:1521:xe";
    static String user = "system";
    static String pass = "root";
    Scanner sc = new Scanner(System.in);


        public String addCustomerToDataBase(Customer c)
        {

            try(Connection conn = DriverManager.getConnection(dbUrl,user,pass);){
                // Class.forName("oracle.jdbc.driver.OracleDriver");

                Statement stmt = conn.createStatement();

                String addCustomerQuery = "insert into customer "+
                                          "values( "+"c_id.nextval ,"+"'"+c.getFirstName()+"'"+" ,"+
                                          "'"+c.getLastName()+"'"+" ,"+
                                          "'"+c.getEmail()+"'"+" ,"+
                                          "'"+c.getMobileNumber()+"'"+" ,"+
                                          "'"+c.getAddress()+"'"+" ,"+
                                          "'"+c.getForgetPasswordAnswer()+"'"+" ,"+
                                          "'"+c.getPassword()+"'"+")";


                stmt.executeUpdate(addCustomerQuery);

                String get_customer_id = "select c_id from customer where c_mobile_number = "+"'"+c.getMobileNumber()+"'";
                String id=null;
                ResultSet rs = stmt.executeQuery(get_customer_id);
                while (rs.next())
                {
                     id = rs.getString("c_id");
                }


                conn.close();
                return id;


            } catch (SQLException e) {
                    e.printStackTrace();
            }

            return null;
        }

    public boolean removeCustomer(Customer c,User user1)
    {

        try(Connection conn = DriverManager.getConnection(dbUrl,user,pass);){
            // Class.forName("oracle.jdbc.driver.OracleDriver");

            Statement stmt = conn.createStatement();


            String deleteCustomerQuery = "delete from customer where c_id = "+"'"+user1.getUserId()+"'";
            stmt.executeQuery(deleteCustomerQuery);


            conn.close();
            return true;


        } catch (SQLException e) {
            e.printStackTrace();
        }

        return false;
    }

    public boolean updateCustomer(Customer c,User user1,int val)
    {

        try(Connection conn = DriverManager.getConnection(dbUrl,user,pass);){
            // Class.forName("oracle.jdbc.driver.OracleDriver");

            Statement stmt = conn.createStatement();

            String update="";

            if(val==1)
            {
                update+="update customer set c_first_name = "+"'"+c.getFirstName()+"'"+"where c_id= "+user1.getUserId();

            }else if(val==2){
                update+="update customer set c_last_name = "+"'"+c.getLastName()+"'"+"where c_id= "+user1.getUserId();
            }else if(val==3){
                update+="update customer set c_email = "+"'"+c.getEmail()+"'"+"where c_id= "+user1.getUserId();
            }else if(val==4){
                update+="update customer set c_mobile_number = "+"'"+c.getMobileNumber()+"'"+"where c_id= "+user1.getUserId();
            }else if(val==5){
                update+="update customer set c_address = "+"'"+c.getAddress()+"'"+"where c_id= "+user1.getUserId();
            }

            stmt.executeUpdate(update);
            System.out.println("Updated !");



            conn.close();
            return true;


        } catch (SQLException e) {
            e.printStackTrace();
        }

        return false;
    }

    public boolean validateUser(User user1)
    {

        try(Connection conn = DriverManager.getConnection(dbUrl,user,pass);){
            // Class.forName("oracle.jdbc.driver.OracleDriver");

            Statement stmt = conn.createStatement();

            String selectUserFromCustomer = "select * from customer where c_id = "+"'"+user1.getUserId()+"'";



            ResultSet rs = stmt.executeQuery(selectUserFromCustomer);
            boolean flag = false;
            String id="";
            String pass="";
            while(rs.next())
            {
                id = rs.getString("c_id");
                pass = rs.getString("c_password");
                flag=true;
            }
            if(user1.getUserId().equals(id) && user1.getPassword().equals(pass))
            {
                return true;
            }


            conn.close();

            return false;

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return false;
    }

    public Customer getCustomerDetails(User user1)
    {

        try(Connection conn = DriverManager.getConnection(dbUrl,user,pass)){
            // Class.forName("oracle.jdbc.driver.OracleDriver");

            Statement stmt = conn.createStatement();

            String customerId="";
            String firstName="";
            String lastName="";
            String mobileNumber="";
            String address="";
            String email="";
            String password="";
            String forgetPasswordAnswer="";

            String getDetailsQuery= "select * from customer where c_id = "+"'"+user1.getUserId()+"'";

            ResultSet rs = stmt.executeQuery(getDetailsQuery);

            while (rs.next())
            {
                customerId = rs.getString("c_id");
                firstName = rs.getString("c_first_name");
                lastName = rs.getString("c_last_name");
                mobileNumber = rs.getString("c_mobile_number");
                address = rs.getString("c_address");
                email = rs.getString("c_email");
                forgetPasswordAnswer = rs.getString("c_forgot_password_answer");
                password = rs.getString("c_password");

            }
            Customer c = new Customer(firstName,lastName,mobileNumber,address,email,forgetPasswordAnswer,password);

            c.setCustomerId(customerId);

            conn.close();
            return c;


        } catch (SQLException e) {
            e.printStackTrace();
        }

        return null;
    }

    public Admin getAdminDetails(){

            try(Connection con = DriverManager.getConnection(dbUrl,user,pass)){

                Statement statement = con.createStatement();

                String q= "select * from customer where c_id = 135";
                String f_name ="";
                String l_name="";
                String pass ="";
                ResultSet rs = statement.executeQuery(q);
                while (rs.next()){
                    f_name = rs.getString("c_first_name");
                    l_name = rs.getString("c_last_name");
                    pass = rs.getString("c_password");
                }
                Admin a = new Admin(f_name+" "+l_name,pass);

                con.close();
                return a;




            }catch (SQLException e){
                e.printStackTrace();
            }
            return null;
    }


    public boolean validateAd(User user1)
    {

        try(Connection conn = DriverManager.getConnection(dbUrl,user,pass);){
            // Class.forName("oracle.jdbc.driver.OracleDriver");

            Statement stmt = conn.createStatement();

            String selectUserFromCustomer = "select * from customer where c_id=135";



            ResultSet rs = stmt.executeQuery(selectUserFromCustomer);

            String id="";
            String pass="";
            while(rs.next())
            {
                id = rs.getString("c_id");
                pass = rs.getString("c_password");
            }
            if(user1.getUserId().equals(id) && user1.getPassword().equals(pass))
            {
                return true;
            }


            conn.close();

            return false;

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return false;
    }

    public boolean getAllCustomers()
    {
        try(Connection con = DriverManager.getConnection(dbUrl,user,pass)){

            Statement statement = con.createStatement();

            String getCustomerDetails = "select * from customer";

            ResultSet rs = statement.executeQuery(getCustomerDetails);

            while (rs.next())
            {
                if(rs.getString("c_id").equals("135"))
                {
                    continue;
                }
                System.out.println("-----------------------");
                System.out.println(rs.getString("c_first_name")+" "+rs.getString("c_last_name"));
                System.out.println(rs.getString("c_mobile_number"));
                System.out.println(rs.getString("c_email"));
                System.out.println(rs.getString("c_address"));
            }
            System.out.println("-----------------------");
            con.close();
            return true;

        }catch (SQLException e)
        {
            e.printStackTrace();
        }
        return false;
    }


    public boolean getCustomerWithId(){

            try(Connection con = DriverManager.getConnection(dbUrl,user,pass)){

                Statement statement = con.createStatement();

                String get ="";
                String id;
                System.out.println("Enter customer id = ");
                id = sc.next();
                get="select * from customer where c_id = "+"'"+id+"'";

                ResultSet rs = statement.executeQuery(get);

                while (rs.next()){
                    System.out.println("-----------------------");
                    System.out.println(rs.getString("c_first_name")+" "+rs.getString("c_last_name"));
                    System.out.println(rs.getString("c_mobile_number"));
                    System.out.println(rs.getString("c_email"));
                    System.out.println(rs.getString("c_address"));
                }
                System.out.println("-----------------------");
                con.close();
                return true;


            }catch (SQLException e){
                e.printStackTrace();
            }

            return false;
    }

    public boolean removeCustomerWithId(){

            try(Connection con = DriverManager.getConnection(dbUrl,user,pass)){


                int id;
                System.out.println("Enter customer id = ");
                id = sc.nextInt();
                PreparedStatement stmt=con.prepareStatement("delete from customer where c_id=?");
                stmt.setInt(1,id);

                boolean i =stmt.execute();

                if(!i)
                {
                    System.out.println("Account deleted successfully !");
                    return true;
                }
                System.out.println("Invalid customer id ..");
                con.close();
                return false;


            }catch (Exception e){

            }

            return false;
    }

}
