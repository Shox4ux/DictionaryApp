package com.example.mydict.Adapters;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.mydict.Models.Definitions;
import com.example.mydict.R;
import com.example.mydict.ViewHolders.DefinitionViewHolder;

import java.util.List;

public class DefinitionAdapter extends RecyclerView.Adapter<DefinitionViewHolder> {
    private final Context context;
    private final List<Definitions> definitionsList;

    public DefinitionAdapter(Context context, List<Definitions> definitionsList) {
        this.context = context;
        this.definitionsList = definitionsList;
    }

    @NonNull
    @Override
    public DefinitionViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new DefinitionViewHolder(LayoutInflater.from(context).inflate(R.layout.definitions_list_items,parent,false));
    }

    @SuppressLint("SetTextI18n")
    @Override
    public void onBindViewHolder(@NonNull DefinitionViewHolder holder, int position) {
        holder.textview_definition.setText("Definition: "+definitionsList.get(position).getDefinition());
        holder.textview_example.setText(definitionsList.get(position).getExample());

        StringBuilder synonyms = new StringBuilder();
        StringBuilder antonyms = new StringBuilder();

        synonyms.append(definitionsList.get(position).getSynonyms());
        antonyms.append(definitionsList.get(position).getAntonyms());

        holder.textview_synonyms.setText(synonyms);
        holder.textview_antonyms.setText(antonyms);

        holder.textview_synonyms.setSelected(true);
        holder.textview_antonyms.setSelected(true);





    }

    @Override
    public int getItemCount() {
        return definitionsList.size();
    }
}
