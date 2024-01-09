package com.app.controller;

import com.app.dto.AppointmentDTO;
import com.app.service.AppointmentService;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/appointments")
public class AppointmentController {

	@Autowired
    private  AppointmentService appointmentService;

    @Autowired
    public AppointmentController() {
		System.out.println("in ctor of " + getClass());

    }

    @PostMapping
    public AppointmentDTO createAppointment(@RequestBody AppointmentDTO appointmentDTO) {
        return appointmentService.scheduleAppointment(appointmentDTO);
    }
    
    @GetMapping("/{doctorId}/upcoming")
    public List<AppointmentDTO> getAppoinments(@PathVariable Long doctorId)
    {
    	return appointmentService.getUpcomingAppointments(doctorId);
    }
    @DeleteMapping("/{appointmentId}")
    public String cancelAppointment(@PathVariable Long appointmentId) {
        appointmentService.cancelAppointment(appointmentId);
        return "Appointment with ID " + appointmentId + " has been canceled.";
    }

}
