package ddwucom.mobile.finalreport;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class UpdateDiary extends AppCompatActivity {
    EditText upTitle, upDetail;
    Diary diary;
    DiaryDBManager diaryDBManager;
    ImageView icon;

    RadioGroup upWeather;
    RadioGroup upFeeling;
    RadioButton w1, w2, w3;
    RadioButton f1, f2, f3;

    String uw, uf;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.update_diary);

        diary = (Diary) getIntent().getSerializableExtra("diary");
        upTitle = (EditText) findViewById(R.id.update_title);
        upWeather = (RadioGroup) findViewById(R.id.update_weather);
        upFeeling = (RadioGroup) findViewById(R.id.update_feeling);
        upDetail = (EditText) findViewById(R.id.update_detail);
        icon = (ImageView) findViewById(R.id.up_icon);

        upTitle.setText(diary.getTitle());
        upDetail.setText(diary.getDetail());

        w1 = findViewById(R.id.upSunny);
        w2 = findViewById(R.id.upCloudy);
        w3 = findViewById(R.id.upRain);

        f1 = findViewById(R.id.upFine);
        f2 = findViewById(R.id.upSad);
        f3 = findViewById(R.id.upAngry);

        if(diary.getWeather().equals("맑음")) {
            upWeather.check(w1.getId());
        } else if(diary.getWeather().equals("흐림")) {
            upWeather.check(w2.getId());
        } else if(diary.getWeather().equals("비")){
            upWeather.check(w3.getId());
        }

        if(diary.getFeeling().equals("좋음")) {
            upFeeling.check(f1.getId());
            icon.setImageResource(R.mipmap.smile);
        } else if(diary.getFeeling().equals("슬픔")) {
            upFeeling.check(f2.getId());
            icon.setImageResource(R.mipmap.sad);
        } else if(diary.getFeeling().equals("화남")){
            upFeeling.check(f3.getId());
            icon.setImageResource(R.mipmap.angry);
        }


        diaryDBManager = new DiaryDBManager(this);

        upWeather.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {
                switch (i) {
                    case R.id.upSunny:
                        uw = "맑음";
                        break;

                    case R.id.upCloudy:
                        uw = "흐림";
                        break;

                    case R.id.upRain:
                        uw = "비";
                        break;
                }
            }
        });

        upFeeling.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {
                switch (i) {
                    case R.id.upFine:
                        uf = "좋음";
                        icon.setImageResource(R.mipmap.smile);
                        break;

                    case R.id.upSad:
                        uf = "슬픔";
                        icon.setImageResource(R.mipmap.sad);
                        break;

                    case R.id.upAngry:
                        uf = "화남";
                        icon.setImageResource(R.mipmap.angry);
                        break;
                }
            }
        });
    }

    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.button_update:
                String title = upTitle.getText().toString();
                String detail = upDetail.getText().toString();
                boolean check[] = {true, true};
                String missing[] = {"제목", "내용"};
                String msg = "";

                diary.setTitle(title);
                diary.setDetail(detail);

                if(uw == null) {
                    diary.setWeather(diary.getWeather());
                } else {
                    diary.setWeather(uw);
                }

                if(uf == null) {
                    diary.setFeeling(diary.getFeeling());
                } else {
                    diary.setFeeling(uf);
                }

                if(title.equals("") || title == null) {
                    check[0] = false;
                }
                if(detail.equals("") || detail == null) {
                    check[1] = false;
                }

                for(int i=0; i<check.length; i++) {
                    if(!check[i]) {
                        msg += missing[i] + " ";
                    }
                }

                if(msg.equals("")) {
                    boolean result = diaryDBManager.modifyDiary(diary);

                    if(result) {
                        setResult(RESULT_OK);
                    } else {
                        setResult(RESULT_CANCELED);
                    }
                    finish();
                } else {
                    Toast.makeText(this, msg + "을(를) 입력해주세요!", Toast.LENGTH_SHORT).show();
                }

                break;

            case R.id.button_cancel2:
                setResult(RESULT_CANCELED);
                finish();
                break;
        }


    }
}