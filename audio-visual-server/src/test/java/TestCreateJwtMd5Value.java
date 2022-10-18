import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.util.DigestUtils;

public class TestCreateJwtMd5Value {
    public static void main(String[] args) {
        BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();
        System.out.println(bCryptPasswordEncoder.encode("xxx"));
//        String md5DigestAsHex = DigestUtils.md5DigestAsHex("xxx".getBytes());
//        System.out.println(md5DigestAsHex);
    }
}
