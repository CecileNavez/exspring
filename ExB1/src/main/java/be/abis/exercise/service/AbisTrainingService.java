package be.abis.exercise.service;

import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import be.abis.exercise.exception.EnrollException;
import be.abis.exercise.model.Course;
import be.abis.exercise.model.Person;
import be.abis.exercise.repository.CourseRepository;
import be.abis.exercise.repository.PersonRepository;

@Service
public class AbisTrainingService implements TrainingService{

	@Autowired
	PersonRepository personRep;
	@Autowired
	CourseService courseService;
	
	@Override
	public CourseService getCourseService() {
		return courseService;
	}


	public void setCourseService(CourseService courseService) {
		this.courseService = courseService;
	}
	
		
	@Override
	public ArrayList<Person> getAllPersons() {
		return personRep.getAllPersons();
	}

	@Override
	public Person findPerson(int id) {
		return personRep.findPerson(id);
	}

	@Override
	public Person findPerson(String emailAddress, String passWord) {
		return personRep.findPerson(emailAddress, passWord);
	}

	@Override
	public void addPerson(Person p) throws IOException {
		personRep.addPerson(p);;
		
	}

	@Override
	public void deletePerson(int id) {
		personRep.deletePerson(id);
	}

	@Override
	public void changePassword(Person p, String newPswd) throws IOException {
		personRep.changePassword(p, newPswd);
		
	}

	@Override
	public List<Course> showFollowedCourses(Person person) {
		return null;
	}

	@Override
	public void enrollForSession(Person person, Course course, LocalDate date) throws EnrollException {
		
	}

}
