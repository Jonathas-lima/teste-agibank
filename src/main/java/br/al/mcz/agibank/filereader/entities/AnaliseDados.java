package br.al.mcz.agibank.filereader.entities;

import br.al.mcz.agibank.filereader.exceptions.VendaNaoEncontradaException;
import br.al.mcz.agibank.filereader.exceptions.VendedorNaoEncontradoException;
import lombok.Builder;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static java.util.Comparator.comparing;

@Builder
public class AnaliseDados implements Serializable {

    private final List<Venda> vendas;
    private final List<Cliente> clientes;
    private final List<Vendedor> vendedores;

    public Integer obterQuantidadeClientes() {
        return clientes.size();
    }

    public Integer obterQuantidadeVendedores() {
        return vendedores.size();
    }

    public Integer obterIdVendaMaisCara() {
        return this.vendas.stream()
                .max(comparing(Venda::obterValorVenda))
                .map(Venda::getIdVenda)
                .orElseThrow(VendaNaoEncontradaException::new);
    }

    public Vendedor obterPiorVendedor(){
        Map<String, BigDecimal> valorVendaPorVendedor = obterValoresVendasPorVendedor();
        String nomeVendedor = obterNomePiorVendedor(valorVendaPorVendedor);

        return this.vendedores.stream()
                .filter(v -> v.getNome().equals(nomeVendedor))
                .findFirst()
                .orElseThrow(VendedorNaoEncontradoException::new);
    }

    private String obterNomePiorVendedor(Map<String, BigDecimal> valores){
        return  valores.entrySet()
                .stream()
                .min(Map.Entry.comparingByValue())
                .orElseThrow(VendedorNaoEncontradoException::new).getKey();
    }

    private Map<String, BigDecimal> obterValoresVendasPorVendedor() {
        Map<String, BigDecimal> valorVendaPorVendedor = new HashMap<>();

        vendedores.forEach(vendedor -> {
            BigDecimal valorVendas = this.vendas.stream()
                    .filter(venda -> venda.getNomeVendedor().equals(vendedor.getNome()))
                    .map(Venda::obterValorVenda)
                    .reduce(BigDecimal.ZERO, BigDecimal::add);
            valorVendaPorVendedor.put(vendedor.getNome(), valorVendas);
        });
        return valorVendaPorVendedor;
    }

    public String obterResumoAnalise() {
        return "Quantidade de clientes: " + this.obterQuantidadeClientes() + "\n" +
               "Quantidade de vendedores: " + this.obterQuantidadeVendedores() + "\n" +
               "Pior vendedor: " + this.obterPiorVendedor().getNome()+ " ** Número do documento: "+ this.obterPiorVendedor().getNumeroDocumento() + "\n" +
               "ID venda mais cara: " + this.obterIdVendaMaisCara();
    }

}
