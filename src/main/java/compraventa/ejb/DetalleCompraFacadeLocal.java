/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package compraventa.ejb;

import compraventa.entity.DetalleCompra;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author marcelo
 */
@Local
public interface DetalleCompraFacadeLocal {

    void create(DetalleCompra detalleCompra);

    void edit(DetalleCompra detalleCompra);

    void remove(DetalleCompra detalleCompra);

    DetalleCompra find(Object id);

    List<DetalleCompra> findAll();

    List<DetalleCompra> findRange(int[] range);

    int count();
    
}
