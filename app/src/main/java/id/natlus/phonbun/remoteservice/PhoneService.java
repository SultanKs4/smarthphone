package id.natlus.phonbun.remoteservice;

import java.util.List;

import id.natlus.phonbun.db.PhoneEntity;
import retrofit2.Call;
import retrofit2.http.GET;

public interface PhoneService {
    @GET("/smartphone/api.php/phones")
    Call<List<PhoneEntity>> getPhones();
}
