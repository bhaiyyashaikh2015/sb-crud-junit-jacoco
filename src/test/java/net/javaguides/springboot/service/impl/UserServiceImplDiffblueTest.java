package net.javaguides.springboot.service.impl;

import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.ArgumentMatchers.isA;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import net.javaguides.springboot.entity.User;
import net.javaguides.springboot.repository.UserRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.aot.DisabledInAotMode;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ContextConfiguration(classes = {UserServiceImpl.class})
@ExtendWith(SpringExtension.class)
@DisabledInAotMode
class UserServiceImplDiffblueTest {
    @MockBean
    private UserRepository userRepository;

    @Autowired
    private UserServiceImpl userServiceImpl;

    /**
     * Method under test: {@link UserServiceImpl#createUser(User)}
     */
    @Test
    void testCreateUser() {
        // Arrange
        User user = new User();
        user.setEmail("jane.doe@example.org");
        user.setFirstName("Jane");
        user.setId(1L);
        user.setLastName("Doe");
        when(userRepository.save(Mockito.<User>any())).thenReturn(user);

        User user2 = new User();
        user2.setEmail("jane.doe@example.org");
        user2.setFirstName("Jane");
        user2.setId(1L);
        user2.setLastName("Doe");

        // Act
        User actualCreateUserResult = userServiceImpl.createUser(user2);

        // Assert
        verify(userRepository).save(isA(User.class));
        assertSame(user, actualCreateUserResult);
    }

    /**
     * Method under test: {@link UserServiceImpl#getUserById(Long)}
     */
    @Test
    void testGetUserById() {
        // Arrange
        User user = new User();
        user.setEmail("jane.doe@example.org");
        user.setFirstName("Jane");
        user.setId(1L);
        user.setLastName("Doe");
        Optional<User> ofResult = Optional.of(user);
        when(userRepository.findById(Mockito.<Long>any())).thenReturn(ofResult);

        // Act
        User actualUserById = userServiceImpl.getUserById(1L);

        // Assert
        verify(userRepository).findById(eq(1L));
        assertSame(user, actualUserById);
    }

    /**
     * Method under test: {@link UserServiceImpl#getAllUsers()}
     */
    @Test
    void testGetAllUsers() {
        // Arrange
        ArrayList<User> userList = new ArrayList<>();
        when(userRepository.findAll()).thenReturn(userList);

        // Act
        List<User> actualAllUsers = userServiceImpl.getAllUsers();

        // Assert
        verify(userRepository).findAll();
        assertTrue(actualAllUsers.isEmpty());
        assertSame(userList, actualAllUsers);
    }

    /**
     * Method under test: {@link UserServiceImpl#updateUser(User)}
     */
    @Test
    void testUpdateUser() {
        // Arrange
        User user = new User();
        user.setEmail("jane.doe@example.org");
        user.setFirstName("Jane");
        user.setId(1L);
        user.setLastName("Doe");
        Optional<User> ofResult = Optional.of(user);

        User user2 = new User();
        user2.setEmail("jane.doe@example.org");
        user2.setFirstName("Jane");
        user2.setId(1L);
        user2.setLastName("Doe");
        when(userRepository.save(Mockito.<User>any())).thenReturn(user2);
        when(userRepository.findById(Mockito.<Long>any())).thenReturn(ofResult);

        User user3 = new User();
        user3.setEmail("jane.doe@example.org");
        user3.setFirstName("Jane");
        user3.setId(1L);
        user3.setLastName("Doe");

        // Act
        User actualUpdateUserResult = userServiceImpl.updateUser(user3);

        // Assert
        verify(userRepository).findById(eq(1L));
        verify(userRepository).save(isA(User.class));
        assertSame(user2, actualUpdateUserResult);
    }

    /**
     * Method under test: {@link UserServiceImpl#deleteUser(Long)}
     */
    @Test
    void testDeleteUser() {
        // Arrange
        doNothing().when(userRepository).deleteById(Mockito.<Long>any());

        // Act
        userServiceImpl.deleteUser(1L);

        // Assert that nothing has changed
        verify(userRepository).deleteById(eq(1L));
        assertTrue(userServiceImpl.getAllUsers().isEmpty());
    }
}
