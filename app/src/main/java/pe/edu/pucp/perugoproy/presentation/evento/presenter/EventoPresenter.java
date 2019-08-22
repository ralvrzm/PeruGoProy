package pe.edu.pucp.perugoproy.presentation.evento.presenter;

import java.util.List;

import pe.edu.pucp.perugoproy.data.entities.Evento;
import pe.edu.pucp.perugoproy.domain.evento_interactor.IEventoInteractor;
import pe.edu.pucp.perugoproy.presentation.evento.IEventoContract;

public class EventoPresenter implements IEventoContract.IPresenter  {
    IEventoContract.IView view;
    IEventoInteractor interactor;

    @Override
    public void attachView(IEventoContract.IView view) {
        this.view = view;
    }

    @Override
    public void detachView() {
        view = null;
    }

    @Override
    public boolean isViewAttached() {
        return view != null;
    }

    @Override
    public void getAllEventos() {
        view.showProgressBar();
        interactor.getAllEventos(new IEventoInteractor.IEventoCallBack() {
            @Override
            public void onSuccess(List<Evento> listaEventos) {
                if (isViewAttached()) {
                    view.getAllEventosSuccess(listaEventos);
                    view.hideProgressBar();
                }
            }

            @Override
            public void onError(String errorMsg) {
                if (isViewAttached()) {
                    view.showError(errorMsg);
                    view.hideProgressBar();
                }
            }
        });

    }
}
