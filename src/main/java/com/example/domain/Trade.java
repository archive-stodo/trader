package com.example.domain;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonValue;
import com.fasterxml.jackson.annotation.JsonView;


@Entity
@Table(name = "Trade")
public class Trade {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@JsonView(View.Trader.class)
	private int id;

	@ManyToOne(cascade = {CascadeType.ALL} )
	@JoinColumn(name="trader_id")
	@JsonIgnore
	private Trader trader;

	@Lob
	@Column(name = "image")
	private byte[] image;

	@Column(name = "side")
	private String side;

	@JsonView(View.Trader.class)
	@Column(name = "instrument_name")
	private String instrumentName;

	@Column(name = "number_of_instruments")
	private long numberOfInstruments;

	@Column(name = "entry_price")
	private float entryPrice;

	@Column(name = "current_close_price")
	private float currentClosePrice;

	@Column(name = "close_price")
	private float closePrice;

	@Column(name = "open_time")
	private Date openTime;

	@Column(name = "close_time")
	private LocalDateTime closeTime;

	@Column(name = "notes")
	private String notes;

//	@JsonValue
//	public String serializeTrade(){
//		return String.valueOf(id);
//	}

	public Trade(){

	}

	public Trade(String instrumentName, long numberOfInstruments, float entryPrice,
			Date openTime) {
		super();
		this.instrumentName = instrumentName;
		this.numberOfInstruments = numberOfInstruments;
		this.entryPrice = entryPrice;
		this.openTime = openTime;
	}

	public Trade(String instrumentName, long numberOfInstruments, float entryPrice,
			String notes, byte[] image, Date openTime) {
		super();
		this.instrumentName = instrumentName;
		this.numberOfInstruments = numberOfInstruments;
		this.entryPrice = entryPrice;
		this.openTime = openTime;
		this.notes = notes;
		this.image = image;
	}

	public Trade(String instrumentName, long numberOfInstruments, float entryPrice,
			float closePrice, Date openTime, String side) {
		super();
		this.instrumentName = instrumentName;
		this.numberOfInstruments = numberOfInstruments;
		this.entryPrice = entryPrice;
		this.openTime = openTime;
		this.side = side;
		this.closePrice = closePrice;
	}

	public Trade(String instrumentName, long numberOfInstruments, float entryPrice,
			float closePrice, String notes, byte[] image, Date openTime, String side) {
		super();
		this.instrumentName = instrumentName;
		this.numberOfInstruments = numberOfInstruments;
		this.entryPrice = entryPrice;
		this.openTime = openTime;
		this.notes = notes;
		this.image = image;
		this.side = side;
		this.closePrice = closePrice;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Trader getTrader() {
		return trader;
	}

	public void setTrader(Trader trader) {
		this.trader = trader;
	}

	public String getInstrumentName() {
		return instrumentName;
	}

	public void setInstrumentName(String instrumentName) {
		this.instrumentName = instrumentName;
	}

	public long getNumberOfInstruments() {
		return numberOfInstruments;
	}

	public void setNumberOfInstruments(long numberOfInstruments) {
		this.numberOfInstruments = numberOfInstruments;
	}

	public float getEntryPrice() {
		return entryPrice;
	}

	public void setEntryPrice(float entryPrice) {
		this.entryPrice = entryPrice;
	}

	public float getCurrentClosePrice() {
		return currentClosePrice;
	}

	public void setCurrentClosePrice(float currentClosePrice) {
		this.currentClosePrice = currentClosePrice;
	}

	public float getClosePrice() {
		return closePrice;
	}

	public void setClosePrice(float closePrice) {
		this.closePrice = closePrice;
	}

	public Date getOpenTime() {
		return openTime;
	}

	public void setOpenTime(Date openTime) {
		this.openTime = openTime;
	}

	public LocalDateTime getCloseTime() {
		return closeTime;
	}

	public void setCloseTime(LocalDateTime closeTime) {
		this.closeTime = closeTime;
	}

	public String getNotes() {
		return notes;
	}

	public void setNotes(String notes) {
		this.notes = notes;
	}

	public byte[] getImage() {
		return image;
	}

	public void setImage(byte[] image) {
		this.image = image;
	}

	public String getSide() {
		return side;
	}

	public void setSide(String side) {
		this.side = side;
	}


}
