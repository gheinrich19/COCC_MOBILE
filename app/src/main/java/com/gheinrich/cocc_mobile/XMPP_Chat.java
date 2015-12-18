package com.gheinrich.cocc_mobile;

        import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import org.jivesoftware.smack.AbstractXMPPConnection;
import org.jivesoftware.smack.Chat;
import org.jivesoftware.smack.ChatManager;
import org.jivesoftware.smack.ChatManagerListener;
import org.jivesoftware.smack.ChatMessageListener;
import org.jivesoftware.smack.ConnectionConfiguration;
import org.jivesoftware.smack.SmackException;
import org.jivesoftware.smack.XMPPException;
import org.jivesoftware.smack.packet.Message;
import org.jivesoftware.smack.tcp.XMPPTCPConnection;
import org.jivesoftware.smack.tcp.XMPPTCPConnectionConfiguration;

import java.io.IOException;

public class XMPP_Chat extends AppCompatActivity implements View.OnClickListener {
    public  static String ChatServer  = "h4cks3rv3r";
    public static EditText mComposeET;
    public static EditText mchatWithET;
    public static boolean isClicked = false;
    public static Button sendButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.xmpp_chat);

        sendButton = (Button) findViewById(R.id.sendBtn);
        sendButton.setOnClickListener(this);
        mComposeET = (EditText)findViewById(R.id.chatET);
        mchatWithET = (EditText)findViewById(R.id.toET);
        mComposeET.setText("");


        clearText();

    }


    @Override

    public void onClick(View v) {
        new Thread() {
            public void run() {
                try {


                    XMPPTCPConnectionConfiguration.Builder builder = XMPPTCPConnectionConfiguration.builder();
                    builder.setSecurityMode(ConnectionConfiguration.SecurityMode.disabled);
                    builder.setUsernameAndPassword("test_2" + "@" + ChatServer, "football19");
                    builder.setServiceName(ChatServer);
                    builder.setHost("192.168.0.34");
                    builder.setPort(5222);
                    builder.setDebuggerEnabled(true);



                    //this is also error
                    //SASLPlainMechanism saslPlainMechanism = new SASLPlainMechanism();
                    // SASLAuthentication.registerSASLMechanism(saslPlainMechanism);
                    AbstractXMPPConnection connection = new XMPPTCPConnection(builder.build());
                    //saslPlainMechanism.instanceForAuthentication(connection);

                    connection.connect();
                    connection.login();

                    ChatManager chatmanager = ChatManager.getInstanceFor(connection);
                    //Create chat

                    Chat newChat = chatmanager.createChat( mchatWithET.getText().toString() + "@" + ChatServer, new ChatMessageListener() {
                        @Override
                        public void processMessage(Chat chat, Message message) {
                            try {
                                chat.sendMessage(message);
                            } catch (SmackException.NotConnectedException e) {
                                Log.d("CHAT_ERROR", e.toString());
                            }
                        }
                    });


                    newChat.sendMessage(mComposeET.getText().toString());


                    chatmanager.addChatListener(new ChatManagerListener() {
                        @Override
                        public void chatCreated(Chat chat, boolean createdLocally) {
                            if (!createdLocally) {
                                chat.addMessageListener(new ChatMessageListener() {
                                    @Override
                                    public void processMessage(Chat chat, Message message) {

                                    }
                                });
                            }
                        }
                    });
                } catch (SmackException | IOException | XMPPException e) {
                    Log.d("CHAT_ERROR", e.toString());
                }
            }


        }.start();



    }


    public void clearText (){

        if (isClicked == true)
        {
            mComposeET.getText().clear();
        }

    }



}
