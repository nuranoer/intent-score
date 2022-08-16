package id.fitrizuyinanurazizah.skorbola;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;
import java.io.IOException;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
     EditText homeTeam;
     EditText awayTeam;
     ImageView homeLogo;
     ImageView awayLogo;
     Button btnTeam;

    private static final String TAG = MainActivity.class.getCanonicalName();
    private static final int GALLERY_REQUEST_CODE = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getSupportActionBar().hide();

        homeTeam = findViewById(R.id.home_team);
        awayTeam = findViewById(R.id.away_team);
        homeLogo = findViewById(R.id.home_logo);
        awayLogo = findViewById(R.id.away_logo);


        btnTeam = (Button) findViewById(R.id.btn_team);
        btnTeam.setOnClickListener(this);

        //TODO
        //Fitur Main Activity
        //1. Validasi Input Home Team
        //2. Validasi Input Away Team
        //3. Ganti Logo Home Team
        //4. Ganti Logo Away Team
        //5. Next Button Pindah Ke MatchActivity
    }

        @Override
        public void onClick(View view) {
            String home = homeTeam.getText().toString();
            String away = awayTeam.getText().toString();

            //1. Validasi Input Home Team
            if(home.trim().equals("")){
                homeTeam.setError("Team Name is Required");
            }
            //2. Validasi Input Away Team
            else if(away.trim().equals((""))){
                awayTeam.setError("Team Name is required");
            }
            //5. Next Button Pindah Ke MatchActivity
            else {
                Intent kirim = new Intent(MainActivity.this, MatchActivity.class);
                kirim.putExtra("home_team", home);
                kirim.putExtra("away_team", away);
                kirim.putExtra("home_logo", R.id.home_logo);
                kirim.putExtra("away_logo", R.id.away_logo);
                startActivity(kirim);
            }

        }

    //3. Ganti Logo Home Team
    public void handleChangeHome(View view) {
        Intent intent = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
        startActivityForResult(intent, GALLERY_REQUEST_CODE);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        if (resultCode == RESULT_CANCELED) {
            return;
        }

        if (requestCode == GALLERY_REQUEST_CODE) {
            if (data != null) {
                try {
                    Uri imageUri = data.getData();
                    Bitmap bitmap = MediaStore.Images.Media.getBitmap(this.getContentResolver(), imageUri);
                    homeLogo.setImageBitmap(bitmap);
                    awayLogo.setImageBitmap(bitmap);
                } catch (IOException e) {
                    Toast.makeText(this, "Can't load image", Toast.LENGTH_SHORT).show();
                    Log.e(TAG, e.getMessage());
                }
            }
        }
        super.onActivityResult(requestCode, resultCode, data);
    }
    //4. Ganti Logo Away Team
    public void handleChangeAway(View view) {
        Intent intent = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
        startActivityForResult(intent, GALLERY_REQUEST_CODE);
    }
}
