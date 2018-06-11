package com.cosmelione.platzigram.views.fragments;


import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.transition.Explode;
import android.support.transition.Fade;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.cosmelione.platzigram.R;

import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link RootFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class RootFragment extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_FRAGMENT = "ID_FRAGMENT";

    public static final String HOME = "com.cosmelione.platzigram.views.fragments.HomeFragment";
    public static final String SEARCH = "com.cosmelione.platzigram.views.fragments.SearchFragment";
    public static final String PROFILE = "com.cosmelione.platzigram.views.fragments.ProfileFragment";

    // TODO: Rename and change types of parameters
    private String mFragment;


    public RootFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param mFragment
     * @return A new instance of fragment RootFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static RootFragment newInstance(String mFragment) {
        RootFragment fragment = new RootFragment();
        Bundle args = new Bundle();
        args.putString(ARG_FRAGMENT, mFragment);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mFragment = getArguments().getString(ARG_FRAGMENT);
//            Fragment fragment = getChildFragmentManager().getFragment(getArguments(),mFragment);
//            if (fragment != null) {
//                restoreFragment(fragment);
//            }
//            else {
//                createFragment();
//            }
        }
        createFragment();

        FragmentManager.OnBackStackChangedListener onBackStackChangedListener = new FragmentManager.OnBackStackChangedListener() {
            @Override
            public void onBackStackChanged() {
                if (isBaseFragment(getVisibleFragment())) {
                    getChildFragmentManager().popBackStack(getVisibleFragment().getId(), FragmentManager.POP_BACK_STACK_INCLUSIVE);
                    getArguments().remove(mFragment);
                }
            }
        };
        getChildFragmentManager().addOnBackStackChangedListener(onBackStackChangedListener);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_root, container, false);
    }


    @Override
    public void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);
        Fragment fragment = getVisibleFragment();
        if (fragment != null && isBaseFragment(fragment)) {
            getChildFragmentManager().putFragment(outState, mFragment, fragment);

        }
    }


    public void changeFragment (@NonNull Fragment fragment, @NonNull Bundle bundle) {
        onSaveInstanceState(bundle);
        restoreFragment(fragment);
    }

    public boolean isNotEmpty() {
        Fragment fragment = getVisibleFragment();
        return getChildFragmentManager().getBackStackEntryCount() > 0 && !isBaseFragment(fragment);
    }

    private boolean isBaseFragment(@NonNull Fragment fragment) {

       return fragment instanceof HomeFragment |
                fragment instanceof SearchFragment | fragment instanceof ProfileFragment;
    }



    public void popBackStack() {
        try {
            getChildFragmentManager().popBackStack();
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }

    public String getMenuItemFragment () {
        return mFragment;
    }


    private Fragment getVisibleFragment() {
        FragmentManager fragmentManager = getChildFragmentManager();
        return fragmentManager.findFragmentById(R.id.fragment_content);
    }

    private void createFragment() {
        Fragment fragment;

        if (mFragment.equals(HOME)) {
            fragment = new HomeFragment();
        }
        else if (mFragment.equals(SEARCH)) {
            fragment = new SearchFragment();
        }
        else if (mFragment.equals(PROFILE)) {
            fragment = new ProfileFragment();
        }
        else {
            fragment = new HomeFragment();
        }

        FragmentManager fragmentManager = getChildFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();

        fragmentTransaction.add(R.id.fragment_content, fragment);
        fragmentTransaction.commit();
    }

    private void restoreFragment (@NonNull Fragment fragment) {

        Fragment prevFragment = getVisibleFragment();
        prevFragment.setExitTransition(new Explode());
        fragment.setEnterTransition(new Fade());
        FragmentManager fragmentManager = getChildFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();

        fragmentTransaction.replace(R.id.fragment_content, fragment);
        fragmentTransaction.addToBackStack(null);
        fragmentTransaction.commitAllowingStateLoss();

    }

    public void restoreBaseFragment (@NonNull Bundle savedFragment) {

        Fragment baseFragment = getChildFragmentManager().getFragment(savedFragment,mFragment);

        if (baseFragment != null) {
            restoreFragment(baseFragment);
        }

    }

}
