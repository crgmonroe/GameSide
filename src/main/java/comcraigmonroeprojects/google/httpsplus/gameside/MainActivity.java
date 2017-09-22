package comcraigmonroeprojects.google.httpsplus.gameside;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
//import android.app.Activity;
//import android.os.Bundle;
import android.view.View;
//import android.view.View.OnClickListener;


public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void clickExit(View v){
        moveTaskToBack(true);
        android.os.Process.killProcess(android.os.Process.myPid());
        System.exit(1);
    }

    //public int clickToAdd(View v){
    //   String teamName = msg
    //}



}