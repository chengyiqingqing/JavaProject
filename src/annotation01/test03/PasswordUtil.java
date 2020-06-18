package annotation01.test03;

public class PasswordUtil {

    @UseCase(id=47,description ="Password must contain at least one numeric")
    public boolean validatePassword(String password){
        return (password.matches("\\w*\\d\\w*"));
    }


    @UseCase(id=48)
    public boolean encryptPassword(String password){
        return (password.matches("\\w*\\d\\w*"));
    }

    @UseCase(id=49,description = "new password can`t equal previously used ones")
    public boolean checkNewPassword(String password){
        return (password.matches("\\w*\\d\\w*"));
    }

}
