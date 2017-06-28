class C {
	intUno : Int;
	intDos : Int <- 45;
	x : Int;
	
	init() : Int {
           {
		intUno <- intDos;
		let intLocal : Int <- intUno + intDos in (
			intDos <- (intUno * x) / intLocal
		); 
           }
	};
};

Class Main {
	intMain : Int <- 5;

	main():Int {
		intMain <- intMain * 2
	};
};
