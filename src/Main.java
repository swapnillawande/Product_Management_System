import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;
import java.util.regex.Pattern;

public class Main {
    static Scanner sc = new Scanner(System.in);
    static Customer c;
    static CustomersList list =new CustomersList();
    static Admin admin = new Admin("root","root");
    static ICustomerService iCustomerService = new ICustomerService();
    static IAdminService iAdminService = new IAdminService();
    static Database database = new Database();


    public static void main(String[] args) {


        boolean cond=true;
        while(cond)
        {
            cond=start();
        }


    }

    public static int displayMenu()
    {
        int val=5 ;
        try{
            System.out.println("\n----- Welcome to Product Management System ----- ");
            System.out.println("\t\t\t "+getDate()+"\n");
            System.out.println("1.Admin login");
            System.out.println("2.Customer login");
            System.out.println("3.Create Customer Account");
            System.out.println("4.Forgot password");
            System.out.println("5.Exit");
            System.out.println("Enter your choice ..\n");
            val = sc.nextInt();

        }catch (Exception e)
        {

        }
        return val;
    }

    public static int customerLoginMenu(String s)
    {
        int val ;

        System.out.println("\t\t\t "+getDate()+"\n");
        System.out.println(s);
        System.out.println("1.My profile");
        System.out.println("2.Update profile");
        System.out.println("3.Delete my Account");
        System.out.println("4.My Orders");
        System.out.println("5.Shop online");
        System.out.println("6.Logout");
        System.out.println("Enter your choice ..\n");
        val = sc.nextInt();

        return val;

    }

    public static int adminLoginMenu()
    {
        int val=0;

        try {
            System.out.println("\t\t\t "+getDate()+"\n");
            System.out.println("Admin page ..");
            System.out.println("1.My profile");
            System.out.println("2.View customers");
            System.out.println("3.View customer with id");
            System.out.println("4.Delete customer account");
            System.out.println("5.View products");
            System.out.println("6.Add products");
            System.out.println("7.Delete products");
            System.out.println("8.Update products");
            System.out.println("9.View orders");
            System.out.println("10.Logout");
            System.out.println("Enter your choice ..\n");
            val = sc.nextInt();
        }catch (Exception e)
        {
            System.out.println("Invalid input ..");
        }


        return val;
    }


    public static boolean start()
    {
        int val = displayMenu();

        switch (val){

            case 1:
                 //iAdminService.displayAllCustomers(iCustomerService);
                  // iAdminService.displayCustomerWithSpecificId("C_ID_100",iCustomerService);

                try{
                    String id;
                    String pass;
                    System.out.println("Enter your Id = ");
                    id = sc.next();
                    System.out.println("Enter your password = ");
                    pass = sc.next();

                    if(id.equals("") && pass.equals(""))
                    {
                        System.out.println("Please enter id and password ..");
                        break;
                    }
                    User user1 = new User(id,pass,null);

                    boolean res = database.validateAd(user1);


                    if(res){

                        boolean a_login_flag=true;

                        while (a_login_flag)
                        {
                            int get = adminLoginMenu();

                            switch (get)
                            {
                                case 1:
                                    Admin a=database.getAdminDetails();
                                    System.out.println("Admin name = "+a.getName());
                                    System.out.println("Admin pass = "+a.getPassword());
                                    break;
                                case 2:
                                    database.getAllCustomers();
                                    break;
                                case 3:
                                    database.getCustomerWithId();
                                    break;
                                case 4:
                                    database.removeCustomerWithId();
                                    break;
                                case 5:
                                    iAdminService.viewProducts();
                                    break;
                                case 6:
                                    iAdminService.addProduct();
                                    break;
                                case 7:
                                    iAdminService.removeProduct();
                                    break;
                                case 8:
                                    iAdminService.updateProduct();
                                    break;
                                case 9:
                                    iAdminService.getAllOrders();
                                    break;
                                case 10:
                                    a_login_flag=false;
                                    break;
                                default:
                                    System.out.println("Invalid input");
                                    break;
                            }
                        }
                    }else {
                        System.out.println("Invalid admin credentials ..");
                    }


                }catch (Exception e){
                    e.printStackTrace();
                }


                break;

            case 2:
                try {
                    String id;
                    String pass;
                    System.out.println("Enter your Id = ");
                    id = sc.next();
                    System.out.println("Enter your password = ");
                    pass = sc.next();

                    if(id.equals("") && pass.equals(""))
                    {
                        System.out.println("Please enter id and password ..");
                        break;
                    }
                    User user1 = new User(id,pass,null);
                    boolean res = database.validateUser(user1);

                    if (res)
                    {
                        c=database.getCustomerDetails(user1);

                        boolean c_login_flag=true;
                        String s ="Welcome "+c.getFirstName()+" "+c.getLastName();
                        while (c_login_flag)
                        {

                            int get =customerLoginMenu(s);

                            switch (get)
                            {
                                case 1:
                                    System.out.println("Customer Id = "+c.getCustomerId());
                                    System.out.println(c.getFirstName()+" "+c.getLastName());
                                    System.out.println(c.getMobileNumber());
                                    System.out.println(c.getEmail());
                                    System.out.println("Security question answer = "+c.getForgetPasswordAnswer());
                                    System.out.println("Address = "+c.getAddress());
                                    System.out.println("Login password = "+c.getPassword());

                                    break;
                                case 2:
                                    iCustomerService.updateCustomer(c,database);
                                    break;
                                case 3:
                                    boolean go_back =iCustomerService.removeCustomer(c,database);
                                    if (go_back)
                                    {
                                        c_login_flag=false;
                                    }
                                    break;
                                case 4:
                                    int c_id_ = Integer.parseInt(id);
                                    iCustomerService.myOrders(c_id_);
                                    break;
                                case 5:
                                    int c_id = Integer.parseInt(id);
                                    iCustomerService.shopOnline(c_id);
                                    break;

                                case 6:
                                    System.out.println("Bye..Have a nice day !");
                                    c_login_flag=false;
                                    break;
                            }

                        }


                    }else{
                        System.out.println("Invalid user ..");
                    }

                }catch (Exception e){
                    e.printStackTrace();
                }
                break;

            case 3:

                  boolean get = iCustomerService.addCustomer(c,database);
//                c= new Customer();
//                c.setCustomerId("123");
//                c.setPassword("s");
//                boolean get = iCustomerService.removeCustomer(c,database);



                return true;

            case 4:

                break;

            case 5:
                System.out.println("Bye..Have a nice day !");
                return false;

        }
        return true;
    }


    public static String getDate()
    {
        Date date = new Date();
        SimpleDateFormat dateFormat = new SimpleDateFormat("hh.mm aa");
        String time= dateFormat.format(new Date()).toString();

        String[] arr = date.toString().split(" ");

        String day = arr[2];
        String month = arr[1];
        String year = arr[5];

        String date1 = day+"-"+month+"-"+year+"  "+time;

        return date1;
    }



}
