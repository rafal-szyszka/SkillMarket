package it.szyszka.skillmarket.modules.offers.api;

import java.util.List;

import it.szyszka.skillmarket.modules.offers.model.Announcement;
import it.szyszka.skillmarket.modules.offers.model.PreparedAdvertisement;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.POST;

/**
 * Created by rafal on 08.12.17.
 */

public interface AdvertService {

    String AUTH = "Authorization";

    @GET("offer")
    Call<List<Announcement>> getAllAdverts(@Header(AUTH) String auth);

    @POST("offer")
    Call<Announcement> placeAdvertisement(@Header(AUTH) String auth, @Body PreparedAdvertisement preparedAdvertisement);

}
