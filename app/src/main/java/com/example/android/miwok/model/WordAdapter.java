package com.example.android.miwok.model;

import android.app.Activity;
import android.support.v4.content.ContextCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.android.miwok.R;

import java.util.ArrayList;

/**
 * Created by Casa on 29/09/2016.
 */
public class WordAdapter extends ArrayAdapter<Word> {
    private int mColor;
    private final static int NO_COLOR = -1;

    public WordAdapter(Activity context, ArrayList<Word> words) {
        super(context, 0, words);

        this.mColor = NO_COLOR;
    }

    public WordAdapter(Activity context, ArrayList<Word> words, int color) {
        super(context, 0, words);
        this.mColor = color;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View listItemView = convertView;

        if (listItemView == null) {
            listItemView = LayoutInflater.from(getContext()).inflate(R.layout.list_item, parent, false);
        }

        Word word = getItem(position);
        ImageView iv = (ImageView) listItemView.findViewById(R.id.image);
        TextView default_trn = (TextView)listItemView.findViewById(R.id.default_translation);
        TextView miwok_trn = (TextView)listItemView.findViewById(R.id.miwok_translation);
        ImageView implay  = (ImageView) listItemView.findViewById(R.id.image_ply);
        LinearLayout ly = (LinearLayout)listItemView.findViewById(R.id.list_item_data);

        if (word.hasImage()) {
            iv.setImageResource(word.getDrawable());
            iv.setVisibility(View.VISIBLE);
        } else {
            iv.setVisibility(View.GONE);
        }
        default_trn.setText(word.getDefaultTranslation());
        miwok_trn.setText(word.getMiwokTranslation());

        if (hasColor()) {
            int color = ContextCompat.getColor(getContext(), mColor);
            ly.setBackgroundColor(color);
            implay.setBackgroundColor(color);
        }


        return listItemView;
    }

    private boolean hasColor() {
        return this.mColor != NO_COLOR;
    }
}
