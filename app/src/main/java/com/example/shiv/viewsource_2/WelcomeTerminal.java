package com.example.shiv.viewsource_2;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.Selection;
import android.text.TextWatcher;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.view.KeyEvent;
import android.view.inputmethod.EditorInfo;
import android.widget.TextView;

import java.io.Console;
import java.util.Random;

import static android.R.attr.max;

public class WelcomeTerminal extends AppCompatActivity implements View.OnKeyListener {

    EditText edt;
    TextView res_edt;// = (TextView) findViewById(R.id.textView3);
    Button enter;
    String Terminal_Val;
    String Result;
    TerminalFunctions Ter;
    Intent in;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_welcome_terminal);
        edt = (EditText) findViewById(R.id.editText);
        Ter = new TerminalFunctions(this);
        Ter.resetTable("StartMenu");
        Ter.initializeMenu();
        Random rn = new Random();

        res_edt = (TextView) findViewById(R.id.textView3);
        //res_edt.setText(" by Shiv with <3\n");
        Terminal_Val = "";
        Result = "";
        in = new Intent(WelcomeTerminal.this, LevelActivity.class);
        Variables.randN = rn.nextInt(5);
        if(Variables.randN == 3){
            Variables.Creaname = "Satan";
            Variables.about = "\nI'm "+Variables.Creaname+"...\n666\n666\n666\n666";
        }
        else{
            Variables.Creaname = "Shiv";
            Variables.about = "\nI'm "+Variables.Creaname+"...\nThis is mostly an experimental game." +
                    " I made this game coz I had nothing to do for the day. I will add more fun levels in the future." +
                    "\nhelp me.";
        }


        edt.setText("root@ViewSource:$ ");
        Selection.setSelection(edt.getText(), edt.getText().length());


        edt.addTextChangedListener(new TextWatcher() {

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                // TODO Auto-generated method stub

            }

            @Override
            public void beforeTextChanged(CharSequence s, int start, int count,
                                          int after) {
                // TODO Auto-generated method stub

            }

            @Override
            public void afterTextChanged(Editable s) {
                if(!s.toString().contains("root@ViewSource:$ ")){
                    edt.setText("root@ViewSource:$ ");
                    Selection.setSelection(edt.getText(), edt.getText().length());


                }

            }
        });
        edt.setOnKeyListener(this);

    }

    @Override
    public boolean onKey(View v, int keyCode, KeyEvent event) {
        if(keyCode == KeyEvent.KEYCODE_ENTER){
            Terminal_Val = edt.getText().toString();
            boolean unl = true;
            res_edt.setText(Terminal_Val);
            Result = Ter.FireQuer(Terminal_Val);
            if(Result.equals("Loading...")){
                Variables.execute = Terminal_Val.replace("root@ViewSource:$ ", "");
//                in = new Intent();
//                try{
//                in.setClassName(WelcomeTerminal.this, Variables.execute.replace(".exe", ""));
//                }
//                catch (Exception e){
//                    unl = false;
//                }
                //System.out.println(Variables.execute);
                if(Variables.execute.equals("level_2.exe")){

                    //System.out.println("Inside");
                    Result = "Level Under Construction!... Coming soon!";
                }
                else {
                    in = new Intent(WelcomeTerminal.this, LevelActivity.class);
                    startActivity(in);
                    Result = "";
                }
//                switch (Variables.execute){
//                    case "level_1.exe":
//                        in = new Intent(WelcomeTerminal.this, LevelActivity.class);
//                        break;
//                    case "level_2.exe":
//
//                    default:
//                        unl = false;
//
//
//                }
//                if(unl){
//
//                }

            }
            res_edt.setText(Result);

        }
        return false;
    }


}
