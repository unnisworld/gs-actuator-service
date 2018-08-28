package com.pearson.empapp.repository;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.rdf4j.repository.Repository;
import org.eclipse.rdf4j.repository.RepositoryConnection;
import org.eclipse.rdf4j.repository.sail.SailRepository;
import org.eclipse.rdf4j.repository.util.Repositories;
import org.eclipse.rdf4j.rio.RDFFormat;
import org.eclipse.rdf4j.rio.Rio;
import org.eclipse.rdf4j.sail.memory.MemoryStore;
import org.eclipse.rdf4j.model.IRI;
import org.eclipse.rdf4j.model.Model;
import org.eclipse.rdf4j.model.ValueFactory;
import org.eclipse.rdf4j.model.util.ModelBuilder;
import org.eclipse.rdf4j.model.vocabulary.FOAF;
import org.eclipse.rdf4j.model.vocabulary.RDF;
import org.eclipse.rdf4j.model.vocabulary.RDFS;
import org.eclipse.rdf4j.query.BindingSet;
import org.eclipse.rdf4j.query.QueryResults;

import com.pearson.empapp.entity.Employee;

@org.springframework.stereotype.Repository ("employeeDao")
public class EmployeeRepositoryRDFImpl implements EmployeeRepository {
	
	public EmployeeRepositoryRDFImpl() {
	    repo = new SailRepository(new MemoryStore());
		repo.initialize();
	}

	@Override
	public List<Employee> findAll() {
		
		List<Employee> result = new ArrayList<>();
		
		String queryString = "PREFIX ex: <http://example.org/> \n";
		queryString += "PREFIX foaf: <http://xmlns.com/foaf/0.1/> \n";
		queryString += "SELECT ?s ?n ?b \n";
	    queryString += "WHERE { \n";
	    queryString += "    ?s a foaf:Person ; \n";
	    queryString += "       foaf:name ?n ;";
	    queryString += "       foaf:birthday ?b .";
	    queryString += "}";
		
		List<BindingSet> results =  
		Repositories.tupleQuery(repo, queryString, r -> QueryResults.asList(r));
	    
		for (BindingSet bs : results) {
			System.out.println(bs.toString());

			Employee e = new Employee(bs.getBinding("s").getValue().stringValue(), 
					bs.getBinding("n").getValue().stringValue(), 
					bs.getBinding("b").getValue().stringValue());
			result.add(e);
			
		}

		return result;
	}
	
	@Override
	public void save(Employee e) {
		empTable.add(e);
		
		try ( RepositoryConnection con = repo.getConnection() ) {
				ModelBuilder builder = new ModelBuilder();
				builder.setNamespace("ex", "http://example.org/");
				
				builder
				   .namedGraph("ex:"+ e.getName()+ "_graph")
			       .subject("ex:"+ e.getName())
			       .add(RDF.TYPE, FOAF.PERSON)
			       .add(FOAF.NAME, e.getName())
			       .add(FOAF.BIRTHDAY, e.getDob());
				
				Model m = builder.build();
				
				System.out.println("Printing RDF...");
				Rio.write(m, System.out, RDFFormat.TURTLE);
				
				con.add(m);
			}
		
	}
	
	List<Employee> empTable = new ArrayList<Employee>();
	Repository repo = null;

}
