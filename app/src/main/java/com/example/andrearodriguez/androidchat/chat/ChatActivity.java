package com.example.andrearodriguez.androidchat.chat;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.widget.EditText;
import android.widget.TextView;

import com.example.andrearodriguez.androidchat.R;
import com.example.andrearodriguez.androidchat.chat.ui.ChatView;
import com.example.andrearodriguez.androidchat.chat.ui.adapters.ChatAdapter;
import com.example.andrearodriguez.androidchat.domain.AvatarDomindHelper;
import com.example.andrearodriguez.androidchat.entities.ChatMessagge;
import com.example.andrearodriguez.androidchat.lib.GladeImageLoader;
import com.example.andrearodriguez.androidchat.lib.ImageLoader;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import de.hdodenhof.circleimageview.CircleImageView;

public class ChatActivity extends AppCompatActivity implements ChatView {

    @BindView(R.id.imgAvatar)
    CircleImageView imgAvatar;
    @BindView(R.id.txtUser)
    TextView txtUser;
    @BindView(R.id.txtStatus)
    TextView txtStatus;
    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.messaageRecycleView)
    RecyclerView messaageRecycleView;
    @BindView(R.id.editTxtMenssagge)
    EditText editTxtMenssagge;


    private ChatAdapter adapter;
    private ChatPresenter presenter;

    public final static String EMAIL_KEY = "email";
    public final static String ONLINE_KEY = "online";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chat);
        ButterKnife.bind(this);

        setupAdapter();
        setupRecyclerView();
        presenter = new ChatPresenterImpl(this);
        presenter.onCreate();
        setuUpToolBar(getIntent());
    }

    private void setupAdapter() {
        adapter = new ChatAdapter(getApplicationContext(), new ArrayList<ChatMessagge>());
    }

    private void setupRecyclerView() {
        messaageRecycleView.setLayoutManager(new LinearLayoutManager(this));
        messaageRecycleView.setAdapter(adapter);
    }

    private void setuUpToolBar(Intent i) {
        String recipients= i.getStringExtra(EMAIL_KEY);
        presenter.setChatRecipies((recipients));

        boolean online =i.getBooleanExtra(ONLINE_KEY, false);
        String status = online ? "online" : "offline";
        int color = online ? Color.rgb(96, 169, 23) : Color.RED;

        txtUser.setText(recipients);
       txtStatus.setText(status);
       txtStatus.setTextColor(color);

        ImageLoader imageLoader = new GladeImageLoader(getApplicationContext());
        imageLoader.load(imgAvatar, AvatarDomindHelper.getAvatarUrl(recipients));

        setSupportActionBar(toolbar);
    }

    @Override
    protected void onResume() {
        presenter.onResume();
        super.onResume();
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
    public void onMessaggeReceived(ChatMessagge msg) {
        adapter.add(msg);
        messaageRecycleView.scrollToPosition(adapter.getItemCount() - 1);
    }
    @OnClick(R.id.btnSendMessagge)
    public void sendMessagge(){
        presenter.sendMessagge(editTxtMenssagge.getText().toString());
        editTxtMenssagge.setText("");
    }
}
