package com.charpixel.zylo;

import android.app.Activity;
import android.app.DialogFragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;

import com.charpixel.zylo.basefiles.BaseDialogFragment;
import com.charpixel.zylo.databinding.FragmentZyloBinding;


/**
 * Created by ashu Template.
 */

public class ZyloFragment extends BaseDialogFragment {

    private final String TAG = this.getClass().getSimpleName();

    FragmentZyloBinding binding;

    boolean isChangeStateChanged = false;


    public static final int LENGTH_SHORT = 1000;
    public static final int LENGTH_NORMAL = 2000;
    public static final int LENGTH_LONG = 3000;
    public String text;
    private int length;
    private int bottomMargin = 50;

    Handler handler;
    Runnable runnable;

    public int getBottomMargin() {
        return bottomMargin;
    }

    public void setBottomMargin(int bottomMargin) {
        this.bottomMargin = bottomMargin;
    }

    public int getLength() {
        return length;
    }

    public void setLength(int length) {
        this.length = length;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public static ZyloFragment getInstance(Bundle bundle) {
        ZyloFragment fragment = new ZyloFragment();

        if (bundle == null) {
            bundle = new Bundle();
        }

        fragment.setArguments(bundle);
        return fragment;
    }

    @Override
    public View createView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        binding = FragmentZyloBinding.inflate(inflater, container, false);

        binding.title.setText(text);


        handler = new Handler();

        try {

            handler.postDelayed(new Runnable() {
                @Override
                public void run() {


                    if(!isChangeStateChanged)
                    dismiss();
                }
            }, length);
        }catch (Exception e)
        {
            e.printStackTrace();
        }


        return binding.getRoot();
    }

    public void refresh() {

    }



    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        WindowManager.LayoutParams params = getDialog().getWindow()
                .getAttributes();
        params.gravity = Gravity.BOTTOM | Gravity.CENTER_HORIZONTAL;
        params.y = bottomMargin;//b.getInt("y");
        getDialog().getWindow().setAttributes(params);

        return super.onCreateView(inflater, container, savedInstanceState);
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        try {


            getDialog().getWindow()
                    .getAttributes().windowAnimations = R.style.DialogAnimation;
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setStyle(DialogFragment.STYLE_NO_FRAME, R.style.MyDialog);
    }

    @Override
    public void show(FragmentManager manager, String tag) {
        super.show(manager, tag);
    }

    public static   void showPopup(Activity context, String s,int length, int bottomMargin) {

        if(context == null){
            return ;
        }

        if(!context.isFinishing()){
            FragmentTransaction fm = context.getFragmentManager().beginTransaction();
            ZyloFragment fragment = new ZyloFragment();
            fragment.setText(s);
            fragment.setLength(length);
            fragment.setBottomMargin(bottomMargin);
//        temBundle.putStringArrayList("data",strArray);
//        fragment.setArguments(temBundle);
            // fragment.setupRestroAdapter();

            fragment.show(fm,"str");

        }



//        FragmentTransaction transactionFragment = context.getFragmentManager().beginTransaction();
//        ZyloFragment fragment = new ZyloFragment();
//
//        fragment.setText(s);
//        fragment.setLength(length);
//        fragment.setBottomMargin(bottomMargin);
//        transactionFragment.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
////        newFragment.setArguments(temBundle);
//        transactionFragment.add(android.R.id.content, fragment).addToBackStack(null).commitAllowingStateLoss();
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
    }


    public void close() {

        getActivity().setResult(Activity.RESULT_OK);
        getActivity().finish();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();

        if(handler != null)
        {
            handler.removeCallbacksAndMessages(null);
        }

    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        isChangeStateChanged = true;
    }
}




