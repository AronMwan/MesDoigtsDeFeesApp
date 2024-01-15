package be.ehb.mesdoigtsdefeesapp.DAO;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

import be.ehb.mesdoigtsdefeesapp.models.Lesson;


@Dao
public interface LessonDAO {

    @Insert
    void insertLesson(Lesson lesson);

    @Update
    void updateLesson(Lesson lesson);

    @Delete
    void deleteLesson(Lesson lesson);



    @Query("SELECT * FROM Lesson WHERE Id = :id")
    LiveData<Lesson> getLessonById(int id);


    @Query("SELECT * FROM Lesson WHERE Name = :name")
    LiveData<List<Lesson>> getLessonByName(String name);

    @Query("SELECT * FROM Lesson ORDER BY Name ASC")
    LiveData<List<Lesson>> getAllLessons();

    @Query("SELECT * FROM Lesson WHERE SelectedType = :type")
    LiveData<List<Lesson>> getLessonByType(String type);


}
