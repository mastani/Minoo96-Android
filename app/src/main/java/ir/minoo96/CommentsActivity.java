package ir.minoo96;

import android.content.DialogInterface;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.InputType;
import android.view.Gravity;
import android.view.View;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

import ir.minoo96.API.Callbacks.CommentsCallback;
import ir.minoo96.API.Callbacks.RequestCallback;
import ir.minoo96.API.Requests;
import ir.minoo96.Adapters.CommentListAdapter;
import ir.minoo96.Items.Comment;
import ir.minoo96.Utility.FontButton;
import ir.minoo96.Utility.Variables;

public class CommentsActivity extends AppCompatActivity {

    ArrayList<Comment> adapterComments;
    ListView listView;
    CommentListAdapter listAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_comments);

        adapterComments = new ArrayList<>();

        listView = (ListView) findViewById(R.id.listView);

        final int i = getIntent().getExtras().getInt("item");

        Requests.fetchComment(getBaseContext(), i, new CommentsCallback() {
            @Override
            public void onSuccess(ArrayList<Comment> comments) {
                listAdapter = new CommentListAdapter(CommentsActivity.this, comments);
                listView.setAdapter(listAdapter);
            }

            @Override
            public void onFailed() {

            }
        });

        final EditText editTextComment = (EditText) findViewById(R.id.editTextComment);

        FontButton btnSend = (FontButton) findViewById(R.id.btnSend);
        btnSend.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (editTextComment.length() < 5) {
                    Toast.makeText(getBaseContext(), "متن نظر شما باید بیش از 5 کاراکتر باشد", Toast.LENGTH_LONG).show();
                    return;
                }

                if (Variables.userName.length() == 0) {
                    AlertDialog.Builder builder = new AlertDialog.Builder(CommentsActivity.this);
                    builder.setTitle("لطفا ابتدا نام خود را وارد کنید:");

                    final EditText input = new EditText(CommentsActivity.this);
                    input.setInputType(InputType.TYPE_CLASS_TEXT);
                    input.setPadding(30, 10, 30, 10);
                    input.setGravity(Gravity.RIGHT);
                    builder.setView(input);

                    builder.setPositiveButton("تایید", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            Variables.userName = input.getText().toString();
                            Requests.fetchUpdateUserName(getBaseContext(), Variables.userId, Variables.userName, new RequestCallback() {
                                @Override
                                public void onSuccess() {

                                }

                                @Override
                                public void onFailed() {

                                }
                            });
                        }
                    });
                    builder.setNegativeButton("لغو", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            dialog.cancel();
                        }
                    });

                    builder.show();

                    return;
                }

                Requests.fetchSendComment(getBaseContext(), i, Variables.userId, editTextComment.getText().toString(), new RequestCallback() {
                    @Override
                    public void onSuccess() {
                        editTextComment.setText("");
                        Toast.makeText(getBaseContext(), "نظر شما ثبت شد و بعد از تایید نمایش داده می شود.", Toast.LENGTH_LONG).show();
                    }

                    @Override
                    public void onFailed() {

                    }
                });
            }
        });
    }
}
