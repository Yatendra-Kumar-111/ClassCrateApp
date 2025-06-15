package com.learningwithteam.classcrate1;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;


public class SubjectsFragment extends Fragment {


    public SubjectsFragment () {

    }

    private SharedViewModel inputDataViewModel;
    private static String subName , msg;
    private LinearLayout btnSub1, btnSub2,btnSub3, btnSub4, btnSub5;
    private TextView textView,sub1, sub2, sub3, sub4, sub5;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_subjects, container, false);

        // Block clicks passing through
        view.setOnTouchListener((v, event) -> true); // Consumes all touch events

        btnSub1 = view.findViewById(R.id.btnSub1);
        btnSub2 = view.findViewById(R.id.btnSub2);
        btnSub3 = view.findViewById(R.id.btnSub3);
        btnSub4 = view.findViewById(R.id.btnSub4);
        btnSub5 = view.findViewById(R.id.btnSub5);


         textView = view.findViewById(R.id.title_subjects);
         sub1 = view.findViewById(R.id.title_subject_name1);
         sub2 = view.findViewById(R.id.title_subject_name2);
         sub3 = view.findViewById(R.id.title_subject_name3);
         sub4 = view.findViewById(R.id.title_subject_name4);
         sub5 = view.findViewById(R.id.title_subject_name5);

        inputDataViewModel = new ViewModelProvider(requireActivity()).get(SharedViewModel.class);

            inputDataViewModel.getData().observe(getViewLifecycleOwner(), data -> {
                if (data != null && !data.isEmpty()) {
                    if(data.equalsIgnoreCase("book")) {
                        msg = data;
                    } else if(data.equalsIgnoreCase("notes")) {
                        msg = data;
                    } else if(data.equalsIgnoreCase("syllabus")) {
                        msg = data;
                    } else if(data.equalsIgnoreCase("models")) {
                        msg = data;
                    } else if(data.equalsIgnoreCase("exam papers")) {
                        msg = data;
                    }
//                    else if(data.equalsIgnoreCase("papers solution")) {
//                        msg = data;
//                    }
                    else if(data.equalsIgnoreCase("videos")) {
                        msg = data;
                    }
//                    else if(data.equalsIgnoreCase("notice")) {
//                        msg = data;
//                    }
//                    Toast.makeText(getContext(), msg + "+++++++++", Toast.LENGTH_SHORT).show();
//                Toast.makeText(getContext(), "Hiii, Msg aa gya home -> sem -> sub", Toast.LENGTH_SHORT).show();
                } else {
                    textView.setText("List is empty");
                }
            });


            
        inputDataViewModel.getArrayList().observe(getViewLifecycleOwner(), list -> {
//            Toast.makeText(getContext(), "in Subject Fragment", Toast.LENGTH_SHORT).show();
//            ArrayList<String> arrayList = list;
            if (list != null && !list.isEmpty()) {
                sub1.setText(list.get(0));
                sub2.setText(list.get(1));
                sub3.setText(list.get(2));
                sub4.setText(list.get(3));
                sub5.setText(list.get(4));
            } else {
                textView.setText("List is empty");
            }

        });







        btnSub1.setOnClickListener(v -> {

            subName = sub1.getText().toString();
            inputDataViewModel.setData(subName);
//            Toast.makeText(getContext(), "*****" + msg, Toast.LENGTH_SHORT).show();
            inputDataViewModel.setData3(msg);

            openSubjectsFragment();
//            ArrayList <String> arrayList2 = new ArrayList<>();
//            arrayList2.add(msg);     // from Home -> Sem -> Sub -> Pdf       &        index is 1
//            arrayList2.add(subName);   //
//            inputDataViewModel.setArrayList(arrayList2);

//            requireActivity().getSupportFragmentManager().beginTransaction()
//                .replace(R.id.frameLayoutSubject, new PdfViewerFragment())
//                .addToBackStack(null)
//                .commit();
        });
        btnSub2.setOnClickListener(v -> {
            subName = sub2.getText().toString();
            inputDataViewModel.setData(subName);
//            Toast.makeText(getContext(),  msg, Toast.LENGTH_SHORT).show();
//            Toast.makeText(getContext(), "*****" + msg, Toast.LENGTH_SHORT).show();
            inputDataViewModel.setData3(msg);

            openSubjectsFragment();

//            ArrayList <String> arrayList2 = new ArrayList<>();
//            arrayList2.add(msg);
//            arrayList2.add(subName);
//            inputDataViewModel.setArrayList(arrayList2);


//            requireActivity().getSupportFragmentManager().beginTransaction()
//                    .replace(R.id.frameLayoutSubject, new PdfViewerFragment())
//                    .addToBackStack(null)
//                    .commit();
        });
        btnSub3.setOnClickListener(v -> {
            subName = sub3.getText().toString();
            inputDataViewModel.setData(subName);

//            Toast.makeText(getContext(), "*****" + msg, Toast.LENGTH_SHORT).show();
            inputDataViewModel.setData3(msg);
            openSubjectsFragment();

//            ArrayList <String> arrayList2 = new ArrayList<>();
//            arrayList2.add(msg);
//            arrayList2.add(subName);
//            inputDataViewModel.setArrayList(arrayList2);


//            requireActivity().getSupportFragmentManager().beginTransaction()
//                    .replace(R.id.frameLayoutSubject, new PdfViewerFragment())
//                    .addToBackStack(null)
//                    .commit();
        });
        btnSub4.setOnClickListener(v -> {
            subName = sub4.getText().toString();
            inputDataViewModel.setData(subName);

//            Toast.makeText(getContext(), "*****" + msg, Toast.LENGTH_SHORT).show();
            inputDataViewModel.setData3(msg);

            openSubjectsFragment();
//            ArrayList <String> arrayList2 = new ArrayList<>();
//            arrayList2.add(msg);
//            arrayList2.add(subName);
//            inputDataViewModel.setArrayList(arrayList2);


//            requireActivity().getSupportFragmentManager().beginTransaction()
//                    .replace(R.id.frameLayoutSubject, new PdfViewerFragment())
//                    .addToBackStack(null)
//                    .commit();
        });
        btnSub5.setOnClickListener(v -> {
            subName = sub5.getText().toString();
            inputDataViewModel.setData(subName);

//            Toast.makeText(getContext(), "*****" + msg, Toast.LENGTH_SHORT).show();
            inputDataViewModel.setData3(msg);



//            ArrayList <String> arrayList2 = new ArrayList<>();
//            arrayList2.add(msg);
//            arrayList2.add(subName);
//            inputDataViewModel.setArrayList(arrayList2);

            openSubjectsFragment();
//            requireActivity().getSupportFragmentManager().beginTransaction()
//                    .replace(R.id.frameLayoutSubject, new PdfViewerFragment())
//                    .addToBackStack(null)
//                    .commit();
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
//                        R.anim.zoom_in,
//                        R.anim.zoom_out
                )
                .replace(R.id.frameLayoutSubject, new PdfViewerFragment())
                .addToBackStack(null)
                .commit();
    }

/*
    @Override
    public void onDestroyView() {
        super.onDestroyView();

        // ViewModel se list clear karo
//        SharedViewModel viewModel = new ViewModelProvider(requireActivity()).get(SharedViewModel.class);
        inputDataViewModel.clearList();
    }
    */

    @Override
    public void onDestroyView() {
        super.onDestroyView();

//        // Restore the ActionBar
//        if (requireActivity() instanceof AppCompatActivity) {
//            ((AppCompatActivity) requireActivity()).getSupportActionBar().show();
//        }
    }




}