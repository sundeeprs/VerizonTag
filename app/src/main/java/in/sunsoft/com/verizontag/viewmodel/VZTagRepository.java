package in.sunsoft.com.verizontag.viewmodel;

import android.app.Activity;
import android.app.Application;
import android.app.ProgressDialog;
import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;
import android.nfc.Tag;
import android.os.AsyncTask;
import android.util.Log;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import in.sunsoft.com.verizontag.database.VZTagDao;
import in.sunsoft.com.verizontag.database.VZTagDataBase;
import in.sunsoft.com.verizontag.model.VZTags;
import in.sunsoft.com.verizontag.views.VZTagsListActivity;
import in.sunsoft.com.vztaglibrary.model.Tags;

public class VZTagRepository {

    private VZTagDataBase tagsDataBase;
    private VZTagDao tagDao;
    private static LiveData<List<VZTags>> savedTags;
    private static List<VZTags> mTags = new ArrayList<>();
    private static LiveData<Integer> mTagsCounts;
    private VZTagsListActivity vzTagsListActivity;
    private static ProgressBar progressBar;


    public VZTagRepository(Application application) {
        tagsDataBase = VZTagDataBase.getInstance(application);
        tagDao = tagsDataBase.getTagsDao();
        progressBar = new ProgressBar(application);
        progressBar.setTag("Please wait...");

    }


    public LiveData<List<VZTags>> getAllTags() {
         return savedTags = tagDao.getAllTags();
    }

    public List<VZTags> getAllTagsFromRepo() {
        new GetAllTagsAsycTask(tagDao).execute();
        return mTags;
    }

    public LiveData <List<VZTags>> getAllTagsByASC() {
        return savedTags =tagDao.getAllTagsInASC();
    }

    public LiveData<List<VZTags>> getAllTagsBYDESC() {
        return savedTags = tagDao.getAllTagsInDESC();
    }



    public LiveData<Integer> getAllTagsCount() {

        return mTagsCounts =tagDao.getAllTagsCount();
    }


    public void InsertTag(VZTags tags) {
        new InsertagToDBAsycTask(tagDao).execute(tags);

    }

    public void DeleteAllTag() {
        new DeleteAllTagsAsycTask(tagDao).execute();

    }

    public void InsertTagThousandTime(List<VZTags> tags) {
        new CopyAllTagsThousandTimes(tagDao).execute(tags);

    }

    //Asyn task to insert Tags to Database
    public static class InsertagToDBAsycTask extends AsyncTask<VZTags, Void, Void> {
        private VZTagDao mTagDao;

        public InsertagToDBAsycTask(VZTagDao tagDao) {
            this.mTagDao = tagDao;
        }


        @Override
        protected Void doInBackground(VZTags... vztags) {
            mTagDao.InsertTags(vztags[0]);
            return null;
        }
    }


    //Asyn task to insert Tags to Database
    public static class GetAllTagsAsycTask extends AsyncTask<Void, Void, List<VZTags>> {
        private VZTagDao mTagDao;

        public GetAllTagsAsycTask(VZTagDao tagDao) {
            this.mTagDao = tagDao;
        }

        @Override
        protected List<VZTags> doInBackground(Void... voids) {
            mTags = mTagDao.getAllTagsFromRepo();
            return mTags;
        }
    }

    //Asyn task to Delete All Tags from Database
    public static class DeleteAllTagsAsycTask extends AsyncTask<VZTags, Void, Void> {
        private VZTagDao mTagDao;

        public DeleteAllTagsAsycTask(VZTagDao tagDao) {
            this.mTagDao = tagDao;
        }


        @Override
        protected Void doInBackground(VZTags... tags) {

            mTagDao.DeleteAllTags();
            return null;
        }
    }

    //Asyn task to Copy All Tags to Database
    public static class CopyAllTagsThousandTimes extends AsyncTask<List<VZTags>, Void, Boolean> {
        private VZTagDao mTagDao;
        private List<VZTags> localTags = new ArrayList<>();

        public CopyAllTagsThousandTimes(VZTagDao tagDao) {
            this.mTagDao = tagDao;
        }

        @Override
        protected void onPreExecute() {
            super.onPreExecute();


            //progressBar("Please Wait...");
            progressBar.setVisibility(View.VISIBLE);
        }

        @Override
        protected Boolean doInBackground(List<VZTags>... tags) {

            int finalcount = tags.length;
            for (int i = 0; i <1000; ++i) {
               for (int j = 0; i >j ; j++) {
                   try {
                    mTagDao.InsertTags(new VZTags(tags[i].get(i).getTag()));
                    if(i == finalcount ) {
                        j = 0;
                        break;
                    }

                   } catch (Exception e) {

                       e.printStackTrace();
                       Log.d("VZTagRepository..", "doInBackground: "+e);
                       return false;
                   }
                   Log.i("TagsRepository..", "doInBackground: "+tags[0].get(i).getTag());
               }

            }

            return true;
        }

        @Override
        protected void onPostExecute(Boolean aBoolean) {
            super.onPostExecute(aBoolean);

        }

        interface onTaskDone{
            void onTaskFinished(String result);
        }

        onTaskDone taskDone = new onTaskDone() {
            @Override
            public void onTaskFinished(String result) {

            }
        };
    }

}
