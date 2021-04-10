import com.cljz.audioVisual.utils.JwtUtil;
import io.jsonwebtoken.Claims;

public class TestParesToken {
    public static void main(String[] args) {
        Claims claims = JwtUtil.parseJWT("eyJhbGciOiJIUzI1NiJ9.eyJyb2xlIjoicm9sZV9ub3JtYWwiLCJpZCI6IjcyMjI1MDY0ODI3OTU4MDY3MyIsImF2YXRhciI6Imh0dHBzOi8vY2RuLnN1bm9mYmVhY2hlcy5jb20vaW1hZ2VzL2RlZmF1bHRfYXZhdGFyLnBuZyIsInVzZXJOYW1lIjoi5rWL6K-V55So5oi3IiwiZXhwIjoxNjA0ODA1NTI5LCJlbWFpbCI6InRlc3RAc3Vub2ZiZWFjaC5uZXQifQ.aNOft2-1RONksxQ_aV1aO49zdqV0b4yX0BqXVw10DzU");
        //==============================================//
        Object id = claims.get("id");

        Object name = claims.get("userName");
        Object role = claims.get("role");
        Object avatar = claims.get("avatar");
        Object email = claims.get("email");


        System.out.println("id == > " + id);
        System.out.println("name == > " + name);
        System.out.println("role == > " + role);
        System.out.println("avatar == > " + avatar);
        System.out.println("email == > " + email);
    }
}