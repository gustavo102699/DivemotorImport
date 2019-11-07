package pe.edu.upc.service;

import java.util.List;
import java.util.Optional;

import pe.edu.upc.entity.Users;

public interface IUserService {

	public void insertRole(String authority, int idUser);

	public Integer insert(Users user);

	public List<Users> list();

	Optional<Users> listId(int idUser);

	public void delete(int idUser);

	public Users findByUserName(String username);

	public List<Users> findByUsernameLikeIgnoreCase(String username);
}
