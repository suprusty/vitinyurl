package com.virtualidentity.vitinyurl.model;

import java.util.Objects;

public class TinyURLDTO {

	private String originalUrl;
	private String jwtToken;

	public String getOriginalUrl() {
		return originalUrl;
	}

	public void setOriginalUrl(String originalUrl) {
		this.originalUrl = originalUrl;
	}

	public String getJwtToken() {
		return jwtToken;
	}

	public void setJwtToken(String jwtToken) {
		this.jwtToken = jwtToken;
	}
	@Override
	public int hashCode() {
		return Objects.hash(jwtToken, originalUrl);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		TinyURLDTO other = (TinyURLDTO) obj;
		return Objects.equals(jwtToken, other.jwtToken) && Objects.equals(originalUrl, other.originalUrl);
	}

}
