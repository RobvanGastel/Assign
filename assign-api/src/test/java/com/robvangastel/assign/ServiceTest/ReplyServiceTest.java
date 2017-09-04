package com.robvangastel.assign.ServiceTest;

import com.robvangastel.assign.dao.IReplyDao;
import com.robvangastel.assign.domain.Post;
import com.robvangastel.assign.domain.Reply;
import com.robvangastel.assign.domain.User;
import com.robvangastel.assign.exception.ReplyException;
import com.robvangastel.assign.firebase.FirebaseService;
import com.robvangastel.assign.services.ReplyService;

import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import static org.mockito.Mockito.*;
import static org.mockito.MockitoAnnotations.initMocks;

/**
 * @author Rob van Gastel
 */
public class ReplyServiceTest {

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
    private ReplyService replyService;

    @Mock
    private FirebaseService firebaseService;

    @Mock
    private IReplyDao replyDao;

    @Before
    public void before() {
        initMocks(this);
    }

    /***
     * public void create(User user, Post post)
     *
     * Case: Create a Reply on when the reply already exists.
     *
     */
    @Test(expected=ReplyException.class)
    public void createTest1() {
        User user = new User("user@email.nl", "password", "name");
        Post post = new Post(user, "This is a test!", "This message # contains zero hashtags");

        when(replyDao.DidUserReply(user, post)).thenReturn(true);
        replyService.create(user, post);
        verify(replyDao, never()).create(new Reply(user, post));
    }

    /***
     * public void create(User user, Post post)
     *
     * Case: Create a Reply on when the reply doesn't exists yet.
     *
     */
    @Test
    public void createTest2() {
        User user = new User("user@email.nl", "password", "name");
        Post post = new Post(user, "This is a test!", "This message # contains zero hashtags");

        when(replyDao.DidUserReply(user, post)).thenReturn(false);
        replyService.create(user, post);

        verify(replyDao, atLeastOnce()).create(new Reply(user, post));
    }
}
