package com.clinkworks.timecard.api;

import javax.annotation.concurrent.ThreadSafe;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import com.clinkworks.timecard.component.TimecardEntryComponent;
import com.clinkworks.timecard.component.TimecardSegmentComponent;
import com.google.inject.Inject;
import com.google.inject.Singleton;

/**
 * rest api for time segments.
 * @author AnthonyJCLink
 *
 */
@Singleton
@Produces(MediaType.APPLICATION_JSON)
@Path("timecard/segment")
public final class TimeSegmentService {
	
	private final TimecardEntryComponent timecardEntryComponent;
	private final TimecardSegmentComponent timecardSegmentComponent;
	
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
