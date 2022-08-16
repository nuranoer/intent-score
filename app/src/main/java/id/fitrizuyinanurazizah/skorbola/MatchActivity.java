package id.fitrizuyinanurazizah.skorbola;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

public class MatchActivity extends AppCompatActivity implements View.OnClickListener {

    TextView txtHome;
    TextView txtAway;
    int scoreHome = 0;
    int scoreAway = 0;
    ImageView homeLogo;
    ImageView awayLogo;

    Button btnAddHomeScore;
    Button btnAddAwayScore;
    Button btnResult;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_match);

        txtHome = findViewById(R.id.txt_home);
        txtAway = findViewById(R.id.txt_away);

        btnAddHomeScore = findViewById(R.id.btn_add_home);
        btnAddAwayScore = findViewById(R.id.btn_add_away);
        btnResult = findViewById(R.id.btn_result);
        btnAddHomeScore.setOnClickListener(addPointHome());
        btnAddAwayScore.setOnClickListener(addPointAway());
        btnResult.setOnClickListener(this);

        //1.Menampilkan detail match sesuai data dari main activity
        Intent terima = getIntent();

        String home = terima.getStringExtra("home_team");
        String away = terima.getStringExtra("away_team");

        txtHome.setText(home);
        txtAway.setText(away);

        //TODO
        //1.Menampilkan detail match sesuai data dari main activity
        //2.Tombol add score menambahkan satu angka dari angka 0, setiap kali di tekan
        //3.Tombol Cek Result menghitung pemenang dari kedua tim dan mengirim nama pemenang ke ResultActivity, jika seri di kirim text "Draw"
    }

    @Override
    public void onClick(View view) {
        //3.Tombol Cek Result menghitung pemenang dari kedua tim dan mengirim nama pemenang ke ResultActivity, jika seri di kirim text "Draw"
        Intent i = new Intent(MatchActivity.this, ResultActivity.class);
        if(scoreHome > scoreAway){
            i.putExtra("home_team", txtHome);
        }
        else if(scoreHome<scoreAway){
            i.putExtra("away_team", txtAway);
        }
        startActivity(i);

    }
    //2.Tombol add score menambahkan satu angka dari angka 0, setiap kali di tekan
    public void displayForHomeTeam(int score){
        TextView scoreView = (TextView) findViewById(R.id.score_home);
        scoreView.setText(String.valueOf(score));
    }
    public void addPointHome(View v){
        scoreHome = scoreHome + 1;
        displayForHomeTeam(scoreHome);
    }
    public void displayForAwayTeam(int score){
        TextView scoreView = (TextView) findViewById(R.id.score_away);
        scoreView.setText(String.valueOf(score));
    }
    public void addPointAway(View v){
        scoreAway = scoreAway + 1;
        displayForAwayTeam(scoreAway);
    }
}
