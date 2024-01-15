package be.ehb.mesdoigtsdefeesapp.views.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.Navigation;

import be.ehb.mesdoigtsdefeesapp.R;
import be.ehb.mesdoigtsdefeesapp.models.Lesson;
import be.ehb.mesdoigtsdefeesapp.views.viewmodels.LessonViewModel;

public class NewLessonFragment extends Fragment {

    private EditText etTitle;
    private EditText etDescription;
    private Spinner spinner;
    private Button button;



    public static NewLessonFragment newInstance() {
        NewLessonFragment fragment = new NewLessonFragment();
        return fragment;
    }

    public NewLessonFragment() {
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_new_lesson, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        LessonViewModel lessonViewModel = new ViewModelProvider(getActivity()).get(LessonViewModel.class);

        etTitle = view.findViewById(R.id.editTextTitle);
        etDescription = view.findViewById(R.id.editTextDescription);
        button = view.findViewById(R.id.buttonCreateLesson);
        spinner = view.findViewById(R.id.spinnerType);

        //Ik maak een adapter aan voor de spinner. Dit helpt om de data van de spinner te krijgen.

        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(requireContext(), R.array.lesson_types, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String title = etTitle.getText().toString();
                String description = etDescription.getText().toString();
                String selectedType = spinner.getSelectedItem().toString();

                Lesson toSave = new Lesson(title, description, selectedType);



                lessonViewModel.insertLesson(toSave);

                Navigation.findNavController(view).navigateUp();

            }
        });

    }
}