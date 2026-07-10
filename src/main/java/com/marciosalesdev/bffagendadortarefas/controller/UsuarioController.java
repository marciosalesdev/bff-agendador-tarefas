package com.marciosalesdev.bffagendadortarefas.controller;

import com.marciosalesdev.bffagendadortarefas.business.UsuarioService;
import com.marciosalesdev.bffagendadortarefas.business.dto.in.EnderecoDTORequest;
import com.marciosalesdev.bffagendadortarefas.business.dto.in.LoginRequestDTO;
import com.marciosalesdev.bffagendadortarefas.business.dto.in.TelefoneDTORequest;
import com.marciosalesdev.bffagendadortarefas.business.dto.in.UsuarioDTORequest;
import com.marciosalesdev.bffagendadortarefas.business.dto.out.EnderecoDTOResponse;
import com.marciosalesdev.bffagendadortarefas.business.dto.out.TelefoneDTOResponse;
import com.marciosalesdev.bffagendadortarefas.business.dto.out.UsuarioDTOResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequestMapping(path = "/usuario")
@RequiredArgsConstructor
@Tag(name = "Usuário", description = "cadastro e login de usuário")
public class UsuarioController {

    private final UsuarioService usuarioService;

    @Operation(summary = "Salvar Usuário", description = "Cria um novo usuário")
    @ApiResponse(responseCode = "201", description = "Usuário salvo com sucesso")
    @ApiResponse(responseCode = "400", description = "Usuário ja cadastrado")
    @ApiResponse(responseCode = "500", description = "Erro no servidor")
    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping
    public ResponseEntity<UsuarioDTOResponse> salvarUsuario(@RequestBody UsuarioDTORequest usuarioDTO) {
        return ResponseEntity.ok(usuarioService.salvarUsuario(usuarioDTO));
    }

    @ApiResponse(responseCode = "200", description = "Login realizado com sucesso")
    @ApiResponse(responseCode = "401", description = "Erro ao Logar, credenciais inválidas")
    @ApiResponse(responseCode = "500", description = "Erro no servidor")
    @Operation(summary = "Login", description = "acesso do usuario")
    @PostMapping("/login")
    public String login(@RequestBody LoginRequestDTO usuarioDto) {
        return usuarioService.loginUsuario(usuarioDto);
    }

    @ApiResponse(responseCode = "200", description = "Usuário encontrado")
    @ApiResponse(responseCode = "404", description = "Usuário nao encontrado")
    @ApiResponse(responseCode = "500", description = "Erro no servidor")
    @Operation(summary = "Busca um Usuário por Email", description = "busca um usuario por email")
    @GetMapping
    public ResponseEntity<UsuarioDTOResponse> buscarUsuarioPorEmail(@RequestParam("email") String email,
                                                                    @RequestHeader(name = "Authorization", required = false) String token) {
        log.info("token recebido " + " " + token);
        return ResponseEntity.status(HttpStatus.OK).body(usuarioService.buscarUsuarioPorEmail(email, token));
    }

    @ApiResponse(responseCode = "204", description = "Deleta usuário por id com sucesso")
    @ApiResponse(responseCode = "404", description = "Usuário nao encontrado")
    @ApiResponse(responseCode = "500", description = "Erro no servidor")
    @Operation(summary = "Deleta usuário por Email", description = "deleta um usuário por email")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @DeleteMapping("/{email}")
    public ResponseEntity<Void> deletaUsuarioPorEmail(@PathVariable String email,
                                                      @RequestHeader(name = "Authorization", required = false) String token) {
        usuarioService.deletarUsuarioPorEmail(email, token);
        return ResponseEntity.ok().build();
    }

    @ApiResponse(responseCode = "200", description = "Atualiza usuário com sucesso")
    @ApiResponse(responseCode = "404", description = "Usuário não encontrado")
    @ApiResponse(responseCode = "500", description = "Erro no servidor")
    @Operation(summary = "Atualiza dados do usuario", description = "atualiza dados do usuário")
    @PutMapping
    public ResponseEntity<UsuarioDTOResponse> atualizarUsuario(@RequestBody UsuarioDTORequest usuarioDTO,
                                                               @RequestHeader(name = "Authorization", required = false) String token) {
        return ResponseEntity.ok(usuarioService.atualziarDadosUsuario(usuarioDTO, token));
    }

    @ApiResponse(responseCode = "200", description = "Endereço atualizado com sucesso")
    @ApiResponse(responseCode = "404", description = "Endereço nao encontrado")
    @ApiResponse(responseCode = "500", description = "Erro no servidor")
    @Operation(summary = "Endereço do usuario", description = "Atualiza endereço do usuario")
    @PutMapping(path = "/endereco")
    public ResponseEntity<EnderecoDTOResponse> atualizarEndereco(@RequestBody EnderecoDTORequest enderecoDTO,
                                                                 @RequestParam("id") Long id,
                                                                 @RequestHeader(name = "Authorization", required = false) String token) {
        return ResponseEntity.ok(usuarioService.atualizarEndereco(id, enderecoDTO, token));
    }

    @ApiResponse(responseCode = "200", description = "Telefone atualizado com sucesso")
    @ApiResponse(responseCode = "404", description = "Telefone nao encontrado")
    @ApiResponse(responseCode = "500", description = "Erro no servidor")
    @Operation(summary = "Atualiza Telefone do Usuario", description = "Atualiza telefone do usuario")
    @PutMapping(path = "/telefone")
    public ResponseEntity<TelefoneDTOResponse> atualizarUsuario(@RequestBody TelefoneDTORequest telefoneDTO,
                                                                @RequestParam("id") Long id,
                                                                @RequestHeader(name = "Authorization", required = false) String token) {
        return ResponseEntity.ok(usuarioService.atualizarTelefone(id, telefoneDTO, token));
    }

    @ApiResponse(responseCode = "200", description = "Endereço cadastrado com sucesso")
    @ApiResponse(responseCode = "404", description = "Endereço nao encontrado")
    @ApiResponse(responseCode = "500", description = "Erro no servidor")
    @Operation(summary = "Cadastra Endereço do Usuario", description = "cadastra endereço do usuario")
    @PostMapping(path = "/endereco")
    public ResponseEntity<EnderecoDTOResponse> cadastroEndereco(@RequestBody EnderecoDTORequest enderecoDTO,
                                                                @RequestHeader(name = "Authorization", required = false) String token) {
        return ResponseEntity.ok(usuarioService.cadastrarEndereco(enderecoDTO, token));
    }

    @ApiResponse(responseCode = "200", description = "Telefone castrado com sucesso")
    @ApiResponse(responseCode = "404", description = "Telefone nao encontrado")
    @ApiResponse(responseCode = "500", description = "Erro no servidor")
    @Operation(summary = "Cadastra Telefone do Usuario", description = "cadastra endereço do usuario")
    @PostMapping(path = "/telefone")
    public ResponseEntity<TelefoneDTOResponse> cadastroTelefone(@RequestBody TelefoneDTORequest telefoneDTO,
                                                                @RequestHeader(name = "Authorization", required = false) String token) {
        return ResponseEntity.ok(usuarioService.cadastrarTelefone(telefoneDTO, token));
    }
}
