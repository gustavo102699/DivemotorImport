package pe.edu.upc.serviceimpl;

import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import pe.edu.upc.entity.Role;
//import pe.edu.upc.entity.User;
import pe.edu.upc.repository.IRoleRepository;
import pe.edu.upc.service.IRoleService;

@Service
public class RoleServiceImpl implements IRoleService {

	@Autowired
	private IRoleRepository rR;

	// @Override
	// public Integer insert(Role role) {
	// return null;
	// }

	@Override
	public List<Role> list() {
		return rR.findAll();
	}

	@Override
	public Optional<Role> listId(int idRole) {
		return rR.findById(idRole);
	}

	@Transactional
	@Override
	public void delete(int idRole) {
		rR.deleteById(idRole);
	}

	//@Override
	//public User findByUserName(String username) {
	//	return rR.findByUsername(username);
	//}

	@Override
	public List<Role> findByRoleNameLikeIgnoreCase(String roleName) {
		return rR.findByRoleNameLikeIgnoreCase(roleName);
	}

	@Override
	public List<Role> findByRoleName(String roleName) {
		return rR.findByRole(roleName);
	}

}
