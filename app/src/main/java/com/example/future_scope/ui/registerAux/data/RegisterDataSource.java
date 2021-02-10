package com.example.future_scope.ui.registerAux.data;

import com.example.future_scope.model.User;

import java.io.IOException;

/**
 * Class that handles authentication w/ login credentials and retrieves user information.
 */
public class RegisterDataSource {

    public Result<User> login(String username, String password) {

        try {
            // TODO: handle loggedInUser authentication
            User fakeUser =
                    new User(
                            "Jane Doe");
            return new Result.Success<>(fakeUser);
        } catch (Exception e) {
            return new Result.Error(new IOException("Error logging in", e));
        }
    }

    public void logout() {
        // TODO: revoke authentication
    }
}