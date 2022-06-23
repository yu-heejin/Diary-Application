package ddwucom.mobile.finalreport;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

public class UpdateDiary extends AppCompatActivity {
    EditText upTitle, upWeather, upFeeling, upDetail;
    Diary diary;
    DiaryDBManager diaryDBManager;

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
        upWeather.setText(diary.getWeather());
        upFeeling.setText(diary.getFeeling());
        upDetail.setText(diary.getDetail());

        diaryDBManager = new DiaryDBManager(this);
    }

    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.button_update:
                String title = upTitle.getText().toString();
                String weather = upWeather.getText().toString();
                String feeling = upFeeling.getText().toString();
                String detail = upDetail.getText().toString();

                diary.setTitle(title);
                diary.setWeather(weather);
                diary.setFeeling(feeling);
                diary.setDetail(detail);

                boolean result = diaryDBManager.modifyDiary(diary);

                if(result) {
                    Intent resultIntent = new Intent();
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
