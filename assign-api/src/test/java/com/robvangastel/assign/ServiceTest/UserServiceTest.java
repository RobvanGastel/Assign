package com.robvangastel.assign.ServiceTest;

import com.robvangastel.assign.dao.IUserDao;
import com.robvangastel.assign.domain.User;
import com.robvangastel.assign.exception.UserException;
import com.robvangastel.assign.services.UserService;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import static org.mockito.Mockito.*;
import static org.mockito.MockitoAnnotations.initMocks;

/**
 * @author Rob van Gastel
 */
public class UserServiceTest {

    // EXAMPLE DESCRIPTION
    /***
     * TEST CASE METHOD
     *
     * Case: STATE TEST CASE
     *
     * Method:
     * STATE METHOD DESCRIPTION
     */

    @InjectMocks
    private UserService userService;

    @Mock
    private IUserDao userDao;

    @Before
    public void before() {
        initMocks(this);
    }

    /***
     * public void create(User entity) throws Exception
     *
     * Case: Create a user with a existing email.
     *
     */
    @Test(expected = UserException.class)
    public void createTest2() throws Exception {
        User user = new User("user@email.nl", "password", "user");

        when(userDao.findByEmail(user.getEmail())).thenReturn(user);
        userService.create(user);
        verify(userDao, never()).create(user);
    }
}
