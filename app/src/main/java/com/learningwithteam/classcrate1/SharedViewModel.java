package com.learningwithteam.classcrate1;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import java.util.ArrayList;

public class SharedViewModel extends ViewModel {

    private final MutableLiveData<String> selectedData = new MutableLiveData<>();
    private final MutableLiveData<ArrayList<String>> arrayListLiveData = new MutableLiveData<>();

    private final MutableLiveData<String> selectedData2 = new MutableLiveData<>();

    private final MutableLiveData<String> selectedDataNew = new MutableLiveData<>();
    private final MutableLiveData<ArrayList<String>> arrayListLiveDataNew = new MutableLiveData<>();
    private final MutableLiveData<String> selectedData3 = new MutableLiveData<>();


    public void setData3(String data3) {
        selectedData3.setValue(data3);
    }

    public LiveData<String> getData3() {
        return selectedData3;
    }


    public void setData(String data) {
        selectedData.setValue(data);
    }

    public LiveData<String> getData() {
        return selectedData;
    }


    public void setData2(String data) {
        selectedData2.setValue(data);
    }

    public LiveData<String> getData2() {
        return selectedData2;
    }


    public void setArrayList(ArrayList<String> list) {
        arrayListLiveData.setValue(list);
    }

    public LiveData<ArrayList<String>> getArrayList() {
        return arrayListLiveData;
    }


    public void setDataNew(String data) {
        selectedDataNew.setValue(data);
    }

    public LiveData<String> getDataNew() {
        return selectedDataNew;
    }


    public void clearList() {
        arrayListLiveData.setValue(new ArrayList<>()); // empty list bhejna
    }


 public void setArrayListNew(ArrayList<String> list2) {
        arrayListLiveDataNew.setValue(list2);
    }

    public LiveData<ArrayList<String>> getArrayListNew() {
        return arrayListLiveDataNew;
    }

    // this code is by chat gpt

    private final MutableLiveData<String> pdfUrl = new MutableLiveData<>();
    private final MutableLiveData<String> selectedSubjectName = new MutableLiveData<>();

    public void setPdfUrl(String url) {
        pdfUrl.setValue(url);
    }

    public LiveData<String> getPdfUrl() {
        return pdfUrl;
    }

    public void setSelectedSubject(String name) {
        selectedSubjectName.setValue(name);
    }

    public LiveData<String> getSelectedSubject() {
        return selectedSubjectName;
    }

}
