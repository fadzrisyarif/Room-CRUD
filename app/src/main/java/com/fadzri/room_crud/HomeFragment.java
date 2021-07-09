package com.fadzri.room_crud;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.navigation.NavArgs;
import androidx.navigation.Navigation;
import androidx.navigation.fragment.NavHostFragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.fadzri.room_crud.adapter.UserAdapter;
import com.fadzri.room_crud.database.AppDatabase;
import com.fadzri.room_crud.database.User;
import com.google.android.material.dialog.MaterialAlertDialogBuilder;

import java.util.List;

public class HomeFragment extends Fragment {

    private RecyclerView recyclerView;
    private AppDatabase appDatabase;
    private UserAdapter userAdapter;
    private AlertDialog.Builder dialog;
    private User user;
    private Bundle bundle;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view =  inflater.inflate(R.layout.fragment_home, container, false);

        recyclerView = view.findViewById(R.id.recyclerview);

        appDatabase = AppDatabase.getInstance(getContext().getApplicationContext());
        userAdapter = new UserAdapter(getContext().getApplicationContext(), appDatabase.userDao().getAll());

        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getContext().getApplicationContext(), RecyclerView.VERTICAL, false);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(userAdapter);

        userAdapter.setDialog(new UserAdapter.Dialog() {
            @Override
            public void onClick(int position) {
                final CharSequence[] dialogItem = {"Edit", "Hapus"};
                dialog = new AlertDialog.Builder(getActivity());
//                dialog.setItems(dialogItem, new DialogInterface.OnClickListener() {
//                    @Override
//                    public void onClick(DialogInterface dialog, int which) {
//                        switch(which){
//                            case 0:
//
//                                int uid = appDatabase.userDao().getAll().get(position).uid;
//
//                                HomeFragmentDirections.ActionHomeFragmentToEditFragment actionHomeFragmentToEditFragment = HomeFragmentDirections.actionHomeFragmentToEditFragment();
//                                actionHomeFragmentToEditFragment.setUid(uid);
//
//                                System.out.println(actionHomeFragmentToEditFragment.setUid(uid));
//
//                                Navigation.findNavController(view).navigate(R.id.action_homeFragment_to_editFragment);
//
//
//                                break;
//                            case 1:
//                                user = appDatabase.userDao().getAll().get(position);
//                                appDatabase.userDao().delete(user);
//
//                                Navigation.findNavController(view).navigate(R.id.action_homeFragment_to_blankFragment);
//
//                                break;
//                        }
//                    }
//                });
//                dialog.show();
//
            }
        });

        view.findViewById(R.id.floatingActionButton).setOnClickListener(v -> {
            Navigation.findNavController(view).navigate(R.id.action_homeFragment_to_insertFragment);
        });

        return view;
    }

}