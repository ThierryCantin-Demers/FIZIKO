package fiziko.tests_unitaires;

import fiziko.modele.moteurdephysique.EquationsProjectile;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

class EquationsProjectileTest {
    EquationsProjectile ep = new EquationsProjectile();

    @Test
    void testEquationsProjectile() {
        assertTrue(ep.getPositionInitX() == 0);
        assertTrue(ep.getPositionInitY() == 0);
        assertTrue(ep.getPositionFinX() == 0);
        assertTrue(ep.getPositionFinX() == 0);
        assertTrue(ep.getVariationPositionX() == 0);
        assertTrue(ep.getVariationPositionY() == 0);
        assertTrue(ep.getVitesseInit() == 0);
        assertTrue(ep.getVitesseInitX() == 0);
        assertTrue(ep.getVitesseInitY() == 0);
        assertTrue(ep.getVitesseFin() == 0);
        assertTrue(ep.getVitesseFinX() == 0);
        assertTrue(ep.getVitesseFinY() == 0);
        assertTrue(ep.getVariationVitesse() == 0);
        assertTrue(ep.getVariationVitesseX() == 0);
        assertTrue(ep.getVariationVitesseY() == 0);
        assertTrue(ep.getChampGravitationnel() == -9.80665);
        assertTrue(ep.getTempsInit() == 0);
        assertTrue(ep.getTempsFin() == 0);
        assertTrue(ep.getVariationTemps() == 0);
        assertTrue(ep.getAngleInit() == 0);
        assertTrue(ep.getAngleFin() == 0);
        assertTrue(ep.getAcceleration() == 0);
        assertTrue(ep.getAccelerationX() == 0);
        assertTrue(ep.getAccelerationY() == 0);
        assertTrue(ep.getVitesseMoyenneX() == 0);
        assertTrue(ep.getVitesseMoyenneY() == 0);
    }

    @Test
    void testCalculerPositionFinX() {
        ep.setPositionInitX(10);
        ep.setVitesseInitX(25);
        ep.setVariationTemps(42);
        assertTrue(ep.calculerPositionFinX() == 1060);

        ep.setPositionInitX(-73);
        ep.setVitesseInitX(-37);
        ep.setVariationTemps(50);
        assertTrue(ep.calculerPositionFinX() == -1923);

    }

    @Test
    void testCalculerPositionFinY() {
        ep.setPositionInitY(100);
        ep.setVitesseInitY(35);
        ep.setVariationTemps(120);
        assertTrue(ep.calculerPositionFinY() == -66307.87999999999);

        ep.setPositionInitY(-250);
        ep.setVitesseInitY(57);
        ep.setVariationTemps(19);
        assertTrue(ep.calculerPositionFinY() == -937.1003249999999);
    }

    @Test
    void testCalculerVitesseFinX() {
        ep.setVitesseInitX(59);
        assertTrue(ep.calculerVitesseFinX() == 59);

        ep.setVitesseInitX(-987);
        assertTrue(ep.calculerVitesseFinX() == -987);
    }

    @Test
    void testCalculerVitesseFinY() {
        ep.setVitesseInitY(59);
        ep.setVariationTemps(27);
        assertEquals(ep.calculerVitesseFinY(), -205.77954999999997);

        ep.setVitesseInitY(-1024);
        ep.setVariationTemps(35);
        ep.setChampGravitationnel(10.65);
        assertEquals(ep.calculerVitesseFinY(), -651.25);
    }

    @Test
    void testCalculerVitesseMoyenneX() {
        ep.setVariationTemps(53);
        ep.setVariationPositionX(1537.89);
        assertEquals(ep.calculerVitesseMoyenneX(), 29.01679245283019);

        ep.setVariationTemps(58);
        ep.setVariationPositionX(-5395);
        assertEquals(ep.calculerVitesseMoyenneX(), -93.01724137931035);

    }

