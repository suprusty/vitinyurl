package com.virtualidentity.vitinyurl.entity;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Objects;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;

@Entity
public class User {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	Long id;

	private String userName;

	private String email;

	@ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	@JoinTable(name = "users_tinyUrls", joinColumns = @JoinColumn(name = "user_id", referencedColumnName = "id"), inverseJoinColumns = @JoinColumn(name = "tinyurl_id", referencedColumnName = "id"))
	List<TinyURL> tinyUrls;

	public List<TinyURL> getTinyUrls() {
		return tinyUrls;
	}

	public void setTinyUrls(List<TinyURL> tinyUrls) {
		this.tinyUrls = tinyUrls;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public void addTinyURL(TinyURL tinyURL) {
		if (tinyURL != null) {
			if (tinyUrls == null) {
				tinyUrls = new ArrayList<>();
			}
			Set<User> users = new HashSet<>();
			users.add(this);
			tinyURL.setUsers(users);
			tinyUrls.add(tinyURL);
		}

	}
}
