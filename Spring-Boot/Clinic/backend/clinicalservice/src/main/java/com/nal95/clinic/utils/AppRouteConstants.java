package com.nal95.clinic.utils;

public class AppRouteConstants {

    public static final String USER = "/api/user"; //POST
    public static final String API = "/api"; //POST
    public static final String SIGN_IN_URL = USER + "/login"; //POST

    public static final String SIGN_UP_URL = USER + "/registration"; //POST

    public static final String USER_URL = USER + "/all"; //GET

    public static final String ROLE_URL = USER + "/role"; //POST

    public static final String ADD_ROLE_TO_USER_URL = USER + "/add-role-to-user"; //POST

    public static final String REFRESH_TOKEN = USER + "/token/refresh"; //GET
    public static final String PATIENT_URL = API + "/patients"; //GET & POST

    public static final String PATIENTS_URL = PATIENT_URL + "/**"; //GET

    public static final String PATIENT_ANALYZE_URL = PATIENT_URL + "/analyze/**"; //GET

    public static final String CLINICAL_URL = API + "/clinicals"; //POST

    public static final String CLINICAL_DATE_URL = CLINICAL_URL + "/**"; //GET
}
