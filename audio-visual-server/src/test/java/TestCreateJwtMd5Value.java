import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.util.DigestUtils;

public class TestCreateJwtMd5Value {
    public static void main(String[] args) {
        BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();
        System.out.println(bCryptPasswordEncoder.encode("4793285CLJZ"));
//        String md5DigestAsHex = DigestUtils.md5DigestAsHex("4793285CLJZ".getBytes());
//        System.out.println(md5DigestAsHex);
    }
}
