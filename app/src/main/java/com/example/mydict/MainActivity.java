package com.example.mydict;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

import com.example.mydict.Adapters.MeaningsAdapter;
import com.example.mydict.Adapters.PhoneticsAdapter;
import com.example.mydict.Models.APIResponse;

public class MainActivity extends AppCompatActivity {
    ProgressDialog progressDialog;
    SearchView search_view;
    TextView textView_word;
    RecyclerView recyclerView_phonetic, recyclerView_meanings;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        progressDialog = new ProgressDialog(this);

        search_view = findViewById(R.id.search_view);
        textView_word = findViewById(R.id.textView_word);
        recyclerView_meanings = findViewById(R.id.recycler_meanings);
        recyclerView_phonetic = findViewById(R.id.recycler_phonetics);


        progressDialog.setTitle("Loading....");
        progressDialog.show();
        RequestManager requestManager = new RequestManager(MainActivity.this);
        requestManager.getWordMeaning(listener,"hello");


        search_view.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                progressDialog.setTitle("Searching:" +query);
                progressDialog.show();
                RequestManager requestManager = new RequestManager(MainActivity.this);
                requestManager.getWordMeaning(listener,query);
            return true;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                return false;
            }
        });
    }

    private final OnFetchDataListener listener = new OnFetchDataListener() {
        @Override
        public void onFetchData(APIResponse apiResponse, String message) {
            progressDialog.dismiss();
            if (apiResponse==null){
                Toast.makeText(MainActivity.this, "No data found", Toast.LENGTH_SHORT).show();
                return;
            }
            showData(apiResponse);
        }

        @Override
        public void onError(String message) {
            progressDialog.dismiss();
            Toast.makeText(MainActivity.this, message, Toast.LENGTH_SHORT).show();
        }
    };

    private void showData(APIResponse apiResponse) {
        textView_word.setText("Word: "+apiResponse.getWord());
        recyclerView_phonetic.setHasFixedSize(true);
        recyclerView_phonetic.setLayoutManager(new GridLayoutManager(this,1));
        PhoneticsAdapter adapter = new PhoneticsAdapter(this,apiResponse.getPhonetics());
        recyclerView_phonetic.setAdapter(adapter);

        recyclerView_meanings.setHasFixedSize(true);
        recyclerView_meanings.setLayoutManager(new GridLayoutManager(this,1));
        MeaningsAdapter adapter1 = new MeaningsAdapter(this,apiResponse.getMeanings());
        recyclerView_meanings.setAdapter(adapter1);
    }
}