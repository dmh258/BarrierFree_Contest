package sungjusin.gatag;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import sungjusin.gatag3.MainActivity;
import sungjusin.gatag3.R;
import sungjusin.gatag3.Search2Activity;
import sungjusin.gatag3.SearchActivity;
import sungjusin.gatag3.SearchAdapter;

public class blue_tooth_connect extends AppCompatActivity {

    private static MediaPlayer mp;

    private List<String> list;          // 데이터를 넣은 리스트변수
    private ListView listView;     // 검색을 보여줄 리스트변수
    private SearchAdapter adapter;      // 리스트뷰에 연결할 아답터
    private ArrayList<String> arraylist;

    @Override
    public void onBackPressed() {
        Intent intent = new Intent(blue_tooth_connect.this, MainActivity.class);
        startActivity(intent);
        finish();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_blue_tooth_connect);

        listView = (ListView) findViewById(R.id.listView);

        Intent intentt = getIntent();
        Intent intent2 = getIntent();
        final String myData = intentt.getStringExtra("sfName2");
        final String obj_Data = intent2.getStringExtra("sfName4");

        list = new ArrayList<String>();

        List<String> fur_list = new ArrayList<String>();
        List<String> obj_list = new ArrayList<String>();

        // 검색에 사용할 데이터를 미리 저장한다.
        File directory = new File(getFilesDir() + "/database");
        File[] files = directory.listFiles();
        for (int i = 0; i < files.length; i++) {
            try {
                BufferedReader br = new BufferedReader(new FileReader(getFilesDir() + "/database" + "/" + files[i].getName()));
                String str = null;
                while (((str = br.readLine()) != null)) {
                    obj_list.add(str);
                    fur_list.add(files[i].getName());
                }
                br.close();

            } catch (FileNotFoundException e) {
                e.printStackTrace();
                Toast.makeText(this, "File not Found", Toast.LENGTH_SHORT).show();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View v, int position, long id) {
                Intent intent = new Intent(blue_tooth_connect.this, bluetooth_final.class);

                String obj_temp = list.get(position);

                intent.putExtra("key", obj_temp);
                intent.putExtra("sfName3", myData);

                startActivity(intent);
            }
        }) ;

        // 리스트의 모든 데이터를 arraylist에 복사한다.// list 복사본을 만든다.
        list.addAll(obj_list);
        arraylist = new ArrayList<String>();
        arraylist.addAll(list);

        // 리스트에 연동될 아답터를 생성한다.
        adapter = new SearchAdapter(list, this);

        // 리스트뷰에 아답터를 연결한다.
        listView.setAdapter(adapter);

        mp = MediaPlayer.create(this, R.raw.searchsound);
        mp.start();

        mp.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
            @Override
            public void onCompletion(MediaPlayer mp) {
                mp.stop();
                mp.release();
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
        }
    }
}
