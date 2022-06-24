package ddwucom.mobile.finalreport;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import org.w3c.dom.Text;

public class AddDiary extends AppCompatActivity {
    TextView title;
    RadioGroup weather;
    RadioGroup feeling;
    TextView detail;
    String w, f;

    DiaryDBManager diaryDBManager;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.add_diary);

        title = findViewById(R.id.edit_title);
        weather = findViewById(R.id.edit_weather);
        feeling = findViewById(R.id.edit_feeling);
        detail = findViewById(R.id.edit_detail);


        weather.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {
                switch (i) {
                    case R.id.sunny:
                        w = "맑음";
                        break;

                    case R.id.cloudy:
                        w = "흐림";
                        break;

                    case R.id.rain:
                        w = "비";
                        break;
                }
            }

        });

        feeling.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {
                switch (i) {
                    case R.id.fine:
                        f = "좋음";
                        break;

                    case R.id.sad:
                        f = "슬픔";
                        break;

                    case R.id.angry:
                        f = "화남";
                        break;
                }
            }
        });

        diaryDBManager = new DiaryDBManager(this);
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.button_ok:
                String t = title.getText().toString();
                String d = detail.getText().toString();
                boolean check[] = {true, true, true, true};
                String missing[] = {"제목", "내용", "날씨", "기분"};
                String msg = "";

                if(t.equals("") || t == null) {
                    check[0] = false;
                }
                if(d.equals("") || d == null) {
                    check[1] = false;
                }
                if(w == null) {
                    check[2] = false;
                }
                if(f == null) {
                    check[3] = false;
                }

                for(int i=0; i<check.length; i++) {
                    if(!check[i]) {
                         msg += missing[i] + " ";
                    }
                }

                if(msg.equals("")) {
                    boolean result = diaryDBManager.addNewDiary(
                            new Diary(t, f, w, d)
                    );

                    if (result) {
                        setResult(RESULT_OK);
                        finish();
                    }
                } else {
                    Toast.makeText(this, msg + "을(를) 입력해주세요!", Toast.LENGTH_SHORT).show();
                }

                break;

            case R.id.button_cancel:
                setResult(RESULT_CANCELED);
                finish();
                break;
        }
    }
}
