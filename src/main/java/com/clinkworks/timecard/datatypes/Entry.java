package com.clinkworks.timecard.datatypes;

import java.sql.Timestamp;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.apache.openjpa.persistence.Persistent;
import org.joda.time.DateTime;

import com.google.inject.Inject;
import com.google.inject.assistedinject.Assisted;

@Entity
@Table(name="EntryTable")
@NamedQueries({
    @NamedQuery(
    	name="Entry.findAll",
    	query="SELECT e FROM Entry e"
    ),
    @NamedQuery(
    	name="Entry.findAllBetween",
    	query="SELECT e FROM Entry e " +
    		"WHERE e.timestamp BETWEEN :start AND :end"
    ),
    @NamedQuery(
    	name="Entry.deleteById", 
    	query="DELETE FROM Entry e WHERE e.id = :ID"
    )
}) 
public class Entry {
	
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY, generator="ENTRY_SEQ")
	private Long id;
	
    
	@Persistent
	private Timestamp timestamp;
	
	/*
	 * this is a hack till I figure out how to handle joda datetime correctly, or learn to make a custom
	 * mapper
	 */	
	@Transient
	private DateTime systemDateTime;
	
	@Inject
	public Entry(@Assisted DateTime timeStamp){
		this.timestamp = new Timestamp(timeStamp.getMillis());
		systemDateTime = timeStamp;
	}
	
	public DateTime getTimestamp(){
		//NOTE: this hack will go away as soon as I take the time to properly map jodatime
		if(systemDateTime == null && timestamp != null){
			systemDateTime = new DateTime(timestamp);
		}
		
		return systemDateTime;		
	}

	public Long getId() {
		return id;
	}
}
