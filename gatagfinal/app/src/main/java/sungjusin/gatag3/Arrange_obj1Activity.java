package sungjusin.gatag3;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;


public class Arrange_obj1Activity extends AppCompatActivity {

    @Override
    public void onBackPressed() {
        Toast.makeText(getApplicationContext(),"입력완료를 누르면 입력을 중단합니다.",Toast.LENGTH_SHORT).show();
    }

    @Override
    protected void onCreate(Bundle saveInstanceState) {
        super.onCreate(saveInstanceState);
        setContentView(R.layout.activity_arrangeobj1);

        Button button_obj_finish = (Button) findViewById(R.id.finish_obj);
        Button button_obj_next = (Button) findViewById(R.id.next_obj);
        final EditText editText_obj = (EditText)findViewById(R.id.editobj1);
        final CheckBox checkbox = (CheckBox) findViewById(R.id.checkBox1);
        TextView textView_obj1 = (TextView)findViewById(R.id.textView_obj1);

        Intent intent = getIntent();
        final String str = intent.getStringExtra("furniture_name");

        textView_obj1.setText(str + "에 위치한 물건의 이름을 입력해주세요.");


        //중복확인
        final List<String> same_test = new ArrayList<String>();
        File directory = new File(getFilesDir() + "/list");
        File[] files = directory.listFiles();
        try {
            BufferedReader br = new BufferedReader(new FileReader(getFilesDir() + "/list/objdatabase.txt"));
            String name1 = null;
            while (((name1 = br.readLine()) != null)) {
                same_test.add(name1);
            }
            br.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        button_obj_next.setOnClickListener(new View.OnClickListener()  {
            public void onClick(View v) {
                if (editText_obj.getText().toString().length() > 0){
                File fursaveFile = new File(getFilesDir().getAbsolutePath() + "/database"); //가구 텍스트 파일을 담고 있는 폴더 저장 경로
                File fursaveList = new File(getFilesDir().getAbsolutePath() + "/list"); // 위험 물건 텍스트 파일과 모든 물건을 담고 있는 파일을 담고 있는 폴더 저장 경로

                String str_obj = editText_obj.getText().toString();

                //물건 이름 중복체크
                int same = 0;
                for(int i=0; i<same_test.size(); i++){
                    if(str_obj.equals(same_test.get(i))){
                        same = 1;
                    }
                }

                if(same == 1){
                    Toast.makeText(getApplicationContext(), "물건 이름이 중복입니다.", Toast.LENGTH_SHORT).show();
                }
                else{
                    try {
                        BufferedWriter buf = new BufferedWriter(new FileWriter(fursaveFile+ "/"+str+".txt", true));
                        BufferedWriter buf2 = new BufferedWriter(new FileWriter(fursaveList + "/"+str+"warning.txt", true));
                        buf.append(str_obj); // 파일 쓰기

                        if (checkbox.isChecked() == true) {
                            buf2.append("1");
                            buf2.newLine();
                            checkbox.setChecked(false);
                        }
                        if ( checkbox.isChecked() == false) {
                            buf2.append("0");
                            buf2.newLine();
                            checkbox.setChecked(false);
                        }
                        buf.newLine();
                        buf.close();
                        buf2.close();
                    } catch (FileNotFoundException e) {
                        e.printStackTrace();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }

                    try {
                        BufferedWriter buf = new BufferedWriter(new FileWriter(fursaveList+ "/objdatabase.txt", true));
                        buf.append(str_obj);
                        buf.newLine();
                        buf.close();
                    } catch (FileNotFoundException e) {
                        e.printStackTrace();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    editText_obj.setText("");
                }
            }
            }
        });

        button_obj_finish.setOnClickListener(new View.OnClickListener()  {
            public void onClick(View v) {
                if (editText_obj.getText().toString().length() > 0) {
                    EditText editText_obj = (EditText) findViewById(R.id.editobj1);
                    File fursaveFile = new File(getFilesDir().getAbsolutePath() + "/database"); //가구 텍스트 파일을 담고 있는 폴더 저장 경로
                    File fursaveList = new File(getFilesDir().getAbsolutePath() + "/list");  // 위험 물건 텍스트 파일과 모든 물건을 담고 있는 파일을 담고 있는 폴더 저장 경로

                    String str_obj = editText_obj.getText().toString();

                    //중복체크
                    int same = 0;
                    for (int i = 0; i < same_test.size(); i++) {
                        if (str_obj.equals(same_test.get(i))) {
                            same = 1;
                        }
                    }

                    if (same == 1) {
                        Toast.makeText(getApplicationContext(), "물건 이름이 중복입니다.", Toast.LENGTH_SHORT).show();
                    } else {
                        try {
                            BufferedWriter buf = new BufferedWriter(new FileWriter(fursaveFile + "/" + str + ".txt", true));
                            BufferedWriter buf2 = new BufferedWriter(new FileWriter(fursaveList + "/" + str + "warning.txt", true));
                            buf.append(str_obj); // 파일 쓰기
                            if (checkbox.isChecked() == true) {
                                buf2.append("1");
                                buf2.newLine();
                                checkbox.setChecked(false);
                            }
                            if (checkbox.isChecked() == false) {
                                buf2.append("0");
                                buf2.newLine();
                                checkbox.setChecked(false);
                            }
                            buf.newLine();
                            buf.close();
                            buf2.close();
                        } catch (FileNotFoundException e) {
                            e.printStackTrace();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }

                        try {
                            BufferedWriter buf = new BufferedWriter(new FileWriter(fursaveList + "/objdatabase.txt", true));
                            buf.append(str_obj);
                            buf.newLine();
                            buf.close();
                        } catch (FileNotFoundException e) {
                            e.printStackTrace();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }

                        editText_obj.setText("");
                    }
                }
                Intent intent = new Intent(Arrange_obj1Activity.this, Arrange_fur1Activity.class);
                startActivity(intent);
                finish();
            }
        });
    }
}