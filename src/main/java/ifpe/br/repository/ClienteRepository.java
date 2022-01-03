package ifpe.br.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ifpe.br.model.Cliente;


public interface ClienteRepository extends JpaRepository <Cliente,Long> {

}
