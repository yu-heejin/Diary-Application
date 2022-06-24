package ddwucom.mobile.finalreport;

import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;


public class AddDiary extends AppCompatActivity {
    EditText title;
    RadioGroup weather;
    RadioGroup feeling;
    EditText detail;
    String w, f;
    ImageView icon;

    DiaryDBManager diaryDBManager;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.add_diary);

        title = (EditText) findViewById(R.id.edit_title);
        weather = (RadioGroup) findViewById(R.id.edit_weather);
        feeling = (RadioGroup) findViewById(R.id.edit_feeling);
        detail = (EditText) findViewById(R.id.edit_detail);
        icon = (ImageView) findViewById(R.id.add_icon);


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
                        icon.setImageResource(R.mipmap.smile);
                        break;

                    case R.id.sad:
                        f = "슬픔";
                        icon.setImageResource(R.mipmap.sad);
                        break;

                    case R.id.angry:
                        f = "화남";
                        icon.setImageResource(R.mipmap.angry);
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
