package ohtu.ohtuvarasto;

import org.junit.*;
import static org.junit.Assert.*;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

public class VarastoTest {

    Varasto varasto;
    Varasto varasto2;
    Varasto varasto3;
    Varasto varasto4;
    Varasto varasto5;
    Varasto varasto6;
    double vertailuTarkkuus = 0.0001;

    @Before
    public void setUp() {
        varasto = new Varasto(10);
        varasto2 = new Varasto(10, 11);
        varasto3 = new Varasto(-5);
        varasto4 = new Varasto(-5, 2);
        varasto5 = new Varasto(5, -7);
        varasto6 = new Varasto(10, 8);
    }

    @Test
    public void konstruktoriLuoTyhjanVaraston() {
        assertEquals(0, varasto.getSaldo(), vertailuTarkkuus);
    }

    @Test
    public void uudellaVarastollaOikeaTilavuus() {
        assertEquals(10, varasto.getTilavuus(), vertailuTarkkuus);
    }
    @Test

    public void uudellaVarastollaOikeaTilavuusneg() {
        assertEquals(0, varasto3.getTilavuus(), vertailuTarkkuus);
    }

    @Test
    public void uudellaVarastollaOikeaTilavuusJaSaldoKunYli() {
        assertEquals(10, varasto2.getSaldo(), vertailuTarkkuus);
    }

    @Test
    public void uudellaVarastollaOikeaTilavuusJaSaldo() {
        assertEquals(8, varasto6.getSaldo(), vertailuTarkkuus);
    }
    @Test

    public void uudellaVarastollaOikeaTilavuusJaSaldoneg() {
        assertEquals(0, varasto4.getTilavuus(), vertailuTarkkuus);
    }
    @Test
    public void uudellaVarastollaOikeaSaldo() {
        assertEquals(0, varasto5.getSaldo(), vertailuTarkkuus);
    }
    @Test
    public void uudellaVarastollaOikeaVapaaTila() {
        assertEquals(0, varasto2.paljonkoMahtuu(), vertailuTarkkuus);
    }

    @Test
    public void lisaysLisaaSaldoa() {
        varasto.lisaaVarastoon(8);

        // saldon pitäisi olla sama kun lisätty määrä
        assertEquals(8, varasto.getSaldo(), vertailuTarkkuus);
    }

    @Test
    public void lisaysLisaaPienentaaVapaataTilaa() {
        varasto.lisaaVarastoon(8);

        // vapaata tilaa pitäisi vielä olla tilavuus-lisättävä määrä eli 2
        assertEquals(2, varasto.paljonkoMahtuu(), vertailuTarkkuus);
    }

    @Test
    public void ottaminenPalauttaaOikeanMaaran() {
        varasto.lisaaVarastoon(8);

        double saatuMaara = varasto.otaVarastosta(2);

        assertEquals(2, saatuMaara, vertailuTarkkuus);
    }

    @Test
    public void ottaminenLisääTilaa() {
        varasto.lisaaVarastoon(8);

        varasto.otaVarastosta(2);

        // varastossa pitäisi olla tilaa 10 - 8 + 2 eli 4
        assertEquals(4, varasto.paljonkoMahtuu(), vertailuTarkkuus);
    }

    @Test
    public void lisäysEiLisääTilavuutta() {
        varasto.lisaaVarastoon(12);
        assertEquals(10, varasto.getSaldo(), vertailuTarkkuus);
    }

    @Test
    public void lisääNegatiivinen() {
        varasto.lisaaVarastoon(5);
        varasto.lisaaVarastoon(-2);
        assertEquals(5, varasto.getSaldo(), vertailuTarkkuus);
    }

    @Test
    public void ottaminenEiOtaLiikaa() {
        varasto.lisaaVarastoon(12);
        double saatu = varasto.otaVarastosta(11);
        assertEquals(10, saatu, vertailuTarkkuus);
    }

    @Test
    public void otaNegatiivinen() {
        varasto.lisaaVarastoon(11);

        varasto.otaVarastosta(-1);
        assertEquals(10, varasto.getSaldo(), vertailuTarkkuus);
    }
    @Test
    public void printSaldo() {
        String expected = "saldo = 0.0, vielä tilaa 10.0";
        assertEquals(expected, varasto.toString());
    }

}