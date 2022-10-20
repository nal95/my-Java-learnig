package com.nal95.clinic.security.config;

public class SecurityConstants {
    public static final long EXPIRATION_TIME = 864000000; //10 days
    public static final String TOKEN_PREFIX = "Bearer ";
    public static final String HEADER_STRING = "Authorization";
    public static final String SIGN_UP_URL = "/api/users";

    public static final String PATIENT_URL = "/api/patients";

    public static final String PATIENTS_URL = "/api/patients/*";

    public static final String PATIENT_ANALYZE_URL = "/api/patients/analyze/*";

    public static final String CLINICAL_URL = "/api/clinicals";

    public static final String CLINICALS_URL = "/api/clinicals/**";

    public static final String TOKEN_SECRET = "jf9i4jgu83nfl0l";
}
