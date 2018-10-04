package com.job.JobAssist;

public class SaveUserProfile {

    public String name,city,address,pincode
            ,xmarks,xyear,xiimarks,xiiyear
            ,ugmarks,ugyear
            ,skills,achievements,certifications,workexp,sex,ugcourse,email,dob;

    public SaveUserProfile(String name, String city, String address, String pincode, String xmarks, String xyear, String xiimarks, String xiiyear, String ugmarks, String ugyear, String skills, String achievements, String certifications, String workexp, String sex, String ugcourse,String email,String dob) {
        this.city = city;
        this.name = name;
        this.address = address;
        this.pincode = pincode;
        this.xmarks = xmarks;
        this.xyear = xyear;
        this.xiimarks = xiimarks;
        this.xiiyear = xiiyear;
        this.ugmarks = ugmarks;
        this.ugyear = ugyear;
        this.skills = skills;
        this.achievements = achievements;
        this.certifications = certifications;
        this.workexp = workexp;
        this.sex = sex;
        this.ugcourse = ugcourse;
        this.email=email;
        this.dob=dob;
    }

    public SaveUserProfile(){

    }
}
