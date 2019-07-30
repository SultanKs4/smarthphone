package id.natlus.phonbun;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProviders;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.List;

import id.natlus.phonbun.adapters.PhoneAdapter;
import id.natlus.phonbun.db.CheckoutEntity;
import id.natlus.phonbun.db.PhoneDao;
import id.natlus.phonbun.db.PhoneEntity;
import id.natlus.phonbun.models.Checkout;
import id.natlus.phonbun.repository.SmartphoneRepository;
import id.natlus.phonbun.viewmodel.MainViewModel;

public class CheckoutActivity extends AppCompatActivity {
    TextView resultName, resultAddress, resultNumberPhone, resultEmail, resultPayment, resultType, resultPrice, resultDetail;
    ImageView resultImage;
    CheckoutEntity checkout;
    String phone_Detail;
    String phone_Price;
    String phone_Image;
    private MainViewModel mainViewModel;

    public static final String Key_RegisterActivity = "Key_RegisterActivity";

    // Loading indicator untuk ditampilkan saat menyimpan data
    ProgressDialog loadingIndicator;

    private void showLoadingIndicator() {
        loadingIndicator = new ProgressDialog(this);
        loadingIndicator.setMessage("Please wait...");
        loadingIndicator.setIndeterminate(false);
        loadingIndicator.setCancelable(false);
        loadingIndicator.show();
    }

    private void initComponents(){
        checkout = getIntent().getParcelableExtra(Key_RegisterActivity);
        phone_Detail = getIntent().getStringExtra("phone_Detail");
        phone_Price = getIntent().getStringExtra("phone_Price");
        phone_Image = getIntent().getStringExtra("phone_Image");
        resultType = findViewById(R.id.checkout_typeText);
        resultAddress = findViewById(R.id.checkout_addressText);
        resultDetail = findViewById(R.id.checkout_detailText);
        resultName = findViewById(R.id.checkout_nameText);
        resultNumberPhone = findViewById(R.id.checkout_numberPhoneText);
        resultEmail = findViewById(R.id.checkout_EmailAddressText);
        resultPayment = findViewById(R.id.checkout_PaymentText);
        resultPrice = findViewById(R.id.checkout_textPrice);
        resultImage = findViewById(R.id.checkout_imgTop);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_checkout);

        mainViewModel = ViewModelProviders.of(this).get(MainViewModel.class);

        initComponents();

        resultType.setText(checkout.getType());
        resultAddress.setText(checkout.getAddress());
        resultDetail.setText(phone_Detail);
        resultPrice.setText(phone_Price);
        resultName.setText(checkout.getName());
        resultNumberPhone.setText(checkout.getPhone_number());
        resultEmail.setText(checkout.getEmail());
        resultPayment.setText(checkout.getPayment());
        Picasso.get().load(phone_Image).placeholder(R.color.colorWhite).into(resultImage);
    }

    public void postBuy(View view) {
        showLoadingIndicator();
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                mainViewModel.saveCheckout(checkout);
                loadingIndicator.dismiss();
                Intent i = new Intent(CheckoutActivity.this, SuccessActivity.class);
                startActivity(i);
                finish();
            }
        }, 3000);
    }
}
