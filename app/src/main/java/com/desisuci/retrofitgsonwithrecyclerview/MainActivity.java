package com.desisuci.retrofitgsonwithrecyclerview;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;

import android.view.View;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

public class MainActivity extends AppCompatActivity {
    RecyclerView recyclerView;
    private ContactViewModel contactViewModel;
    FloatingActionButton fabAdd;
    public static  final int ADD_CONTACT_REQUEST = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        RecyclerView recyclerView = findViewById(R.id.recycler_view);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setHasFixedSize(true);

        final KontakAdapter adapter = new KontakAdapter();
        recyclerView.setAdapter(adapter);

        contactViewModel = ViewModelProviders.of(this).get(ContactViewModel.class);
        contactViewModel.getListContact().observe(this, new Observer<List<Kontak>>() {
            @Override
            public void onChanged(List<Kontak> kontaks) {
                adapter.setKontaks(kontaks);
                Toast.makeText(MainActivity.this,"onChange",Toast.LENGTH_SHORT).show();
            }
        });

        fabAdd = (FloatingActionButton) findViewById(R.id.btn_fab);
        fabAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent add = new Intent(MainActivity.this,InputKontak.class);
                startActivityForResult(add,ADD_CONTACT_REQUEST);
            }
        });

        new ItemTouchHelper(new ItemTouchHelper.SimpleCallback(0,ItemTouchHelper.LEFT | ItemTouchHelper.RIGHT) {
            @Override
            public boolean onMove(@NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder, @NonNull RecyclerView.ViewHolder target) {
                return false;
            }

            @Override
            public void onSwiped(@NonNull RecyclerView.ViewHolder viewHolder, int direction) {

                AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
                builder.setMessage("Apakah data ingin dihapus?")
                        .setTitle("Hapus Data");

                builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        int id = adapter.getContactAt(viewHolder.getAdapterPosition()).getContactId();
                        contactViewModel.deleteContact(id,MainActivity.this);

                    }
                });
                builder.setNegativeButton("No", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        adapter.notifyDataSetChanged();
                    }
                });

                AlertDialog dialog = builder.create();
                dialog.show();

            }
        }).attachToRecyclerView(recyclerView);

}

        @Override
        protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data){
        super.onActivityResult(requestCode,resultCode,data);

        if(requestCode == ADD_CONTACT_REQUEST && resultCode == RESULT_OK){
            String name = data.getStringExtra(InputKontak.EXTRA_NAME);
            String email = data.getStringExtra(InputKontak.EXTRA_EMAIL);
            String alamat = data.getStringExtra(InputKontak.EXTRA_ADDRESS);
            String nohp = data.getStringExtra(InputKontak.EXTRA_PHONE);

            Kontak kontak = new Kontak(name,email,nohp,alamat);
            contactViewModel.insertContact(kontak,MainActivity.this);

        }
        else{
            Toast.makeText(this,"Contact Not Saved",Toast.LENGTH_SHORT).show();
        }
        }

}