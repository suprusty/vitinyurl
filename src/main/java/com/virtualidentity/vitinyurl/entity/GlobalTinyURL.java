package com.virtualidentity.vitinyurl.entity;

import java.util.Date;
import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

@Entity
@Table(name = "GlobalTinyURL")
public class GlobalTinyURL {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(name = "original_Url")
	private String originalUrl;

	@Column(name = "short_Url")
	private String shortUrl;
	
	@Column(name = "shortened_Count")
	private int shortenedCount;

	public int getShortenedCount() {
		return shortenedCount;
	}

	public void setShortenedCount(int shortenedCount) {
		this.shortenedCount = shortenedCount;
	}

	public int getAccessCount() {
		return accessCount;
	}

	public void setAccessCount(int accessCount) {
		this.accessCount = accessCount;
	}

	@Column(name = "access_Count")
	private int accessCount;

	@CreatedDate
	@Column(name = "created_date")
	private Date createdTime;

	@LastModifiedDate
	@Column(name = "last_modified_date")
	private Date lastModifiedTime;

	public Long getId() {
		return id;
	}

	public Date getCreatedTime() {
		return createdTime;
	}

	public void setCreatedTime(Date createdTime) {
		this.createdTime = createdTime;
	}

	public Date getLastModifiedTime() {
		return lastModifiedTime;
	}

	public void setLastModifiedTime(Date lastModifiedTime) {
		this.lastModifiedTime = lastModifiedTime;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getOriginalUrl() {
		return originalUrl;
	}

	public void setOriginalUrl(String originalUrl) {
		this.originalUrl = originalUrl;
	}

	public String getShortUrl() {
		return shortUrl;
	}

	public void setShortUrl(String shortUrl) {
		this.shortUrl = shortUrl;
	}

	public void incrementShortenedCount() {
		this.shortenedCount++;
	}

	public void incrementAccessCount() {
		this.accessCount++;
	}

	@Override
	public int hashCode() {
		return Objects.hash(originalUrl, shortUrl);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		GlobalTinyURL other = (GlobalTinyURL) obj;
		return Objects.equals(originalUrl, other.originalUrl) && Objects.equals(shortUrl, other.shortUrl);
	}

	@Override
	public String toString() {
		return "TinyURL [id=" + id + ", originalUrl=" + originalUrl + ", shortUrl=" + shortUrl + ", createdTime="
				+ createdTime + ", lastModifiedTime=" + lastModifiedTime + "]";
	}
}
