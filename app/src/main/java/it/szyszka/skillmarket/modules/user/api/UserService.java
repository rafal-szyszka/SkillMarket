package it.szyszka.skillmarket.modules.user.api;

import java.util.ArrayList;

import it.szyszka.skillmarket.modules.user.model.User;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.POST;
import retrofit2.http.Query;

public interface UserService {

    String AUTH = "Authorization";

    @POST("user/reg")
    Call<UserResponse> signUpUser(@Body User user);

    @GET("user/login")
    Call<User> signIn(@Header(AUTH) String auth, @Query("userEmail") String email);

    @GET("user/get/friends")
    Call<ArrayList<User>> getFriends(@Header(AUTH) String auth, @Query("userEmail") String email);


}