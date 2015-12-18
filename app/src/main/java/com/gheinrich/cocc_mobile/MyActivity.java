/*************************************************************************
 *****               Author: Gage Heinrich                             ***
 ****   Description: This program is an app that is made for COCC      ***
 ****   There is a main page that will have all the buttons            ***
 ****   or links to other "activity pages" or web URl's.               ***
 ****   It is supossed to be more convinient for those with phones     ***
 ****   to access the bobcat web account, blackbord etc.               ***
 ****                                                                  ***
 * ***********************************************************************
 **/

package com.gheinrich.cocc_mobile;

import android.app.Activity;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

import java.io.IOException;


public class MyActivity extends Activity {
    /**
     * Called when the activity is first created.
     */

    @Override

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);




        // button that is 2nd from the top that goes to the map_page


        // button that will be on top of the homepage app
        ImageButton myButton = (ImageButton) findViewById(R.id.button3);
        ImageButton google_button = (ImageButton) findViewById(R.id.button2);
        ImageButton fBButton = (ImageButton) findViewById(R.id.fbButton);
        ImageButton LibButton = (ImageButton) findViewById(R.id.LibraryButton);
        ImageButton YoutubeButton = (ImageButton) findViewById(R.id.YtButton);
        ImageButton DirectButton = (ImageButton) findViewById(R.id.DirectoryButton);
        ImageButton employeebutton = (ImageButton) findViewById(R.id.employeebutton);
        ImageButton blackboardbutton = (ImageButton) findViewById(R.id.blackboardbutton);
        ImageButton mChatButton = (ImageButton) findViewById(R.id.chatButton);
        final String mTag = "tag";

        DirectButton.setOnClickListener(myhandler);
        myButton.setOnClickListener(myhandler);
        google_button.setOnClickListener(myhandler);
        fBButton.setOnClickListener(myhandler);
        LibButton.setOnClickListener(myhandler);
        YoutubeButton.setOnClickListener(myhandler);
        employeebutton.setOnClickListener(myhandler);
        blackboardbutton.setOnClickListener(myhandler);
        mChatButton.setOnClickListener(myhandler);

        // extending Asynctask to allow for http request through Jsoup
        // parsing the html for current weather data displayed at bottom of main activity
        class getWeather extends AsyncTask<Void, Void, String> {

            @Override
            protected String doInBackground(Void... params) {

                String result = null;
                try {
                    Document doc = Jsoup.connect("https://www.google.com/search?sourceid=chrome-psyapi2&ion=1&espv=2&ie=UTF-8&q=bend%20oregon%20weather&oq=bend%20oregon%20weather&aqs=chrome..69i57j0l5.7194j0j4").get();


                    // creating an elements object running a css query for specific class in String format
                    Elements tblElements = doc.select(".vk_bk sol-tmp");

                    result = tblElements.text();

                    // printing out result in degrees to the logcat
                    Log.d(mTag, result);
                } catch (IOException e) {
                    e.printStackTrace();
                }
                return result;
            }

            // passing the result returned from doInbackGround to execute and create instance of the getWeather class!!
            @Override
            protected void onPostExecute(String result) {

                ((TextView) findViewById(R.id.setweather)).setText(result);
            }

        }

        // the way I make the class execute while existing within other classes
        getWeather weather = new getWeather();
        weather.execute();

    }

    // handler to listen for all mainacivity button clicks: Sets each click to a new intent to start a new activity
    public View.OnClickListener myhandler = new View.OnClickListener() {
        @Override
        public void onClick(View v) {

            if (v.getId() == R.id.fbButton) {


                Intent fbintent = new Intent(MyActivity.this, facebook_cocc.class);
                startActivity(fbintent);
            }

            if (v.getId() == R.id.button3) {

                Intent web_view_login = new Intent(MyActivity.this, web_view_login.class);
                startActivity(web_view_login);

            }

            if (v.getId() == R.id.button2) {
                Intent clickmap = new Intent(MyActivity.this, google_map.class);
                startActivity(clickmap);

            }

            if (v.getId() == R.id.LibraryButton) {
                Intent LibraryPageIntent = new Intent(MyActivity.this, library.class);
                startActivity(LibraryPageIntent);

            }

            if (v.getId() == R.id.YtButton) {
                Intent YtubeIntent = new Intent(MyActivity.this, YouTube.class);
                startActivity(YtubeIntent);

            }

            if (v.getId() == R.id.DirectoryButton) {
                Intent DirectoryIntent = new Intent(MyActivity.this, Directory.class);
                startActivity(DirectoryIntent);
            }

            if (v.getId() == R.id.blackboardbutton){
                Intent blackboardintent = new Intent(MyActivity.this, blackboard.class);
            startActivity(blackboardintent);

        }

            if (v.getId() == R.id.employeebutton)
            {
                Intent employeeIntent = new Intent(MyActivity.this, emoloyee_webview.class);{
                    startActivity(employeeIntent);
                }
            }

            if (v.getId() == R.id.chatButton)
            {
                Intent chatIntent = new Intent(MyActivity.this, XMPP_Chat.class);{
                startActivity(chatIntent);
            }
            }
        }


    };

}




