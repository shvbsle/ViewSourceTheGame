package com.example.shiv.viewsource_2;

import android.content.Intent;
import android.graphics.Typeface;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.Selection;
import android.text.TextWatcher;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class SourceActivity extends AppCompatActivity {
    TextView selected_sprite;
    TextView Source_code;
    ImageView done_btn;
    Intent in;
    String Test = "";
    ArrayList<EditText> etl = new ArrayList<EditText>();


    int i = 0;
    int j = 0;
    int m = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_source);
        //this boolean is to be made true to reset the level! Genius
        Variables.enter_level = false;
        LinearLayout ll = (LinearLayout) findViewById(R.id.SourceDock);

        TextView[] myTextViews = new TextView[10];
        EditText[] myEditViews = new EditText[10];
        done_btn = (ImageView) findViewById(R.id.done_button);
        in = new Intent(SourceActivity.this, LevelActivity.class);
        selected_sprite =  (TextView) findViewById(R.id.textView5);
        Source_code =  (TextView) findViewById(R.id.textView7);
        selected_sprite.setText("Subject No. #"+Variables.Selected_Sprite);
        try {
            Variables.SrcList = Variables.map.get(Variables.Selected_Sprite);
            //Toast.makeText(SourceActivity.this, "Src now!!\n"+Variables.SrcList.toString(), Toast.LENGTH_LONG).show();
            Test = "#root/world/VS/Generate_Source.py";

            //Make the Redering look sexier in this process!
            for (Map<String, String> E : Variables.SrcList) {


                // set some properties of rowTextView or something
                //Test += E.toString() + "\n";//E; // number = new Integer(number+1);

                for (final Map.Entry<String, String> entry : E.entrySet())
                {
                    //Read only Code! Biatch
                    if(entry.getValue().equals("r")){
                        TextView rowTextView = new TextView(this);
//                        LinearLayout.LayoutParams llp = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT);
//                        llp.setMargins(10, 10, 0, 0);
                        rowTextView.setTypeface(Typeface.create("sans-serif-condensed", Typeface.NORMAL));
                        rowTextView.setTextSize(18);
                        rowTextView.setTextColor(ContextCompat.getColor(SourceActivity.this, R.color.ReadB));
                        rowTextView.setBackgroundColor(ContextCompat.getColor(SourceActivity.this, R.color.readC));
                        //rowTextView.layout(10, 10, 0, 0);
                        //rowTextView.setLayoutParams(llp);
                        rowTextView.setText(entry.getKey());
                        ll.addView(rowTextView);
                        myTextViews[i] = rowTextView;
                        i++;

                    }
                    else if(entry.getValue().equals("w")){
                        EditText et = new EditText(this);
//                        LinearLayout.LayoutParams llp = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT);
//                        llp.setMargins(10, 10, 0, 0);
                        et.setTypeface(Typeface.create("sans-serif-condensed", Typeface.NORMAL));
                        et.setTextSize(18);
                        et.setTextColor(ContextCompat.getColor(SourceActivity.this, R.color.WriteC));

                        //et.layout(10, 10, 0, 0);
                        //et.setLayoutParams(llp);
                        et.setText(entry.getKey());
                        ll.addView(et);
                        myEditViews[j] = et;
                        Variables.selMap.put(entry.getKey(),myEditViews[j]);
                        myEditViews[j].setOnTouchListener(new View.OnTouchListener()
                        {
                            public boolean onTouch(View arg0, MotionEvent arg1)
                            {
                                Variables.original_key = entry.getKey();
                                Variables.pseudo_key = entry.getKey();
                                //evalue="1";
                                return false;
                            }
                        });
                        myEditViews[j].setOnClickListener(new View.OnClickListener() {
                            public void onClick(View v) {
                                Variables.original_key = entry.getKey();
                                Variables.pseudo_key = entry.getKey(); //here I store the original Key
                                //Toast.makeText(SourceActivity.this, "Pressed Enter!\n"+Variables.selMap.get(Variables.original_key).getText(), Toast.LENGTH_SHORT).show();
//
                            }
                        });

                        j++;

                    }

                    //System.out.println(entry.getKey() + "/" + entry.getValue());
                }


            }
        }
        catch (Exception e){
            Test = "Strange! This Entity has no source code!";
        }



        Source_code.setText(Test);

        //Set the image of the selected sprite
        ImageView propic =(ImageView) findViewById(R.id.propic);
        propic.setImageResource(Variables.SpriteImg);

        //imp loop needed for code editing
        for (m = 0; m < j; m++){

            myEditViews[m].addTextChangedListener(new TextWatcher() {
                private boolean isReached = false;

                @Override
                public void onTextChanged(CharSequence s, int start, int before, int count) {
                    // TODO Auto-generated method stub


                }

                @Override
                public void beforeTextChanged(CharSequence s, int start, int count,
                                              int after) {
                    // TODO Auto-generated method stub
//                    String temp  = Variables.selMap.get(Variables.original_key).getText().toString();
                    //Variables.pseudo_key = Variables.original_key;

                }

                @Override
                public void afterTextChanged(Editable s) {
                    //This whole this was so fucking hectic!

                    String temp  = new String(Variables.selMap.get(Variables.original_key).getText().toString());//myEditViews[Variables.editedj].getText().toString();
                    System.out.println("\nnew:\n"+Variables.original_key + "| here!\n" + Variables.map.get(Variables.Selected_Sprite)+"\ntemp:\n"+temp+"\npseudoKye\n"+Variables.pseudo_key);

                    List<Map<String, String>> temp2 = new ArrayList<Map<String, String>>();
                    temp2 = Variables.map.get(Variables.Selected_Sprite);

                    for (Map<String, String> entry : temp2)
                    {
                        String v = entry.get(Variables.pseudo_key);
                        if(v != null) {
                            entry.remove(Variables.pseudo_key);
                            entry.put(temp, "w");
                        }
                    }

                    Variables.map.remove(Variables.Selected_Sprite);
                    Variables.map.put(Variables.Selected_Sprite, temp2);
                    Variables.SrcList = Variables.map.get(Variables.Selected_Sprite);
                    Variables.pseudo_key = temp;
                    System.out.println(Variables.map.get(Variables.Selected_Sprite).toString()+"\nHere is the selMap:\n"+Variables.selMap.toString());
                    //Toast.makeText(SourceActivity.this, "Hua na change MC", Toast.LENGTH_SHORT).show();

                }
            });

        }
        done_btn.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                //Toast.makeText(SourceActivity.this, "Hey I'm done!", Toast.LENGTH_SHORT).show();
                try {
                    Variables.CallSt = Variables.selMap.get(Variables.original_key).getText().toString();
                }
                catch (Exception e){
                    //do nothing
                }
                startActivity(in);
            }
        });

    }


}