    @Test
    void testCalculerVitesseMoyenneY() {
        ep.setVariationTemps(55.5);
        ep.setVariationPositionX(66.75);
        assertEquals(ep.calculerVitesseMoyenneX(), 1.2027027027027026);

        ep.setVariationTemps(11);
        ep.setVariationPositionX(-127);
        assertEquals(ep.calculerVitesseMoyenneX(), -11.545454545454545);
    }

    @Test
    void testCalculerVitesseFinTemps() {
        ep.setVitesseInitX(12.7);
        ep.setAccelerationX(3.45);
        ep.setVariationTemps(22);
        assertEquals(ep.calculerVitesseFinTemps("X"), 12.7);

        ep.setVitesseInitY(24.6);
        ep.setAccelerationY(1.8);
        ep.setVariationTemps(12);
        assertEquals(ep.calculerVitesseFinTemps("Y"), 46.2);

        assertEquals(ep.calculerVitesseFinTemps("asdasd"), Double.NaN);
    }

    @Test
    void testCalculerDeplacement() {
        ep.setVitesseInitX(10.7);
        ep.setVitesseFinX(90.37);
        ep.setVariationTemps(30.5);
        assertEquals(ep.calculerDeplacement("X"), 1388.8425000000002);

        ep.setVitesseInitY(0);
        ep.setVitesseFinY(102.4);
        ep.setVariationTemps(29);
        assertEquals(ep.calculerDeplacement("Y"), 1484.8000000000002);

        assertEquals(ep.calculerDeplacement("sfdghasdwqe213"), Double.NaN);

    }

    @Test
    void testCalculerVitesseFinDeplacement() {
        ep.setVitesseInitX(10);
        ep.setAccelerationX(50.75);
        ep.setVariationPositionX(1000);
        assertEquals(ep.calculerVitesseFinDeplacement("X"), 318.74754901018457);

        ep.setVitesseInitY(455);
        ep.setAccelerationY(-65.5);
        ep.setVariationPositionY(-750);
        assertEquals(ep.calculerVitesseFinDeplacement("Y"), 552.5169680652351);

        assertEquals(ep.calculerVitesseFinDeplacement("jambon634512lpk"),
                Double.NaN);
    }

    @Test
    void testCalculerAcceleration() {
        ep.setVariationVitesseX(12.64);
        ep.setVariationTemps(3.7);
        assertEquals(ep.calculerAcceleration("X"), 3.4162162162162164);

        ep.setVariationVitesseY(38.45);
        ep.setVariationTemps(8.9);
        assertEquals(ep.calculerAcceleration("Y"), 4.320224719101124);

        ep.setVariationVitesse(20);
        ep.setVariationTemps(4);
        assertEquals(ep.calculerAcceleration(""), 5);

        assertEquals(ep.calculerAcceleration("aopweapiofje213sfdg"),
                Double.NaN);
    }

    @Test
    void testCalculerDeplacementVitesseInitiale() {
        ep.setVitesseInitX(6.5);
        ep.setAccelerationX(3.2);
        ep.setVariationTemps(53);
        assertEquals(ep.calculerPositionFinVitesseInitiale("X"), 0.0);

        ep.setVitesseInitY(5);
        ep.setAccelerationY(70.7);
        ep.setVariationTemps(4.9);
        assertEquals(ep.calculerPositionFinVitesseInitiale("Y"), 0.0);

        assertEquals(ep.calculerPositionFinVitesseInitiale("sadasdasdasd"),
                Double.NaN);
    }

    @Test
    void testCalculerDeplacementVitesseFinale() {
        ep.setVitesseFinX(13.6);
        ep.setAccelerationX(4.5);
        ep.setVariationTemps(24.75);
        assertEquals(ep.calculerPositionFinVitesseFinale("X"), -1041.665625);

        ep.setVitesseFinY(457);
        ep.setAccelerationY(70.7);
        ep.setVariationTemps(4.9);
        assertEquals(ep.calculerPositionFinVitesseFinale("Y"), 1390.5465);

        assertEquals(ep.calculerPositionFinVitesseFinale("sadasdasdasd"),
                Double.NaN);
    }

