package com.charpixel.zylo.basefiles;

import android.app.Activity;
import android.app.DialogFragment;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;

/**
 * Created by Devil Guru on 7/4/2017.
 */

public abstract class BaseDialogFragment extends DialogFragment{

    private InputMethodManager mInputManager;


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {


        return createView(inflater, container, savedInstanceState);
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

    }

    protected boolean isNetworkRequired() {
        return true;
    }




    public boolean isDestroy() {
        return getActivity() == null;
    }

    protected void hideKeyboard() {
        if (mInputManager == null || getActivity().getCurrentFocus() == null) {
            return;
        }
        mInputManager.hideSoftInputFromWindow(getActivity().getCurrentFocus().getWindowToken(), InputMethodManager.HIDE_NOT_ALWAYS);
    }

    protected void updateActionButtonState() {
    }


    protected void attach(Activity activity) {
    }




    @Override
    public void onDestroy() {

        super.onDestroy();
        Runtime.getRuntime().gc();


    }

    public boolean onBackPress() {
        return false;
    }

    public abstract View createView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState);
}
