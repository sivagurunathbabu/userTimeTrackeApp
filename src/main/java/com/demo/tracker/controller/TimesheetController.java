package com.demo.tracker.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.demo.tracker.domain.TimeTracker;
import com.demo.tracker.service.TimeTrackerService;

@RestController
public class TimesheetController {
	
	@Autowired
	TimeTrackerService timeTrackerService;

	 @RequestMapping(value="/index")
	 public ModelAndView showHomePage(){
		 return new ModelAndView("index", "", null);
	    }
	 
	 @RequestMapping(value="/search")
	 public ModelAndView showSearchPage(){
		 return new ModelAndView("search",  "timeTracker", new TimeTracker());
	    }
	 

	@RequestMapping(value="/tracker")
	public ModelAndView showAllRecords(@RequestParam(name ="offset" ,defaultValue = "0") Integer offset, 
            @RequestParam(name="length",defaultValue = "10") Integer length) {
		List<TimeTracker> allrecords = timeTrackerService.findAll(offset,length);
		
		return new ModelAndView("usersTimesheets", "userTimesheets", allrecords);
	}

	@RequestMapping("/{email}")
	public ModelAndView search(@PathVariable(value = "email", required = false) String email,@RequestParam(name ="offset" ,defaultValue = "0") Integer offset, 
            @RequestParam(name="length",defaultValue = "10") Integer length) {
		List<TimeTracker> allrecords = timeTrackerService.findByEmail(email,offset,length);
		return new ModelAndView("usersTimesheets", "userTimesheets", allrecords);
	}
	
	@RequestMapping("/userTimesheets")
	public ModelAndView searchUserTimesheet(@RequestParam String email,@RequestParam(name ="offset" ,defaultValue = "0") Integer offset, 
            @RequestParam(name="length",defaultValue = "10") Integer length) {
		
		if(email==null || email.trim().length()==0) {
			 ModelAndView modelAndView = new ModelAndView();
			 modelAndView.setViewName("search");
			 return modelAndView;
		}
		List<TimeTracker> allrecords = timeTrackerService.findByEmail(email,offset,length);
		return new ModelAndView("usersTimesheets", "userTimesheets", allrecords);
	}
	
	
	 @RequestMapping(value = "/createTimesheet", method = RequestMethod.GET)
	   public ModelAndView createTimesheet() {
	      return new ModelAndView("createTimesheet", "timeTracker", new TimeTracker());
	   }
	
	@RequestMapping("/saveTimesheet")
	public ModelAndView create(@Valid @ModelAttribute("timeTracker")TimeTracker timeTracker,final BindingResult result) {
		if(result.hasErrors()) {
			 ModelAndView modelAndView = new ModelAndView();
			 modelAndView.setViewName("createTimesheet");
			 return modelAndView;
		}
		List<TimeTracker> allrecords = timeTrackerService.findByEmail(timeTracker.getEmail(),0,10);
		TimeTracker savedEntity = timeTrackerService.save(timeTracker);
		allrecords.add(0, savedEntity);
		return new ModelAndView("usersTimesheets", "userTimesheets", allrecords);
	}
}
