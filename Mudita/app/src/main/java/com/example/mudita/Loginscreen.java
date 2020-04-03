package com.example.mudita;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.nfc.Tag;
import android.os.Bundle;
import android.os.Looper;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInApi;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.SignInButton;
import com.google.android.gms.common.api.ApiException;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.tabs.TabLayout;
import com.google.firebase.auth.AuthCredential;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.GoogleAuthProvider;

public class Loginscreen extends AppCompatActivity {
    private GoogleSignInClient mGoogleSignInClient;
    private SignInButton signInButton;
    private FirebaseAuth auth;
    private Button SignoutButton;
    private int RC_SIGN_IN=9001;
    private String Tag="activity_loginscreen";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_loginscreen);
        signInButton=findViewById(R.id.googlesignin);
        auth= FirebaseAuth.getInstance();
        SignoutButton=findViewById(R.id.loginbutton);
        GoogleSignInOptions gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN).requestIdToken(getString(R.string.default_web_client_id)).requestEmail().build();
        mGoogleSignInClient = GoogleSignIn.getClient(this, gso);


        signInButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onsignin();
            }
        });
        SignoutButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mGoogleSignInClient.signOut();
                Toast.makeText(Loginscreen.this,"Your Are Signed Out",Toast.LENGTH_SHORT).show();
                SignoutButton.setVisibility(View.INVISIBLE);
            }
        });



    }

    private void onsignin() {
        Intent signInIntent = mGoogleSignInClient.getSignInIntent();
        startActivityForResult(signInIntent, RC_SIGN_IN);
    }
    /*@Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable  Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        // Result returned from launching the Intent from GoogleSignInClient.getSignInIntent(...);
        if (requestCode == RC_SIGN_IN) {
            // The Task returned from this call is always completed, no need to attach
            // a listener.
            Task<GoogleSignInAccount> task = GoogleSignIn.getSignedInAccountFromIntent(data);
            handleSignInResult(task);
        }
    }*/

   // @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        // Result returned from launching the Intent from GoogleSignInApi.getSignInIntent(...);
        if (requestCode == RC_SIGN_IN) {
            Task<GoogleSignInAccount> task = GoogleSignIn.getSignedInAccountFromIntent(data);
            try {
                // Google Sign In was successful, authenticate with Firebase
                GoogleSignInAccount account = task.getResult(ApiException.class);
                FirebaseGoogleAuth(account);
            } catch (ApiException e) {
                // Google Sign In failed, update UI appropriately
                Toast.makeText(Loginscreen.this,"Signed in Unsuccessful",Toast.LENGTH_SHORT).show();
                FirebaseGoogleAuth(null);

            }
        }
    }

   /* private void handleSignInResult(Task<GoogleSignInAccount> completedTask) {
        try {
            GoogleSignInAccount account = completedTask.getResult(ApiException.class);

            // Signed in successfully, show authenticated UI.
            FirebaseGoogleAuth(account);
        } catch (ApiException e) {
            // The ApiException status code indicates the detailed failure reason.
            // Please refer to the GoogleSignInStatusCodes class reference for more information.
            Toast.makeText(Loginscreen.this,"Signed in Unsuccessful",Toast.LENGTH_SHORT).show();
           FirebaseGoogleAuth(null);
        }
    }*/

    private void FirebaseGoogleAuth(GoogleSignInAccount acct)
    {
        AuthCredential authCredential= GoogleAuthProvider.getCredential(acct.getIdToken(),null);
        auth.signInWithCredential(authCredential).addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
              if(task.isSuccessful())
              {   Toast.makeText(Loginscreen.this,"Login Successful",Toast.LENGTH_SHORT).show();
                  FirebaseUser user=auth.getCurrentUser();
                  updateUI(user);

              }
              else
              {
                  Toast.makeText(Loginscreen.this,"Login UnSuccessful",Toast.LENGTH_SHORT).show();
                  updateUI(null);
              }
            }
        });

    }
    private void updateUI(FirebaseUser fuser)
    { SignoutButton.setVisibility(View.VISIBLE);
     GoogleSignInAccount account= GoogleSignIn.getLastSignedInAccount(getApplicationContext());
     if(account!=null)
     { Intent intent=new Intent(Loginscreen.this,Mainscreen1.class);
               startActivity(intent);
               }

    }
  /*  public void onsignout()
    {

    }
*/
}
