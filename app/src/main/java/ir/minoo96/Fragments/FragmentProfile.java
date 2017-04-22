package ir.minoo96.Fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.LinearLayout;

import ir.minoo96.R;
import ir.minoo96.Utility.FontTextView;
import ir.minoo96.Utility.SharedPreferenceHelper;

public class FragmentProfile extends Fragment {

    private static final String TAG = FragmentProfile.class.getSimpleName();

    LinearLayout subLogin;
    LinearLayout subRegister;
    LinearLayout subProfile;
    EditText txtUsername;
    EditText txtPassword;
//    ActionProcessButton btnSignIn;
//    ActionProcessButton btnRegister;

    FontTextView linkRegister;
    FontTextView linkLogin;

    public FragmentProfile() {

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_profile, container, false);
    }

    @Override
    public void onViewCreated(final View view, @Nullable final Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        subLogin = (LinearLayout) view.findViewById(R.id.sub_login);
        subRegister = (LinearLayout) view.findViewById(R.id.sub_register);
        subProfile = (LinearLayout) view.findViewById(R.id.sub_profile);

        txtUsername = (EditText) subLogin.findViewById(R.id.txtUsernameLogin);
        txtPassword = (EditText) subLogin.findViewById(R.id.txtPasswordLogin);
//        btnSignIn = (ActionProcessButton) subLogin.findViewById(R.id.btnSignIn);
//        btnSignIn.setMode(ActionProcessButton.Mode.ENDLESS);
//
//        btnRegister = (ActionProcessButton) subRegister.findViewById(R.id.btnRegister);
//        btnRegister.setMode(ActionProcessButton.Mode.ENDLESS);

        linkRegister = (FontTextView) subLogin.findViewById(R.id.link_register);
        linkLogin = (FontTextView) subRegister.findViewById(R.id.link_login);

        subLogin.setVisibility(View.GONE);
        subRegister.setVisibility(View.GONE);
        subProfile.setVisibility(View.GONE);

        calcState();
    }

    private void calcState() {
        SharedPreferenceHelper pref = new SharedPreferenceHelper(getContext(), "user");
        String sessId = pref.getString("sessId", "");
        if (sessId.length() == 0) {
            subLogin.setVisibility(View.VISIBLE);
        } else {
            subProfile.setVisibility(View.VISIBLE);
            loadProfileData();
        }
    }

    @Override
    public void onActivityCreated(@Nullable final Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        linkLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                subLogin.setVisibility(View.VISIBLE);
                subRegister.setVisibility(View.GONE);
            }
        });

        linkRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                subRegister.setVisibility(View.VISIBLE);
                subLogin.setVisibility(View.GONE);
            }
        });

//        btnSignIn.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Login();
//            }
//        });
//        btnRegister.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Register();
//            }
//        });
    }

    private void Login() {
//        btnSignIn.setProgress(50);
//        Requests.reqLogin(getContext(), txtUsername.getText().toString(), txtPassword.getText().toString(), new RequestCallback() {
//            @Override
//            public void onSuccess() {
//                btnSignIn.setProgress(100);
//                subProfile.setVisibility(View.VISIBLE);
//                loadProfileData();
//            }
//
//            @Override
//            public void onFailed() {
//                btnSignIn.setProgress(-1);
//            }
//        });
    }

    private void Register() {

    }

    private void loadProfileData() {

    }
}
