package com.problemsolvers.textsaviour;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.problemsolvers.textsaviour.model.SaviourViewModel;

import java.util.List;

public class MainActivity extends AppCompatActivity {
    public static final int ADD_SAVIOUR_REQUEST = 1;
    public static final int EDIT_NOTE_REQUEST = 2;
    private SaviourViewModel saviourViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        FloatingActionButton buttonAddNote = findViewById(R.id.button_add_note);
        buttonAddNote.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, AddEditSaviour.class);
                startActivityForResult(intent, ADD_SAVIOUR_REQUEST);
                //instead of startactivity we written here startActivityForResult
                //sot that we can later get our input back
            }
        });


        RecyclerView recyclerView = findViewById(R.id.recycler_View);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setHasFixedSize(true);

        final SaviourAdapter adapter = new SaviourAdapter();
        recyclerView.setAdapter(adapter);


        new ItemTouchHelper(new ItemTouchHelper.SimpleCallback(0,
                ItemTouchHelper.LEFT | ItemTouchHelper.RIGHT) {
            @Override
            public boolean onMove(@NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder, @NonNull RecyclerView.ViewHolder target) {
                return false;
            }

            @Override
            public void onSwiped(@NonNull RecyclerView.ViewHolder viewHolder, int direction) {
                saviourViewModel.delete(adapter.getSuviourAt(viewHolder.getAdapterPosition()));


                Toast.makeText(MainActivity.this, "Note deleted", Toast.LENGTH_SHORT).show();

            }
        }).attachToRecyclerView(recyclerView);


        saviourViewModel = new ViewModelProvider.AndroidViewModelFactory(getApplication())
                .create(SaviourViewModel.class);
        saviourViewModel.getAllSaviour().observe(this, new Observer<List<Saviour>>() {
            @Override
            public void onChanged(List<Saviour> saviours) {
                //update recyclerView here
                adapter.setSaviours(saviours);


            }
        });
       adapter.setOnItemClickListener(new SaviourAdapter.OnItemClickListener() {
           @Override
           public void onItemClick(Saviour saviour) {
               Intent intent = new Intent(MainActivity.this, AddEditSaviour.class);
               //NOTE- here we changed the name of our from AddNoteActivity.class to
               // AddEditNoteActivity.class (SHIFT + F6)

               intent.putExtra(AddEditSaviour.EXTRA_Id, saviour.getId());
               intent.putExtra(AddEditSaviour.EXTRA_PLATFORM, saviour.getPlatformName());
               intent.putExtra(AddEditSaviour.EXTRA_EMAIL, saviour.getEmail());
               intent.putExtra(AddEditSaviour.EXTRA_PASSWORD, saviour.getPassword());
               startActivityForResult(intent, EDIT_NOTE_REQUEST);
                      }
       });



    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == ADD_SAVIOUR_REQUEST && resultCode == RESULT_OK) {
            String platform = data.getStringExtra(AddEditSaviour.EXTRA_PLATFORM);
            String email = data.getStringExtra(AddEditSaviour.EXTRA_EMAIL);
            String password = data.getStringExtra(AddEditSaviour.EXTRA_PASSWORD);

            Saviour saviour = new Saviour(platform, email, password);
            saviourViewModel.insert(saviour);

            Toast.makeText(this, "Note Saved", Toast.LENGTH_SHORT).show();

        } else if (requestCode == EDIT_NOTE_REQUEST && resultCode == RESULT_OK) {
            int id = data.getIntExtra(AddEditSaviour.EXTRA_Id, -1);

            if (id == -1) {
                Toast.makeText(this, "Note can't be updated", Toast.LENGTH_SHORT).show();
                return;
            }
            String platform = data.getStringExtra(AddEditSaviour.EXTRA_PLATFORM);
            String email = data.getStringExtra(AddEditSaviour.EXTRA_EMAIL);
            String password = data.getStringExtra(AddEditSaviour.EXTRA_PASSWORD);

            Saviour saviour = new Saviour(platform, email, password);
            saviour.setId(id);
            saviourViewModel.update(saviour);

            Toast.makeText(this, "Note Updated", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(this, "Note not saved", Toast.LENGTH_SHORT).show();
        }

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.main_menu, menu);

        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {

        switch (item.getItemId()) {
            case R.id.delete_all_notes:
                saviourViewModel.deleteAll();
                Toast.makeText(this, "All notes deleted", Toast.LENGTH_SHORT).show();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
}
