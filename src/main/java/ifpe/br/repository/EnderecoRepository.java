package ifpe.br.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ifpe.br.model.Endereco;

public interface EnderecoRepository extends JpaRepository<Endereco, Long> {

}
