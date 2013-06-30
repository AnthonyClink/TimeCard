package com.clinkworks.timecard.services;


import org.jukito.JukitoRunner;

import org.jukito.UseModules;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;

import com.clinkworks.timecard.config.JpaConfig;
import com.clinkworks.timecard.config.TestCaseConfigBase;
import com.clinkworks.timecard.datatypes.Entry;
import com.clinkworks.timecard.service.EntryService;
import com.clinkworks.timecard.util.JPAHandler;

@RunWith(JukitoRunner.class)
@UseModules({ TestCaseConfigBase.class, JpaConfig.class })
public class EntryServiceIntegrationTests {
	
	@Test
	public void EntryServiceCanPersistEntries(EntryService entryService){
		Entry entry = entryService.createEntry();
		Assert.assertNotNull(entry.getId());
	}
}
