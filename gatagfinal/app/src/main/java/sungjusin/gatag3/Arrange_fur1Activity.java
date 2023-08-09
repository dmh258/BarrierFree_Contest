package sungjusin.gatag3;

        import androidx.appcompat.app.AppCompatActivity;

        import android.content.Intent;
        import android.os.Bundle;
        import android.text.Editable;
        import android.text.TextWatcher;
        import android.view.View;
        import android.widget.Button;
        import android.widget.EditText;
        import android.widget.Toast;

        import java.io.BufferedWriter;
        import java.io.File;
        import java.io.FileNotFoundException;
        import java.io.FileWriter;
        import java.io.IOException;


public class Arrange_fur1Activity extends AppCompatActivity {
    @Override
    public void onBackPressed() {
        Toast.makeText(getApplicationContext(),"입력완료를 누르면 입력을 중단합니다.",Toast.LENGTH_SHORT).show();
    }

    @Override
    protected void onCreate(Bundle saveInstanceState) {
        super.onCreate(saveInstanceState);
        setContentView(R.layout.activity_arrange_fur1);

        Button button_fur_finish = (Button) findViewById(R.id.finish_fur);
        EditText editTextNew = (EditText) findViewById(R.id.editsearch) ;
        Button button_fur_next = (Button) findViewById(R.id.next_fur);

        button_fur_next.setEnabled(false) ;

        editTextNew.addTextChangedListener(new TextWatcher() {
            @Override
            public void afterTextChanged(Editable editTextNew) {
                Button buttonAdd = (Button) findViewById(R.id.next_fur) ;
                if (editTextNew.toString().length() > 0) { //입력이 안돼있을 때
                    // 버튼 상태 활성화.
                    buttonAdd.setEnabled(true) ;
                } else {
                    // 버튼 상태 비활성화.
                    buttonAdd.setEnabled(false) ;
                }
            }

            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
            }
        }) ;

        button_fur_next.setOnClickListener(new View.OnClickListener()  {
            public void onClick(View v) {
                EditText editText_fur = (EditText)findViewById(R.id.editsearch);

                if (editText_fur.getText().toString().length() > 0){
                    String str = editText_fur.getText().toString();


                    // 폴더 생성
                    File fursaveFile = new File(getFilesDir().getAbsolutePath() + "/database"); //가구 텍스트 파일을 담고 있는 폴더 저장 경로
                    File fursaveList = new File(getFilesDir().getAbsolutePath() + "/list"); // 위험 물건 텍스트 파일과 모든 물건을 담고 있는 파일을 담고 있는 폴더 저장 경로


                    // 폴더 생성
                    if(!fursaveFile.exists()){ // 파일이 없을 경우
                        fursaveFile.mkdir(); // 파일 생성

                    }
                    if(!fursaveList.exists()){ // 파일이 없을 경우
                        fursaveList.mkdir(); // 파일 생성
                    }

                    try {
                        BufferedWriter buf = new BufferedWriter(new FileWriter(fursaveFile+ "/"+str+".txt", true)); //database 폴더 내에 가구 이름을 제목으로 가지고 있는 텍스트 파일 생성
                        BufferedWriter buf2 = new BufferedWriter(new FileWriter(fursaveList + "/물건" + str+" warning.txt", true)); //list 폴더 내에 가구 이름을 제목으로 가지고 있는 warning 텍스트 파일 생성

                        buf.close();
                        buf2.close();

                    } catch (FileNotFoundException e) {
                        e.printStackTrace();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }

                    try {
                        BufferedWriter buf = new BufferedWriter(new FileWriter(fursaveList+ "/"+"objdatabase.txt", true)); //모든 물건 정보를 입력받는 파일 생성
                        buf.append("");
                        buf.newLine();
                        buf.close();
                    } catch (FileNotFoundException e) {
                        e.printStackTrace();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }

                    Intent intent = new Intent(Arrange_fur1Activity.this, Arrange_obj1Activity. class);
                    intent.putExtra("furniture_name", str);
                    startActivity(intent);
                    finish();
                }
            }
        });

        button_fur_finish.setOnClickListener(new View.OnClickListener()  {
            public void onClick(View v) {
                Intent intent = new Intent(Arrange_fur1Activity.this, ArrangeFinishActivity. class);
                startActivity(intent);
                finish();
            }
        });


    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        finish();
    }
}