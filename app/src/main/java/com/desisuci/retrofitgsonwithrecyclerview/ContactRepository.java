package com.desisuci.retrofitgsonwithrecyclerview;

import android.app.Application;
import android.app.ProgressDialog;
import android.content.Context;
import android.util.Log;
import android.widget.Toast;

import java.util.List;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class ContactRepository {
    Retrofit connection = ContactConnection.getInstance();
    //ProgressDialog progressDialog;
    private KontakApi kontakApi = connection.create(KontakApi.class);

    private MutableLiveData<List<Kontak>> allContacts = new MutableLiveData<>();


    public LiveData<List<Kontak>> getAllContact(){
        Call<List<Kontak>> call = kontakApi.getAllContact();
        call.enqueue(new Callback<List<Kontak>>() {
            @Override
            public void onResponse(Call<List<Kontak>> call, Response<List<Kontak>> response) {
                allContacts.setValue(response.body());
            }

            @Override
            public void onFailure(Call<List<Kontak>> call, Throwable t) {
                Log.e("Err : ", t.getMessage());
            }
        });
        return  allContacts;
    }

    public void insert(Kontak kontak,Context context){
        ProgressDialog progressDialog = new ProgressDialog(context);
        progressDialog.setMessage("Saving...");
        //progressDialog.setCancelable(false);
        progressDialog.show();
        Call<Kontak> call = kontakApi.saveContact(kontak);
        call.enqueue(new Callback<Kontak>() {
            @Override
            public void onResponse(Call<Kontak> call, Response<Kontak> response) {
                if (!response.isSuccessful()) {
                    Toast.makeText(context,response.code(),Toast.LENGTH_SHORT).show();
                    progressDialog.dismiss();
                }
                Toast.makeText(context,"Saved contact is success",Toast.LENGTH_SHORT).show();
                getAllContact();
                progressDialog.dismiss();
            }

            @Override
            public void onFailure(Call<Kontak> call, Throwable t) {
                Toast.makeText(context,t.getMessage(),Toast.LENGTH_SHORT).show();
               // progressDialog.dismiss();
            }
        });

    }

    public void delete(int id, Context context){
        ProgressDialog progressDialog = new ProgressDialog(context);
        progressDialog.setMessage("Deleting...");
        //progressDialog.setCancelable(false);
        progressDialog.show();
        Call<Void> call = kontakApi.deleteContact(id);
        call.enqueue(new Callback<Void>() {
            @Override
            public void onResponse(Call<Void> call, Response<Void> response) {
                if (!response.isSuccessful()) {
                    Toast.makeText(context, response.code(), Toast.LENGTH_SHORT).show();
                }
                Toast.makeText(context,"Deleting contact is success",Toast.LENGTH_SHORT).show();
                getAllContact();
                progressDialog.dismiss();
            }

            @Override
            public void onFailure(Call<Void> call, Throwable t) {
                Toast.makeText(context,t.getMessage(),Toast.LENGTH_SHORT).show();
                progressDialog.dismiss();
            }
        });


    }

}
