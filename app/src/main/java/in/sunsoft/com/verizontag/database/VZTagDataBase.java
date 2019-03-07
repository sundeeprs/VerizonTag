package in.sunsoft.com.verizontag.database;

import android.arch.persistence.db.SupportSQLiteDatabase;
import android.arch.persistence.room.Database;
import android.arch.persistence.room.Room;
import android.arch.persistence.room.RoomDatabase;
import android.content.Context;
import android.support.annotation.NonNull;

import in.sunsoft.com.verizontag.model.VZTags;

@Database(entities = {VZTags.class}, version = 1)
public abstract class VZTagDataBase extends RoomDatabase {


    public static final String TAG = VZTagDataBase.class.getSimpleName();
    public static volatile VZTagDataBase instance;

    public abstract VZTagDao getTagsDao();

    public static synchronized VZTagDataBase getInstance(Context context){

        if(instance == null) {
            instance = Room.databaseBuilder(context.getApplicationContext(),
                    VZTagDataBase.class, "TagDataBase")
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
