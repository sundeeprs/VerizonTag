package in.sunsoft.com.vztaglibrary.viewmodel;

import android.app.Application;
import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;
import android.nfc.Tag;
import android.os.AsyncTask;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

import in.sunsoft.com.vztaglibrary.database.TagDao;
import in.sunsoft.com.vztaglibrary.database.TagDataBase;
import in.sunsoft.com.vztaglibrary.model.Tags;

public class TagRepository {

    private TagDataBase tagsDataBase;
    private TagDao tagDao;
    private static LiveData<List<Tags>> savedTags;
    private static LiveData<Integer> mTagsCounts;


    public TagRepository(Application application) {
        tagsDataBase = TagDataBase.getInstance(application);
        tagDao = tagsDataBase.getTagsDao();

    }


    public LiveData<List<Tags>> getAllTags() {
         return savedTags = tagDao.getAllTags();
    }

    public LiveData <List<Tags>> getAllTagsByASC() {
        return savedTags =tagDao.getAllTagsInASC();
    }

    public LiveData<List<Tags>> getAllTagsBYDESC() {
        return savedTags = tagDao.getAllTagsInDESC();
    }



    public LiveData<Integer> getAllTagsCount() {
         return mTagsCounts =tagDao.getAllTagsCount();
    }


    public void InsertTag(Tags tags) {
        new InsertagToDBAsycTask(tagDao).execute(tags);

    }

    public void DeleteAllTag() {
        new DeleteAllTagsAsycTask(tagDao).execute();

    }

    public void InsertTagThousandTime(List<Tags> tags) {
        new CopyAllTagsThousandTimes(tagDao).execute(tags);

    }

    //Asyn task to insert Tags to Database
    public static class InsertagToDBAsycTask extends AsyncTask<Tags, Void, Void> {
        private TagDao mTagDao;

        public InsertagToDBAsycTask(TagDao tagDao) {
            this.mTagDao = tagDao;
        }


        @Override
        protected Void doInBackground(Tags... tags) {
            mTagDao.InsertTags(tags[0]);
            return null;
        }
    }


    //Asyn task to get total Tags count
    /*public static class GetAllTagsCountAsycTask extends AsyncTask<Void, Void, LiveData<Integer>> {
        private TagDao mTagDao;

        public GetAllTagsCountAsycTask(TagDao tagDao) {
            this.mTagDao = tagDao;
        }

        @Override
        protected LiveData<Integer> doInBackground(Void... voids) {
            mTagsCounts = mTagDao.getAllTagsCount();
            return mTagsCounts;
        }
    }*/

    //Asyn task to Get All Tags from Database
    /*public static class GetAllTagsFromDBAsycTask extends AsyncTask<Tags, Void, Void> {
        private TagDao mTagDao;

        public GetAllTagsFromDBAsycTask(TagDao tagDao) {
            this.mTagDao = tagDao;
        }


        @Override
        protected Void doInBackground(Tags... tags) {

            savedTags = mTagDao.getAllTags();
            return null;
        }
    }*/

    //Asyn task to Get All Tags from Database in ASC order
    /*public static class GetAllTagsASCAsycTask extends AsyncTask<Tags, Void, Void> {
        private TagDao mTagDao;

        public GetAllTagsASCAsycTask(TagDao tagDao) {
            this.mTagDao = tagDao;
        }


        @Override
        protected Void doInBackground(Tags... tags) {

            savedTags = mTagDao.getAllTagsInASC();
            return null;
        }
    }*/

    //Asyn task to Get All Tags from Database in DESC order
    /*public static class GetAllTagsDESCAsycTask extends AsyncTask<Tags, Void, Void> {
        private TagDao mTagDao;

        public GetAllTagsDESCAsycTask(TagDao tagDao) {
            this.mTagDao = tagDao;
        }


        @Override
        protected Void doInBackground(Tags... tags) {

            savedTags = mTagDao.getAllTagsInDESC();
            return null;
        }
    }*/

    //Asyn task to Delete All Tags from Database
    public static class DeleteAllTagsAsycTask extends AsyncTask<Tags, Void, Void> {
        private TagDao mTagDao;

        public DeleteAllTagsAsycTask(TagDao tagDao) {
            this.mTagDao = tagDao;
        }


        @Override
        protected Void doInBackground(Tags... tags) {

            mTagDao.DeleteAllTags();
            return null;
        }
    }

    //Asyn task to Copy All Tags to Database
    public static class CopyAllTagsThousandTimes extends AsyncTask<List<Tags>, Void, Void> {
        private TagDao mTagDao;
        private List<Tags> localTags = new ArrayList<>();

        public CopyAllTagsThousandTimes(TagDao tagDao) {
            this.mTagDao = tagDao;
        }


        @Override
        protected Void doInBackground(List<Tags>... tags) {

            for (int i = 0; i < 1000; i++) {
               // for (int j = 0; j <tags.length ; j++) {
                    mTagDao.InsertTags(new Tags(tags[0].get(i).getTag()));
                    Log.i("TagsRepository..", "doInBackground: "+tags[0].get(i).getTag());
               // }
            }

            return null;
        }
    }

}
