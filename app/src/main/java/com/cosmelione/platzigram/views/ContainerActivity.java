package com.cosmelione.platzigram.views;


import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.transition.Fade;
import android.support.transition.Slide;
import android.support.v4.app.BundleCompat;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;

import com.cosmelione.platzigram.R;
import com.cosmelione.platzigram.views.fragments.HomeFragment;
import com.cosmelione.platzigram.views.fragments.ProfileFragment;
import com.cosmelione.platzigram.views.fragments.RootFragment;
import com.cosmelione.platzigram.views.fragments.SearchFragment;

import java.util.List;

import static android.support.v4.app.FragmentManager.POP_BACK_STACK_INCLUSIVE;

public class ContainerActivity extends AppCompatActivity {

    private Fragment currentFragment;
    private int currentItem;
    private BottomNavigationView bottomNavigationView;


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
        bottomNavigationView = (BottomNavigationView) findViewById(R.id.bottomNavigationView);
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



    private boolean replaceFragment(int itemId) {

        String tagFragment = null;
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

    private void reselectedBottomNavItem(String item) {
       if (item.equals(RootFragment.HOME)) {
           bottomNavigationView.getMenu().getItem(0).setChecked(true);
       }
       else if (item.equals(RootFragment.SEARCH)) {
           bottomNavigationView.getMenu().getItem(1).setChecked(true);
       }
       else {
           bottomNavigationView.getMenu().getItem(2).setChecked(true);
       }
    }

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


}
