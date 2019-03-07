package in.sunsoft.com.verizontag.views;

import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import in.sunsoft.com.verizontag.R;
import in.sunsoft.com.verizontag.adapter.VZTagsListAdapter;
import in.sunsoft.com.verizontag.model.VZTags;
import in.sunsoft.com.verizontag.viewmodel.OnTaskCompletion;
import in.sunsoft.com.verizontag.viewmodel.VZTagViewModel;
import in.sunsoft.com.vztaglibrary.model.Tags;


public class VZTagsListActivity extends AppCompatActivity implements OnTaskCompletion {


    public static final String TAG = VZTagsListActivity.class.getSimpleName();
    private RecyclerView mListTagsRecyclerView;
    private VZTagViewModel mtagsViewModel;
    private VZTagsListAdapter mTagListAdapter;
    private List<VZTags> mTagList = new ArrayList<>();
    private ProgressBar mProgressBar;
    private TextView mTextViewCount;

    //private TagsViewModule tagsViewModel;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tags_list);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        getSupportActionBar().setHomeAsUpIndicator(R.drawable.ic_arrow);
        int tagsCount = getIntent().getIntExtra("tagCount", 0);
        setTitle("Saved Tags");

        mProgressBar = (ProgressBar) findViewById(R.id.progress_bar);
        mListTagsRecyclerView = (RecyclerView) findViewById(R.id.recyclerview_tags_list);
        mTextViewCount = (TextView) findViewById(R.id.textview_tags_count);
        mtagsViewModel = ViewModelProviders.of(this).get(VZTagViewModel.class);

        //call to get number of tag present in DB
        getAllTagsCount();

        mtagsViewModel.getAllTagsFromRepo().observe(this, new Observer<List<VZTags>>() {
            @Override
            public void onChanged(@Nullable List<VZTags> tags) {

                mTagListAdapter.SetTagsFromRepo(tags);
                mTagListAdapter.notifyDataSetChanged();

            }
        });

        initializeRecyclerView();
    }

    private void getAllTagsCount() {

        if(mtagsViewModel != null) {
            mtagsViewModel.getAllTagsCountFromRepo().observe(this, new Observer<Integer>() {
                @Override
                public void onChanged(@Nullable Integer integer) {
                    mTextViewCount.setText("Total Tag Count : " + integer);
                }
            });
        }

    }


    @Override
    protected void onStart() {
        super.onStart();


    }

    private void initializeRecyclerView() {

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this,
                LinearLayoutManager.VERTICAL, false);
        mListTagsRecyclerView.setLayoutManager(linearLayoutManager);
        mListTagsRecyclerView.setHasFixedSize(true);
        mListTagsRecyclerView.addItemDecoration(new DividerItemDecoration(mListTagsRecyclerView.getContext(), DividerItemDecoration.VERTICAL));
        Log.i(TAG, "initializeRecyclerView: "+ mTagList.size());
        if(mTagList.size() > 0) {
            mTagListAdapter = new VZTagsListAdapter(this, mTagList);
        }else {
            VZTags newTags = new VZTags("Hello World!");
            mTagList.add(newTags);
            mTagListAdapter = new VZTagsListAdapter(this, mTagList);
        }

        mListTagsRecyclerView.setAdapter(mTagListAdapter);
        mTagListAdapter.notifyDataSetChanged();

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        int id = item.getItemId();

        if (id == R.id.action_accending) {
            Log.i(TAG, "onOptionsItemSelected: Acending ");
            mtagsViewModel.getAllTagsByASC().observe(this, new Observer<List<VZTags>>() {
                @Override
                public void onChanged(@Nullable List<VZTags> tags) {
                    mTagListAdapter.SetTagsFromRepo(tags);
                    mTagListAdapter.notifyDataSetChanged();
                }
            });

            return true;
        } else if(id == R.id.action_decending) {
            Log.i(TAG, "onOptionsItemSelected: Decending ");
            mtagsViewModel.getAllTagsByDESC().observe(this, new Observer<List<VZTags>>() {
                @Override
                public void onChanged(@Nullable List<VZTags> tags) {
                    mTagListAdapter.SetTagsFromRepo(tags);
                    mTagListAdapter.notifyDataSetChanged();
                }
            });
            return true;
        } else if(id == R.id.action_copy) {

            showProgressBar();

            List<VZTags> newTagList = new ArrayList<>();
            newTagList = mtagsViewModel.getAllTags();

            mtagsViewModel.InsertTagToDatabaseThousandTime(newTagList);

        }else if(id == R.id.action_delete_all) {
            mtagsViewModel.DeleteAllTagFromDatabase();
            mTagListAdapter.notifyDataSetChanged();
        } else if(id == R.id.action_max_occurence) {
            //TODO - implement this
            Toast.makeText(this," In progress..", Toast.LENGTH_SHORT).show();

        }

        return super.onOptionsItemSelected(item);
    }

    private void showProgressBar() {
        if(mProgressBar != null)
            mProgressBar.setVisibility(View.VISIBLE);
    }

    private void hideProgressBar() {
        if(mProgressBar != null)
            mProgressBar.setVisibility(View.INVISIBLE);
    }

    @Override
    protected void onResume() {
        super.onResume();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }
}
