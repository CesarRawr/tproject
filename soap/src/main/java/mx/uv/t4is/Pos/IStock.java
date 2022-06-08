package mx.uv.t4is.Pos;

import java.util.Optional;
import org.springframework.data.repository.CrudRepository;

public interface IStock extends CrudRepository<Stock, Integer> {
	// Buscar producto por id
	Optional<Stock> findByUsuario(int id);
}
