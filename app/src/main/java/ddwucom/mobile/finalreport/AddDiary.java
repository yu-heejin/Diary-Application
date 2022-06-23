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
    RadioButton w1, w2, w3;
    RadioGroup feeling;
    RadioButton f1, f2, f3;
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

        w1 = findViewById(R.id.sunny);
        w2 = findViewById(R.id.cloudy);
        w3 = findViewById(R.id.rain);

        f1 = findViewById(R.id.fine);
        f2 = findViewById(R.id.sad);
        f3 = findViewById(R.id.angry);



        diaryDBManager = new DiaryDBManager(this);
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.button_ok:
                String t = title.getText().toString();

                String d = detail.getText().toString();

                boolean result = diaryDBManager.addNewDiary(
                        new Diary(t, f, w, d)
                );

                if(result) {
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
