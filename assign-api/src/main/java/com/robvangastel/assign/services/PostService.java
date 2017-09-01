package com.robvangastel.assign.services;

import com.robvangastel.assign.CodeGenerator;
import com.robvangastel.assign.dao.IPostDao;
import com.robvangastel.assign.domain.Post;
import com.robvangastel.assign.domain.User;
import org.jboss.logging.Logger;

import javax.ejb.Stateless;
import javax.inject.Inject;
import java.io.Serializable;
import java.util.List;

/**
 * @author Rob van Gastel
 * TODO Handle creation Post
 */

@Stateless
public class PostService implements Serializable {

    private static final Logger LOG = Logger.getLogger(PostService.class.getSimpleName());

    @Inject
    private IPostDao postDao;

    public Post findById(long id) {
        return postDao.findById(id);
    }

    public Post findByUrl(String url) {
        return postDao.findByUrl(url);
    }

    public List<Post> findAll(int start, int size) { return postDao.findAll(start, size); }

    public List<Post> findAll(User user, int start, int size) {
        return postDao.findAll(user, start, size);
    }

    public List<Post> findByQuery(User user, String query, int start, int size) {
        return postDao.findByQuery(user, query, start, size);
    }

    public List<Post> findByUser(long id, int start, int size) {
        return postDao.findByUser(id, start, size);
    }

    public void delete(long id) throws Exception {
        postDao.deleteById(id);
    }

    public void create(Post entity) throws Exception {
        // This generates the URL which has the total number of possiblities of
        // 218.340.105.584.896 different strings.
        String code = CodeGenerator.getInstance().getCode(8);
        entity.setUrl(code);
        postDao.create(entity);

        LOG.info("Post created with title: " + entity.getTitle());
    }

    public Post setDone(Post entity, boolean done) {
        entity.setDone(done);
        return postDao.update(entity);
    }

}
