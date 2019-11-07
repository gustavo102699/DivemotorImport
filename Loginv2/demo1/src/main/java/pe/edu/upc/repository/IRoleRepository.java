package pe.edu.upc.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import pe.edu.upc.entity.Role;
//import pe.edu.upc.entity.User;

@Repository
public interface IRoleRepository extends JpaRepository<Role, Integer> {
	//@Query("select count(r.roleName) from Role r where r.roleName =:name")
	//public int findByRoleName(@Param("name") String roleName);

	@Query("select r from Role r where r.roleName like %:name%")
	List<Role> findByRole(@Param("name") String roleName);

	//@Query("from Role r where r.user.username = :name")
	//public User findByUsername(@Param("name") String username);
	
	List<Role> findByRoleNameLikeIgnoreCase(String roleName);
}
