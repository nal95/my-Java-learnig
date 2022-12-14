package com.nal95.clinic_v1.utils;

public class AppRouteConstants {

    private AppRouteConstants() {
        //notting
    }

    public static final String EMAIL = "springtest95@gmail.com";
    public static final String API = "/api";
    public static final String PATIENT = API + "/patient"; //GET
    public static final String PATIENTS = API + "/patients"; //GET & POST
    public static final String gDATA = API + "/data"; // POST
    public static final String pDATA = API + "/data/**"; //GET
    public static final String VERIFICATION_LINK = "http://localhost:8080/clinicalServices" + API + "/verification/";


}
