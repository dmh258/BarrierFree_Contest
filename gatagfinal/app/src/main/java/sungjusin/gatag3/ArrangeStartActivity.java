package sungjusin.gatag3;

import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;


public class ArrangeStartActivity extends AppCompatActivity {

    private static MediaPlayer mp;

    @Override
    public void onBackPressed() {
        Intent intent = new Intent(ArrangeStartActivity.this, ArrangefirstActivity.class);
        startActivity(intent);
        finish();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_arrangestart);

        mp = MediaPlayer.create(this, R.raw.arrangesound1);
        mp.start();

        Button button_str = (Button) findViewById(R.id.arr_start_btn);
        button_str.setOnClickListener(new View.OnClickListener()  {
            public void onClick(View v) {
                Intent intent = new Intent(ArrangeStartActivity.this, Arrange_fur1Activity. class);
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