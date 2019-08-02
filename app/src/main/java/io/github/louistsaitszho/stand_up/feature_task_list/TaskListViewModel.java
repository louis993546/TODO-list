package io.github.louistsaitszho.stand_up.feature_task_list;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import org.threeten.bp.LocalDate;

import java.util.ArrayList;
import java.util.List;

import io.github.louistsaitszho.stand_up.core.model.Task;
import io.github.louistsaitszho.stand_up.core.model.TaskState;

public class TaskListViewModel extends ViewModel {

    private ArrayList<Task> fakeList = new ArrayList<>();
    // TODO only expose a non-mutable version
    public MutableLiveData<List<Task>> fakeListLiveData = new MutableLiveData<>();

    public void onButtonAddTaskClicked() {
        fakeList.add(new Task(1, "test", TaskState.TODO, LocalDate.now()));
        fakeListLiveData.setValue(fakeList);
    }


}
