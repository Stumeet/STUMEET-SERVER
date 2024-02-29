package com.stumeet.server.stub;

public class TokenStub {

    private TokenStub() {}

    public static String getKakaoAccessToken() {
        return "Bearer rjdizj7Ae09H0oWlW46Oll9_x7AhzaJkp1gKKwzTAAABjd_1h0EhI_W2iN1234";
    }

    public static String getInvalidKakaoAccessToken() {
        return "invalidToken";
    }

    public static String getExpiredAccessToken() {
        return "eyJhbGciOiJIUzI1NiJ9.eyJpc3MiOiJTVFVNRUVUIiwic3ViIjoiMSIsImF1dGgiOiJGSVJTVF9MT0dJTiIsImV4cCI6MTcwOTAzOTIzNH0.5EPRp2zAJgXCOMgGKASF586R44o6U8-fla-IqTsrDBA";
    }

    public static String getMockAccessToken() {
        return "Bearer eyJhbGciOiJIUzI1NiJ9.eyJpc3MiOiJTVFVNRUVUIiwic3ViIjoiMSIsImF1dGgiOiJGSVJTVF9MT0dJTiIsImV4cCI6MTcwOTA0MTM1Mn0.1dU2fb1wUgJeV8R1RAjFpKxBg3qToRZnft1lxSejL7o";
    }
}
