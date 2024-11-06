package com.app.cadastroClientesAPI.Rest.Controller;

import com.app.cadastroClientesAPI.Domain.Entity.Cliente;
import com.app.cadastroClientesAPI.Exception.CustomerAlreadyExistsException;
import com.app.cadastroClientesAPI.Exception.CustomerNotFoundException;
import com.app.cadastroClientesAPI.Service.ClienteService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RequestMapping("api/clientes")
@RestController
public class ClienteController {
    @Autowired
    private ClienteService clienteService;

    private static final Logger logger = LoggerFactory.getLogger(ClienteController.class);


    @Operation(summary = "Criar um novo cliente", description = "Cria um novo cliente no sistema.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "cliente criado com sucesso"),
            @ApiResponse(responseCode = "400", description = "cliente já existe")
    })
    @PostMapping
    public ResponseEntity<?> criarCliente(@RequestBody Cliente cliente) throws CustomerAlreadyExistsException {
        try {
            Cliente novoCliente = clienteService.criarCliente(cliente);
            return ResponseEntity.status(HttpStatus.CREATED).body(novoCliente);
        }catch(CustomerAlreadyExistsException e) {
            logger.info("Erro ao criar cliente :" + e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Erro ao criar cliente :" + e.getMessage());
        }
    }




    @Operation(summary = "Atualizar um cliente", description = "Atualizar um cliente no sistema.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "cliente atualizado com sucesso"),
            @ApiResponse(responseCode = "404", description = "cliente não encontrado")
    })
    @PutMapping("/{id}")
    public ResponseEntity<?> atualizarCliente(@PathVariable Long id, @RequestBody Cliente cliente) throws CustomerNotFoundException {
        try{
            Cliente clienteAtualizado = clienteService.atualizarCliente(id, cliente);
            return ResponseEntity.ok( clienteAtualizado );
        }catch (CustomerNotFoundException e){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Erro ao atualizar cliente :" + e.getMessage());
        }

    }



    @Operation(summary = "Deletar um cliente", description = "Deletar um cliente no sistema.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "cliente deletado com sucesso"),
            @ApiResponse(responseCode = "404", description = "cliente não encontrado")
    })
    @ExceptionHandler(CustomerNotFoundException.class)
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deletarCliente(@PathVariable Long id) throws CustomerNotFoundException {
        try{
            clienteService.deleteCliente(id);
            return ResponseEntity.status(HttpStatus.ACCEPTED).body("Cliente deletado com Sucesso ");
        }catch (CustomerNotFoundException e){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Erro ao deletar cliente :" + e.getMessage());
        }

    }


    @Operation(summary = "Listar um cliente", description = "Listar um cliente no sistema.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "cliente listado com sucesso"),
            @ApiResponse(responseCode = "404", description = "cliente não encontrado")
    })
    @ExceptionHandler(CustomerNotFoundException.class)
    @GetMapping("/{id}")
    public ResponseEntity<?> listaUmCliente(@PathVariable Long id) throws CustomerNotFoundException {
        try{
            Optional<Cliente> cliente = clienteService.buscaUmCliente(id);
            return ResponseEntity.ok(cliente);
        }catch (CustomerNotFoundException e){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Erro ao deletar cliente :" + e.getMessage());
        }
    }



    @Operation(summary = "Listar todos os clientes", description = "Retorna uma lista de todos os clientes cadastrados.")
    @ApiResponse(responseCode = "200", description = "Lista de clientes retornada com sucesso")
    @GetMapping
    public List<Cliente> listaVariosClientes(){
        return clienteService.listarClientes();
    }



    @Operation(summary = "Listar todos os clientes ordenados por nome", description = "Retorna uma lista de todos os clientes cadastrados ordenados por nome.")
    @ApiResponse(responseCode = "200", description = "Lista de clientes retornada com sucesso")
    @GetMapping("/ordenado")
    public List<Cliente> listarClientesOrdenadosPorNome() {
        return clienteService.listarTodosOrdenadosPorNome();
    }



    @Operation(summary = "Listar todos os clientes por estado", description = "Retorna uma lista de todos os clientes cadastrados por estado.")
    @ApiResponse(responseCode = "200", description = "Lista de clientes retornada com sucesso")
    @GetMapping("/estado/{estado}")
    public List<Cliente> listarClientesPorEstado(@PathVariable String estado) {
        return clienteService.listarPorEstado(estado);
    }




}
