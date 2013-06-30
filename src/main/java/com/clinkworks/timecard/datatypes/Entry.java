package com.clinkworks.timecard.datatypes;

import java.sql.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.apache.openjpa.persistence.Persistent;
import org.joda.time.DateTime;

import com.google.inject.Inject;
import com.google.inject.assistedinject.Assisted;

@Entity
@Table(name="EntryTable")
@NamedQuery(name="Entry.findAll", query="SELECT Entry FROM Entry") 
public class Entry{
	
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY, generator="ENTRY_SEQ")
	private Long id;
	
	/**
	 * this is a hack till I figure out how to handle joda datetime correctly, or learn to make a custom
	 * mapper
	 */
	@Transient
	private DateTime systemTimeStamp;
	
	@Persistent
	private Date timeStamp;
	
	@Inject
	public Entry(@Assisted DateTime timeStamp){
		this.systemTimeStamp = timeStamp;
		this.timeStamp = new Date(timeStamp.getMillis());
	}
	
	public DateTime getTimeStamp(){
		return systemTimeStamp;
	}
	
	public Long getId(){
		return id;
	}
	
	
}
