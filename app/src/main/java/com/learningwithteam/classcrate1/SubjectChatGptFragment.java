package com.learningwithteam.classcrate1;

import android.graphics.Typeface;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;


import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.fragment.NavHostFragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class SubjectChatGptFragment extends Fragment {

    public SubjectChatGptFragment() {
        // Required empty public constructor
    }


    private SharedViewModel sharedViewModel;
    private int semesterIndex;
    private List<String> subjectList;
    private List<String> pdfUrlList;




    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_subject_chat_gpt, container, false);
    }

/*
    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        sharedViewModel = new ViewModelProvider(requireActivity()).get(SharedViewModel.class);
        semesterIndex = sharedViewModel.getSelectedSemesterIndex().getValue();

        subjectList = new ArrayList<>();
        pdfUrlList = new ArrayList<>();

        // Add subject names and Google Drive links
        if (semesterIndex == 0) {
            subjectList.add("Mathematics-I");
            pdfUrlList.add("https://drive.google.com/uc?export=download&id=YOUR_FILE_ID_1");

            subjectList.add("Introduction to Programming using C");
            pdfUrlList.add("https://drive.google.com/uc?export=download&id=YOUR_FILE_ID_2");

        } else if (semesterIndex == 1) {
            subjectList.add("Mathematics-II");
            pdfUrlList.add("https://drive.google.com/uc?export=download&id=YOUR_FILE_ID_3");

            subjectList.add("Digital Electronics");
            pdfUrlList.add("https://drive.google.com/uc?export=download&id=YOUR_FILE_ID_4");
        }

        LinearLayout layout = view.findViewById(R.id.subjectListLayout);

        for (int i = 0; i < subjectList.size(); i++) {
            String subject = subjectList.get(i);
            String url = pdfUrlList.get(i);

            TextView textView = new TextView(requireContext());
            textView.setText(subject);
            textView.setTextSize(18);
            textView.setPadding(16, 16, 16, 16);
            textView.setTypeface(Typeface.DEFAULT_BOLD);
            textView.setBackgroundResource(android.R.drawable.dialog_holo_light_frame);
            textView.setClickable(true);

            int finalI = i;
            textView.setOnClickListener(v -> {
                sharedViewModel.setSelectedSubject(subjectList.get(finalI));
                sharedViewModel.setPdfUrl(pdfUrlList.get(finalI));

                NavHostFragment.findNavController(this)
                        .navigate(R.id.action_subjectsFragment_to_pdfViewerFragment);
            });

            layout.addView(textView);
        }
    }*/
}