package id.natlus.phonbun;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import id.natlus.phonbun.adapters.PhoneAdapter;
import id.natlus.phonbun.models.Phone;

public class MainActivity extends AppCompatActivity implements PhoneAdapter.OnPhoneClickListener {
    private final  String TAG = MainActivity.class.getName();
    public static final String Key_RegisterActivity = "Key_RegisterActivity";
    public RecyclerView recyclerView;
    public PhoneAdapter phoneAdapter;
    public RecyclerView.LayoutManager layoutManager;
    public String[] typeArray, detailArray, priceArray, imageArray;
    public List<Phone> phoneList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recyclerView = findViewById(R.id.rvPhone);

        typeArray = getResources().getStringArray(R.array.type_phone);
        detailArray = getResources().getStringArray(R.array.detail_phone);
        priceArray = getResources().getStringArray(R.array.price_phone);
        imageArray = getResources().getStringArray(R.array.image_phone);

        for (int i = 0; i < typeArray.length; i++) {
            phoneList.add(new Phone(typeArray[i], priceArray[i], imageArray[i], detailArray[i]));
        }

        phoneAdapter = new PhoneAdapter(phoneList);
        phoneAdapter.setListener(this);
        layoutManager = new LinearLayoutManager(getApplicationContext());
        recyclerView.setAdapter(phoneAdapter);
        recyclerView.setLayoutManager(layoutManager);
    }

    @Override
    public void onClick(View view, int position) {
        Phone phone = phoneList.get(position);
        Intent i = new Intent(MainActivity.this, OptionActivity.class);
        i.putExtra(Key_RegisterActivity, phone);
        startActivity(i);
    }
}
