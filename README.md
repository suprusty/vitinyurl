

We can access the page http://localhost:8080/vitinyurl/apispecification




1st) If the original URL is present in the "GlobalTinyURL" database table and the JWT token is empty, the API returns the appropriate short URL.

 2nd) The API creates new data in "GlobalTinyURL" and returns the matching short URL if the original URL doesn't exist in the database table "GlobalTinyURL" and the JWT token is empty.

3rd) The API retrieves the username and email from the JWT token if it is not empty and uses the username to locate the user in the database "NewUser" using the email. The API raises a JWTTokenNotFoundException exception if the user cannot be located.

    Next, the API checks if there is User data in the database table "User". If the "User" data is not found, the API creates a new User data and sets its email and username fields to the values fetched from the JWT token.

    If the database table "GlobalTinyURL" is not found in the database, the API creates a new data "GlobalTinyURL".

    The API creates new TinyURL data.

    The API adds the newly created TinyURL data to the User table, saves the User data to the database by calling the save and finally returns the short URL of the newly created TinyURL.













VI tinyurl User Creation API

1st) We can generate the short URL without creating a user account.
instance 1) If the JWT token is empty and the original URL is present in the "GlobalTinyURL" database table, the API delivers the appropriate short URL.
Case 2) If the original URL does not exist in the database table "GlobalTinyURL" and the JWT token is empty, the API adds new data to "GlobalTinyURL" and returns the appropriate short URL.
 

Response is {
  "id": 1,
  "originalUrl": "https://www.nytimes.com/2023/04/14/magazine/courts-power-government.html?utm_source=pocket-newtab-intl-en",
  "shortUrl": "http://localhost:8080/vitinyurl/shortenurl/CNkYA4Vx",
  "shortenedCount": 2,
  "accessCount": 1,
  "createdTime": "2023-04-18T05:37:20.000+00:00",
  "lastModifiedTime": "2023-04-18T05:38:12.288+00:00"
}


We have a validation to detect faulty JWT tokens..

2nd)We can create the user account by providing Username,email and password.
We can verify whether the user account has previously been created. 

Response : Dummy jwtToken -sushil+skp.hpiso@gmail.com

In order for the Url to be assigned to the newly formed user, we can utilise generate token as part of the above mentioned payload. 

3rd) We can use generated shortened URL to redirection. 
![image](https://user-images.githubusercontent.com/29355409/232682962-cf105830-756a-45eb-ad38-8c0be9fa5096.png)
