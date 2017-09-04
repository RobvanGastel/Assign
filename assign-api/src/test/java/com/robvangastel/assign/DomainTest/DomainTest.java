package com.robvangastel.assign.DomainTest;

import com.robvangastel.assign.domain.Post;
import com.robvangastel.assign.domain.User;
import org.junit.Assert;
import org.junit.Test;

/**
 * @author Rob van Gastel
 */
public class DomainTest {

    // EXAMPLE DESCRIPTION
    /***
     * TEST CASE METHOD
     *
     * Case: STATE TEST CASE
     *
     * Method:
     * STATE METHOD DESCRIPTION
     */

    /***
     * public Post(User user, String title, String description)
     *
     * Case: On creating a post object add 2 hashtags to the message.
     */
    @Test
    public void PostTest1() {
        User user = new User("user@email.nl", "password", "name");
        Post post = new Post(user, "This is a test!", "This #message contains two #hashtags");

        Assert.assertEquals(post.getTags().size(), 2);
    }

    /***
     * public Post(User user, String title, String description)
     *
     * Case: On creating a post object add 0 hashtags to the message.
     */
    @Test
    public void PostTest2() {
        User user = new User("user@email.nl", "password", "name");
        Post post = new Post(user, "This is a test!", "This message contains zero hashtags");

        Assert.assertEquals(post.getTags().size(), 0);
    }

    /***
     * public Post(User user, String title, String description)
     *
     * Case: On creating a post object add 0 hashtags to the message with a trailing
     * hashtag.
     */
    @Test
    public void PostTest3() {
        User user = new User("user@email.nl", "password", "name");
        Post post = new Post(user, "This is a test!", "This message # contains zero hashtags");

        Assert.assertEquals(post.getTags().size(), 0);
    }
}
