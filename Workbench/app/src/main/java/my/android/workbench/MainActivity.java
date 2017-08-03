package my.android.workbench;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        findViewById(R.id.buttonEx2).setOnClickListener(new example2Screen());
        findViewById(R.id.buttonEx3SyncResp).setOnClickListener(new example3OnSync());
        findViewById(R.id.buttonEx3ASyncResp).setOnClickListener(new example3OnAsync());
        findViewById(R.id.buttonEx4ContactForm).setOnClickListener(new example4ContactForm());
        Log.d("my.android.workbench", "onCreate");
    }

    @Override
    protected void onStart() {
        super.onStart();
        Log.d("Workbench", "onStart");
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.d("Workbench", "onResume");
    }

    @Override
    protected void onPause() {
        super.onPause();
        Log.d("Workbench", "onPause");
    }

    @Override
    protected void onStop() {
        super.onStop();
        Log.d("Workbench", "onStop");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.d("Workbench", "onDestroy");
    }

    class example2Screen implements View.OnClickListener {
        public void onClick(View v) {
            Intent intent = new Intent(MainActivity.this, Example2Activity.class);
            startActivity(intent);
        }
    }

    class example3OnSync implements View.OnClickListener {
        public void onClick(View v) {
            Intent intent = new Intent(MainActivity.this, AppSlowResponseActivity.class);
            startActivity(intent);
        }
    }

    class example3OnAsync implements View.OnClickListener {
        public void onClick(View v) {
            Intent intent = new Intent(MainActivity.this, AppSlowResponseAsyncActivity.class);
            startActivity(intent);
        }
    }

    class example4ContactForm implements View.OnClickListener {
        public void onClick(View v) {
            Intent intent = new Intent (MainActivity.this, ContactFormActivity.class);
            startActivity(intent);
        }
    }

}
