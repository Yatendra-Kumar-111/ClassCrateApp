package com.learningwithteam.classcrate1;

import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.Intent;
import android.media.Image;
import android.net.Uri;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;

import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


public class YoutubeLinkFragment extends Fragment {

    public YoutubeLinkFragment() {
        // Required empty public constructor
    }

    // Declaring all things
    private LinearLayout btnLink1, btnLink2, btnLink3;
    private TextView title_YTChannelName1, title_YTChannelName2, title_YTChannelName3 , title;
    private String ytUrl1, ytUrl3, ytUrl2,  msg;
    private SharedViewModel inputDataViewModel;

    private String videoId1, videoId2, videoId3;
    private ImageView thumbnailImageView1, thumbnailImageView2, thumbnailImageView3;
    private Image img;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view =  inflater.inflate(R.layout.fragment_youtube_link, container, false);

        ClipboardManager clipboardManager = (ClipboardManager) getActivity().getSystemService(getActivity().CLIPBOARD_SERVICE);

        // Block clicks passing through
        view.setOnTouchListener((v, event) -> true); // Consumes all touch events


        btnLink1 = view.findViewById(R.id.btnLink1);
        btnLink2 = view.findViewById(R.id.btnLink2);
        btnLink3 = view.findViewById(R.id.btnLink3);

        title_YTChannelName1 = view.findViewById(R.id.titleYTChName1);
        title_YTChannelName2 = view.findViewById(R.id.titleYTChName2);
        title_YTChannelName3 = view.findViewById(R.id.titleYTChName3);
        title = view.findViewById(R.id.title);

        thumbnailImageView1 = view.findViewById(R.id.thumbnail1);
        thumbnailImageView2 = view.findViewById(R.id.thumbnail2);
        thumbnailImageView3 = view.findViewById(R.id.thumbnail3);


        inputDataViewModel = new ViewModelProvider(requireActivity()).get(SharedViewModel.class);
//        inputDataViewModel.getData3().observe(getViewLifecycleOwner(), data -> {
//            msg  = data;
//        });
        inputDataViewModel.getData().observe(getViewLifecycleOwner(), data -> {

            if (data != null && !data.isEmpty()) {
                title.setText(data);
                setYTData(data);

            } else {
                Toast.makeText(getActivity() , "Error! In Data Transfer", Toast.LENGTH_SHORT).show();
            }
        });



// -----------------YT Channel Button for YT Open-----------------
        btnLink1.setOnClickListener(v -> {
            Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(ytUrl1));
            startActivity(intent);
        });

        btnLink2.setOnClickListener(v -> {
            Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(ytUrl2));
            startActivity(intent);
        });

        btnLink3.setOnClickListener(v -> {
            Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(ytUrl3));
            startActivity(intent);
        });


// -----------------Long Press Button for YT link Copy-----------------
        btnLink1.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                String textToCopy = ytUrl1;

                if (!textToCopy.isEmpty()) {
                    ClipData clip = ClipData.newPlainText("Copied Text", textToCopy);
                    clipboardManager.setPrimaryClip(clip);
                    Toast.makeText(getActivity(), "Copied: " + textToCopy, Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(getActivity(), "No text to copy!", Toast.LENGTH_SHORT).show();
                }
                return true;
            }
        });

        return view;
    }

