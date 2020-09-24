package com.phantomarts.mylyft;

import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.res.ResourcesCompat;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.phantomarts.mylyft.model.User;
import com.shuhart.stepview.StepView;

import java.util.ArrayList;

public class CompleteRegActivity extends AppCompatActivity {


    static String phoneNo;
    private ActionBar actionBar;
    public StepView stepView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_complete_reg);
        //getSupportActionBar().setDisplayHomeAsUpEnabled(true);
       // getSupportActionBar().setTitle("Back");

        //initializeing stepview
        stepView=findViewById(R.id.step_view);
        stepView.setVisibility(View.INVISIBLE);
        stepView.getState()
                .animationType(StepView.ANIMATION_CIRCLE)
                .steps(new ArrayList<String>(){{
                    add("Add NIC");
                    add("Vehicle Details");
                    add("Complete Profile");
                }})
                .stepsNumber(3)
                .animationDuration(getResources().getInteger(android.R.integer.config_shortAnimTime))
                .typeface(ResourcesCompat.getFont(this, R.font.roboto))
                .commit();

        Fragment fragment=null;
        switch(getIntent().getIntExtra("regCompStatus",-1)){
            case User.ACCOUNT_COMPLETE_STAGE1:
                fragment=new CompleteRegStage1Fragment();
                break;

            default:
                System.out.println("ran def:"+getIntent().getIntExtra("regCompStatus",-1));
                break;
        }
//
        loadFragment(this,R.id.parentLayoutCompReg,fragment, "comp_reg_stage1");
        System.out.println("fireing oncreate");
    }


    @Override
    protected void onStart() {
        super.onStart();
        System.out.println("onstart");
    }

    @Override
    public boolean onSupportNavigateUp() {
        Toast.makeText(this, "back", Toast.LENGTH_SHORT).show();
        return super.onSupportNavigateUp();
    }

    private void loadFragment(Context context, int parent, Fragment fragment, String id){
        FragmentManager fragmentManager=this.getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.add(parent,fragment,id);
        fragmentTransaction.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE);
        fragmentTransaction.commit();
        System.out.println("loading fragment");
    }

    public void btnClick(View v){
//        new MaterialAlertDialogBuilder(this)
//                .setTitle("Title")
//                .setMessage("Message")
//                .setPositiveButton("Ok", this)
//                .setNegativeButton("no", this)
//                .show();
    }

//    @Override
//    public void onClick(DialogInterface dialog, int which) {
//        if(which==-1){
//            //positive
//            Toast.makeText(CompleteRegActivity.this, "postivie", Toast.LENGTH_SHORT).show();
//        }else if(which==-2){
//            //negative
//            Toast.makeText(CompleteRegActivity.this, "negative", Toast.LENGTH_SHORT).show();
//        }
//    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }
}
