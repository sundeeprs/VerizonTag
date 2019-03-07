package in.sunsoft.com.vztaglibrary.database;

import android.arch.persistence.db.SupportSQLiteDatabase;
import android.arch.persistence.room.Database;
import android.arch.persistence.room.Room;
import android.arch.persistence.room.RoomDatabase;
import android.content.Context;
import android.support.annotation.NonNull;

import in.sunsoft.com.vztaglibrary.model.Tags;

@Database(entities = {Tags.class}, version = 2)
public abstract class TagDataBase extends RoomDatabase {


    public static final String TAG = TagDataBase.class.getSimpleName();
    public static volatile TagDataBase instance;

    public abstract TagDao getTagsDao();

    public static synchronized TagDataBase getInstance(Context context){

        if(instance == null) {
            instance = Room.databaseBuilder(context.getApplicationContext(),
                    TagDataBase.class, "TagDataBase")
                    .fallbackToDestructiveMigration()
                    .build();
        }

        return instance;

    }

    private static RoomDatabase.Callback callback = new Callback() {
        @Override
        public void onCreate(@NonNull SupportSQLiteDatabase db) {
            super.onCreate(db);
        }
    };
}
