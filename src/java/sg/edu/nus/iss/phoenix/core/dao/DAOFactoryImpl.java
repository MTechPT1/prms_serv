package sg.edu.nus.iss.phoenix.core.dao;

import sg.edu.nus.iss.phoenix.dao.RoleDao;
import sg.edu.nus.iss.phoenix.dao.UserDao;
import sg.edu.nus.iss.phoenix.dao.impl.RoleDaoImpl;
import sg.edu.nus.iss.phoenix.dao.impl.UserDaoImpl;
import sg.edu.nus.iss.phoenix.dao.ProgramDAO;
import sg.edu.nus.iss.phoenix.dao.impl.ProgramDAOImpl;

public class DAOFactoryImpl implements DAOFactory {
	private UserDao userDAO = new UserDaoImpl();
	private RoleDao roleDAO = new RoleDaoImpl();
	private ProgramDAO rpdao = new ProgramDAOImpl();

	@Override
	public UserDao getUserDAO() {
		// TODO Auto-generated method stub
		return userDAO;
	}

	@Override
	public RoleDao getRoleDAO() {
		// TODO Auto-generated method stub
		return roleDAO;
	}

	@Override
	public ProgramDAO getProgramDAO() {
		// TODO Auto-generated method stub
		return rpdao;
	}

}
