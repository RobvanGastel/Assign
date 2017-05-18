package com.robvangastel.assign;

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
			userService.create(new User("admin@assign.nl", "admin", "admin"));

		} catch(Exception e) {
			LOG.info("An exception occurred : " + e.getMessage());
		}
	}
}
