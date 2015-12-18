package com.gheinrich.cocc_mobile;


import android.app.Activity;
import android.os.AsyncTask;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;

import android.widget.ListView;
import android.widget.SearchView;

import java.io.*;
import java.util.ArrayList;


import java.util.List;

/**
 * Created by gheinrich on 6/27/2014. This Activity grabs data from a csv file and puts it into a lisview using a cutsom Listadapter
 * the end result or listview is searchable and updates as you enter a search query. This is usefull since the list holds a lot of data
 */

public class Directory extends Activity {
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.directory);

// creating objects able to bind to the xml element widgets
        final List<String> DirectoryList = new ArrayList<String>();
        final SearchView mSearchview = (SearchView) findViewById(R.id.searchView);
        final ListView list = (ListView) findViewById(R.id.listViewDirectory);

        // since we are reading data from a file we have to do it off the main thread so we use Asynctask which will implement SearchView.OnQueryListiner to make the listview searchable



        class Datahandler extends AsyncTask<Void, Void, File> implements SearchView.OnQueryTextListener {

            // override the doInBackground to perform our specified task(s)
            @Override
            protected File doInBackground(Void... params) {

                String line = null;
                try {

                    // need to create an input stream object through Inputstream and use the .getAssets() to fetch the file located i the Assets folder I haven't found another way to do it unless
                    // you create an Assets folder and put the resource file in there.
                    InputStream inputStream = getAssets().open("directory.csv___jb_bak___");

                    // in order to read inputstream object we have to access it through a bufferedreader and an inpustream reader
                    BufferedReader br = new BufferedReader(new InputStreamReader(inputStream));
                    String mytag = "MYTAG";
                    while ((line = br.readLine()) != null) {

                        // my way to check what is contained in the file read by looking at the logcat
                        Log.d(mytag, line);
                        DirectoryList.add(line);

                    }
                    // Accesss the do in background by creating a new runnable and sending it to the UI
                    Directory.this.runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            // Array adapter extends baseadapter which serves up data from your source to your xml views


                            myadapter myadapter = new myadapter(Directory.this, DirectoryList);
                            list.setAdapter(myadapter);
                            list.setTextFilterEnabled(true);
                            setupSearchview();

                        }

                        private void setupSearchview() {

                            mSearchview.setIconified(false);
                            mSearchview.setOnQueryTextListener(Datahandler.this);
                            mSearchview.setSubmitButtonEnabled(true);
                            mSearchview.setQueryHint("Search COCC");

                        }
                    });


                } catch (IOException e) {
                    e.printStackTrace();

                }

                return null;
            }

            @Override
            public boolean onQueryTextSubmit(String query) {


                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                if (TextUtils.isEmpty(newText)) {
                    list.clearTextFilter();
                } else {
                    list.setFilterText(newText.toString());
                }
                return true;
            }
        }

        Datahandler dataander = new Datahandler();
        dataander.execute();


        // data handler encapsulates all the things needed for the listview and querying
    }

}