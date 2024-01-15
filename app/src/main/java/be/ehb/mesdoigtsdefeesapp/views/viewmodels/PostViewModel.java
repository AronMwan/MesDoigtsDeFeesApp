package be.ehb.mesdoigtsdefeesapp.views.viewmodels;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import be.ehb.mesdoigtsdefeesapp.database.MesDoigtsDeFeeDatabase;
import be.ehb.mesdoigtsdefeesapp.models.Post;

public class PostViewModel extends AndroidViewModel {
    private ExecutorService mExecutorService;
    private MesDoigtsDeFeeDatabase mesDoigtsDeFeeDatabase;

    public PostViewModel(@NonNull Application application) {
        super(application);
        mExecutorService = Executors.newFixedThreadPool(2);
        mesDoigtsDeFeeDatabase = MesDoigtsDeFeeDatabase.getInstance(application);
    }

    public void insertPost(Post post){

        mExecutorService.execute(()->{
            mesDoigtsDeFeeDatabase.getPostDAO().insertPost(post);
        });

    }

    public LiveData<List<Post>> getPostsByTitle(String title){
        return mesDoigtsDeFeeDatabase.getPostDAO().getPostsByTitle(title);
    }

    public LiveData<List<Post>> getAllPosts(){
        return mesDoigtsDeFeeDatabase.getPostDAO().getAllPostByTitle();
    }


}