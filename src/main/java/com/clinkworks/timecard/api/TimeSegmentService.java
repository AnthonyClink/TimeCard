package com.clinkworks.timecard.api;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import com.clinkworks.timecard.component.TimecardEntryComponent;
import com.clinkworks.timecard.component.TimecardSegmentComponent;
import com.clinkworks.timecard.domain.TimecardEntry;
import com.clinkworks.timecard.domain.TimecardSegment;
import com.google.inject.Inject;
import com.google.inject.Provider;
import com.google.inject.Singleton;

/**
 * rest api for time segments.
 * @author AnthonyJCLink
 *
 */
@Produces(MediaType.APPLICATION_JSON)
@Path("timecard/segment")
public class TimeSegmentService {
	
	private final TimecardEntryComponent timecardEntryComponent;
	private final TimecardSegmentComponent timecardSegmentComponent;
	//private final Provider<TimecardSegment> segmentProvider; 
	
	@Inject
	public TimeSegmentService(TimecardEntryComponent timecardEntryComponent, TimecardSegmentComponent timecardSegmentComponent){
		this.timecardEntryComponent = timecardEntryComponent;
		this.timecardSegmentComponent = timecardSegmentComponent;
	}
	
	
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getTimecardEntry() {
        return build200ServiceResponse(timecardEntryComponent.createTimecardEntry());
    }
    

	private Response build200ServiceResponse(Object payload){
		return Response.status(200).entity(payload).build();
	}    
	
}
