package comcraigmonroeprojects.google.httpsplus.gameside;

import android.app.Activity;
import android.content.Context;
import android.database.Cursor;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
//import android.app.Activity;
//import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;
//import android.view.View.OnClickListener;


public class MainActivity extends AppCompatActivity {

    DatabaseHelper myDb;
    EditText team_name;
    String team_color;

    Button btnAddData;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        myDb = new DatabaseHelper(this);

        team_name = (EditText) findViewById(R.id.team_name);
        //team_color = (EditText) findViewById(R.id.team_color);

        Spinner colorSpinner = (Spinner) findViewById(R.id.spinner_color);
        ArrayAdapter<String> colorAdapter = new ArrayAdapter<String>(MainActivity.this,R.layout.spinner_item,getResources().getStringArray(R.array.color_array));
        colorAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        //colorSpinner.getPopupBackground().setColorFilter(ContextCompat.getColor(this,R.color.white, PorterDuff.Mode.SRC_ATOP));
        colorSpinner.getBackground().setColorFilter(Color.parseColor("#000000"),PorterDuff.Mode.SRC_ATOP);
        // show the data inside the spinner
        colorSpinner.setAdapter(colorAdapter);
        colorSpinner.setSelection(((ArrayAdapter<String>)colorSpinner.getAdapter()).getPosition(team_color));

        //team_color = colorSpinner.getSelectedItem().toString();

        btnAddData = (Button) findViewById(R.id.button_add);


        addTeam();
        boolean teamsDefined = listTeams();
        // adding code to the main page is shown for development
        //teamsDefined = false;

        if(teamsDefined){
            Toast.makeText(MainActivity.this, "Display Teams", Toast.LENGTH_LONG);

            setContentView(R.layout.activity_team_chooser);
        }


    }

    public void clickExit(View v){
        moveTaskToBack(true);
        android.os.Process.killProcess(android.os.Process.myPid());
        System.exit(1);
    }

    public void addTeam() {
        btnAddData.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public  void onClick(View v) {
                        String team =  team_name.getText().toString();
                        String color = team_color.toString();
                        boolean isInserted = myDb.addTeam(team, team_color);
                        if(isInserted == true) {
                            Toast.makeText(MainActivity.this, "Team Added", Toast.LENGTH_LONG).show();
                            // list the teams
                            //getAllTeams();

                        } else {
                            Toast.makeText(MainActivity.this, "Unable to Add Team", Toast.LENGTH_LONG).show();
                        }
                    }
                }

        );
    }

    public boolean listTeams() {
        Cursor res = myDb.getAllTeams();
        if (res.getCount() == 0) {
            //showMessage("Error","Nothing Found");
            Toast.makeText(MainActivity.this, "No Teams Registered", Toast.LENGTH_SHORT);
            return false;
        } else {
            StringBuffer buffer = new StringBuffer();
            while (res.moveToNext()) {
                buffer.append("Team: " + res.getString(1) + "\n");
            }
            Toast.makeText(MainActivity.this, buffer.toString(), Toast.LENGTH_SHORT);
            //showMessage("Teams",buffer.toString());
            return true;
        }
    }



    public void showMessage(String title, String message) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setCancelable(true);
        builder.setTitle(title);
        builder.setMessage(message);
        builder.show();

    }
}
