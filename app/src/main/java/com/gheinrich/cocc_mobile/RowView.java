package com.gheinrich.cocc_mobile;


import android.content.Context;
import android.widget.LinearLayout;
import android.widget.TextView;

/**
 * Created by gheinrich on 7/1/2014.
 */
public class RowView extends LinearLayout {



    TextView theTextview;
    public RowView(Context context) {
        super(context);
        this.setOrientation(LinearLayout.HORIZONTAL);


        this.theTextview = new TextView(context);

        this.theTextview.setTextColor(getResources().getColor(R.color.cocc_grey));


        this.addView(this.theTextview);
    }


    public void setTextView (String mainText){
        this.theTextview.setText(mainText);
    }


}
