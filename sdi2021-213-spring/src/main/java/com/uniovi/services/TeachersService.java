package com.uniovi.services;

import java.util.LinkedList;
import java.util.List;

import javax.annotation.PostConstruct;

import org.springframework.stereotype.Service;

import com.uniovi.entities.Teacher;

@Service
public class TeachersService {
	
	private List<Teacher> teachersList = new LinkedList<Teacher>();

	@PostConstruct
	public void init() {
		teachersList.add(new Teacher(1L, "Nombre1", "Apellido1", "Categoria1"));
		teachersList.add(new Teacher(2L, "Nombre2", "Apellido2", "Categoria2"));
	}

	public List<Teacher> getTeachers() {
		return teachersList;
	}

	public Teacher getTeacher(Long dni) {
		return teachersList.stream().filter(teacher -> teacher.getDni().equals(dni)).findFirst().get();
	}

	public void addTeacher(Teacher teacher) {
		// Si en Id es null le asignamos el ultimo + 1 de la lista
		if (teacher.getDni() == null) {
			teacher.setDni(teachersList.get(teachersList.size() - 1).getDni() + 1);
		}

		teachersList.add(teacher);
	}

	public void deleteTeacher(Long dni) {
		teachersList.removeIf(teacher -> teacher.getDni().equals(dni));
	}

}
