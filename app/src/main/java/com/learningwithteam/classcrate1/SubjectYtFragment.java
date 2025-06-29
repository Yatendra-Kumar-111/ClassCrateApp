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

public class SubjectYtFragment extends Fragment {

    public SubjectYtFragment() {
        // Required empty public constructor
    }

    private SharedViewModel inputDataViewModel;
    private String subName , msg;
    LinearLayout btnSub1, btnSub2,btnSub3, btnSub4, btnSub5;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_subject_yt, container, false);

        // Block clicks passing through
        view.setOnTouchListener((v, event) -> true); // Consumes all touch events

        btnSub1 = view.findViewById(R.id.btnSub1);
        btnSub2 = view.findViewById(R.id.btnSub2);
        btnSub3 = view.findViewById(R.id.btnSub3);
        btnSub4 = view.findViewById(R.id.btnSub4);
        btnSub5 = view.findViewById(R.id.btnSub5);


        TextView textView = view.findViewById(R.id.title_subject);
        TextView sub1 = view.findViewById(R.id.title_subject_name1);
        TextView sub2 = view.findViewById(R.id.title_subject_name2);
        TextView sub3 = view.findViewById(R.id.title_subject_name3);
        TextView sub4 = view.findViewById(R.id.title_subject_name4);
        TextView sub5 = view.findViewById(R.id.title_subject_name5);

        inputDataViewModel = new ViewModelProvider(requireActivity()).get(SharedViewModel.class);

        inputDataViewModel.getArrayList().observe(getViewLifecycleOwner(), list -> {
            if (list != null && !list.isEmpty()) {
                sub1.setText(list.get(0));
                sub2.setText(list.get(1));
                sub3.setText(list.get(2));
                sub4.setText(list.get(3));
                sub5.setText(list.get(4));
            } else {
                textView.setText("Error! Subject List is empty");
            }
        });


        btnSub1.setOnClickListener(v -> {

            subName = sub1.getText().toString();
            msg = sub1.getText().toString();
            inputDataViewModel.setData(subName);
            inputDataViewModel.setData3(msg);

            openYoutubeFragment();
        });

        btnSub2.setOnClickListener(v -> {
            subName = sub2.getText().toString();
            msg = sub2.getText().toString();
            inputDataViewModel.setData(subName);
            inputDataViewModel.setData3(msg);

            openYoutubeFragment();
        });

        btnSub3.setOnClickListener(v -> {
            subName = sub3.getText().toString();
            msg = sub3.getText().toString();
            inputDataViewModel.setData(subName);
            inputDataViewModel.setData3(msg);

            openYoutubeFragment();
        });

        btnSub4.setOnClickListener(v -> {
            subName = sub4.getText().toString();
            msg = sub4.getText().toString();
            inputDataViewModel.setData(subName);
            inputDataViewModel.setData3(msg);

            openYoutubeFragment();
        });

        btnSub5.setOnClickListener(v -> {
            subName = sub5.getText().toString();
            msg = sub5.getText().toString();
            inputDataViewModel.setData(subName);
            inputDataViewModel.setData3(msg);

            openYoutubeFragment();
        });

        return view;
    }


    private void openYoutubeFragment() {
        requireActivity().getSupportFragmentManager().beginTransaction()
                .setCustomAnimations(
                        R.anim.slide_in_right,  // enter
                        R.anim.slide_out_left,  // exit
                        R.anim.slide_in_left,   // popEnter
                        R.anim.slide_out_right  // popExit
                )
                .replace(R.id.frameLayoutSubjectYT, new YoutubeLinkFragment())
                .addToBackStack(null)
                .commit();
    }

}