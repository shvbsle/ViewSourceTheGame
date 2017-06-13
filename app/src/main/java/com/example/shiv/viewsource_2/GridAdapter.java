package com.example.shiv.viewsource_2;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

/**
 * Created by shiv on 5/28/17.
 */

public class GridAdapter extends BaseAdapter{

    private int icons[];
    private String letters[];
    private Context context;
    private LayoutInflater inflater;

    public GridAdapter(Context context, int icons[], String letters[]){
        this.context = context;
        this.icons = icons;
        this.letters = letters;
        Variables.error+= "\nvar initized!";

    }

    @Override
    public int getCount() {
        //This one line cost me so many hours fuckers. this was the extreme shit!
        return letters.length;
    }

    @Override
    public Object getItem(int position) {
        return letters[position];
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        View gridView = convertView;

        if(convertView == null){
            inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            gridView = inflater.inflate(R.layout.custo_layour,null);
        }
        ImageView icon =(ImageView) gridView.findViewById(R.id.imageView3);
        TextView letter =(TextView) gridView.findViewById(R.id.letters);

        icon.setImageResource(icons[position]);
        letter.setText(letters[position]);
        return gridView;
    }
}
