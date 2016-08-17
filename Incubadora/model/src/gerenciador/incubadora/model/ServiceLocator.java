package gerenciador.incubadora.model;

import gerenciador.incubadora.model.service.AmbienteExternoService;
import gerenciador.incubadora.model.service.AmbienteInternoService;
import gerenciador.incubadora.model.service.AvaliadorService;
import gerenciador.incubadora.model.service.CategoriaService;
import gerenciador.incubadora.model.service.CronogramaAnualService;
import gerenciador.incubadora.model.service.CronogramaManagerService;
import gerenciador.incubadora.model.service.EditalService;
import gerenciador.incubadora.model.service.EmpreendedorService;
import gerenciador.incubadora.model.service.EmpreendimentoService;
import gerenciador.incubadora.model.service.EstagioEvolucaoService;
import gerenciador.incubadora.model.service.GestorService;
import gerenciador.incubadora.model.service.OrientacaoService;
import gerenciador.incubadora.model.service.AcaoService;
import gerenciador.incubadora.model.service.ApresentacaoNegocioService;
import gerenciador.incubadora.model.service.ArquivoService;
import gerenciador.incubadora.model.service.AvaliacaoService;
import gerenciador.incubadora.model.service.CriterioAvaliacaoService;
import gerenciador.incubadora.model.service.DuvidaService;
import gerenciador.incubadora.model.service.EixoService;
import gerenciador.incubadora.model.service.EmailService;
import gerenciador.incubadora.model.service.EmpreendimentoEmpreendedorService;
import gerenciador.incubadora.model.service.NotaService;
import gerenciador.incubadora.model.service.ProcessoChaveService;
import gerenciador.incubadora.model.service.PraticaChaveService;
import gerenciador.incubadora.model.service.RamoAtividadeService;
import gerenciador.incubadora.model.service.RespostaDuvidaService;
import gerenciador.incubadora.model.service.UsuarioService;

public class ServiceLocator {
   
   public static ArquivoService getArquivoService(){
      return new ArquivoService();
   }
   
    public static RamoAtividadeService getRamoAtividade() {
        return new RamoAtividadeService();
    }

    public static UsuarioService getUsuarioService() {
        return new UsuarioService();
    }

    public static EmpreendimentoService getEmpreendimentoService() {
        return new EmpreendimentoService();
    }

    public static ProcessoChaveService getProcessoChaveService() {
        return new ProcessoChaveService();
    }

    public static PraticaChaveService getPraticaChaveService() {
        return new PraticaChaveService();
    }

    public static GestorService getGestorService() {
        return new GestorService();
    }

    public static EmpreendedorService getEmpreendedorService() {
        return new EmpreendedorService();
    }

    public static EstagioEvolucaoService getEstagioEvolucaoService() {
        return new EstagioEvolucaoService();
    }

    public static CronogramaAnualService getCronogramaAnualService() {
        return new CronogramaAnualService();
    }

    public static CronogramaManagerService getCronogramaManagerService() {
        return new CronogramaManagerService();
    }

    public static OrientacaoService getOrientacaoService() {
        return new OrientacaoService();
    }

    public static AcaoService getAcaoService() {
        return new AcaoService();
    }

    public static AmbienteExternoService getAmbienteExternoService() {
        return new AmbienteExternoService();
    }

    public static AmbienteInternoService getAmbienteInternoService() {
        return new AmbienteInternoService();
    }

    public static CategoriaService getCategoriaService() {
        return new CategoriaService();
    }

    public static EditalService getEditalService() {
        return new EditalService();
    }

    public static AvaliadorService getAvaliadorService() {
        return new AvaliadorService();
    }

    public static ApresentacaoNegocioService getApresentacaoNegocioService() {
        return new ApresentacaoNegocioService();
    }

    public static EixoService getEixoService() {
        return new EixoService();
    }

    public static CriterioAvaliacaoService getCriterioAvaliacaoService() {
        return new CriterioAvaliacaoService();
    }

    public static DuvidaService getDuvidaService() {
        return new DuvidaService();
    }

    public static RespostaDuvidaService getRespostaDuvidaService() {
        return new RespostaDuvidaService();
    }

    public static NotaService getNotaService() {
        return new NotaService();
    }

    public static EmailService getEmailService() {
        return new EmailService();
    }
    
    public static EmpreendimentoEmpreendedorService getEmpreendimentoEmpreendedorService(){
        return new EmpreendimentoEmpreendedorService();
    }
    
    public static AvaliacaoService getAvaliacaoService(){
        return new AvaliacaoService();
    }
}
