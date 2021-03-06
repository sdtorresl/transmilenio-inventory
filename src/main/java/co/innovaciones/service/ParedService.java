package co.innovaciones.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;
import co.innovaciones.model.Pared;
import co.innovaciones.repository.ParedRepository;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(readOnly = true)
public class ParedService {

	@Autowired
	private ParedRepository paredRepository;

	public List<Pared> findAll() {
		return paredRepository.findAll();
	}

	public Optional<Pared> findById(Integer id) {
		return paredRepository.findById(id);
	}
	
	@Transactional(readOnly = false)
	public Pared save(Pared entity) {
		return paredRepository.save(entity);
	}

	@Transactional(readOnly = false)
	public void delete(Pared entity) {
		paredRepository.delete(entity);
	}

}
	
