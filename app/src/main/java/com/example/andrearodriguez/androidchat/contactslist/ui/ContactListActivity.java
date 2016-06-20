package com.example.andrearodriguez.androidchat.contactslist.ui;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;

import com.example.andrearodriguez.androidchat.R;
import com.example.andrearodriguez.androidchat.addcontact.ui.AddContactBlankFragment;
import com.example.andrearodriguez.androidchat.chat.ChatActivity;
import com.example.andrearodriguez.androidchat.contactslist.ContactListPresenter;
import com.example.andrearodriguez.androidchat.contactslist.ContactListPresenterImp;
import com.example.andrearodriguez.androidchat.contactslist.ui.adapters.ContactListAdapter;
import com.example.andrearodriguez.androidchat.contactslist.ui.adapters.OnItemClickListener;
import com.example.andrearodriguez.androidchat.entities.User;
import com.example.andrearodriguez.androidchat.lib.GladeImageLoader;
import com.example.andrearodriguez.androidchat.lib.ImageLoader;
import com.example.andrearodriguez.androidchat.login.ui.LoginActivity;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class ContactListActivity extends AppCompatActivity implements ContactListView, OnItemClickListener{
    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.recyclerViewContacts)
    RecyclerView recyclerViewContacts;

    private ContactListAdapter adapter;
    private ContactListPresenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contact_list);
        ButterKnife.bind(this);

        setupAdapter();
        setupRecyclerView();

        presenter = new ContactListPresenterImp(this);
        presenter.onCreate();
        setupToolbar();

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if(item.getItemId() == R.id.action_logut){
            presenter.signOff();
            Intent intent = new Intent(this, LoginActivity.class);
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP
                    |intent.FLAG_ACTIVITY_NEW_TASK
                    |intent.FLAG_ACTIVITY_CLEAR_TASK);
            startActivity(intent);
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_contactlist, menu);
        return super.onCreateOptionsMenu(menu);
    }

    private void setupRecyclerView() {
        recyclerViewContacts.setLayoutManager(new LinearLayoutManager(this));
        recyclerViewContacts.setAdapter(adapter);
    }

    private void setupAdapter() {
        ImageLoader loader = new GladeImageLoader(this.getApplicationContext());
        adapter = new ContactListAdapter(new ArrayList<User>(), loader, this);
    }

    private void setupToolbar() {
        toolbar.setTitle(presenter.getCurrentUserEmail());
        setSupportActionBar(toolbar);
    }

    @OnClick(R.id.fab)
    public void addContact(){
        new AddContactBlankFragment().show(getSupportFragmentManager(), getString(R.string.addcontact_messagge_title));
    }

    @Override
    protected void onResume() {
        super.onResume();
        presenter.onResume();
    }

    @Override
    protected void onPause() {
        presenter.onPause();
        super.onPause();
    }

    @Override
    protected void onDestroy() {
        presenter.onDestroy();
        super.onDestroy();
    }

    @Override
    public void onContactAdd(User user) {
        adapter.add(user);
    }

    @Override
    public void onContactChange(User user) {
        adapter.change(user);
    }

    @Override
    public void onContactRemoved(User user) {
        adapter.remove(user);
    }

    @Override
    public void onItemClick(User user) {
      Intent intent = new Intent(this, ChatActivity.class);
        intent.putExtra(ChatActivity.EMAIL_KEY, user.getEmail());
        intent.putExtra(ChatActivity.ONLINE_KEY, user.isOnline());
        startActivity(intent);
    }

    @Override
    public void onItemLongClick(User user) {
      presenter.removeContact(user.getEmail());
    }
}
