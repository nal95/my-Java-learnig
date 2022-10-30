package com.nal95.clinic.dto.request;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ClinicalDataRequest {
    private String componentName;
    private String componentValue;
    private int patientId;
}
