package com.clinkworks.timecard.datatypes;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQuery;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import org.apache.openjpa.persistence.Persistent;
import org.joda.time.DateTime;

import clinkworks.timecard.persistence.IEntity;

import com.google.inject.Inject;
import com.google.inject.assistedinject.Assisted;

@Entity
@Table(name="EntryTable")
@NamedQuery(name="Entry.findAll", query="SELECT Entry FROM Entry") 
public class Entry implements IEntity<Long>{
	
	@Id
	@SequenceGenerator(name = "ENTRY_ID_GENERATOR", sequenceName = "SEQ_ENTRY")
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "ENTRY_ID_GENERATOR")	
	private Long id;
	
	@Persistent
	private DateTime timeStamp;
	
	@Inject
	public Entry(@Assisted DateTime timeStamp){
		this.timeStamp = timeStamp;
	}
	
	public DateTime getTimeStamp(){
		return timeStamp;
	}
	
	@Override
	public Long getId(){
		return id;
	}
	
	
}
