package com.app.service;

import java.util.List;

import com.app.dto.AppointmentDTO;

public interface AppointmentService {
	AppointmentDTO scheduleAppointment(AppointmentDTO appointmentDTO);
	List<AppointmentDTO> getUpcomingAppointments(Long doctorId);
	String cancelAppointment(Long appointmentId);
}
