package pe.edu.pucp.perugoproy.base;

public interface BasePresenter<V extends BaseView> {

    void attach(V view);

    void detach();
}