    @Test
    void testGetPositionInitX() {
        ep.setPositionInitX(67.88);
        assertEquals(ep.getPositionInitX(), 67.88);

        ep.setPositionInitX(-142);
        assertEquals(ep.getPositionInitX(), -142);
    }

    @Test
    void testSetPositionInitX() {
        assertEquals(ep.getPositionInitX(), 0);
        ep.setPositionInitX(12);
        assertEquals(ep.getPositionInitX(), 12);

        ep.setPositionInitX(-3.3);
        assertEquals(ep.getPositionInitX(), -3.3);
    }

    @Test
    void testGetPositionInitY() {
        ep.setPositionInitY(9873);
        assertEquals(ep.getPositionInitY(), 9873);

        ep.setPositionInitY(-87);
        assertEquals(ep.getPositionInitY(), -87);
    }

    @Test
    void testSetPositionInitY() {
        assertEquals(ep.getPositionInitY(), 0);
        ep.setPositionInitY(73);
        assertEquals(ep.getPositionInitY(), 73);

        ep.setPositionInitY(-7.3);
        assertEquals(ep.getPositionInitY(), -7.3);
    }

    @Test
    void testGetPositionFinX() {
        ep.setPositionFinX(65.78);
        assertEquals(ep.getPositionFinX(), 65.78);

        ep.setPositionFinX(-87);
        assertEquals(ep.getPositionFinX(), -87);
    }

    @Test
    void testSetPositionFinX() {
        assertEquals(ep.getPositionFinX(), 0);
        ep.setPositionFinX(7998);
        assertEquals(ep.getPositionFinX(), 7998);

        ep.setPositionFinX(-5.4);
        assertEquals(ep.getPositionFinX(), -5.4);
    }

    @Test
    void testGetPositionFinY() {
        ep.setPositionFinY(43);
        assertEquals(ep.getPositionFinY(), 43);

        ep.setPositionFinY(-0.01);
        assertEquals(ep.getPositionFinY(), -0.01);
    }

    @Test
    void testSetPositionFinY() {
        assertEquals(ep.getPositionFinY(), 0);
        ep.setPositionFinY(43);
        assertEquals(ep.getPositionFinY(), 43);

        ep.setPositionFinY(-0.01);
        assertEquals(ep.getPositionFinY(), -0.01);
    }

    @Test
    void testGetVitesseInitX() {
        ep.setVitesseInitX(90.997);
        assertEquals(ep.getVitesseInitX(), 90.997);

        ep.setVitesseInitX(-87.2);
        assertEquals(ep.getVitesseInitX(), -87.2);
    }

    @Test
    void testSetVitesseInitX() {
        assertEquals(ep.getVitesseInitX(), 0);
        ep.setVitesseInitX(67);
        assertEquals(ep.getVitesseInitX(), 67);

        ep.setVitesseInitX(-26.47);
        assertEquals(ep.getVitesseInitX(), -26.47);
    }

    @Test
    void testGetVitesseInitY() {
        ep.setVitesseInitY(103);
        assertEquals(ep.getVitesseInitY(), 103);

        ep.setVitesseInitY(-3.7);
        assertEquals(ep.getVitesseInitY(), -3.7);
    }

    @Test
    void testSetVitesseInitY() {
        assertEquals(ep.getVitesseInitY(), 0);
        ep.setVitesseInitY(2024);
        assertEquals(ep.getVitesseInitY(), 2024);

        ep.setVitesseInitY(-37890.34);
        assertEquals(ep.getVitesseInitY(), -37890.34);
    }

    @Test
    void testGetVitesseFinX() {
        ep.setVitesseFinX(1.03);
        assertEquals(ep.getVitesseFinX(), 1.03);

        ep.setVitesseFinX(-56.89);
        assertEquals(ep.getVitesseFinX(), -56.89);
    }

