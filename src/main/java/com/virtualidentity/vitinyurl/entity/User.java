package com.virtualidentity.vitinyurl.entity;

import java.util.HashSet;
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
	Set<TinyURL> tinyUrls;

	public Set<TinyURL> getTinyUrls() {
		return tinyUrls;
	}

	public void setTinyUrls(Set<TinyURL> tinyUrls) {
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
				tinyUrls = new HashSet<>();
			}
			Set<User> users = new HashSet<>();
			users.add(this);
			tinyURL.setUsers(users);
			tinyUrls.add(tinyURL);
		}

	}

	@Override
	public int hashCode() {
		return Objects.hash(email, tinyUrls, userName);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		User other = (User) obj;
		return Objects.equals(email, other.email) && Objects.equals(tinyUrls, other.tinyUrls)
				&& Objects.equals(userName, other.userName);
	}
}
