package com.demo.tracker.repository;

import java.util.List;

import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.repository.MongoRepository;

import com.demo.tracker.domain.TimeTracker;

public interface TimeTrackerRepository extends MongoRepository<TimeTracker, String> {

	List<TimeTracker> findByEmail(String name,Sort  sort);

	default Sort orderByStartTime() {
			return new Sort(Sort.Direction.ASC, "startTime");
		}
}
