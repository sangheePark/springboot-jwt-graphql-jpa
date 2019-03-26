package com.novles.security.jwt;

public class JwtConstants {

    public static final String SECRET = "SecretKeyToGenJWTs";
    public static final long EXPIRATION_TIME = 864_000_000; // 10 days
    public static final String TOKEN_PREFIX = "Bearer ";
    public static final String HEADER_STRING = "Authorization";
    public static final String AUTHENTICATION_URL = "/api/sign";
    public static final String REFRESH_TOKEN_URL = "/api/token";
    public static final String REST_API_ROOT_URL = "/api/**";
    public static final String GRAPHQL_API_ROOT_URL = "/gapi/**";
    public static final String GRAPHQL_SUBSCRIPTIONS_URL = "/subscriptions";
    public static final String GRAPHQL_GUI_URL = "/gui";
}