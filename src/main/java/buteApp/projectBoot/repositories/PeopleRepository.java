package buteApp.projectBoot.repositories;

import buteApp.projectBoot.models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;



@Repository
public interface PeopleRepository extends JpaRepository<User, Integer> {

}
