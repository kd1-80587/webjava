package com.app.dto;

import java.time.LocalDateTime;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
@Getter
@Setter
@ToString
public class GetAppointmentDTO {
	private Long id;
	private Long doctorId;
	private Long patientId;
	private LocalDateTime appointmentDateTime;
	

}
