package com.learningwithteam.classcrate1;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;


public class SemesterFragment extends Fragment {

    private LinearLayout btnSem1, btnSem2, btnSem3, btnSem4, btnSem5;
    private SharedViewModel inputDataViewModel;
    private static String msg;
    private TextView title, tvSem1, tvSem2, tvSem3, tvSem4, tvSem5, tvSem6,textView;


    public SemesterFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_semester, container, false);

        // Block clicks passing through
        view.setOnTouchListener((v, event) -> true); // Consumes all touch events

        btnSem1 = view.findViewById(R.id.btnSem1);
        btnSem2 = view.findViewById(R.id.btnSem2);
        btnSem3 = view.findViewById(R.id.btnSem3);
        btnSem4 = view.findViewById(R.id.btnSem4);
        btnSem5 = view.findViewById(R.id.btnSem5);

        title = view.findViewById(R.id.title_semester);
        tvSem1 = view.findViewById(R.id.tvSem1);
        tvSem2 = view.findViewById(R.id.tvSem2);
        tvSem3 = view.findViewById(R.id.tvSem3);
        tvSem4 = view.findViewById(R.id.tvSem4);
        tvSem5 = view.findViewById(R.id.tvSem5);


        inputDataViewModel = new ViewModelProvider(requireActivity()).get(SharedViewModel.class);

        inputDataViewModel.getData().observe(getViewLifecycleOwner(), msgFromHomeToSem -> {
            if (msgFromHomeToSem != null && !msgFromHomeToSem.isEmpty()) {
                msg = msgFromHomeToSem.toLowerCase();
            } else {
                Toast.makeText(getContext(), "Error!!!", Toast.LENGTH_SHORT).show();
                title.setText("List is empty");
            }

        });


        btnSem1.setOnClickListener(v -> {
            String msgSendToMethod = tvSem1.getText().toString();

            inputDataViewModel.setData(msg);
            inputDataViewModel.setArrayList(subjectsForAllBtns(msgSendToMethod));
            openSubjectsFragment();
        });
        btnSem2.setOnClickListener(v -> {
            String msgSendToMethod;
                msgSendToMethod = tvSem2.getText().toString();

            inputDataViewModel.setData(msg);
            inputDataViewModel.setArrayList(subjectsForAllBtns(msgSendToMethod));
            openSubjectsFragment();
        });
        btnSem3.setOnClickListener(v -> {
            String msgSendToMethod;
                msgSendToMethod = tvSem3.getText().toString();

            inputDataViewModel.setData(msg);
            inputDataViewModel.setArrayList(subjectsForAllBtns(msgSendToMethod));
            openSubjectsFragment();

        });
        btnSem4.setOnClickListener(v -> {
            String msgSendToMethod;
                msgSendToMethod = tvSem4.getText().toString();

            inputDataViewModel.setData(msg);
            inputDataViewModel.setArrayList(subjectsForAllBtns(msgSendToMethod));
            openSubjectsFragment();

        });
        btnSem5.setOnClickListener(v -> {
            String msgSendToMethod;
                msgSendToMethod = tvSem5.getText().toString();

            inputDataViewModel.setData(msg);
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
                .replace(R.id.frameLayoutSemester, new SubjectsFragment())
                .addToBackStack(null)
                .commit();
    }

    public ArrayList<String> subjectsForAllBtns(String semName) {
        ArrayList<String> subjects = new ArrayList<>();

        switch (semName.toLowerCase()) {
            case "semester i":
                subjects.add("Computer Fundamentals and MS-Office");
                subjects.add("Introduction to Programming using C");
                subjects.add("Business Communication and Soft Skill");
                subjects.add("Introduction to HTML-CSS-XML");
                subjects.add("Mathematics- I");
                break;

            case "semester ii":
                subjects.add("Object Oriented Programming using C++");
                subjects.add("Digital Electronics");
                subjects.add("Data Structure using C/C++");
                subjects.add("Principles of Management");
                subjects.add("Numerical Methods");
                break;

            case "semester iii":
                subjects.add("Data Base Management System");
                subjects.add("E-Commerce and ERP");
                subjects.add("Computer Organization and Architecture");
                subjects.add("Operating System with the case study of UNIX & Windows");
                subjects.add("Statistical Method and Application");
                break;

            case "semester iv":
                subjects.add("JAVA Programming");
                subjects.add("Web Technology with PHP & MySQL");
                subjects.add("Artificial Intelligence");
                subjects.add("Computer Network");
                subjects.add("Optimization Techniques");
                break;

            case "semester v":
                subjects.add("Network Security");
                subjects.add("Visual Basic .NET");
                subjects.add("Computer Graphics");
                subjects.add("System Analysis & Design");
                subjects.add("Design & Analysis of Algorithms");
                break;

            default:
                Toast.makeText(getContext(), "Error in SemesterFragment => " + semName + " -> Subjects",
                        Toast.LENGTH_SHORT).show();
                return null;
        }

        return subjects;
    }

   @Override
   public void onDestroyView() {
       super.onDestroyView();

       // Restore ActionBar if hidden
       if (requireActivity() instanceof AppCompatActivity) {
           ((AppCompatActivity) requireActivity()).getSupportActionBar().show();
       }
   }
}