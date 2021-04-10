package com.cljz.audioVisual.utils;

public interface Constants {

    int DEFAULT_SIZE = 30;
    interface User{
        String ROLE_ROOT = "role_root";
        String ROLE_ADMIN = "role_admin";
        String DEFAULT_AVATAR = "https://pics1.baidu.com/feed/b7fd5266d0160924d76e9384063db9fde7cd34a3.jpeg?token=a2140e3a88487ec11f4057dfc3766fe9";

        String KEY_CAPTCHA_CONTENT = "key_captcha_content_";
        String KEY_EMAIL_SEND__IP = "key_email_ip_";
        String KEY_EMAIL_SEND_ADDRESS = "key_email_send_address_";
        String KEY_EMAIL_CODE_CONTENT = "key_email_code_content_";

        String COOKIE_TOKEN_KEY = "cookie-token-key";
    }

    interface TimeValue {
        Integer HOUR_2 = 2 * 60 * 60 * 1000;
        Integer HOUR_3 = 3 * 60 * 60*1000;
    }
}