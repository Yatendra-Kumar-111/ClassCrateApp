package com.learningwithteam.classcrate1;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class SemsterYtFragment extends Fragment {

    public SemsterYtFragment() {
        // Required empty public constructor
    }

    private LinearLayout btnSem1, btnSem2, btnSem3, btnSem4, btnSem5, btnSem6;
    private SharedViewModel inputDataViewModel;
    private TextView title, tvSem1, tvSem2, tvSem3, tvSem4, tvSem5, tvSem6;
    private String msg;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_semster_yt, container, false);

        // Block clicks passing through
        view.setOnTouchListener((v, event) -> true); // Consumes all touch events


        btnSem1 = view.findViewById(R.id.btnSem1);
        btnSem2 = view.findViewById(R.id.btnSem2);
        btnSem3 = view.findViewById(R.id.btnSem3);
        btnSem4 = view.findViewById(R.id.btnSem4);
        btnSem5 = view.findViewById(R.id.btnSem5);

        TextView title = view.findViewById(R.id.title_semester_yt);
        TextView tvSem1 = view.findViewById(R.id.tvSem1);
        TextView tvSem2 = view.findViewById(R.id.tvSem2);
        TextView tvSem3 = view.findViewById(R.id.tvSem3);
        TextView tvSem4 = view.findViewById(R.id.tvSem4);
        TextView tvSem5 = view.findViewById(R.id.tvSem5);


        inputDataViewModel = new ViewModelProvider(requireActivity()).get(SharedViewModel.class);

        btnSem1.setOnClickListener(v -> {

            String msgSendToMethod = tvSem1.getText().toString();
            inputDataViewModel.setArrayList(subjectsForAllBtns(msgSendToMethod));
            openSubjectsFragment();
        });

        btnSem2.setOnClickListener(v -> {
            String msgSendToMethod = tvSem2.getText().toString();
            inputDataViewModel.setArrayList(subjectsForAllBtns(msgSendToMethod));
            openSubjectsFragment();
        });

        btnSem3.setOnClickListener(v -> {
            String msgSendToMethod = tvSem3.getText().toString();
            inputDataViewModel.setArrayList(subjectsForAllBtns(msgSendToMethod));
            openSubjectsFragment();
        });

        btnSem4.setOnClickListener(v -> {
            String msgSendToMethod = tvSem4.getText().toString();
            inputDataViewModel.setArrayList(subjectsForAllBtns(msgSendToMethod));
            openSubjectsFragment();
        });

        btnSem5.setOnClickListener(v -> {
            String msgSendToMethod = tvSem5.getText().toString();
            inputDataViewModel.setArrayList(subjectsForAllBtns(msgSendToMethod));
            openSubjectsFragment();
        });

        return view;
    }



    private void openSubjectsFragment() {
        requireActivity().getSupportFragmentManager().beginTransaction()
                .setCustomAnimations(
                        R.anim.slide_in_right,  // enter
                        R.anim.slide_out_left,  // exit
                        R.anim.slide_in_left,   // popEnter
                        R.anim.slide_out_right  // popExit
                )
                .replace(R.id.frameLayoutSemesterYT, new SubjectYtFragment())
                .addToBackStack(null)
                .commit();
    }


    public ArrayList<String> subjectsForAllBtns (String semName) {

        if(semName.equalsIgnoreCase("Semester I")) {
            ArrayList<String> subjects = new ArrayList<>();

            subjects.add("Computer Fundamentals and MS-Office");
            subjects.add("Introduction to Programming using C");
            subjects.add("Business Communication and Soft Skill");
            subjects.add("Introduction to HTML-CSS-XML");
            subjects.add("Mathematics- I");
            return subjects;

        } else if(semName.equalsIgnoreCase("Semester II")) {
            ArrayList<String> subjects = new ArrayList<>();

            subjects.add("Object Oriented Programming using C++");
            subjects.add("Digital Electronics");
            subjects.add("Data Structure using C/C++");
            subjects.add("Principles of Management");
            subjects.add("Numerical Methods");
            return subjects;

        } else if(semName.equalsIgnoreCase("Semester III")) {
            ArrayList<String> subjects = new ArrayList<>();

            subjects.add("Data Base Management System");
            subjects.add("E-Commerce and ERP");
            subjects.add("Computer Organization and Architecture");
            subjects.add("Operating System with the case study of UNIX & Windows");
            subjects.add("Statistical Method and Application");
            return subjects;

        } else if(semName.equalsIgnoreCase("Semester IV")) {
            ArrayList<String> subjects = new ArrayList<>();

            subjects.add("JAVA Programming" );
            subjects.add("Web Technology with PHP & MySQL");
            subjects.add("Artificial Intelligence");
            subjects.add("Computer Network");
            subjects.add("Optimization Techniques");
            return subjects;

        } else if(semName.equalsIgnoreCase("Semester V")) {
            ArrayList<String> subjects = new ArrayList<>();

            subjects.add("Network Security");
            subjects.add("Visual Basic .NET");
            subjects.add("Computer Graphics");
            subjects.add("System Analysis & Design");
            subjects.add("Design & Analysis of Algorithms");
            return subjects;

        }

        else {
            Toast.makeText(getContext(), "Error!!!!! in SemesterFragment => " +semName+ "-> " +
                            "Subjects",
                    Toast.LENGTH_SHORT).show();
            return null;
        }
    }


    @Override
    public void onDestroyView() {
        super.onDestroyView();
        inputDataViewModel.clearList();
    }
}