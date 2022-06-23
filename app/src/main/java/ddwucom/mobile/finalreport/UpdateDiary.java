package ddwucom.mobile.finalreport;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import androidx.appcompat.app.AppCompatActivity;

public class UpdateDiary extends AppCompatActivity {
    EditText upTitle, upDetail;
    Diary diary;
    DiaryDBManager diaryDBManager;

    RadioGroup upWeather;
    RadioGroup upFeeling;
    RadioButton w1, w2, w3;
    RadioButton f1, f2, f3;

    String uw, uf;
    String w, f;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.update_diary);

        diary = (Diary) getIntent().getSerializableExtra("diary");
        upTitle = findViewById(R.id.update_title);
        upWeather = findViewById(R.id.update_weather);
        upFeeling = findViewById(R.id.update_feeling);
        upDetail = findViewById(R.id.update_detail);

        upTitle.setText(diary.getTitle());
        upDetail.setText(diary.getDetail());



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
                        break;

                    case R.id.upSad:
                        uf = "슬픔";
                        break;

                    case R.id.upAngry:
                        uf = "화남";
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

                diary.setTitle(title);
                diary.setDetail(detail);
                diary.setWeather(uw);
                diary.setFeeling(uf);

                boolean result = diaryDBManager.modifyDiary(diary);

                if(result) {
                    setResult(RESULT_OK);
                } else {
                    setResult(RESULT_CANCELED);
                }

                break;

            case R.id.button_cancel2:
                setResult(RESULT_CANCELED);
                break;
        }

        finish();
    }
}
