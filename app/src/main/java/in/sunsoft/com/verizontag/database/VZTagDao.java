package in.sunsoft.com.verizontag.database;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;
import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;
import android.icu.text.Replaceable;

import java.util.List;
import in.sunsoft.com.verizontag.model.VZTags;

@Dao
public interface VZTagDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void InsertTags(VZTags tags);

    @Query("DELETE FROM TAG_TABLE")
    void DeleteAllTags();

    @Query("SELECT * FROM TAG_TABLE")
    LiveData<List<VZTags>> getAllTags();

    @Query("SELECT * FROM TAG_TABLE")
    List<VZTags> getAllTagsFromRepo();

    @Query("SELECT * FROM TAG_TABLE ORDER BY tag ASC")
    LiveData<List<VZTags>> getAllTagsInASC();

    @Query("SELECT * FROM TAG_TABLE ORDER BY tag DESC")
    LiveData<List<VZTags>> getAllTagsInDESC();

    @Query("SELECT count(*) FROM TAG_TABLE")
    LiveData<Integer> getAllTagsCount();

}
