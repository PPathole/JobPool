package com.job.JobAssist;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.OnProgressListener;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import java.util.Calendar;

public class EditProfile extends AppCompatActivity implements View.OnClickListener{


    EditText u_city,u_name,u_address,u_pincode
            ,u_xmarks,u_xiimarks
            ,u_ugmarks
            ,u_skills,u_achievements,u_certifications,u_workexp;

    ImageView date;
    TextView textViewStatus,videotextViewStatus,u_dob;
    ProgressBar progressBar,videoprogressBar;

    RadioGroup u_radioGroup;
    RadioButton male,female;

    Spinner ugspinner,xspinner,xiispinner,ug_yearspinner,workexp_spinner;

    String[] uglist={"BCA","BCom","BBA","B.E/B.Tech"};
    String[] exp={"0 Years","1 Years","2 Years","3 Years","4 Years","5 Years",};
    String[] yearlist={"2000","2001","2002","2003"
            ,"2004","2005","2006","2007","2008"
            ,"2009","2010","2011","2012","2013","2014","2015","2016","2017","2018"};



    FirebaseAuth mAuth;

    StorageReference mStorageReference;
    DatabaseReference databaseReference,mDatabasereference;

    @Override
    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_profile);
        mAuth = FirebaseAuth.getInstance();
        FirebaseUser user = mAuth.getCurrentUser();
        mStorageReference= FirebaseStorage.getInstance().getReference();

        mDatabasereference=FirebaseDatabase.getInstance().getReference((Constants.DATABASE_PATH_UPLOADS+"/"+user.getUid()+"/"));
        databaseReference=FirebaseDatabase.getInstance().getReference();


        progressBar=(ProgressBar)findViewById(R.id.progress);
        u_name=(EditText)findViewById(R.id.et_name);
        u_radioGroup=(RadioGroup)findViewById(R.id.rg_sex);
        u_city=(EditText)findViewById(R.id.et_city);
        u_address=(EditText)findViewById(R.id.et_address);
        u_pincode=(EditText)findViewById(R.id.et_pincode);
        u_xmarks=(EditText)findViewById(R.id.et_10marks);

        u_xiimarks=(EditText)findViewById(R.id.et_12marks);

        u_ugmarks=(EditText)findViewById(R.id.et_ugmarks);

        u_skills=(EditText)findViewById(R.id.et_skills);
        u_achievements=(EditText)findViewById(R.id.et_achievement);
        u_certifications=(EditText)findViewById(R.id.et_certification);

        male=(RadioButton)findViewById(R.id.male);
        male.setChecked(true);
        u_dob=(TextView)findViewById(R.id.et_dob);
        date=(ImageView)findViewById(R.id.date);


        ugspinner=(Spinner) findViewById(R.id.sp_ug);

        xspinner=(Spinner)findViewById(R.id.sp_xsp);
        xiispinner=(Spinner)findViewById(R.id.sp_xiisp);
        ug_yearspinner=(Spinner)findViewById(R.id.sp_ugsp);

        workexp_spinner=(Spinner)findViewById(R.id.workexp);



        ArrayAdapter<String> ug=new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,uglist);
        ugspinner.setAdapter(ug);

        ArrayAdapter<String> xyear=new ArrayAdapter<>(this,android.R.layout.simple_list_item_1,yearlist);
        xspinner.setAdapter(xyear);

        ArrayAdapter<String> xiiyear=new ArrayAdapter<>(this,android.R.layout.simple_list_item_1,yearlist);
        xiispinner.setAdapter(xiiyear);

        ArrayAdapter<String> ugyear=new ArrayAdapter<>(this,android.R.layout.simple_list_item_1,yearlist);
        ug_yearspinner.setAdapter(ugyear);


        ArrayAdapter<String> workexp=new ArrayAdapter<>(this,android.R.layout.simple_list_item_1,exp);
        workexp_spinner.setAdapter(workexp);

        findViewById(R.id.btn_saveprofile).setOnClickListener(this);



        date.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Calendar calendar=Calendar.getInstance();
                int year=calendar.get(Calendar.YEAR);
                int month=calendar.get(Calendar.MONTH);
                int day=calendar.get(Calendar.DAY_OF_MONTH);

                DatePickerDialog dialog=new DatePickerDialog(EditProfile.this, new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                        String date=dayOfMonth+"/"+(month+1)+"/"+year;
                        u_dob.setText(date);

                    }
                },year,month,day);
                dialog.show();
            }
        });


    }

    //////to upload user data

    private void SaveProfile() {

        FirebaseUser user=mAuth.getCurrentUser();


        String name = u_name.getText().toString().trim().toUpperCase();
        RadioGroup rg = (RadioGroup)findViewById(R.id.rg_sex);
        String sex = ((RadioButton)findViewById(rg.getCheckedRadioButtonId())).getText().toString().toUpperCase();
        String city = u_city.getText().toString().trim().toUpperCase();
        String address = u_address.getText().toString().trim().toUpperCase();
        String pincode = u_pincode.getText().toString().trim().toUpperCase();
        String xmarks = u_xmarks.getText().toString().trim().toUpperCase();
        String xyear = xspinner.getSelectedItem().toString().trim();
        String xiimarks = u_xiimarks.getText().toString().trim().toUpperCase();
        String xiiyear = xiispinner.getSelectedItem().toString().trim().toUpperCase();
        String ugcourse = ugspinner.getSelectedItem().toString().trim().toUpperCase();
        String ugmarks = u_ugmarks.getText().toString().trim().toUpperCase();
        String ugyear = ug_yearspinner.getSelectedItem().toString().trim().toUpperCase();
        String skills = u_skills.getText().toString().trim().toUpperCase();
        String achievements = u_achievements.getText().toString().trim().toUpperCase();
        String certifications = u_certifications.getText().toString().trim().toUpperCase();
        String workexp =workexp_spinner.getSelectedItem().toString();
        String email = user.getEmail();
        String dob=u_dob.getText().toString();

        if (name.isEmpty()){
            u_name.setError("Name Required");
            u_name.requestFocus();
            return;
        }
        if (city.isEmpty()){
            u_city.setError("City Required");
            u_city.requestFocus();
            return;
        }
        if (address.isEmpty()){
            u_address.setError("Address Required");
            u_address.requestFocus();
            return;
        }
        if (pincode.isEmpty()){
            u_pincode.setError("Pincode Required");
            u_pincode.requestFocus();
            return;
        }
        if (xmarks.isEmpty()){
            u_xmarks.setError("Marks Needed");
            u_xmarks.requestFocus();
            return;
        }

        if (xiimarks.isEmpty()){
            u_xiimarks.setError("Marks Needed");
            u_xiimarks.requestFocus();
            return;
        }

        if (ugmarks.isEmpty()){
            u_ugmarks.setError("Marks Needed");
            u_ugmarks.requestFocus();
            return;
        }



        if (skills.isEmpty()){
            u_skills.setError("Skills Needed");
            u_skills.requestFocus();
            return;
        }

        if (workexp.isEmpty()){
            u_workexp.setError("Work Experience Needed");
            u_workexp.requestFocus();
            return;
        }
        if (dob.isEmpty()){
            u_dob.setError("DOB Needed");
            u_dob.requestFocus();
            return;
        }

        SaveUserProfile sp=new SaveUserProfile(name,city,address,pincode
                ,xmarks,xyear,xiimarks,xiiyear
                ,ugmarks,ugyear
                ,skills,achievements,certifications,workexp,sex,ugcourse,email,dob);


        databaseReference.child("Users").child(user.getUid()).setValue(sp);
        Toast.makeText(EditProfile.this, "success", Toast.LENGTH_SHORT).show();

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){

            case R.id.btn_saveprofile:
                SaveProfile();
                Intent i=new Intent(EditProfile.this,ProfileActivity.class);
                startActivity(i);


        }

    }
}
