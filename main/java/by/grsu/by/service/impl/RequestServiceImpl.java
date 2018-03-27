package by.grsu.by.service.impl;

import java.util.List;

import by.grsu.by.dataaccess.impl.RequestDao;
import by.grsu.by.datamodel.Request;
import by.grsu.by.service.Service;

public class RequestServiceImpl extends RequestDao implements Service<Request> {

	public RequestServiceImpl(String rootFolderPath) {
		super(rootFolderPath);
		dao = new RequestDao(rootFolderPath);
	}

	private RequestDao dao;

	@Override
	public void saveOrUpdate(Request request) {
		if (request.getId() == null) {
			dao.saveNew(request);
		} else {
			dao.update(request);
		}
	}

	@Override
	public void saveNew(Request entity) {
		dao.saveNew(entity);
	}

	@Override
	public void update(Request entity) {
		dao.update(entity);
	}

	@Override
	public Request get(Long id) {
		return dao.get(id);
	}

	@Override
	public List<Request> getAll() {
		return dao.getAll();
	}

	@Override
	public void delete(Long id) {
		dao.delete(id);
	}

}
