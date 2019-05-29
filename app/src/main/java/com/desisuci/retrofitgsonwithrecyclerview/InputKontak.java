package com.desisuci.retrofitgsonwithrecyclerview;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import org.json.JSONException;
import org.json.JSONObject;

public class InputKontak extends AppCompatActivity {
    private EditText inputNama,inputEmail,inputAlamat,inputNoHp;
    private Button btn_simpan;
    public static final String EXTRA_NAME = "com.desisuci.retrofitgsonwithrecyclerview.EXTRA_NAME";
    public static final String EXTRA_EMAIL = "com.desisuci.retrofitgsonwithrecyclerview.EXTRA_EMAIL";
    public static final String EXTRA_ADDRESS = "com.desisuci.retrofitgsonwithrecyclerview.EXTRA_ADDRESS";
    public static final String EXTRA_PHONE = "com.desisuci.retrofitgsonwithrecyclerview.EXTRA_PHONE";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_input_kontak);

        btn_simpan = (Button) findViewById(R.id.btn_simpan);

        inputNama = (EditText) findViewById(R.id.in_nama);
        inputEmail = (EditText) findViewById(R.id.in_email);
        inputAlamat = (EditText) findViewById(R.id.in_alamat);
        inputNoHp = (EditText) findViewById(R.id.in_noHp);

        btn_simpan.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                saveContact();
            }
        });
    }

    public void saveContact(){
            String name = inputNama.getText().toString();
            String email = inputEmail.getText().toString();
            String alamat = inputAlamat.getText().toString();
            String nohp = inputNoHp.getText().toString();

            if(name.trim().isEmpty() || nohp.trim().isEmpty()){
                Toast.makeText(this,"Please insert name and phone",Toast.LENGTH_SHORT).show();
                return;
            }
            Intent data = new Intent();
            data.putExtra(EXTRA_NAME,name);
            data.putExtra(EXTRA_EMAIL,email);
            data.putExtra(EXTRA_ADDRESS,alamat);
            data.putExtra(EXTRA_PHONE,nohp);


            setResult(RESULT_OK,data);
            finish();
    }
}
