package com.fadzri.room_crud;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavArgs;
import androidx.navigation.Navigation;
import androidx.room.Update;

import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Adapter;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.fadzri.room_crud.adapter.UserAdapter;
import com.fadzri.room_crud.database.AppDatabase;
import com.fadzri.room_crud.database.User;
import com.google.android.material.textfield.TextInputEditText;

public class EditFragment extends Fragment {

    AppDatabase appDatabase;
    User user;
    TextInputEditText editFnameInput, editLnameInput, editEmailInput;
    Button updateBtn;
    int uid;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        View view = inflater.inflate(R.layout.fragment_edit, container, false);

        uid = EditFragmentArgs.fromBundle(getArguments()).getUid();

        appDatabase = AppDatabase.getInstance(getContext().getApplicationContext());
        user = appDatabase.userDao().getUser(uid);

        editFnameInput = view.findViewById(R.id.editFnameInput);
        editLnameInput = view.findViewById(R.id.editLnameInput);
        editEmailInput = view.findViewById(R.id.editEmailInput);
        updateBtn = view.findViewById(R.id.updateBtn);

        editFnameInput.setText(user.firstName);
        editLnameInput.setText(user.lastName);
        editEmailInput.setText(user.email);

        setHasOptionsMenu(true);

        updateBtn.setOnClickListener(v -> {
             user = new User();
             user.uid = uid;
             user.firstName = editFnameInput.getText().toString();
             user.lastName = editLnameInput.getText().toString();
             user.email = editEmailInput.getText().toString();

             if(user.firstName.isEmpty()==false&&user.lastName.isEmpty()==false&&user.email.isEmpty()==false) {
                 appDatabase.userDao().updateData(user);
                 Navigation.findNavController(v).navigate(R.id.action_editFragment_to_homeFragment);
                 Toast.makeText(requireContext(), "Update Sucessfully!", Toast.LENGTH_SHORT).show();
             }else{
                 Toast.makeText(requireContext(), "Please fill out all fields!", Toast.LENGTH_SHORT).show();
             }
        });

        return view;
    }

    @Override
    public void onCreateOptionsMenu(@NonNull Menu menu, @NonNull MenuInflater inflater) {
        inflater.inflate(R.menu.delete_menu, menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if(item.getItemId()==R.id.menu_delete){
            deleteUser(user);
            Navigation.findNavController(getView()).navigate(R.id.action_editFragment_to_homeFragment);
        }
        return super.onOptionsItemSelected(item);
    }

    public void deleteUser(User user){
        appDatabase.userDao().delete(user);
    }

}