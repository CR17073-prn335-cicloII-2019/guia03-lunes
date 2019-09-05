package ues.occ.edu.sv.ingenieria.prn335.guia03.controller;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import ues.occ.edu.sv.ingenieria.prn335.cineData.entity.Sala;
import ues.occ.edu.sv.ingenieria.prn335.cineData.entity.Funcion;

/**
 *
 * @author kevin0022
 */
@Stateless
@LocalBean
public class UtilitiesFacade implements Serializable {
    
    public EntityManager em;
    public Query c;
    
     /**
     * Metodo para consultar sobre asientos reclinables
     * @return Lista de salas.
     */
    public List<Sala> tipoAsiento() {
        List<Sala> lista = new ArrayList();
        try {
            if (em != null && c != null) {
                c = em.createQuery("SELECT s FROM Sala s LEFT JOIN s.asientoSalaList a LEFT JOIN a.asiento.atributoAsientoList q LEFT JOIN q.caracteristicaAsiento c WHERE c.idCaracteristica=2");
                if (c != null) {
                    lista = c.getResultList();
                    return lista;
                }
            } else {
                throw new IllegalStateException("-");
            }
        } catch (Exception e) {
            Logger.getLogger(getClass().getName()).log(Level.SEVERE, e.getMessage());
        }

        return Collections.EMPTY_LIST;
    }
 
       /**
     * Metodo el director de peliculas el que se a indicado.
     * @param director - String con el nombre del director
     * @return Lista que cumple lo que se pide
     */
        public List<Funcion> funcionPorDirector(String director) {
        List<Funcion> lista = new ArrayList();
        try {
            if (em != null && c != null) {
                Query c;
                c = em.createQuery("SELECT f FROM Funcion f JOIN f.idPelicula p JOIN p.idDirector d WHERE d.idDirector=1");
                if (c != null) {
                    lista = c.getResultList();
                    return lista;
                }
            } else {
                throw new IllegalStateException("Error:v");
            }
        } catch (Exception e) {
            Logger.getLogger(getClass().getName()).log(Level.SEVERE, e.getMessage());
        }

        return Collections.EMPTY_LIST;
    }
        
        /**
     * Metodo que lista la sala que tengan funcion despues de la hora indicada
     * @param fecha - Fecha para ver las funciones despues de ella.
     * @return Lista de Salas que cumplen
     */
        public List<Sala> salasPorFuncion(Date fecha) {
        List<Sala> lista = new ArrayList();

        try {
            if (em != null) {
                Query c;
                c = em.createQuery("SELECT s FROM Sala s LEFT JOIN s.asientoSalaList a  LEFT JOIN a.boletoList b LEFT JOIN b.idFuncion f WHERE f.fecha>'2019-09-01'");
                lista = c.getResultList();
                return lista;
            } else {
                throw new IllegalStateException("Error");
            }
        } catch (Exception e) {
            Logger.getLogger(getClass().getName()).log(Level.SEVERE, e.getMessage());
        }

        return Collections.EMPTY_LIST;
    }
    
    
}
