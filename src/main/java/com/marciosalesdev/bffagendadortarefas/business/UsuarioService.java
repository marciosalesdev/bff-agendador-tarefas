package com.marciosalesdev.bffagendadortarefas.business;


import com.marciosalesdev.bffagendadortarefas.business.dto.EnderecoDTO;
import com.marciosalesdev.bffagendadortarefas.business.dto.TelefoneDTO;
import com.marciosalesdev.bffagendadortarefas.business.dto.UsuarioDTO;
import com.marciosalesdev.bffagendadortarefas.infrastructure.client.UsuarioClient;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UsuarioService {

    private final UsuarioClient usuarioClient;


    public UsuarioDTO salvarUsuario(UsuarioDTO usuarioDTO) {
        return usuarioClient.salvarUsuario(usuarioDTO);
    }

    public String loginUsuario(UsuarioDTO usuarioDTO) {
        return usuarioClient.login(usuarioDTO);
    }

    public UsuarioDTO buscarUsuarioPorEmail(String email, String token) {
        return usuarioClient.buscarUsuarioPorEmail(email, token);
    }

    public void deletarUsuarioPorEmail(String email, String token) {
        usuarioClient.deletaUsuarioPorEmail(email, token);
    }

    public UsuarioDTO atualziarDadosUsuario(UsuarioDTO usuarioDTO, String token) {
        return usuarioClient.atualizarUsuario(usuarioDTO, token);
    }

    public EnderecoDTO atualizarEndereco(Long id, EnderecoDTO enderecoDTO, String token) {
        return usuarioClient.atualizarEndereco(enderecoDTO, id, token);
    }

    public TelefoneDTO atualizarTelefone(Long id, TelefoneDTO telefone, String token) {
        return usuarioClient.atualizarUsuario(telefone, id, token);
    }

    public EnderecoDTO cadastrarEndereco(EnderecoDTO enderecoDTO, String token) {
        return usuarioClient.cadastroEndereco(enderecoDTO, token);
    }

    public TelefoneDTO cadastrarTelefone(TelefoneDTO telefoneDTO, String token) {
        return usuarioClient.cadastroTelefone(telefoneDTO, token);
    }
}
