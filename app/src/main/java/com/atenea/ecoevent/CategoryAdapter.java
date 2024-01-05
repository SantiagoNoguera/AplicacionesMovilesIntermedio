package com.atenea.ecoevent;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.atenea.ecoevent.models.Category;

import java.text.DecimalFormat;
import java.util.List;

public class CategoryAdapter extends ArrayAdapter<Category> {

    private Context context;
    private List<Category> categories;

    public CategoryAdapter(@NonNull Context context, List<Category> categories) {
        super(context, 0, categories);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        Category category = getItem(position);

        if (convertView == null ) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.list_view, parent, false);
        }

        TextView nameTV = (TextView) convertView.findViewById(R.id.tv_list_category);
        TextView totalTV = (TextView) convertView.findViewById(R.id.tv_list_total);
        TextView mediaTV = (TextView) convertView.findViewById(R.id.tv_list_media);
        TextView metaTV = (TextView) convertView.findViewById(R.id.tv_list_meta);
        TextView missingTV = (TextView) convertView.findViewById(R.id.tv_list_missing);

        nameTV.setText(category.getName());
        totalTV.setText(String.valueOf(category.getTotal()));

        DecimalFormat format = new DecimalFormat("0.0");
        mediaTV.setText(String.valueOf(format.format(category.getMedia())));

        metaTV.setText(String.valueOf(category.getMeta()));
        missingTV.setText(String.valueOf(category.getMissing()));

        return convertView;
    }
}
