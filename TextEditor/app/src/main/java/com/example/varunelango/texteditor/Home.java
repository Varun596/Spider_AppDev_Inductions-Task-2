package com.example.varunelango.texteditor;

import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.content.Intent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.CheckBox;
import android.widget.ListView;
import java.util.ArrayList;

public class Home extends ActionBarActivity implements View.OnClickListener {

    public Bundle bundle = new Bundle();
    String[] Colors = {"BLACK","BLUE","CYAN","GRAY","GREEN","MAGENTA","RED","WHITE","YELLOW"};
    String[] Fonts = {"Arial","Calibri","Comic Sans","Times New Roman"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        Spinner CS = (Spinner)findViewById(R.id.ColorSpinner);
        Spinner SS = (Spinner) findViewById(R.id.SizeSpinner);
        final CheckBox BCB = (CheckBox) findViewById(R.id.BoldCheckBox);
        final CheckBox ICB = (CheckBox) findViewById(R.id.ItalicsCheckBox);
        ListView FLV = (ListView) findViewById(R.id.FontListView);
        Button EB = (Button) findViewById(R.id.EditButton);

        ArrayList<String> Sizes = new ArrayList<String>();
        for(int i=8;i<30;i+=2) {
        Sizes.add(Integer.toString(i));
        }
        Sizes.add(Integer.toString(36));
        Sizes.add(Integer.toString(48));

        final ArrayAdapter<String> SizesAdapter = new ArrayAdapter<String>(this,android.R.layout.simple_spinner_dropdown_item,Sizes);
        SS.setAdapter(SizesAdapter);
            SS.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                @Override
                public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                    String size = SizesAdapter.getItem(position);
                    bundle.putString("Size", size);
                }

                @Override
                public void onNothingSelected(AdapterView<?> parent) {

                }
            });

        final ArrayAdapter<String> ColorsAdapter = new ArrayAdapter<String>(this,android.R.layout.simple_spinner_dropdown_item,Colors);
        CS.setAdapter(ColorsAdapter);
            CS.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                @Override
                public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                    String color = ColorsAdapter.getItem(position);
                    bundle.putString("Color", color);
                }

                @Override
                public void onNothingSelected(AdapterView<?> parent) {

                }
            });

        String font ="Arial";
        bundle.putString("Font",font);

        final ArrayAdapter<String> FontsAdapter = new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,Fonts);
        FLV.setAdapter(FontsAdapter);
            FLV.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                    String font = FontsAdapter.getItem(position);
                    bundle.putString("Font", font);

                    boolean BoldChecked = BCB.isChecked();
                    boolean ItalicsChecked = ICB.isChecked();
                    bundle.putBoolean("Bold", BoldChecked);
                    bundle.putBoolean("Italics", ItalicsChecked);
                }
            });

        EB.setOnClickListener(this);
        }

   public void onClick(View view){
        Intent i=new Intent(getApplicationContext(),Display.class);
        final EditText input = (EditText) findViewById(R.id.Input);
        bundle.putString("TextToEdit",input.getText().toString());
        i.putExtra("Bundle",bundle);
        startActivity(i);
   }
}
