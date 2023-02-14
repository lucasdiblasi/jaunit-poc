package br.ce.poc.servicos;

import static br.ce.poc.utils.DataUtils.adicionarDias;

import java.util.Date;

import org.junit.Test;
import org.testng.ITest;

import br.ce.poc.entidades.Filme;
import br.ce.poc.entidades.Locacao;
import br.ce.poc.entidades.Usuario;
import br.ce.poc.utils.DataUtils;

public class LocacaoService {
	
	public Locacao alugarFilme(Usuario usuario, Filme filme) {
		Locacao locacao = new Locacao();
		locacao.setFilme(filme);
		locacao.setUsuario(usuario);
		locacao.setDataLocacao(new Date());
		locacao.setValor(filme.getPrecoLocacao());

		//Entrega no dia seguinte
		Date dataEntrega = new Date();
		dataEntrega = adicionarDias(dataEntrega, 1);
		locacao.setDataRetorno(dataEntrega);
		
		//Salvando a locacao...	
		//TODO adicionar método para salvar
		
		return locacao;
	}
	@Test
	public void test() {
		//Scneario
		LocacaoService service = new LocacaoService();
		Usuario usuario = new Usuario("Usuario 1");
		Filme filme = new Filme("Filme 1", 2, 5.0);

		//acao
		Locacao locacao = service.alugarFilme(usuario, filme);

		//verificacao
		Assert.assertTrue(locacao.getValor() == 4.0);
		Assert.assertTrue(DataUtils.isMesmaData(locacao.getDataLocacao(), new Date()));
		Assert.assertTrue(DataUtils.isMesmaData(locacao.getDataRetorno(), DataUtils.obterDataComDiferencaDias(1)));
	}

	public static void main(String[] args) {
		
	}
}