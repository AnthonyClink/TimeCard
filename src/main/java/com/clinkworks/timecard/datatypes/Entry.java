package com.clinkworks.timecard.datatypes;

import java.sql.Timestamp;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQuery;
import javax.persistence.NamedQueries;
import javax.persistence.Table;
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
    @NamedQuery(name="Entry.findAll",
                query="SELECT e FROM Entry e"),
    @NamedQuery(name="Entry.findAllBetween",
    			query="SELECT e FROM Entry e " +
    					"WHERE e.timeStamp BETWEEN :start AND :end")
}) 
public class Entry implements ReadableInstant{
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY, generator="ENTRY_SEQ")
	private Long id;
	
	/**
	 * this is a hack till I figure out how to handle joda datetime correctly, or learn to make a custom
	 * mapper
	 */
	@Persistent
	Timestamp timeStamp;
	
	@Inject
	public Entry(@Assisted DateTime timeStamp){
		this.timeStamp = new Timestamp(timeStamp.getMillis());
	}
	
	
	public DateTime getTimeStamp(){
		//NOTE: this hack will go away as soon as I take the time to properly map jodatime 
		return new DateTime(timeStamp);
	}
	
	public Long getId(){
		return id;
	}
	
	public boolean isBefore(Entry that){
		return getTimeStamp().isBefore(that.getTimeStamp());
	}
	
	public boolean isAfter(Entry that){
		return getTimeStamp().isAfter(that.getTimeStamp());
	}
	
	public boolean isEqual(Entry that){
		return getTimeStamp().isEqual(that.getTimeStamp());
	}
	
	@Override
	public long getMillis() {
		return getTimeStamp().getMillis();
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
		return getTimeStamp().get(type);
	}


	@Override
	public boolean isSupported(DateTimeFieldType field) {
		return getTimeStamp().isSupported(field);
	}


	@Override
	public Instant toInstant() {
		return getTimeStamp().toInstant();
	}


	@Override
	public boolean isEqual(ReadableInstant instant) {
		return getTimeStamp().isEqual(instant);
	}


	@Override
	public boolean isAfter(ReadableInstant instant) {
		return getTimeStamp().isAfter(instant);
	}


	@Override
	public boolean isBefore(ReadableInstant instant) {
		return getTimeStamp().isBefore(instant);
	}


	@Override
	public int compareTo(ReadableInstant instant) {
		return getTimeStamp().compareTo(instant);
	}

}
