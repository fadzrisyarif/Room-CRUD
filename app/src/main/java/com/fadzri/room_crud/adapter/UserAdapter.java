package com.fadzri.room_crud.adapter;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.ListFragment;
import androidx.navigation.NavDirections;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.RecyclerView;

import com.fadzri.room_crud.EditFragment;
import com.fadzri.room_crud.HomeFragmentDirections;
import com.fadzri.room_crud.MainActivity;
import com.fadzri.room_crud.R;
import com.fadzri.room_crud.database.AppDatabase;
import com.fadzri.room_crud.database.User;
import com.google.android.material.navigation.NavigationView;

import java.text.BreakIterator;
import java.util.List;

public class UserAdapter extends RecyclerView.Adapter<UserAdapter.viewAdapter>{

    private List<User> list;
    private Context context;
    private Dialog dialog;

    public interface Dialog{
        void onClick(int position);
    }

    public void setDialog(Dialog dialog){
        this.dialog = dialog;
    }


    public UserAdapter(Context context, List<User> list){
        this.context = context;
        this.list = list;

    }

    @NonNull
    @Override
    public viewAdapter onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.row_user, parent, false);
        return new viewAdapter(view);
    }

    @Override
    public void onBindViewHolder(@NonNull UserAdapter.viewAdapter holder, int position) {
        holder.firstname.setText(list.get(position).firstName);
        holder.lastname.setText(list.get(position).lastName);
        holder.email.setText(list.get(position).email);

        holder.itemView.findViewById(R.id.rowLayout).setOnClickListener(v -> {
            HomeFragmentDirections.ActionHomeFragmentToEditFragment actionHomeFragmentToEditFragment = HomeFragmentDirections.actionHomeFragmentToEditFragment();
            actionHomeFragmentToEditFragment.setUid(list.get(position).uid);
            Navigation.findNavController(v).navigate(actionHomeFragmentToEditFragment);
        });

    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    class viewAdapter extends RecyclerView.ViewHolder{
        TextView firstname, lastname, email, uid, fname, lname, eml;
        LinearLayout rowLayout;

        AppDatabase appDatabase;

        public viewAdapter(@NonNull View itemView) {
            super(itemView);
            firstname = itemView.findViewById(R.id.firstName);
            lastname = itemView.findViewById(R.id.lastName);
            email = itemView.findViewById(R.id.email);

            rowLayout = itemView.findViewById(R.id.rowLayout);


            appDatabase = AppDatabase.getInstance(context.getApplicationContext());

            itemView.setOnClickListener(v -> {
                if(dialog!=null){
                    dialog.onClick(getLayoutPosition());
                }
            });

//            itemView.setOnClickListener(v -> {
//
//                User user = appDatabase.userDao().findByName(firstname.getText().toString(), lastname.getText().toString());
//                Navigation.findNavController(v).navigate(R.id.action_homeFragment_to_editFragment);
//
//                try{
//                    Activity ac = FragmentManager.findFragment(v).getActivity();
//                    (MainActivity(getActivity())).
//                }catch (Exception e){
//                    e.printStackTrace();
//                }

                //TextView fname = v.findViewById(R.id.fname);

                //System.out.println(fname.getText());
//                fname.setText(user.firstName);
//                lname.setText(user.lastName);
//                eml.setText(user.email);
//            });

        }
    }
}
