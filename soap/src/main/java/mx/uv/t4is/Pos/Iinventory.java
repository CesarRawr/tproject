package mx.uv.t4is.Pos;

import java.util.Optional;
import org.springframework.data.repository.CrudRepository;

public interface Iinventory extends CrudRepository<Inventario, Integer> {
	// Buscar producto por id
	Optional<Inventario> findById(int id);
	// Buscar producto por nombre
	Optional<Inventario> findByNombre(String nombre);
}
