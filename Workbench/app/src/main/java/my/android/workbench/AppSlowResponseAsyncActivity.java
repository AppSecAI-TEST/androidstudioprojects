package my.android.workbench;

import android.os.AsyncTask;
import android.os.Bundle;
import android.os.SystemClock;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class AppSlowResponseAsyncActivity extends AppCompatActivity {

    TextView tv;            // for class wide reference to update status
    int count;              // number of times process has run, used for feedback
    boolean processing;     // defaults false, set true when the slow process starts
    Button bt;              // used to update button caption

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_app_slow_response_async);
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
        tv = findViewById(R.id.textView);

        // handle button presses
        findViewById(R.id.buttonGo).setOnClickListener(new doButtonClick());
        bt = findViewById(R.id.buttonGo);
    }

    class doButtonClick implements View.OnClickListener {
        ThisTakesAWhile ttaw; // defaults null
        public void onClick(View v) {
            if(!processing) {
                ttaw = new ThisTakesAWhile();
                ttaw.execute(10); // loop 10 times
            } else {
                ttaw.cancel(true);
            }
        }
    }

    class ThisTakesAWhile extends AsyncTask<Integer, Integer, Integer> {
        int numCycles; // total number of times to execute process
        protected void onPreExecute() {
            // Executes in UI thread before task begins
            // Can be used to set things up in UI such as showing progress bar
            count = 0; // count number of cycles
            processing = true;
            tv.setText("Processing, please wait.");
            bt.setText("STOP");
        }

        protected Integer doInBackground(Integer... arg0) {
            // Runs in a background thread
            // Used to run code that could block the UI
            numCycles = arg0[0]; // Run arg0 times
            // Need to check isCancelled to see if cancel was called
            while(count < numCycles && !isCancelled()) {
                // wait one second ( simulate a long process )
                SystemClock.sleep(1000);
                // count cycles
                count++;
                // signal to the UI (via onProgressUpdate)
                // class arg1 determines type of data sent
                publishProgress(count);
            }
            // return value sent to UI via onPostExecute
            // class arg2 determines result type sent
            return count;
        }

        protected void onProgressUpdate(Integer... arg1) {
            // called when background task calls publishProgress
            // in doInBackground
            if(isCancelled()) {
                tv.setText("Cancelled! Completed "+ arg1[0] + " processes.");
            } else {
                tv.setText("Processed " + arg1[0] + " of " + numCycles + ".");
            }
        }

        protected void onPostExecute(Integer result) {
            // result comes from return value of doInBackground
            // runs on UI thread, not called if task cancelled
            tv.setText("Processed " + result + ", finished!");
            processing = false;
            bt.setText("Go");
        }

        protected void onCancelled(Integer result) {
            // run on UI thread if task is cancelled
            // result comes from return value of doInBackground
            tv.setText("Cancelled called after " + result + " processes.");
            processing = false;
            bt.setText("Go");
        }

    }

}
