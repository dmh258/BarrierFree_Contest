package sungjusin.gatag3;

import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;


public class ArrangeFinishActivity extends AppCompatActivity {

    private static MediaPlayer mp;

    @Override
    public void onBackPressed() {
        Intent intent = new Intent(ArrangeFinishActivity.this, MainActivity.class);
        startActivity(intent);
        finish();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_arrangefinish);

        mp = MediaPlayer.create(this, R.raw.arrangesound2);
        mp.start();

        Button button2 = (Button) findViewById(R.id.rearrange_btn1);
        button2.setOnClickListener(new View.OnClickListener()  {
            public void onClick(View v) {
                Intent intent = new Intent(ArrangeFinishActivity.this, ArrangeStartActivity. class);
                startActivity(intent);

                mp.stop();
                finish();
            }   });

        Button button1 = (Button) findViewById(R.id.finish_btn1);
        button1.setOnClickListener(new View.OnClickListener()  {
            public void onClick(View v) {
                Intent intent = new Intent(ArrangeFinishActivity.this, MainActivity. class);
                startActivity(intent);

                mp.stop();
                finish();
            }   });

        mp.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
            @Override
            public void onCompletion(MediaPlayer mp) {
                mp.stop();
                mp.release();
            }
        });
    }

    @Override
    protected void onStop() {
        super.onStop();
        mp.stop();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        // MediaPlayer 해지
        if (mp != null) {
            mp.release();
            mp = null;
        }
        finish();
    }
}