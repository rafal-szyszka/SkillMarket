package it.szyszka.skillmarket.modules.user.api;

import it.szyszka.skillmarket.modules.user.model.User;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.POST;
import retrofit2.http.Query;

public interface UserService {

    @POST("user/reg")
    Call<UserResponse> signUpUser(@Body User user);

    @GET("user/login")
    Call<User> signIn(@Header("Authorization") String auth, @Query("userEmail") String email);

}