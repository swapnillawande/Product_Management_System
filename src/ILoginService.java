public class ILoginService {


    public boolean validateUser(User user,Database database)
    {

        boolean check = database.validateUser(user);
        if (check)
        {
            return true;
        }

        return false;
    }
}
