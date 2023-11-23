package sungjusin.gatag3;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import android.Manifest;
import android.app.Activity;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.content.SharedPreferences;
import android.widget.TextView;
import android.widget.Toast;
import java.io.File;
import java.util.ArrayList;


public class MainActivity extends AppCompatActivity {

    private static MediaPlayer mp;
    String sfName2 = "myFile";

    private long time= 0;


    public static ArrayList<Activity> actList = new ArrayList<Activity>();

    @Override
    public void onBackPressed(){
        if(System.currentTimeMillis()-time>=2000){
            time=System.currentTimeMillis();
            Toast.makeText(getApplicationContext(),"뒤로 버튼을 한번 더 누르면 종료합니다.",Toast.LENGTH_SHORT).show();
        }else if(System.currentTimeMillis()-time<2000){
            ActivityCompat.finishAffinity(this);
        }

    }

    //권한얻기
    final static int PERMISSION_REQUEST_CODE = 1000;

    private void permissionCheck(){
        if (android.os.Build.VERSION.SDK_INT >= 23){
            int permissionCheck = ContextCompat.checkSelfPermission(this, Manifest.permission.READ_EXTERNAL_STORAGE);
            ArrayList<String> arrayPermission = new ArrayList<String>();

            if (permissionCheck != PackageManager.PERMISSION_GRANTED){
                arrayPermission.add(Manifest.permission.READ_EXTERNAL_STORAGE);
            }

            permissionCheck = ContextCompat.checkSelfPermission(this,Manifest.permission.WRITE_EXTERNAL_STORAGE);
            if (permissionCheck != PackageManager.PERMISSION_GRANTED){
                arrayPermission.add(Manifest.permission.WRITE_EXTERNAL_STORAGE);
            }

            if(arrayPermission.size() > 0){
                String strArray[] = new String[arrayPermission.size()];
                strArray = arrayPermission.toArray(strArray);
                ActivityCompat.requestPermissions(this, strArray, PERMISSION_REQUEST_CODE);
            }
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResult) {
        switch (requestCode) {
            case PERMISSION_REQUEST_CODE: {
                if (grantResult.length < 1) {
                    super.onRequestPermissionsResult(requestCode, permissions, grantResult);
                    return;
                }

                for (int i = 0; i < grantResult.length; i++) {
                    if (grantResult[i] != PackageManager.PERMISSION_GRANTED) {
                        finish();
                        return;
                    }
                }
            }
            break;
        }
        super.onRequestPermissionsResult(requestCode, permissions, grantResult);
    }


    @Override
    protected void onCreate(Bundle saveInstanceState) {
        super.onCreate(saveInstanceState);
        setContentView(R.layout.activity_main);
        final String data2;

        SharedPreferences sf2 = getSharedPreferences(sfName2, 0);
        SharedPreferences.Editor editor2 = sf2.edit();//저장하려면 editor가 필요
        String str2 = sf2.getString("name", ""); // 키값으로 꺼냄
        TextView topline2 = (TextView)findViewById(R.id.topline2);


        //Update_note customDialog = new Update_note(MainActivity.this);
        //customDialog.callFunction(topline2);


        Intent intent = getIntent();
        final String myData = intent.getStringExtra("sfName");

        if (myData == null) {
            editor2.commit();
        }

        else{
            str2 = myData; // 사용자가 입력한 값
            editor2.putString("name", str2); // 입력
            editor2.commit(); // 파일에 최종 반영함
        }
        data2 = str2;

        mp = MediaPlayer.create(this, R.raw.mainsound);
        mp.start();
        mp.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
            @Override
            public void onCompletion(MediaPlayer mp) {
                mp.stop();

            }
        });

        Button button = (Button) findViewById(R.id.search_btn);
        button.setOnClickListener(new View.OnClickListener()   {
            public void onClick(View v) {

                File fursaveFile = new File(getFilesDir().getAbsolutePath() + "/database");

                if(fursaveFile.exists()) {
                    Intent intent = new Intent(MainActivity.this, SearchActivity.class);
                    intent.putExtra("sfName2", data2);
                    startActivity(intent);
                    mp.stop();
                    finish();
                }
                else{
                    Toast.makeText(getApplicationContext(),"입력된 정보가 없습니다.\n가구와 물건을 먼저 입력해주세요.",Toast.LENGTH_SHORT).show();
                }
            }
        });

        Button button1 = (Button) findViewById(R.id.arrange_btn);
        button1.setOnClickListener(new View.OnClickListener()  {
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, ArrangefirstActivity. class);
                startActivity(intent);
                mp.stop();
                finish();
            }   });

        Button button2 = (Button) findViewById(R.id.setting_btn);
        button2.setOnClickListener(new View.OnClickListener()  {
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, SettingActivity. class);
                startActivity(intent);
                mp.stop();
                finish();
            }   });

        Button button3 = (Button) findViewById(R.id.manual);
        button3.setOnClickListener(new View.OnClickListener()  {
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, manual1. class);
                startActivity(intent);
                mp.stop();
                finish();
            }   });

        Button button4 = (Button) findViewById(R.id.personal_information);
        button4.setOnClickListener(new View.OnClickListener()  {
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, personal_information_handling_policy. class);
                startActivity(intent);
                mp.stop();
                finish();
            }   });

        Button button5 = (Button) findViewById(R.id.bluetooth_search_btn);
        button5.setOnClickListener(new View.OnClickListener()  {
            public void onClick(View v) {
                Toast.makeText(getApplicationContext(),"서비스 준비중입니다.",Toast.LENGTH_LONG).show();
            }   });
    }

    @Override
    protected void onPause() {
        super.onPause();
        mp.stop();
    }
}