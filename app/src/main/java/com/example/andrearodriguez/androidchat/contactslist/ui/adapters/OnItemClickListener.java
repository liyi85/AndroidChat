package com.example.andrearodriguez.androidchat.contactslist.ui.adapters;

import com.example.andrearodriguez.androidchat.entities.User;

/**
 * Created by andrearodriguez on 6/16/16.
 */
public interface OnItemClickListener {
    void onItemClick(User user);
    void onItemLongClick(User user);
}
