package com.nal95.clinic.utils;

public class AppRouteConstants {
    private AppRouteConstants(){
        //notting
    }
    public static final String EMAIL = "springtest95@gmail.com";
    public static final String API = "/api"; //POST
    public static final String USER = API +"/user"; //POST
    public static final String VERIFICATION_LINK = "http://localhost:8080/clinicalservices" + USER +"/verification/";

    public static final String TOKEN_VERIFICATION = USER + "/verification/**";
    public static final String SIGN_IN_URL = USER + "/login"; //POST

    public static final String SIGN_UP_URL = USER + "/signup"; //POST

    public static final String USER_URL = USER + "/all"; //GET

    public static final String ROLE_URL = USER + "/role"; //POST

    public static final String ADD_ROLE_TO_USER_URL = USER + "/add-role-to-user"; //POST

    public static final String REFRESH_TOKEN = USER + "/token/refresh"; //GET
    public static final String PATIENT_URL = API + "/patients"; //GET & POST

    public static final String PATIENTS_URL = PATIENT_URL + "/**"; //GET

    public static final String PATIENT_ANALYZE_URL = PATIENT_URL + "/analyze/**"; //GET
    public static final String CLINICAL_URL = API + "/clinical-data"; //POST

    public static final String CLINICAL_DATA_URL = CLINICAL_URL + "/**"; //GET
}
