package net.copaba.configurandomipropioendpointenmiservidor.presentador;

/**
 * Created by Polo on 01/09/16.
 */
public interface IRecyclerViewFragmentPresenter {
    public void getPetsBaseDatos();
    void obtenUsuariosSandbox();
    public void showPetsRV();
    void obtenerMediosRecientes();
    void obtenerMediosRecientes(String userID);
}
