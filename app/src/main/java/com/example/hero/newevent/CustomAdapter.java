package com.example.hero.newevent;
import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.hero.newevent.R;
import com.example.hero.newevent.Song;

import java.util.ArrayList;


// Custom Adapter to display an Array of objects. I wanted to display title and Description and maybe rating

public class CustomAdapter extends ArrayAdapter<Song> {

    private IdButtonClickedListener idButtonClickedListener;

    public void setIdButtonClickedListener(IdButtonClickedListener idButtonClickedListener) {
        this.idButtonClickedListener = idButtonClickedListener;
    }

    private Context con;
    private ArrayList<Song> songs;
    private TextView  textViewHead, textViewBody;
    private Button button;

    CustomAdapter(Context context, ArrayList<Song> songs) {
        super(context, R.layout.card_view, songs);
        con = context;
        this.songs = songs;

    }

    @NonNull
    @Override
    //I tried a 2 copies of the inflator , still the adapter would only update  if  you opened the keyboard
    public View getView(final int position, @Nullable View convertView, @NonNull final ViewGroup parent) {
        LayoutInflater inflater = LayoutInflater.from(getContext());
        //LayoutInflater inflater = (LayoutInflater)con.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        if (convertView == null) {

            convertView = inflater.inflate(R.layout.card_view, parent, false);
        }
        textViewHead = (TextView)convertView.findViewById(R.id.textViewHead);
        textViewBody = (TextView) convertView.findViewById(R.id.textViewBody);
        textViewHead.setText(songs.get(position).getName());
        textViewBody.setText(songs.get(position).getId().toString());
        button = (Button) convertView.findViewById(R.id.imageButton);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d("position",songs.get(position).getId()+"");

                if(idButtonClickedListener!=null)
                    idButtonClickedListener.onIdButtonClicked(songs.get(position).getId());


            }
        });
        return convertView;
    }


}

