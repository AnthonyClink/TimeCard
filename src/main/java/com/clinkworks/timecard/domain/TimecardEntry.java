package com.clinkworks.timecard.domain;

import java.sql.Timestamp;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQuery;
import javax.persistence.NamedQueries;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.apache.openjpa.persistence.Persistent;

import org.joda.time.Chronology;
import org.joda.time.DateTime;
import org.joda.time.DateTimeFieldType;
import org.joda.time.DateTimeZone;
import org.joda.time.Instant;
import org.joda.time.ReadableInstant;

import com.google.inject.Inject;
import com.google.inject.assistedinject.Assisted;

@Entity
@Table(name="EntryTable")
@NamedQueries({
    @NamedQuery(
    	name="TimecardEntry.findAll",
    	query="SELECT e FROM TimecardEntry e"
    ),
    @NamedQuery(
    	name="TimecardEntry.findAllBetween",
    	query="SELECT e FROM TimecardEntry e " +
    		"WHERE e.timeStamp BETWEEN :start AND :end"
    ),
    @NamedQuery(
    	name="TimecardEntry.deleteById", 
    	query="DELETE FROM TimecardEntry e WHERE e.id = :ID"
    )
}) 
public class TimecardEntry implements ReadableInstant{
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY, generator="ENTRY_SEQ")
	private Long id;
	

	@Persistent
	private Timestamp timeStamp;
	
	/*
	 * this is a hack till I figure out how to handle joda datetime correctly, or learn to make a custom
	 * mapper
	 */	
	@Transient
	private DateTime systemDateTime;
	
	@Inject
	public TimecardEntry(@Assisted DateTime timeStamp){
		this.timeStamp = new Timestamp(timeStamp.getMillis());
		systemDateTime = timeStamp;
	}
	
	
	public DateTime getTimeStamp(){
		//NOTE: this hack will go away as soon as I take the time to properly map jodatime
		if(systemDateTime == null && timeStamp != null){
			systemDateTime = new DateTime(timeStamp);
		}
		
		return systemDateTime;
	}
	
	public Long getId(){
		return id;
	}
	
	@Transient
	public boolean isBefore(TimecardEntry that){
		return getTimeStamp().isBefore(that.getTimeStamp());
	}
	
	@Transient
	public boolean isAfter(TimecardEntry that){
		return getTimeStamp().isAfter(that.getTimeStamp());
	}
	
	@Transient
	public boolean isEqual(TimecardEntry that){
		return getTimeStamp().isEqual(that.getTimeStamp());
	}
	
	@Transient
	@Override
	public long getMillis() {
		return getTimeStamp().getMillis();
	}


	@Transient
	@Override
	public Chronology getChronology() {
		return this.getChronology();
	}

	@Transient
	@Override
	public DateTimeZone getZone() {
		return this.getZone();
	}

	@Transient
	@Override
	public int get(DateTimeFieldType type) {
		return getTimeStamp().get(type);
	}

	@Transient
	@Override
	public boolean isSupported(DateTimeFieldType field) {
		return getTimeStamp().isSupported(field);
	}

	@Transient
	@Override
	public Instant toInstant() {
		return getTimeStamp().toInstant();
	}

	@Transient
	@Override
	public boolean isEqual(ReadableInstant instant) {
		return getTimeStamp().isEqual(instant);
	}

	@Transient
	@Override
	public boolean isAfter(ReadableInstant instant) {
		return getTimeStamp().isAfter(instant);
	}

	@Transient
	@Override
	public boolean isBefore(ReadableInstant instant) {
		return getTimeStamp().isBefore(instant);
	}

	@Transient
	@Override
	public int compareTo(ReadableInstant instant) {
		return getTimeStamp().compareTo(instant);
	}
	
	@Transient
	@Override
	public boolean equals(Object object){
		if(object == this){
			return true;
		}
		if(object instanceof TimecardEntry){
			return isEqual((TimecardEntry)object);
		}
		if(object instanceof DateTime){
			return isEqual((DateTime)object);
		}
		return false;
	}
	
	@Transient	
	@Override
	public int hashCode(){
		return getTimeStamp().hashCode();
	}
}
