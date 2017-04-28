package ir.minoo96;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import ir.minoo96.Adapters.SearchCandidateAdapter;
import ir.minoo96.Adapters.UpdateAdapter;
import ir.minoo96.Utility.FontTextView;
import ir.minoo96.Utility.Variables;

public class SearchResultActivity extends AppCompatActivity {

    LinearLayoutManager linearLayoutManager;
    RecyclerView recyclerViewCandidates;
    SearchCandidateAdapter searchCandidateAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_result);

        String query = getIntent().getExtras().getString("query");

        FontTextView searchQuery = (FontTextView) findViewById(R.id.searchQuery);
        searchQuery.setText("نتایج جستجو برای عبارت '" + query + "' :");

        recyclerViewCandidates = (RecyclerView) findViewById(R.id.recyclerViewCandidates);
        recyclerViewCandidates.setHasFixedSize(true);
        linearLayoutManager = new LinearLayoutManager(this);
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerViewCandidates.setLayoutManager(linearLayoutManager);
        searchCandidateAdapter = new SearchCandidateAdapter(this);
        recyclerViewCandidates.setAdapter(searchCandidateAdapter);
    }
}