// -------------For youtube thumbnail show-------------
    private String extractYoutubeVideoId(String url) {
        String pattern = "(?<=v=|be/|embed/)[^&#?]+";
        Pattern compiledPattern = Pattern.compile(pattern);
        Matcher matcher = compiledPattern.matcher(url);

        if (matcher.find()) {
            return matcher.group();
        }
        return null;
    }

    private void showThumbnail(String videoId, ImageView thumbnailImageView) {
        if (videoId != null) {
            String thumbnailUrl = "https://img.youtube.com/vi/" + videoId + "/0.jpg";
//            String thumbnailUrl = "https://img.youtube.com/vi/" + videoId + "/hqdefault.jpg";
            Glide.with(this)
                    .load(thumbnailUrl)
                    .into(thumbnailImageView);
        }
    }

    private void setThumbnail(String ytUrl1, String ytUrl2, String ytUrl3) {
        videoId1 = extractYoutubeVideoId(ytUrl1);
        videoId2 = extractYoutubeVideoId(ytUrl2);
        videoId3 = extractYoutubeVideoId(ytUrl3);
        showThumbnail(videoId1, thumbnailImageView1);
        showThumbnail(videoId2, thumbnailImageView2);
        showThumbnail(videoId3, thumbnailImageView3);
    }

    private void setThumbnail(String ytUrl1, String ytUrl3) {
        videoId1 = extractYoutubeVideoId(ytUrl1);
        videoId3 = extractYoutubeVideoId(ytUrl3);

        showThumbnail(videoId1, thumbnailImageView1);

        thumbnailImageView2.setImageResource(R.drawable.yt_link_computer_fundamental);

        showThumbnail(videoId3, thumbnailImageView3);
    }

    private void openYouTubeLink(String url) {
        String ytUrl = url;
        Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(ytUrl));
        startActivity(intent);
    }

    private void setTitle_YTChannelName (String name1, String name2, String name3) {
        this.title_YTChannelName1.setText(name1.toUpperCase());
        this.title_YTChannelName2.setText(name2.toUpperCase());
        this.title_YTChannelName3.setText(name3.toUpperCase());
    }
