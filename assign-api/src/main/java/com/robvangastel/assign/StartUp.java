package com.robvangastel.assign;

import org.jboss.logging.Logger;

import javax.annotation.PostConstruct;
import javax.ejb.Startup;
import javax.ejb.Singleton;

/**
 * Created by Rob on 23-4-2017.
 */

@Singleton
@Startup
public class StartUp {

	private static final Logger LOG = Logger.getLogger(StartUp.class.getName());

	@PostConstruct
	public void initData() {
		try {

		} catch(Exception e) {
			//TODO replace with logger
			System.out.println(e.getMessage());
		}
	}
}
