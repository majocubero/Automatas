import java.io.PrintStream;
import java.util.*;

/** This class may be used to contain the semantic information such as
 * the inheritance graph.  You may use it or not as you like: it is only
 * here to provide a container for the supplied methods.  */
class ClassTable {
	private AbstractSymbol filename;
	private int semantErrors;
	private PrintStream errorStream;
	
	private Map<AbstractSymbol, class_c> mapaDeClasesBasicas;
    private Map<AbstractSymbol, class_c> mapaTablaDeClases;
	private Map<AbstractSymbol, Features> mapaTablaFeature;

    /** Creates data structures representing basic Cool classes (Object,
     * IO, Int, Bool, String).  Please note: as is this method does not
     * do anything useful; you will need to edit it to make if do what
     * you want.
     * */
    private void installBasicClasses() {
	AbstractSymbol filename 
	    = AbstractTable.stringtable.addString("<basic class>");
	
	// The following demonstrates how to create dummy parse trees to
	// refer to basic Cool classes.  There's no need for method
	// bodies -- these are already built into the runtime system.

	// IMPORTANT: The results of the following expressions are
	// stored in local variables.  You will want to do something
	// with those variables at the end of this method to make this
	// code meaningful.

	// The Object class has no parent class. Its methods are
	//        cool_abort() : Object    aborts the program
	//        type_name() : Str        returns a string representation 
	//                                 of class name
	//        copy() : SELF_TYPE       returns a copy of the object

	class_c Object_class = 
	    new class_c(0, 
		       TreeConstants.Object_, 
		       TreeConstants.No_class,
		       new Features(0)
			   .appendElement(new method(0, 
					      TreeConstants.cool_abort, 
					      new Formals(0), 
					      TreeConstants.Object_, 
					      new no_expr(0)))
			   .appendElement(new method(0,
					      TreeConstants.type_name,
					      new Formals(0),
					      TreeConstants.Str,
					      new no_expr(0)))
			   .appendElement(new method(0,
					      TreeConstants.copy,
					      new Formals(0),
					      TreeConstants.SELF_TYPE,
					      new no_expr(0))),
		       filename);
	
	// The IO class inherits from Object. Its methods are
	//        out_string(Str) : SELF_TYPE  writes a string to the output
	//        out_int(Int) : SELF_TYPE      "    an int    "  "     "
	//        in_string() : Str            reads a string from the input
	//        in_int() : Int                "   an int     "  "     "

	class_c IO_class = 
	    new class_c(0,
		       TreeConstants.IO,
		       TreeConstants.Object_,
		       new Features(0)
			   .appendElement(new method(0,
					      TreeConstants.out_string,
					      new Formals(0)
						  .appendElement(new formalc(0,
								     TreeConstants.arg,
								     TreeConstants.Str)),
					      TreeConstants.SELF_TYPE,
					      new no_expr(0)))
			   .appendElement(new method(0,
					      TreeConstants.out_int,
					      new Formals(0)
					      .appendElement(new formalc(0,
								     TreeConstants.arg,
								     TreeConstants.Int)),
					      TreeConstants.SELF_TYPE,
					      new no_expr(0)))
			   .appendElement(new method(0,
					      TreeConstants.in_string,
					      new Formals(0),
					      TreeConstants.Str,
					      new no_expr(0)))
			   .appendElement(new method(0,
					      TreeConstants.in_int,
					      new Formals(0),
					      TreeConstants.Int,
					      new no_expr(0))),
		       filename);

	// The Int class has no methods and only a single attribute, the
	// "val" for the integer.

	class_c Int_class = 
	    new class_c(0,
		       TreeConstants.Int,
		       TreeConstants.Object_,
		       new Features(0)
			   .appendElement(new attr(0,
					    TreeConstants.val,
					    TreeConstants.prim_slot,
					    new no_expr(0))),
		       filename);

	// Bool also has only the "val" slot.
	class_c Bool_class = 
	    new class_c(0,
		       TreeConstants.Bool,
		       TreeConstants.Object_,
		       new Features(0)
			   .appendElement(new attr(0,
					    TreeConstants.val,
					    TreeConstants.prim_slot,
					    new no_expr(0))),
		       filename);

	// The class Str has a number of slots and operations:
	//       val                              the length of the string
	//       str_field                        the string itself
	//       length() : Int                   returns length of the string
	//       concat(arg: Str) : Str           performs string concatenation
	//       substr(arg: Int, arg2: Int): Str substring selection

	class_c Str_class =
	    new class_c(0,
		       TreeConstants.Str,
		       TreeConstants.Object_,
		       new Features(0)
			   .appendElement(new attr(0,
					    TreeConstants.val,
					    TreeConstants.Int,
					    new no_expr(0)))
			   .appendElement(new attr(0,
					    TreeConstants.str_field,
					    TreeConstants.prim_slot,
					    new no_expr(0)))
			   .appendElement(new method(0,
					      TreeConstants.length,
					      new Formals(0),
					      TreeConstants.Int,
					      new no_expr(0)))
			   .appendElement(new method(0,
					      TreeConstants.concat,
					      new Formals(0)
						  .appendElement(new formalc(0,
								     TreeConstants.arg, 
								     TreeConstants.Str)),
					      TreeConstants.Str,
					      new no_expr(0)))
			   .appendElement(new method(0,
					      TreeConstants.substr,
					      new Formals(0)
						  .appendElement(new formalc(0,
								     TreeConstants.arg,
								     TreeConstants.Int))
						  .appendElement(new formalc(0,
								     TreeConstants.arg2,
								     TreeConstants.Int)),
					      TreeConstants.Str,
					      new no_expr(0))),
		       filename);

	/* Definition of SELF_TYPE */
        class_c Self_class = 
            new class_c (0, TreeConstants.SELF_TYPE, 
                            TreeConstants.Object_,
                            new Features(0),
                            filename
            );


	/* Do somethind with Object_class, IO_class, Int_class,
           Bool_class, and Str_class here */
        this.filename = filename;
        mapaDeClasesBasicas.put(TreeConstants.Object_, Object_class);
        mapaDeClasesBasicas.put(TreeConstants.IO, IO_class);
        mapaDeClasesBasicas.put(TreeConstants.Int, Int_class);
        mapaDeClasesBasicas.put(TreeConstants.Bool, Bool_class);
        mapaDeClasesBasicas.put(TreeConstants.Str, Str_class); 
        mapaDeClasesBasicas.put(TreeConstants.SELF_TYPE, Self_class); 
     
		mapaTablaDeClases.putAll(mapaDeClasesBasicas);
    }

