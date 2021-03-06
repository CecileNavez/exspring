package be.abis.exercise.controller;

import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import be.abis.exercise.model.Course;
import be.abis.exercise.model.Person;
import be.abis.exercise.service.CourseService;
import be.abis.exercise.service.TrainingService;

@Controller
public class AppController {
	
	@Autowired
	TrainingService trainingService;
	@Autowired
	CourseService courseService;
	
	Person loggedPerson;
	List<Person> personsFound;
	Person personFound;
	List<Course> coursesFound;
	Person removedPerson;
	Person addedPerson;
	Course courseFound;
	
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
		Person person = new Person();
		model.addAttribute("person", person);
		return "addperson";
	}
	
	@PostMapping("/addperson")
	public String submitNewPerson(Model model, Person person) {
		try {
			trainingService.addPerson(person);
			} catch (IOException e) {
			e.printStackTrace();
		}
		return "redirect:/addedperson";
	}
	
	@GetMapping("/addedperson")
	public String showAddedPerson(Model model) {
		return "addedperson";
	}
	
	
	@GetMapping("/removeperson")
	public String showRemovePerson(Model model) {
		Person person = new Person();
		model.addAttribute("person", person);
		return "removeperson";
	}
	
	@PostMapping("/removeperson")
	public String submitRemovePerson(Model model, Person person) {
		trainingService.deletePerson(person.getPersonId());
		return "redirect:/removedperson";
	}
	
	@GetMapping("/removedperson")
	public String showRemovedPerson(Model model) {
		return "removedperson";
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
		personsFound = trainingService.getAllPersons();
		model.addAttribute("persons", personsFound);
		return "allpersons";
	}
		
	@GetMapping("/searchpersonbyid")
	public String showSearchPersonById(Model model) {
		Person person = new Person();
		model.addAttribute("person", person);
		return "searchpersonbyid";
	}
	
	@GetMapping("/resultpersonbyid")
	public String showResultPersonById(Model model) {
		model.addAttribute("person", personFound);
		return "/resultpersonbyid";
	}
	
	@GetMapping("/allcourses")
	public String showAllCourses(Model model) {
		coursesFound = courseService.findAllCourses();
		model.addAttribute("courses",coursesFound);
		return "/allcourses";
	}
	
	@GetMapping("/backtosearchforcourses")
	public String backtosearchforcourses() {
			return "redirect:/searchforcourse";
	}
	
	@GetMapping("/searchcoursebyid")
	public String showSearchCourseById(Model model) {
		Course course = new Course();
		model.addAttribute("course", course);
		return "searchcoursebyid";
	}
	
	@PostMapping("/searchcoursebyid")
	public String submitSearchCourseById(Model model, Course course) {
		courseFound = courseService.findCourse(Integer.parseInt(course.getCourseId()));
		return "redirect:/resultcoursebyid";
	}
	
	@GetMapping("/resultcoursebyid")
	public String showResultCourseById(Model model) {
		model.addAttribute("course", courseFound);
		return "/resultcoursebyid";
	}
	
	@GetMapping("/searchcoursebyname")
	public String showSearchCourseByName(Model model) {
		Course course = new Course();
		model.addAttribute("course", course);
		return "searchcoursebyname";
	}
	
	@PostMapping("/searchcoursebyname")
	public String submitSearchCourseByName(Model model, Course course) {
		courseFound = courseService.findCourse(course.getShortTitle());
		return "redirect:/resultcoursebyname";
	}
	
	@GetMapping("/resultcoursebyname")
	public String showResultCourseByName(Model model) {
		model.addAttribute("course", courseFound);
		return "/resultcoursebyname";
	}
}
