package ifpe.br.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ifpe.br.model.Telefone;

public interface TelefoneRepository extends JpaRepository<Telefone, Long> {

}
