package co.innovaciones.repository;
import org.springframework.stereotype.Repository;
import org.springframework.data.jpa.repository.JpaRepository;
import co.innovaciones.model.Torniquete;

@Repository
public interface TorniqueteRepository extends JpaRepository<Torniquete, Integer> {
	
}