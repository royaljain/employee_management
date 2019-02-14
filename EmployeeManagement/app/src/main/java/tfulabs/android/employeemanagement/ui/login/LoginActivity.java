package tfulabs.android.employeemanagement.ui.login;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.amitshekhar.DebugDB;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.FirebaseApp;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import java.util.regex.Pattern;

import tfulabs.android.employeemanagement.R;
import tfulabs.android.employeemanagement.ui.userSelection.UserSelectionActivity;
import tfulabs.android.employeemanagement.utils.BaseActivity;

/**
 * The type Login activity.
 *
 * @author vishal kumar
 */
public class LoginActivity extends BaseActivity implements View.OnClickListener{

    private static final String TAG = LoginActivity.class.getSimpleName();

    private EditText editTextEmail, editTextPassword;
    private EditText editTextStoreId;

    /**
     * The Store id.
     */
    String storeId;
    private FirebaseAuth firebaseAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        initUi();
        initMember();
        storeId = editTextStoreId.getText().toString();
    }

    private void initUi() {
        // Views
        editTextEmail = findViewById(R.id.et_email);
        editTextPassword = findViewById(R.id.et_password);
        editTextStoreId = findViewById(R.id.et_store_id);
        // Button listeners
        findViewById(R.id.btn_login).setOnClickListener(this);
    }

    private void initMember() {
        // Initialize Firebase Auth
        FirebaseApp.initializeApp(this);
        firebaseAuth = FirebaseAuth.getInstance();

    }

    private boolean validateEmailPassword(String email, String password) {
        boolean isValid = true;
        if (TextUtils.isEmpty(email)){
            editTextEmail.setError("Enter e-mail");
            isValid = false;
        }else if (!isValidEmail(email)){
            editTextEmail.setError("Enter a valid email");
            isValid = false;
        }

        if (TextUtils.isEmpty(password)){
            editTextEmail.setError("Enter password");
            isValid = false;
        }

        if (password.length()<6){
            isValid = false;
            Toast.makeText(LoginActivity.this, "Enter Correct Password.", Toast.LENGTH_SHORT).show();
        }
        return isValid;
    }

    /**
     * Is valid email boolean.
     *
     * @param email the email
     * @return the boolean
     */
    public static boolean isValidEmail(String email) {
        String emailRegex = "^[a-zA-Z0-9_+&*-]+(?:\\."+
                "[a-zA-Z0-9_+&*-]+)*@" +
                "(?:[a-zA-Z0-9-]+\\.)+[a-z" +
                "A-Z]{2,7}$";

        Pattern pat = Pattern.compile(emailRegex);
        if (email == null)
            return false;
        return pat.matcher(email).matches();
    }

    @Override
    public void onClick(View view) {
        int i = view.getId();
        if (i == R.id.btn_login){
            hideKeyboard(view);
            String email = editTextEmail.getText().toString();
            String password = editTextPassword.getText().toString();
            if(validateEmailPassword(email, password)){
                showProgressDialog();
                mProgressDialog.setMessage(getString(R.string.progress_msg_auth));
                firebaseAuth.signInWithEmailAndPassword(email,password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()){
                            Log.i(TAG, "onComplete: "+ firebaseAuth.getUid() +" Success");
                            mProgressDialog.setMessage(getString(R.string.progress_msg_auth_success));
                            startActivity(new Intent(getApplicationContext(), UserSelectionActivity.class));

                        }else {
                            Log.e(TAG, "Sign-in Failed: "+task.getException());
                            mProgressDialog.setMessage(getString(R.string.progress_msg_auth_failed));
                            hideProgressDialog();
                        }
                    }
                });
            }
        }
    }

    @Override
    protected void onStart() {
        super.onStart();
        FirebaseUser currentUser = firebaseAuth.getCurrentUser();
        if (currentUser != null){
            startActivity(new Intent(getApplicationContext(), UserSelectionActivity.class));
        }
    }
}
