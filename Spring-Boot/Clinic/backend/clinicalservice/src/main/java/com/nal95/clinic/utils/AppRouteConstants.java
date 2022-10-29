package com.nal95.clinic.utils;

public class AppRouteConstants {

    public static final String SIGN_IN_URL = "/api/user/login"; //POST

    public static final String SIGN_UP_URL = "/api/user/registration"; //POST
    public static final String USER_URL = "/api/users"; //GET

    public static final String ROLE_URL = "/api/role"; //POST

    public static final String ADD_ROLE_TO_USER_URL = "/api/user/role"; //POST

    public static final String PATIENT_URL = "/api/patients"; //GET & POST

    public static final String PATIENTS_URL = "/api/patients/*"; //GET

    public static final String PATIENT_ANALYZE_URL = "/api/patients/analyze/*"; //GET

    public static final String CLINICAL_URL = "/api/clinicals"; //POST

    public static final String CLINICALS_URL = "/api/clinicals/**"; //GET
}