    @Test
    void testSetVitesseFinX() {
        assertEquals(ep.getVitesseFinX(), 0);
        ep.setVitesseFinX(65);
        assertEquals(ep.getVitesseFinX(), 65);

        ep.setVitesseFinX(-56.67);
        assertEquals(ep.getVitesseFinX(), -56.67);
    }

    @Test
    void testGetVitesseFinY() {
        ep.setVitesseFinY(0.63);
        assertEquals(ep.getVitesseFinY(), 0.63);

        ep.setVitesseFinY(-98.76);
        assertEquals(ep.getVitesseFinY(), -98.76);
    }

    @Test
    void testSetVitesseFinY() {
        assertEquals(ep.getVitesseFinY(), 0);
        ep.setVitesseFinY(0.5867);
        assertEquals(ep.getVitesseFinY(), 0.5867);

        ep.setVitesseFinY(-1);
        assertEquals(ep.getVitesseFinY(), -1);
    }

    @Test
    void testGetChampGravitationnel() {
        ep.setChampGravitationnel(107.89);
        assertEquals(ep.getChampGravitationnel(), 107.89);

        ep.setChampGravitationnel(0);
        assertEquals(ep.getChampGravitationnel(), 0);
    }

    @Test
    void testGetTempsInit() {
        ep.setTempsInit(37.45);
        assertEquals(ep.getTempsInit(), 37.45);

        ep.setTempsInit(-1.008);
        assertEquals(ep.getTempsInit(), -1.008);
    }

    @Test
    void testSetTempsInit() {
        assertEquals(ep.getTempsInit(), 0);
        ep.setTempsInit(9876);
        assertEquals(ep.getTempsInit(), 9876);

        ep.setTempsInit(-19.34);
        assertEquals(ep.getTempsInit(), -19.34);
    }

    @Test
    void testGetTempsFin() {
        ep.setTempsFin(53.4);
        assertEquals(ep.getTempsFin(), 53.4);

        ep.setTempsFin(-12.7);
        assertEquals(ep.getTempsFin(), -12.7);
    }

    @Test
    void testSetTempsFin() {
        assertEquals(ep.getTempsFin(), 0);
        ep.setTempsFin(11.11);
        assertEquals(ep.getTempsFin(), 11.11);

        ep.setTempsFin(-6789.03);
        assertEquals(ep.getTempsFin(), -6789.03);
    }

    @Test
    void testGetVariationTemps() {
        ep.setTempsInit(64.2);
        assertEquals(ep.getTempsInit(), 64.2);

        ep.setTempsInit(-7.63);
        assertEquals(ep.getTempsInit(), -7.63);
    }

    @Test
    void testSetVariationTemps() {
        assertEquals(ep.getTempsInit(), 0);
        ep.setTempsInit(252.5);
        assertEquals(ep.getTempsInit(), 252.5);

        ep.setTempsInit(-39.9);
        assertEquals(ep.getTempsInit(), -39.9);
    }

    @Test
    void testGetAngleInit() {
        ep.setAngleInit(2.65);
        assertEquals(ep.getAngleInit(), 2.65);

        ep.setAngleInit(-18);
        assertEquals(ep.getAngleInit(), -18);
    }

    @Test
    void testSetAngleInit() {
        assertEquals(ep.getAngleInit(), 0);
        ep.setAngleInit(39);
        assertEquals(ep.getAngleInit(), 39);

        ep.setAngleInit(-23.4);
        assertEquals(ep.getAngleInit(), -23.4);
    }

    @Test
    void testGetAngleFin() {
        ep.setAngleFin(2.65);
        assertEquals(ep.getAngleFin(), 2.65);

        ep.setAngleFin(-18);
        assertEquals(ep.getAngleFin(), -18);
    }

    @Test
    void testSetAngleFin() {
        assertEquals(ep.getAngleFin(), 0);
        ep.setAngleFin(35.89);
        assertEquals(ep.getAngleFin(), 35.89);

        ep.setAngleFin(-17.3);
        assertEquals(ep.getAngleFin(), -17.3);
    }

