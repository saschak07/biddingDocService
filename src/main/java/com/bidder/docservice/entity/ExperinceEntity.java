package com.bidder.docservice.entity;

import javax.persistence.Embeddable;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
@Setter
@Getter
@Builder

@AllArgsConstructor
@NoArgsConstructor
@Embeddable
public class ExperinceEntity {
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
