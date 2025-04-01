package com.stumeet.server.stub;

public class TokenStub {

    private TokenStub() {
    }

    public static String getKakaoAccessToken() {
        return "Bearer rjdizj7Ae09H0oWlW46Oll9_x7AhzaJkp1gKKwzTAAABjd_1h0EhI_W2iN1234";
    }

    public static String getInvalidSignatureAccessToken() {
        return "Bearer eyJraWQiOiJweWFSUXBBYm5ZIiwiYWxnIjoiUlMyNTYifQ.eyJpc3MiOiJodHRwczovL2FwcGxlaWQuYXBwbGUuY29tIiwiYXVkIjoiU3R1bWVldC5TdHVtZWV0IiwiZXhwIjoxNzEwMTQzMTM2LCJpYXQiOjE3MTAwNTY3MzYsInN1YiI6IjAwMTY2NS5iODlkNDQxZDYzMGI0Mjk5YTllYjkzNDY2OWFlMjcxMy4xNDE0IiwiY19oYXNoIjoiMlBQN1dpMUdiNzVrVWt3T0Z2YVRVUSIsImVtYWlsIjoidjRoNzRnZDRrakBwcml2YXRlcmVsYXkuYXBwbGVpZC5jb20iLCJlbWFpbF92ZXJpZmllZCI6dHJ1ZSwiaXNfcHJpdmF0ZV9lbWFpbCI6dHJ1ZSwiYXV0aF90aW1lIjoxNzEwMDU2NzM2LCJub25jZV9zdXBwb3J0ZWQiOnRydWV9.TLTJjSAU3dExWtOuPK2KFK7gthwFz1ftVc8wnrgPGhfDTy8yYnsKJ1D5uF4FzxDuKBeIVibuy8MMVNXy4attuKJ9sl7jNH-10Zh8DJMb8L9KwhFpap5lpJb1DiWxtjlolEnUtMhoyMZjEaWxvloCPhZUJtftb1MHLdAwVkDbFwy43yDxKYL-fFiJWVdoW_ERfMOpyyKaJFKq_dTAGD2TyXTxQZ9pCJyY2tY6DaiZNu6bX-TVRgN2gjPzNDSUlo0KLVZ0GOZH9-zlRp1yxQyZ97TSOLGthQ2psSQ4zMJ3HCak0dJP_jCZw50Jyyw3PDm3r_pXbC1pA8ZzvriB7ZsA";
    }

    public static String getExpiredAccessToken() {
        return "eyJhbGciOiJIUzI1NiJ9.eyJpc3MiOiJTVFVNRUVUIiwic3ViIjoiMSIsImF1dGgiOiJGSVJTVF9MT0dJTiIsImV4cCI6MTcwOTAzOTIzNH0.5EPRp2zAJgXCOMgGKASF586R44o6U8-fla-IqTsrDBA";
    }

    public static String getInvalidToken() {
        return "Bearer eyJhbGciOiJIUzI1NiJ9.eyJpc3MiOiJTVFVNRUVUIiwic3ViIjoiMSIsImF1dGgiOiJGSVJTVF9MT0dJTiIsImV4cCI6MTcwOTA0MTM1Mn0.1dU2fb1wUgJeV8R1RAjFpKx3g3qToRZnft1lxSejL7o";
    }

    public static String getMockAccessToken() {
        return "Bearer eyJhbGciOiJIUzI1NiJ9.eyJpc3MiOiJTVFVNRUVUIiwic3ViIjoiMSIsImF1dGgiOiJGSVJTVF9MT0dJTiIsImV4cCI6MTcwOTA0MTM1Mn0.1dU2fb1wUgJeV8R1RAjFpKxBg3qToRZnft1lxSejL7o";
    }

    public static String getExpiredRefreshToken() {
        return "eyJhbGciOiJIUzI1NiJ9.eyJpc3MiOiJTVFVNRUVUIiwiaWF0IjoxNzA5MTkzMzgwLCJzdWIiOiIxIiwiZXhwIjoxNzA5MTkzMzgwfQ.faFrP-sNlCnu5vGPfl0vQieMZ_Iydk_UjhaYIkhz4P4";
    }

}
