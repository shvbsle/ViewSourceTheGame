package com.example.shiv.viewsource_2;

import android.content.Intent;
import android.provider.Settings;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
//import javax.swing.Timer;
import android.os.Handler;

import java.util.Timer;
//import java.util.logging.Handler;

public class level_1 extends AppCompatActivity {
    TextView executing;
    ImageView img;
    Intent in, menu;
    TerminalFunctions Ter;
    String[] world_array[] = new String[15][10];
    String[] reset_world[] = new String[15][10];
    String World_render = "";
    GridView gridView;
    String char_pos[] = new String[150];// {"@", "l", "f", "*", "5"};//
    String resChar[];// = new String[150];
    int sprite[] = new int[150];// {R.drawable.eye,R.drawable.eye,R.drawable.eye,R.drawable.eye,R.drawable.eye};//
    int resSprite[] ;//= new int[150];

    final Handler handler = new Handler();
    //I'm disabling the back button coz I can// Yolo!
    @Override
    public void onBackPressed() {
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_level);
        Variables.menu_entry = false;
        executing = (TextView) findViewById(R.id.textView4);
        Variables.original_key = "";
        Variables.pseudo_key = "";
        executing.setText(Variables.execute);
        img = (ImageView) findViewById(R.id.imageView2);
        in = new Intent(level_1.this, SourceActivity.class);
        menu = new Intent(level_1.this, WelcomeTerminal.class);
        Ter = new TerminalFunctions(this);
        Ter.resetTable(Variables.execute.replace(".exe", ""));
        Ter.initializeLevel();
        if(Variables.enter_level){
            Ter.initializeLevelSource();
        }
        //Works good thill here

        //populating the world
        for(int i = 0; i <15; i ++){
            for(int j = 0; j< 10; j++){
                world_array[i][j] = "_";
                reset_world[i][j] = "_";
                Variables.world_id[i][j] = "-1";
            }
        }

        world_array = Ter.Populate_World(world_array);
        reset_world = Ter.Populate_World(reset_world);
        render_world(world_array);
        //use these to reset the world on reset button
        resChar = char_pos.clone();
        resSprite = sprite.clone();
        Variables.error += Variables.execute+ "\n";
        gridView = (GridView) findViewById(R.id.gview);
        final GridAdapter adapter = new GridAdapter(level_1.this, sprite, char_pos);
        gridView.setAdapter(adapter);
        gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                //Toast.makeText(LevelActivity.this, char_pos[position], Toast.LENGTH_SHORT).show();
                Variables.Selected_Sprite = char_pos[position]; //set the Id of the char
                Variables.SpriteImg = sprite[position]; //Set the image of the char
                System.out.println("\nClicked\n"+Variables.Selected_Sprite+" \n positions\n"+position);

