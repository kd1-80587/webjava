package com.app.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.app.entities.Appointment;

public interface AppointmentDao extends JpaRepository<Appointment,Long>{

	List<Appointment> findByDoctorId(Long doctorId);

}