    public ClassTable(Classes cls) {
		semantErrors = 0;
		errorStream = System.err;
		mapaDeClasesBasicas = new HashMap<AbstractSymbol, class_c>();	
		mapaTablaDeClases = new HashMap<AbstractSymbol, class_c>();
		mapaTablaFeature = new HashMap<AbstractSymbol, Features>();
		/* fill this in */
		installBasicClasses();
		mapaTablaDeClases = verificarClases(cls);
		revisarHerenciaAciclica();
    }

    /** Prints line number and file name of the given class.
     *
     * Also increments semantic error count.
     *
     * @param c the class
     * @return a print stream to which the rest of the error message is
     * to be printed.
     *
     * */
    public PrintStream semantError(class_c c) {
		return semantError(c.getFilename(), c);
    }

    /** Prints the file name and the line number of the given tree node.
     *
     * Also increments semantic error count.
     *
     * @param filename the file name
     * @param t the tree node
     * @return a print stream to which the rest of the error message is
     * to be printed.
     *
     * */
    public PrintStream semantError(AbstractSymbol filename, TreeNode t) {
		errorStream.print(filename + ":" + t.getLineNumber() + ": ");
		return semantError();
    }

    /** Increments semantic error count and returns the print stream for
     * error messages.
     *
     * @return a print stream to which the error message is
     * to be printed.
     *
     * */
    public PrintStream semantError() {
		semantErrors++;
		return errorStream;
    }

    /** Returns true if there are any static semantic errors. */
    public boolean errors() {
		return semantErrors != 0;
    }
	
	 public boolean hasType(AbstractSymbol tipo) {
        return mapaDeClasesBasicas.containsKey(tipo) || mapaTablaDeClases.containsKey(tipo);
    }

