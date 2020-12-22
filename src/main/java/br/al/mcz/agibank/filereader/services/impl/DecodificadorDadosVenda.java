package br.al.mcz.agibank.filereader.services.impl;

import br.al.mcz.agibank.filereader.entities.ItemVenda;
import br.al.mcz.agibank.filereader.entities.Venda;
import br.al.mcz.agibank.filereader.services.DecodificadorDados;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import static br.al.mcz.agibank.filereader.shared.Constantes.REGEX_COLCHETES;
import static br.al.mcz.agibank.filereader.shared.Constantes.STRING_VAZIA;
import static br.al.mcz.agibank.filereader.shared.Constantes.Separadores.*;

@Component
public class DecodificadorDadosVenda implements DecodificadorDados<Venda> {

    @Override
    public Venda decodificar(String dados) {

        String[] dadosVenda = dados.split(SEPARDOR_DADOS);

        return Venda.builder()
                .idVenda(Integer.valueOf(dadosVenda[1]))
                .listaItens(obterItensVenda(dadosVenda[2]))
                .nomeVendedor(dadosVenda[3])
                .build();
    }

    private List<ItemVenda> obterItensVenda(String dados){
        dados = dados.replaceAll(REGEX_COLCHETES, STRING_VAZIA);

       return Arrays.stream(dados.split(SEPARADOR_ITEM))
               .map(this::obterUmItem)
               .collect(Collectors.toList());
    }

    private ItemVenda obterUmItem(String dadosItens){
        String[] item = dadosItens.split(SEPARADORES_DADOS_ITEM);
        return criarObjetoItemDe(item);
    }

    private ItemVenda criarObjetoItemDe(String[] item) {
        return ItemVenda.builder()
                .itemId(Integer.valueOf(item[0]))
                .quantidade(Integer.valueOf(item[1]))
                .preco(new BigDecimal(item[2]))
                .build();
    }
}
