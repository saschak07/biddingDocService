package com.bidder.docservice.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class PastExperince {
	private String assignmentName;
    private String country;
    private String location;
    private String staff;
    private String clientName;
    private String noOfStaff;
    private String duration;
    private String startDate;
    private String completionDate;
    private String approxValue;
    private String associateConsultant;
    private String associationTime;
    private String snr_staff;
    private String project_desc;
    private String service_desc;
}
