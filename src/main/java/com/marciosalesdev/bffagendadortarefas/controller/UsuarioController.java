package com.marciosalesdev.bffagendadortarefas.controller;

import com.marciosalesdev.bffagendadortarefas.business.UsuarioService;
import com.marciosalesdev.bffagendadortarefas.business.dto.EnderecoDTO;
import com.marciosalesdev.bffagendadortarefas.business.dto.TelefoneDTO;
import com.marciosalesdev.bffagendadortarefas.business.dto.UsuarioDTO;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path = "/usuario")
@RequiredArgsConstructor
@Tag(name = "Usuário", description = "cadastro e login de usuário")
public class UsuarioController {

    private final UsuarioService usuarioService;

    @Operation(summary = "Salvar Usuário", description = "Cria um novo usuario")
    @ApiResponse(responseCode = "201", description = "Usuario salvo com sucesso")
    @ApiResponse(responseCode = "400", description = "Usuario ja cadastrado")
    @ApiResponse(responseCode = "500", description = "Erro no servidor")
    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping
    public ResponseEntity<UsuarioDTO> salvarUsuario(@RequestBody UsuarioDTO usuarioDTO) {
        return ResponseEntity.ok(usuarioService.salvarUsuario(usuarioDTO));
    }

    @ApiResponse(responseCode = "200", description = "Login realizado com sucesso")
    @ApiResponse(responseCode = "401", description = "Erro ao Logar, credenciais invalidas")
    @ApiResponse(responseCode = "500", description = "Erro no servidor")
    @Operation(summary = "Login", description = "acesso do usuario")
    @PostMapping("/login")
    public String login(@RequestBody UsuarioDTO usuarioDto) {
        return usuarioService.loginUsuario(usuarioDto);
    }

    @ApiResponse(responseCode = "200", description = "Usuario encontrado")
    @ApiResponse(responseCode = "404", description = "Usuario nao encontrado")
    @ApiResponse(responseCode = "500", description = "Erro no servidor")
    @Operation(summary = "Busca um Usuário por Email", description = "busca um usuario por email")
    @GetMapping
    public ResponseEntity<UsuarioDTO> buscarUsuarioPorEmail(@RequestParam("email") String email,
                                                            @RequestHeader("Authorization") String token) {
        return ResponseEntity.status(HttpStatus.OK).body(usuarioService.buscarUsuarioPorEmail(email, token));
    }

    @ApiResponse(responseCode = "204", description = "Deleta usuario por id com sucesso")
    @ApiResponse(responseCode = "404", description = "Usuario nao encontrado")
    @ApiResponse(responseCode = "500", description = "Erro no servidor")
    @Operation(summary = "Deleta usuario por Email", description = "deleta um usuario por email")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @DeleteMapping("/{email}")
    public ResponseEntity<Void> deletaUsuarioPorEmail(@PathVariable String email,
                                                      @RequestHeader("Authorization") String token) {
        usuarioService.deletarUsuarioPorEmail(email, token);
        return ResponseEntity.ok().build();
    }

    @ApiResponse(responseCode = "200", description = "Atualiza usuario com sucesso")
    @ApiResponse(responseCode = "404", description = "Usuario nao encontrado")
    @ApiResponse(responseCode = "500", description = "Erro no servidor")
    @Operation(summary = "Atualiza dados do usuario", description = "atualiza dados do usuario")
    @PutMapping
    public ResponseEntity<UsuarioDTO> atualizarUsuario(@RequestBody UsuarioDTO usuarioDTO,
                                                       @RequestHeader("Authorization") String token) {
        return ResponseEntity.ok(usuarioService.atualziarDadosUsuario(usuarioDTO, token));
    }

    @ApiResponse(responseCode = "200", description = "Endereço atualizado com sucesso")
    @ApiResponse(responseCode = "404", description = "Endereço nao encontrado")
    @ApiResponse(responseCode = "500", description = "Erro no servidor")
    @Operation(summary = "Endereço do usuario", description = "Atualiza endereço do usuario")
    @PutMapping(path = "/endereco")
    public ResponseEntity<EnderecoDTO> atualizarEndereco(@RequestBody EnderecoDTO enderecoDTO,
                                                         @RequestParam("id") Long id,
                                                         @RequestHeader("Authorization") String token) {
        return ResponseEntity.ok(usuarioService.atualizarEndereco(id, enderecoDTO, token));
    }

    @ApiResponse(responseCode = "200", description = "Telefone atualizado com sucesso")
    @ApiResponse(responseCode = "404", description = "Telefone nao encontrado")
    @ApiResponse(responseCode = "500", description = "Erro no servidor")
    @Operation(summary = "Atualiza Telefone do Usuario", description = "Atualiza telefone do usuario")
    @PutMapping(path = "/telefone")
    public ResponseEntity<TelefoneDTO> atualizarUsuario(@RequestBody TelefoneDTO telefoneDTO,
                                                        @RequestParam("id") Long id,
                                                        @RequestHeader("Authorization") String token) {
        return ResponseEntity.ok(usuarioService.atualizarTelefone(id, telefoneDTO, token));
    }

    @ApiResponse(responseCode = "200", description = "Endereço cadastrado com sucesso")
    @ApiResponse(responseCode = "404", description = "Endereço nao encontrado")
    @ApiResponse(responseCode = "500", description = "Erro no servidor")
    @Operation(summary = "Cadastra Endereço do Usuario", description = "cadastra endereço do usuario")
    @PostMapping(path = "/endereco")
    public ResponseEntity<EnderecoDTO> cadastroEndereco(@RequestBody EnderecoDTO enderecoDTO,
                                                        @RequestHeader("Authorization") String token) {
        return ResponseEntity.ok(usuarioService.cadastrarEndereco(enderecoDTO, token));
    }

    @ApiResponse(responseCode = "200", description = "Telefone castrado com sucesso")
    @ApiResponse(responseCode = "404", description = "Telefone nao encontrado")
    @ApiResponse(responseCode = "500", description = "Erro no servidor")
    @Operation(summary = "Cadastra Telefone do Usuario", description = "cadastra endereço do usuario")
    @PostMapping(path = "/telefone")
    public ResponseEntity<TelefoneDTO> cadastroTelefone(@RequestBody TelefoneDTO telefoneDTO,
                                                        @RequestHeader("Authorization") String token) {
        return ResponseEntity.ok(usuarioService.cadastrarTelefone(telefoneDTO, token));
    }
}
