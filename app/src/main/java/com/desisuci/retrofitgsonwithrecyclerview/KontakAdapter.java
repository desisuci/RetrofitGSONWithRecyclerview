package com.desisuci.retrofitgsonwithrecyclerview;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class KontakAdapter extends RecyclerView.Adapter<KontakAdapter.KontakHolder>{
    private List<Kontak> kontaks = new ArrayList<>();

    @NonNull
    @Override
    public KontakHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.cardview,parent,false);
        return new KontakHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull KontakHolder holder, int position) {
        Kontak currentContact = kontaks.get(position);
        holder.txtName.setText(currentContact.getName());
        holder.txtEmail.setText(currentContact.getEmail());
        holder.txtPhone.setText(currentContact.getPhone());
        holder.txtAddress.setText(currentContact.getAddress());
    }

    @Override
    public int getItemCount() {
        return kontaks.size();
    }

    public void setKontaks(List<Kontak> kontaks){
        this.kontaks = kontaks;
        notifyDataSetChanged();
    }
    public Kontak getContactAt(int position){
        return kontaks.get(position);
    }

    class KontakHolder extends RecyclerView.ViewHolder{
        private TextView txtName;
        private TextView txtEmail;
        private TextView txtPhone;
        private TextView txtAddress;

    public KontakHolder(@NonNull View itemView){
        super(itemView);
        txtName = itemView.findViewById(R.id.txt_nama);
        txtEmail = itemView.findViewById(R.id.txt_email);
        txtPhone = itemView.findViewById(R.id.txt_noHp);
        txtAddress = itemView.findViewById(R.id.txt_address);
        }
    }
}
