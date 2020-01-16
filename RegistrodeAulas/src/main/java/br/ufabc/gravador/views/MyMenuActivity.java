package br.ufabc.gravador.views;
import br.ufabc.gravador.*;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

public class MyMenuActivity extends AppCompatActivity {

    Toolbar myToolbar;
    ActionBar myActionBar;

    //@Override
    protected void onCreate(Bundle savedInstanceState, int LayoutID, int ToolbarID, boolean homeEnabled) {
        super.onCreate(savedInstanceState);
        setContentView(LayoutID);

        myToolbar = findViewById(ToolbarID);
        setSupportActionBar(myToolbar);
        myActionBar = getSupportActionBar();
        myActionBar.setDisplayHomeAsUpEnabled(homeEnabled);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_settings:
                // settings
                return true;
            case R.id.action_info:
                // info
                return true;
            case R.id.action_welcome:
                // welcome
                return true;
            case R.id.action_legal:
                // legal
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu, menu);
        return true;
    }
}
