module conta.sistema {
	
	requires javax.inject;
	requires spring.tx;
	
	// expondo porta de entrada (driver) com os endpoints/front-end
    exports account.adapter.controller;
=
    // expondo sistema negocio (service)
    exports account.hexagonal.service;

    // expondo adaptadores de saídas (driven) com o repository/banco de dados
    exports account.hexagonal.repositories;
}