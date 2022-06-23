package ddwucom.mobile.finalreport;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import org.w3c.dom.Text;

public class AddDiary extends AppCompatActivity {
    TextView title;
    TextView weather;
    TextView feeling;
    TextView detail;

    DiaryDBManager diaryDBManager;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.add_diary);

        title = findViewById(R.id.edit_title);
        weather = findViewById(R.id.edit_weather);
        feeling = findViewById(R.id.edit_feeling);
        detail = findViewById(R.id.edit_detail);

        //diaryDBHelper = new DiaryDBHelper(this);    -> DBManager를 호출하면 helper를 부를 필요 없음
        diaryDBManager = new DiaryDBManager(this);
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.button_ok:
                String t = title.getText().toString();
                String w = weather.getText().toString();
                String f = feeling.getText().toString();
                String d = detail.getText().toString();

                boolean result = diaryDBManager.addNewDiary(
                        new Diary(t, f, w, d)
                );

                if(result) {
                    Intent resultIntent = new Intent();
                    setResult(RESULT_OK);
                }
                break;


            case R.id.button_cancel:
                setResult(RESULT_CANCELED);
                break;
        }

        finish();
    }
}
