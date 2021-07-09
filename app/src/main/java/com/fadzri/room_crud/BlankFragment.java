package com.fadzri.room_crud;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;

import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;


public class BlankFragment extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view =  inflater.inflate(R.layout.fragment_blank, container, false);


        long delay = 50L;


        Button button = view.findViewById(R.id.blankbtn);
        button.setOnClickListener(v -> Navigation.findNavController(view).navigate(R.id.action_blankFragment_to_homeFragment));


        Handler m_handler;
        Runnable m_handlerTask = button::performClick;
        m_handler = new Handler();
        Runnable finalM_handlerTask = m_handlerTask;
        m_handlerTask = () -> m_handler.postDelayed(finalM_handlerTask, delay);
        m_handlerTask.run();

        return view;
    }



}