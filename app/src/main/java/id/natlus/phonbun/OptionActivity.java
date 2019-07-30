package id.natlus.phonbun;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Patterns;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.squareup.picasso.Picasso;

import id.natlus.phonbun.db.CheckoutEntity;
import id.natlus.phonbun.db.PhoneEntity;
import id.natlus.phonbun.models.Checkout;

public class OptionActivity extends AppCompatActivity {
    public static final String Key_RegisterActivity = "Key_RegisterActivity";
    PhoneEntity phone;

    TextView resultType, resultPrice, resultDetail, resultFullDetailDisplay, resultFullDetailBrand, resultFullDetailHardware;
    ImageView resultImageUrl;
    String fullDetailDisplay, fullDetailHardware;
    EditText editTextName, editTextAddress, editTextPhone, editTextEmail;
    Spinner paymentSpinner;

    private void initComponents(){
        resultType = findViewById(R.id.option_typeText);
        resultDetail = findViewById(R.id.option_detailText);
        resultPrice = findViewById(R.id.option_textPrice);
        resultImageUrl = findViewById(R.id.option_imgTop);
        resultFullDetailDisplay = findViewById(R.id.option_FullDetailDisplayText);
        resultFullDetailBrand = findViewById(R.id.option_FullDetailBrandText);
        resultFullDetailHardware = findViewById(R.id.option_FullDetailHardwareText);
        editTextName = findViewById(R.id.option_nameTextEdit);
        editTextAddress = findViewById(R.id.option_addressTextEdit);
        editTextPhone = findViewById(R.id.option_numberPhoneTextEdit);
        editTextEmail = findViewById(R.id.option_EmailAddressTextEdit);
        paymentSpinner = findViewById(R.id.option_spinnerPayment);

        phone = getIntent().getParcelableExtra(Key_RegisterActivity);
    }

    private void initSpec(){
        fullDetailDisplay = phone.getDisplay_size() + "\n" + phone.getDisplay_ratio() + "\n" + phone.getDisplay_resolution();
        fullDetailHardware = phone.getHardware_proc() + "\n" + phone.getHardware_camera() + "\n" + phone.getHardware_battery();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_option);

        initComponents();
        initSpec();

        resultType.setText(phone.getType());
        resultDetail.setText(phone.getDetail());
        resultPrice.setText(phone.getPrice());
        resultFullDetailBrand.setText(phone.getBrand());
        resultFullDetailDisplay.setText(fullDetailDisplay);
        resultFullDetailHardware.setText(fullDetailHardware);
        Picasso.get().load(phone.getImage()).placeholder(R.color.colorWhite).into(resultImageUrl);
    }

    public void postCheckout(View view) {
        if (TextUtils.isEmpty(editTextName.getText().toString().trim()) ||
        TextUtils.isEmpty(editTextAddress.getText().toString().trim()) ||
        TextUtils.isEmpty(editTextPhone.getText().toString().trim()) ||
        TextUtils.isEmpty(editTextEmail.getText().toString().trim())){
            Toast.makeText(view.getContext(), "Can\'t Empty!", Toast.LENGTH_SHORT).show();
        } else if (!isValidEmail(editTextEmail.getText().toString().trim()))
            Toast.makeText(view.getContext(),"Email not valid!", Toast.LENGTH_SHORT).show();
        else if (!isValidPhone(editTextPhone.getText().toString().trim()))
            Toast.makeText(view.getContext(),"Number not valid!", Toast.LENGTH_SHORT).show();
        else if (editTextAddress.getText().toString().trim().length() <= 10)
            Toast.makeText(view.getContext(), "Address must at least 10 character", Toast.LENGTH_SHORT).show();
        else {
            CheckoutEntity checkoutEntity = makeCheckout();
            Intent i = new Intent(OptionActivity.this, CheckoutActivity.class);
            i.putExtra(Key_RegisterActivity, checkoutEntity);
            i.putExtra("phone_Detail", phone.getDetail());
            i.putExtra("phone_Price", phone.getPrice());
            i.putExtra("phone_Image", phone.getImage());
            startActivity(i);
        }
    }

    private CheckoutEntity makeCheckout() {
        String name = editTextName.getText().toString().trim();
        String address = editTextAddress.getText().toString().trim();
        String PhoneNumber = editTextPhone.getText().toString().trim();
        String email = editTextEmail.getText().toString().trim();
        String type = resultType.getText().toString().trim();
        String payment = paymentSpinner.getSelectedItem().toString().trim();

        CheckoutEntity checkoutEntity = new CheckoutEntity(name, address, PhoneNumber, email, payment, type);

        return checkoutEntity;
    }

    public static boolean isValidEmail(CharSequence email) {
        return (Patterns.EMAIL_ADDRESS.matcher(email).matches());
    }

    public static boolean isValidPhone(CharSequence phone) {
        return (Patterns.PHONE.matcher(phone).matches());
    }
}
