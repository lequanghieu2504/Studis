package com.studis.service.dao;

import com.studis.dao.UserRetrievalDAO;
import com.studis.exception.serviceException.ServiceException;
import com.studis.model.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Service class responsible for retrieving user data from the database.
 * This class provides methods for querying users by various fields like ID, name, email, and checking user existence.
 */
@Service
public class UserRetrievalService extends BaseDAOService {

    private final UserRetrievalDAO udao;  // DAO for retrieving user data

    /**
     * Constructs the UserRetrievalService with the DAO dependency injected via Spring.
     * @param udao The DAO responsible for retrieving user-related data from the database
     */
    @Autowired
    public UserRetrievalService(UserRetrievalDAO udao) {
        this.udao = udao;
    }

    /**
     * Retrieves a user by their ID.
     * @param id The ID of the user to be retrieved
     * @return The User entity corresponding to the given ID
     * @throws ServiceException if an error occurs while retrieving the user
     */
    public User handleRetrieve(int id) {
        try {
            return udao.retrieve(id);  // Calls the DAO method to retrieve the user by ID
        } catch (Exception e) {
            throw new ServiceException("Error occurred while retrieving user by ID: " + id, e);
        }
    }

    /**
     * Retrieves a user by their name or email (whichever matches).
     * @param nameOrEmail The username or email of the user to be retrieved
     * @return The User entity corresponding to the given name or email
     * @throws ServiceException if an error occurs while retrieving the user
     */
    public User handleRetrieve(String nameOrEmail) {
        try {
            return udao.retrieve(nameOrEmail);  // Calls the DAO method to retrieve the user by name or email
        } catch (Exception e) {
            throw new ServiceException("Error occurred while retrieving user by name or email: " + nameOrEmail, e);
        }
    }

    /**
     * Retrieves a user by any given field and its corresponding value.
     * @param field The field to query (e.g., "user_name", "user_email")
     * @param value The value of the field to search for
     * @return The User entity corresponding to the specified field and value
     * @throws ServiceException if an error occurs while retrieving the user
     */
    public User handleRetrieve(String field, String value) {
        try {
            return udao.retrieve(field, value);  // Calls the DAO method to retrieve the user by a specific field
        } catch (Exception e) {
            throw new ServiceException("Error occurred while retrieving user by " + field + ": " + value, e);
        }
    }

    /**
     * Retrieves the ID of a user by a specific field and value.
     * @param field The field to query (e.g., "user_name", "user_email")
     * @param value The value of the field to search for
     * @param <T> The type of the field value (e.g., String, Integer)
     * @return The ID of the user corresponding to the given field and value
     * @throws ServiceException if an error occurs while retrieving the user ID
     */
    public <T> int handleRetrieveId(String field, T value) {
        try {
            User user = udao.retrieve(field, value);  // Retrieves the user by field and value
            return user.getId();  // Returns the user's ID
        } catch (Exception e) {
            throw new ServiceException("Error occurred while retrieving user ID by " + field + ": " + value, e);
        }
    }

    /**
     * Checks whether a user exists by a given field and value.
     * @param field The field to query (e.g., "user_name", "user_email")
     * @param value The value of the field to check for existence
     * @param <T> The type of the field value
     * @return True if the user exists, otherwise false
     * @throws ServiceException if an error occurs while checking for user existence
     */
    public <T> boolean handleExist(String field, T value) {
        try {
            return udao.exist(field, value);  // Calls the DAO method to check if the user exists
        } catch (Exception e) {
            throw new ServiceException("Error occurred while checking if user exists by " + field + ": " + value, e);
        }
    }
}
