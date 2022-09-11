import java.sql.*;
import java.util.*;
import java.util.regex.Pattern;

public class ICustomerService extends Customer{
    static String dbUrl ="jdbc:oracle:thin:@localhost:1521:xe";
    static String user = "system";
    static String pass = "root";
    Scanner sc = new Scanner(System.in);

    public boolean addCustomer(Customer c,Database database)
    {

        String firstName;
        String lastName;
        String mobileNumber;
        String address;
        String email;
        String forgetPasswordAnswer;
        String password;
        String confirmPass;

        try{
            System.out.println("Enter First name = ");
            firstName = sc.nextLine();
            System.out.println("Enter Last name = ");
            lastName = sc.nextLine();
            System.out.println("Enter mobile number = ");
            mobileNumber = sc.nextLine();
            System.out.println("Enter address = ");
            address = sc.nextLine();
            System.out.println("Enter email = ");
            email = sc.nextLine();
            System.out.println("Which is your favourite place ? ( security purpose ) ");
            forgetPasswordAnswer = sc.nextLine();
            System.out.println("Enter your password = ");
            password=sc.nextLine();
            System.out.println("Confirm your password = ");
            confirmPass = sc.nextLine();



            boolean flag = isValid(email,firstName,lastName,mobileNumber,forgetPasswordAnswer,password,confirmPass);

            if(flag)
            {
                c = new Customer(firstName, lastName, mobileNumber, address, email,forgetPasswordAnswer,password);

                String id = database.addCustomerToDataBase(c);
                c.setCustomerId(id);
                System.out.println("Account created successfully ! please login");
                System.out.println("Your Login id is = "+id);
                return true;
            }else {
                System.out.println("\nPlease try again ..");
            }


        }catch (Exception e)
        {
            System.out.println("Wrong input please try again ..");
        }


        return false;
    }

    public boolean isValid(String email,String fisrt,String last,String mobile,String forgetPass,String pass,String confirmPass) {

        boolean flag=true;

        String emailRegex = "^[a-zA-Z0-9_+&*-]+(?:\\."+
                "[a-zA-Z0-9_+&*-]+)*@" +
                "(?:[a-zA-Z0-9-]+\\.)+[a-z" +
                "A-Z]{2,7}$";

        Pattern pat = Pattern.compile(emailRegex);
        String[] myStr= new String[7];
        int c=0;

        if(!isValidEmail(email)){
            System.out.println("Invalid email ..");
            return false;
        }

        if (email.equals("")){
            myStr[c++]="email";
            flag=false;
        }
        if(fisrt.equals("")){
            myStr[c++]="first name";
            flag = false;
        } if(last.equals(""))
        {
            myStr[c++]="last name";
            flag = false;
        }
        if(mobile.equals(""))
        {
            myStr[c++]="mobile number";
            flag=false;
        } if(forgetPass.equals(""))
        {
            myStr[c++]="security answer";
            flag=false;
        }
         if(pass.equals(""))
        {
            myStr[c++]="password";
            flag=false;
        }
         if(confirmPass.equals(""))
        {
            myStr[c++]="confirm password";
            flag=false;
        }

        if(flag==false)
        {
            System.out.println("Please enter : ");
            for(int i=0;i< myStr.length;i++){
                if(myStr[i]!=null)
                {
                    System.out.println(myStr[i]);
                }
            }
            return false;
        }
        if(pass.equals(confirmPass))
        {
            return flag;
        }
        System.out.println("Your password and confirm password don't match ..");
        return false;
    }

    public boolean isValidEmail(String email ) {

        String emailRegex = "^[a-zA-Z0-9_+&*-]+(?:\\."+
                "[a-zA-Z0-9_+&*-]+)*@" +
                "(?:[a-zA-Z0-9-]+\\.)+[a-z" +
                "A-Z]{2,7}$";

        Pattern pat = Pattern.compile(emailRegex);

        return pat.matcher(email).matches();

    }

    public boolean removeCustomer(Customer c,Database database)
    {

        User user = new User(c.getCustomerId(),c.getPassword(),null);
        ILoginService iLoginService = new ILoginService();
        boolean check = iLoginService.validateUser(user,database);

        if (check)
        {
            boolean val=false;
            String ans="";
            System.out.println("Are you sure do you want to delete account ? (y/n) ");
            ans= sc.next();
            if(ans.equals("y")|| ans.equals("Y")){
                val = database.removeCustomer(c,user);
                if (val)
                {
                    System.out.println("Customer account with id "+user.getUserId()+" deleted successfully !");
                }
            }
            return true;


        }else {
            System.out.println("Invalid user try again ..");
        }



        return false;
    }

