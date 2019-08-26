package com.demo.tracker.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.demo.tracker.domain.TimeTracker;
import com.demo.tracker.repository.TimeTrackerRepository;

@Service
public class TimeTrackerService {

	@Autowired
	TimeTrackerRepository timeTrackerRepository;

	public List<TimeTracker> findAll(Integer offset, Integer length) {
		List<TimeTracker> allRecords = timeTrackerRepository.findAll(timeTrackerRepository.orderByStartTime());
		return getFilteredList(offset, length, allRecords);

	}

	public List<TimeTracker> findByEmail(String email, Integer offset, Integer length) {
		List<TimeTracker> allRecords = timeTrackerRepository.findByEmail(email, timeTrackerRepository.orderByStartTime());
		return getFilteredList(offset, length, allRecords);
	}

	public TimeTracker save(TimeTracker timeTracker) {
		TimeTracker savedEntity = timeTrackerRepository.save(timeTracker);
		return savedEntity;
	}

	

	private List<TimeTracker> getFilteredList(Integer offset, Integer length, List<TimeTracker> allRecords) {
		if (!allRecords.isEmpty()) {
			int offsetValue = offset >= 0 && offset < allRecords.size() ? offset : 0;
			int lengthValue = length > 0 && length <= allRecords.size() ? length : allRecords.size();
			return allRecords.subList(offsetValue, lengthValue);
		} else {
			return new ArrayList<TimeTracker>();
		}
	}

}
