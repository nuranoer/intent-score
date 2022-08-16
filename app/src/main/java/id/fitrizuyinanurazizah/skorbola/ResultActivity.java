package id.fitrizuyinanurazizah.skorbola;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

public class ResultActivity extends AppCompatActivity {
    TextView winner;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result);

        Intent receive = getIntent();

        String winner = receive.getStringExtra("home_score");
        String winner2 = receive.getStringExtra("away_score");


    }
}
