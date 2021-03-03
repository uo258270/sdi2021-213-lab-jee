package com.uniovi.services;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Set;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.uniovi.entities.Mark;
import com.uniovi.entities.User;
import com.uniovi.repositories.MarksRepository;

@Service
public class MarksService {

	@Autowired
	private HttpSession httpSession;

	@Autowired
	private MarksRepository marksRepository;

	@Autowired
	private MarksService marksService;

	public Page<Mark> getMarks(Pageable pageable) {
		Page<Mark> marks = marksRepository.findAll(pageable);
		return marks;
	}

	public Mark getMark(Long id) {
		Set<Mark> consultedList = (Set<Mark>) httpSession.getAttribute("consultedList");
		if (consultedList == null) {
			consultedList = new HashSet<Mark>();
		}
		Mark obtainedmark = marksRepository.findById(id).get();
		consultedList.add(obtainedmark);
		httpSession.setAttribute("consultedList", consultedList);
		return obtainedmark;
	}

	public void addMark(Mark mark) {
		// Si en Id es null le asignamos el ultimo + 1 de la lista
		marksRepository.save(mark);
	}

	public void deleteMark(Long id) {
		marksRepository.deleteById(id);
	}

	public void setMarkResend(boolean revised, Long id) {
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		String dni = auth.getName();
		Mark mark = marksRepository.findById(id).get();
		if (mark.getUser().getDni().equals(dni)) {
			marksRepository.updateResend(revised, id);
		}

	}

	@RequestMapping(value = "/mark/{id}/resend", method = RequestMethod.GET)
	public String setResendTrue(Model model, @PathVariable Long id) {
		marksService.setMarkResend(true, id);
		return "redirect:/mark/list";
	}

	@RequestMapping(value = "/mark/{id}/noresend", method = RequestMethod.GET)
	public String setResendFalse(Model model, @PathVariable Long id) {
		marksService.setMarkResend(false, id);
		return "redirect:/mark/list";
	}

	public Page<Mark> getMarksForUser(Pageable pageable, User user) {
		Page<Mark> marks = new PageImpl<Mark>(new LinkedList<Mark>());
		if (user.getRole().equals("ROLE_STUDENT")) {
			marks = marksRepository.findAllByUser(pageable, user);
		}
		if (user.getRole().equals("ROLE_PROFESSOR")) {
			marks = getMarks(pageable);
		}
		return marks;
	}
	
	public Page<Mark> searchMarksByDescriptionAndNameForUser (Pageable pageable, String searchText, User user){
		Page<Mark> marks = new PageImpl<Mark>(new ArrayList<Mark>());
		searchText = "%"+searchText+"%";
		if ( user.getRole().equals("ROLE_STUDENT")) {
		marks = marksRepository.searchByDescriptionNameAndUser(pageable, searchText, user);
		}
		if ( user.getRole().equals("ROLE_PROFESSOR")){
		marks = marksRepository.searchByDescriptionAndName(pageable, searchText);
		}
		return marks;
		}

}
