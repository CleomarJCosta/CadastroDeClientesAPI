package com.app.cadastroClientesAPI.Service;

import com.app.cadastroClientesAPI.Domain.Entity.Cliente;
import com.app.cadastroClientesAPI.Domain.Repository.ClienteRepository;
import com.app.cadastroClientesAPI.Exception.CustomerAlreadyExistsException;
import com.app.cadastroClientesAPI.Exception.CustomerNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ClienteService {

    @Autowired
    private ClienteRepository clienteRepository;


    public Cliente criarCliente(Cliente cliente) throws CustomerAlreadyExistsException {
       Optional<Cliente> clienteOptional = Optional.ofNullable(clienteRepository.findClienteByEmail(cliente.getEmail()));
       if(clienteOptional.isPresent()){
           throw new CustomerAlreadyExistsException("Cliente já Existe");
       }else{
           cliente.onCreate();
           return clienteRepository.save(cliente);
       }

    }

    public List<Cliente> listarClientes() {
        return clienteRepository.findAll();
    }

    public Cliente atualizarCliente(Long id, Cliente clienteAtualizado) throws  CustomerNotFoundException {
        Cliente clienteExistente = clienteRepository.findById(id)
                .orElseThrow(() -> new CustomerNotFoundException("Cliente não encontrado"));

        clienteExistente.setNome(clienteAtualizado.getNome());
        clienteExistente.setEndereco(clienteAtualizado.getEndereco());
        clienteExistente.onUpdate();

        return clienteRepository.save(clienteExistente);
    }

    public void deleteCliente(Long id) throws CustomerNotFoundException {
        Optional<Cliente> clienteExistente = clienteRepository.findById(id);
        if(clienteExistente.isPresent()){
            clienteRepository.deleteById(id);
            clienteExistente.get().onUpdate();
        }else{
            throw new CustomerNotFoundException("Cliente Inexistente");
        }
    }

    public Optional<Cliente> buscaUmCliente(Long id) throws CustomerNotFoundException {
        Optional<Cliente> clienteExistente = clienteRepository.findById(id);
        if(clienteExistente.isPresent()){
            return clienteRepository.findById(id);
        }else{
            throw new CustomerNotFoundException("Cliente Inexistente");
        }
    }

    public List<Cliente> listarTodosOrdenadosPorNome(){
        return clienteRepository.findAllOrderByNome();
    }

    public List<Cliente> listarPorEstado(String estado){
        return clienteRepository.findByEstado(estado);
    }



}
