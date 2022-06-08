package mx.uv.t4is.Pos;

import java.util.Optional;
import org.springframework.data.repository.CrudRepository;

public interface IUsuario extends CrudRepository<Usuario, Integer> {
	// Buscar producto por id
	Optional<Usuario> findByNickname(String nickname);
}
