package gerenciador.incubadora.model.base.service;

public interface BaseEmailService {

    public void sendEmail(String email, String assunto, String texto) throws Exception;

}
