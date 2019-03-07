package in.sunsoft.com.vztaglibrary.model;

import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;

@Entity(tableName = "tag_table")
public class Tags {

    @PrimaryKey(autoGenerate = true)
    private Integer tagId;
    private String tag;

    public String getTag() {
        return tag;
    }

    public void setTag(String tag) {
        this.tag = tag;
    }

    public Integer getTagId() {
        return tagId;
    }

    public void setTagId(Integer tagId) {
        this.tagId = tagId;
    }


    public Tags(String tag) {
        this.tag = tag;
    }
}
