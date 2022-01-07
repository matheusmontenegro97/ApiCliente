package ifpe.br.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponents;
import org.springframework.web.util.UriComponentsBuilder;

import ifpe.br.model.Cliente;
import ifpe.br.model.Endereco;
import ifpe.br.repository.ClienteRepository;

@Service
public class ClienteService {
	
	@Autowired
	private ClienteRepository clienteRepository;
	
	public List<Cliente> listaClientes(){
		return clienteRepository.findAll();
	}
	
	public Cliente listaClientePorId(Long id) {
		Optional<Cliente> cliente = clienteRepository.findById(id);
		if(cliente.isEmpty())
			return null;
		return cliente.get();
	}
	
	public void salvarCliente(Cliente cliente) {
		clienteRepository.save(cliente);
	}
	
	public void deletarCliente(Cliente cliente) {
		clienteRepository.delete(cliente);
	}
	
	//https://viacep.com.br/ws/{cep}/json
	public void buscaCep(Cliente cliente) {
		RestTemplate template = new RestTemplate();
		
		String cep = cliente.getEndereco().getCep().replace("-", "");
		
		UriComponents uri = UriComponentsBuilder.newInstance()
				.scheme("https")
				.host("viacep.com.br")
				.path("ws/"+cep+"/json")
				.build();
		
		ResponseEntity<Endereco> entity = template.getForEntity(uri.toString(), Endereco.class);
		cliente.getEndereco().setCep(entity.getBody().getCep());
		cliente.getEndereco().setLogradouro(entity.getBody().getLogradouro());
		cliente.getEndereco().setBairro(entity.getBody().getBairro());
		cliente.getEndereco().setLocalidade(entity.getBody().getLocalidade());
		cliente.getEndereco().setUf(entity.getBody().getUf());
	}
}
