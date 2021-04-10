package com.cljz.audioVisual.utils;

import com.cljz.audioVisual.pojo.User;
import io.jsonwebtoken.Claims;

import java.util.HashMap;
import java.util.Map;

public class ClaimsUtils {
    public static final String ID = "id";
    public static final String USER_NAME = "user_name";
    public static final String ROLES = "roles";
    public static final String AVATAR = "avatar";
    public static final String EMAIL = "email";

    public static Map<String, Object> UserClainms(User user) {
        Map<String, Object> claims = new HashMap<>();
        claims.put(ID, user.getId());
        claims.put(USER_NAME, user.getName());
        claims.put(ROLES, user.getRoles());
        claims.put(AVATAR, user.getAvatar());
        claims.put(EMAIL, user.getEmail());
        return claims;
    }

    public static User claimsUser(Claims claims) {
        User user = new User();
        String id = (String) claims.get(ID);
        user.setId(id);

        String userName = (String) claims.get(USER_NAME);
        user.setName(userName);

        String roles = (String) claims.get(ROLES);
        user.setRoles(roles);

        String avatar = (String) claims.get(AVATAR);
        user.setAvatar(avatar);

        String email = (String) claims.get(EMAIL);
        user.setEmail(email);

        return user;
    }
}