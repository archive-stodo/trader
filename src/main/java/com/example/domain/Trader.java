package com.example.domain;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonView;

@Entity
@Table(name = "Trader")
public class Trader {

	@JsonView(View.Trader.class)
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int id;

	@Column(name = "username", unique = true)
	private String username;

	@JsonIgnore
	@Column(name = "password")
	private String password;

	@ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinTable(
        name = "trader_authority",
        joinColumns = @JoinColumn(name = "trader_id", referencedColumnName = "id"),
        inverseJoinColumns = @JoinColumn(name = "authority_id", referencedColumnName = "id")
    )
	private List<Authority> authorities;

	@JsonView(View.Trader.class)
	@OneToMany(mappedBy = "trader", fetch = FetchType.LAZY)
	private Set<Trade> trades = new HashSet<Trade>();

	@JsonView(View.Trader.class)
	@Column(name = "first_name")
	private String firstName;

	@JsonView(View.Trader.class)
	@Column(name = "last_name")
	private String lastName;

	@JsonView(View.Trader.class)
	@Column(name = "email")
	private String email;


	public Trader(){

	}

	public Trader(String firstName, String lastName, String email) {
		super();
		this.firstName = firstName;
		this.lastName = lastName;
		this.email = email;
	}

	public void addTrade(Trade trade){
		trades.add(trade);
		trade.setTrader(this);
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public Set<Trade> getTrades() {
		return trades;
	}

	public void setTrades(Set<Trade> trades) {
		this.trades = trades;
	}


}