                startActivity(in);


            }
        });

        img.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                //Fucking Works!
                //adapter = null;
                //I have to press it every time. Dammit
                update_world();
            }
        });


    }

    public void render_world(String[][] world){
        int counter = 0;
        for(int i = 0; i <15; i ++){
            for(int j = 0; j< 10; j++){

                World_render += world_array[i][j]+ " ";
                char_pos[counter] = Variables.world_id[i][j];
                if (world_array[i][j].equals("%")){
                    sprite[counter] = R.drawable.fence;
                }
                else if(world_array[i][j].equals("C")){
                    sprite[counter] = R.drawable.fucku;
                    Variables.cx = i;
                    Variables.cy = j;
                }
                else if(world_array[i][j].equals("_")){
                    sprite[counter] = R.drawable.blank;
                }
                else if(world_array[i][j].equals("@")){
                    sprite[counter] = R.drawable.fin2;
                    Variables.gx = i;
                    Variables.gy = j;

                }

                counter +=1;// this one staement cost me

            }
            World_render += "\n";
        }

    }


    public void update_world(){
        int delay = 1000;
        int i = 0;
        if(Variables.CallSt != null && !Variables.CallSt.isEmpty()) {
//            for (String[] x : world_array)
//            {
//                for (String y : x)
//                {
//                    System.out.print(y + " ");
//                }
//                System.out.println("\n");
//            }
            //System.out.println("\nnow loop starts\n");
            // for (int i = 0; i < Variables.CallSt.length(); i++) {
            // if(){
            //new Timer(delay,).start();

            if (Variables.CallSt.charAt(i) == 'W') {
                //System.out.println("\n"+(((10*Variables.cx)+Variables.cy)-10)+"\n");
                int temp = sprite[((10*Variables.cx)+Variables.cy)-10];//sprite[((Variables.cx+1)*(Variables.cy+1)-1)-10];
                String temp2 = char_pos[((10*Variables.cx)+Variables.cy)-10];
                String temp3 = Variables.world_id[Variables.cx-1][Variables.cy];
                sprite[((10*Variables.cx)+Variables.cy)-10] = sprite[((10*Variables.cx)+Variables.cy)];
                sprite[((10*Variables.cx)+Variables.cy)] = temp;
                //char_pos
                char_pos[((10*Variables.cx)+Variables.cy)-10] = world_array[Variables.cx][Variables.cy];
                char_pos[((10*Variables.cx)+Variables.cy)] = temp2;
                //world array update
                world_array[Variables.cx-1][Variables.cy] = "C";
                world_array[Variables.cx][Variables.cy] = "_";
                Variables.world_id[Variables.cx-1][Variables.cy] = Variables.world_id[Variables.cx][Variables.cy];
                Variables.world_id[Variables.cx][Variables.cy] = temp3;
                Variables.cx -=1;

            }
            else if (Variables.CallSt.charAt(i) == 'A') {
                //System.out.println("\n"+(((10*Variables.cx)+Variables.cy)-10)+"\n");
                int temp = sprite[((10*Variables.cx)+Variables.cy)-1];//sprite[((Variables.cx+1)*(Variables.cy+1)-1)-10];
                String temp2 = char_pos[((10*Variables.cx)+Variables.cy)-1];
                String temp3 = Variables.world_id[Variables.cx][Variables.cy-1];
                //String temp3 = world_array;
                sprite[((10*Variables.cx)+Variables.cy)-1] = sprite[((10*Variables.cx)+Variables.cy)];
                sprite[((10*Variables.cx)+Variables.cy)] = temp;
                //char_pos
                char_pos[((10*Variables.cx)+Variables.cy)-1] = world_array[Variables.cx][Variables.cy];
                char_pos[((10*Variables.cx)+Variables.cy)] = temp2;
                //world array update
                world_array[Variables.cx][Variables.cy-1] = "C";
                world_array[Variables.cx][Variables.cy] = "_";
                Variables.world_id[Variables.cx][Variables.cy-1] = Variables.world_id[Variables.cx][Variables.cy];
                Variables.world_id[Variables.cx][Variables.cy] = temp3;
                Variables.cy -=1;

            }
            else if (Variables.CallSt.charAt(i) == 'S') {
                //System.out.println("\n"+(((10*Variables.cx)+Variables.cy)-10)+"\n");
                int temp = sprite[((10*Variables.cx)+Variables.cy)+10];//sprite[((Variables.cx+1)*(Variables.cy+1)-1)-10];
                String temp2 = char_pos[((10*Variables.cx)+Variables.cy)+10];
                String temp3 = Variables.world_id[Variables.cx+1][Variables.cy];
                //String temp3 = world_array;
                sprite[((10*Variables.cx)+Variables.cy)+10] = sprite[((10*Variables.cx)+Variables.cy)];
                sprite[((10*Variables.cx)+Variables.cy)] = temp;
                //char_pos
                char_pos[((10*Variables.cx)+Variables.cy)+10] = world_array[Variables.cx][Variables.cy];
                char_pos[((10*Variables.cx)+Variables.cy)] = temp2;
                //world array update
                world_array[Variables.cx+1][Variables.cy] = "C";
                world_array[Variables.cx][Variables.cy] = "_";
                Variables.world_id[Variables.cx+1][Variables.cy] = Variables.world_id[Variables.cx][Variables.cy];
                Variables.world_id[Variables.cx][Variables.cy] = temp3;
                Variables.cx +=1;

            }
            else if (Variables.CallSt.charAt(i) == 'D') {
                int temp = sprite[((10*Variables.cx)+Variables.cy)+1];//sprite[((Variables.cx+1)*(Variables.cy+1)-1)-10];
                String temp2 = char_pos[((10*Variables.cx)+Variables.cy)+1];
                String temp3 = Variables.world_id[Variables.cx][Variables.cy+1];
                //String temp3 = world_array;
                sprite[((10*Variables.cx)+Variables.cy)+1] = sprite[((10*Variables.cx)+Variables.cy)];
                sprite[((10*Variables.cx)+Variables.cy)] = temp;
                //char_pos
                char_pos[((10*Variables.cx)+Variables.cy)+1] = world_array[Variables.cx][Variables.cy];
                char_pos[((10*Variables.cx)+Variables.cy)] = temp2;
                //world array update
                world_array[Variables.cx][Variables.cy+1] = "C";
                world_array[Variables.cx][Variables.cy] = "_";
                Variables.world_id[Variables.cx][Variables.cy+1] = Variables.world_id[Variables.cx][Variables.cy];
                Variables.world_id[Variables.cx][Variables.cy] = temp3;
                Variables.cy +=1;

            }
            render_world(world_array);//idk what will happen
            GridAdapter ad2 = new GridAdapter(level_1.this, sprite, char_pos);
            gridView.setAdapter(ad2);
            if(Variables.CallSt != null && !Variables.CallSt.isEmpty()) {
                Variables.CallSt = Variables.CallSt.substring(1);
            }
            if(Variables.cx == Variables.gx && Variables.cy == Variables.gy){
                Toast.makeText(level_1.this, "Level Won!", Toast.LENGTH_SHORT).show();
                startActivity(menu);
                //boolean a =Ter.AddLevel("level_2.exe", ".exe", "01010101....01010\n" +" Try running it as: $ level_name.exe");
                //Ter.initializeLevel();
//                if(a) {
//                    System.out.println("\n\nLevel Inserted!\n\n");
//                }
//                else{
//                    System.out.println("\n\nLevel Insertion Error\n\n");
//                }
            }
            System.out.println("\nView\n");
//            for (String[] x : world_array)
//            {
//                for (String y : x)
//                {
//                    System.out.print(y + " ");
//                }
//                System.out.println("\n");
//            }
        }else{
            Toast.makeText(level_1.this, "Stack End!", Toast.LENGTH_SHORT).show();
        }

    }

}
