package be.ehb.mesdoigtsdefeesapp.models;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity
public class Lesson {

    @PrimaryKey(autoGenerate = true)
    private int Id;
    private String Name;
    private String Description;
    private String SelectedType;

    public Lesson() {
    }

    public Lesson(String name, String description, String type) {
        Name = name;
        Description = description;
        SelectedType = type;

    }

    public int getId() {
        return Id;
    }

    public void setId(int id) {
        Id = id;
    }

    public String getSelectedType() {
        return SelectedType;
    }

    public void setSelectedType(String selectedType) {
        SelectedType = selectedType;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public String getDescription() {
        return Description;
    }

    public void setDescription(String description) {
        Description = description;
    }
}
