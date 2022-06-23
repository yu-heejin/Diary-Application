package ddwucom.mobile.finalreport;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Build;

import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class DiaryDBHelper extends SQLiteOpenHelper {
    final static String DB_NAME = "diary.db";

    public final static String TABLE_NAME = "diary_table";
    public final static String COL_ID = "_id";
    public final static String COL_TITLE = "title";
    public final static String COL_FEELING = "feeling";
    public final static String COL_WEATHER = "weather";
    public final static String COL_DATE = "date";
    public final static String COL_DETAIL = "detail";
    public final static String COL_PIC = "picture";

    LocalDate localDate;
    DateTimeFormatter formatter;

    public DiaryDBHelper(@Nullable Context context) {
        super(context, DB_NAME, null, 2);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        String sql = "CREATE TABLE " + TABLE_NAME + " (" + COL_ID + " integer primary key autoincrement, "
                + COL_TITLE + " TEXT, " + COL_FEELING + " TEXT, " + COL_WEATHER + " TEXT, " + COL_DATE + " TEXT, "
                + COL_DETAIL + " TEXT, " + COL_PIC + " integer)";

        sqLiteDatabase.execSQL(sql);

        sqLiteDatabase.execSQL("insert into " + TABLE_NAME + " values (null, '오늘의 일기', '좋음', '맑음', '2022-03-01', '오늘은 친구와 맛있는 저녁 식사를 했다. 재미있었다.', " + R.mipmap.smile + ");");
        sqLiteDatabase.execSQL("insert into " + TABLE_NAME + " values (null, '속상한 하루...', '슬픔', '맑음', '2022-03-05', '노력한 만큼 안 나와서 속상해 ㅠㅠ', " + R.mipmap.sad + ");");
        sqLiteDatabase.execSQL("insert into " + TABLE_NAME + " values (null, '놀고싶다', '화남', '비', '2022-03-06', '비가 와서 못 나가!', " + R.mipmap.angry + ");");
        sqLiteDatabase.execSQL("insert into " + TABLE_NAME + " values (null, '강아지랑 산책', '좋음', '맑음', '2022-03-31', '강아지랑 같이 산책하는게 제일 좋아!', " + R.mipmap.smile + ");");
        sqLiteDatabase.execSQL("insert into " + TABLE_NAME + " values (null, '즐거운 내 생일~!', '좋음', '흐림', '2022-04-07', '선물도 많이받고 맛있는것도 많이 받아서 좋다!', " + R.mipmap.smile + ");");
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(sqLiteDatabase);
    }
}
