package org.quark.microapidemo.config;

/**
 * 全局配置
 */
public class GlobalConfig {

    public class WebConfig {

        public static final double REDIS_SUBSCRIBER_EXPIRE_HOUR = 0.50;
        public static final String PASSWORD_NOSECURITY = "NONE";
        public static final String DEFAULT_ROLE = "DEFAULT";
        public static final String HEADER_AUTHORIZE = "Approve";
        public static final String HEADER_REFRESHTOKEN = "RefreshToken";
        public static final String CLAIMS_USER_KEY = "UserId";
        public static final String CLAIMS_ROLE_KEY = "Role";
        public static final int BAD_HEARTBEAT_DASHBOARD_SHOWRULE = 60 * 2; // 2分钟监测不到心跳则判定为心跳异常
    }

    public class JWTConfig {

        public static final String JWTID = "SpringDocker Access Token";
        public static final String JWTREFRESHID = "SpringDocker Refresh Token";
        public static final String SUBJECT = "Online JWT Builder";
        public static final String IISUSER = "StelyLan";
        public static final long TTLMILLIS = 1000 * 30;
        public static final long REFRESHTTLMILLIS = 1000 * 60 * 20;
    }

    public static class RateLimiter {

        public static final String TOKEN_BUCKET_IDENTITIEFER = "TOKEN_BUCKET_";
    }
}
