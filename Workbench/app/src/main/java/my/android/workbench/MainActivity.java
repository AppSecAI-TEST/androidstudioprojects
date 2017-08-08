package my.android.workbench;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        findViewById(R.id.buttonEx2).setOnClickListener(new example2Screen());
        findViewById(R.id.buttonEx3SyncResp).setOnClickListener(new example3OnSync());
        findViewById(R.id.buttonEx3ASyncResp).setOnClickListener(new example3OnAsync());
        findViewById(R.id.buttonEx4ListExample).setOnClickListener(new example4OnList());
        findViewById(R.id.buttonAbout).setOnClickListener(new View.OnClickListener(){
            public void onClick(View arg0) {
                AboutBox.Show(MainActivity.this);
            }
        });
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

    class example4OnList implements View.OnClickListener {
        public void onClick(View v) {
            Intent intent = new Intent(MainActivity.this, ListExampleActivity.class);
            startActivityForResult(intent, 0);
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if(data != null && data.hasExtra("Order"))
            Toast.makeText(this, data.getStringExtra("Order") + " ordered.", Toast.LENGTH_LONG).show();
        else
            Toast.makeText(this, "Nothing ordered!", Toast.LENGTH_LONG).show();
    }

}
