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
 *
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
			// School startup
			School fontys = schoolService.create(new School("Fontys Tilburg"));
			Study ict = schoolService.addStudy(fontys, "ICT");

			// User startup
			User admin = userService.create(new User("admin@mail.nl", "admin", "Jan Pieter", ict, "Media vormgeving", new SocialLink("JanPieterJeWeet", "FacebookenMeneer", "06380013")));
			User max = userService.create(new User("max@mail.nl", "max", "Max Wammels", ict, "Technology", new SocialLink("MaxWammelsWieeehoo", "MeneerDePeer", "06123111")));

			// Social startup
			SocialLink social = new SocialLink();
			social.setTwitter("TwitterKoning");

			User rob = userService.create(new User("rob@mail.nl", "rob", "Rob Schwarzenegger", ict, "Software Engineering", social));

			// Post startup
			Post post = postService.create(new Post(max, "Hoe werkt centreren in CSS?", "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Donec #eleifend ligula enim, #in tempor sem interdum quis. Sed bibendum ex neque, et dapibus nulla ullamcorper a."));
			postService.create(new Post(admin, "Hoe zet ik Java EE7 op?", "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Donec eleifend ligula enim, in tempor sem interdum quis. Sed bibendum ex neque, et dapibus nulla ullamcorper a."));
			postService.create(new Post(rob, "Hoe deploy ik Jenkins op een raspberry pi?", "Lorem ipsum #dolor sit amet, consectetur adipiscing elit. Donec #eleifend #ligula enim, in tempor sem interdum quis. Sed bibendum ex neque, et dapibus nulla ullamcorper a."));
			postService.create(new Post(admin, "Hoe werk ik met Interfaces?", "Lorem ipsum dolor sit #amet, consectetur adipiscing elit. Donec eleifend ligula enim, in tempor sem interdum quis. Sed bibendum ex neque, et dapibus nulla ullamcorper a."));
			postService.create(new Post(admin, "Hoe kies ik mijn Kular?", "Lorem ipsum dolor sit amet, #consectetur adipiscing elit. Donec eleifend #ligula enim, in tempor sem interdum quis. Sed #bibendum ex neque, et dapibus nulla ullamcorper a."));

		} catch(Exception e) {
			LOG.info("An exception occurred : " + e.getMessage());
		}
	}
}
