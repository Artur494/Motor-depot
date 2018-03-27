package by.grsu.by.service;

import org.apache.wicket.authorization.Action;
import org.apache.wicket.authroles.authorization.strategies.role.annotations.AuthorizeAction;
import org.apache.wicket.markup.html.link.Link;


	@AuthorizeAction(roles = { "admin", "driver", "manager" }, action = Action.RENDER)
	public class InvisibleLinkForUsers extends Link {

			public InvisibleLinkForUsers(String id) {
				super(id);
			}

			@Override
			public void onClick() {
			}
		}


