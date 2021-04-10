public class TestCreateToken {
    public static void main(String[] args) {
       /* Map<String, Object> claims = new HashMap<>();
        claims.put("id", "722250648279580673");
        claims.put("userName", "测试用户");
        claims.put("role", "role_normal");
        claims.put("avatar", "https://cdn.sunofbeaches.com/images/default_avatar.png");
        claims.put("email", "test@sunofbeach.net");
        String token = JwtUtil.createToken(
                claims,1000*30);//有效期为1分钟
        System.out.println(token);*/

        Integer num1 = 100;
        Integer num2 = 100;

        System.out.println(num1 == num2);

        Integer num3 = 128;
        Integer num4 = 128;
        System.out.println(num3==num4);

    }
}