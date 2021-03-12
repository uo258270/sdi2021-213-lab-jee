package com.uniovi.services;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.uniovi.entities.Teacher;
import com.uniovi.repositories.TeachersRepository;

@Service
public class TeachersService {
	

	@Autowired
	private TeachersRepository teachersRepository;

	public List<Teacher> getTeachers() {
		List<Teacher> teachers = new ArrayList<Teacher>();
		teachersRepository.findAll().forEach(teachers::add);
		return teachers;
	}

	public Teacher getTeacher(Long id) {
		return teachersRepository.findById(id).get();
	}

	public void addTeacher(Teacher teacher) {
		teachersRepository.save(teacher);
	}


	public void deleteTeacher(Long id) {
		teachersRepository.deleteById(id);
	}
	
	public boolean getTeacherByDni(String dni) {
		List<Teacher> teachers = new ArrayList<Teacher>();
		teachersRepository.findAll().forEach(teachers::add);

		for(Teacher teacher : teachers) {
			System.out.println(teacher.getDni() + ":" + dni);
			if(teacher.getDni().equals(dni)) {
				return true;
			}
		}

		return false;
	}
}
