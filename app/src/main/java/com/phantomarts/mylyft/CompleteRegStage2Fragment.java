package com.phantomarts.mylyft;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.android.volley.AuthFailureError;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.FirebaseDatabase;
import com.phantomarts.mylyft.model.User;

import java.util.HashMap;
import java.util.Map;

public class CompleteRegStage2Fragment extends Fragment implements View.OnClickListener {
    private static final String TAG="VALIDATE_CODE_REQUEST";
    private EditText etCode;
    private Button btnDone,btnSendAgain;

    private ProgressDialog progressDialog;

    private FirebaseAuth firebaseAuth;
    private FirebaseDatabase firebaseDatabase;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View inflate=inflater.inflate(R.layout.fragment_compreg_stage2,container,false);

        firebaseAuth= FirebaseAuth.getInstance();
        firebaseDatabase= FirebaseDatabase.getInstance();

        progressDialog=new ProgressDialog(getContext());
        etCode=inflate.findViewById(R.id.etcode);
        btnDone=inflate.findViewById(R.id.button3);
        btnSendAgain=inflate.findViewById(R.id.button5);

        btnDone.setOnClickListener(this);
        btnSendAgain.setOnClickListener(this);
        return inflate;
    }


    @Override
    public void onClick(View v) {
        if(v==btnDone){
            //validate code

            progressDialog.setMessage("Validating Phone No");
            progressDialog.show();
            sendRequest();
            return;
        }else if(v==btnSendAgain){

            return;
        }
    }

    private void sendRequest(){
        String sudoServeraddress="https://wicked-bat-86.localtunnel.me/";
        String localServeraddress="http://10.0.2.2:8080";
        String url=localServeraddress+"/LyftMeServer/VerifyPhone";
        Response.Listener responseListener=new Response.Listener() {
            @Override
            public void onResponse(Object response) {
                String resp=response.toString().trim();
                if(resp.equals("22")){
                    progressDialog.setMessage("Validation Success!");
                    //save that phone no validated in local db

                    progressDialog.dismiss();
                    //removing all fragments
                    for (Fragment fragment : getActivity().getSupportFragmentManager().getFragments()) {
//                        if (fragment instanceof NavigationDrawerFragment) {
//                            continue;
//                        }
                        if (fragment != null) {
                            getActivity().getSupportFragmentManager().beginTransaction().remove(fragment).commit();
                            System.out.println("removing");
                        }
                    }


                    //update firebase db
                    firebaseDatabase.getReference("/users/customers/"+firebaseAuth.getCurrentUser().getUid()+"/phoneNo")
                            .setValue(CompleteRegActivity.phoneNo);
                    firebaseDatabase.getReference("/users/customers/"+firebaseAuth.getCurrentUser().getUid()+"/regCompleteStatus")
                            .setValue(User.ACCOUNT_COMPLETE_ALL);

                    getActivity().finish();
                    startActivity(new Intent(getContext(),MainActivity.class));

                }else if(resp.equals("33")){
                    etCode.setError("Invalide Code. Check Again");
                    progressDialog.dismiss();
                }
                else{
                    System.out.println(response.toString());
                    Toast.makeText(getContext(), "Something went wrong. Try Again!", Toast.LENGTH_SHORT).show();
                    progressDialog.dismiss();
                }
//


            }
        };
        Response.ErrorListener requestErrorListener=new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                progressDialog.dismiss();
                Toast.makeText(getContext(), "Something went wrong!. Please Try Again Later", Toast.LENGTH_SHORT).show();
                Log.i(TAG,"Error :" + error.toString());
            }
        };

        StringRequest stringRequest=new StringRequest(StringRequest.Method.POST,url,responseListener,requestErrorListener){
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                HashMap<String, String> map = new HashMap<>();
                map.put("uid", firebaseAuth.getCurrentUser().getUid());
                map.put("code", etCode.getText().toString().trim());
                map.put("reqtype", "1");
                return map;
            }
        };

        RequestQueue rq= Volley.newRequestQueue(getContext());
        rq.add(stringRequest);
    }
}
