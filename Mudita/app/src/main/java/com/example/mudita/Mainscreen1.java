package com.example.mudita;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.Menu;
import android.widget.Button;
import android.widget.Toast;

import com.facebook.FacebookSdk;
import com.facebook.login.LoginManager;
import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.common.SignInButton;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;
import com.google.android.material.navigation.NavigationView;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

public class Mainscreen1 extends AppCompatActivity {
    private Button Signoutbutton;
    private GoogleSignIn googleSignIn;
    private GoogleSignInAccount account;
    private GoogleSignInClient mGoogleSignInClient;
    private FirebaseAuth firebaseAuth;

    private AppBarConfiguration mAppBarConfiguration;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mainscreen1);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        FloatingActionButton fab = findViewById(R.id.fab);

        //NO NEED TO USE SEPARATE SIGN OUT CHECK SINCE WE CAN SIGN OUT USING FIREBASE

      /*  //FACEBOOK
        FacebookSdk.sdkInitialize(getApplicationContext());
        //GOOGLE
        GoogleSignInOptions gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN).requestIdToken(getString(R.string.default_web_client_id)).requestEmail().build();
        mGoogleSignInClient = GoogleSignIn.getClient(this, gso);
        account = GoogleSignIn.getLastSignedInAccount(this);*/
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                firebaseAuth = FirebaseAuth.getInstance();

                FirebaseUser user = firebaseAuth.getCurrentUser();
                if(user!=null)
                {  FirebaseAuth.getInstance().signOut();
                    Intent intent=new Intent(Mainscreen1.this,Welcomescreen.class);
                    startActivity(intent);
                    finish();

                }

              /*  if(account!=null) {
                    Toast.makeText(Mainscreen1.this, "Your Are Signed Out", Toast.LENGTH_SHORT).show();
                    mGoogleSignInClient.signOut();
                    Intent intent=new Intent(Mainscreen1.this,Welcomescreen.class);
                    startActivity(intent);
                    finish();


                }
                if(account==null)
                { LoginManager.getInstance().logOut();
                    Intent intent=new Intent(Mainscreen1.this,Welcomescreen.class);
                    startActivity(intent);
                    finish();}*/
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();

            }
        });
        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        NavigationView navigationView = findViewById(R.id.nav_view);
        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.
        mAppBarConfiguration = new AppBarConfiguration.Builder(
                R.id.nav_home, R.id.nav_gallery, R.id.nav_slideshow)
                .setDrawerLayout(drawer)
                .build();
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment);
        NavigationUI.setupActionBarWithNavController(this, navController, mAppBarConfiguration);
        NavigationUI.setupWithNavController(navigationView, navController);






}

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.mainscreen1, menu);
        return true;
    }

    @Override
    public boolean onSupportNavigateUp() {
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment);
        return NavigationUI.navigateUp(navController, mAppBarConfiguration)
                || super.onSupportNavigateUp();
    }
}
