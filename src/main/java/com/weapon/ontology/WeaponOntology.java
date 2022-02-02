package com.weapon.ontology;

import org.apache.jena.ontology.*;
import org.apache.jena.rdf.model.InfModel;
import org.apache.jena.rdf.model.ModelFactory;
import org.apache.jena.reasoner.Reasoner;
import org.apache.jena.vocabulary.XSD;

import static org.apache.jena.ontology.OntModelSpec.OWL_DL_MEM_TRANS_INF;

public class WeaponOntology {
    public static final String SOURCE = "urn:soap/weapon/ontology";
    public static final String NS = SOURCE + "#";

    public static OntModel createOntology() {
        OntModel inf = ModelFactory.createOntologyModel(OWL_DL_MEM_TRANS_INF);
        inf.setStrictMode(false);
        OntClass weapon = inf.createClass(NS + "Weapon");
        OntClass soldier = inf.createClass(NS + "Soldier");
        OntClass rocket = inf.createClass(NS + "Rocket");
        OntClass coldWeapon = inf.createClass(NS + "ColdWeapon");
        OntClass rangeWeapon = inf.createClass(NS + "RangeWeapon");
        weapon.addSubClass(coldWeapon);
        weapon.addSubClass(rangeWeapon);
        OntClass smallColdWeapon = inf.createClass(NS + "SmallColdWeapon");
        OntClass largeColdWeapon = inf.createClass(NS + "LargeColdWeapon");
        coldWeapon.addSubClass(smallColdWeapon);
        DatatypeProperty lengthInCm = inf.createDatatypeProperty(NS + "length");
        lengthInCm.addDomain(coldWeapon);
        lengthInCm.addRange(XSD.xfloat);
        coldWeapon.addSubClass(largeColdWeapon);
        OntClass elasticWeapon = inf.createClass(NS + "ElasticWeapon");
        OntClass siegeWeapon = inf.createClass(NS + "SiegeWeapon");
        OntClass fireWeapon = inf.createClass(NS + "FireWeapon");
        rangeWeapon.addSubClass(elasticWeapon);
        rangeWeapon.addSubClass(siegeWeapon);
        rangeWeapon.addSubClass(fireWeapon);
        OntClass ammunition = inf.createClass(NS + "Ammunition");
        OntClass arrow = inf.createClass(NS + "Arrow");
        DatatypeProperty arrowSize = inf.createDatatypeProperty(NS + "arrowSize");
        arrowSize.addDomain(arrow);
        arrowSize.addRange(XSD.xfloat);
        ObjectProperty hasArrow = inf.createObjectProperty(NS + "use");
        hasArrow.addDomain(elasticWeapon);
        hasArrow.addRange(arrow);
        ammunition.addSubClass(arrow);
        ammunition.addSubClass(rocket);
        DatatypeProperty rateOfFire = inf.createDatatypeProperty(NS + "rateOfFire");
        rateOfFire.addDomain(elasticWeapon);
        rateOfFire.addRange(XSD.xstring);
        DatatypeProperty throwingPower = inf.createDatatypeProperty(NS + "throwingPower");
        throwingPower.addDomain(siegeWeapon);
        throwingPower.addRange(XSD.xint);
        DatatypeProperty range = inf.createDatatypeProperty(NS + "range");
        range.addDomain(siegeWeapon);
        range.addRange(XSD.xint);
        OntClass mechanism = inf.createClass( NS + "Mechanism" );
        EnumeratedClass reloading =
                inf.createEnumeratedClass( NS + "Reloading", null);
        reloading.addOneOf(mechanism.createIndividual("muzzleloader"));
        reloading.addOneOf(mechanism.createIndividual("breechloader"));
        reloading.addOneOf(mechanism.createIndividual("bolt"));
        reloading.addOneOf(mechanism.createIndividual("pump"));
        reloading.addOneOf(mechanism.createIndividual("revolver"));
        reloading.addOneOf(mechanism.createIndividual("semi-automatic"));
        reloading.addOneOf(mechanism.createIndividual("fully-automatic"));
        OntClass size = inf.createClass( NS + "Size" );
        EnumeratedClass caliber =
                inf.createEnumeratedClass( NS + "Caliber", null);
        caliber.addOneOf(size.createIndividual("172"));
        caliber.addOneOf(size.createIndividual("20"));
        caliber.addOneOf(size.createIndividual("204"));
        caliber.addOneOf(size.createIndividual("284"));
        caliber.addOneOf(size.createIndividual("52"));
        caliber.addOneOf(size.createIndividual("311"));
        caliber.addOneOf(size.createIndividual(".40"));
        caliber.addOneOf(size.createIndividual(".50"));
        OntClass fireArm = inf.createClass( NS + "FireArm");
        OntClass gun = inf.createClass(NS + "Gun");
        OntClass artillery = inf.createClass( NS + "Artillery");
        OntClass rocketArtillery = inf.createClass( NS + "RocketArtillery");
        fireWeapon.addSubClass(fireArm);
        fireWeapon.addSubClass(gun);
        DatatypeProperty gunRange = inf.createDatatypeProperty(NS + "rangeInKm");
        gunRange.addDomain(gun);
        gunRange.addRange(XSD.xint);
        artillery.addSubClass(rocketArtillery);
        DatatypeProperty numberOfAmmunition = inf.createDatatypeProperty(NS + "numberOfAmmunition");
        numberOfAmmunition.addDomain(fireArm);
        numberOfAmmunition.addRange(XSD.xint);
        ObjectProperty typeOfReloading = inf.createObjectProperty(NS + "typeOfReloading");
        typeOfReloading.setDomain(fireArm);
        typeOfReloading.setRange(reloading);
        ObjectProperty hasCaliber = inf.createObjectProperty(NS + "hasCaliber");
        hasCaliber.setDomain(fireWeapon);
        hasCaliber.setRange(caliber);
        ObjectProperty gunCrew = inf.createObjectProperty(NS + "hasGunCrew");
        gunCrew.setDomain(artillery);
        gunCrew.setRange(soldier);
        ObjectProperty hasGuns = inf.createObjectProperty(NS + "hasGuns");
        hasGuns.setDomain(artillery);
        hasGuns.setRange(gun);
        ObjectProperty useRockets = inf.createObjectProperty(NS + "useAmmunition");
        useRockets.setDomain(rocketArtillery);
        useRockets.setRange(rocket);
        Individual knife = smallColdWeapon.createIndividual(NS + "knife");
        knife.addProperty(lengthInCm,"5");
        Individual dagger = smallColdWeapon.createIndividual(NS + "dagger");
        dagger.addProperty(lengthInCm,"10");
        Individual katana = largeColdWeapon.createIndividual(NS + "katana");
        katana.addProperty(lengthInCm,"70");
        Individual bow = elasticWeapon.createIndividual(NS + "bow");
        Individual bowArrow = arrow.createIndividual(NS + "bowArrow");
        bowArrow.addProperty(arrowSize,"70");
        bow.addProperty(hasArrow,bowArrow);
        bow.addProperty(rateOfFire,"18rpm");
        Individual crossbow = elasticWeapon.createIndividual(NS + "crossbow");
        Individual crossbowArrow = arrow.createIndividual(NS + "crossbowArrow");
        crossbowArrow.addProperty(arrowSize,"45");
        crossbow.addProperty(hasArrow,crossbowArrow);
        crossbow.addProperty(rateOfFire,"8rpm");
        Individual trebuchet = siegeWeapon.createIndividual(NS + "tribuchet");
        trebuchet.addProperty(throwingPower,"160");
        trebuchet.addProperty(range,"640");
        Individual catapult = siegeWeapon.createIndividual(NS + "catapult");
        catapult.addProperty(throwingPower,"90");
        catapult.addProperty(range,"400");
        Individual pistol = fireArm.createIndividual(NS+ "glock");
        pistol.addProperty(typeOfReloading,"revolver");
        pistol.addProperty(numberOfAmmunition,"7");
        pistol.addProperty(hasCaliber,".40");
        Individual revolver = fireArm.createIndividual(NS+ "colt");
        revolver.addProperty(typeOfReloading,"revolver");
        revolver.addProperty(numberOfAmmunition,"7");
        pistol.addProperty(hasCaliber,".45");
        Individual howitzer = gun.createIndividual(NS+ "M198howitzer");
        howitzer.addProperty(gunRange,"30");
        howitzer.addProperty(hasCaliber,"52");
        Individual john = soldier.createIndividual(NS+ "John");
        Individual battery = artillery.createIndividual(NS+ "battery");
        battery.addProperty(hasGuns,howitzer);
        battery.addProperty(gunCrew,john);
        Individual V2 = rocket.createIndividual(NS+ "V2");
        Individual M270 = rocketArtillery.createIndividual(NS+ "M270");
        M270.addProperty(useRockets,V2);
        return inf;
    }
}









