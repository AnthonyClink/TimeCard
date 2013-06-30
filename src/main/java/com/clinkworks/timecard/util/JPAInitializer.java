package com.clinkworks.timecard.util;

import com.google.inject.Inject;
import com.google.inject.persist.PersistService;


public class JPAInitializer {
	
    @Inject 
    public JPAInitializer(PersistService service) {
            service.start(); 
    } 
}