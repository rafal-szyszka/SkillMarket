package it.szyszka.skillmarket.modules.user.api;

import it.szyszka.skillmarket.modules.user.model.User;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

public interface UserService {

    @POST("user/reg")
    Call<UserResponse> signUpUser(@Body User user);



}