package id.natlus.phonbun;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import java.util.List;

import id.natlus.phonbun.adapters.PhoneAdapter;
import id.natlus.phonbun.db.PhoneEntity;
import id.natlus.phonbun.viewmodel.MainViewModel;

public class MainActivity extends AppCompatActivity implements PhoneAdapter.OnPhoneClickListener {
    private final  String TAG = MainActivity.class.getName();
    public static final String Key_RegisterActivity = "Key_RegisterActivity";
    public RecyclerView recyclerView;
    public PhoneAdapter phoneAdapter;
    private MainViewModel mainViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mainViewModel = ViewModelProviders.of(this).get(MainViewModel.class);

        phoneAdapter = new PhoneAdapter(this);
        phoneAdapter.setListener(this);
        recyclerView = findViewById(R.id.rvPhone);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(phoneAdapter);

        mainViewModel.getPhoneList().observe(this, new Observer<List<PhoneEntity>>() {
            @Override
            public void onChanged(List<PhoneEntity> phoneEntities) {
                phoneAdapter.setPhoneList(phoneEntities);
            }
        });
    }

    @Override
    public void onClick(View view, int position) {
        PhoneEntity phoneEntity = phoneAdapter.getPhoneEntityList().get(position);
        Intent i = new Intent(MainActivity.this, OptionActivity.class);
        i.putExtra(Key_RegisterActivity, phoneEntity);
        startActivity(i);
        Toast.makeText(getApplicationContext(), phoneEntity.getType(), Toast.LENGTH_SHORT).show();
    }

    public void refresh(View view) {
        mainViewModel.getPhoneList().observe(this, new Observer<List<PhoneEntity>>() {
            @Override
            public void onChanged(List<PhoneEntity> phoneEntities) {
                phoneAdapter.setPhoneList(phoneEntities);
            }
        });
    }
}
