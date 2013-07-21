package com.clinkworks.timecard.component;

import com.clinkworks.timecard.domain.TimecardEntry;
import com.clinkworks.timecard.domain.TimecardSegment;
import com.google.inject.Inject;
import com.google.inject.Provider;

public class TimecardSegmentComponent {
	
	private final TimecardEntryComponent timecardEntryComponent;
	private final Provider<TimecardSegment> timecardSegmentProvider;
	
	@Inject
	public TimecardSegmentComponent(TimecardEntryComponent timecardEntryComponent, Provider<TimecardSegment> timecardSegmentProvider) {
		this.timecardEntryComponent = timecardEntryComponent;
		this.timecardSegmentProvider = timecardSegmentProvider;
	}
	
	
	public TimecardSegment createSegment(){
		return null;
	}

}
