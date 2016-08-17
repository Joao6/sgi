package gerenciador.incubadora.model.base.service;

import gerenciador.incubadora.model.base.BaseService;
import gerenciador.incubadora.model.entity.Empreendimento;
import java.io.InputStream;
import java.util.Map;

public interface BaseEmpreendimentoService extends BaseService<Empreendimento> {

    public void uplaod(byte[] bytes, Empreendimento empreendimento) throws Exception;

    public InputStream download(Long id) throws Exception;

    public Map<String, String> validateDataHoraApresentacao(Map<String, Object> fields) throws Exception;
    
}
