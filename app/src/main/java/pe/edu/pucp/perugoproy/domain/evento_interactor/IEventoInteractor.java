package pe.edu.pucp.perugoproy.domain.evento_interactor;

import java.util.List;

import pe.edu.pucp.perugoproy.data.entities.Evento;

public interface IEventoInteractor {
    interface IEventoCallBack {
        void onSuccess(List<Evento> listaEventos);
        void onError(String errorMsg);
    }

    void getAllEventos(IEventoCallBack callBack);
}
