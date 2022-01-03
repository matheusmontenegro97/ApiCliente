package ifpe.br.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ifpe.br.model.Cliente;

import ifpe.br.repository.ClienteRepository;

@RestController
@RequestMapping("cliente")
public class ClienteController {

	@Autowired
	private ClienteRepository clienteRepository;
	
	@GetMapping
	public List<Cliente> listaClientes(){
		return clienteRepository.findAll();
	}
	
	@GetMapping("/{id}")
	public Cliente retornaClientePorId(@PathVariable Long id) {
		Optional<Cliente> cliente = clienteRepository.findById(id);
		if(cliente.isEmpty())
			return null;
		return cliente.get();
	}
	
	@PostMapping
	public String createCliente(@RequestBody Cliente cliente) {
		if(cliente.getEndereco() == null) {
			return "Cliente deve possuir um endereço!";
		}
		else {
		clienteRepository.save(cliente);
		}
		return "Cliente criado!";
	}
	
	@PutMapping
	public String updateCliente(@RequestBody Cliente cliente) {
		if(cliente.getId() == null)
			return "Informe o id!";
			
		clienteRepository.save(cliente);
		return "Cliente atualizado!";
	}
	
	@DeleteMapping
	public String deleteCliente(@RequestBody Cliente cliente) {
		if(cliente.getId() == null)
			return "Informe o id!";
		
		clienteRepository.delete(cliente);
		return "Cliente deletado!";
	}
	
}
