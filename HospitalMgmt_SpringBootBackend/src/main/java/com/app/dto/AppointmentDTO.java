package com.app.dto;

import java.time.LocalDateTime;

import javax.validation.constraints.NotNull;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
@Getter
@Setter
@ToString
public class AppointmentDTO {
	@NotNull(message ="Docotor Id required!!!!")
	private Long doctorId;
	@NotNull(message ="Patient Id required!!!!")
    private Long patientId;
    private LocalDateTime appointmentDateTime;
}
