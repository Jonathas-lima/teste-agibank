package br.al.mcz.agibank.filereader.entities.types;

import br.al.mcz.agibank.filereader.exceptions.TipoDadoInvalidoException;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.Arrays;

@AllArgsConstructor
@Getter
public enum TipoDado {

    VENDEDOR("001"),
    CLIENTE("002"),
    VENDA("003");

    private final String codigo;

    public static TipoDado obterPorCodigo(String codigo){
        return Arrays.stream(TipoDado.values())
                .filter(tipoDado -> tipoDado.getCodigo().equals(codigo))
                .findFirst()
                .orElseThrow(TipoDadoInvalidoException::new);
    }

    public static TipoDado obterTipoDadoPorLinha(String dados) {
        return obterPorCodigo(dados.substring(0, 3));
    }
}
