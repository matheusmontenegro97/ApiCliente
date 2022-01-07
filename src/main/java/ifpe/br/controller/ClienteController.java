package ifpe.br.controller;

import java.util.List;

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
import ifpe.br.service.ClienteService;

@RestController
@RequestMapping("cliente")
public class ClienteController {

	@Autowired
	private ClienteService clienteService;
	
	@GetMapping
	public List<Cliente> retornaClientes(){
		return clienteService.listaClientes();
	}
	
	@GetMapping("/{id}")
	public Cliente retornaClientePorId(@PathVariable Long id) {
		return clienteService.listaClientePorId(id);
	}
	
	@PostMapping
	public String createCliente(@RequestBody Cliente cliente) {
		if(cliente.getEndereco().getCep() == null) {
			return "Cliente deve informar o Cep!";
		}
		else if(cliente.getEndereco().getNumero() == null) {
			return "Cliente deve informar o número!";
		}
		else {
		clienteService.buscaCep(cliente);
		clienteService.salvarCliente(cliente);
		}
		return "Cliente criado!";
	}
	
	@PutMapping
	public String updateCliente(@RequestBody Cliente cliente) {
		if(cliente.getId() == null)
			return "Informe o id!";
			
		clienteService.salvarCliente(cliente);
		return "Cliente atualizado!";
	}
	
	@DeleteMapping
	public String deleteCliente(@RequestBody Cliente cliente) {
		if(cliente.getId() == null)
			return "Informe o id!";
		
		clienteService.deletarCliente(cliente);
		return "Cliente deletado!";
	}
	
}
