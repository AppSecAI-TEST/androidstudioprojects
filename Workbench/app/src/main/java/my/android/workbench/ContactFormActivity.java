package my.android.workbench;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class ContactFormActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contact_form);
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

        findViewById(R.id.buttonEx4ContactForm).setOnClickListener(new View.OnClickListener(){
           public void onClick(View v) {
               String to = ((EditText) findViewById(R.id.editTextTo)).getText().toString();
               String sub = ((EditText) findViewById(R.id.editTextSubject)).getText().toString();
               String mess = ((EditText) findViewById(R.id.editTextMessage)).getText().toString();
               Intent mail = new Intent(Intent.ACTION_SEND);
               mail.putExtra(Intent.EXTRA_EMAIL, new String[]{to});
               mail.putExtra(Intent.EXTRA_SUBJECT, sub);
               mail.putExtra(Intent.EXTRA_TEXT, mess);
               mail.setType("message/rfc82");
               startActivity(Intent.createChooser(mail, "Send email via: "));
           }
        });


    }

}
