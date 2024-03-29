package com.example.mydict.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.mydict.Models.Meanings;
import com.example.mydict.R;
import com.example.mydict.ViewHolders.MeaningViewHolder;

import java.util.Collection;
import java.util.List;

public class MeaningsAdapter extends RecyclerView.Adapter<MeaningViewHolder> {
    private Context context;
    private List<Meanings> meaningsList;

    public MeaningsAdapter(Context context, List<Meanings> meaningsList) {
        this.context = context;
        this.meaningsList = meaningsList;
    }

    @NonNull
    @Override
    public MeaningViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new MeaningViewHolder(LayoutInflater.from(context).inflate(R.layout.meanings_list_items,parent,false));
    }

    @Override
    public void onBindViewHolder(@NonNull MeaningViewHolder holder, int position) {
        holder.textView_partOfSpeech.setText("Parts of Speech: "+meaningsList.get(position).getPartOfSpeech());
        holder.recycler_definitions.setHasFixedSize(true);
        holder.recycler_definitions.setLayoutManager(new GridLayoutManager(context,1));
        DefinitionAdapter adapter = new DefinitionAdapter(context,meaningsList.get(position).getDefinitions());
        holder.recycler_definitions.setAdapter(adapter);

    }

    @Override
    public int getItemCount() {
        return meaningsList.size();
    }
}
