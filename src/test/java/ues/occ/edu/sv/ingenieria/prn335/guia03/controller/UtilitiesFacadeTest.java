/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ues.occ.edu.sv.ingenieria.prn335.guia03.controller;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.Query;
import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import ues.occ.edu.sv.ingenieria.prn335.cineData.entity.Funcion;
import ues.occ.edu.sv.ingenieria.prn335.cineData.entity.Sala;


/**
 *
 * @author kevin0022
 */

@ExtendWith(MockitoExtension.class)
public class UtilitiesFacadeTest {
    
    public UtilitiesFacadeTest() {
    }
    
    
    /**
     * Test del metodo SalasporFuncion
     */
    @Test
    public void testTipoAsiento() throws Exception {
        System.out.println("tipoAsiento");
        UtilitiesFacade cut = new UtilitiesFacade();
        EntityManager emmock = Mockito.mock(EntityManager.class);
        Query qMock=Mockito.mock(Query.class);
        cut.em = emmock;
        cut.c = qMock;
        List<Sala> expResult = new ArrayList<>();
        Sala sa = new Sala();
        sa.setIdSala(1);
        sa.setSala("sala 2");
        expResult.add(sa);
        sa.setIdSala(2);
        sa.setSala("sala 3");
        expResult.add(sa);
        sa.setIdSala(3);
        sa.setSala("sala 4");
        expResult.add(sa);
        Mockito.when(emmock.createQuery("SELECT s FROM Sala s LEFT JOIN s.asientoSalaList a LEFT JOIN a.asiento.atributoAsientoList q LEFT JOIN q.caracteristicaAsiento c WHERE c.idCaracteristica=2")).thenReturn(qMock);
        Mockito.when(qMock.getResultList()).thenReturn(expResult);
        List<Sala> result = cut.tipoAsiento();
        assertEquals(expResult, result);
       
    }
    
    
     /**
     * Test del metodo FuncionPorDirector
     */
    @Test
    public void testFuncionPorDirector() throws Exception {
        System.out.println("funcionPorDirector");
        UtilitiesFacade cut = new UtilitiesFacade();
        EntityManager emmock = Mockito.mock(EntityManager.class);
        Query qMock=Mockito.mock(Query.class);
        cut.em = emmock;
        cut.c = qMock;
        List<Funcion> expResult = new ArrayList<>();
        Funcion fun=new Funcion();
        Date fecha = new Date();
        fecha.setYear(2019);
        fecha.setMonth(10);
        fecha.setDate(12);
        fun.setIdFuncion(1);
        fun.setFecha(fecha);
        expResult.add(fun);
        fecha.setYear(2018);
        fecha.setMonth(9);
        fecha.setDate(7);
        fun.setIdFuncion(2);
        fun.setFecha(fecha);
        expResult.add(fun);
        Mockito.when(emmock.createQuery("SELECT f FROM Funcion f JOIN f.idPelicula p JOIN p.idDirector d WHERE d.idDirector=1")).thenReturn(qMock);
        Mockito.when(qMock.getResultList()).thenReturn(expResult);
        List<Funcion> result = cut.funcionPorDirector("steven spielberg");
        assertEquals(expResult, result);

    }
    
    
    /**
     * Test del metodo SalasPorFuncion
     */
     @Test
    public void testSalasPorFuncion() throws Exception {
        System.out.println("salasPorFuncion");
        UtilitiesFacade cut = new UtilitiesFacade();
        EntityManager emmock = Mockito.mock(EntityManager.class);
        Query qMock=Mockito.mock(Query.class);
        cut.em = emmock;
        cut.c = qMock;
        List<Sala> expResult = new ArrayList<>();
        Sala sa = new Sala();
        sa.setIdSala(1);
        sa.setSala("sala 2");
        expResult.add(sa);
        sa.setIdSala(2);
        sa.setSala("sala 3");
        expResult.add(sa);
        sa.setIdSala(3);
        sa.setSala("sala 4");
        expResult.add(sa);
        Mockito.when(emmock.createQuery("SELECT s FROM Sala s LEFT JOIN s.asientoSalaList a  LEFT JOIN a.boletoList b LEFT JOIN b.idFuncion f WHERE f.fecha>'2019-09-01'")).thenReturn(qMock);
        Mockito.when(qMock.getResultList()).thenReturn(expResult);
        List<Sala> result =cut.salasPorFuncion(Mockito.any());
        assertEquals(expResult, result);

    }
   
}
