package be.ehb.mesdoigtsdefeesapp.DAO;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

import be.ehb.mesdoigtsdefeesapp.models.Post;

@Dao
public interface PostDAO {


        @Insert
        void insertPost(Post post);

        @Update
        void updatePost(Post post);

        @Delete
        void deletePost(Post post);

        @Query("SELECT * FROM Post ORDER BY Title")
        LiveData<List<Post>> getAllPostByTitle();

        @Query("SELECT * FROM Post WHERE Title LIKE :title")
        LiveData<List<Post>> getPostsByTitle(String title);

        @Query("SELECT * FROM Post WHERE Id = :id")
        LiveData<Post> getPostById(int id);



}