    public boolean updateCustomer(Customer c,Database database)
    {

        User user = new User(c.getCustomerId(),c.getPassword(),null);
        ILoginService iLoginService = new ILoginService();
        boolean check = iLoginService.validateUser(user,database);

        if (check)
        {
            String first_name;
            String last_name;
            String email;
            String mobile_no;
            String address;
            boolean flag =true;

            System.out.println("1.Update first name");
            System.out.println("2.Update last name");
            System.out.println("3.Update email");
            System.out.println("4.Update mobile number");
            System.out.println("5.Update address");
            System.out.println("6.Go back");
            System.out.println("Enter your choice ..");
            int val = sc.nextInt();
            sc.nextLine();

            if(val==1)
            {
                System.out.println("Enter your first name to update = ");
                first_name = sc.nextLine();
                if(first_name.equals("")){
                    System.out.println("Please enter first name ..");
                    return false;
                }
                c.setFirstName(first_name);
            }else if(val==2){
                System.out.println("Enter your last name to update = ");
                last_name=sc.nextLine();
                if (last_name.equals("")){
                    System.out.println("Please enter last name ..");
                    return false;
                }
                c.setLastName(last_name);
            }else if(val==3){
                System.out.println("Enter your email to update = ");
                email=sc.nextLine();
                if (email.equals("")){
                    System.out.println("Please enter last name ..");
                    return false;
                }
                boolean get = isValidEmail(email);
                if (get)
                {
                    c.setEmail(email);
                }else {
                    System.out.println("Invalid email ..");
                    return false;
                }

            }else if(val==4){
                System.out.println("Enter your mobile number to update = ");
                mobile_no=sc.nextLine();
                if (mobile_no.equals("")){
                    System.out.println("Please enter mobile number ..");
                    return false;
                }
                c.setMobileNumber(mobile_no);
            }else if(val==5){
                System.out.println("Enter your address to update = ");
                address=sc.nextLine();
                if (address.equals("")){
                    System.out.println("Please enter address ..");
                    return false;
                }
                c.setAddress(address);
            }else if(val==6){
                return false;
            }
            else {
                flag=false;
                System.out.println("Invalid input ..");
            }

            if (flag)
            {
                database.updateCustomer(c,user,val);
            }


        }else {
            System.out.println("Invalid user try again ..");
        }



        return false;
    }


    public boolean shopOnline(int id){

        try(Connection con = DriverManager.getConnection(dbUrl,user,pass)){

            IAdminService iAdminService = new IAdminService();
            boolean flag = true;
            ArrayList<Integer> p_list = new ArrayList<>();
            ArrayList<Integer> q_list = new ArrayList<>();

            while(flag){

                int val = shopMenu();
                switch (val)
                {
                    case 1:
                        iAdminService.viewProducts();
                        break;
                    case 2:
                        System.out.println("Enter product id = ");
                        int p_id = sc.nextInt();
                        System.out.println("Enter product quantity = ");
                        int q = sc.nextInt();

                        p_list.add(p_id);
                        q_list.add(q);


                        break;
                    case 3:

                        int total_sales=0;
                        int total_price=0;

                        int unit_price=0;


                        for(int i=0;i<p_list.size();i++)
                        {
                            total_price=0;
                            String selectProduct ="select * from product where p_id= "+p_list.get(i);

                            Statement statement = con.createStatement();

                            ResultSet rs = statement.executeQuery(selectProduct);
                            int product_id=0;
                            int product_quant=0;

                            while (rs.next()){

                                unit_price = Integer.parseInt(rs.getString("p_price"));
                                product_id = Integer.parseInt(rs.getString("p_id"));
                                product_quant = Integer.parseInt(rs.getString("p_quantity"));
                            }
                            int qu = q_list.get(i);
                            total_price+=qu*unit_price;
                            total_sales+=total_price;

                            String insert = "insert into orders values(SYSDATE,o_id.nextval,?,?,?,?,?)";
                            PreparedStatement preparedStatement  = con.prepareStatement(insert);
                            preparedStatement.setInt(1,product_id);
                            preparedStatement.setInt(2,id);
                            preparedStatement.setInt(3,qu);
                            preparedStatement.setInt(4,unit_price);
                            preparedStatement.setInt(5,total_price);

                            boolean r = preparedStatement.execute();


                            String update ="update product set p_quantity =? where p_id=?";
                            PreparedStatement ss = con.prepareStatement(update);
                            int res = (product_quant-qu);
                            ss.setInt(1,res);
                            ss.setInt(2,p_list.get(i));
                            boolean rr = ss.execute();


                        }

                        System.out.println("Your total shopping cost is = "+total_sales);
                        System.out.println("Bye thank you for shopping..");
                        flag=false;

                        break;
                    case 4:
                        flag=false;
                        break;
                }
            }

            con.close();
            return true;

        }catch (SQLException e){
            e.printStackTrace();
        }

        return false;
    }


    public boolean myOrders(int id)
    {

        try(Connection con = DriverManager.getConnection(dbUrl,user,pass)) {

            String q = "select * from orders where c_id="+id;
            Statement statement = con.createStatement();

            ResultSet rs =statement.executeQuery(q);

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

    public int shopMenu(){

        int val ;
        System.out.println("1.View products");
        System.out.println("2.Add products to cart");
        System.out.println("3.Checkout ");
        System.out.println("4.Go back");
        val= sc.nextInt();
        return val;
    }

}
