package ddwucom.mobile.finalreport;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import org.w3c.dom.Text;

public class AddDiary extends AppCompatActivity {
    TextView title;
    TextView weather;
    TextView feeling;
    TextView detail;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.add_diary);

        title = findViewById(R.id.edit_title);
        weather = findViewById(R.id.edit_weather);
        feeling = findViewById(R.id.edit_feeling);
        detail = findViewById(R.id.edit_detail);
    }

    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.button_ok:
                Intent resultIntent = new Intent();
                String t = title.getText().toString();
                String w = weather.getText().toString();
                String f = feeling.getText().toString();
                String d = detail.getText().toString();



        }
    }
}
