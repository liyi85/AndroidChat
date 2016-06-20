package com.example.andrearodriguez.androidchat.addcontact.ui;

import com.example.andrearodriguez.androidchat.addcontact.AddContactlistRepository;
import com.example.andrearodriguez.androidchat.addcontact.events.AddContactEvent;
import com.example.andrearodriguez.androidchat.domain.FirebaseHelper;
import com.example.andrearodriguez.androidchat.entities.User;
import com.example.andrearodriguez.androidchat.lib.EvenBus;
import com.example.andrearodriguez.androidchat.lib.GreenRobotEventBus;
import com.firebase.client.DataSnapshot;
import com.firebase.client.Firebase;
import com.firebase.client.FirebaseError;
import com.firebase.client.ValueEventListener;

/**
 * Created by andrearodriguez on 6/19/16.
 */
public class AddContactRepositoryImpl implements AddContactlistRepository {

    private EvenBus evenBus;
    private FirebaseHelper helper;

    public AddContactRepositoryImpl() {
        this.helper = FirebaseHelper.getInstance();
        this.evenBus = GreenRobotEventBus.getInstance();
    }

    @Override
    public void addContact(final String email) {
        final String key = email.replace(".","_");
        Firebase uerReference = helper.getUserReference(email);
        uerReference.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                User user = dataSnapshot.getValue(User.class);
                if(user!=null){
                    Firebase myContactReference = helper.getMyContactsReference();
                    myContactReference.child(key).setValue(user.isOnline());

                    String currentUserKey = helper.getAuthUserEmail();
                    currentUserKey = currentUserKey.replace(".","_");

                    Firebase reverseContactReference = helper.getContactsReference(email);
                    reverseContactReference.child(currentUserKey).setValue(User.ONLINE);

                    postSuccess();
                }else{
                    postErro();
                }
            }

            @Override
            public void onCancelled(FirebaseError firebaseError) {
                postErro();
            }
        });
    }

    private void postSuccess(){
        post(false);
    }
    private void postErro(){
        post(true);
    }

    private void post(boolean error) {
        AddContactEvent event = new AddContactEvent();
        event.setError(error);
        evenBus.post(event);
    }
}
