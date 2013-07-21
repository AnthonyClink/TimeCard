package com.clinkworks.timecard.services;

import org.jukito.JukitoRunner;
import org.jukito.UseModules;
import org.junit.Test;
import org.junit.runner.RunWith;

import com.clinkworks.timecard.component.TimecardSegmentComponent;
import com.clinkworks.timecard.config.TestCaseConfigBase;

@RunWith(JukitoRunner.class)
@UseModules({ TestCaseConfigBase.class })
public class TestTimecardSegmentComponent {
	
	@Test
	public void testTimecardSegmentComponentCanCreateSegments(TimecardSegmentComponent underTest){
		
	}
}
