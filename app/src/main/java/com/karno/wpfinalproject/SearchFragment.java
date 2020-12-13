package com.karno.wpfinalproject;

import android.database.Cursor;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;


public class SearchFragment extends Fragment {

    TextView searchIDET,nameET,ageET,cityET,bloodET,sexTV;
    Button searchBTN;

    DatabaseHelper databaseHelper;

    public SearchFragment() {
        // Required empty public constructor
    }



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_search, container, false);

        searchBTN = view.findViewById(R.id.searchBTN);
        searchIDET = view.findViewById(R.id.search_idET);
        nameET = view.findViewById(R.id.nameET);
        ageET = view.findViewById(R.id.ageET);
        cityET = view.findViewById(R.id.cityET);
        bloodET = view.findViewById(R.id.bloodGroupET);
        sexTV = view.findViewById(R.id.sexTv);

        databaseHelper = new DatabaseHelper(getContext());


        searchBTN.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

              String ID = searchIDET.getText().toString();

              if(ID.isEmpty()){
                  Toast.makeText(getContext(), "Please enter ID for search", Toast.LENGTH_SHORT).show();
              }
              else {

                 Cursor cursor = databaseHelper.searchData(Integer.parseInt(ID));

                 if(cursor.getCount() <= 0){
                     Toast.makeText(getContext(), "No data in this ID", Toast.LENGTH_SHORT).show();

                     nameET.setText("No Data Added");
                     cityET.setText("No Data Added");
                     ageET.setText("No Data Added");
                     bloodET.setText("No Data Added");
                     sexTV.setText("No Data Added");
                 }
                 else {
                     while (cursor.moveToNext()){

                         String name = cursor.getString(cursor.getColumnIndex(databaseHelper.COL_NAME));
                         String city = cursor.getString(cursor.getColumnIndex(databaseHelper.COL_CITY));
                         String age = cursor.getString(cursor.getColumnIndex(databaseHelper.COL_AGE));
                         String sex = cursor.getString(cursor.getColumnIndex(databaseHelper.COL_SEX));
                         String blood = cursor.getString(cursor.getColumnIndex(databaseHelper.COL_BLOOD));

                         nameET.setText("Name: "+name);
                         cityET.setText("City: "+city);
                         sexTV.setText("Sex: "+sex);
                         ageET.setText("Age: "+age);
                         bloodET.setText("Blood: "+blood);

                     }
                 }


              }

            }
        });


        return view;
    }
}