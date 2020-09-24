package com.phantomarts.mylyft;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;


public class RideStage1Fragment extends Fragment {

    public static final String TAG = "RideStage1Fragment";
    
    private TextView mtvPickupAddress;
    private TextView mtvDropoffAddress;
    
    private String pickup;
    private String dropoff;

    public RideStage1Fragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View inflate= inflater.inflate(R.layout.fragment_ride_stage1, container, false);

        mtvPickupAddress=inflate.findViewById(R.id.tvpickuprs1);
        mtvDropoffAddress=inflate.findViewById(R.id.tvdropoffrs1);
        if(pickup!=null){
            mtvPickupAddress.setText(pickup);
        }
        if(dropoff!=null){
            mtvDropoffAddress.setText(dropoff);
        }
        return inflate;
    }
    
    public void setParams(String pick,String drop){
        this.pickup=pick;
        this.dropoff=drop;
    }

    public void setPickup(String pickup){
        if(mtvPickupAddress!=null){
            mtvPickupAddress.setText(pickup);
        }
    }

    public void setDropoff(String drop){
        if(mtvPickupAddress!=null){
            mtvPickupAddress.setText(drop);
        }
    }


}
