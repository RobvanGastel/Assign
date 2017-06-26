package com.robvangastel.assign;

import com.robvangastel.assign.domain.Post;
import com.robvangastel.assign.domain.User;
import com.robvangastel.assign.services.PostService;
import com.robvangastel.assign.services.UserService;
import org.jboss.logging.Logger;

import javax.annotation.PostConstruct;
import javax.ejb.Startup;
import javax.ejb.Singleton;
import javax.inject.Inject;

/**
 * Created by Rob on 23-4-2017.
 */

@Singleton
@Startup
public class StartUp {

	private static final Logger LOG = Logger.getLogger(StartUp.class.getName());

	@Inject
	private UserService userService;

	@Inject
	private PostService postService;

	@PostConstruct
	public void initData() {
		try {
			User admin = userService.create(new User("admin@mail.nl", "admin", "admin"));
			User max = userService.create(new User("max@mail.nl", "max", "max"));
			User rob = userService.create(new User("rob@mail.nl", "rob", "rob"));

			postService.create(new Post(max, "Hoe werkt centreren in CSS?", "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Donec eleifend ligula enim, in tempor sem interdum quis. Sed bibendum ex neque, et dapibus nulla ullamcorper a."));
			postService.create(new Post(admin, "Hoe zet ik Java EE7 op?", "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Donec eleifend ligula enim, in tempor sem interdum quis. Sed bibendum ex neque, et dapibus nulla ullamcorper a."));
			postService.create(new Post(rob, "Hoe deploy ik Jenkins op een raspberry pi?", "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Donec eleifend ligula enim, in tempor sem interdum quis. Sed bibendum ex neque, et dapibus nulla ullamcorper a."));
			postService.create(new Post(admin, "Hoe werk ik met Interfaces?", "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Donec eleifend ligula enim, in tempor sem interdum quis. Sed bibendum ex neque, et dapibus nulla ullamcorper a."));
			postService.create(new Post(admin, "Hoe kies ik mijn Kular?", "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Donec eleifend ligula enim, in tempor sem interdum quis. Sed bibendum ex neque, et dapibus nulla ullamcorper a."));
		} catch(Exception e) {
			LOG.info("An exception occurred : " + e.getMessage());
		}
	}
}