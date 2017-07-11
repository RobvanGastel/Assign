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

    private static final Logger LOG = Logger.getLogger(StartUp.class.getName());

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
            School fontys = schoolService.create(new School("Fontys Tilburg"));
            Study ict = schoolService.addStudy(fontys, "ICT");
            Study pabo = schoolService.addStudy(fontys, "PABO");

            School avans = schoolService.create(new School("Avans Hogeschool Breda"));
            Study informatica = schoolService.addStudy(avans, "ICT");

            // Fontys ICT studie
            User admin = userService.create(new User("admin@mail.nl", "admin", "Jan Pieter", ict, "Media vormgeving", new SocialLink("JanPieterJeWeet", "FacebookenMeneer", "06380013")));
            User max = userService.create(new User("max@mail.nl", "max", "Max Wammels", ict, "Technology", new SocialLink("MaxWammelsWieeehoo", "MeneerDePeer", "06123111")));

            // Fontys PABO studie
            User kees = userService.create(new User("kees@mail.nl", "kees", "Kees Chinees", pabo, "Geschiedenis opleiding", new SocialLink("Keeskee", "Seesjeee", "0612312351")));

            // Avans ICT studie
            User pieter = userService.create(new User("pieter@mail.nl", "pieter", "Pieter de Zwart", informatica, "BIM", new SocialLink("Pietertjee", "Pietertjee", "06123511")));

            // Social network without facebook and phonenumber
            SocialLink social = new SocialLink();
            social.setTwitter("TwitterKoning");
            User rob = userService.create(new User("rob@mail.nl", "rob", "Rob Schwarzenegger", ict, "Software Engineering", social));

            // Creating posts
            postService.create(new Post(max, "Hoe werkt centreren in CSS?", "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Donec #eleifend ligula enim, #in tempor sem interdum quis. Sed bibendum ex neque, et dapibus nulla ullamcorper a."));
            postService.create(new Post(admin, "Hoe zet ik Java EE7 op?", "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Donec eleifend ligula enim, in tempor sem interdum quis. Sed bibendum ex neque, et dapibus nulla ullamcorper a."));
            postService.create(new Post(rob, "Hoe deploy ik Jenkins op een raspberry pi?", "Lorem ipsum #dolor sit amet, consectetur adipiscing elit. Donec #eleifend #ligula enim, in tempor sem interdum quis. Sed bibendum ex neque, et dapibus nulla ullamcorper a."));
            postService.create(new Post(admin, "Hoe werk ik met Interfaces?", "Lorem ipsum dolor sit #amet, consectetur adipiscing elit. Donec eleifend ligula enim, in tempor sem interdum quis. Sed bibendum ex neque, et dapibus nulla ullamcorper a."));
            postService.create(new Post(admin, "Hoe kies ik mijn Kular?", "Lorem ipsum dolor sit amet, #consectetur adipiscing elit. Donec eleifend #ligula enim, in tempor sem interdum quis. Sed #bibendum ex neque, et dapibus nulla ullamcorper a."));
            postService.create(new Post(pieter, "Hoe maak ik een Drupal website?", "Lorem ipsum dolor sit amet, #consectetur adipiscing elit. Donec eleifend #ligula enim, in tempor sem interdum quis. Sed #bibendum ex neque, et dapibus nulla ullamcorper a."));
            postService.create(new Post(pieter, "Hoe optimaliseer ik mijn SEO?", "Lorem ipsum dolor sit amet, #consectetur adipiscing elit. Donec eleifend #ligula enim, in tempor sem interdum quis. Sed #bibendum ex neque, et dapibus nulla ullamcorper a."));
            postService.create(new Post(kees, "Hoe krijg ik die kinderen aan het werk?", "Lorem ipsum dolor sit amet, #consectetur adipiscing elit. Donec eleifend #ligula enim, in tempor sem interdum quis. Sed #bibendum ex neque, et dapibus nulla ullamcorper a."));
            postService.create(new Post(kees, "Hoe leg ik de jager/verzamelaar cultuur uit?", "Lorem ipsum dolor sit amet, #consectetur adipiscing elit. Donec eleifend #ligula enim, in tempor sem interdum quis. Sed #bibendum ex neque, et dapibus nulla ullamcorper a."));

        } catch (Exception e) {
            LOG.info("An exception occurred : " + e.getMessage());
        }
    }
}
