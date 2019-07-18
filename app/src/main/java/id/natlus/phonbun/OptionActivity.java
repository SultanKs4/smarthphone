package id.natlus.phonbun;

import androidx.appcompat.app.AppCompatActivity;

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

import java.util.regex.Pattern;

import id.natlus.phonbun.models.Checkout;
import id.natlus.phonbun.models.Phone;

public class OptionActivity extends AppCompatActivity {
    TextView resultType, resultPrice, resultDetail, resultFullDetailDisplay, resultFullDetailBrand, resultFullDetailHardware;
    ImageView resultImageUrl;
    String fullDetailDisplay, fullDetailBrand, fullDetailHardware;
    EditText editTextName, editTextAddress, editTextPhone, editTextEmail;
    Spinner paymentSpinner;

    public static final String Key_RegisterActivity = "Key_RegisterActivity";

    Phone phone;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_option);

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

        String[] typeArray = getResources().getStringArray(R.array.type_phone);
        String[] fullDetailDisplayArray = getResources().getStringArray(R.array.full_detail_phone_display);
        String[] fullDetailHardwareArray = getResources().getStringArray(R.array.full_detail_phone_hardware);
        String[] fullDetailBrandArray = getResources().getStringArray(R.array.full_detail_brand);

        for (int i = 0; i < typeArray.length; i++) {
            if (phone.getType().equals(typeArray[i])){
                fullDetailDisplay = fullDetailDisplayArray[i];
                fullDetailHardware = fullDetailHardwareArray[i];
                if (i >= (typeArray.length - 3)){
                    fullDetailBrand = fullDetailBrandArray[typeArray.length - 3];
                } else{
                    fullDetailBrand = fullDetailBrandArray[i];
                }
                break;
            }
        }

        resultType.setText(phone.getType());
        resultDetail.setText(phone.getDetail());
        resultPrice.setText(phone.getPrice());
        resultFullDetailDisplay.setText(fullDetailDisplay);
        resultFullDetailBrand.setText(fullDetailBrand);
        resultFullDetailHardware.setText(fullDetailHardware);
        Picasso.get().load(phone.getImage()).into(resultImageUrl);
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
            Toast.makeText(view.getContext(),"Phone not valid!", Toast.LENGTH_SHORT).show();
        else if (editTextAddress.getText().toString().trim().length() <= 10)
            Toast.makeText(view.getContext(), "Address must at least 10 character", Toast.LENGTH_SHORT).show();
        else {
            String name = editTextName.getText().toString().trim();
            String address = editTextAddress.getText().toString().trim();
            String PhoneNumber = editTextPhone.getText().toString().trim();
            String email = editTextEmail.getText().toString().trim();
            String payment = paymentSpinner.getSelectedItem().toString().trim();

            Checkout checkout = new Checkout(phone.getType(), phone.getPrice(), phone.getImage(),
                    phone.getDetail(), name, address, PhoneNumber, email, payment);

            Intent i = new Intent(OptionActivity.this, CheckoutActivity.class);
            i.putExtra(Key_RegisterActivity, checkout);
            startActivity(i);
        }
    }

    public static boolean isValidEmail(CharSequence email) {
        return (Patterns.EMAIL_ADDRESS.matcher(email).matches());
    }

    public static boolean isValidPhone(CharSequence phone) {
        return (Patterns.PHONE.matcher(phone).matches());
    }
}
