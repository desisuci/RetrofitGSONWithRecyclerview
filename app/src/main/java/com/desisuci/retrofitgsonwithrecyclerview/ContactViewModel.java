package com.desisuci.retrofitgsonwithrecyclerview;

import android.app.Application;
import android.content.Context;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

public class ContactViewModel extends AndroidViewModel {
    private LiveData<List<Kontak>> allContacts = new MutableLiveData<>();
    private ContactRepository repository;
    Context context;

    public ContactViewModel(@NonNull Application application) {
        super(application);
        repository = new ContactRepository();
        allContacts = repository.getAllContact();
    }

    public LiveData<List<Kontak>> getListContact() {
        allContacts = repository.getAllContact();
        return allContacts;
    }
    public void insertContact(Kontak kontak, Context context){
        repository.insert(kontak,context);
    }

    //public void updateContact(Kontak kontak){repository.update();}

    public void deleteContact(int id, Context context){repository.delete(id,context);}
}
