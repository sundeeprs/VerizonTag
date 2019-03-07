package in.sunsoft.com.verizontag.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

import in.sunsoft.com.verizontag.R;
import in.sunsoft.com.verizontag.model.VZTags;


public class VZTagsListAdapter extends RecyclerView.Adapter<VZTagsListAdapter.TagsViewHolder> {

    private List<VZTags> mTagsList;
    private Context mContext;
    private VZTags mtags;

    public VZTagsListAdapter(Context context, List<VZTags> tags) {
        this.mContext = context;
        mTagsList = tags;

    }

    @NonNull
    @Override
    public TagsViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {

        View view = LayoutInflater.from(mContext).inflate(R.layout.list_tags_layout, viewGroup, false);
        return new TagsViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull TagsViewHolder tagsViewHolder, int i) {
        mtags = mTagsList.get(i);
        tagsViewHolder.listTagsTextView.setText(mtags.getTag().toString());

    }

    @Override
    public int getItemCount() {
        return mTagsList.size();
    }


    public class TagsViewHolder extends RecyclerView.ViewHolder {

        private TextView listTagsTextView;

        public TagsViewHolder(@NonNull View itemView) {
            super(itemView);

            listTagsTextView = (TextView) itemView.findViewById(R.id.textview_list_tags);
        }
    }

    public void SetTagsFromRepo(List<VZTags> tags) {
        this.mTagsList = tags;
        notifyDataSetChanged();
    }
}
