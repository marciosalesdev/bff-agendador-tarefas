package com.marciosalesdev.bffagendadortarefas.infrastructure.client;

import com.marciosalesdev.bffagendadortarefas.business.dto.EnderecoDTO;
import com.marciosalesdev.bffagendadortarefas.business.dto.TelefoneDTO;
import com.marciosalesdev.bffagendadortarefas.business.dto.UsuarioDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;


@FeignClient(name = "usuario", url = "${usuario.url}")
public interface UsuarioClient {

    @GetMapping("/usuario")
    UsuarioDTO buscarUsuarioPorEmail(@RequestParam("email") String email,
                                     @RequestHeader("Authorization") String token);


    @PostMapping
    UsuarioDTO salvarUsuario(@RequestBody UsuarioDTO usuarioDTO);

    @PostMapping("/login")
    String login(@RequestBody UsuarioDTO usuarioDto);

    @DeleteMapping("/{email}")
    void deletaUsuarioPorEmail(@PathVariable String email,
                               @RequestHeader("Authorization") String token);

    @PutMapping
    UsuarioDTO atualizarUsuario(@RequestBody UsuarioDTO usuarioDTO,
                                @RequestHeader("Authorization") String token);

    @PutMapping(path = "/endereco")
    EnderecoDTO atualizarEndereco(@RequestBody EnderecoDTO enderecoDTO,
                                  @RequestParam("id") Long id,
                                  @RequestHeader("Authorization") String token);

    @PutMapping(path = "/telefone")
    TelefoneDTO atualizarUsuario(@RequestBody TelefoneDTO telefoneDTO,
                                 @RequestParam("id") Long id,
                                 @RequestHeader("Authorization") String token);

    @PostMapping(path = "/endereco")
    EnderecoDTO cadastroEndereco(@RequestBody EnderecoDTO enderecoDTO,
                                 @RequestHeader("Authorization") String token);

    @PostMapping(path = "/telefone")
    TelefoneDTO cadastroTelefone(@RequestBody TelefoneDTO telefoneDTO,
                                 @RequestHeader("Authorization") String token);
}
