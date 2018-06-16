package com.cosmelione.platzigram.views;


import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.transition.Fade;
import android.support.transition.Slide;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import com.cosmelione.platzigram.R;
import com.cosmelione.platzigram.login.presenter.LoginPresenter;
import com.cosmelione.platzigram.login.presenter.LoginPresenterImpl;
import com.cosmelione.platzigram.login.view.LoginActivity;
import com.cosmelione.platzigram.login.view.LoginActivityView;
import com.cosmelione.platzigram.views.fragments.RootFragment;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;

public class ContainerActivity extends AppCompatActivity implements LoginActivityView {

    private Fragment currentFragment;
    private int currentItem;
    private BottomNavigationView bottomNavigationView;
    private LoginPresenter loginPresenter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_container);
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();

        currentFragment = RootFragment.newInstance(RootFragment.HOME);

        fragmentTransaction.add(R.id.fragment_container, currentFragment, getString(R.string.HomeFragment));
//        fragmentTransaction.addToBackStack("ROOT");
        fragmentTransaction.commit();

//        BottomNavigationView bottomNavigationView;
        bottomNavigationView =  findViewById(R.id.bottomNavigationView);
        currentItem = bottomNavigationView.getSelectedItemId();

        BottomNavigationView.OnNavigationItemSelectedListener managerBehaviorClick = new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                return replaceFragment(item.getItemId());
            }
        };

        bottomNavigationView.setOnNavigationItemSelectedListener(managerBehaviorClick);

        getSupportFragmentManager().addOnBackStackChangedListener(new FragmentManager.OnBackStackChangedListener() {
            @Override
            public void onBackStackChanged() {
//                currentFragment
                currentFragment = getVisibleFragment();
            }
        });

        loginPresenter = new LoginPresenterImpl(this);
    }


    @Override
    public boolean onSupportNavigateUp() {

        if ( ((RootFragment) currentFragment).isNotEmpty() ) {
            ((RootFragment) currentFragment).popBackStack();
        }
        return true;
    }

    @Override
    public void onBackPressed() {
//        currentFragment = getVisibleFragment();

        if ( ((RootFragment) currentFragment).isNotEmpty() ) {
            ((RootFragment) currentFragment).popBackStack();
        }
        else {
            finish();
//            super.onBackPressed();
//            reselectedBottomNavItem(((RootFragment) currentFragment).getMenuItemFragment());
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_settings, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.sign_out_menu_item:
                loginPresenter.signOut();
                break;
        }
        return super.onOptionsItemSelected(item);
    }


    @Override
    public void goBackToLogin() {
        Intent intentHome = new Intent(this, LoginActivity.class);
        startActivity(intentHome);
        finish();
    }

    private boolean replaceFragment(int itemId) {

        String tagFragment;
        if (currentItem == itemId) {

            Bundle bundle = currentFragment.getArguments();
            tagFragment = ((RootFragment) currentFragment).getMenuItemFragment();
            if (bundle != null && bundle.containsKey(tagFragment))
                ((RootFragment) currentFragment).restoreBaseFragment(bundle);

            return true;
        }
        switch (itemId) {
            case R.id.home_menu_item:
                tagFragment = getString(R.string.HomeFragment);
                currentFragment = getFragmentFromTagIfExists(tagFragment);
                if (currentFragment == null) currentFragment = RootFragment.newInstance(RootFragment.HOME);
                break;
            case R.id.search_menu_item:
                tagFragment = getString(R.string.SearchFragment);
                currentFragment = getFragmentFromTagIfExists(tagFragment);
                if (currentFragment == null) currentFragment = RootFragment.newInstance(RootFragment.SEARCH);
                break;
            case  R.id.profile_menu_item:
                tagFragment = getString(R.string.ProfileFragment);
                currentFragment = getFragmentFromTagIfExists(tagFragment);
                if (currentFragment == null) currentFragment = RootFragment.newInstance(RootFragment.PROFILE);
                break;
            default:
                return false;
        }
        currentItem = itemId;

        Fragment prevFragment = getVisibleFragment();
        prevFragment.setExitTransition(new Slide());
        currentFragment.setEnterTransition(new Fade());

        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction transaction = fragmentManager.beginTransaction();

        // Replace whatever is in the fragment_container view with this fragment,
        // and add the transaction to the back stack
        transaction.replace(R.id.fragment_container, currentFragment, tagFragment);
        transaction.addToBackStack(null);

        // Commit the transaction
        transaction.commit();

        return true;
    }

    private Fragment getFragmentFromTagIfExists(String tag) {

        FragmentManager fragmentManager = getSupportFragmentManager();

        if (fragmentManager.findFragmentByTag(tag) != null) {
            return fragmentManager.findFragmentByTag(tag);
        }

        return null;
    }

    /*private void reselectedBottomNavItem(String item) {
       if (item.equals(RootFragment.HOME)) {
           bottomNavigationView.getMenu().getItem(0).setChecked(true);
       }
       else if (item.equals(RootFragment.SEARCH)) {
           bottomNavigationView.getMenu().getItem(1).setChecked(true);
       }
       else {
           bottomNavigationView.getMenu().getItem(2).setChecked(true);
       }
    }*/

    public String getLikesText(int likes) {
        return String.format(getString(R.string.likesTextCard), likes);
    }

    public String getTimeAgoText(int days) {
        switch (days) {
            case 0:
                return getString(R.string.todayTextCard);
            case 1:
                return getString(R.string.yesterdayTextCard);
            default:
                return String.format(getString(R.string.timeAgoCard), days);
        }
    }

    private Fragment getVisibleFragment() {
        FragmentManager fragmentManager = getSupportFragmentManager();
        return fragmentManager.findFragmentById(R.id.fragment_container);
    }



    @Override
    public void enableInputs() {

    }

    @Override
    public void disableInputs() {

    }

    @Override
    public void showProgressBar() {

    }

    @Override
    public void hideProgressBar() {

    }

    @Override
    public void loginSuccess() {

    }

    @Override
    public void loginError(String error) {

    }

    @Override
    public void showCreateAccount() {

    }

    @Override
    public void goToSignInWithGoogle(GoogleSignInClient googleSignInClient) {

    }

    @Override
    public void goToWebsite() {

    }


}
