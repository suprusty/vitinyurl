package com.virtualidentity.vitinyurl.utils;

public interface VITinyURLConsants {
	String USER_CREATE = "The new User's creation is made possible using API. If the user previously created it, they cannot recreate it.";
	String TINYURL_CREATE="The GlobalTinyURL database is searched to see if the original URL has already been shortened; if not, a new short URL is created in the database GlobalTinyURL, along with a new TinyURL object that is associated to the User object. The API also deals with situations where a valid JWT token is supplied, and it raises an exception if either the token is erroneous or the user cannot be located in the database.";	
	String DOMAIN = "http://localhost:8080/vitinyurl/shortenurl/";
	String ALGORITHM = "SHA-256";
	String CHARSET = "UTF-8";
	int SHORT_URL_LENGTH = 8;
}
