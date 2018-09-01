package com.pearson.empapp.repository;

import org.eclipse.rdf4j.model.IRI;
import org.eclipse.rdf4j.model.Model;
import org.eclipse.rdf4j.model.ValueFactory;
import org.eclipse.rdf4j.model.impl.SimpleValueFactory;
import org.eclipse.rdf4j.model.util.ModelBuilder;
import org.eclipse.rdf4j.model.vocabulary.FOAF;
import org.eclipse.rdf4j.model.vocabulary.RDF;
import org.eclipse.rdf4j.repository.Repository;
import org.eclipse.rdf4j.repository.RepositoryConnection;
import org.eclipse.rdf4j.rio.RDFFormat;
import org.eclipse.rdf4j.rio.Rio;

import com.pearson.empapp.entity.Department;

@org.springframework.stereotype.Repository ("departmentDao")
public class DepartmentRepositoryRDFImpl extends AbstractRDFRepository implements DepartmentRepository {

	public DepartmentRepositoryRDFImpl(QueryFile queries) {
		super(queries);
	}
	
	@Override
	public Department save(Department d) {
		// TODO Auto-generated method stub
		
		ValueFactory vf = SimpleValueFactory.getInstance();
		IRI departmentType = vf.createIRI("http://example.org/ontology/", "Department");
		
		try ( RepositoryConnection con = repo.getConnection() ) {
			ModelBuilder builder = new ModelBuilder();
			builder.setNamespace("ex", "http://example.org/");
			
			builder
			   .namedGraph("ex:"+ d.getName()+ "_graph")
		       .subject("ex:"+ d.getName())
		       .add(RDF.TYPE, departmentType)
		       .add(FOAF.NAME, d.getName());
			
			Model m = builder.build();
			
			System.out.println("Printing Department RDF...");
			Rio.write(m, System.out, RDFFormat.TURTLE);
			
			con.add(m);
		}
		
		// Return the Department instance with Id.
		return new Department("http://example.org/" + d.getName(), d.getName());

	}
	
}
