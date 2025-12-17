package com.marce.service;
import com.marce.repository.ParqueaderoDatos;

public class GestorIngreso implements IValidator {
    public boolean registrarIngreso(String placa) {
        return ParqueaderoDatos.getInstance().existePlaca(placa);
    }
    @Override
    public boolean exitePlaca(String placa) {
        return ParqueaderoDatos.getInstance().existePlaca(placa);
    }
    @Override
    public void realizarSalida(String placa) {
        ParqueaderoDatos.getInstance().registrarSalida(placa);
    }
}