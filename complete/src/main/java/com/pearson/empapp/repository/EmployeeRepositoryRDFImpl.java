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
import org.eclipse.rdf4j.model.impl.SimpleValueFactory;
import org.eclipse.rdf4j.model.util.ModelBuilder;
import org.eclipse.rdf4j.model.vocabulary.FOAF;
import org.eclipse.rdf4j.model.vocabulary.RDF;
import org.eclipse.rdf4j.model.vocabulary.RDFS;
import org.eclipse.rdf4j.query.BindingSet;
import org.eclipse.rdf4j.query.QueryResults;

import com.pearson.empapp.entity.Department;
import com.pearson.empapp.entity.Employee;

@org.springframework.stereotype.Repository ("employeeDao")
public class EmployeeRepositoryRDFImpl extends AbstractRDFRepository implements EmployeeRepository {
	
	public EmployeeRepositoryRDFImpl(QueryFile queries) {
		super(queries);
	}

	@Override
	public List<Employee> findAll() {
		
		List<Employee> result = new ArrayList<>();

		String queryString = queries.getQuery("GET_ALL_EMPLOYEES");

		
		List<BindingSet> results =  
		Repositories.tupleQuery(repo, queryString, r -> QueryResults.asList(r));
	    
		for (BindingSet bs : results) {
			System.out.println(bs.toString());

			Employee e = new Employee(bs.getBinding("employee").getValue().stringValue(), 
					bs.getBinding("name").getValue().stringValue(), 
					bs.getBinding("birthday").getValue().stringValue(),
					bs.getBinding("department").getValue().stringValue());
			
			result.add(e);
		}

		return result;
	}
	
	@Override
	public Employee save(Employee e) {
		
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
				
				System.out.println("Printing Employee RDF...");
				Rio.write(m, System.out, RDFFormat.TURTLE);
				
				con.add(m);
			}
		
		// Return an employee instance with the Id set.
		return new Employee("http://example.org/"+ e.getName(), e.getName(), e.getDob());
		
	}
	
	@Override
	public void updateDepartment(Employee e, Department dept) {
		
		ValueFactory vf = SimpleValueFactory.getInstance();
		IRI departmentType = vf.createIRI("http://example.org/ontology/", "Department");
		IRI departmentId = vf.createIRI(dept.getId());
		
		try ( RepositoryConnection con = repo.getConnection() ) {
			ModelBuilder builder = new ModelBuilder();
			builder.setNamespace("ex", "http://example.org/");
			
			builder
			   .namedGraph("ex:"+ e.getName()+ "_graph")
		       .subject("ex:"+ e.getName())
		       .add(departmentType, departmentId);
			
			Model m = builder.build();
			
			System.out.println("Printing Employee/Department linking RDF...");
			Rio.write(m, System.out, RDFFormat.TURTLE);
			
			con.add(m);
		}
	}
	
}
