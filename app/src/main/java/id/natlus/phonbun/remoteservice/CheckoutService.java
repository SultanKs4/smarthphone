package id.natlus.phonbun.remoteservice;

import java.util.List;

import id.natlus.phonbun.db.CheckoutEntity;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;

public interface CheckoutService {
    @GET("/smartphone/api.php/checkout")
    Call<List<CheckoutEntity>> getCheckout();

    @POST("/smartphone/api.php/checkout")
    Call<CheckoutEntity> postCheckout(@Body CheckoutEntity checkoutEntity);
}
