1st) If the original URL is present in the "GlobalTinyURL" database table and the JWT token is empty, the API returns the appropriate short URL.

 2nd) The API creates new data in "GlobalTinyURL" and returns the matching short URL if the original URL doesn't exist in the database table "GlobalTinyURL" and the JWT token is empty.

3rd) The API retrieves the username and email from the JWT token if it is not empty and uses the username to locate the user in the database "NewUser" using the email. The API raises a JWTTokenNotFoundException exception if the user cannot be located.

    Next, the API checks if there is User data in the database table "User". If the "User" data is not found, the API creates a new User data and sets its email and username fields to the values fetched from the JWT token.

    If the database table "GlobalTinyURL" is not found in the database, the API creates a new data "GlobalTinyURL".

    The API creates new TinyURL data.

    The API adds the newly created TinyURL data to the User table, saves the User data to the database by calling the save and finally returns the short URL of the newly created TinyURL.
