package in.sunsoft.com.verizontag.viewmodel;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;
import android.content.Context;
import android.support.annotation.NonNull;

import java.util.ArrayList;
import java.util.List;

import in.sunsoft.com.verizontag.model.VZTags;

public class VZTagViewModel extends AndroidViewModel {

    private LiveData<List<VZTags>> mLiveDataTags;
    private List<VZTags> mTags = new ArrayList<>();
    private VZTagRepository mTagRepository;
    private LiveData<Integer> mTagsCount;

    public VZTagViewModel(@NonNull Application application) {
        super(application);
        mTagRepository = new VZTagRepository(application);
    }


    public void InsertTagToDatabase(VZTags tags){
        mTagRepository.InsertTag(tags);

    }

    public void DeleteAllTagFromDatabase(){
        mTagRepository.DeleteAllTag();

    }

    public boolean InsertTagToDatabaseThousandTime(List<VZTags> tags){
        mTagRepository.InsertTagThousandTime(tags);
        return true;
    }

    public LiveData<List<VZTags>> getAllTagsFromRepo() {
        mLiveDataTags= mTagRepository.getAllTags();
        return mLiveDataTags;
    }

    public List<VZTags> getAllTags() {
        mTags= mTagRepository.getAllTagsFromRepo();
        return mTags;
    }


    public LiveData<List<VZTags>> getAllTagsByASC() {
        return mLiveDataTags = mTagRepository.getAllTagsByASC();
    }


    public LiveData<List<VZTags>> getAllTagsByDESC() {
        mLiveDataTags = mTagRepository.getAllTagsBYDESC();
        return mLiveDataTags;
    }

    public LiveData<Integer> getAllTagsCountFromRepo() {
        mTagsCount = mTagRepository.getAllTagsCount();
        return mTagsCount;
    }

}
