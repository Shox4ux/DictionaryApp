package com.example.mydict.ViewHolders;





import android.view.View;

import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.mydict.R;

public class MeaningViewHolder extends RecyclerView.ViewHolder {
   public TextView textView_partOfSpeech;
   public RecyclerView recycler_definitions;
     public MeaningViewHolder(@NonNull View itemView) {
        super(itemView);
        textView_partOfSpeech = itemView.findViewById(R.id.textview_partOfSpeech);
        recycler_definitions = itemView.findViewById(R.id.recycler_definitions);
    }


}
