package com.bidder.docservice.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Lob;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name="Tenderer")
@Getter
@Setter
@Builder

@AllArgsConstructor
@NoArgsConstructor
public class TendererEntity {
	@javax.persistence.Id
	@GeneratedValue(generator = "uuid")
    @GenericGenerator(name = "uuid", strategy = "uuid2")
	private String Id;
	private String name;
	private String fileName;

    private String fileType;

    @Lob
    private byte[] data;
	

}
