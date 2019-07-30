package id.natlus.phonbun.remoteservice;

import java.util.List;

import id.natlus.phonbun.db.PhoneEntity;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Url;

public interface PhoneService {
    @GET("/SultanKs4/smartphone/data")
    Call<List<PhoneEntity>> getPhones();

    @GET
    Call<PhoneEntity> getPhoneByType(@Url String url);
}
