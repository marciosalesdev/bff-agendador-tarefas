package com.marciosalesdev.bffagendadortarefas.infrastructure.client;

import com.marciosalesdev.bffagendadortarefas.business.dto.in.EnderecoDTORequest;
import com.marciosalesdev.bffagendadortarefas.business.dto.in.LoginRequestDTO;
import com.marciosalesdev.bffagendadortarefas.business.dto.in.TelefoneDTORequest;
import com.marciosalesdev.bffagendadortarefas.business.dto.in.UsuarioDTORequest;
import com.marciosalesdev.bffagendadortarefas.business.dto.out.EnderecoDTOResponse;
import com.marciosalesdev.bffagendadortarefas.business.dto.out.TelefoneDTOResponse;
import com.marciosalesdev.bffagendadortarefas.business.dto.out.UsuarioDTOResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;


@FeignClient(name = "usuario", url = "${usuario.url}")
public interface UsuarioClient {

    @GetMapping
    UsuarioDTOResponse buscarUsuarioPorEmail(@RequestParam("email") String email,
                                             @RequestHeader("Authorization") String token);


    @PostMapping
    UsuarioDTOResponse salvarUsuario(@RequestBody UsuarioDTORequest usuarioDTO);

    @PostMapping("/login")
    String login(@RequestBody LoginRequestDTO usuarioDto);

    @DeleteMapping("/{email}")
    void deletaUsuarioPorEmail(@PathVariable String email,
                               @RequestHeader("Authorization") String token);

    @PutMapping
    UsuarioDTOResponse atualizarUsuario(@RequestBody UsuarioDTORequest usuarioDTO,
                                        @RequestHeader("Authorization") String token);

    @PutMapping(path = "/endereco")
    EnderecoDTOResponse atualizarEndereco(@RequestBody EnderecoDTORequest enderecoDTO,
                                          @RequestParam("id") Long id,
                                          @RequestHeader("Authorization") String token);

    @PutMapping(path = "/telefone")
    TelefoneDTOResponse atualizarUsuario(@RequestBody TelefoneDTORequest telefoneDTO,
                                         @RequestParam("id") Long id,
                                         @RequestHeader("Authorization") String token);

    @PostMapping(path = "/endereco")
    EnderecoDTOResponse cadastroEndereco(@RequestBody EnderecoDTORequest enderecoDTO,
                                         @RequestHeader("Authorization") String token);

    @PostMapping(path = "/telefone")
    TelefoneDTOResponse cadastroTelefone(@RequestBody TelefoneDTORequest telefoneDTO,
                                         @RequestHeader("Authorization") String token);
}
