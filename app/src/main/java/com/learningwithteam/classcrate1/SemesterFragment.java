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

//    private FragmentHomeBinding binding;
    private LinearLayout btnSem1, btnSem2, btnSem3, btnSem4, btnSem5;
//    private SharedViewModel outputViewModel;
    private SharedViewModel inputDataViewModel;
    private static String msg;

// static is removed from textview
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


//        Bundle bundle = new Bundle();

        btnSem1 = view.findViewById(R.id.btnSem1);
        btnSem2 = view.findViewById(R.id.btnSem2);
        btnSem3 = view.findViewById(R.id.btnSem3);
        btnSem4 = view.findViewById(R.id.btnSem4);
        btnSem5 = view.findViewById(R.id.btnSem5);
//        btnSem6 = view.findViewById(R.id.btnSem6);

        title = view.findViewById(R.id.title_semester);
        tvSem1 = view.findViewById(R.id.tvSem1);
        tvSem2 = view.findViewById(R.id.tvSem2);
        tvSem3 = view.findViewById(R.id.tvSem3);
        tvSem4 = view.findViewById(R.id.tvSem4);
        tvSem5 = view.findViewById(R.id.tvSem5);

      /*  if(tvSem1.getText().toString() == null){
            Toast.makeText(getContext(), "Errorrrrrrrrr", Toast.LENGTH_SHORT).show();
            Handler handler = new Handler();
            handler.postDelayed(new Runnable() {
                @Override
                public void run() {

                        Toast.makeText(getContext(), "whyyyyyyyyyyy", Toast.LENGTH_SHORT).show();
                }
            }, 5000);
        }*/




        inputDataViewModel = new ViewModelProvider(requireActivity()).get(SharedViewModel.class);

        inputDataViewModel.getData().observe(getViewLifecycleOwner(), msgFromHomeToSem -> {
            if (msgFromHomeToSem != null && !msgFromHomeToSem.isEmpty()) {
                msg = msgFromHomeToSem.toLowerCase();
//                    Toast.makeText(getContext(), msg + "&&&&&&!", Toast.LENGTH_SHORT).show();

            } else {
                Toast.makeText(getContext(), "Error!!!", Toast.LENGTH_SHORT).show();
                title.setText("List is empty");
            }

        });



        btnSem1.setOnClickListener(v -> {
            String msgSendToMethod = tvSem1.getText().toString();

//            Toast.makeText(getContext(), msgSendToMethod+"!!!!!!!", Toast.LENGTH_SHORT).show();
            inputDataViewModel.setData(msg);
            inputDataViewModel.setArrayList(subjectsForAllBtns(msgSendToMethod));
            openSubjectsFragment();
        });
        btnSem2.setOnClickListener(v -> {
            String msgSendToMethod;
                msgSendToMethod = tvSem2.getText().toString();

            inputDataViewModel.setData(msg);
//            subjectsForAllBtns(msgSendToMethod);
            inputDataViewModel.setArrayList(subjectsForAllBtns(msgSendToMethod));
            openSubjectsFragment();
        });
        btnSem3.setOnClickListener(v -> {
            String msgSendToMethod;
                msgSendToMethod = tvSem3.getText().toString();

//            String msgSendToMethod = tvSem3.getText().toString();
//            subjectsForAllBtns(msgSendToMethod);

            inputDataViewModel.setData(msg);
            inputDataViewModel.setArrayList(subjectsForAllBtns(msgSendToMethod));
            openSubjectsFragment();

        });
        btnSem4.setOnClickListener(v -> {
            String msgSendToMethod;
                msgSendToMethod = tvSem4.getText().toString();

//            String msgSendToMethod = tvSem4.getText().toString();
//            subjectsForAllBtns(msgSendToMethod);

            inputDataViewModel.setData(msg);
            inputDataViewModel.setArrayList(subjectsForAllBtns(msgSendToMethod));
            openSubjectsFragment();

        });
        btnSem5.setOnClickListener(v -> {
            String msgSendToMethod;
                msgSendToMethod = tvSem5.getText().toString();

//            String msgSendToMethod = tvSem5.getText().toString();
//            subjectsForAllBtns(msgSendToMethod);

            inputDataViewModel.setData(msg);
            inputDataViewModel.setArrayList(subjectsForAllBtns(msgSendToMethod));
            openSubjectsFragment();

        });
 /*       btnSem6.setOnClickListener(v -> {
            String msgSendToMethod = tvSem6.getText().toString();
//            subjectsForAllBtns(msgSendToMethod);

            inputDataViewModel.setData(msg);
            inputDataViewModel.setArrayList(subjectsForAllBtns(msgSendToMethod));

            requireActivity().getSupportFragmentManager().beginTransaction()
                    .replace(R.id.frameLayoutSemester, new SubjectsFragment())
                    .addToBackStack(null)
                    .commit();
        });*/








