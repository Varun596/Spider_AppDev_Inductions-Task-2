package com.example.varunelango.texteditor;

import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.widget.TextView;
import android.content.Intent;
import android.view.View;
import android.graphics.Color;
import android.graphics.Typeface;
import android.util.TypedValue;

public class Display extends ActionBarActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_display);

        Bundle EditData = getIntent().getBundleExtra("Bundle");
        if(EditData==null){
            return;
        }
        String Text = EditData.getString("TextToEdit");
        final TextView DisplayText = (TextView) findViewById(R.id.TextView);
        DisplayText.setText(Text);

        String size = EditData.getString("Size");
        String color = EditData.getString("Color");
        String font = EditData.getString("Font");
        Boolean bold = EditData.getBoolean("Bold");
        Boolean italics = EditData.getBoolean("Italics");

        DisplayText.setTextColor(Color.parseColor(color));

        float sizes = Float.parseFloat(size);
        DisplayText.setTextSize(TypedValue.COMPLEX_UNIT_SP,sizes * getResources().getDisplayMetrics().scaledDensity);

        int style;
        if((bold)&&(italics)) style = Typeface.BOLD_ITALIC;
        else if(bold) style = Typeface.BOLD;
        else if(italics) style = Typeface.ITALIC;
        else style = Typeface.NORMAL;
        Typeface typeface = Typeface.createFromAsset(getAssets(),font+".ttf");
        DisplayText.setTypeface(typeface,style);
    }

    public void onClick1(View view){
        Intent i=new Intent(this,Home.class);
        startActivity(i);
    }

    public void onClick2(View view) {
        this.finish();
        Intent intent = new Intent(Intent.ACTION_MAIN);
        intent.addCategory(Intent.CATEGORY_HOME);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(intent);
    }

}
