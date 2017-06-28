class C {
	x : Int;
	w : Bool;

	init(y : Int) : C {
           {
		let v : Bool, p : Int in {
		p <- 1;  -- p esta en el scope del let
		self;
		};
           }
	};
};

Class Main {
	main() : Bool {
		true
	};
};
