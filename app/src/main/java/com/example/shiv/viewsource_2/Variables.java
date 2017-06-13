package com.example.shiv.viewsource_2;

import android.widget.EditText;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

/**
 * Created by shiv on 5/28/17.
 * My map structure will be something like this:
 * {"Char_ID" : ((Src1: type), (Src2: type), (src3: type) ....)}
 *
 */

public class Variables {

    Random rn1 = new Random();
    public static String log_book = "[D-344|T-11:36]\nDear Dairy,\n\tI am beginning to think that there is more to this reality. Sometimes I can get a glimpse " +
            "of my true nature. I see some writing. I wonder if I'm deluded.\n\n[D-347|T-21:02]\n" +
            "Dear Dairy.\n" +
            "\tHoly Shit! I can see it! I think I changed my reality somehow!\n" +
            "\n" +
            "[D-351|T-13:13]\nDear Dairy.\n\tI Sense  Strange Object. I wonder what it is...\n\n";
    public static String Creaname = "Shiv";
    public static String about = "\nI'm "+Creaname+"...\nThis is mostly an experimental game." +
            " I made this game coz I had nothing to do for the day. I will add more fun levels in the future." +
            "\nhelp me.";
    public static Integer randN = -1;
    public static String execute = "";
    public static String Selected_Sprite = "None";
    public static String error = "no error yet";
    public static String[] world_id[] = new String[15][10];
    public static String p1 = "", p2 = "";
    public static int SpriteImg;
    public static String SpriteSource = "";
    //This will be the final structure of the source code retreival
    public static Map<String, List<Map<String, String>>> map = new HashMap<String, List<Map<String, String>>>();
    public static List<Map<String, String>> SrcList = new ArrayList<Map<String, String>>();
    public static Map<String, String> TypeMap = new HashMap<String, String>();
    //This is the data that will be used when I need to update the code in any given section
    public static Integer editedj = 0;
    public static String original_key = "";
    public static String pseudo_key = "";
    public static Map<String, EditText> selMap = new HashMap<String, EditText>();
    //This will fix my problem easily!
    public static boolean enter_level = true;
    public  static boolean menu_entry = true;
    //Goal Positions:
    public  static  Integer gx = -1;
    public  static Integer gy = -1;
    //Char Position
    public  static  Integer cx = -1;
    public  static Integer cy = -1;
    //Program Stack
    public static String CallSt = "";





}
