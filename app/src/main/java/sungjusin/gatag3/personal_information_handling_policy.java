package sungjusin.gatag3;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class personal_information_handling_policy extends AppCompatActivity {

    @Override
    public void onBackPressed(){
        Intent intent = new Intent(personal_information_handling_policy.this, MainActivity. class);
        startActivity(intent);
        finish();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_personal_information_handling_policy);

        Button button = (Button) findViewById(R.id.back);
        button.setOnClickListener(new View.OnClickListener()  {
            public void onClick(View v) {
                Intent intent = new Intent(personal_information_handling_policy.this, MainActivity. class);
                startActivity(intent);
                finish();
            }   });
    }
}
