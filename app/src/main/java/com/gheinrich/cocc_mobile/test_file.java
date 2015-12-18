package com.gheinrich.cocc_mobile;

import android.app.Activity;
import android.content.res.Resources;
import android.os.Bundle;
import android.util.Log;

import java.io.*;

/**
 * Created by gheinrich on 6/30/2014.
 */

public class test_file extends Activity {


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.testlayout);


        try {
            loadtext();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    public void loadtext() throws FileNotFoundException {

        String myfile = "C:\\Users\\gheinrich\\Idea Projects\\test_android\\src\\com\\example\\test_android\\campusdirectory.txt";
        File directoryFile = new File(myfile);
        BufferedReader reader = new BufferedReader(new FileReader(directoryFile));
        String line = null;
        String TAG = "TAG";


        try {
            while ((line = reader.readLine()) != null) {


                Log.d(TAG, "MyTag");
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}







