package sungjusin.gatag3;

import android.content.Intent;
import android.content.SharedPreferences;
import android.media.MediaPlayer;
import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;
import java.io.File;

public class SettingActivity  extends AppCompatActivity {

    private static MediaPlayer mp;
    EditText editText;
    String sfName = "myFile";

    @Override
    public void onBackPressed() {
        Intent intent = new Intent(SettingActivity.this, MainActivity.class);
        startActivity(intent);
        finish();
    }

    private long time= 0;
    //삭제코드
    public void ResetButton(View v) {
        File fursaveFile = new File(getFilesDir().getAbsolutePath() + "/database");

        if(!fursaveFile.exists()) {
            Toast.makeText(getApplicationContext(),"입력된 정보가 없습니다.\n가구와 물건을 먼저 입력해주세요.",Toast.LENGTH_SHORT).show();
        }
        else{
        if(System.currentTimeMillis()-time>=2000){
            time=System.currentTimeMillis();
            Toast.makeText(getApplicationContext(),"버튼을 한 번 더 누르면 모든 입력정보가 초기화됩니다.",Toast.LENGTH_SHORT).show();
        }else if(System.currentTimeMillis()-time<2000){
            File[] files = fursaveFile.listFiles();
            for (int i = 0; i < files.length; i++) {
                files[i].delete();
            }
            File fursaveFile2 = new File(getFilesDir().getAbsolutePath() + "/list");
            File[] files2 = fursaveFile2.listFiles();
            for (int i = 0; i < files2.length; i++) {
                files2[i].delete();
            }
            Toast.makeText(getApplicationContext(),"모든 입력정보를 초기화했습니다.",Toast.LENGTH_SHORT).show();
        }
    }}

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_setting);

        mp = MediaPlayer.create(this, R.raw.settingsound);
        mp.start();
        mp.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
            @Override
            public void onCompletion(MediaPlayer mp) {
                mp.stop();
            }
        });

        editText = (EditText)findViewById(R.id.editText);

        // 지난번 저장해놨던 사용자 입력값을 꺼내서 보여주기
        SharedPreferences sf = getSharedPreferences(sfName, 0);
        String str = sf.getString("name", ""); // 키값으로 꺼냄
        editText.setText(str); // EditText에 반영함
    }

    @Override
    protected void onStop() {
        super.onStop();
        String str = editText.getText().toString(); // 사용자가 입력한 값
        Intent intent = new Intent(SettingActivity.this, MainActivity.class);
        intent.putExtra("sfName", str);
        // Activity 가 종료되기 전에 저장한다
        // SharedPreferences 에 설정값(특별히 기억해야할 사용자 값)을 저장하기
        SharedPreferences sf = getSharedPreferences(sfName, 0);
        SharedPreferences.Editor editor = sf.edit();//저장하려면 editor가 필요
        editor.putString("name", str); // 입력
        editor.commit(); // 파일에 최종 반영함

        mp.stop();
        finish();
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