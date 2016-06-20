package com.example.andrearodriguez.androidchat.chat.ui.adapters;

import android.content.Context;
import android.content.res.TypedArray;
import android.support.v7.widget.RecyclerView;
import android.util.TypedValue;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.andrearodriguez.androidchat.R;
import com.example.andrearodriguez.androidchat.entities.ChatMessagge;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by andrearodriguez on 6/19/16.
 */
public class ChatAdapter extends RecyclerView.Adapter<ChatAdapter.ViewHolder> {

    Context context;
    private List<ChatMessagge> chatMessagges;

    public ChatAdapter(Context context, List<ChatMessagge> chatMessagges) {
        this.context = context;
        this.chatMessagges = chatMessagges;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.content_chat, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        ChatMessagge chatMessagge = chatMessagges.get(position);
        String msg = chatMessagge.getMsg();
        holder.txtMessagge.setText(msg);

        int color = fetchColor(R.attr.colorPrimary);
        int gravity = Gravity.LEFT;

        if (!chatMessagge.isSentByMe()) {
            color = fetchColor(R.attr.colorAccent);
            gravity = Gravity.RIGHT;
        }
        holder.txtMessagge.setBackgroundColor(color);
        LinearLayout.LayoutParams params = (LinearLayout.LayoutParams) holder.txtMessagge.getLayoutParams();
        params.gravity = gravity;
        holder.txtMessagge.setLayoutParams(params);
    }
    private int fetchColor(int color) {
        TypedValue typedValue = new TypedValue();
        TypedArray a = context.obtainStyledAttributes(typedValue.data, new int[]{color});
        int returnColor = a.getColor(0, 0);
        a.recycle();
        return returnColor;
    }


    @Override
    public int getItemCount() {

        return chatMessagges.size();
    }

    public void add(ChatMessagge msg) {
        if (!chatMessagges.contains(msg)) {
            chatMessagges.add(msg);
            notifyDataSetChanged();
        }
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.txtMessagge)
        TextView txtMessagge;

        public ViewHolder(View itemView) {

            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}
