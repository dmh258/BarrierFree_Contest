package sungjusin.gatag3;

import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
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

public class SearchActivity extends AppCompatActivity {

    private static MediaPlayer mp;

    private List<String> list;          // 데이터를 넣은 리스트변수
    private ListView listView;     // 검색을 보여줄 리스트변수
    private EditText editSearch;        // 검색어를 입력할 Input 창
    private SearchAdapter adapter;      // 리스트뷰에 연결할 아답터
    private ArrayList<String> arraylist;

    @Override
    public void onBackPressed() {
        Intent intent = new Intent(SearchActivity.this, MainActivity.class);
        startActivity(intent);
        finish();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);


        editSearch = (EditText) findViewById(R.id.editsearch);
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
                Intent intent = new Intent(SearchActivity.this, Search2Activity.class);

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

        // input창에 검색어를 입력시 "addTextChangedListener" 이벤트 리스너를 정의한다.
        editSearch.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
            }

            @Override
            public void afterTextChanged(Editable editable) {
                // input창에 문자를 입력할때마다 호출된다.
                // search 메소드를 호출한다.
                String text = editSearch.getText().toString();
                search(text);
            }
        });

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

    // 검색을 수행하는 메소드
    public void search(String charText) {

        // 문자 입력시마다 리스트를 지우고 새로 뿌려준다.
        list.clear();

        // 문자 입력이 없을때는 모든 데이터를 보여준다.
        if (charText.length() == 0) {
            list.addAll(arraylist);
        }
        // 문자 입력을 할때..
        else
        {
            // 리스트의 모든 데이터를 검색한다.
            for(int i = 0;i < arraylist.size(); i++)
            {
                // arraylist의 모든 데이터에 입력받은 단어(charText)가 포함되어 있으면 true를 반환한다.
                if (arraylist.get(i).toLowerCase().contains(charText))
                {
                    // 검색된 데이터를 리스트에 추가한다.
                    list.add(arraylist.get(i));
                }
            }
        }
        // 리스트 데이터가 변경되었으므로 아답터를 갱신하여 검색된 데이터를 화면에 보여준다.
        adapter.notifyDataSetChanged();
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