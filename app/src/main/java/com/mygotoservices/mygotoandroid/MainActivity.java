package com.mygotoservices.mygotoandroid;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.bottomnavigation.LabelVisibilityMode;
import android.support.design.widget.BottomNavigationView;
import android.support.design.widget.CoordinatorLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.location.places.Place;
import com.google.android.gms.location.places.Places;
import com.google.android.gms.location.places.ui.PlacePicker;
import com.mygotoservices.mygotoandroid.Fragments.AccountFragment;
import com.mygotoservices.mygotoandroid.Fragments.HomeFragment;
import com.mygotoservices.mygotoandroid.Fragments.MarketFragment;
import com.mygotoservices.mygotoandroid.Fragments.MoreFragment;
import com.mygotoservices.mygotoandroid.Fragments.ServicesFragment;
import com.mygotoservices.mygotoandroid.Misc.BottomNavigationViewHelper;
import com.mygotoservices.mygotoandroid.RealmTables.UserTable;

import io.realm.Realm;

public class MainActivity extends AppCompatActivity implements GoogleApiClient.OnConnectionFailedListener, GoogleApiClient.ConnectionCallbacks {

    private TextView mTextMessage;
    private GoogleApiClient mGoogleApiClient;
    Fragment mainFragment;


    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            Fragment fragment;
            Realm realm = Realm.getDefaultInstance();
            UserTable userTable= realm.where(UserTable.class).findFirst();

            switch (item.getItemId()) {
                case R.id.home:
                    //toolbar.setBackgroundResource(R.drawable.tool_bar);
                    fragment = new HomeFragment();
                    loadFragment(fragment);

                    return true;

                case R.id.services:
                    //toolbar.setBackgroundResource(R.drawable.tool_bar);
                    fragment = new ServicesFragment();
                    loadFragment(fragment);
                    return true;
                case R.id.market:
                    //toolbar.setBackgroundResource(R.drawable.tool_bar);

                    if(userTable == null)
                    {
                        Intent intent = new Intent(MainActivity.this, LoginActivity.class);
                        startActivity(intent);
                    }
                    else
                    {
                        fragment = new MarketFragment();
                        loadFragment(fragment);
                    }

                    return true;
                case R.id.account:
                    //toolbar.setBackgroundResource(R.drawable.tool_bar);
                    if(userTable == null)
                    {
                        Intent intent = new Intent(MainActivity.this, LoginActivity.class);
                        startActivity(intent);
                    }
                    else
                    {
                        fragment = new AccountFragment();
                        loadFragment(fragment);
                    }
                    return true;
                case R.id.more:
                    //toolbar.setBackgroundResource(R.drawable.tool_bar);
                    fragment = new MoreFragment();
                    loadFragment(fragment);
                    return true;
            }
            return false;
        }
    };

    private int getSelectedItem(BottomNavigationView bottomNavigationView) {
        Menu menu = bottomNavigationView.getMenu();
        for (int i = 0; i < bottomNavigationView.getMenu().size(); i++) {
            MenuItem menuItem = menu.getItem(i);
            if (menuItem.isChecked()) {
                return menuItem.getItemId();
            }
        }
        return 0;
    }
    private void loadFragment(Fragment fragment) {
        // load fragment
        mainFragment = fragment;
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.frame_container, fragment);
        transaction.addToBackStack(null);
        transaction.commit();
    }
    BottomNavigationView navigation;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);

        mTextMessage = (TextView) findViewById(R.id.message);
         navigation = (BottomNavigationView) findViewById(R.id.navigation);
        BottomNavigationViewHelper.disableShiftMode(navigation);
        navigation.setLabelVisibilityMode(LabelVisibilityMode.LABEL_VISIBILITY_LABELED);
        CoordinatorLayout.LayoutParams layoutParams = (CoordinatorLayout.LayoutParams) navigation.getLayoutParams();
        layoutParams.setBehavior(new BottomNavigationViewHelper());
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);
        Fragment fragment;
        Realm realm = Realm.getDefaultInstance();
        UserTable userTable= realm.where(UserTable.class).findFirst();

        switch (getSelectedItem(navigation)) {
            case R.id.home:
                //toolbar.setBackgroundResource(R.drawable.tool_bar);
                fragment = new HomeFragment();
                loadFragment(fragment);

                break;

            case R.id.services:
                //toolbar.setBackgroundResource(R.drawable.tool_bar);
                fragment = new ServicesFragment();
                loadFragment(fragment);
                break;
            case R.id.market:
                //toolbar.setBackgroundResource(R.drawable.tool_bar);

                if(userTable == null)
                {
                    Intent intent = new Intent(MainActivity.this, LoginActivity.class);
                    startActivity(intent);
                }
                else
                {
                    fragment = new MarketFragment();
                    loadFragment(fragment);
                }
                break;
            case R.id.account:
                //toolbar.setBackgroundResource(R.drawable.tool_bar);
                if(userTable == null)
                {
                    Intent intent = new Intent(MainActivity.this, LoginActivity.class);
                    startActivity(intent);
                }
                else
                {
                    fragment = new AccountFragment();
                    loadFragment(fragment);
                }
                break;
            case R.id.more:
                //toolbar.setBackgroundResource(R.drawable.tool_bar);
                fragment = new MoreFragment();
                loadFragment(fragment);
            default:
                fragment = new HomeFragment();
                loadFragment(fragment);
                break;
        }


    }

    @Override
    protected void onResume() {
        super.onResume();
        navigation.setSelectedItemId(R.id.home);
        Fragment fragment = new HomeFragment();
        loadFragment(fragment);

    }

    @Override
    public void onConnected(@Nullable Bundle bundle) {

    }

    @Override
    public void onConnectionSuspended(int i) {

    }

    @Override
    public void onConnectionFailed(@NonNull ConnectionResult connectionResult) {

    }


    @Override
    protected void onActivityResult(int requestCode, final int resultCode, Intent data)
    {

       // super.onActivityResult(requestCode, resultCode, data);
    }

}
