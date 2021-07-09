package com.fadzri.room_crud;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.fadzri.room_crud.database.AppDatabase;
import com.fadzri.room_crud.database.User;

public class InsertFragment extends Fragment {

    private EditText firstNameField, lastNameField, emailField;
    private Button insertBtn;
    private AppDatabase appDatabase;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view =  inflater.inflate(R.layout.fragment_insert, container, false);

        firstNameField = view.findViewById(R.id.firstNameField);
        lastNameField = view.findViewById(R.id.lastNameField);
        emailField = view.findViewById(R.id.emailField);
        insertBtn = view.findViewById(R.id.insertBtn);

        appDatabase = AppDatabase.getInstance(getContext().getApplicationContext());

        insertBtn.setOnClickListener(v -> {
            User user = new User();
            user.firstName = firstNameField.getText().toString();
            user.lastName = lastNameField.getText().toString();
            user.email = emailField.getText().toString();
            if(user.firstName.isEmpty()==false && user.lastName.isEmpty()==false && user.email.isEmpty()==false) {
                appDatabase.userDao().insertAll(user);
                Navigation.findNavController(v).navigate(R.id.action_insertFragment_to_homeFragment);
                Toast.makeText(requireContext(), "Successfully added!", Toast.LENGTH_LONG).show();
            }else{
                Toast.makeText(requireContext(), "Please fill out all fields!", Toast.LENGTH_SHORT).show();
            }
        });


        return view;
    }
}