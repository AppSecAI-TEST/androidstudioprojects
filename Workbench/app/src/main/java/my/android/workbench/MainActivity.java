package my.android.workbench;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        findViewById(R.id.buttonEx2).setOnClickListener(new example2Screen());
        findViewById(R.id.buttonEx3SyncResp).setOnClickListener(new example3OnSync());
    }

    class example2Screen implements View.OnClickListener {
        public void onClick(View v) {
            Intent intent = new Intent(MainActivity.this, Example2Activity.class);
            startActivity(intent);
        }
    }

    class example3OnSync implements View.OnClickListener {
        public void onClick(View v) {
            Intent intent = new Intent(MainActivity.this, AppNoResponseActivity.class);
            startActivity(intent);
        }
    }
}