// Note:- playlist ka thumbnail show nahi ho sakta lekin single video ka ho sakta hai

    private void setYTData (String data) {

        String aC = "Apna College", cWH = "CodeWithHarry", cHelp =
                "CodeHelp", coderArmy = "Coder Army", gSm = "Gate Smashers",
                lC = "Learn Coding", cWall = "College Wallah", nesoAca = "Neso Academy", easyEng
                = "EASY ENGINEERING", knowGate = "KnowledgeGATE", unbeatenLea = "Unbeaten Learning"
                ;
        if (data != null && !data.isEmpty()) {

           switch(data) {
               case "Computer Fundamentals and MS-Office" :
                   ytUrl1 = "https://youtube.com/playlist?list=PL-aCB6EV0VsfOgLJzTRQSYwUi8Yfc70Dk&si=pqPtycbkcHo18x1f";
                   ytUrl2 = "https://youtube.com/playlist?list=PLs5_Rtf2P2r79nPPJ8xFIV7iDvhNmTPeK&si=T2xGdoVOBAV3s8h7" ;
                   ytUrl3 = "https://youtube.com/playlist?list=PLeUy5pFI21O8btGoQNLUr9-wq_2vB8pdc&si=zEDmhu-CMhoXaUTR";
                    setThumbnail("https://youtu.be/fytedSoQvz8?si=UGPxBh5y5RRfZ2S6",
                            "https://youtu.be/qnHuTWJFHlE?si=sBHR2RsR5C3wFEul");

                   setTitle_YTChannelName("theBCAstudent", "Unacademy - Kreatryx Gate",
                           "Study With Harshit");
                   break;

               case "Introduction to Programming using C" :
                   ytUrl1 = "https://www.youtube.com/watch?v=7Dh73z3icd8&list=PLu0W_9lII9aiXlHcLx-mDH1Qul38wD3aR";
                   ytUrl2 = "https://www.youtube.com/watch?v=irqbmMNs2Bo&pp=ygUUYyBwcm9nYW1taW5nIHBsYWxpc3Q%3D";
                   ytUrl3 = "https://www.youtube.com/watch?v=kVIOEdrigWY&list=PLmRclvVt5DtksgReOH3s7R1_cb1QA8vrb";
                   setThumbnail(ytUrl1, ytUrl2, ytUrl3);
                   setTitle_YTChannelName(cWH,
                           aC, "Codeitup");
                   break;

               case "Business Communication and Soft Skill" :
                   ytUrl1 = "https://www.youtube.com/watch?v=BitO6ccFPws&list=PLsh2FvSr3n7eovNQqZ90abqSJyqYGs-GT";
                   ytUrl2 = "https://www.youtube.com/watch?v=fyzmCU931QE&list=PLnyJK8kcdCduqkQ7rWxHyIic6_nOeUS5W";
                   ytUrl3 = "https://youtube.com/playlist?list=PL9dEomJZklDpx4odNXIi6ZoCcogIOmz7b&si=MqQC-zkIeVpXHa-Z";
                   setThumbnail("https://youtu.be/BitO6ccFPws?si=NxU3xXItnDNfkFzH",
                           "https://youtu.be/fyzmCU931QE?si=_oCbfYnZRAUTl_-U",
                           "https://youtu.be/rCh3nOLYBDE?si=ZOdZScHHo4x_1mmH");

                   setTitle_YTChannelName("DWIVEDI GUIDANCE", "ACCOUNTING SEEKHO", "Mahaveer PG " +
                           "College");
                   break;

               case "Introduction to HTML-CSS-XML" :
                   ytUrl1 = "https://www.youtube.com/watch?v=6mbwJ2xhgzM&list=PLu0W_9lII9agiCUZYRsvtGTXdxkzPyItg";
                   ytUrl2 = "https://youtube.com/playlist?list=PL-5LSxEGO9SCDhCEMshMc9uiWJTYZewNK&si=WZZoGlYryOkQcF-O";
                   ytUrl3 = "https://www.youtube.com/watch?v=HGTJBPNC-Gw&pp=ygURaHRtbCBjc3MgcGxheWxpc3Q%3D";
                   setThumbnail("https://youtu.be/6mbwJ2xhgzM?si=Jup0w4Zym9IxFcvC",
                           "https://youtu.be/HcOc7P5BMi4?si=EaZkm6VopEnjvAoq",
                           ytUrl3);
                   setTitle_YTChannelName(cWH, "Apna College & ....", "Bro Code");
                   break;

               case "Mathematics- I" :
                   ytUrl1 = "https://youtube.com/playlist?list=PLU6SqdYcYsfKV1QmzQNtzMuIH7mq5qb62&si=IdvbC3KoxEuyRHxs";
                   ytUrl2 = "https://youtube.com/playlist?list=PLHz3_2lRlEzpYEFBz1FHOmXYUgmgeQHfy&si=KKFmK4QYYeU1zpZw";
                   ytUrl3 = "https://youtube.com/playlist?list=PLEHGYFbPuuMFwjyLadgPI3UFsWj4zMJdj&si=blCHbRHz2KAA2epr";
                   setThumbnail("https://youtu.be/oWtoDPiZQJg?si=0I3-1srZ77TPmhZc",
                           "https://youtu.be/du-nL4ObLOA?si=-GIkkzmIt79MztSM",
                           "https://youtu.be/cDml8ML-us0?si=DzIBpusX8MFGAVWR");
                   setTitle_YTChannelName("Dr.Gajendra Purohit", "The Science Fever", "Dream Maths");
                   break;


               case "Object Oriented Programming using C++" :
                   ytUrl1 = "https://www.youtube.com/watch?v=mlIUKyZIUUU&pp=ygUWT09QUyB3aXRoIGMrKyBwbGF5bGlzdA%3D%3D";
                   ytUrl2 = "https://www.youtube.com/watch?v=e7sAf4SbS_g&pp=ygUWT09QUyB3aXRoIGMrKyBwbGF5bGlzdA%3D%3D";
                   ytUrl3 = "https://youtube.com/playlist?list=PLQEaRBV9gAFujcBWJhBT2XXsuMlIfETBy&si=zLlmx6hWzVD23_ow";
                   setThumbnail(ytUrl1,
                           ytUrl2,
                           "https://youtu.be/iw1Xf_33YM0?si=vbXVtw1FTAVpP47U");
                   setTitle_YTChannelName(aC, cWall, coderArmy);
                   break;

               case "Digital Electronics" :
                   ytUrl1 = "https://youtube.com/playlist?list=PLxCzCOWd7aiGmXg4NoX6R31AsC5LeCPHe&si=JinPu9v3Dw1XECTv";
                   ytUrl2 = "https://www.youtube.com/watch?v=M0mx8S05v60&list=PLBlnK6fEyqRjMH3mWf6kwqiTbT798eAOm";
                   ytUrl3 = "https://youtu.be/pHNbm-4reIc?si=1Xvdo6bYvcLP5AP0";

                   setThumbnail("https://youtu.be/47u7b2yh7s8?si=RUX-b9Iqksgbqytb",
                           "https://youtu.be/M0mx8S05v60?si=M9SO14gt1jQGeSrq",
                           ytUrl3);
                   setTitle_YTChannelName(gSm, nesoAca, "KnowledgeGATE by Sanchit Sir");
                   break;

               case "Data Structure using C/C++" :
                   ytUrl1 = "https://www.youtube.com/watch?v=VTLCoHnyACE&list=PLfqMhTWNBTe137I_EPQd34TsgV6IO55pt";
                   ytUrl2 = "https://www.youtube.com/watch?v=WQoB2z67hvY&list=PLDzeHZWIZsTryvtXdMr6rPh4IDexB5NIA";
                   ytUrl3 = "https://www.youtube.com/watch?v=y3OOaXrFy-Q&list=PLQEaRBV9gAFu4ovJ41PywklqI7IyXwr01";
                   setThumbnail("https://youtu.be/VTLCoHnyACE?si=p1wbNpbZ7X7CPR-O",
                           "https://youtu.be/WQoB2z67hvY?si=6ndGt6wTPiHoDfwc",
                           "https://youtu.be/2Gexv2eld4Y?si=zTc0uhkqYVuCoili");
                   setTitle_YTChannelName(aC, "CodeHelp - by Babbar", coderArmy);
                   break;

               case "Principles of Management" :
                   ytUrl1 = "https://www.youtube.com/watch?v=c8z2CS8jvbs&pp=ygUgcHJpbmNpcGxlIG9mIG1hbmFnZW1lbnQgcGxheWxpc3Q%3D";
                   ytUrl2 = "https://www.youtube.com/watch?v=kTWyt6KC9Jw&list=PLaAhQ2ofZZRBjpgXHPpWF0sYwiLD5Gh1k";
                   ytUrl3 = "https://youtube.com/playlist?list=PLzs7q4LSx_lTd42jaMK45vE2fWwhxcJzp&si=cb3CesW8ZF3PQ-CH";
                   setThumbnail(ytUrl1,
                           "https://youtu.be/kTWyt6KC9Jw?si=M77t6NL3WT_pa4aX",
                           "https://youtu.be/eH6VgHs5mwU?si=vmm4r6E5zaMe85hx");
                   setTitle_YTChannelName("Commerce Wallah by PW", "Accounting MasterClass",
                           "Unbeaten Learning");
                   break;

               case "Numerical Methods" :
                   ytUrl1 = "https://www.youtube.com/watch?v=Y6sx1u5cDmo&list=PLEHGYFbPuuMG-Jn-P7C0u8btns0tQE8Cf";
                   ytUrl2 = "https://youtube.com/playlist?list=PLHz3_2lRlEzqy9OQ6cSeigDMYstN_wWVL&si=wy5JiIwD0ujPZ9GN";
                   ytUrl3 = "https://youtube.com/playlist?list=PLrGv400rgYyTz8DxPpBDd2S8MwFejvkai&si=Y-cR0LgYXIia6ybA";
                   setThumbnail("https://youtu.be/Y6sx1u5cDmo?si=89uD-m8iWwTO6CaN",
                           "https://youtu.be/8er5fOmwtSI?si=ncICJlQUsWjnQnDo",
                           "https://youtu.be/wPeEJf6C78w?si=iCMYm-_kWl7BBSTy");
                   setTitle_YTChannelName("Dream Maths", "The Science Fever", "Tutorial Classes by Garima Singh");
                   break;

               case "Data Base Management System" :
                   ytUrl1 = "https://www.youtube.com/watch?v=kBdlM6hNDAE&list=PLxCzCOWd7aiFAN6I8CuViBuCdJgiOkT2Y";
                   ytUrl2 = "https://youtube.com/playlist?list=PLqleLpAMfxGDEClbx9ymd-KWDDJrx_W8C&si=qvT1K-x3xNVrF-w7";
                   ytUrl3 = "https://www.youtube.com/watch?v=YRnjGeQbsHQ&list=PLmXKhU9FNesR1rSES7oLdJaNFgmuj0SYV&pp=0gcJCV8EOCosWNin";
                   setThumbnail("https://youtu.be/3EJlovevfcA?si=DRb1oANXEjXGITf6",
                           "https://youtu.be/H9ICBRHz7Lo?si=mjHKoL8nSXY9ekBp",
                           ytUrl3);
                   setTitle_YTChannelName(gSm, lC, knowGate);
                   break;

               case "E-Commerce and ERP" :
                   ytUrl1 = "https://www.youtube.com/watch?v=-7PljEN-NIo&list=PLV8vIYTIdSnbh5dOzzOGYABSZLdguEbUG";
                   ytUrl2 = "https://youtube.com/playlist?list=PLzs7q4LSx_lQIoaL1e0GrXtex_HCtDjbi&si=GancTKJ52M10FLXK";
                   ytUrl3 = "https://youtube.com/playlist?list=PLU2YP04_LRDtJnxFvR_0IMP5PO9ZRMidz&si=xT3ZKZVhwwtPAHgX";
                   setThumbnail("https://youtu.be/-7PljEN-NIo?si=5-6yV90_0s_73uuH",
                           "https://youtu.be/vJ6gKcySu6E?si=_NhAgmnyfhn1SBcK",
                           "https://youtu.be/aYFRKceSX5o?si=kk0ElvqzNwXw_S6g");
                   setTitle_YTChannelName("EASY ENGINEERING", unbeatenLea, "Mathur Sir Classes");
                   break;


// working start from here,
               case "Computer Organization and Architecture" :
                   ytUrl1 = "https://www.youtube.com/watch?v=L9X7XXfHYdU&list=PLxCzCOWd7aiHMonh3G6QNKq53C6oNXGrX";
                   ytUrl2 = "https://www.youtube.com/watch?v=Ol8D69VKX2k&list=PLBlnK6fEyqRgLLlzdgiTUKULKJPYc0A4q";
                   ytUrl3 = "https://www.youtube.com/watch?v=DsK35f8wyUw&list=PLmXKhU9FNesS4B30OmgxP7nrzq1UhiMiv";
                   setThumbnail("https://youtu.be/L9X7XXfHYdU?si=NhmiDAiT3w5m2OsL",
                           "https://youtu.be/Ol8D69VKX2k?si=Q-fMNqNWOcqHB93I",
                           "https://youtu.be/DsK35f8wyUw?si=H3nriZrI9BXlQ1pR");
                   setTitle_YTChannelName(gSm, nesoAca, knowGate);
                   break;

               case "Operating System with the case study of UNIX & Windows" :
                   ytUrl1 = "https://www.youtube.com/watch?v=bkSWJJZNgf8&list=PLxCzCOWd7aiGz9donHRrE9I3Mwn6XdP8p";
                   ytUrl2 = "https://www.youtube.com/watch?v=xw_OuOhjauw&list=PLmXKhU9FNesSFvj6gASuWmQd23Ul5omtD";
                   ytUrl3 = "https://www.youtube.com/watch?v=vBURTt97EkA&list=PLBlnK6fEyqRiVhbXDGLXDk_OQAeuVcp2O";
                   setThumbnail("https://youtu.be/bkSWJJZNgf8?si=pcCijNTCuMNfMnb-",
                           "https://youtu.be/xw_OuOhjauw?si=Pq_bDOVxUKYCW0JK",
                           "https://youtu.be/vBURTt97EkA?si=fBBBZvbtnyRmaIus");
                   setTitle_YTChannelName(gSm,
                           knowGate,
                           nesoAca);
                   break;

               case "Statistical Method and Application" :
                   ytUrl1 = "https://www.youtube.com/watch?v=df_Rtm4HmJg&list=PLaAhQ2ofZZRCIhJiozZBb9oDOpOoXhWjc";
                   ytUrl2 = "https://www.youtube.com/watch?v=pdH4YYoOdt4&list=PLEHGYFbPuuMGlKpJKcKHleeZ0RN2RI0t1";
                   ytUrl3 = "https://youtube.com/playlist?list=PLsh2FvSr3n7dQjUpTrsv34gjO-D7H47zG&si=_-9DATFGPen-oKh9";
                   setThumbnail("https://youtu.be/df_Rtm4HmJg?si=s6nFgGKwhBRttDMt",
                       "https://youtu.be/pdH4YYoOdt4?si=YBFnpZNHdKqxQckP",
                       "https://youtu.be/YnV46UNPWEY?si=nCXUIHkjTzbVAZmu");
                   setTitle_YTChannelName("ACCOUNTING MASTERCLASS",
                           "DREAM MATHS",
                           "DWIVEDI GUIDANCE");
                   break;

               case "JAVA Programming" :
                   ytUrl1 = "https://www.youtube.com/watch?v=yRpLlJmRo2w&list=PLfqMhTWNBTe3LtFWcvwpqTkUSlB32kJop";
                   ytUrl2 = "https://www.youtube.com/watch?v=ntLJmHOJ0ME&list=PLu0W_9lII9agS67Uits0UnJyrYiXhDS6q";
                   ytUrl3 = "https://youtube.com/playlist?list=PLsyeobzWxl7pe_IiTfNyr55kwJPWbgxB5&si=qh1hYVi8G0xkHmqf";
                   setThumbnail("https://youtu.be/yRpLlJmRo2w?si=K3MoKEfcDw0xpi8O",
                       "https://youtu.be/ntLJmHOJ0ME?si=4YgKbDh-li68V4NQ",
                   "https://youtu.be/bm0OyhwFDuY?si=EwtZBTlmCaAlR_m0");
                   setTitle_YTChannelName(aC,
                           cWH,
                           "Telusko  (English)");
                   break;

               case "Web Technology with PHP & MySQL" :
                   ytUrl1 = "https://www.youtube.com/watch?v=at19OmH2Bg4&list=PLu0W_9lII9aikXkRE0WxDt1vozo3hnmtR";
                   ytUrl2 = "https://www.youtube.com/watch?v=a_qREkJ78f4&list=PL0b6OzIxLPbyrzCMJOFzLnf_-_5E_dkzs";
                   ytUrl3 = "https://www.youtube.com/watch?v=D4DXbRsQAOA&list=PL8p2I9GklV44cSOlKzB_0TrzxEgwfvicK";
                   setThumbnail("https://youtu.be/at19OmH2Bg4?si=pgN6JC4RZ3yRZhGD",
                            "https://youtu.be/a_qREkJ78f4?si=n4L94ov15wTHHgrT",
                           "https://youtu.be/D4DXbRsQAOA?si=K0Gqnq5TLRpHL9Fr");
                   setTitle_YTChannelName(cWH,
                           "YAHU BABA (RECOMMENDED)",
                           "CODE STEP BY STEP");
                   break;

               case "Artificial Intelligence" :
                   ytUrl1 = "https://www.youtube.com/watch?v=uB3i-qV6VdM&list=PLxCzCOWd7aiHGhOHV-nwb0HR5US5GFKFI&pp=0gcJCV8EOCosWNin";
                   ytUrl2 = "https://www.youtube.com/watch?v=FpvnF7_-tW8&list=PLV8vIYTIdSnYsdt0Dh9KkD9WFEi7nVgbe";
                   ytUrl3 = "https://www.youtube.com/watch?v=yiXAmkimZRQ&pp=ygULYWkgcGxheWxpc3Q%3D";
                   setThumbnail("https://youtu.be/uB3i-qV6VdM?si=D7ZrjnJMklU3TlrK",
                       "https://youtu.be/FpvnF7_-tW8?si=36ll7ByDtR3DLpJV",
                       ytUrl3);
                   setTitle_YTChannelName(gSm,
                           easyEng,
                           knowGate);
                   break;


               case "Computer Network" :
                   ytUrl1 = "https://www.youtube.com/watch?v=JFF2vJaN0Cw&list=PLxCzCOWd7aiGFBD2-2joCpWOLUrDLvVV_";
                   ytUrl2 = "https://www.youtube.com/watch?v=VwN91x5i25g&list=PLBlnK6fEyqRgMCUAG0XRw78UA8qnv6jEx";
                   ytUrl3 = "https://www.youtube.com/watch?v=q3Z3Qa1UNBA&list=PLmXKhU9FNesSjFbXSZGF8JF_4LVwwofCd";
                   setThumbnail("https://youtu.be/JFF2vJaN0Cw?si=_UyW5AbqlVdybsmT",
                       "https://youtu.be/VwN91x5i25g?si=ueKJydHTqLiLpa-D",
                       "https://youtu.be/q3Z3Qa1UNBA?si=Bqr10_fg5fDnY_W3");
                   setTitle_YTChannelName(gSm, nesoAca, knowGate);
                   break;


               case "Optimization Techniques" :
                   ytUrl1 = "https://www.youtube.com/watch?v=knZrhVkZ71Q&list=PLU6SqdYcYsfLewoQPYjgg7SMBLjSV704v";
                   ytUrl2 = "https://www.youtube.com/watch?v=3rdpmYavMDs&list=PLEHGYFbPuuMFV4_ZNPO6fXa4kZ0EM0vP1";
                   ytUrl3 = "https://youtube.com/playlist?list=PLoqncgBllXqvf_KvGvbCy65bL6WahsnCA&si=qVq89yXcwJtQ0D_Y";
                   setThumbnail("https://youtu.be/knZrhVkZ71Q?si=CQcLcxQwrCw46IEk",
                       "https://youtu.be/3rdpmYavMDs?si=juh2z9gR_eMEm6lA",
                       "https://youtu.be/q5aZTH22Hb0?si=PD96BBhwwmQFt9FE");
                   setTitle_YTChannelName("DR. GAJENDRA",  "DREAM MATHS",  "Maths with Sakshi");
                   break;


               case "Network Security" :
                   ytUrl1 = "https://www.youtube.com/watch?v=JoeiLuFNBc4&list=PLBlnK6fEyqRgJU3EsOYDTW7m6SUmW6kII";
                   ytUrl2 = "https://www.youtube.com/watch?v=XFl1NSkJwXk&list=PLHEcKKWWhXy8O8L8QqBaAlQ_2GTINAYAu";
                   ytUrl3 = "https://youtube.com/playlist?list=PLYwpaL_SFmcArHtWmbs_vXX6soTK3WEJw&si=bk8r2otgV-KMFpdb";
                   setThumbnail("https://youtu.be/JoeiLuFNBc4?si=8uk1elFhkVjMtYfi",
                           "https://youtu.be/XFl1NSkJwXk?si=WOV788j5CE4QXfjE",
                           "https://youtu.be/CrLRFn5O_F8?si=GU9hAilqvxjFwrU_");
                   setTitle_YTChannelName(nesoAca, "EDU DESIRE", "5 Minutes Engineering");
                   break;


               case "Visual Basic .NET" :
                   ytUrl1 = "https://www.youtube.com/watch?v=gcv_Z8tjw6k&list=PLOd2apPiwn-amApBElVDAZaF6zaSsBEPF";
                   ytUrl2 = "https://www.youtube.com/watch?v=7Y4PD8I83g0&pp=ygUUdmlzdWFsIGJhc2ljIGRvdCBuZXQ%3D";
                   ytUrl3 = "https://www.youtube.com/watch?v=ue8twDT4trM&list=PLckngGUpesHEqF1QHubWLHxE0SNiRkf-R&pp=0gcJCV8EOCosWNin";
                   setThumbnail("https://youtu.be/gcv_Z8tjw6k?si=R_jhONLjmB9HCbdi",
                       ytUrl2,
                       "https://youtu.be/ue8twDT4trM?si=IVMfwief5DkoWFnx");
                   setTitle_YTChannelName("SARKAR STUDY WAVES EDUCATION (RECOMMENDED)",
                           "AUTOMATE WITH RAKESH",
                           "CODING WITH CYBOTECH");
                   break;


               case "System Analysis & Design" :
                   ytUrl1 = "https://www.youtube.com/watch?v=kuy3lj2zGfI&list=PLWxTHN2c_6cbuRXdCpsYYMxy0N4SSfIX9";
                   ytUrl2 = "https://www.youtube.com/watch?v=-ns1BPpF9xQ&list=PLpTOe981SqUcssflSvQMa7UnoPm4_JaJc";
                   ytUrl3 = "https://youtube.com/playlist?list=PLi81x6d2Os_8Sa8HifiFruWK6wmgG3Wrg&si=EBlB_IEZs_HFXdi1";
                   setThumbnail("https://youtu.be/kuy3lj2zGfI?si=le9ONJPEse1stP2Z",
                       "https://youtu.be/-ns1BPpF9xQ?si=yhSpO8dPgM7ISojc",
                       "https://youtu.be/lGe74JlhG7I?si=hvGLf_r7pmxj4xuy");
                   setTitle_YTChannelName("TJ WEBDEV", "Elina Classes", "Gursimran Singh Dhillon");
                   break;


               case "Computer Graphics" :
                   ytUrl1 = "https://www.youtube.com/watch?v=uTBKa1PSyf8&list=PLYwpaL_SFmcAtxMe7ahYC4ZYjQHun_b-T";
                   ytUrl2 = "https://www.youtube.com/watch?v=NmMky9Pg8Yc&list=PLrjkTql3jnm9cY0ijEyr2fPdwnH-0t8EY";
                   ytUrl3 = "https://youtube.com/playlist?list=PL4yL5rqgtVtq1NSGGz8ugZHV9PVk8xvQI&si=47-jgyZ95e-UUbtx";
                   setThumbnail("https://youtu.be/uTBKa1PSyf8?si=jp1l2bVYZXRW75lw",
                       "https://youtu.be/NmMky9Pg8Yc?si=rUSVsPG86ofOM7mL",
                       "https://youtu.be/l95BztHFk5g?si=tGm3EjL4KRhakUvm");
                   setTitle_YTChannelName("5 MINUTES \nENGINEERING", "EDUCATION 4U", "Vinay Mishra");
                   break;


               case "Design & Analysis of Algorithms" :
                   ytUrl1 = "https://www.youtube.com/watch?v=u8JZ9gU5o4g&list=PLxCzCOWd7aiHcmS4i14bI0VrMbZTUvlTa";
                   ytUrl2 = "https://www.youtube.com/watch?v=h1mAUcDXCG0&pp=ygUhZGVzaWduIGFuZCBhbmFseXNpcyBvZiBhbGdvcml0aG1z";
                   ytUrl3 = "https://youtube.com/playlist?list=PLBlnK6fEyqRhoF3cPp0mgOZPuXeu84nAd&si=LwWan8ocUHmLiIbX";
                   setThumbnail("https://youtu.be/u8JZ9gU5o4g?si=Cgex4qYBFOc8ux3f",
                       ytUrl2,
                       "https://youtu.be/UglQN5S9-lc?si=I3M2wDoQk4bR2TIf");
                   setTitle_YTChannelName(gSm, "5 MINUTES ENGINEERING", nesoAca);
                   break;

               default:
                   Toast.makeText(getActivity(), "Error! In Finding Links ", Toast.LENGTH_SHORT).show();
           }
        }
    }

}


