package com.robvangastel.assign.services;

import com.robvangastel.assign.domain.Post;
import com.robvangastel.assign.domain.Reply;
import com.robvangastel.assign.domain.User;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.ejb.Stateless;
import javax.inject.Inject;
import java.io.Serializable;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * @author Rob van Gastel
 */
@Stateless
public class OverviewService implements Serializable {

    @Inject
    private PostService postService;

    @Inject
    private ReplyService replyService;

    public List<Item> findByUser(long id, int start, int size) {

        List<Post> posts = postService.findByUser(id, 0, size);
        List<Reply> replies = replyService.findByUser(id, 0, size);
        List<Item> items = new ArrayList<>();

        for(Reply reply : replies) {
            items.add(new Item(reply.getId(), reply.getUser(),
                    reply.getUser().getName() + " wil helpen met: ",
                    reply.getPost().getTitle(), reply.getDateCreated()));
        }

        for(Post post : posts) {
            items.add(new Item(post.getId(), post.getUser(),
                    post.getTitle(), post.getDescription(),
                    post.getDateCreated()));
        }

        Collections.sort(items);

        if((items.size() -1) > (start + size)) {
            items.subList(start, (start + size));
        }

        return items;
    }

    @Data
    @EqualsAndHashCode
    @NoArgsConstructor
    public class Item implements Comparable<Item> {

        private Long id;
        private User user;
        private String title;
        private String description;
        private Timestamp dateCreated;

        public Item(long id, User user, String title, String description, Timestamp dateCreated) {
            this.id = id;
            this.user = user;
            this.title = title;
            this.description = description;
            this.dateCreated = dateCreated;
        }

        @Override
        public int compareTo(Item o) {
            return getDateCreated().compareTo(o.getDateCreated());
        }
    }
}
