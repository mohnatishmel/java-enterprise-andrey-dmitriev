package by.itacademy.security.encoder;

public class MD5PasswordEncoder implements PasswordEncoder{

    @Override
    public String encodePassword(String pass) {
        return pass;
    }
}
