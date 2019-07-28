package id.natlus.phonbun.viewmodel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import java.util.List;

import id.natlus.phonbun.db.CheckoutEntity;
import id.natlus.phonbun.db.PhoneEntity;
import id.natlus.phonbun.repository.SmartphoneRepository;

public class MainViewModel extends AndroidViewModel {
    private SmartphoneRepository smartphoneRepository;

    public MainViewModel(@NonNull Application application) {
        super(application);

        smartphoneRepository = new SmartphoneRepository(application);
    }

    public LiveData<List<PhoneEntity>> getPhoneList(){
        return smartphoneRepository.getPhoneList();
    }

    public void saveCheckout(CheckoutEntity checkoutEntity){
        smartphoneRepository.saveCheckout(checkoutEntity);
    }

    public LiveData<List<CheckoutEntity>> getCheckoutList(){
        return smartphoneRepository.getCheckoutList();
    }

    public PhoneEntity findByType(String type){
        return smartphoneRepository.findByType(type);
    }
}
