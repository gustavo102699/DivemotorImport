package pe.edu.upc.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import pe.edu.upc.entity.Users;

@Repository
public interface IUserRepository extends JpaRepository<Users, Integer> {

	public Users findByUsername(String username);

	@Modifying
	@Query(value = "insert into Role (roleName, idUser) VALUES (:roleName, :idUser)", nativeQuery = true)
	public void insertRole(@Param("roleName") String authority, @Param("idUser") int idUser);

	@Query("select count(u.username) from Users u where u.username =:name")
	public int findByUsername1(@Param("name") String username);

	@Query("from Users u where u.username = :username")
	public Users findByUsername2(@Param("username") String username);

	List<Users> findByUsernameLikeIgnoreCase(String username);
}