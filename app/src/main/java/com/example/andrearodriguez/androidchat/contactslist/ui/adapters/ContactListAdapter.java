package com.example.andrearodriguez.androidchat.contactslist.ui.adapters;

import android.graphics.Color;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.andrearodriguez.androidchat.R;
import com.example.andrearodriguez.androidchat.entities.User;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import de.hdodenhof.circleimageview.CircleImageView;

/**
 * Created by andrearodriguez on 6/16/16.
 */
public class ContactListAdapter extends RecyclerView.Adapter<ContactListAdapter.ViewHolder> {

    private List<User> contactList;
    private ImageLoading imageLoader;
    private OnItemClickListener onItemClickListener;

    public ContactListAdapter(List<User> contactList, ImageLoading imageLoading, OnItemClickListener onItemClickListener) {
        this.contactList = contactList;
        this.imageLoader = imageLoading;
        this.onItemClickListener = onItemClickListener;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.content_contacts, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        User user = contactList.get(position);
        holder.setClickListener(user, onItemClickListener);

        boolean online = user.isOnline();
        String status = online ? "online" : "offline";
        int color = online ? Color.GREEN : Color.RED;

        holder.txtUser.setText(user.getEmail());
        holder.txtStatus.setText(user.getEmail());
        holder.txtStatus.setTextColor(color);
        imageLoader.load(holder.imgAvatar, "");
    }

    @Override
    public int getItemCount() {
        return contactList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.imgAvatar)
        CircleImageView imgAvatar;
        @BindView(R.id.txtUser)
        TextView txtUser;
        @BindView(R.id.txtStatus)
        TextView txtStatus;
        private View view;

        public ViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
            this.view = view;
        }

        private void setClickListener(final User user, final OnItemClickListener listener){
            view.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    listener.onItemClick(user);

                }
            });
            view.setOnLongClickListener(new View.OnLongClickListener() {
                @Override
                public boolean onLongClick(View v) {
                    listener.onItemLongClick(user);
                    return false;
                }
            });
        }
    }
}
