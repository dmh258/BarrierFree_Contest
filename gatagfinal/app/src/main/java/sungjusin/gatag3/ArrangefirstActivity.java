package sungjusin.gatag3;

import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import androidx.appcompat.app.AppCompatActivity;


public class ArrangefirstActivity extends AppCompatActivity {

    private static MediaPlayer mp;

    @Override
    public void onBackPressed() {
        Intent intent = new Intent(ArrangefirstActivity.this, MainActivity.class);
        startActivity(intent);
        finish();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_arrangefirst);

        mp = MediaPlayer.create(this, R.raw.arrangesound1);
        mp.start();

        Button button_edit = (Button) findViewById(R.id.input_btn);
        button_edit.setOnClickListener(new View.OnClickListener()  {
            public void onClick(View v) {
                Intent intent = new Intent(ArrangefirstActivity.this, ArrangeStartActivity. class);
                startActivity(intent);
                mp.stop();
                mp.release();
                finish();
            }
        });

        Button button_fin = (Button) findViewById(R.id.delete_btn);
        button_fin.setOnClickListener(new View.OnClickListener()  {
            public void onClick(View v) {

                Intent intent = new Intent(ArrangefirstActivity.this, SettingActivity.class);
                startActivity(intent);
                mp.stop();
                mp.release();
                finish();
            }
        });

        mp.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
            @Override
            public void onCompletion(MediaPlayer mp) {
                mp.stop();
                mp.release();
                finish();
            }
        });
    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
        // MediaPlayer 해지
        if (mp != null) {
            mp.release();
            mp = null;
            finish();
        }
    }
}