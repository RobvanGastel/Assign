package com.robvangastel.assign;

import com.robvangastel.assign.domain.*;
import com.robvangastel.assign.services.PostService;
import com.robvangastel.assign.services.ReplyService;
import com.robvangastel.assign.services.SchoolService;
import com.robvangastel.assign.services.UserService;
import org.jboss.logging.Logger;

import javax.annotation.PostConstruct;
import javax.ejb.Singleton;
import javax.ejb.Startup;
import javax.inject.Inject;

/**
 * @author Rob van Gastel
 */

@Singleton
@Startup
public class StartUp {

    private static final Logger LOG = Logger.getLogger(StartUp.class.getSimpleName());

    @Inject
    private UserService userService;

    @Inject
    private PostService postService;

    @Inject
    private SchoolService schoolService;

    @Inject
    private ReplyService replyService;

    @PostConstruct
    public void initData() {
        try {
            // Creating schools
            schoolService.create(new School("Fontys Tilburg"));
            School fontys = schoolService.findById(1L);
            Study ict = schoolService.addStudy(fontys, "ICT");
            Study pabo = schoolService.addStudy(fontys, "PABO");

            schoolService.create(new School("Avans Hogeschool Breda"));
            School avans = schoolService.findById(4L);
            Study informatica = schoolService.addStudy(avans, "ICT");

            // Fontys ICT studie
            userService.create(new User("admin@mail.nl", "admin", "Jan Jansen", ict, "Media vormgeving", new SocialLink("JanPieterJeWeet", "FacebookenMeneer", "06380013")));
            userService.create(new User("max@mail.nl", "max", "Max Wammes", ict, "Interactief Vormgeving 3IVV", new SocialLink("MaxWammelsWieeehoo", "MeneerDePeer", "06123111")));

            // Fontys PABO studie
            userService.create(new User("kees@mail.nl", "kees", "Kees Alders", pabo, "Geschiedenis opleiding", new SocialLink("Keeskee", "Seesjeee", "0612312351")));

            // Avans ICT studie
            userService.create(new User("pieter@mail.nl", "pieter", "Pieter Nieuwkoop", informatica, "BIM", new SocialLink("Pietertjee", "Pietertjee", "06123511")));

            // Social network without facebook and phonenumber
            SocialLink social = new SocialLink();
            social.setTwitter("TwitterKoning");
            userService.create(new User("rob@mail.nl", "rob", "Rob van Gastel", ict, "Software Engineering 3SED", social));

            User admin = userService.findById(6L);
            admin.setRole(Role.ADMIN);
            userService.update(admin);

            User max = userService.findById(9L);
            User kees = userService.findById(12L);
            User pieter = userService.findById(15L);
            User rob = userService.findById(18L);

            // Creating posts
            postService.create(new Post(max, "Hoe werkt centreren in CSS?", "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Donec #eleifend ligula enim, #in tempor sem interdum quis. Sed bibendum ex neque, et dapibus nulla ullamcorper a."));
            postService.create(new Post(max, "Hoe zet ik Java EE7 op?", "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Donec eleifend ligula enim, in tempor sem interdum quis. Sed bibendum ex neque, et dapibus nulla ullamcorper a."));
            postService.create(new Post(rob, "Hoe deploy ik Jenkins op een raspberry pi?", "Lorem ipsum #dolor sit amet, consectetur adipiscing elit. Donec #eleifend #ligula enim, in tempor sem interdum quis. Sed bibendum ex neque, et dapibus nulla ullamcorper a."));
            postService.create(new Post(rob, "Hoe werk ik met Interfaces?", "Lorem ipsum dolor sit #amet, consectetur adipiscing elit. Donec eleifend ligula enim, in tempor sem interdum quis. Sed bibendum ex neque, et dapibus nulla ullamcorper a."));
            postService.create(new Post(max, "Hoe kies ik mijn Kular?", "Lorem ipsum dolor sit amet, #consectetur adipiscing elit. Donec eleifend #ligula enim, in tempor sem interdum quis. Sed #bibendum ex neque, et dapibus nulla ullamcorper a."));
            postService.create(new Post(pieter, "Hoe maak ik een Drupal website?", "Lorem ipsum dolor sit amet, #consectetur adipiscing elit. Donec eleifend #ligula enim, in tempor sem interdum quis. Sed #bibendum ex neque, et dapibus nulla ullamcorper a."));
            postService.create(new Post(pieter, "Hoe optimaliseer ik mijn SEO?", "Lorem ipsum dolor sit amet, #consectetur adipiscing elit. Donec eleifend #ligula enim, in tempor sem interdum quis. Sed #bibendum ex neque, et dapibus nulla ullamcorper a."));
            postService.create(new Post(kees, "Hoe krijg ik die kinderen aan het werk?", "Lorem ipsum dolor sit amet, #consectetur adipiscing elit. Donec eleifend #ligula enim, in tempor sem interdum quis. Sed #bibendum ex neque, et dapibus nulla ullamcorper a."));
            postService.create(new Post(kees, "Hoe leg ik de jager/verzamelaar cultuur uit?", "Lorem ipsum dolor sit amet, #consectetur adipiscing elit. Donec eleifend #ligula enim, in tempor sem interdum quis. Sed #bibendum ex neque, et dapibus nulla ullamcorper a."));

            Post post_max_2 = postService.findById(21L);
            Post post_max_3 = postService.findById(22L);

            Post post_rob_1 = postService.findById(23L);
            Post post_rob_2 = postService.findById(24L);

            // Creating replies
            replyService.create(max, post_rob_1);
            replyService.create(max, post_rob_2);

            replyService.create(rob, post_max_2);
            replyService.create(rob, post_max_3);

            LOG.info("Startup successfully executed.");

        } catch (Exception e) {
            LOG.warn("An exception occurred : " + e.getMessage());
        }
    }
}
