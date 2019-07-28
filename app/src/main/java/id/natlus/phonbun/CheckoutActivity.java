package id.natlus.phonbun;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProviders;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import id.natlus.phonbun.adapters.PhoneAdapter;
import id.natlus.phonbun.db.CheckoutEntity;
import id.natlus.phonbun.db.PhoneDao;
import id.natlus.phonbun.db.PhoneEntity;
import id.natlus.phonbun.models.Checkout;
import id.natlus.phonbun.viewmodel.MainViewModel;

public class CheckoutActivity extends AppCompatActivity {
    TextView resultName, resultAddress, resultNumberPhone, resultEmail, resultPayment, resultType, resultPrice, resultDetail;
    ImageView resultImage;
    PhoneEntity phoneEntity;
    MainViewModel mainViewModel;
    CheckoutEntity checkout;

    public static final String Key_RegisterActivity = "Key_RegisterActivity";


    private void initComponents(){
        resultType = findViewById(R.id.checkout_typeText);
        resultAddress = findViewById(R.id.checkout_addressText);
        resultDetail = findViewById(R.id.checkout_detailText);
        resultName = findViewById(R.id.checkout_nameText);
        resultNumberPhone = findViewById(R.id.checkout_numberPhoneText);
        resultEmail = findViewById(R.id.checkout_EmailAddressText);
        resultPayment = findViewById(R.id.checkout_PaymentText);
        resultPrice = findViewById(R.id.checkout_textPrice);
        resultImage = findViewById(R.id.checkout_imgTop);

        checkout = getIntent().getParcelableExtra(Key_RegisterActivity);

        mainViewModel = ViewModelProviders.of(this).get(MainViewModel.class);

        PhoneEntity phoneEntity1 = mainViewModel.findByType(checkout.getType());
        System.out.println(phoneEntity1.getType());
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_checkout);

        initComponents();

        resultType.setText(checkout.getType());
        resultAddress.setText(checkout.getAddress());
        resultDetail.setText(phoneEntity.getDetail());
        resultPrice.setText(phoneEntity.getPrice());
        resultName.setText(checkout.getName());
        resultNumberPhone.setText(checkout.getPhone_number());
        resultEmail.setText(checkout.getEmail());
        resultPayment.setText(checkout.getPayment());
        Picasso.get().load(phoneEntity.getImage()).placeholder(R.color.colorWhite).into(resultImage);
    }

    public void postBuy(View view) {
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent i = new Intent(CheckoutActivity.this, SuccessActivity.class);
                startActivity(i);
                finish();
            }
        }, 500);
    }
}
