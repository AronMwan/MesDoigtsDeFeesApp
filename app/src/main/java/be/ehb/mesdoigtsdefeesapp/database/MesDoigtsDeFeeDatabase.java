package be.ehb.mesdoigtsdefeesapp.database;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import be.ehb.mesdoigtsdefeesapp.DAO.LessonDAO;
import be.ehb.mesdoigtsdefeesapp.DAO.PostDAO;
import be.ehb.mesdoigtsdefeesapp.models.Lesson;
import be.ehb.mesdoigtsdefeesapp.models.Post;

@Database(version = 1, entities = {Post.class, Lesson.class})
public abstract class MesDoigtsDeFeeDatabase extends RoomDatabase{
    private static MesDoigtsDeFeeDatabase INSTANCE;

    public static MesDoigtsDeFeeDatabase getInstance(Context context){
        if(INSTANCE == null){
            //maak database verbinding
            INSTANCE = Room.databaseBuilder(context, MesDoigtsDeFeeDatabase.class, "mesdoigtsdefee.sqlite").build();
        }
        return INSTANCE;
    }

    public abstract PostDAO getPostDAO();
    public abstract LessonDAO getLessonDAO();

}
