package ddwucom.mobile.finalreport;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.SearchView;
import android.widget.Toast;

import java.lang.reflect.Array;
import java.util.ArrayList;

//과제명 : 다이어리 앱
//분반 : 01분반
//학번 : 20200989 성명: 유희진
//제출일

public class MainActivity extends AppCompatActivity {
    private ArrayList<Diary> diaryArrayList;
    private DiaryDBManager diaryDBManager;
    private DiaryAdapter diaryAdapter;
    private ListView listView;
    SearchView searchView;
    MenuItem menuItem;

    final int REQ_CODE = 100;
    final int UPDATE_CODE = 200;

    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        listView = (ListView) findViewById(R.id.diary_list);
        diaryDBManager = new DiaryDBManager(this);
        diaryArrayList = diaryDBManager.getAllDiary();


        diaryAdapter = new DiaryAdapter(this, R.layout.custom_adapter, diaryArrayList);

        //diaryArrayList.add(new Diary("다이어리 제목 테스트", "좋음", "흐림", "재밌다", R.mipmap.angry));

        listView.setAdapter(diaryAdapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Diary diary = diaryArrayList.get(i);

                Intent intent = new Intent(MainActivity.this, UpdateDiary.class);
                intent.putExtra("diary", diary);
                startActivityForResult(intent, UPDATE_CODE);
            }
        });

        listView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> adapterView, View view, int i, long l) {
                final int position = i;
                String title = diaryArrayList.get(i).getTitle();

                AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
                builder.setTitle("다이어리 삭제")
                        .setMessage("\"" + title + "\" 기록을 정말 삭제하시겠습니까?")
                        .setPositiveButton("삭제", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                boolean result = diaryDBManager.removeDiary(diaryArrayList.get(position).get_id());

                                if(result) {
                                    Toast.makeText(MainActivity.this, "삭제가 완료되었습니다.", Toast.LENGTH_SHORT).show();
                                    onResume();
                                } else {
                                    Toast.makeText(MainActivity.this, "삭제에 실패하였습니다.", Toast.LENGTH_SHORT).show();
                                }
                            }
                        })
                        .setNegativeButton("취소", null)
                        .setCancelable(false)
                        .show();
                return true;
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if(requestCode == REQ_CODE) {
            switch(resultCode) {
                case RESULT_OK:
                    Toast.makeText(this, "오늘 하루를 기록했습니다!", Toast.LENGTH_SHORT).show();
                    break;

                case RESULT_CANCELED:
                    Toast.makeText(this, "취소가 완료되었습니다.", Toast.LENGTH_SHORT).show();
            }

        } else if(requestCode == UPDATE_CODE) {
            switch (resultCode) {
                case RESULT_OK:
                    Toast.makeText(this, "수정이 완료되었습니다!", Toast.LENGTH_SHORT).show();
                    break;

                case RESULT_CANCELED:
                    Toast.makeText(this, "수정 취소가 완료되었습니다.", Toast.LENGTH_SHORT).show();
            }
        }
    }

    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);

        menuItem = menu.findItem(R.id.app_bar_search);
        searchView = (SearchView) menuItem.getActionView();
        searchView.setQueryHint("제목으로 검색하기");

        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String s) {
                //검색 버튼이 눌렸을 때 이벤트 처리
                ArrayList<Diary> arrayList = diaryDBManager.getDiaryByTitle(s);
                diaryArrayList.clear();
                diaryArrayList.addAll(arrayList);
                diaryAdapter.notifyDataSetChanged();
                return false;
            }

            @RequiresApi(api = Build.VERSION_CODES.O)
            @Override
            public boolean onQueryTextChange(String s) {
                if(s.equals("")) {
                    diaryArrayList.clear();
                    diaryArrayList.addAll(diaryDBManager.getAllDiary());
                    diaryAdapter.notifyDataSetChanged();
                } else {
                    diaryArrayList.clear();
                    ArrayList<Diary> tmp = diaryDBManager.getAllDiary();
                    for(int i=0; i<tmp.size(); i++) {
                        if(tmp.get(i).getTitle().contains(s)) {
                            diaryArrayList.add(tmp.get(i));
                        }
                    }
                    diaryAdapter.notifyDataSetChanged();
                }

                return false;
            }
        });
        return true;
    }

    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.add:
                //Toast.makeText(this, "일기 쓰기", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(this, AddDiary.class);
                startActivityForResult(intent, REQ_CODE);
                break;

            case R.id.intro:
                Intent intent1 = new Intent(this, IntroActivity.class);
                startActivity(intent1);
                break;

            case R.id.exit:
                //Toast.makeText(this, "나가기", Toast.LENGTH_SHORT).show();
                AlertDialog.Builder builder = new AlertDialog.Builder(this);
                builder.setTitle("앱 종료하기")
                        .setMessage("정말 나가셔도 괜찮으신가요?")
                        .setPositiveButton("괜찮아요", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                Toast.makeText(MainActivity.this, "다음에 또 봐요!", Toast.LENGTH_SHORT).show();
                                finish();
                            }
                        })
                        .setCancelable(false)
                        .setNegativeButton("취소", null)
                        .show();

        }

        return true;
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    protected void onResume() {
        super.onResume();
        diaryArrayList.clear();
        diaryArrayList.addAll(diaryDBManager.getAllDiary());
        diaryAdapter.notifyDataSetChanged();
    }

}