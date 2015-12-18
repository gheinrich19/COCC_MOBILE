package com.gheinrich.cocc_mobile;

import android.app.Activity;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.*;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;


/**
 * Created by Gage Heinrich on 6/18/14. i am going to implement Jsoup to arse HTML into a listview. it will target the
 * table in the cocc calendar. This Activity may get a little confusing. I will try to expleain as best I can.
 * ******** 1. We start by creating our Activity
 * ******** 2. Since Parsing is involved/ through an HTTP request we need to instantiate or override the AsyncTask class.(This allows us
 * ********    To run a process in the background while not overworking or crashing the main or UI thread).
 * ******** 3. We use the doInBackground funtcion to complete this task.
 * ******** 4. I have imported the Jsoup library for parsing purposes.
 * ******** 5. To update the UI thread with the calander information that was handled in the parsingtable class we use the
 * ********    runOnUiThread that is linked to the Library activity found on line #72.
 * ******** 6. We use an arrayadapter to convert our List<string> to put in the Listview layout in the xml file Library
 */
public class library extends Activity {
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.library);


        // We are using AsyncTask to allow the http/ network connection rto run in the background. A process that uses the network can't be done on the main or UI Thread.
        // So we push all the parsing to the background and when it is done the words are dispalye
        ImageButton LibFbButton = (ImageButton) findViewById(R.id.LibTwitButton);
        ImageButton LibTwitButton = (ImageButton) findViewById(R.id.LibFbButton);


        LibFbButton.setOnClickListener(LibraryHandler);
        LibTwitButton.setOnClickListener(LibraryHandler);



        class parsingtable extends AsyncTask<Void, Void, String> {
            @Override


            public String doInBackground(Void... params) {
                // these variables are for testing purposes and the top right heading in the library layout xml file.
                String MyTAG = "MYTAG";
                String title = "";

                // this is a List that we will use to store info being skimmed from url
                final List<String> columnTitles = new ArrayList<String>();

                try {

                    // initialize the Document to pull data from to parse
                    Document doc = Jsoup.connect("http://events.cocc.edu/wv3/wv3_servlet/urd/run/wv_event.DayList?evdt=0,evfilter=61580").get();
                    title = doc.title();
                    Log.d(MyTAG, "" + title);

                    // ListHeading is the HTML className located on the webpage in which table we are pulling from

                    Elements tblElements = doc.select(".DayCalText");
                    tblElements = tblElements.not("script");


                    // doc.getElementsByClass("DayCalText").not("<script>");//doc.getElementsByClass("DayCalText");


                    // This for loop grabs each instance of  tblElements and puts in List of type dataType String
                    for (Element e : tblElements) {

                        String s = e.text();

                        if (s != "" || s != " " || s != null || !s.isEmpty()) {
                            columnTitles.add(s);
                        }
                        Log.d(MyTAG, "" + e.text());
                    }

                    for (int i = 0; i < columnTitles.size(); i++) {
                        if (columnTitles.get(i).length() == 0 || columnTitles.get(i).length() == 1) {
                            columnTitles.remove(i);
                        }
                        if (columnTitles.get(i) == "" || columnTitles.get(i) == " " || columnTitles.get(i) == "  " || columnTitles.get(i) == null) {
                            columnTitles.remove(i);


                        }
                    }


                    library.this.runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                           // ArrayAdapter<String> adapter = new myadapter(library.this, R.layout.eventcalanderitem, columnTitles);


                            ListView list = (ListView) findViewById(R.id.listViewt);
                            final myadapter myadapter = new myadapter(library.this, columnTitles);
                            list.setAdapter(myadapter);


                        }
                    });


                } catch (IOException e) {
                    e.printStackTrace();
                }

                return title;
            }

            @Override
            protected void onPostExecute(String result) {
                ((TextView) findViewById(R.id.libraryhours)).setText(result);


            }

        }

        parsingtable parsingtable = new parsingtable();
        parsingtable.execute();
    }

    public View.OnClickListener LibraryHandler = new View.OnClickListener() {
        @Override
        public void onClick(View v) {

            if (v.getId() == R.id.LibFbButton) {
                Intent LibFbButtonIntent = new Intent(library.this, LibraryFbButton_webview.class);
                startActivity(LibFbButtonIntent);


            }

            if (v.getId() == R.id.LibTwitButton) {
                Intent LibTwitButtonIntent = new Intent(library.this, LibTwitButton_webview.class);
                startActivity(LibTwitButtonIntent);
            }

        }
    };

}




