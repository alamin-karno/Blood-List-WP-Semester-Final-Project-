package com.karno.wpfinalproject;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.Toast;

import java.util.Objects;


public class HomeFragment extends Fragment {

    EditText nameET,ageET,cityET;
    Spinner bloodGroupSP;
    Button insertBTN;
    RadioGroup radioGroup;
    RadioButton radioButton;

    DatabaseHelper databaseHelper;

    public HomeFragment() {
        // Required empty public constructor
    }



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_home, container, false);

        nameET = view.findViewById(R.id.nameET);
        ageET = view.findViewById(R.id.ageET);
        cityET = view.findViewById(R.id.cityET);
        bloodGroupSP = view.findViewById(R.id.bloodGroupSP);
        insertBTN = view.findViewById(R.id.insertBTN);
        radioGroup = view.findViewById(R.id.radioGroup);

        databaseHelper = new DatabaseHelper(getContext());


        insertBTN.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String name = nameET.getText().toString();
                String age = ageET.getText().toString();
                String city = cityET.getText().toString();
                String blood = bloodGroupSP.getSelectedItem().toString();

                int radioID = radioGroup.getCheckedRadioButtonId();
                radioButton = Objects.requireNonNull(getActivity()).findViewById(radioID);
                String sex = radioButton.getText().toString();


                if(name.isEmpty()){
                    Toast.makeText(getContext(), "Enter your name first", Toast.LENGTH_SHORT).show();
                }
                else if(age.isEmpty()){
                    Toast.makeText(getContext(), "Enter your age first", Toast.LENGTH_SHORT).show();
                }
                else if(city.isEmpty()){
                    Toast.makeText(getContext(), "Enter your city first", Toast.LENGTH_SHORT).show();
                }
                else if(sex.isEmpty()){
                    Toast.makeText(getContext(), "Select Sex first", Toast.LENGTH_SHORT).show();
                }
                else if(blood.equals("Select Blood Group")){

                    Toast.makeText(getContext(), "Select Blood Group", Toast.LENGTH_SHORT).show();
                }
                else {

                    Long id = databaseHelper.insertDate(name,age,sex,city,blood);

                    Toast.makeText(getContext(), "Data inserted & ID is : "+id, Toast.LENGTH_SHORT).show();

                    nameET.setText("");
                    ageET.setText("");
                    cityET.setText("");
                    bloodGroupSP.setSelection(0);
                }

            }
        });

        return view;
    }
}