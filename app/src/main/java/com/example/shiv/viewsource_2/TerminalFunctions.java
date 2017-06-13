package com.example.shiv.viewsource_2;

/**
 * Created by shiv on 5/28/17.
 */

import android.content.Context;
import android.database.Cursor;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Selection;
import android.view.Window;
import android.widget.EditText;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class TerminalFunctions {
    Database_Helper myDb;
    String Answer = "";
    //Map_Builder[] mp = new Map_Builder[50];
    //A[] arr = new A[4];

    public TerminalFunctions(Context context) {
        myDb = new Database_Helper(context);


    }

    public void initializeMenu(){
        boolean isInserted = myDb.insertData("README.txt",
                "Text",
                " Commands that are supported:\n ls: list files\n cat: Read Files\n clear: clear screen\n" +
                        "\n Directly run .exe files to run the level.\n ex: $ level_1.exe");
        isInserted = myDb.insertData("MANUAL.txt",
                ".txt",
                "-When in any level click on the object to view its source code.\nThe code that is highlighted in red cannot be edited.\n" +
                        "-The code in blue can be edited.\n" +
                        "-Your aim is to reach the flag by editing the source code.\n" +
                        "-Everytime you click the play button, the instructions in instruction queue is executed!\n\n" +
                        "Have fun" );
        isInserted = myDb.insertData("level_1.exe",
                ".exe",
                "01010101....01010\n Try running it as: $ level_name.exe");
        if(!Variables.menu_entry){
            isInserted = myDb.insertData("level_2.exe",
                    ".exe",
                    "01010101....01010\n Try running it as: $ level_name.exe");
        }
        isInserted = myDb.insertData("Journal.txt",
                ".txt",
                Variables.log_book );
        isInserted = myDb.insertData("About.txt",
                ".txt",
                Variables.about );

    }

    public boolean AddLevel(String Name, String Type, String Content){
        boolean isInserted =  myDb.insertData(Name,
                Type,
                Content);
        return  isInserted;

    }

        //Do not open this function. It's too huge. It has to be automated
    public void initializeLevel(){
        //resetTable("level_1");
        // database has to be specially created here
        // Remeber to put this in the database before hand
        //Also remember to automate this thing. Too huge to be true
        myDb.startLevel();
        boolean isInserted;
        switch (Variables.execute){
            case "level_1.exe":
                isInserted = myDb.insertLevelData("C",
                        "2", "3");
                if(isInserted){
                    Variables.error += "\nData is inserted!";
                }
                else{
                    Variables.error += "\ndata insertion mai tatti!";
                }

//                isInserted = myDb.insertLevelData("%",
//                        "0", "0");
//                isInserted = myDb.insertLevelData("%",
//                        "0", "1");
//                isInserted = myDb.insertLevelData("%",
//                        "0", "2");
//                isInserted = myDb.insertLevelData("%",
//                        "0", "3");
                isInserted = myDb.insertLevelData("@",
                        "0", "4");
                if(isInserted){
                    Variables.error += "\nData is inserted!";
                }
                break;
            case "level_2.exe":
                isInserted = myDb.insertLevelData("C",
                        "5", "5");
                if(isInserted){
                    Variables.error += "\nData is inserted!";
                }
                else{
                    Variables.error += "\ndata insertion mai tatti!";
                }

                isInserted = myDb.insertLevelData("%",
                        "0", "0");
                isInserted = myDb.insertLevelData("%",
                        "0", "1");
                isInserted = myDb.insertLevelData("%",
                        "0", "2");
                isInserted = myDb.insertLevelData("%",
                        "0", "3");
                isInserted = myDb.insertLevelData("@",
                        "2", "3");
                if(isInserted){
                    Variables.error += "\nData is inserted!";
                }
        }

//        isInserted = myDb.insertLevelData("%",
//                "void main(){" +
//
//                        "dont move anywhere. You are a fence}",
//                "0", "5");
//        isInserted = myDb.insertLevelData("%",
//                "void main(){" +
//
//                        "dont move anywhere. You are a fence}",
//                "0", "6");
//        isInserted = myDb.insertLevelData("%",
//                "void main(){" +
//
//                        "dont move anywhere. You are a fence}",
//                "0", "7");
//        isInserted = myDb.insertLevelData("%",
//                "void main(){" +
//
//                        "dont move anywhere. You are a fence}",
//                "0", "8");
//        isInserted = myDb.insertLevelData("%",
//                "void main(){" +
//
//                        "dont move anywhere. You are a fence}",
//                "0", "9");
//
//        isInserted = myDb.insertLevelData("%",
//                "void main(){" +
//                        "dont move anywhere. You are a fence}",
//
//                "1", "0");
//
//        isInserted = myDb.insertLevelData("%",
//                "void main(){" +
//                        "dont move anywhere. You are a fence}",
//                "2", "0");
//        isInserted = myDb.insertLevelData("%",
//                "void main(){" +
//                        "dont move anywhere. You are a fence}",
//
//                "3", "0");
//        isInserted = myDb.insertLevelData("%",
//                "void main(){" +
//                        "dont move anywhere. You are a fence}",
//
//                "4", "0");
//        isInserted = myDb.insertLevelData("%",
//                "void main(){" +
//                        "dont move anywhere. You are a fence}",
//
//                "5", "0");
//        isInserted = myDb.insertLevelData("%",
//                "void main(){" +
//                        "dont move anywhere. You are a fence}",
//
//                "6", "0");
//        isInserted = myDb.insertLevelData("%",
//                "void main(){" +
//                        "dont move anywhere. You are a fence}",
//
//                "7", "0");
//        isInserted = myDb.insertLevelData("%",
//                "void main(){" +
//                        "dont move anywhere. You are a fence}",
//
//                "8", "0");
//        isInserted = myDb.insertLevelData("%",
//                "void main(){" +
//                        "dont move anywhere. You are a fence}",
//
//                "9", "0");
//        isInserted = myDb.insertLevelData("%",
//                "void main(){" +
//                        "dont move anywhere. You are a fence}",
//
//                "10", "0");
//        isInserted = myDb.insertLevelData("%",
//                "void main(){" +
//                        "dont move anywhere. You are a fence}",
//
//                "11", "0");
//        isInserted = myDb.insertLevelData("%",
//                "void main(){" +
//                        "dont move anywhere. You are a fence}",
//
//                "12", "0");
//        isInserted = myDb.insertLevelData("%",
//                "void main(){" +
//                        "dont move anywhere. You are a fence}",
//
//                "13", "0");
//        isInserted = myDb.insertLevelData("%",
//                "void main(){" +
//                        "dont move anywhere. You are a fence}",
//
//                "14", "0");
//        //Right Walls
//        isInserted = myDb.insertLevelData("%",
//                "void main(){" +
//                        "dont move anywhere. You are a fence}",
//
//                "1", "9");
//
//        isInserted = myDb.insertLevelData("%",
//                "void main(){" +
//                        "dont move anywhere. You are a fence}",
//                "2", "9");
//        isInserted = myDb.insertLevelData("%",
//                "void main(){" +
//                        "dont move anywhere. You are a fence}",
//
//                "3", "9");
//        isInserted = myDb.insertLevelData("%",
//                "void main(){" +
//                        "dont move anywhere. You are a fence}",
//
//                "4", "9");
//        isInserted = myDb.insertLevelData("%",
//                "void main(){" +
//                        "dont move anywhere. You are a fence}",
//
//                "5", "9");
//        isInserted = myDb.insertLevelData("%",
//                "void main(){" +
//                        "dont move anywhere. You are a fence}",
//
//                "6", "9");
//        isInserted = myDb.insertLevelData("%",
//                "void main(){" +
//                        "dont move anywhere. You are a fence}",
//
//                "7", "9");
//        isInserted = myDb.insertLevelData("%",
//                "void main(){" +
//                        "dont move anywhere. You are a fence}",
//
//                "8", "9");
//        isInserted = myDb.insertLevelData("%",
//                "void main(){" +
//                        "dont move anywhere. You are a fence}",
//
//                "9", "9");
//        isInserted = myDb.insertLevelData("%",
//                "void main(){" +
//                        "dont move anywhere. You are a fence}",
//
//                "10", "9");
//        isInserted = myDb.insertLevelData("%",
//                "void main(){" +
//                        "dont move anywhere. You are a fence}",
//
//                "11", "9");
//        isInserted = myDb.insertLevelData("%",
//                "void main(){" +
//                        "dont move anywhere. You are a fence}",
//
//                "12", "9");
//        isInserted = myDb.insertLevelData("%",
//                "void main(){" +
//                        "dont move anywhere. You are a fence}",
//
//                "13", "9");
//        isInserted = myDb.insertLevelData("%",
//                "void main(){" +
//                        "dont move anywhere. You are a fence}",
//
//                "14", "9");
//        //Bottom walls
//        isInserted = myDb.insertLevelData("%",
//                "void main(){" +
//                        "dont move anywhere. You are a fence}",
//                "14", "1");
//        isInserted = myDb.insertLevelData("%",
//                "void main(){" +
//                        "dont move anywhere. You are a fence}",
//                "14", "2");
//        isInserted = myDb.insertLevelData("%",
//                "void main(){" +
//                        "dont move anywhere. You are a fence}",
//                "14", "3");
//        isInserted = myDb.insertLevelData("%",
//                "void main(){" +
//
//                        "dont move anywhere. You are a fence}",
//                "14", "4");
//        if(isInserted){
//            Variables.error += "\nData is inserted!";
//        }
//        isInserted = myDb.insertLevelData("%",
//                "void main(){" +
//
//                        "dont move anywhere. You are a fence}",
//                "14", "5");
//        isInserted = myDb.insertLevelData("%",
//                "void main(){" +
//
//                        "dont move anywhere. You are a fence}",
//                "14", "6");
//        isInserted = myDb.insertLevelData("%",
//                "void main(){" +
//
//                        "dont move anywhere. You are a fence}",
//                "14", "7");
//        isInserted = myDb.insertLevelData("%",
//                "void main(){" +
//
//                        "dont move anywhere. You are a fence}",
//                "14", "8");


    }

    public void initializeLevelSource(){
        boolean isInserted;
        switch(Variables.execute){
            case "level_1.exe":
                isInserted = myDb.insertLevelSource("import soul import mind\n" +
                                "import engine import Physics\n" +
                                "# the of each code should be this long V---\n" +
                                "#------------------------------------------\n" +
                                "# at first there was nothing and then God\n" +
                                "# said Let there be Light!\n" +
                                "'''\n" +
                                "Just some fake and dummy code for my game.\n" +
                                "Most Libraries aren't real\n" +
                                "and most functions aren't real. Dummy code!\n" +
                                "'''\n" +
                                "Self = mind()\n" +
                                "Force = Physics()\n" +
                                "def Move(Dir):\n" +
                                "    for char in Path:\n" +
                                "        if char == \"\\n\":\n" +
                                "            continue\n" +
                                "        else:\n" +
                                "            if Dir == \"W\":\n" +
                                "                Self.Act(Force.up(1))\n" +
                                "            elif Dir == \"A\":\n" +
                                "                Self.Act(Force.left(1))\n" +
                                "            if Dir == \"S\":\n" +
                                "                Self.Act(Force.down(1))\n" +
                                "            if Dir == \"D\":\n" +
                                "                Self.Act(Force.right(1))\n" +
                                "\n" +
                                "def endMaze():\n" +
                                "    if Self.Finds(Physics.Singularity):\n" +
                                "        return True\n" +
                                "    else:\n" +
                                "        return False\n" +
                                "\n" +
                                "def Travel():\n" +
                                "    for i in range(0, 10):\n" +
                                "        if (endMaze()):\n" +
                                "            return true\n" +
                                "        else:\n" +
                                "            Path = '''",
                        "r", "1", 1);
                if(isInserted){
                    Variables.error += "\nSource is inserted!";
                }
                else{
                    Variables.error += "\nSource insertion mai tatti!";
                }
                isInserted = myDb.insertLevelSource("",
                        "w", "2", 1);
                isInserted = myDb.insertLevelSource("            '''\n" +
                                "            Move(Path)\n" +
                                "Travel()",
                        "r", "3", 1);
                isInserted = myDb.insertLevelSource("This the code for the walla",
                        "r", "1", 2);
                isInserted = myDb.insertLevelSource("Write here",
                        "w", "2", 2);
                isInserted = myDb.insertLevelSource("editable here",
                        "w", "3", 2);
                break;
            case "level_2.exe":
                isInserted = myDb.insertLevelSource("code of level 2",
                        "r", "1", 1);
                if(isInserted){
                    Variables.error += "\nSource is inserted!";
                }
                else{
                    Variables.error += "\nSource insertion mai tatti!";
                }
                isInserted = myDb.insertLevelSource("",
                        "w", "2", 1);
                isInserted = myDb.insertLevelSource("            '''\n" +
                                "            Move(Path)\n" +
                                "Travel()",
                        "r", "3", 1);
                isInserted = myDb.insertLevelSource("This the code for the walla",
                        "r", "1", 2);
                isInserted = myDb.insertLevelSource("Write here",
                        "w", "2", 2);
                isInserted = myDb.insertLevelSource("editable here",
                        "w", "3", 2);
                break;
        }




    }

    // Populate the world array
    // this fucntion puts all the map elements int the world array
    public String[][] Populate_World(String[][] world){
        Cursor res = myDb.getAllLevelData();
        if(res.getCount() == 0 ) {
            // show message
            return (world);
        }

        // Very Risky Business. But! I'm gonna try !
        StringBuffer buffer = new StringBuffer();
        while (res.moveToNext()) {
            Cursor resrc = myDb.getAllSource();

            List<Map<String, String>> Srl = new ArrayList<Map<String, String>>();
            if(resrc.getCount() == 0 ) {
                // source not present
                Variables.error+= "\nkhali haath vapis";
                return (world);
            }
            String x = res.getString(2); //3
            String y = res.getString(3); //4
            String Sym = res.getString(1);
            //String Source = res.getString(2); //source in this var
            Integer ID = res.getInt(0);
            world[Integer.parseInt(x)][Integer.parseInt(y)] = Sym;
            Variables.world_id[Integer.parseInt(x)][Integer.parseInt(y)] = ID.toString();
            //Variables.error+= "\nyaha tak aaya";
            // Setting the up the dictionary
            while(resrc.moveToNext()){
                try {
                    if (resrc.getInt(1) == ID) {
                        Map<String, String> tmap = new HashMap<String, String>();
                        //Variables.error += "\n" + resrc.getString(2) + " " + resrc.getString(3) + " " + ID.toString();
                        tmap.put(resrc.getString(2), resrc.getString(3));
                        Srl.add(tmap);
                        //Variables.error += "\nReaching here";
                        //Variables.SrcList.add(Variables.TypeMap);
                        Variables.error += "\n"+Srl.toString()+"\n";
                        //Variables.TypeMap.clear();
                    } else {
                        continue;
                    }
                }
                catch (Exception e){
                    Variables.error += e.toString();
                }

            }
            if(Variables.enter_level) {
                Variables.map.put(ID.toString(), Srl); // map created hopefully
            }
            Variables.error += "\n\n\n" + Variables.map.toString();
            //Variables.SrcList.clear();
        }

        // Show all data
        //Answer = buffer.toString();

        return world;
    }


    public void resetTable(String TName){
        boolean trunc = myDb.truncateData(TName);
    }

    public String FireQuer(String Query) {
        Query = Query.replace("root@ViewSource:$ ", "");
        String Q[] = Query.split(" ");
        switch (Q[0]){
            case "ls":
                Answer = list_Files();
                break;
            case "clear":
                Answer = "";
                break;
            case "cat":
                Answer = view_contents(Q[1]);
                break;
            default:
                if(Query.contains(".exe")){
                    Answer = find_exe(Q[0]);

                }
                else{
                    Answer = Query +": Command Not Recognized";
                }
        }

//
//        if(Query.equals("ls")){
//            Answer = list_Files();
//        }
//        else{
//            Answer = ;
//        }

        //Answer = viewAll();
        return Answer;

    }

    public String viewAll() {

        Cursor res = myDb.getAllData();
        if(res.getCount() == 0) {
            // show message
            return ("No data in the database");
        }

        StringBuffer buffer = new StringBuffer();
        while (res.moveToNext()) {
            buffer.append("Id :"+ res.getString(0)+"\n");
            buffer.append("File Name :"+ res.getString(1)+"\n");
            buffer.append("Type :"+ res.getString(2)+"\n");
            buffer.append("Contents :"+ res.getString(3)+"\n\n");
        }

        // Show all data
        //Answer = buffer.toString();
        return buffer.toString();
    }


    public String view_contents(String Q) {

        Cursor res = myDb.getAllData();
        boolean found = false;
        if(res.getCount() == 0) {
            // show message
            return ("No data in the database");
        }

        StringBuffer buffer = new StringBuffer();
        while (res.moveToNext()) {
            if(Q.equals(res.getString(1))){
                buffer.append(res.getString(3)+"\n");
                found = true;
                break;
            }

        }
        if(!found){
            return "No such file";
        }

        // Show all data
        //Answer = buffer.toString();
        return buffer.toString();
    }

    public String find_exe(String Q) {

        Cursor res = myDb.getAllData();
        boolean found = false;
        if(res.getCount() == 0) {
            // show message
            return ("No data in the database");
        }

        StringBuffer buffer = new StringBuffer();
        while (res.moveToNext()) {
            if(Q.equals(res.getString(1))){
                buffer.append("Loading...");
                found = true;
                break;
            }

        }
        if(!found){
            return "No such executable file";
        }

        // Show all data
        //Answer = buffer.toString();
        return buffer.toString();
    }

    public String list_Files() {

        Cursor res = myDb.getAllData();
        if(res.getCount() == 0) {
            // show message
            return ("No data in the database");
        }

        StringBuffer buffer = new StringBuffer();
        while (res.moveToNext()) {
            buffer.append(res.getString(1)+"\n");
        }

        // Show all data
        //Answer = buffer.toString();
        return buffer.toString();
    }

}


