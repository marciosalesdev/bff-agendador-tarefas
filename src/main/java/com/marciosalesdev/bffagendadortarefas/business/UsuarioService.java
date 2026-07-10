package com.marciosalesdev.bffagendadortarefas.business;


import com.marciosalesdev.bffagendadortarefas.business.dto.in.EnderecoDTORequest;
import com.marciosalesdev.bffagendadortarefas.business.dto.in.LoginRequestDTO;
import com.marciosalesdev.bffagendadortarefas.business.dto.in.TelefoneDTORequest;
import com.marciosalesdev.bffagendadortarefas.business.dto.in.UsuarioDTORequest;
import com.marciosalesdev.bffagendadortarefas.business.dto.out.EnderecoDTOResponse;
import com.marciosalesdev.bffagendadortarefas.business.dto.out.TelefoneDTOResponse;
import com.marciosalesdev.bffagendadortarefas.business.dto.out.UsuarioDTOResponse;
import com.marciosalesdev.bffagendadortarefas.infrastructure.client.UsuarioClient;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UsuarioService {

    private final UsuarioClient usuarioClient;


    public UsuarioDTOResponse salvarUsuario(UsuarioDTORequest usuarioDTO) {
        return usuarioClient.salvarUsuario(usuarioDTO);
    }

    public String loginUsuario(LoginRequestDTO usuarioDTO) {
        return usuarioClient.login(usuarioDTO);
    }

    public UsuarioDTOResponse buscarUsuarioPorEmail(String email, String token) {

        return usuarioClient.buscarUsuarioPorEmail(email, token);
    }

    public void deletarUsuarioPorEmail(String email, String token) {
        usuarioClient.deletaUsuarioPorEmail(email, token);
    }

    public UsuarioDTOResponse atualziarDadosUsuario(UsuarioDTORequest usuarioDTO, String token) {
        return usuarioClient.atualizarUsuario(usuarioDTO, token);
    }

    public EnderecoDTOResponse atualizarEndereco(Long id, EnderecoDTORequest enderecoDTO, String token) {
        return usuarioClient.atualizarEndereco(enderecoDTO, id, token);
    }

    public TelefoneDTOResponse atualizarTelefone(Long id, TelefoneDTORequest telefone, String token) {
        return usuarioClient.atualizarUsuario(telefone, id, token);
    }

    public EnderecoDTOResponse cadastrarEndereco(EnderecoDTORequest enderecoDTO, String token) {
        return usuarioClient.cadastroEndereco(enderecoDTO, token);
    }

    public TelefoneDTOResponse cadastrarTelefone(TelefoneDTORequest telefoneDTO, String token) {
        return usuarioClient.cadastroTelefone(telefoneDTO, token);
    }
}
