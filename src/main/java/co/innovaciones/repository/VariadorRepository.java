package co.innovaciones.repository;
import org.springframework.stereotype.Repository;
import org.springframework.data.jpa.repository.JpaRepository;
import co.innovaciones.model.Variador;

@Repository
public interface VariadorRepository extends JpaRepository<Variador, Integer> {
	
}