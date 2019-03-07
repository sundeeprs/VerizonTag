package in.sunsoft.com.verizontag.model;

import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;

@Entity(tableName = "tag_table")
public class VZTags {

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


        public VZTags(String tag) {
            this.tag = tag;
        }

}
