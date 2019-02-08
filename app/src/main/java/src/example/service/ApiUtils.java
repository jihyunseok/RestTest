package src.example.service;

public class ApiUtils {

    private ApiUtils() {}

    public static final String BASE_URL = "http://www.jihyunseok.cf";

    public static LoginService getLoginService() {
        return RetrofitClient.getClient(BASE_URL).create(LoginService.class);
    }
    public static HomeService getHomeService() {
        return RetrofitClient.getClient(BASE_URL).create(HomeService.class);
    }

}
