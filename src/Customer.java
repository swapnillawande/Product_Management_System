import java.util.Scanner;
import java.util.regex.Pattern;

public class Customer {

    private String customerId;
    private String firstName;
    private String lastName;
    private String mobileNumber;
    private String address;
    private String email;
    private String password;
    private String forgetPasswordAnswer;
    private static Scanner sc = new Scanner(System.in);

    public Customer( String firstName, String lastName, String mobileNumber, String address, String email,String forgetPasswordAnswer,String password) {
//        this.customerId = "C_ID_"+c;
        this.firstName = firstName;
        this.lastName = lastName;
        this.mobileNumber = mobileNumber;
        this.address = address;
        this.email = email;
        this.forgetPasswordAnswer = forgetPasswordAnswer;
        this.password = password;


    }

    public Customer()
    {

    }

    public String getCustomerId() {
        return customerId;
    }

    public void setCustomerId(String customerId) {
        this.customerId = customerId;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getMobileNumber() {
        return mobileNumber;
    }

    public void setMobileNumber(String mobileNumber) {
        this.mobileNumber = mobileNumber;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getForgetPasswordAnswer() {
        return forgetPasswordAnswer;
    }

    public void setForgetPasswordAnswer(String forgetPasswordAnswer) {
        this.forgetPasswordAnswer = forgetPasswordAnswer;
    }
}
