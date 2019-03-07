package in.sunsoft.com.vztaglibrary.viewmodel;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;
import android.content.Context;
import android.support.annotation.NonNull;

import java.util.List;

import in.sunsoft.com.vztaglibrary.model.Tags;

public class TagViewModel extends AndroidViewModel {

    private LiveData<List<Tags>> mLiveDataTags;
    private TagRepository mTagRepository;
    private LiveData<Integer> mTagsCount;
    private MutableLiveData<Boolean> isUpdting = new MutableLiveData<>();

    public TagViewModel(@NonNull Application application) {
        super(application);
        mTagRepository = new TagRepository(application);
        //mLiveDataTags = mTagRepository.getAllTags();
    }


    public void InsertTagToDatabase(Tags tags){
        isUpdting.setValue(true);
        mTagRepository.InsertTag(tags);

    }

    public void DeleteAllTagFromDatabase(){
        isUpdting.setValue(true);
        mTagRepository.DeleteAllTag();

    }



    public void InsertTagToDatabaseThousandTime(List<Tags> tags){
        isUpdting.setValue(true);
        mTagRepository.InsertTagThousandTime(tags);

    }

    public LiveData<List<Tags>> getAllTagsFromRepo() {
        isUpdting.setValue(true);
        mLiveDataTags= mTagRepository.getAllTags();
        return mLiveDataTags;
    }


    public LiveData<List<Tags>> getAllTagsByASC() {
        isUpdting.setValue(true);
        return mLiveDataTags = mTagRepository.getAllTagsByASC();
    }


    public LiveData<List<Tags>> getAllTagsByDESC() {
        isUpdting.setValue(true);
        mLiveDataTags = mTagRepository.getAllTagsBYDESC();
        return mLiveDataTags;
    }

    public MutableLiveData<Boolean> getIsUpdatiing () {
        isUpdting.setValue(false);
        return isUpdting;

    }


    public LiveData<Integer> getAllTagsCountFromRepo() {
        isUpdting.postValue(false);
        mTagsCount = mTagRepository.getAllTagsCount();

        return mTagsCount;
    }


    public void setIsUpdting(MutableLiveData<Boolean> isUpdting) {
        this.isUpdting = isUpdting;
    }

}
