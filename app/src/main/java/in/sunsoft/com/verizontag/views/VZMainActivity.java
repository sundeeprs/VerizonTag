package in.sunsoft.com.verizontag.views;

import android.arch.lifecycle.ViewModelProviders;
import android.content.Context;
import android.content.Intent;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import in.sunsoft.com.verizontag.R;
import in.sunsoft.com.verizontag.model.VZTags;
import in.sunsoft.com.verizontag.viewmodel.VZTagViewModel;

public class VZMainActivity extends AppCompatActivity {

    private EditText mInputTagEditText;
    private VZTagViewModel mtagsViewModel;
    private Button mAddTagBTN, mShowtagListBTN;
    private CoordinatorLayout mainLayout;
    private Integer mTagsCount = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mtagsViewModel = ViewModelProviders.of(this).get(VZTagViewModel.class);

        mInputTagEditText = (EditText) findViewById(R.id.edittext_input_tag);

        // Get your layout set up, this is just an example
        mainLayout = (CoordinatorLayout) findViewById(R.id.parent_constraint_layout);
    }

    public void onAddClicked(View view) {
        InputMethodManager imm = (InputMethodManager)getSystemService(Context.INPUT_METHOD_SERVICE);
        imm.hideSoftInputFromWindow(mainLayout.getWindowToken(), 0);

        if(mInputTagEditText.getText().equals("") || mInputTagEditText.getText().length() == 0) {
            Toast.makeText(this, "Please enter a tag to save!", Toast.LENGTH_LONG).show();
            return;
        }

        mtagsViewModel.InsertTagToDatabase(new VZTags(mInputTagEditText.getText().toString()));
        Snackbar.make(view, "Tag added to SQLite DB!", Snackbar.LENGTH_LONG)
                .setAction("Action", null).show();
        mInputTagEditText.setText("");
    }

    public void onShowtagListClicked(View view) {

        InputMethodManager imm = (InputMethodManager)getSystemService(Context.INPUT_METHOD_SERVICE);
        imm.hideSoftInputFromWindow(mainLayout.getWindowToken(), 0);

        Intent showListTagIntent = new Intent(this, VZTagsListActivity.class);
        showListTagIntent.putExtra("tagCount", mTagsCount);
        startActivity(showListTagIntent);


    }
}
