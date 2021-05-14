package by.itacademy.security.encoder;

import org.apache.commons.codec.digest.DigestUtils;
import org.springframework.stereotype.Component;

@Component
public class MD5PasswordEncoder implements PasswordEncoder{

    @Override
    public String encodePassword(String pass) {
        return DigestUtils.md5Hex(pass);
    }
}
