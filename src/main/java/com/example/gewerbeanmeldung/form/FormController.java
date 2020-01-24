package com.example.gewerbeanmeldung.form;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMethod;



@CrossOrigin(origins="https://veranstaltungsformular.firebaseapp.com")
@RestController
@RequestMapping(path = "")
public class FormController {
	
	@Autowired
	private FormService formService;
	// Lists all Questions
		@RequestMapping(path = "/forms/all")
		public List<Form> getAllForms() {
			return formService.getAllForms();
		}
	
		@RequestMapping(method = RequestMethod.POST, value = "/forms/add")
		public String saveForm(@RequestBody String formname) {
			return formService.addWhenNotExisting(formname);
		}
	
}
