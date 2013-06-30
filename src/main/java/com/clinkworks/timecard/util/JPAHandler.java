package com.clinkworks.timecard.util;

import com.google.inject.Inject;
import com.google.inject.Singleton;
import com.google.inject.persist.PersistService;

public class JPAHandler {
	
	@Inject 
    public JPAHandler(PersistService persistService) {
		persistService.start();
    } 
    
}