package be.ehb.mesdoigtsdefeesapp.views.viewmodels;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import java.util.List;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

import be.ehb.mesdoigtsdefeesapp.database.MesDoigtsDeFeeDatabase;
import be.ehb.mesdoigtsdefeesapp.models.Lesson;

public class LessonViewModel extends AndroidViewModel {
    private Executor mExecutorService;
    private MesDoigtsDeFeeDatabase mesDoigtsDeFeeDatabase;

    public LessonViewModel(@NonNull Application application) {
        super(application);
        mExecutorService = Executors.newFixedThreadPool(2);
        mesDoigtsDeFeeDatabase = MesDoigtsDeFeeDatabase.getInstance(application);

    }

    public void insertLesson(Lesson l){
        mExecutorService.execute(()->{
            mesDoigtsDeFeeDatabase.getLessonDAO().insertLesson(l);
        });
    }

    public void updateLesson(Lesson l){
        mExecutorService.execute(()->{
            mesDoigtsDeFeeDatabase.getLessonDAO().updateLesson(l);
        });
    }

    public void deleteLesson(Lesson l){
        mExecutorService.execute(()->{
            mesDoigtsDeFeeDatabase.getLessonDAO().deleteLesson(l);
        });
    }

    public LiveData<List<Lesson>> getAllLessons(){
        return mesDoigtsDeFeeDatabase.getLessonDAO().getAllLessons();
    }

    public LiveData<Lesson> getLessonById(int id) {
        return mesDoigtsDeFeeDatabase.getLessonDAO().getLessonById(id);
    }

    public LiveData<List<Lesson>> getLessonByName(String name) {
        return mesDoigtsDeFeeDatabase.getLessonDAO().getLessonByName(name);
    }

    public LiveData<List<Lesson>> getLessonByType(String type) {
        return mesDoigtsDeFeeDatabase.getLessonDAO().getLessonByType(type);
    }


}