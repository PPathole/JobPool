package com.job.JobAssist;

import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class ShowProfile extends AppCompatActivity {

    TextView tv_name,tv_sex, tv_city,tv_address,
            tv_pincode,tv_10marks,tv_10year,tv_12marks,
            tv_12year,tv_ugmarks,tv_ugyear,tv_ugcourse,tv_skills,
            tv_achievement,tv_certification,tv_workexp
            ,tv_dob;

    FirebaseAuth mAuth;
    Button downloadcv,showvideo;

    DatabaseReference databaseReference;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_profile);
        mAuth = FirebaseAuth.getInstance();
        FirebaseUser user=mAuth.getCurrentUser();

        databaseReference= FirebaseDatabase.getInstance().getReference("Users"+"/"+user.getUid()+"/");


        tv_name=(TextView)findViewById(R.id.tv_name);
        tv_sex=(TextView)findViewById(R.id.tv_sex);
        tv_city=(TextView)findViewById(R.id.tv_city);
        tv_address=(TextView)findViewById(R.id.tv_address);
        tv_pincode=(TextView)findViewById(R.id.tv_pincode);
        tv_10marks=(TextView)findViewById(R.id.tv_10marks);
        tv_10year=(TextView)findViewById(R.id.tv_10year);
        tv_12marks=(TextView)findViewById(R.id.tv_12marks);
        tv_12year=(TextView)findViewById(R.id.tv_12year);
        tv_ugmarks=(TextView)findViewById(R.id.tv_ugmarks);
        tv_ugyear=(TextView)findViewById(R.id.tv_ugyear);
        tv_ugcourse=(TextView)findViewById(R.id.tv_ugcourse);
        tv_skills=(TextView)findViewById(R.id.tv_skills);
        tv_achievement=(TextView)findViewById(R.id.tv_achievement);
        tv_certification=(TextView)findViewById(R.id.tv_certification);
        tv_workexp=(TextView)findViewById(R.id.tv_workexp);
        tv_dob=(TextView)findViewById(R.id.tv_dob);



        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                String name=dataSnapshot.child("name").getValue(String.class);
                String sex=dataSnapshot.child("sex").getValue(String.class);
                String city=dataSnapshot.child("city").getValue(String.class);
                String address=dataSnapshot.child("address").getValue(String.class);
                String pincode=dataSnapshot.child("pincode").getValue(String.class);
                String xmarks=dataSnapshot.child("xmarks").getValue(String.class);
                String xyear=dataSnapshot.child("xyear").getValue(String.class);
                String xiimarks=dataSnapshot.child("xiimarks").getValue(String.class);
                String xiiyear=dataSnapshot.child("xiiyear").getValue(String.class);
                String ugmarks=dataSnapshot.child("ugmarks").getValue(String.class);
                String ugyear=dataSnapshot.child("ugyear").getValue(String.class);
                String ugcourse=dataSnapshot.child("ugcourse").getValue(String.class);
                String skills=dataSnapshot.child("skills").getValue(String.class);
                String achievements=dataSnapshot.child("achievements").getValue(String.class);
                String certification=dataSnapshot.child("certifications").getValue(String.class);
                String workexp=dataSnapshot.child("workexp").getValue(String.class);
                String dob=dataSnapshot.child("dob").getValue(String.class);




                tv_name.setText(name);
                tv_sex.setText(sex);
                tv_city.setText(city);
                tv_address.setText(address);
                tv_pincode.setText(pincode);
                tv_10marks.setText(xmarks);tv_10year.setText(xyear);
                tv_12marks.setText(xiimarks);
                        tv_12year.setText(xiiyear);
                        tv_ugmarks.setText(ugmarks);
                        tv_ugyear.setText(ugyear);
                        tv_ugcourse.setText(ugcourse);
                        tv_skills.setText(skills);
                        tv_achievement.setText(achievements);
                        tv_certification.setText(certification);
                        tv_workexp.setText(workexp);
                        tv_dob.setText(dob);

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                Toast.makeText(ShowProfile.this, "Error", Toast.LENGTH_SHORT).show();
            }
        });







    }
}
