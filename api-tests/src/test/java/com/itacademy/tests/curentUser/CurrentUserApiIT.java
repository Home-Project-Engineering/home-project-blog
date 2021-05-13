package com.itacademy.tests.curentUser;

import com.itacademy.tests.utils.ApiClientUtil;
import com.softserveinc.ita.homeproject.blog.client.api.CurrentUserApi;
import com.softserveinc.ita.homeproject.blog.client.api.UsersApi;
import com.softserveinc.ita.homeproject.blog.client.model.Password;
import com.softserveinc.ita.homeproject.blog.client.model.User;
import org.apache.commons.lang3.RandomStringUtils;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;


class CurrentUserApiIT {

    private final CurrentUserApi currentUserApi = new CurrentUserApi(ApiClientUtil.getClient());
    private final UsersApi usersApi = new UsersApi(ApiClientUtil.getClient());

    @Test
    void getCurrentUser() {
        User user = currentUserApi.getCurrentUser();
        assertNotNull(user);
    }


    @Test
    void updateCurrentUser() {
        User savedUser = currentUserApi
                .getCurrentUser();
        User updateUser = new User()
                .email(savedUser.getEmail())
                .firstName("updatedFirstName")
                .lastName("updatedLastName")
                .name(savedUser.getName());

        User updated = currentUserApi.updateCurrentUser(updateUser);
        assertUser(savedUser, updateUser, updated);
    }

    @Test
    void updateCurrentUserPassword() {
        User expected = usersApi.createUser(createTestUser());
        CurrentUserApi currentUserApiForUpdatePassword = new CurrentUserApi(ApiClientUtil.getClient(expected.getEmail(), "passworD321"));
        String newPassword = "newPassworD321";
        currentUserApiForUpdatePassword.updateCurrentUserPassword(new Password().oldPassword("passworD321").newPassword(newPassword));
        currentUserApiForUpdatePassword.getApiClient().setPassword(newPassword);
        User actual = currentUserApiForUpdatePassword.getCurrentUser();
        assertUser(expected, actual);
    }

    private User createTestUser() {
        return new User()
                .name(RandomStringUtils.randomAlphabetic(5).concat("_test"))
                .firstName("firstName")
                .lastName("lastName")
                .password("passworD321")
                .email(RandomStringUtils.randomAlphabetic(5).concat("@example.com"));
    }

    private void assertUser(User saved, User update, User updated) {
        assertNotNull(updated);
        assertNotEquals(saved, updated);
        assertEquals(update.getFirstName(), updated.getFirstName());
        assertEquals(update.getLastName(), updated.getLastName());
    }

    private void assertUser(User expected, User actual) {
        assertNotNull(expected);
        assertEquals(expected.getName(), actual.getName());
        assertEquals(expected.getFirstName(), actual.getFirstName());
        assertEquals(expected.getLastName(), actual.getLastName());
        assertEquals(expected.getEmail(), actual.getEmail());
    }

}
