package com.pearson.empapp.repository;

import org.eclipse.rdf4j.repository.Repository;
import org.eclipse.rdf4j.repository.sail.SailRepository;
import org.eclipse.rdf4j.sail.memory.MemoryStore;

public abstract class AbstractRDFRepository {

	protected Repository repo = null;
	
	protected QueryFile queries;

	public AbstractRDFRepository(QueryFile queries) {
		this.queries = queries;
		
	    repo = new SailRepository(new MemoryStore());
		repo.initialize();
	}
	
	
}
