package id.natlus.phonbun;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.widget.TextView;

import java.util.List;

import id.natlus.phonbun.adapters.CheckoutAdapter;
import id.natlus.phonbun.db.CheckoutEntity;
import id.natlus.phonbun.viewmodel.MainViewModel;

public class HistoryActivity extends AppCompatActivity {
    public RecyclerView recyclerView;
    public CheckoutAdapter checkoutAdapter;
    private MainViewModel mainViewModel;
    private List<CheckoutEntity> checkoutEntity;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_history);

        mainViewModel = ViewModelProviders.of(this).get(MainViewModel.class);

        checkoutAdapter = new CheckoutAdapter(this);
        recyclerView = findViewById(R.id.history_rvHistory);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(checkoutAdapter);
        mainViewModel.getCheckoutList().observe(this, new Observer<List<CheckoutEntity>>() {
            @Override
            public void onChanged(List<CheckoutEntity> checkoutEntities) {
                checkoutAdapter.setCheckoutEntityList(checkoutEntities);
                checkoutEntity = checkoutEntities;
            }
        });
    }
}
