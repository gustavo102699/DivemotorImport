package pe.edu.upc.service;

import java.util.List;
import java.util.Optional;

import pe.edu.upc.entity.Role;
//import pe.edu.upc.entity.User;

public interface IRoleService {
	//public Integer insert(Role role);

	public List<Role> list();

	Optional<Role> listId(int idRole);

	public void delete(int idUser);

	public List<Role> findByRoleName(String roleName);
	//public User findByUserName(String username);

	public List<Role> findByRoleNameLikeIgnoreCase(String roleName);
}
