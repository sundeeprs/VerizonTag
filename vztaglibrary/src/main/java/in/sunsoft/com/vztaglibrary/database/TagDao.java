package in.sunsoft.com.vztaglibrary.database;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;
import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;
import android.icu.text.Replaceable;

import java.util.List;

import in.sunsoft.com.vztaglibrary.model.Tags;

@Dao
public interface TagDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void InsertTags(Tags tags);

    @Query("DELETE FROM TAG_TABLE")
    void DeleteAllTags();

    @Query("SELECT * FROM TAG_TABLE")
    LiveData<List<Tags>> getAllTags();

    @Query("SELECT * FROM TAG_TABLE ORDER BY tag ASC")
    LiveData<List<Tags>> getAllTagsInASC();

    @Query("SELECT * FROM TAG_TABLE ORDER BY tag DESC")
    LiveData<List<Tags>> getAllTagsInDESC();

    @Query("SELECT count(*) FROM TAG_TABLE")
    LiveData<Integer> getAllTagsCount();

    //@Query("SELECT tag FROM TAG_TABLE GROUP BY tag HAVING COUNT(tag) > 1")
    //LiveData<Integer> getMaxDuplicateTagsCount();

}