//        View root = binding.getRoot();
//
//        final TextView textView = binding.title;
//        binding.title.setText("BCA CRATE");

        return view;
    }


    private void openSubjectsFragment() {
        requireActivity().getSupportFragmentManager().beginTransaction()
                .setCustomAnimations(
                        R.anim.slide_in_right,  // enter
                        R.anim.slide_out_left,  // exit
                        R.anim.slide_in_left,   // popEnter
                        R.anim.slide_out_right  // popExit
//                        R.anim.fade_in,
//                        R.anim.fade_out
                )
                .replace(R.id.frameLayoutSemester, new SubjectsFragment())
                .addToBackStack(null)
                .commit();
    }

  /*
    public void btnClick (LinearLayout btn, TextView textView) {
        btn.setOnClickListener(v -> {
            String semName = textView.getText().toString();
            Toast.makeText(getContext(), "Drama ho raha hai", Toast.LENGTH_SHORT).show();
            subjectsForAllBtns(semName);

            inputDataViewModel.setArrayList(subjectsForAllBtns(semName));

            requireActivity().getSupportFragmentManager().beginTransaction()
                    .replace(R.id.frameLayoutSemester, new SubjectsFragment())
                    .addToBackStack(null)
                    .commit();
        });
    }
    */




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




/*
    public ArrayList<String> subjectsForAllBtns (String semName) {
//            Toast.makeText(getContext(), semName+" ::: Sem name" , Toast.LENGTH_SHORT).show();

        if(semName.equalsIgnoreCase("Semester I")) {
            ArrayList<String> subject1 = new ArrayList<>();

            subject1.add("Computer Fundamentals and MS-Office");
            subject1.add("Introduction to Programming using C");
            subject1.add("Business Communication and Soft Skill");
            subject1.add("Introduction to HTML-CSS-XML");
            subject1.add("Mathematics- I");
            return subject1;

        } else if(semName.equalsIgnoreCase("Semester II")) {
            ArrayList<String> subject2 = new ArrayList<>();

//            Toast.makeText(getContext(), semName+" -> Subjects" , Toast.LENGTH_SHORT).show();

            subject2.add("Object Oriented Programming using C++");
            subject2.add("Digital Electronics");
            subject2.add("Data Structure using C/C++");
            subject2.add("Principles of Management");
            subject2.add("Numerical Methods");
            return subject2;

        } else if(semName.equalsIgnoreCase("Semester III")) {
            ArrayList<String> subject3 = new ArrayList<>();

//            Toast.makeText(getContext(), semName+" -> Subjects" , Toast.LENGTH_SHORT).show();

            subject3.add("Data Base Management System");
            subject3.add("E-Commerce and ERP");
            subject3.add("Computer Organization and Architecture");
            subject3.add("Operating System with the case study of UNIX & Windows");
            subject3.add("Statistical Method and Application");
            return subject3;

        } else if(semName.equalsIgnoreCase("Semester IV")) {
            ArrayList<String> subject4 = new ArrayList<>();

//            Toast.makeText(getContext(), semName+" -> Subjects" , Toast.LENGTH_SHORT).show();


            subject4.add("JAVA Programming" );
            subject4.add("Web Technology with PHP & MySQL");
            subject4.add("Artificial Intelligence");
            subject4.add("Computer Network");
            subject4.add("Optimization Techniques");
            return subject4;

        } else if(semName.equalsIgnoreCase("Semester V")) {
            ArrayList<String> subject5 = new ArrayList<>();

//            Toast.makeText(getContext(), semName+" -> Subjects" , Toast.LENGTH_SHORT).show();

            subject5.add("Network Security");
            subject5.add("Visual Basic .NET");
            subject5.add("Computer Graphics");
            subject5.add("System Analysis & Design");
            subject5.add("Design & Analysis of Algorithms");
            return subject5;

        }
        else if(semName.equalsIgnoreCase("Semester VI")) {
            ArrayList<String> subjects = new ArrayList<>();
//            Toast.makeText(getContext(), semName+" -> Subjects" , Toast.LENGTH_SHORT).show();
            subjects.add("Project Work\nPractical Work");
            return subjects;
        }

        else {
            Toast.makeText(getContext(), "Error!!! in SemesterFragment => " +semName+ "-> Subjects",
//            Toast.makeText(getContext(), "No Data Share b/w" +semName+ "-> Subjects",
                    Toast.LENGTH_SHORT).show();
        return null;
        }
    }
*/




 /*   @Override
    public void onDestroyView() {
        super.onDestroyView();

        // ViewModel se list clear karo
//        SharedViewModel viewModel = new ViewModelProvider(requireActivity()).get(SharedViewModel.class);
        inputDataViewModel.clearList();
    }*/


   @Override
   public void onDestroyView() {
       super.onDestroyView();

       // Restore ActionBar if hidden
       if (requireActivity() instanceof AppCompatActivity) {
           ((AppCompatActivity) requireActivity()).getSupportActionBar().show();
       }
   }


}