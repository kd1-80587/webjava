package com.app.service;

import java.util.List;
import java.util.stream.Collectors;

import javax.persistence.EntityNotFoundException;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.app.customException.ResourceNotFoundExc;
import com.app.dao.AppointmentDao;
import com.app.dao.DoctorDao;
import com.app.dao.PatientDao;
import com.app.dto.AppointmentDTO;
import com.app.entities.Appointment;
import com.app.entities.Doctor;
import com.app.entities.Patient;

@Service
@Transactional
public class AppointmentServiceImpl implements AppointmentService{

	 @Autowired
	    private AppointmentDao appointmentDao;

	    @Autowired
	    private DoctorDao doctorDao;

	    @Autowired
	    private PatientDao patientDao;

	    @Autowired
	    private ModelMapper modelMapper; 
	    @Override
	    public AppointmentDTO scheduleAppointment(AppointmentDTO appointmentDTO) {
	        Doctor doctor = doctorDao.findById(appointmentDTO.getDoctorId())
	                .orElseThrow(() -> new EntityNotFoundException("Doctor not found with ID: " + appointmentDTO.getDoctorId()));

	        Patient patient = patientDao.findById(appointmentDTO.getPatientId())
	                .orElseThrow(() -> new EntityNotFoundException("Patient not found with ID: " + appointmentDTO.getPatientId()));

	        Appointment appointment = modelMapper.map(appointmentDTO, Appointment.class);
	        appointment.setDoctor(doctor);
	        appointment.setPatient(patient);

	        appointment = appointmentDao.save(appointment);

	        return modelMapper.map(appointment, AppointmentDTO.class);
	    }
		@Override
		public List<AppointmentDTO> getUpcomingAppointments(Long doctorId) {
			List<Appointment> appointments=appointmentDao.findByDoctorId(doctorId);
			return appointments.stream().map(appointment -> modelMapper.map(appointment, AppointmentDTO.class)).collect(Collectors.toList());
		}
		@Override
		public String cancelAppointment(Long appointmentId) {
		    Appointment appointment = appointmentDao.findById(appointmentId)
		            .orElseThrow(() -> new ResourceNotFoundExc("Invalid Appointment ID " + appointmentId));

		    appointmentDao.deleteById(appointmentId);
		    
		    return "Appointment with ID " + appointmentId + " has been canceled";
		}


}
