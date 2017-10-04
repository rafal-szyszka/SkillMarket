package it.szyszka.skillmarket.api;

import retrofit2.Response;

/**
 * Created by rafal on 04.10.17.
 */

public interface ApiResponse<T> {

    void onSuccess(Response<T> response);
    void onFailure(Response<T> response);
    void handleErrors(String errorMessage);
}