    @Test
    void testGetAcceleration() {
        ep.setAcceleration(1235);
        assertEquals(ep.getAcceleration(), 1235);

        ep.setAcceleration(-25.75);
        assertEquals(ep.getAcceleration(), -25.75);
    }

    @Test
    void testSetAcceleration() {
        assertEquals(ep.getAcceleration(), 0);
        ep.setAcceleration(9456);
        assertEquals(ep.getAcceleration(), 9456);

        ep.setAcceleration(-2.37);
        assertEquals(ep.getAcceleration(), -2.37);
    }

    @Test
    void testGetVitesseInit() {
        ep.setVitesseInit(1567);
        assertEquals(ep.getVitesseInit(), 1567);

        ep.setVitesseInit(-87);
        assertEquals(ep.getVitesseInit(), -87);
    }

    @Test
    void testSetVitesseInit() {
        assertEquals(ep.getVitesseInit(), 0);
        ep.setVitesseInit(4567);
        assertEquals(ep.getVitesseInit(), 4567);

        ep.setVitesseInit(-76.75);
        assertEquals(ep.getVitesseInit(), -76.75);
    }

    @Test
    void testGetVitesseFin() {
        ep.setVitesseFin(123);
        assertEquals(ep.getVitesseFin(), 123);

        ep.setVitesseFin(-0.67);
        assertEquals(ep.getVitesseFin(), -0.67);
    }

    @Test
    void testSetVitesseFin() {
        assertEquals(ep.getVitesseFin(), 0);
        ep.setVitesseFin(32);
        assertEquals(ep.getVitesseFin(), 32);

        ep.setVitesseFin(-34.3);
        assertEquals(ep.getVitesseFin(), -34.3);
    }

    @Test
    void testGetVariationPositionX() {
        ep.setVariationPositionX(1567);
        assertEquals(ep.getVariationPositionX(), 1567);

        ep.setVariationPositionX(-87);
        assertEquals(ep.getVariationPositionX(), -87);
    }

    @Test
    void testSetVariationPositionX() {
        assertEquals(ep.getVariationPositionX(), 0);
        ep.setVariationPositionX(57);
        assertEquals(ep.getVariationPositionX(), 57);

        ep.setVariationPositionX(-98.76);
        assertEquals(ep.getVariationPositionX(), -98.76);
    }

    @Test
    void testGetVariationPositionY() {
        ep.setVariationPositionY(45);
        assertEquals(ep.getVariationPositionY(), 45);

        ep.setVariationPositionY(-9.954);
        assertEquals(ep.getVariationPositionY(), -9.954);
    }

    @Test
    void testSetVariationPositionY() {
        assertEquals(ep.getVariationPositionY(), 0);
        ep.setVariationPositionY(50574);
        assertEquals(ep.getVariationPositionY(), 50574);

        ep.setVariationPositionY(-13.1313);
        assertEquals(ep.getVariationPositionY(), -13.1313);
    }

    @Test
    void testGetAccelerationX() {
        ep.setAccelerationX(747);
        assertEquals(ep.getAccelerationX(), 747);

        ep.setAccelerationX(-13.4);
        assertEquals(ep.getAccelerationX(), -13.4);
    }

    @Test
    void testSetAccelerationX() {
        assertEquals(ep.getAccelerationX(), 0);
        ep.setAccelerationX(7.65);
        assertEquals(ep.getAccelerationX(), 7.65);

        ep.setAccelerationX(-86);
        assertEquals(ep.getAccelerationX(), -86);
    }

    @Test
    void testGetAccelerationY() {
        ep.setAccelerationY(87.9);
        assertEquals(ep.getAccelerationY(), 87.9);

        ep.setAccelerationY(-3);
        assertEquals(ep.getAccelerationY(), -3);
    }

