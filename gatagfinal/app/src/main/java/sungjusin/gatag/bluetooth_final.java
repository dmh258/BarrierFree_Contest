package sungjusin.gatag;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import sungjusin.gatag3.MainActivity;
import sungjusin.gatag3.R;

import static android.view.View.GONE;
import static android.view.View.VISIBLE;

public class bluetooth_final extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bluetooth_final);

        TextView obj_name = (TextView)findViewById(R.id.obj_name);
        final TextView stop_explain = (TextView)findViewById(R.id.stop_explain);
        final TextView start_explain = (TextView)findViewById(R.id.start_explain);
        Button start_btn = (Button)findViewById(R.id.start_btn);
        Button stop_btn = (Button)findViewById(R.id.stop_btn);

        Intent intent = getIntent();
        final String obj_data = intent.getStringExtra("key");

        stop_explain.setVisibility(View.INVISIBLE);

        start_btn.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                start_explain.setVisibility(GONE);
                stop_explain.setVisibility(VISIBLE);
            }
        });

        stop_btn.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                start_explain.setVisibility(VISIBLE);
                stop_explain.setVisibility(View.INVISIBLE);
            }
        });

        obj_name.setText("물건: " + obj_data);
    }
}
