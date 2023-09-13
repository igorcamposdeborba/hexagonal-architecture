module conta.sistema {
	
	requires javax.inject;
	requires spring.tx;

	
	// expondo porta de entrada (driver) front-end
    exports account.adapter;

    // expondo sistema negocio service
    exports account.hexagonal.service;

    // expondo adaptadores de saídas (driven) banco de dados
    exports account.adapter.repositories;
    
    // Abrir reflexão
    opens account.hexagonal.controller;
    opens account.hexagonal.entities;
    opens account.hexagonal.service;
    opens account.adapter;
    
}