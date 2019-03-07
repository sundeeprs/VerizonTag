package in.sunsoft.com.verizontag.viewmodel;

import java.util.List;

import in.sunsoft.com.verizontag.model.VZTags;

public interface OnTaskCompletion {

    public interface onTaskCompletion {

        void onTaskCompleted(List<VZTags> vzTagsList);
    }

    public interface onResultReceived {

        void onTaskCompleted(Boolean result);
    }
}
