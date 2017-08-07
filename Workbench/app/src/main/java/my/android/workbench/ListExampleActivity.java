package my.android.workbench;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

public class ListExampleActivity extends AppCompatActivity {
    ListView coffeeList;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_example);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        String[] coffeeChoices = getResources().getStringArray(R.array.coffeeMenu);
        coffeeList = findViewById(R.id.listView);
        coffeeList.setAdapter(new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, coffeeChoices));
        coffeeList.setOnItemClickListener(new returnClickedItem());
    }

    class returnClickedItem implements AdapterView.OnItemClickListener {
        @Override
        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
            String item = (String) parent.getItemAtPosition(position);
            Intent data = new Intent();
            data.putExtra("Order", item);
            setResult(RESULT_OK, data);
            finish();
        }
    }
}
