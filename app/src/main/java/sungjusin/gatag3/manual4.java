package sungjusin.gatag3;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class manual4 extends AppCompatActivity {

    @Override
    public void onBackPressed(){
        Intent intent = new Intent(manual4.this, manual3. class);
        startActivity(intent);
        finish();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_manual4);

        Button button = (Button) findViewById(R.id.back);
        Button button2 = (Button) findViewById(R.id.next);
        button.setOnClickListener(new View.OnClickListener()  {
            public void onClick(View v) {
                Intent intent = new Intent(manual4.this, manual3. class);
                startActivity(intent);
                finish();
            }   });

        button2.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent intent = new Intent(manual4.this, manual5.class);
                startActivity(intent);
                finish();
            } });
    }
}
