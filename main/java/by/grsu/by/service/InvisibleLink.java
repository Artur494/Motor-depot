package by.grsu.by.service;

import org.apache.wicket.authorization.Action;
import org.apache.wicket.authroles.authorization.strategies.role.annotations.AuthorizeAction;
import org.apache.wicket.markup.html.link.Link;

@AuthorizeAction(roles = { "admin" }, action = Action.RENDER)
public class InvisibleLink extends Link {

		public InvisibleLink(String id) {
			super(id);
		}

		@Override
		public void onClick() {
		}
	}
