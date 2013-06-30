package com.clinkworks.timecard.util;

import com.google.inject.Inject;
import com.google.inject.persist.PersistService;

public class JpaInit {
	
	@Inject 
    public JpaInit(PersistService persistService) {
		persistService.start();
    } 
    
}