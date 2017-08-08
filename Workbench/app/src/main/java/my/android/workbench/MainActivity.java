package my.android.workbench;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.PopupWindow;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    String goldWinner = "Fred Bloggs";
    int goldScore = 98123;
    String silverWinner = "John Doe";
    int silverScore = 54900;
    String bronzeWinner = "Joe Public";
    int bronzeScore = 25789;

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
        findViewById(R.id.buttonPopup).setOnClickListener(new example6OnPopup());
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

    private class example6OnPopup implements View.OnClickListener {
        public void onClick(View arg0) {
            showWinners();
        }
    }

    private void showWinners() {
        // We need to get the instance of the LayoutInflater, use the context of this activity
        LayoutInflater inflater = (LayoutInflater) MainActivity.this.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        // Inflate the view from a predefined XML layout (no need for root id, using entire layout)
        View layout = inflater.inflate(R.layout.winners, null);
        // load results
        ((TextView) layout.findViewById(R.id.goldName)).setText(goldWinner);
        ((TextView) layout.findViewById(R.id.goldScore)).setText(Integer.toString(goldScore));
        ((TextView) layout.findViewById(R.id.silverName)).setText(silverWinner);
        ((TextView) layout.findViewById(R.id.silverScore)).setText(Integer.toString(silverScore));
        ((TextView) layout.findViewById(R.id.bronzeName)).setText(bronzeWinner);
        ((TextView) layout.findViewById(R.id.bronzeScore)).setText(Integer.toString(bronzeScore));
        // Get the devices screen density to calculate correct pixel sizes
        float density = MainActivity.this.getResources().getDisplayMetrics().density;
        // create a focusable PopupWindow with the given layout and correct size
        final PopupWindow pw = new PopupWindow(layout, (int)density*240, (int)density*285, true);
        // Button to close the pop-up
        ((Button) layout.findViewById(R.id.close)).setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                pw.dismiss();
            }
        });
        // Set up touch closing outside of pop-up
        pw.setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        pw.setTouchInterceptor(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                if(event.getAction() == MotionEvent.ACTION_OUTSIDE) {
                    pw.dismiss();
                    return true;
                }
                return false;
            }
        });
        pw.setOutsideTouchable(true);
        // display the pop-up in the center
        pw.showAtLocation(layout, Gravity.CENTER, 0, 0);
    }
}
