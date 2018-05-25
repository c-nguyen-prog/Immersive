package de.chinguyen.immersive;

import android.Manifest;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

public class Immersive extends AppCompatActivity {

    TextView textView, website, connect, command;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        textView = (TextView) findViewById(R.id.mainText);
        website = (TextView) findViewById(R.id.website);
        connect = (TextView) findViewById(R.id.connect);
        command = (TextView) findViewById(R.id.command);

        checkStatus();
        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                checkStatus();
            }
        });
    }

    private void checkStatus() {
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.WRITE_SECURE_SETTINGS) == PackageManager.PERMISSION_GRANTED) {
            textView.setText(R.string.perm_granted);
            website.setVisibility(View.GONE);
            connect.setVisibility(View.GONE);
            command.setVisibility(View.GONE);
        } else {
            textView.setText(R.string.perm_needed);
            website.setText(R.string.website);
            website.setVisibility(View.VISIBLE);
            connect.setText(R.string.slide_2_title);
            connect.setVisibility(View.VISIBLE);
            command.setText(R.string.slide_2_desc);
            command.setVisibility(View.VISIBLE);
        }
    }
}
