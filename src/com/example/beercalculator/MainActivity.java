package com.example.beercalculator;

import java.text.DecimalFormat;

import android.app.Activity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.Menu;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends Activity {

	EditText origin;
	EditText end;
	TextView grados;
	DecimalFormat df;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		df = new DecimalFormat("#.00");
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		origin=(EditText)findViewById(R.id.origin);
		end=(EditText)findViewById(R.id.end);
		grados= (TextView)findViewById(R.id.grados);
		
		origin.addTextChangedListener(mStartWatcher);
		end.addTextChangedListener(mStartWatcher);
	}

	public double platoToSg(double plato){
		double sg;
		sg = 1+ (plato/(258.6-((plato/258.2)*227.1)));
		return sg;
	}
	
	public double calculateAbv(double origin, double end){
		double abv;
		abv = (origin-end)*105*1.25;
		return abv;
	}
	 private final TextWatcher  mStartWatcher = new TextWatcher() {
         
         public void beforeTextChanged(CharSequence s, int start, int count, int after)
         {
                     // When No Password Entered
                    grados.setText("Not Entered");
         }

         public void onTextChanged(CharSequence s, int start, int before, int count)
         {
            
         }

         public void afterTextChanged(Editable s)
         {
        	 double start_val,end_val;
        	 double sgStart;
        	 double sgEnd;
        	 double abv;
        	 if (origin.getText().length() == 0){
        		 start_val = 0;
        	 }
        	 else {
        		 try{
        			 start_val = Double.valueOf(origin.getText().toString());
        		 }
        		 catch(NumberFormatException e){
        			 start_val = 0.0;
        			 origin.setText("");
        		 }
        	 }
        	 
        	 if (end.getText().length() == 0){
        		 end_val = 0;
        	 }
        	 else {
        		 end_val = Double.valueOf(end.getText().toString());
        	 }
//        	 sgStart = Double.valueOf(origin.toString());
//        	 sgEnd = Double.valueOf(end.toString());
        	 
         	 sgStart = platoToSg(start_val);
        	 sgEnd   = platoToSg(end_val);
        	 
        	 abv = calculateAbv(sgStart,sgEnd);
        	 
        	 //grados.setText( origin.getText().toString() );
        	 grados.setText(String.format("%.2f", abv)+" %");
         }
 };

	
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

}
