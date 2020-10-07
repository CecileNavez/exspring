package be.abis.exercise.controller;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import be.abis.exercise.model.Person;
import be.abis.exercise.service.TrainingService;

@Controller
public class AppController {
	
	@Autowired
	TrainingService trainingService;
	
	Person loggedPerson;
	
	@GetMapping("/")
	public String showLogin(Model model) {
		Person person = new Person();
		model.addAttribute("person", person);
		return "login";
	}
	
	@PostMapping("/")
	public String submitLogin(Model model, Person person) {
		loggedPerson = trainingService.findPerson(person.getEmailAddress(), person.getPassword());
		return "redirect:/welcomepage";
	}
	
	@GetMapping("/welcomepage")
	public String showWelcome(Model model) {
		model.addAttribute("person", loggedPerson);
		return "welcomepage";
	}
	
	@GetMapping("/logout")
	public String logout() {
		return "redirect:/";
	}
	
	@GetMapping("/searchforcourse")
	public String showSearchForCourse(Model model) {
		return "searchforcourse";
	}
	
	@GetMapping("/personadmin")
	public String showPersonAdmin(Model model) {
		return "personadmin";
	}
	
	@GetMapping("/back")
	public String back() {
		return "redirect:/welcomepage";
	}

	@GetMapping("/changepassword")
	public String showChangePassword(Model model) {
		model.addAttribute("person", loggedPerson);
		return "changepassword";
	}
	
	@PostMapping("/changepassword")
	public String submitNewPassword(Model model, Person person) {
		try {
			trainingService.changePassword(loggedPerson, person.getPassword());
		} catch (IOException e) {
			e.printStackTrace();
		}
		return "redirect:/passwordmodified";
	}
	
	@GetMapping("/passwordmodified")
	public String showPasswordModified(Model model) {
		return "passwordmodified";
	}
	
	@GetMapping("/searchpersons")
	public String showSearchPersons(Model model) {
		return "searchpersons";
	}
	
	@GetMapping("/addperson")
	public String showAddPerson(Model model) {
		return "addperson";
	}
	
	@GetMapping("/removeperson")
	public String showRemovePerson(Model model) {
		return "removeperson";
	}
	
	@GetMapping("/backtoadmin")
	public String backtoadmin() {
		return "redirect:/personadmin";
	}
	
	@GetMapping("/backtosearchpersons")
	public String backtosearchpersons() {
		return "redirect:/searchpersons";
	}
	
	@GetMapping("/allpersons")
	public String showAllPersons(Model model) {
		return "allpersons";
	}
	
	@GetMapping("/searchpersonbyid")
	public String showSearchPersonById(Model model) {
		return "searchpersonbyid";
	}
	
}