    public Feature getFeature(AbstractSymbol nombreFeature, AbstractSymbol nombreClase, boolean esMetodo) {
        if (mapaTablaDeClases.containsKey(nombreClase)) {
            AbstractSymbol pt = nombreClase; 
            while (!TreeConstants.No_class.equals(pt)) {
                Features features = mapaTablaFeature.get(pt);
                if (features != null) {
                    for (Enumeration e = features.getElements(); e.hasMoreElements();) {
                        Feature feature = (Feature) e.nextElement();
                        if (feature.getName().equals(nombreFeature)) {
                            if ((feature instanceof method && esMetodo) ||
                                (feature instanceof attr && !esMetodo)) {
                                return feature;
                            }
                        }
                    }
                }
                pt = mapaTablaDeClases.get(pt).getParent();
            }
        }
        return null; 
    }

    public void addFeature(Feature feature, class_c claseActual) {
        Features features;
        if (!mapaTablaFeature.containsKey(claseActual.getName())) {
            features = new Features(0);
        } else {
            features = mapaTablaFeature.get(claseActual.getName());
        }
        mapaTablaFeature.put(claseActual.getName(), features.appendElement(feature));
    }

    public void dumpFeatures() {
        for (AbstractSymbol sym : mapaTablaFeature.keySet()) {
            for (Enumeration e = mapaTablaFeature.get(sym).getElements(); e.hasMoreElements();) {
                System.out.print(sym.toString() + "   ");
                System.out.println(((Feature)e.nextElement()).getName());
            }
        }
    }

    public AbstractSymbol getParent(AbstractSymbol nombreClase) {
        if (mapaTablaDeClases.containsKey(nombreClase)) {
            return mapaTablaDeClases.get(nombreClase).parent;
        }
        return TreeConstants.Object_;
    }

    public Classes getAllClasses() {
        Classes classes = new Classes(0);
        for (AbstractSymbol nombreClase : mapaTablaDeClases.keySet()) {
            classes.addElement(mapaTablaDeClases.get(nombreClase));
        }
        return classes;
    }
	
	private void revisarHerenciaAciclica() {
        Set <AbstractSymbol> visitados = new HashSet<AbstractSymbol>();
        Set <AbstractSymbol> clasesIndefinidas = new HashSet<AbstractSymbol>();
        for (AbstractSymbol key : mapaTablaDeClases.keySet()) {
            class_c claseActual = mapaTablaDeClases.get(key);
            visitados.clear();
            visitados.add(claseActual.name);
            while (!TreeConstants.Object_.equals(claseActual.name) &&
                   !TreeConstants.IO.equals(claseActual.name)) {
                if (!mapaTablaDeClases.containsKey(claseActual.parent)) {
                    if (!clasesIndefinidas.contains(claseActual.parent)) {
                        clasesIndefinidas.add(claseActual.parent);
                        errorHeredaClaseIndefinida(claseActual);
                    }
                    break;
                } else if (visitados.contains(claseActual.parent)) {
                    errorCicloHerencia(claseActual);
                    break;
                } else {
                    claseActual = mapaTablaDeClases.get(claseActual.parent);
                    visitados.add(claseActual.name);
                }
            }     
        }
	}
	
	private void errorHeredaClaseIndefinida(class_c claseActual) {
        semantError(claseActual).println("La clase " + claseActual.name.toString() + 
                                      " hereda de una clase indefinida: " +
                                      claseActual.parent.toString() + ".");
    }
    
    private void errorClaseRedefinida(class_c claseActual) {
        semantError(claseActual).println("La clase " + claseActual.name.toString() + " ya habia sido definida.");
	}

    private void errorCicloHerencia(class_c claseActual) {
        semantError(claseActual).println("La clase " + claseActual.parent.toString() +
                                      ", o alguno de sus ancestros: " + claseActual.parent.toString() +
                                      ", posee herencia cíclica.");
	}
	
	private Map<AbstractSymbol, class_c> verificarClases(Classes cls) {
        class_c claseActual;
		for (Enumeration e = cls.getElements(); e.hasMoreElements();) {
            claseActual = (class_c) e.nextElement();
            AbstractSymbol padre = claseActual.parent;
            if (mapaTablaDeClases.containsKey(claseActual.name)){
            	errorClaseRedefinida(claseActual);
            }
            mapaTablaDeClases.put(claseActual.name, claseActual);
        }
        return mapaTablaDeClases;
	}
	
}
			  
    
