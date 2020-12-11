package com.problemsolvers.textsaviour;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.Toast;

import static android.nfc.NfcAdapter.EXTRA_ID;

public class AddEditSaviour extends AppCompatActivity {

    public static final String EXTRA_Id = "com.problemsolvers.saviours.EXTRA_ID";
    public static final String EXTRA_PLATFORM = "com.problemsolvers.saviours.EXTRA_PLATFORM";
    public static final String EXTRA_EMAIL = "com.problemsolvers.saviours.EXTRA_EMAIL";
    public static final String EXTRA_PASSWORD = "com.problemsolvers.saviours.EXTRA_PASSWORD";







    private EditText platfrm;
    private EditText email;
    private EditText password;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_saviour);

        platfrm = findViewById(R.id.platfrm);
        email = findViewById(R.id.email);
        password = findViewById(R.id.password);

        getSupportActionBar().setHomeAsUpIndicator(R.drawable.ic_baseline_close_24);


        Intent intent = getIntent();
        if (intent.hasExtra(EXTRA_ID)) {
            setTitle("Edit Note");
            platfrm.setText(intent.getStringExtra(EXTRA_PLATFORM));
            email.setText(intent.getStringExtra(EXTRA_EMAIL));
            password.setText(intent.getStringExtra(EXTRA_PASSWORD));
        } else {
            setTitle("Add Note");
        }

        setTitle("Add Note");





    }







    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.add_note_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.save_note:
                saveSaviour();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }

    }



    private void saveSaviour() {
        String platform = platfrm.getText().toString();
        String emal = email.getText().toString();
        String passw = password.getText().toString();

        if (platform.trim().isEmpty() || emal.trim().isEmpty()) {
            Toast.makeText(this, "Please insert a title and Description", Toast.LENGTH_LONG).show();
            return;
        }

        Intent data = new Intent();
        data.putExtra(EXTRA_PLATFORM, platform);
        data.putExtra(EXTRA_EMAIL, emal);
        data.putExtra(EXTRA_PASSWORD, passw);

        //
        // the below code is for editing the view and then saving it.
        int id = getIntent().getIntExtra(EXTRA_ID, -1);
        if (id != -1) {
            data.putExtra(EXTRA_ID, id);
        }
        // till here

        setResult(RESULT_OK, data);
        finish();



    }
}