package dkpc.projects.presupuestos.app.dao;

import dkpc.projects.presupuestos.app.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {

    User save(User user);

}
