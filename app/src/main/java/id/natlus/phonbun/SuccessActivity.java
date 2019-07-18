package id.natlus.phonbun;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class SuccessActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_success);
    }

    public void postHome(View view) {
        Intent i = new Intent(SuccessActivity.this, MainActivity.class);
        startActivity(i);
        finish();
    }
}
