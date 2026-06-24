package com.example.QuatroBytes.service;

import com.example.QuatroBytes.repository.ClienteRepository;
import com.example.QuatroBytes.repository.ProdutoRepository;
import com.example.QuatroBytes.repository.VendaRepository;
import org.springframework.stereotype.Service;

@Service
public class VendaService {
    ProdutoRepository produtoRepository;
    VendaRepository vendaRepository;
    ClienteRepository clienteRepository;
    UsuarioService usuarioService;

    public VendaService(ProdutoRepository produtoRepository, VendaRepository vendaRepository, ClienteRepository clienteRepository, UsuarioService usuarioService) {
        this.produtoRepository = produtoRepository;
        this.vendaRepository = vendaRepository;
        this.clienteRepository = clienteRepository;
        this.usuarioService = usuarioService;
    }



}
