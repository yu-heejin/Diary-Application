package ddwucom.mobile.finalreport;

import android.os.Bundle;
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
}