    @Test
    void testSetAccelerationY() {
        assertEquals(ep.getAccelerationY(), 0);
        ep.setAccelerationY(90.43);
        assertEquals(ep.getAccelerationY(), 90.43);

        ep.setAccelerationY(-390.34);
        assertEquals(ep.getAccelerationY(), -390.34);
    }

    @Test
    void testGetVitesseMoyenneX() {
        ep.setVitesseMoyenneX(0.3);
        assertEquals(ep.getVitesseMoyenneX(), 0.3);

        ep.setVitesseMoyenneX(-563);
        assertEquals(ep.getVitesseMoyenneX(), -563);
    }

    @Test
    void testSetVitesseMoyenneX() {
        assertEquals(ep.getVitesseMoyenneX(), 0);
        ep.setVitesseMoyenneX(9.78);
        assertEquals(ep.getVitesseMoyenneX(), 9.78);

        ep.setVitesseMoyenneX(-563);
        assertEquals(ep.getVitesseMoyenneX(), -563);
    }

    @Test
    void testGetVitesseMoyenneY() {
        ep.setVitesseMoyenneY(0.3576);
        assertEquals(ep.getVitesseMoyenneY(), 0.3576);

        ep.setVitesseMoyenneY(-563.2);
        assertEquals(ep.getVitesseMoyenneY(), -563.2);
    }

    @Test
    void testSetVitesseMoyenneY() {
        assertEquals(ep.getVitesseMoyenneY(), 0);
        ep.setVitesseMoyenneY(0.00000001);
        assertEquals(ep.getVitesseMoyenneY(), 0.00000001);

        ep.setVitesseMoyenneY(-789);
        assertEquals(ep.getVitesseMoyenneY(), -789);
    }

    @Test
    void testSetChampGravitationnel() {
        assertEquals(ep.getChampGravitationnel(), -9.80665);
        ep.setChampGravitationnel(90);
        assertEquals(ep.getChampGravitationnel(), 90);

        ep.setChampGravitationnel(-13.2);
        assertEquals(ep.getChampGravitationnel(), -13.2);
    }

    @Test
    void testGetVariationVitesseX() {
        ep.setVariationVitesseX(36);
        assertEquals(ep.getVariationVitesseX(), 36);

        ep.setVariationVitesseX(-25.57);
        assertEquals(ep.getVariationVitesseX(), -25.57);
    }

    @Test
    void testSetVariationVitesseX() {
        assertEquals(ep.getVariationVitesseX(), 0);
        ep.setVariationVitesseX(23.54);
        assertEquals(ep.getVariationVitesseX(), 23.54);

        ep.setVariationVitesseX(-7676.75);
        assertEquals(ep.getVariationVitesseX(), -7676.75);
    }

    @Test
    void testGetVariationVitesseY() {
        ep.setVariationVitesseX(36908);
        assertEquals(ep.getVariationVitesseX(), 36908);

        ep.setVariationVitesseX(-98.5);
        assertEquals(ep.getVariationVitesseX(), -98.5);
    }

    @Test
    void testSetVariationVitesseY() {
        assertEquals(ep.getVariationVitesseX(), 0);
        ep.setVariationVitesseX(703);
        assertEquals(ep.getVariationVitesseX(), 703);

        ep.setVariationVitesseX(-37);
        assertEquals(ep.getVariationVitesseX(), -37);
    }

    @Test
    void testGetVariationVitesse() {
        ep.setVariationVitesse(36);
        assertEquals(ep.getVariationVitesse(), 36);

        ep.setVariationVitesse(-25.57);
        assertEquals(ep.getVariationVitesse(), -25.57);
    }

    @Test
    void testSetVariationVitesse() {
        assertEquals(ep.getVariationVitesse(), 0);
        ep.setVariationVitesse(36.36);
        assertEquals(ep.getVariationVitesse(), 36.36);

        ep.setVariationVitesse(-98);
        assertEquals(ep.getVariationVitesse(), -98);
    }

}
