package com.studis.model.entity;

import java.sql.Timestamp;
import java.sql.Date;

/**
 * Represents a User entity in the system.
 * This class holds information about the user such as name, email, hashed password, 
 * salt for password hashing, the creation date of the user, and the timestamp of the user's recent access.
 */
public class User {

    private int id = -1;                 // User's unique identifier, default value is -1 for an uninitialized user
    private String name;                 // User's name
    private String email;                // User's email address
    private String hashedPassword;       // User's hashed password (for security purposes)
    private String salt;                 // Salt used for hashing the password
    private final Date createDate;      // The date the user was created (immutable)
    private Timestamp recentAccess;     // Timestamp of the user's most recent access to the system

    /**
     * Constructor to initialize a User with name, email, hashed password, and salt.
     * The creation date and recent access timestamp are set to the current system time.
     *
     * @param name The user's name.
     * @param email The user's email address.
     * @param hashedPassword The user's hashed password.
     * @param salt The salt used for hashing the password.
     */
    public User(String name, String email, String hashedPassword, String salt) {
        this.name = name;
        this.email = email;
        this.hashedPassword = hashedPassword;
        this.salt = salt;
        this.createDate = new Date(System.currentTimeMillis()); // Sets the current date as the creation date
        this.recentAccess = new Timestamp(System.currentTimeMillis()); // Sets the current timestamp as recent access
    }

    /**
     * Constructor to initialize a User with an id, name, email, hashed password, salt, and creation date.
     * The recent access timestamp is set to the current system time.
     *
     * @param id The user's unique identifier.
     * @param name The user's name.
     * @param email The user's email address.
     * @param hashedPassword The user's hashed password.
     * @param salt The salt used for hashing the password.
     * @param createDate The date the user was created.
     */
    public User(int id, String name, String email, String hashedPassword, String salt, Date createDate) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.hashedPassword = hashedPassword;
        this.salt = salt;
        this.createDate = createDate;
        this.recentAccess = new Timestamp(System.currentTimeMillis()); // Sets the current timestamp as recent access
    }

    /**
     * Constructor to initialize a User with just name and email (for cases when other information is not yet available).
     * Other fields are set to null.
     *
     * @param name The user's name.
     * @param email The user's email address.
     */
    public User(String name, String email) {
        this.id = -1;
        this.name = name;
        this.email = email;
        this.hashedPassword = null;
        this.salt = null;
        this.createDate = null;
        this.recentAccess = null;
    }

    /**
     * Default constructor for a User object with all fields set to null or default values.
     */
    public User() {
        this.id = -1;
        this.name = null;
        this.email = null;
        this.hashedPassword = null;
        this.salt = null;
        this.createDate = null;
        this.recentAccess = null;
    }

    // Getters and setters for the User fields

    public int getId() {
        return id; // Returns the user's unique identifier
    }

    public String getName() {
        return name; // Returns the user's name
    }

    public String getEmail() {
        return email; // Returns the user's email address
    }

    public String getHashedPassword() {
        return hashedPassword; // Returns the user's hashed password
    }

    public String getSalt() {
        return salt; // Returns the salt used for hashing the password
    }

    public Date getCreateDate() {
        return createDate; // Returns the user's creation date
    }

    public Timestamp getRecentAccess() {
        return recentAccess; // Returns the timestamp of the user's most recent access
    }

    // Setters for the User fields

    public void setName(String name) {
        this.name = name; // Sets the user's name
    }

    public void setEmail(String email) {
        this.email = email; // Sets the user's email address
    }

    public void setHashedPassword(String hashedPassword) {
        this.hashedPassword = hashedPassword; // Sets the user's hashed password
    }

    public void setSalt(String salt) {
        this.salt = salt; // Sets the salt used for hashing the password
    }

    public void setRecentAccess(Timestamp recentAccess) {
        this.recentAccess = recentAccess; // Sets the timestamp of the user's most recent access
    }

    /**
     * Returns a string representation of the User object.
     * This is useful for debugging and logging the state of a User object.
     *
     * @return A string representing the user's information.
     */
    @Override
    public String toString() {
        return "User{"
                + "id=" + id
                + ", name='" + name + '\'' 
                + ", email='" + email + '\'' 
                + ", hashedPassword='" + hashedPassword + '\'' 
                + ", salt='" + salt + '\'' 
                + ", createDate=" + createDate 
                + ", recentAccess=" + recentAccess 
                + '}';
    }

    /**
     * Checks if the User object is in its default state.
     * This is useful for checking if a User object has been properly initialized.
     *
     * @return True if the User object is in its default state (id = -1, other fields are null).
     */
    public boolean isDefault() {
        return id == -1
                && hashedPassword == null
                && salt == null
                && createDate == null
                && recentAccess == null;
    }

    /**
     * Updates the user's name or email based on the provided field name and new value.
     * This method allows for selective updates to the User's attributes.
     *
     * @param field The field to update ("user_name" or "user_email").
     * @param value The new value for the field.
     */
    public void updateNameOrEmail(String field, String value) {
        if (field.equals("user_name")) {
            this.name = value; // Updates the user's name
        } else if (field.equals("user_email")) {
            this.email = value; // Updates the user's email address
        }
    }
}
