module conta.sistema {
	
	requires javax.inject;
	requires spring.tx;

	
	// expondo porta de entrada (driver) front-end
    exports account.adapter;

    // expondo sistema negocio service
    exports account.hexagonal.service;

    // expondo adaptadores de saídas (driven) banco de dados
    exports account.adapter.repositories;
}