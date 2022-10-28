package com.sky.skyoverflow.Adapter;


import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CompoundButton;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;


import com.sky.skyoverflow.databinding.CustomDilveryAddressBinding;

import java.util.ArrayList;

public class AddressAdapter extends RecyclerView.Adapter<AddressAdapter.ViewHolder> {
    Context context;
    private ArrayList<String> data;
    private ArrayList<Integer> selectCheck = new ArrayList<>();

    public AddressAdapter(Context context, ArrayList<String> data) {
        this.context = context;
        this.data = data;
        for (int i = 0; i < data.size(); i++) {
            selectCheck.add(0);
        }
    }


    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        CustomDilveryAddressBinding binding = CustomDilveryAddressBinding.inflate(inflater, parent, false);

        return new ViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, @SuppressLint("RecyclerView") int position) {
        holder.binding.txtType.setText(data.get(position));

        if (selectCheck.get(position) == 1) {
            holder.binding.checkbox.setChecked(true);
        } else {
            holder.binding.checkbox.setChecked(false);
        }
        holder.binding.checkbox.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                for (int k = 0; k < selectCheck.size(); k++) {
                    if (k == position) {
                        selectCheck.set(k, 1);
                    } else {
                        selectCheck.set(k, 0);
                    }
                }
                notifyDataSetChanged();
            }
        });

        holder.binding.checkbox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {

                if (isChecked == true) {

                }
            }
        });


    }

    @Override
    public int getItemCount() {

        return data.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        CustomDilveryAddressBinding binding;

        public ViewHolder(CustomDilveryAddressBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }
    }


}

