package com.weapon.ontology;

import org.apache.jena.graph.Triple;
import org.apache.jena.ontology.OntModel;
import org.apache.jena.rdf.model.*;
import org.apache.jena.reasoner.Derivation;
import org.apache.jena.reasoner.Reasoner;
import org.apache.jena.reasoner.ReasonerRegistry;
import org.apache.jena.riot.Lang;
import org.apache.jena.riot.RDFDataMgr;
import org.apache.jena.vocabulary.OWL;
import org.apache.jena.vocabulary.OWL2;
import org.apache.jena.vocabulary.RDFS;

import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Iterator;

import static com.weapon.ontology.WeaponOntology.NS;

public class App {

    public static void main(String[] args) throws IOException {
        OntModel ont1 = WeaponOntology.createOntology();
        Reasoner reasoner = ReasonerRegistry.getOWLReasoner();
        InfModel inf2 = ModelFactory.createInfModel(reasoner, ont1);
        RDFDataMgr.write(new FileOutputStream("weapon-ontology"), ont1, Lang.TURTLE) ;
    }
}
