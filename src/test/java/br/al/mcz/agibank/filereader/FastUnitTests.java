package br.al.mcz.agibank.filereader;


import br.al.mcz.agibank.filereader.entities.AnaliseDadosTest;
import br.al.mcz.agibank.filereader.entities.VendaTest;
import br.al.mcz.agibank.filereader.entities.types.TipoDadoTest;
import br.al.mcz.agibank.filereader.services.impl.DecodificadorDadosServiceClienteTest;
import br.al.mcz.agibank.filereader.services.impl.DecodificadorDadosServiceVendaTest;
import br.al.mcz.agibank.filereader.services.impl.DecodificadorDadosServiceVendedorTest;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;


@RunWith(Suite.class)
@Suite.SuiteClasses(
        {
                TipoDadoTest.class,
                AnaliseDadosTest.class,
                VendaTest.class,
                DecodificadorDadosServiceClienteTest.class,
                DecodificadorDadosServiceVendaTest.class,
                DecodificadorDadosServiceVendedorTest.class
        })
public class FastUnitTests { }
