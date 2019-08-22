package pe.edu.pucp.perugoproy.presentation.evento;

import java.util.List;

import pe.edu.pucp.perugoproy.data.entities.Evento;

public interface IEventoContract {
    interface IView{
        void showError(String errorMsg);
        void showProgressBar();
        void hideProgressBar();
        void getAllEventosSuccess(List<Evento> listaEventos);
        void gotToDetalleEvento(int idEvento);
    }

    interface IPresenter{
        // las 3 sgtes funciones validan que el presentador siga activo
        void attachView(IView view);
        void detachView();
        boolean isViewAttached();
        // obtener todos los eventos
        void getAllEventos();
    }
}
