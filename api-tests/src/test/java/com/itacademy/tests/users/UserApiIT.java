package com.itacademy.tests.users;

import com.itacademy.tests.utils.ApiClientUtil;
import com.softserveinc.ita.homeproject.blog.ApiException;
import com.softserveinc.ita.homeproject.blog.client.api.UsersApi;
import com.softserveinc.ita.homeproject.blog.client.model.Post;
import com.softserveinc.ita.homeproject.blog.client.model.Role;
import com.softserveinc.ita.homeproject.blog.client.model.User;
import org.apache.commons.lang3.RandomStringUtils;
import org.junit.jupiter.api.Test;

import javax.ws.rs.core.Response;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Objects;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatExceptionOfType;
import static org.junit.jupiter.api.Assertions.*;


class UserApiIT {

    private final UsersApi userApi = new UsersApi(ApiClientUtil.getClient());
    private final UsersApi unauthorizedUserApi = new UsersApi(ApiClientUtil.getUnauthorizedClient());


    @Test
    void getUsers() {
        saveListUser();

        List<User> actualListUsers = userApi.getUsers(
                null,
                null,
                null,
                1,
                10
                );

        assertThat(actualListUsers).isNotEmpty();
    }
    @Test
    void getUsersById() {
        User expectedUser = userApi.createUser(createTestUser());
        List<User> actualListUsers = userApi.getUsers(
                expectedUser.getId(),
                null,
                null,
                1,
                10
        );
        actualListUsers.forEach(readUser -> assertThat(readUser.getId()).isEqualTo(expectedUser.getId()));
    }

    @Test
    void getUsersByName() {
        User expectedUser = userApi.createUser(createTestUser());
        List<User> actualListUsers = userApi.getUsers(
                null,
                expectedUser.getName(),
                null,
                1,
                10
        );

        actualListUsers.forEach(readUser -> assertThat(readUser.getName()).isEqualTo(expectedUser.getName()));
    }

    @Test
    void getAllUsersAscSortByIdTest() throws ApiException {
        saveListUser();

        List<User> actualListUsers = userApi.getUsers(
                null,
                null,
                "id",
                1,
                10
        );

        assertThat(actualListUsers).isSortedAccordingTo(Comparator.comparing(u -> Objects
                .requireNonNull(u.getId())));
    }
    @Test
    void getAllUsersDescSortByFirstNameTest() throws ApiException {
        saveListUser();

        List<User> actualListUsers = userApi.getUsers(
                null,
                null,
                "-name",
                1,
                10
        );

        assertThat(actualListUsers).isSortedAccordingTo((u1, u2) -> Objects
                .requireNonNull(u2.getName()).compareToIgnoreCase(Objects.requireNonNull(u1.getName())));
    }



    @org.junit.jupiter.api.Test
    void getUserRole() {
        User user = userApi.createUser(createTestUser());
        Role role = userApi.getUserRole(user.getId());
        assertUserRole(user.getRole(), role);
    }

    @org.junit.jupiter.api.Test
    void updateUserRole() {
        User user = userApi.createUser(createTestUser());
        Role provided = new Role().name(Role.NameEnum.MODERATOR);
        Role updated = userApi.updateUserRole(user.getId(), provided);
        assertUserRole(user.getRole(), provided, updated);
    }

    @Test
    void createUserTest() throws ApiException {
        User expectedUser = createTestUser();
        User user = userApi.createUser(expectedUser);
        assertUser(expectedUser, user);
    }

    @Test
    void getUserTest() throws ApiException {
        User expectedUser = createTestUser();
        User user = userApi.getUser(userApi.createUser(expectedUser).getId());
        assertUser(expectedUser, user);
    }

    @Test
    void updateUserTest() throws ApiException {
        User savedUser = userApi
                .createUser(createTestUser());
        User updateUser = new User()
                .email(savedUser.getEmail())
                .firstName("updatedFirstName")
                .lastName("updatedLastName")
                .name(savedUser.getName());

        User updated = userApi.updateUser(savedUser.getId(), updateUser);
        assertUser(savedUser, updateUser, updated);
    }

    @Test
    void removeUserTest() throws ApiException {
        User expectedUser = userApi.createUser(createTestUser());
        userApi.removeUser(expectedUser.getId());

        List<User> actualUsersList = userApi.getUsers(
                expectedUser.getId()
                , expectedUser.getName()
                , "-id"
                , 1
                , 10);

        assertFalse(actualUsersList.contains(expectedUser));
        assertThatExceptionOfType(ApiException.class)
                .isThrownBy(() -> userApi.getUser(expectedUser.getId()));
    }

    @Test
    void unauthorizedRequestTest() {
        User expectedUser = userApi.createUser(createTestUser());
        ApiException exception = assertThrows(ApiException.class,
                () -> unauthorizedUserApi.getUserWithHttpInfo(expectedUser.getId()));
        assertEquals(Response.Status.UNAUTHORIZED.getStatusCode(), exception.getCode());
    }

    @Test
    void createUserInvalidEmailTest() {
        User createUserInvalidEmail = new User()
                .name(RandomStringUtils.randomAlphabetic(5).concat("_test"))
                .lastName("lastName")
                .password("passworD321")
                .email("email.com");

        assertThatExceptionOfType(ApiException.class)
                .isThrownBy(() -> userApi.createUser(createUserInvalidEmail))
                .matches(exception -> exception.getCode() == 400);
    }

    @Test
    void createUserInvalidPasswordTest() {
        User createUserInvalidPassword = new User()
                .name(RandomStringUtils.randomAlphabetic(5).concat("_test"))
                .lastName("lastName")
                .password("some password")
                .email("email@example.com");

        assertThatExceptionOfType(ApiException.class)
                .isThrownBy(() -> userApi.createUser(createUserInvalidPassword))
                .matches(exception -> exception.getCode() == 400);
    }

    private User createTestUser() {
        return new User()
                .name(RandomStringUtils.randomAlphabetic(5).concat("_test"))
                .firstName("firstName")
                .lastName("lastName")
                .password("passworD321")
                .email(RandomStringUtils.randomAlphabetic(5).concat("@example.com"));
    }

    private void saveListUser() throws ApiException {
        List<User> list = createUsersList();
        for (User cu : list) {
            userApi.createUser(cu);
        }
    }

    private List<User> createUsersList() {
        List<User> list = new ArrayList<>();
        list.add(createTestUser());
        list.add(createTestUser());
        list.add(createTestUser());
        return list;
    }
    private void assertUserRole(Role expected, Role actual) {
        assertNotNull(expected);
        assertEquals(expected.getName(), actual.getName());
    }
    private void assertUserRole(Role saved, Role update, Role updated) {
        assertNotNull(updated);
        assertNotEquals(saved, updated);
        assertEquals(update.getName(), updated.getName());
    }

    private void assertUser(User expected, User actual) {
        assertNotNull(expected);
        assertEquals(expected.getFirstName(), actual.getFirstName());
        assertEquals(expected.getLastName(), actual.getLastName());
        assertEquals(expected.getEmail(), actual.getEmail());
    }

    private void assertUser(User saved, User update, User updated) {
        assertNotNull(updated);
        assertNotEquals(saved, updated);
        assertEquals(update.getFirstName(), updated.getFirstName());
        assertEquals(update.getLastName(), updated.getLastName());
    }
}
