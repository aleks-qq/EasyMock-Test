/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import static org.easymock.EasyMock.createMock;
import static org.easymock.EasyMock.expect;
import static org.easymock.EasyMock.replay;
import static org.easymock.EasyMock.verify;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author alekssk
 */
public class testaaKäsittelijäWithEasyMockHinnoittelija2 {
    
    @Test
    public void testaaKäsittelijäWithEasyMockHinnoittelija2() {
        // arrange
        float alkuSaldo = 100.0f;
        float listaHinta = 130.0f;
 
        float alennus = 20.0f;
        float loppuSaldo = alkuSaldo - (listaHinta * (1 - alennus / 100));

        Asiakas asiakas = new Asiakas(alkuSaldo);
        Tuote tuote = new Tuote("TDD in Action", listaHinta);
        Hinnoittelija hinnoittelija = createMock(Hinnoittelija.class);

        // record
        hinnoittelija.aloita();
        expect(hinnoittelija.getAlennusProsentti(asiakas, tuote))
                .andReturn(alennus);
        
        
       hinnoittelija.setAlennusProsentti(asiakas, alennus + 5);
         

        expect(hinnoittelija.getAlennusProsentti(asiakas, tuote))
                .andReturn(alennus);
        
        hinnoittelija.lopeta();
        replay(hinnoittelija);
        
        
        // act
        TilaustenKäsittely käsittelijä = new TilaustenKäsittely();
        käsittelijä.setHinnoittelija(hinnoittelija);
        käsittelijä.käsittele(new Tilaus(asiakas, tuote));
        // assert
        assertEquals(loppuSaldo, asiakas.getSaldo(), 0.001);
        verify(hinnoittelija);
    }
}
