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

import com.clinkworks.timecard.datatypes.Entry;
import com.google.inject.Inject;
import com.google.inject.assistedinject.Assisted;


public class TimecardEntry implements ReadableInstant{
    
	private Entry entry;
	
	@Inject
	public TimecardEntry(@Assisted Entry entry){
		this.entry = entry;
	
	}
	
	public java.sql.Date getTimestampWithSqlDate(){
		return new java.sql.Date(getTimestamp().getMillis());
	}
	
	public java.util.Date getTimestampWithJavaDate(){
		return new java.util.Date(getTimestamp().getMillis());
	}
	
	public DateTime getTimestamp(){
		return entry.getTimestamp();
	}
	
	public Long getId(){
		return entry.getId();
	}
	
	
	public boolean isBefore(TimecardEntry that){
		return getTimestamp().isBefore(that.getTimestamp());
	}
	
	
	public boolean isAfter(TimecardEntry that){
		return getTimestamp().isAfter(that.getTimestamp());
	}
	
	
	public boolean isEqual(TimecardEntry that){
		return getTimestamp().isEqual(that.getTimestamp());
	}
	
	
	@Override
	public long getMillis() {
		return getTimestamp().getMillis();
	}


	
	@Override
	public Chronology getChronology() {
		return this.getChronology();
	}

	
	@Override
	public DateTimeZone getZone() {
		return this.getZone();
	}

	
	@Override
	public int get(DateTimeFieldType type) {
		return getTimestamp().get(type);
	}

	
	@Override
	public boolean isSupported(DateTimeFieldType field) {
		return getTimestamp().isSupported(field);
	}

	
	@Override
	public Instant toInstant() {
		return getTimestamp().toInstant();
	}

	
	@Override
	public boolean isEqual(ReadableInstant instant) {
		return getTimestamp().isEqual(instant);
	}

	
	@Override
	public boolean isAfter(ReadableInstant instant) {
		return getTimestamp().isAfter(instant);
	}

	
	@Override
	public boolean isBefore(ReadableInstant instant) {
		return getTimestamp().isBefore(instant);
	}

	
	@Override
	public int compareTo(ReadableInstant instant) {
		return getTimestamp().compareTo(instant);
	}
	
	
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
		
	@Override
	public int hashCode(){
		return getTimestamp().hashCode();
	}
	
	@Override
	public String toString(){
		return getTimestampWithJavaDate().toString();
	}

	public Entry getEntry() {
		return entry;
	}
}
