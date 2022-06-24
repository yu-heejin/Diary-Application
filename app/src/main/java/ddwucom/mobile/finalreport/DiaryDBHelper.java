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
        super(context, DB_NAME, null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        String sql = "CREATE TABLE " + TABLE_NAME + " (" + COL_ID + " integer primary key autoincrement, "
                + COL_TITLE + " TEXT, " + COL_FEELING + " TEXT, " + COL_WEATHER + " TEXT, " + COL_DATE + " TEXT, "
                + COL_DETAIL + " TEXT, " + COL_PIC + " integer)";

        sqLiteDatabase.execSQL(sql);

        sqLiteDatabase.execSQL("insert into " + TABLE_NAME + " values (null, '오늘의 일기', '좋음', '맑음', '2022-03-01', '오늘은 친구와 맛있는 저녁 식사를 했다. 재미있었다.', " + R.mipmap.smile + ");");
        sqLiteDatabase.execSQL("insert into " + TABLE_NAME + " values (null, '슬픈 하루', '슬픔', '맑음', '2022-03-05', '넘어져서 다쳤다.', " + R.mipmap.sad + ");");
        sqLiteDatabase.execSQL("insert into " + TABLE_NAME + " values (null, '친구랑 싸웠다', '화남', '비', '2022-03-06', '화가난다.', " + R.mipmap.angry + ");");
        sqLiteDatabase.execSQL("insert into " + TABLE_NAME + " values (null, '강아지랑 산책', '좋음', '맑음', '2022-03-31', '재미있다!', " + R.mipmap.smile + ");");
        sqLiteDatabase.execSQL("insert into " + TABLE_NAME + " values (null, '벚꽃 구경', '좋음', '흐림', '2022-04-07', '벚꽃이 참 이쁘다', " + R.mipmap.smile + ");");
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(sqLiteDatabase);
    }
}
