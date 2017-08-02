package my.android.workbench;

import android.os.Bundle;
import android.os.SystemClock;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.TextView;

public class AppNoResponseActivity extends AppCompatActivity {

    TextView tv; // for class wide reference to update status

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_app_no_response);
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

        // get the references to on screen items
        tv = findViewById(R.id.textViewFeedback);

        // handle button presses
        findViewById(R.id.buttonGo).setOnClickListener(new doGoButtonClick());
    }

    class doGoButtonClick implements View.OnClickListener {
        public void onClick(View v) {
            tv.setText("Processing, please wait.");
            ThisTakesAWhile();
            tv.setText("Finished.");
        }
    }
    private void ThisTakesAWhile() {
        // mimic long running code
        int count = 0;
        do {
            SystemClock.sleep(1000);
            count++;
            tv.setText("Processed " + count + " of 10.");
        } while (count < 10);
    }
}
