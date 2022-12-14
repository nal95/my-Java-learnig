package com.nal95.clinic.dto.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ClinicalDataRequest {
    private String componentName;
    private String componentValue;
    private long patientId;
}