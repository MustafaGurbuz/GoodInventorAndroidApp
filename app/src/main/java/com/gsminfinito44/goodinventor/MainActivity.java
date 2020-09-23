package com.gsminfinito44.goodinventor;

import android.app.Activity;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

import androidx.annotation.RequiresApi;

public class MainActivity extends Activity {
    Button save;
    Button exit;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

    }

    public void bringBack(View v){
        try{
            FileInputStream fisFile = openFileInput("bring.text");
            EditText oET = (EditText) findViewById(R.id.object);
            EditText oET1 = (EditText) findViewById(R.id.location);
            fisFile.read(oET.getText().toString().getBytes());
            fisFile.read(oET1.getText().toString().getBytes());
            TextView t = (TextView) findViewById(R.id.result);
            TextView tv = (TextView) findViewById(R.id.sonuc);
            t.setText(String.valueOf(oET));
            tv.setText(String.valueOf(oET1));
            fisFile.close();
            Toast.makeText(MainActivity.this,"Showed your text", Toast.LENGTH_LONG).show();
        }
        catch(IOException e){
            Log.v("BRING","FILE IO PROBLEM");
        }
    }
    public void sendSave(View v){
        try{
            FileOutputStream fosFile = openFileOutput("savednote.txt",0);
            EditText oET = (EditText) findViewById(R.id.object);
            EditText oET1 = (EditText) findViewById(R.id.location);
            fosFile.write(oET.getText().toString().getBytes());
            fosFile.write(oET1.getText().toString().getBytes());
            fosFile.close();
            Toast.makeText(MainActivity.this,"Saved your text", Toast.LENGTH_LONG).show();
        }
        catch(IOException e){
            Log.v("REMEMBER","FILE IO PROBLEM");}
    }
    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    public void exitNotSave(View v) throws FileNotFoundException {
        String path = Environment.getExternalStorageState(new File(Environment.DIRECTORY_PICTURES));
        File fFnote  = new File (path,"note.txt");
        FileOutputStream fosFile = new FileOutputStream(fFnote);
    }
}
