package pe.edu.upc.serviceimpl;

import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import pe.edu.upc.entity.Users;
import pe.edu.upc.repository.IUserRepository;
import pe.edu.upc.service.IUserService;

@Service
public class UserServiceImpl implements IUserService {
	@Autowired
	private IUserRepository uR;

	@Transactional
	@Override
	public Integer insert(Users user) {
		int rpta = uR.findByUsername1(user.getUsername());
		if (rpta == 0) {
			uR.save(user);
		}
		return rpta;
	}

	@Transactional
	@Override
	public List<Users> list() {
		return uR.findAll();
	}

	@Override
	public Optional<Users> listId(int idUser) {
		return uR.findById(idUser);
	}

	@Transactional
	@Override
	public void delete(int idUser) {
		uR.deleteById(idUser);
	}

	@Override
	public Users findByUserName(String username) {
		return uR.findByUsername2(username);
	}

	@Override
	public List<Users> findByUsernameLikeIgnoreCase(String username) {
		return uR.findByUsernameLikeIgnoreCase(username);
	}

	@Override
	public void insertRole(String authority, int idUser) {
		uR.insertRole(authority, idUser);

	}

}
