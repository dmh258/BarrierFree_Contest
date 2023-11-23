package sungjusin.gatag3;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class Search2Activity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search2);

        Button btn = (Button)findViewById(R.id.delete_btn);

        TextView fur_name = (TextView)findViewById(R.id.fur_name);
        TextView obj_name = (TextView)findViewById(R.id.obj_name);

        Intent intent = getIntent();
        final String obj_data = intent.getStringExtra("key");

        //가구이름 가져오기
        String fur_data = null;
        File directory = new File(getFilesDir() + "/database");
        File[] files = directory.listFiles();
        for (int i = 0; i < files.length; i++) {
            try {
                BufferedReader br3 = new BufferedReader(new FileReader(getFilesDir() + "/database/" + files[i].getName()));
                String str = null;
                while (((str = br3.readLine()) != null)) {
                    if(obj_data.equals(str)){
                        fur_data = files[i].getName();
                        fur_data = fur_data.substring(0, fur_data.length()-4);
                    }
                }
                br3.close();
            } catch (FileNotFoundException e) {
                e.printStackTrace();
                Toast.makeText(this, "File not Found1", Toast.LENGTH_SHORT).show();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        //텍스트뷰 추가
        fur_name.setText(fur_data);
        obj_name.setText("물건: " + obj_data);


        //위험물건 확인
        int warn = 0;
        try {
            BufferedReader br = new BufferedReader(new FileReader(getFilesDir() + "/list/" + fur_data + "warning.txt"));
            String str = null;
            while (((str = br.readLine()) != null)) {
                if("1".equals(str)){
                    warn = 1;
                }
            }
            br.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            Toast.makeText(this, "File not Found2", Toast.LENGTH_SHORT).show();
        } catch (IOException e) {
            e.printStackTrace();
        }

        if(warn == 1){
            //위험 팝업
            WarningpopupActivity customDialog = new WarningpopupActivity(Search2Activity.this);
            // 커스텀 다이얼로그를 호출한다.
            // 커스텀 다이얼로그의 결과를 출력할 TextView를 매개변수로 같이 넘겨준다.
            customDialog.callFunction(fur_name);
        }

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = getIntent();
                final String myData = intent.getStringExtra("sfName3");
                System.out.println(myData);

                Intent intent2 = new Intent(Intent.ACTION_VIEW, Uri.parse("tel:" + myData));
                startActivity(intent2);
            }
        });
    }
}