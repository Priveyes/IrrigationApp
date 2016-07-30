package com.example.meedy.irrigationapp;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;


public class drip_irrFrequency extends AppCompatActivity {


    TextView freq, Ram, net, freasnwer, days;
    EditText AvailableMoisture, depfactor;
    Button comp;
    MySingleton mySingleton = MySingleton.getInstance();
    drip_frequency_formula drip_freq = new drip_frequency_formula();

@Override
    protected void onCreate(Bundle savedInstanceState){
    super.onCreate(savedInstanceState);
    setContentView(R.layout.drip_irrfrequency);

    freq = (TextView)findViewById(R.id.freq);
    Ram = (TextView)findViewById(R.id.AvailableMoisture);
    days =(TextView)findViewById(R.id.days);
    freasnwer =(TextView)findViewById(R.id.ansFreq);
    net =(TextView)findViewById(R.id.netpertree);
    comp =(Button)findViewById(R.id.compbtn);

    AvailableMoisture =(EditText)findViewById(R.id.AM);
    depfactor = (EditText)findViewById(R.id.depletion);

    comp.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View v) {

            if(drip_freq.freq_drip() > 0){

                float ans = (drip_freq.freq_drip())/(mySingleton.netdepth_drip * mySingleton.Sp * mySingleton.Sr);
                Log.d("check", ans +"" );
                freasnwer.setText(Float.toString(ans));
            }else{

            }


        }
    });




    net.setText(Float.toString((mySingleton.netdepth_drip) * (mySingleton.Sp * mySingleton.Sr)));


    AvailableMoisture.addTextChangedListener(new TextWatcher() {
        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {

        }

        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {


            try {


                float AvailableM = Float.parseFloat(AvailableMoisture.getText().toString());
                drip_freq.setAvailablemoisture(AvailableM);
                //Ram.setText(Float.toString(drip_freq.getAvailablemoisture()));

                if (depfactor.getText().toString().matches("")) {
                    Log.d("answer 2", Float.toString(drip_freq.getAvailablemoisture()));
                    Ram.setText("0.0");
                } else {
                    Ram.setText(Float.toString(drip_freq.freq_drip()));
                }


            } catch (NumberFormatException e) {
            }
        }

        @Override
        public void afterTextChanged(Editable s) {


        }
    });

    depfactor.addTextChangedListener(new TextWatcher() {
        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {


        }

        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {


        }

        @Override
        public void afterTextChanged(Editable s) {

            //Log.d("deleting" , Float.parseFloat(depfactor.getText().toString())*2 +"ye");

            try {


                float factor = Float.parseFloat(depfactor.getText().toString());
                if (AvailableMoisture.getText().toString().matches("")) {
                    factor = 0;
                }

                drip_freq.setDfactor(factor/100);

                if (depfactor.getText().toString().matches("")) {
                    Ram.setText("0.0");
                } else {
                    Ram.setText(Float.toString(drip_freq.freq_drip()));
                }
                //float answer = factor;
                //mySingleton.factor = answer;
            } catch (NumberFormatException e) {

            }


        }
    });


    }



}
