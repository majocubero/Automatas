// -*- mode: java -*- 
//
// file: cool-tree.m4
//
// This file defines the AST
//
//////////////////////////////////////////////////////////

import java.util.Enumeration;
import java.io.PrintStream;
import java.util.Vector;
import java.util.*;


/** Defines simple phylum Program */
abstract class Program extends TreeNode {
    protected Program(int lineNumber) {
        super(lineNumber);
    }
    public abstract void dump_with_types(PrintStream out, int n);
    public abstract void semant();

}


/** Defines simple phylum Class_ */
abstract class Class_ extends TreeNode {
    protected Class_(int lineNumber) {
        super(lineNumber);
    }
    public abstract void dump_with_types(PrintStream out, int n);
    public abstract void semant(ClassTable classTable);
    public abstract void scanFeatures(ClassTable classTable);
}


/** Defines list phylum Classes
    <p>
    See <a href="ListNode.html">ListNode</a> for full documentation. */
class Classes extends ListNode {
    public final static Class elementClass = Class_.class;
    /** Returns class of this lists's elements */
    public Class getElementClass() {
        return elementClass;
    }
    protected Classes(int lineNumber, Vector elements) {
        super(lineNumber, elements);
    }
    /** Creates an empty "Classes" list */
    public Classes(int lineNumber) {
        super(lineNumber);
    }
    /** Appends "Class_" element to this list */
    public Classes appendElement(TreeNode elem) {
        addElement(elem);
        return this;
    }
    public TreeNode copy() {
        return new Classes(lineNumber, copyElements());
    }
}


/** Defines simple phylum Feature */
abstract class Feature extends TreeNode {
    protected Feature(int lineNumber) {
        super(lineNumber);
    }
    public abstract void dump_with_types(PrintStream out, int n);
    public abstract void semant(ClassTable tablaClases, SymbolTable tablaSimbolos, class_c claseActual, boolean firstScan);
    public abstract AbstractSymbol getName();
	public abstract AbstractSymbol getType();
}


/** Defines list phylum Features
    <p>
    See <a href="ListNode.html">ListNode</a> for full documentation. */
class Features extends ListNode {
    public final static Class elementClass = Feature.class;
    /** Returns class of this lists's elements */
    public Class getElementClass() {
        return elementClass;
    }
    protected Features(int lineNumber, Vector elements) {
        super(lineNumber, elements);
    }
    /** Creates an empty "Features" list */
    public Features(int lineNumber) {
        super(lineNumber);
    }
    /** Appends "Feature" element to this list */
    public Features appendElement(TreeNode elem) {
        addElement(elem);
        return this;
    }
    public TreeNode copy() {
        return new Features(lineNumber, copyElements());
    }
}


/** Defines simple phylum Formal */
abstract class Formal extends TreeNode {
    protected Formal(int lineNumber) {
        super(lineNumber);
    }
    public abstract void dump_with_types(PrintStream out, int n);
	public abstract void semant(ClassTable tablaClases, SymbolTable tablaSimbolos, class_c claseActual);
    public abstract AbstractSymbol getName();
	public abstract AbstractSymbol getType();
}


/** Defines list phylum Formals
    <p>
    See <a href="ListNode.html">ListNode</a> for full documentation. */
class Formals extends ListNode {
    public final static Class elementClass = Formal.class;
    /** Returns class of this lists's elements */
    public Class getElementClass() {
        return elementClass;
    }
    protected Formals(int lineNumber, Vector elements) {
        super(lineNumber, elements);
    }
    /** Creates an empty "Formals" list */
    public Formals(int lineNumber) {
        super(lineNumber);
    }
    /** Appends "Formal" element to this list */
    public Formals appendElement(TreeNode elem) {
        addElement(elem);
        return this;
    }
    public TreeNode copy() {
        return new Formals(lineNumber, copyElements());
    }
}


/** Defines simple phylum Expression */
abstract class Expression extends TreeNode {
    protected Expression(int lineNumber) {
        super(lineNumber);
    }
    private AbstractSymbol type = null;                                 
    public AbstractSymbol get_type() { return type; }           
    public Expression set_type(AbstractSymbol s) { type = s; return this; } 
    public abstract void dump_with_types(PrintStream out, int n);
    public void dump_type(PrintStream out, int n) {
        if (type != null)
            { out.println(Utilities.pad(n) + ": " + type.getString()); }
        else
            { out.println(Utilities.pad(n) + ": _no_type"); }
    }
    
	public abstract Expression semant(ClassTable tablaClases, SymbolTable tablaSimbolos, class_c claseActual);
	
}


/** Defines list phylum Expressions
    <p>
    See <a href="ListNode.html">ListNode</a> for full documentation. */
class Expressions extends ListNode {
    public final static Class elementClass = Expression.class;
    /** Returns class of this lists's elements */
    public Class getElementClass() {
        return elementClass;
    }
    protected Expressions(int lineNumber, Vector elements) {
        super(lineNumber, elements);
    }
    /** Creates an empty "Expressions" list */
    public Expressions(int lineNumber) {
        super(lineNumber);
    }
    /** Appends "Expression" element to this list */
    public Expressions appendElement(TreeNode elem) {
        addElement(elem);
        return this;
    }
    public TreeNode copy() {
        return new Expressions(lineNumber, copyElements());
    }
}


/** Defines simple phylum Case */
abstract class Case extends TreeNode {
    protected Case(int lineNumber) {
        super(lineNumber);
    }
    public abstract void dump_with_types(PrintStream out, int n);
	public abstract AbstractSymbol semant(ClassTable tablaClases, SymbolTable tablaSimbolos, class_c claseActual);
	public abstract AbstractSymbol getType();
	
}


/** Defines list phylum Cases
    <p>
    See <a href="ListNode.html">ListNode</a> for full documentation. */
class Cases extends ListNode {
    public final static Class elementClass = Case.class;
    /** Returns class of this lists's elements */
    public Class getElementClass() {
        return elementClass;
    }
    protected Cases(int lineNumber, Vector elements) {
        super(lineNumber, elements);
    }
    /** Creates an empty "Cases" list */
    public Cases(int lineNumber) {
        super(lineNumber);
    }
    /** Appends "Case" element to this list */
    public Cases appendElement(TreeNode elem) {
        addElement(elem);
        return this;
    }
    public TreeNode copy() {
        return new Cases(lineNumber, copyElements());
    }
}


/** Defines AST constructor 'programc'.
    <p>
    See <a href="TreeNode.html">TreeNode</a> for full documentation. */
class programc extends Program {
    protected Classes classes;
    /** Creates "programc" AST node. 
      *
      * @param lineNumber the line in the source file from which this node came.
      * @param a0 initial value for classes
      */
    public programc(int lineNumber, Classes a1) {
        super(lineNumber);
        classes = a1;
    }
    public TreeNode copy() {
        return new programc(lineNumber, (Classes)classes.copy());
    }
    public void dump(PrintStream out, int n) {
        out.print(Utilities.pad(n) + "programc\n");
        classes.dump(out, n+2);
    }

    
    public void dump_with_types(PrintStream out, int n) {
        dump_line(out, n);
        out.println(Utilities.pad(n) + "_program");
        for (Enumeration e = classes.getElements(); e.hasMoreElements(); ) {
            // sm: changed 'n + 1' to 'n + 2' to match changes elsewhere
	    ((Class_)e.nextElement()).dump_with_types(out, n + 2);
        }
    }
    /** This method is the entry point to the semantic checker.  You will
        need to complete it in programming assignment 4.
	<p>
        Your checker should do the following two things:
	<ol>
	<li>Check that the program is semantically correct
	<li>Decorate the abstract syntax tree with type information
        by setting the type field in each Expression node.
        (see tree.h)
	</ol>
	<p>
	You are free to first do (1) and make sure you catch all semantic
    	errors. Part (2) can be done in a second stage when you want
	to test the complete compiler.
    */
    public void semant() {
	/* ClassTable constructor may do some semantic analysis */
		ClassTable tablaClases = new ClassTable(classes);
		
		
		for (Enumeration e = tablaClases.getAllClasses().getElements(); e.hasMoreElements();){
			((Class_) e.nextElement()).scanFeatures(tablaClases);
		}
		
		/* some semantic analysis code may go here */
		for (Enumeration e = classes.getElements(); e.hasMoreElements();) {
            ((Class_) e.nextElement()).semant(tablaClases);
		}

		if (tablaClases.errors()) {
			System.err.println("Compilation halted due to static semantic errors.");
			System.exit(1);
		}
    }

}


/** Defines AST constructor 'class_c'.
    <p>
    See <a href="TreeNode.html">TreeNode</a> for full documentation. */
class class_c extends Class_ {
    protected AbstractSymbol name;
    protected AbstractSymbol parent;
    protected Features features;
    protected AbstractSymbol filename;
    /** Creates "class_c" AST node. 
      *
      * @param lineNumber the line in the source file from which this node came.
      * @param a0 initial value for name
      * @param a1 initial value for parent
      * @param a2 initial value for features
      * @param a3 initial value for filename
      */
    public class_c(int lineNumber, AbstractSymbol a1, AbstractSymbol a2, Features a3, AbstractSymbol a4) {
        super(lineNumber);
        name = a1;
        parent = a2;
        features = a3;
        filename = a4;
    }
    public TreeNode copy() {
        return new class_c(lineNumber, copy_AbstractSymbol(name), copy_AbstractSymbol(parent), (Features)features.copy(), copy_AbstractSymbol(filename));
    }
    public void dump(PrintStream out, int n) {
        out.print(Utilities.pad(n) + "class_c\n");
        dump_AbstractSymbol(out, n+2, name);
        dump_AbstractSymbol(out, n+2, parent);
        features.dump(out, n+2);
        dump_AbstractSymbol(out, n+2, filename);
    }

    
    public AbstractSymbol getFilename() { return filename; }
    public AbstractSymbol getName()     { return name; }
    public AbstractSymbol getParent()   { return parent; }

    public void dump_with_types(PrintStream out, int n) {
        dump_line(out, n);
        out.println(Utilities.pad(n) + "_class");
        dump_AbstractSymbol(out, n + 2, name);
        dump_AbstractSymbol(out, n + 2, parent);
        out.print(Utilities.pad(n + 2) + "\"");
        Utilities.printEscapedString(out, filename.getString());
        out.println("\"\n" + Utilities.pad(n + 2) + "(");
        for (Enumeration e = features.getElements(); e.hasMoreElements();) {
	    ((Feature)e.nextElement()).dump_with_types(out, n + 2);
        }
        out.println(Utilities.pad(n + 2) + ")");
    }
    
    @Override
    public void semant(ClassTable tablaClases) {
    
    
        SymbolTable tablaSimbolos = new SymbolTable();
        tablaSimbolos.enterScope();
        tablaSimbolos.addId(TreeConstants.self, TreeConstants.SELF_TYPE);
        for (Enumeration e = features.getElements(); e.hasMoreElements();) {
            Feature f = (Feature) e.nextElement();
            if (f instanceof attr) {
                f.semant(tablaClases, tablaSimbolos, this, true);
            }
        }
        for (Enumeration e = features.getElements(); e.hasMoreElements();) {
            ((Feature) e.nextElement()).semant(tablaClases, tablaSimbolos, this, false);
        }
        tablaSimbolos.exitScope();
    }

    @Override
    public void scanFeatures(ClassTable tablaClases) {
        for (Enumeration e = features.getElements(); e.hasMoreElements();) {
            Feature f = (Feature) e.nextElement();
                tablaClases.addFeature(f, this);
        }
    }
}


/** Defines AST constructor 'method'.
    <p>
    See <a href="TreeNode.html">TreeNode</a> for full documentation. */
class method extends Feature {
    protected AbstractSymbol name;
    protected Formals formals;
    protected AbstractSymbol return_type;
    protected Expression expr;
    /** Creates "method" AST node. 
      *
      * @param lineNumber the line in the source file from which this node came.
      * @param a0 initial value for name
      * @param a1 initial value for formals
      * @param a2 initial value for return_type
      * @param a3 initial value for expr
      */
    public method(int lineNumber, AbstractSymbol a1, Formals a2, AbstractSymbol a3, Expression a4) {
        super(lineNumber);
        name = a1;
        formals = a2;
        return_type = a3;
        expr = a4;
    }
    public TreeNode copy() {
        return new method(lineNumber, copy_AbstractSymbol(name), (Formals)formals.copy(), copy_AbstractSymbol(return_type), (Expression)expr.copy());
    }
    public void dump(PrintStream out, int n) {
        out.print(Utilities.pad(n) + "method\n");
        dump_AbstractSymbol(out, n+2, name);
        formals.dump(out, n+2);
        dump_AbstractSymbol(out, n+2, return_type);
        expr.dump(out, n+2);
    }

    
    public void dump_with_types(PrintStream out, int n) {
        dump_line(out, n);
        out.println(Utilities.pad(n) + "_method");
        dump_AbstractSymbol(out, n + 2, name);
        for (Enumeration e = formals.getElements(); e.hasMoreElements();) {
	    ((Formal)e.nextElement()).dump_with_types(out, n + 2);
        }
        dump_AbstractSymbol(out, n + 2, return_type);
	expr.dump_with_types(out, n + 2);
    }
	
	@Override
    public AbstractSymbol getName() {
        return name;
    }
   
    @Override
    public AbstractSymbol getType() {
        return return_type;
    }

    public Formals getFormals() {
        return formals;
    }

   @Override
    public void semant(ClassTable tablaClases, SymbolTable tablaSimbolos, class_c claseActual, boolean primeraRevision) {
        tablaSimbolos.enterScope();
        method metodoOriginal = this;
        AbstractSymbol nombreClase = claseActual.getName();
        while (!TreeConstants.Object_.equals(nombreClase)) {
            method findMethod = (method) tablaClases.getFeature(name, nombreClase, true);
            if (findMethod != null) {
                metodoOriginal = findMethod;
            }
            nombreClase = tablaClases.getParent(nombreClase);
        }
   
        if (metodoOriginal.getFormals().getLength() != formals.getLength()) {
            errorNumeroParametrosDiferente(claseActual, tablaClases);
        } else {        
            Enumeration f = metodoOriginal.getFormals().getElements();
            for (Enumeration e = formals.getElements(); e.hasMoreElements();) {
                AbstractSymbol tipoOriginal = ((Formal) e.nextElement()).getType();
                AbstractSymbol tipoUtilizado = ((Formal) f.nextElement()).getType();
				if (TreeConstants.No_type.equals(tipoUtilizado))
				{
					tipoUtilizado = TreeConstants.Object_;
				}
				if (TreeConstants.No_type.equals(tipoOriginal))
				{
					tipoOriginal = tipoUtilizado;
				}
                if (!tipoOriginal.equals(tipoUtilizado)) {
                    errorParametrosTiposDiferentes(tipoOriginal, tipoUtilizado, claseActual, tablaClases);
                }
            }
        }

        for (Enumeration e = formals.getElements(); e.hasMoreElements();) {
            ((Formal)e.nextElement()).semant(tablaClases, tablaSimbolos, claseActual);
        }
        AbstractSymbol exprType = expr.semant(tablaClases, tablaSimbolos, claseActual).get_type(); 
        tablaSimbolos.exitScope();

        if (!tablaClases.hasType(return_type)) {
            errorTipoRetornoIndefinido(claseActual, tablaClases);
            return_type = TreeConstants.Object_;
        }
		
		if (TreeConstants.No_type.equals(exprType))
		{
			exprType = return_type;
		}
		
        if (!TreeConstants.SELF_TYPE.equals(return_type)) {
            if (TreeConstants.SELF_TYPE.equals(exprType))
			{
				exprType = claseActual.getName();
			}
        }
 
        if (!exprType.equals(return_type)) {
            errorTipoRetornoNoCoincide(exprType, claseActual, tablaClases);
        }
	}

	private void errorNumeroParametrosDiferente(class_c claseActual, ClassTable tablaClases) {
        tablaClases.semantError(claseActual).println("Los números de los parámetros no coinciden en el método " +
                                                 name.toString() + ".");
	}

	private void errorParametrosTiposDiferentes(AbstractSymbol tipoOriginal, AbstractSymbol tipoUtilizado, 
                                              class_c claseActual, ClassTable tablaClases) {
        tablaClases.semantError(claseActual).println("En el método " +
                        name.toString() + ", el tipo del parámetro " + tipoOriginal.toString() +
                        " es diferente del tipo definido " + tipoUtilizado.toString());
	}

	private void errorTipoRetornoNoCoincide(AbstractSymbol tipoExpresion, class_c claseActual, ClassTable tablaClases) {
        tablaClases.semantError(claseActual).println("El tipo de retorno " + 
                   tipoExpresion.toString() + " del método " + name.toString() + 
                   " no corresponde al definido " + return_type.toString() + ".");
	}
	
	private void errorTipoRetornoIndefinido(class_c claseActual, ClassTable tablaClases) {
        tablaClases.semantError(claseActual).println("El tipo de retorno: " + return_type.toString() +
                                                 " en el método " + name.toString() + " está indefinido.");
	}
	
}

	
/** Defines AST constructor 'attr'.
    <p>
    See <a href="TreeNode.html">TreeNode</a> for full documentation. */
class attr extends Feature {
    protected AbstractSymbol name;
    protected AbstractSymbol type_decl;
    protected Expression init;
    /** Creates "attr" AST node. 
      *
      * @param lineNumber the line in the source file from which this node came.
      * @param a0 initial value for name
      * @param a1 initial value for type_decl
      * @param a2 initial value for init
      */
    public attr(int lineNumber, AbstractSymbol a1, AbstractSymbol a2, Expression a3) {
        super(lineNumber);
        name = a1;
        type_decl = a2;
        init = a3;
    }
    public TreeNode copy() {
        return new attr(lineNumber, copy_AbstractSymbol(name), copy_AbstractSymbol(type_decl), (Expression)init.copy());
    }
    public void dump(PrintStream out, int n) {
        out.print(Utilities.pad(n) + "attr\n");
        dump_AbstractSymbol(out, n+2, name);
        dump_AbstractSymbol(out, n+2, type_decl);
        init.dump(out, n+2);
    }
	
	@Override
    public AbstractSymbol getName() {
        return name;
    }
    
    @Override 
    public AbstractSymbol getType() {
        return type_decl;
	}
    
    public void dump_with_types(PrintStream out, int n) {
        dump_line(out, n);
        out.println(Utilities.pad(n) + "_attr");
        dump_AbstractSymbol(out, n + 2, name);
        dump_AbstractSymbol(out, n + 2, type_decl);
	init.dump_with_types(out, n + 2);
    }

	@Override
    public void semant(ClassTable tablaClases, SymbolTable tablaSimbolos, class_c claseActual, boolean primeraRevision) {
        int contadorErrores = 0;
        if (tablaSimbolos.probe(name) != null && primeraRevision) {
            errorAtirbutoDefinidoMultiples(claseActual, tablaClases);
            contadorErrores++;
        }
        if (!tablaClases.hasType(type_decl) && primeraRevision) {
            errorAtributoIndefinido(claseActual, tablaClases);
            type_decl = TreeConstants.No_type;
        }
        if (TreeConstants.self.equals(name) & primeraRevision) {
            errorAtributoNombreSelf(claseActual, tablaClases);
            contadorErrores++;
        }

        if (contadorErrores == 0 && primeraRevision) {
            tablaSimbolos.addId(name, type_decl); 
        }

        if (!primeraRevision) {
            if (tablaClases.getFeature(name, claseActual.getParent(), false) != null) {          
                errorAtributoHeredado(claseActual, tablaClases);  
            }
            if (init instanceof no_expr) {  
                return; 
            }
            AbstractSymbol tipoExpresion = init.semant(tablaClases, tablaSimbolos, claseActual).get_type(); 
			if (TreeConstants.No_type.equals(tipoExpresion)){
				tipoExpresion =type_decl;
			}
            if (!TreeConstants.SELF_TYPE.equals(type_decl)) {
				if (TreeConstants.SELF_TYPE.equals(tipoExpresion)){
					tipoExpresion = claseActual.getName();
				}
            }
            if (!tipoExpresion.equals(type_decl)) {
                errorTipoAtributoNoConforme(tipoExpresion, claseActual, tablaClases);
            }
        }
    }

    private void errorAtirbutoDefinidoMultiples(class_c claseActual, ClassTable tablaClases) {
        tablaClases.semantError(claseActual).println("Atributo " + name.toString() +" esta definido multiples veces.");
    }

    private void errorAtributoIndefinido(class_c claseActual, ClassTable tablaClases) {
        tablaClases.semantError(claseActual).println("Clase " + type_decl.toString() + 
                                                 " del atributo " + name.toString() + " es indefinido.");
    }

    private void errorAtributoNombreSelf(class_c claseActual, ClassTable tablaClases) {
        tablaClases.semantError(claseActual).println("'self' no puede ser el nombre de un atributo.");
    }

    private void errorAtributoHeredado(class_c claseActual, ClassTable tablaClases) {
        tablaClases.semantError(claseActual).println("Atributo " + name.toString() + " es un atributo de una clase heredada.");
    }

    private void errorTipoAtributoNoConforme(AbstractSymbol tipoExpresion, class_c claseActual, ClassTable tablaClases) {
        tablaClases.semantError(claseActual).println("Tipo no referido " + tipoExpresion.toString() + 
                               " de la inicializacion del atributo " + name.toString() + 
                               " no es conforme a la declaracion del tipo " + type_decl.toString() + ".");
	}
}

	

/** Defines AST constructor 'formalc'.
    <p>
    See <a href="TreeNode.html">TreeNode</a> for full documentation. */
class formalc extends Formal {
    protected AbstractSymbol name;
    protected AbstractSymbol type_decl;
    /** Creates "formalc" AST node. 
      *
      * @param lineNumber the line in the source file from which this node came.
      * @param a0 initial value for name
      * @param a1 initial value for type_decl
      */
    public formalc(int lineNumber, AbstractSymbol a1, AbstractSymbol a2) {
        super(lineNumber);
        name = a1;
        type_decl = a2;
    }
    public TreeNode copy() {
        return new formalc(lineNumber, copy_AbstractSymbol(name), copy_AbstractSymbol(type_decl));
    }
    public void dump(PrintStream out, int n) {
        out.print(Utilities.pad(n) + "formalc\n");
        dump_AbstractSymbol(out, n+2, name);
        dump_AbstractSymbol(out, n+2, type_decl);
    }

    
    public void dump_with_types(PrintStream out, int n) {
        dump_line(out, n);
        out.println(Utilities.pad(n) + "_formal");
        dump_AbstractSymbol(out, n + 2, name);
        dump_AbstractSymbol(out, n + 2, type_decl);
    }
	
	public AbstractSymbol getName() {
        return name;
    }
    
    public AbstractSymbol getType() {
        return type_decl;
	}

	@Override
    public void semant(ClassTable tablaClases, SymbolTable tablaSimbolos, class_c claseActual) {
        
	}
}


/** Defines AST constructor 'branch'.
    <p>
    See <a href="TreeNode.html">TreeNode</a> for full documentation. */
class branch extends Case {
    protected AbstractSymbol name;
    protected AbstractSymbol type_decl;
    protected Expression expr;
    /** Creates "branch" AST node. 
      *
      * @param lineNumber the line in the source file from which this node came.
      * @param a0 initial value for name
      * @param a1 initial value for type_decl
      * @param a2 initial value for expr
      */
    public branch(int lineNumber, AbstractSymbol a1, AbstractSymbol a2, Expression a3) {
        super(lineNumber);
        name = a1;
        type_decl = a2;
        expr = a3;
    }
    public TreeNode copy() {
        return new branch(lineNumber, copy_AbstractSymbol(name), copy_AbstractSymbol(type_decl), (Expression)expr.copy());
    }
    public void dump(PrintStream out, int n) {
        out.print(Utilities.pad(n) + "branch\n");
        dump_AbstractSymbol(out, n+2, name);
        dump_AbstractSymbol(out, n+2, type_decl);
        expr.dump(out, n+2);
    }
    
    public void dump_with_types(PrintStream out, int n) {
        dump_line(out, n);
        out.println(Utilities.pad(n) + "_branch");
        dump_AbstractSymbol(out, n + 2, name);
        dump_AbstractSymbol(out, n + 2, type_decl);
	expr.dump_with_types(out, n + 2);
    }
	
	@Override
    public AbstractSymbol getType() {
        return type_decl;
	}
	
	 @Override
    public AbstractSymbol semant(ClassTable tablaClases, SymbolTable tablaSimbolo, class_c claseActual) {
         AbstractSymbol tipoRetorno = TreeConstants.Object_;
		 return tipoRetorno;
	}

}


/** Defines AST constructor 'assign'.
    <p>
    See <a href="TreeNode.html">TreeNode</a> for full documentation. */
class assign extends Expression {
    protected AbstractSymbol name;
    protected Expression expr;
    /** Creates "assign" AST node. 
      *
      * @param lineNumber the line in the source file from which this node came.
      * @param a0 initial value for name
      * @param a1 initial value for expr
      */
    public assign(int lineNumber, AbstractSymbol a1, Expression a2) {
        super(lineNumber);
        name = a1;
        expr = a2;
    }
    public TreeNode copy() {
        return new assign(lineNumber, copy_AbstractSymbol(name), (Expression)expr.copy());
    }
    public void dump(PrintStream out, int n) {
        out.print(Utilities.pad(n) + "assign\n");
        dump_AbstractSymbol(out, n+2, name);
        expr.dump(out, n+2);
    }

    public void dump_with_types(PrintStream out, int n) {
        dump_line(out, n);
        out.println(Utilities.pad(n) + "_assign");
        dump_AbstractSymbol(out, n + 2, name);
	expr.dump_with_types(out, n + 2);
	dump_type(out, n);
    }
	
	@Override
    public Expression semant(ClassTable tablaClases, SymbolTable tablaSimbolos, class_c claseActual) {
		if (TreeConstants.self.equals(name)) {
            errorAsignacionSelf(claseActual, tablaClases);
            return set_type(TreeConstants.No_type);
		}
        AbstractSymbol tipoAsignado = (AbstractSymbol)tablaSimbolos.lookup(name);
		AbstractSymbol tipoExpresion = expr.semant(tablaClases, tablaSimbolos, claseActual).get_type();
        if (tipoAsignado == null) {
            Feature atributoFeature = tablaClases.getFeature(name, claseActual.getName(), false);
            if (atributoFeature != null) {
                tipoAsignado = atributoFeature.getType();
            } else {
                errorVariableNoDefinida(claseActual, tablaClases);
            }
        } 
		if (tipoAsignado != null) {  
		if (!tipoExpresion.equals(TreeConstants.No_type)){    
            if (!tipoExpresion.equals(tipoAsignado)) {
                errorTipoAssignNoConforme(tipoExpresion, tipoAsignado, claseActual, tablaClases);
            } else {
                return set_type(tipoAsignado);
            }
            }
} 
        return set_type(TreeConstants.No_type);        
    }

    private void errorVariableNoDefinida(class_c claseActual, ClassTable tablaClases) {
        tablaClases.semantError(claseActual).println("Hubo una asignacion a la variable no declarada "+ name.toString() +".");
	}
	
	private void errorAsignacionSelf(class_c claseActual, ClassTable tablaClases) {
        tablaClases.semantError(claseActual).println("No se puede asginar a 'self'.");
	}
	
	private void errorTipoAssignNoConforme(AbstractSymbol tipoExpresion, AbstractSymbol tipoAsignado, class_c claseActual, ClassTable tablaClases) {
        tablaClases.semantError(claseActual).println("Tipo " + tipoExpresion.toString() + 
                     " de la expresion asignada no conforme " + tipoAsignado.toString() +
                     " de identificador " + name.toString() + ".");
	}
}


/** Defines AST constructor 'static_dispatch'.
    <p>
    See <a href="TreeNode.html">TreeNode</a> for full documentation. */
class static_dispatch extends Expression {
    protected Expression expr;
    protected AbstractSymbol type_name;
    protected AbstractSymbol name;
    protected Expressions actual;
    /** Creates "static_dispatch" AST node. 
      *
      * @param lineNumber the line in the source file from which this node came.
      * @param a0 initial value for expr
      * @param a1 initial value for type_name
      * @param a2 initial value for name
      * @param a3 initial value for actual
      */
    public static_dispatch(int lineNumber, Expression a1, AbstractSymbol a2, AbstractSymbol a3, Expressions a4) {
        super(lineNumber);
        expr = a1;
        type_name = a2;
        name = a3;
        actual = a4;
    }
    public TreeNode copy() {
        return new static_dispatch(lineNumber, (Expression)expr.copy(), copy_AbstractSymbol(type_name), copy_AbstractSymbol(name), (Expressions)actual.copy());
    }
    public void dump(PrintStream out, int n) {
        out.print(Utilities.pad(n) + "static_dispatch\n");
        expr.dump(out, n+2);
        dump_AbstractSymbol(out, n+2, type_name);
        dump_AbstractSymbol(out, n+2, name);
        actual.dump(out, n+2);
    }

    
    public void dump_with_types(PrintStream out, int n) {
        dump_line(out, n);
        out.println(Utilities.pad(n) + "_static_dispatch");
	expr.dump_with_types(out, n + 2);
        dump_AbstractSymbol(out, n + 2, type_name);
        dump_AbstractSymbol(out, n + 2, name);
        out.println(Utilities.pad(n + 2) + "(");
        for (Enumeration e = actual.getElements(); e.hasMoreElements();) {
	    ((Expression)e.nextElement()).dump_with_types(out, n + 2);
        }
        out.println(Utilities.pad(n + 2) + ")");
	dump_type(out, n);
    }

	@Override
    public Expression semant(ClassTable tablaClases, SymbolTable tablaSimbolos, class_c claseActual) {
        AbstractSymbol tipoExpresion;
        method metodoDispatch = null;
        tipoExpresion = expr.semant(tablaClases, tablaSimbolos, claseActual).get_type();
        metodoDispatch = (method) tablaClases.getFeature(name, type_name, true);
        if (metodoDispatch == null) {
            errorMetodoNoDefinido(claseActual, tablaClases);
        } 
        return set_type(TreeConstants.No_type);
    }

    private void errorMetodoNoDefinido(class_c claseActual, ClassTable tablaClases) {
        tablaClases.semantError(claseActual).println("Hubo un Dispatch a un método no definido:  " + name.toString() + ".");
	}
}


/** Defines AST constructor 'dispatch'.
    <p>
    See <a href="TreeNode.html">TreeNode</a> for full documentation. */
class dispatch extends Expression {
    protected Expression expr;
    protected AbstractSymbol name;
    protected Expressions actual;
    /** Creates "dispatch" AST node. 
      *
      * @param lineNumber the line in the source file from which this node came.
      * @param a0 initial value for expr
      * @param a1 initial value for name
      * @param a2 initial value for actual
      */
    public dispatch(int lineNumber, Expression a1, AbstractSymbol a2, Expressions a3) {
        super(lineNumber);
        expr = a1;
        name = a2;
        actual = a3;
    }
    public TreeNode copy() {
        return new dispatch(lineNumber, (Expression)expr.copy(), copy_AbstractSymbol(name), (Expressions)actual.copy());
    }
    public void dump(PrintStream out, int n) {
        out.print(Utilities.pad(n) + "dispatch\n");
        expr.dump(out, n+2);
        dump_AbstractSymbol(out, n+2, name);
        actual.dump(out, n+2);
    }

    
    public void dump_with_types(PrintStream out, int n) {
        dump_line(out, n);
        out.println(Utilities.pad(n) + "_dispatch");
	expr.dump_with_types(out, n + 2);
        dump_AbstractSymbol(out, n + 2, name);
        out.println(Utilities.pad(n + 2) + "(");
        for (Enumeration e = actual.getElements(); e.hasMoreElements();) {
	    ((Expression)e.nextElement()).dump_with_types(out, n + 2);
        }
        out.println(Utilities.pad(n + 2) + ")");
	dump_type(out, n);
    }
	
	 @Override
	public Expression semant(ClassTable tablaClases, SymbolTable tablaSimbolos, class_c claseActual) {
		return set_type(TreeConstants.No_type);
	}

}


/** Defines AST constructor 'cond'.
    <p>
    See <a href="TreeNode.html">TreeNode</a> for full documentation. */
class cond extends Expression {
    protected Expression pred;
    protected Expression then_exp;
    protected Expression else_exp;
    /** Creates "cond" AST node. 
      *
      * @param lineNumber the line in the source file from which this node came.
      * @param a0 initial value for pred
      * @param a1 initial value for then_exp
      * @param a2 initial value for else_exp
      */
    public cond(int lineNumber, Expression a1, Expression a2, Expression a3) {
        super(lineNumber);
        pred = a1;
        then_exp = a2;
        else_exp = a3;
    }
    public TreeNode copy() {
        return new cond(lineNumber, (Expression)pred.copy(), (Expression)then_exp.copy(), (Expression)else_exp.copy());
    }
    public void dump(PrintStream out, int n) {
        out.print(Utilities.pad(n) + "cond\n");
        pred.dump(out, n+2);
        then_exp.dump(out, n+2);
        else_exp.dump(out, n+2);
    }

    public void dump_with_types(PrintStream out, int n) {
        dump_line(out, n);
        out.println(Utilities.pad(n) + "_cond");
		pred.dump_with_types(out, n + 2);
		then_exp.dump_with_types(out, n + 2);
		else_exp.dump_with_types(out, n + 2);
		dump_type(out, n);
    }
	
	@Override
	public Expression semant(ClassTable tablaClases, SymbolTable tablaSimbolos, class_c claseActual) {
	AbstractSymbol tipoCondicion = pred.semant(tablaClases, tablaSimbolos, claseActual).get_type();
        if (!TreeConstants.Bool.equals(tipoCondicion)) {
            errorNoBool(claseActual, tablaClases);
        }
        AbstractSymbol tipoThen = then_exp.semant(tablaClases, tablaSimbolos, claseActual).get_type();
        AbstractSymbol tipoElse = else_exp.semant(tablaClases, tablaSimbolos, claseActual).get_type();
        return set_type(TreeConstants.Bool);
    }

    private void errorNoBool(class_c claseActual, ClassTable tablaClases) {
        tablaClases.semantError(claseActual).println("Condicion del 'if' no es una expresion booleana.");
	}
}


/** Defines AST constructor 'loop'.
    <p>
    See <a href="TreeNode.html">TreeNode</a> for full documentation. */
class loop extends Expression {
    protected Expression pred;
    protected Expression body;
    /** Creates "loop" AST node. 
      *
      * @param lineNumber the line in the source file from which this node came.
      * @param a0 initial value for pred
      * @param a1 initial value for body
      */
    public loop(int lineNumber, Expression a1, Expression a2) {
        super(lineNumber);
        pred = a1;
        body = a2;
    }
    public TreeNode copy() {
        return new loop(lineNumber, (Expression)pred.copy(), (Expression)body.copy());
    }
    public void dump(PrintStream out, int n) {
        out.print(Utilities.pad(n) + "loop\n");
        pred.dump(out, n+2);
        body.dump(out, n+2);
    }

    
    public void dump_with_types(PrintStream out, int n) {
        dump_line(out, n);
        out.println(Utilities.pad(n) + "_loop");
	pred.dump_with_types(out, n + 2);
	body.dump_with_types(out, n + 2);
	dump_type(out, n);
    }
	
	@Override
	public Expression semant(ClassTable tablaClases, SymbolTable tablaSimbolos, class_c claseActual) {
		return set_type(TreeConstants.Object_);
	}

}


/** Defines AST constructor 'typcase'.
    <p>
    See <a href="TreeNode.html">TreeNode</a> for full documentation. */
class typcase extends Expression {
    protected Expression expr;
    protected Cases cases;
    /** Creates "typcase" AST node. 
      *
      * @param lineNumber the line in the source file from which this node came.
      * @param a0 initial value for expr
      * @param a1 initial value for cases
      */
    public typcase(int lineNumber, Expression a1, Cases a2) {
        super(lineNumber);
        expr = a1;
        cases = a2;
    }
    public TreeNode copy() {
        return new typcase(lineNumber, (Expression)expr.copy(), (Cases)cases.copy());
    }
    public void dump(PrintStream out, int n) {
        out.print(Utilities.pad(n) + "typcase\n");
        expr.dump(out, n+2);
        cases.dump(out, n+2);
    }

    
    public void dump_with_types(PrintStream out, int n) {
        dump_line(out, n);
        out.println(Utilities.pad(n) + "_typcase");
	expr.dump_with_types(out, n + 2);
        for (Enumeration e = cases.getElements(); e.hasMoreElements();) {
	    ((Case)e.nextElement()).dump_with_types(out, n + 2);
        }
	dump_type(out, n);
    }

	@Override
	public Expression semant(ClassTable tablaClases, SymbolTable tablaSimbolos, class_c claseActual) {
		AbstractSymbol retType = TreeConstants.Object_;
		return set_type(retType);
	}
}


/** Defines AST constructor 'block'.
    <p>
    See <a href="TreeNode.html">TreeNode</a> for full documentation. */
class block extends Expression {
    protected Expressions body;
    /** Creates "block" AST node. 
      *
      * @param lineNumber the line in the source file from which this node came.
      * @param a0 initial value for body
      */
    public block(int lineNumber, Expressions a1) {
        super(lineNumber);
        body = a1;
    }
    public TreeNode copy() {
        return new block(lineNumber, (Expressions)body.copy());
    }
    public void dump(PrintStream out, int n) {
        out.print(Utilities.pad(n) + "block\n");
        body.dump(out, n+2);
    }

    
    public void dump_with_types(PrintStream out, int n) {
        dump_line(out, n);
        out.println(Utilities.pad(n) + "_block");
        for (Enumeration e = body.getElements(); e.hasMoreElements();) {
	    ((Expression)e.nextElement()).dump_with_types(out, n + 2);
        }
	dump_type(out, n);
    }
	
	@Override
	public Expression semant(ClassTable tablaClases, SymbolTable tablaSimbolos, class_c claseActual) {
	Expression ultima;
        AbstractSymbol tipoBloque = TreeConstants.No_type;
        if (body.getLength() == 0 ) { return set_type(tipoBloque); }
        for (Enumeration exprs = body.getElements(); exprs.hasMoreElements();) {
            ultima = (Expression) exprs.nextElement();
            tipoBloque = ultima.semant(tablaClases, tablaSimbolos, claseActual).get_type();       
        }
	return set_type(tipoBloque);
	}

}


/** Defines AST constructor 'let'.
    <p>
    See <a href="TreeNode.html">TreeNode</a> for full documentation. */
class let extends Expression {
    protected AbstractSymbol identifier;
    protected AbstractSymbol type_decl;
    protected Expression init;
    protected Expression body;
    /** Creates "let" AST node. 
      *
      * @param lineNumber the line in the source file from which this node came.
      * @param a0 initial value for identifier
      * @param a1 initial value for type_decl
      * @param a2 initial value for init
      * @param a3 initial value for body
      */
    public let(int lineNumber, AbstractSymbol a1, AbstractSymbol a2, Expression a3, Expression a4) {
        super(lineNumber);
        identifier = a1;
        type_decl = a2;
        init = a3;
        body = a4;
    }
    public TreeNode copy() {
        return new let(lineNumber, copy_AbstractSymbol(identifier), copy_AbstractSymbol(type_decl), (Expression)init.copy(), (Expression)body.copy());
    }
    public void dump(PrintStream out, int n) {
        out.print(Utilities.pad(n) + "let\n");
        dump_AbstractSymbol(out, n+2, identifier);
        dump_AbstractSymbol(out, n+2, type_decl);
        init.dump(out, n+2);
        body.dump(out, n+2);
    }

    
    public void dump_with_types(PrintStream out, int n) {
        dump_line(out, n);
        out.println(Utilities.pad(n) + "_let");
	dump_AbstractSymbol(out, n + 2, identifier);
	dump_AbstractSymbol(out, n + 2, type_decl);
	init.dump_with_types(out, n + 2);
	body.dump_with_types(out, n + 2);
	dump_type(out, n);
    }
	
	@Override
	public Expression semant(ClassTable tablaClases, SymbolTable tablaSimbolos, class_c claseActual) {
		tablaSimbolos.enterScope();
        if (TreeConstants.self.equals(identifier)) { 
            errorLigadoASelf(claseActual, tablaClases);
        } else {
            if (!tablaClases.hasType(type_decl)) {
                errorIdIndefinido(claseActual, tablaClases);
                type_decl = TreeConstants.No_type;
            }
            if (!(init instanceof no_expr)) {
                AbstractSymbol tipoInicial = init.semant(tablaClases, tablaSimbolos, claseActual).get_type();
                if ((TreeConstants.SELF_TYPE.equals(type_decl) || TreeConstants.SELF_TYPE.equals(tipoInicial)) && 
                     !type_decl.equals(tipoInicial) && !TreeConstants.No_type.equals(type_decl) && 
                     !TreeConstants.No_type.equals(tipoInicial)) {
                    errorTipoNoConforme(tipoInicial, claseActual, tablaClases);
                }
				if (TreeConstants.No_type.equals(type_decl)) {
					type_decl = TreeConstants.Object_;
				}
				if (TreeConstants.No_type.equals(tipoInicial)) {
					tipoInicial = type_decl;
				}
                if (!tipoInicial.equals(type_decl)) {
                    errorTipoNoConforme(tipoInicial, claseActual, tablaClases);
                    type_decl = TreeConstants.No_type;
                }
            }
            tablaSimbolos.addId(identifier, type_decl);
        } 
         
        AbstractSymbol tipoExpresionLet = body.semant(tablaClases, tablaSimbolos, claseActual).get_type(); 
        tablaSimbolos.exitScope();

        return set_type(tipoExpresionLet); 
    } 

    private void errorLigadoASelf(class_c claseActual, ClassTable tablaClases) {
        tablaClases.semantError(claseActual).println("'self' no puede estar ligado a una expresion 'let'.");
    }

    private void errorIdIndefinido(class_c claseActual, ClassTable tablaClases) {
        tablaClases.semantError(claseActual).println("Clase " + type_decl.toString() + 
                                         " o un atributo del Let bound  " + identifier.toString() + " esta indefinida.");
    }

    private void errorTipoNoConforme(AbstractSymbol initType, class_c claseActual, ClassTable tablaClases) {
        tablaClases.semantError(claseActual).println("Tipo " + initType.toString() + 
                                         " de inicializacion " + identifier.toString() + 
                                         " no conforma la declaracion de tipo " + type_decl.toString() + ".");
	}

}


/** Defines AST constructor 'plus'.
    <p>
    See <a href="TreeNode.html">TreeNode</a> for full documentation. */
class plus extends Expression {
    protected Expression e1;
    protected Expression e2;
    /** Creates "plus" AST node. 
      *
      * @param lineNumber the line in the source file from which this node came.
      * @param a0 initial value for e1
      * @param a1 initial value for e2
      */
    public plus(int lineNumber, Expression a1, Expression a2) {
        super(lineNumber);
        e1 = a1;
        e2 = a2;
    }
    public TreeNode copy() {
        return new plus(lineNumber, (Expression)e1.copy(), (Expression)e2.copy());
    }
    public void dump(PrintStream out, int n) {
        out.print(Utilities.pad(n) + "plus\n");
        e1.dump(out, n+2);
        e2.dump(out, n+2);
    }

    
    public void dump_with_types(PrintStream out, int n) {
        dump_line(out, n);
        out.println(Utilities.pad(n) + "_plus");
	e1.dump_with_types(out, n + 2);
	e2.dump_with_types(out, n + 2);
	dump_type(out, n);
    }
	
	@Override
	public Expression semant(ClassTable tablaClases, SymbolTable tablaSimbolos, class_c claseActual) {
		AbstractSymbol primerTipo, segundoTipo;
        primerTipo = e1.semant(tablaClases, tablaSimbolos, claseActual).get_type();
        segundoTipo = e2.semant(tablaClases, tablaSimbolos, claseActual).get_type(); 
        if (primerTipo.equals(TreeConstants.Int) && segundoTipo.equals(TreeConstants.Int)) {
            return set_type(TreeConstants.Int);
        } else {
            errorNoEsInt(primerTipo, segundoTipo, claseActual, tablaClases);
        }
		return set_type(TreeConstants.No_type);
	}
	
	private void errorNoEsInt(AbstractSymbol izquierda, AbstractSymbol derecha, class_c claseActual, ClassTable tablaClases) {
        tablaClases.semantError(claseActual).println("Los siguientes argumentos no son de tipo int: " + izquierda.toString() + 
                                                 " + " + derecha.toString());
	}

}


/** Defines AST constructor 'sub'.
    <p>
    See <a href="TreeNode.html">TreeNode</a> for full documentation. */
class sub extends Expression {
    protected Expression e1;
    protected Expression e2;
    /** Creates "sub" AST node. 
      *
      * @param lineNumber the line in the source file from which this node came.
      * @param a0 initial value for e1
      * @param a1 initial value for e2
      */
    public sub(int lineNumber, Expression a1, Expression a2) {
        super(lineNumber);
        e1 = a1;
        e2 = a2;
    }
    public TreeNode copy() {
        return new sub(lineNumber, (Expression)e1.copy(), (Expression)e2.copy());
    }
    public void dump(PrintStream out, int n) {
        out.print(Utilities.pad(n) + "sub\n");
        e1.dump(out, n+2);
        e2.dump(out, n+2);
    }

    
    public void dump_with_types(PrintStream out, int n) {
        dump_line(out, n);
        out.println(Utilities.pad(n) + "_sub");
		e1.dump_with_types(out, n + 2);
		e2.dump_with_types(out, n + 2);
		dump_type(out, n);
    }
	
	@Override
	public Expression semant(ClassTable tablaClases, SymbolTable tablaSimbolos, class_c claseActual) {
		AbstractSymbol primerTipo, segundoTipo;
        primerTipo = e1.semant(tablaClases, tablaSimbolos, claseActual).get_type();
        segundoTipo = e2.semant(tablaClases, tablaSimbolos, claseActual).get_type(); 
        if (primerTipo.equals(TreeConstants.Int) && segundoTipo.equals(TreeConstants.Int)) {
            return set_type(TreeConstants.Int);
        } else {
            errorNoEsInt(primerTipo, segundoTipo, claseActual, tablaClases);
        }
		return set_type(TreeConstants.No_type);
	}
	
	private void errorNoEsInt(AbstractSymbol izquierda, AbstractSymbol derecha, class_c claseActual, ClassTable tablaClases) {
        tablaClases.semantError(claseActual).println("Los siguientes argumentos no son de tipo int: " + izquierda.toString() + 
                                                 " + " + derecha.toString());
	}
}


/** Defines AST constructor 'mul'.
    <p>
    See <a href="TreeNode.html">TreeNode</a> for full documentation. */
class mul extends Expression {
    protected Expression e1;
    protected Expression e2;
    /** Creates "mul" AST node. 
      *
      * @param lineNumber the line in the source file from which this node came.
      * @param a0 initial value for e1
      * @param a1 initial value for e2
      */
    public mul(int lineNumber, Expression a1, Expression a2) {
        super(lineNumber);
        e1 = a1;
        e2 = a2;
    }
    public TreeNode copy() {
        return new mul(lineNumber, (Expression)e1.copy(), (Expression)e2.copy());
    }
    public void dump(PrintStream out, int n) {
        out.print(Utilities.pad(n) + "mul\n");
        e1.dump(out, n+2);
        e2.dump(out, n+2);
    }

    
    public void dump_with_types(PrintStream out, int n) {
        dump_line(out, n);
        out.println(Utilities.pad(n) + "_mul");
	e1.dump_with_types(out, n + 2);
	e2.dump_with_types(out, n + 2);
	dump_type(out, n);
    }
	
	@Override
	public Expression semant(ClassTable tablaClases, SymbolTable tablaSimbolos, class_c claseActual) {
		AbstractSymbol primerTipo, segundoTipo;
        primerTipo = e1.semant(tablaClases, tablaSimbolos, claseActual).get_type();
        segundoTipo = e2.semant(tablaClases, tablaSimbolos, claseActual).get_type(); 
        if (primerTipo.equals(TreeConstants.Int) && segundoTipo.equals(TreeConstants.Int)) {
            return set_type(TreeConstants.Int);
        } else {
            errorNoEsInt(primerTipo, segundoTipo, claseActual, tablaClases);
        }
		return set_type(TreeConstants.No_type);
	}
	
	private void errorNoEsInt(AbstractSymbol izquierda, AbstractSymbol derecha, class_c claseActual, ClassTable tablaClases) {
        tablaClases.semantError(claseActual).println("Los siguientes argumentos no son de tipo int: " + izquierda.toString() + 
                                                 " + " + derecha.toString());
	}
}


/** Defines AST constructor 'divide'.
    <p>
    See <a href="TreeNode.html">TreeNode</a> for full documentation. */
class divide extends Expression {
    protected Expression e1;
    protected Expression e2;
    /** Creates "divide" AST node. 
      *
      * @param lineNumber the line in the source file from which this node came.
      * @param a0 initial value for e1
      * @param a1 initial value for e2
      */
    public divide(int lineNumber, Expression a1, Expression a2) {
        super(lineNumber);
        e1 = a1;
        e2 = a2;
    }
    public TreeNode copy() {
        return new divide(lineNumber, (Expression)e1.copy(), (Expression)e2.copy());
    }
    public void dump(PrintStream out, int n) {
        out.print(Utilities.pad(n) + "divide\n");
        e1.dump(out, n+2);
        e2.dump(out, n+2);
    }

    
    public void dump_with_types(PrintStream out, int n) {
        dump_line(out, n);
        out.println(Utilities.pad(n) + "_divide");
	e1.dump_with_types(out, n + 2);
	e2.dump_with_types(out, n + 2);
	dump_type(out, n);
    }
	
	@Override
	public Expression semant(ClassTable tablaClases, SymbolTable tablaSimbolos, class_c claseActual) {
		AbstractSymbol primerTipo, segundoTipo;
        primerTipo = e1.semant(tablaClases, tablaSimbolos, claseActual).get_type();
        segundoTipo = e2.semant(tablaClases, tablaSimbolos, claseActual).get_type(); 
        if (primerTipo.equals(TreeConstants.Int) && segundoTipo.equals(TreeConstants.Int)) {
            return set_type(TreeConstants.Int);
        } else {
            errorNoEsInt(primerTipo, segundoTipo, claseActual, tablaClases);
        }
		return set_type(TreeConstants.No_type);
	}
	
	private void errorNoEsInt(AbstractSymbol izquierda, AbstractSymbol derecha, class_c claseActual, ClassTable tablaClases) {
        tablaClases.semantError(claseActual).println("Los siguientes argumentos no son de tipo int: " + izquierda.toString() + 
                                                 " + " + derecha.toString());
	}

}


/** Defines AST constructor 'neg'.
    <p>
    See <a href="TreeNode.html">TreeNode</a> for full documentation. */
class neg extends Expression {
    protected Expression e1;
    /** Creates "neg" AST node. 
      *
      * @param lineNumber the line in the source file from which this node came.
      * @param a0 initial value for e1
      */
    public neg(int lineNumber, Expression a1) {
        super(lineNumber);
        e1 = a1;
    }
    public TreeNode copy() {
        return new neg(lineNumber, (Expression)e1.copy());
    }
    public void dump(PrintStream out, int n) {
        out.print(Utilities.pad(n) + "neg\n");
        e1.dump(out, n+2);
    }

    
    public void dump_with_types(PrintStream out, int n) {
        dump_line(out, n);
        out.println(Utilities.pad(n) + "_neg");
	e1.dump_with_types(out, n + 2);
	dump_type(out, n);
    }
	
	@Override
	public Expression semant(ClassTable tablaClases, SymbolTable tablaSimbolos, class_c claseActual) {
	AbstractSymbol tipoExpresion = e1.semant(tablaClases, tablaSimbolos, claseActual).get_type();
        if (TreeConstants.Int.equals(tipoExpresion)) {
            return set_type(TreeConstants.Int);
        } else {
            errorNoEsInt(tipoExpresion, claseActual, tablaClases);
        }
        return set_type(TreeConstants.No_type);
    }

    private void errorNoEsInt(AbstractSymbol tipoExpresion, class_c claseActual, ClassTable tablaClases) {
        tablaClases.semantError(claseActual).println("El argumento '~' tiene tipo" +
                                                 tipoExpresion.toString() + " en vez de int.");
	}

}


/** Defines AST constructor 'lt'.
    <p>
    See <a href="TreeNode.html">TreeNode</a> for full documentation. */
class lt extends Expression {
    protected Expression e1;
    protected Expression e2;
    /** Creates "lt" AST node. 
      *
      * @param lineNumber the line in the source file from which this node came.
      * @param a0 initial value for e1
      * @param a1 initial value for e2
      */
    public lt(int lineNumber, Expression a1, Expression a2) {
        super(lineNumber);
        e1 = a1;
        e2 = a2;
    }
    public TreeNode copy() {
        return new lt(lineNumber, (Expression)e1.copy(), (Expression)e2.copy());
    }
    public void dump(PrintStream out, int n) {
        out.print(Utilities.pad(n) + "lt\n");
        e1.dump(out, n+2);
        e2.dump(out, n+2);
    }

    
    public void dump_with_types(PrintStream out, int n) {
        dump_line(out, n);
        out.println(Utilities.pad(n) + "_lt");
	e1.dump_with_types(out, n + 2);
	e2.dump_with_types(out, n + 2);
	dump_type(out, n);
    }
	
	@Override
	public Expression semant(ClassTable tablaClases, SymbolTable tablaSimbolos, class_c claseActual) {
		AbstractSymbol primerTipo = e1.semant(tablaClases, tablaSimbolos, claseActual).get_type();
        AbstractSymbol segundoTipo = e2.semant(tablaClases, tablaSimbolos, claseActual).get_type();

        if (TreeConstants.Int.equals(primerTipo) && TreeConstants.Int.equals(segundoTipo)) {
            return set_type(TreeConstants.Bool);
        } else {
            errorNoEsInt(primerTipo, segundoTipo, claseActual, tablaClases);
        }
		return set_type(TreeConstants.No_type);
	}
	
	private void errorNoEsInt(AbstractSymbol izquierda, AbstractSymbol derecha, class_c claseActual, ClassTable tablaClases) {
        tablaClases.semantError(claseActual).println("Los siguientes argumentos no son de tipo int: " + izquierda.toString() + 
                                                 " + " + derecha.toString());
	}

}


/** Defines AST constructor 'eq'.
    <p>
    See <a href="TreeNode.html">TreeNode</a> for full documentation. */
class eq extends Expression {
    protected Expression e1;
    protected Expression e2;
    /** Creates "eq" AST node. 
      *
      * @param lineNumber the line in the source file from which this node came.
      * @param a0 initial value for e1
      * @param a1 initial value for e2
      */
    public eq(int lineNumber, Expression a1, Expression a2) {
        super(lineNumber);
        e1 = a1;
        e2 = a2;
    }
    public TreeNode copy() {
        return new eq(lineNumber, (Expression)e1.copy(), (Expression)e2.copy());
    }
    public void dump(PrintStream out, int n) {
        out.print(Utilities.pad(n) + "eq\n");
        e1.dump(out, n+2);
        e2.dump(out, n+2);
    }

    
    public void dump_with_types(PrintStream out, int n) {
        dump_line(out, n);
        out.println(Utilities.pad(n) + "_eq");
	e1.dump_with_types(out, n + 2);
	e2.dump_with_types(out, n + 2);
	dump_type(out, n);
    }
	
	@Override
    public Expression semant(ClassTable tablaClases, SymbolTable tablaSimbolos, class_c claseActual) {
        AbstractSymbol primerTipo = e1.semant(tablaClases, tablaSimbolos, claseActual).get_type();
        AbstractSymbol segundoTipo = e2.semant(tablaClases, tablaSimbolos, claseActual).get_type();
        if (TreeConstants.Int.equals(primerTipo) || TreeConstants.Bool.equals(primerTipo) || TreeConstants.Str.equals(primerTipo)) {
            if (!primerTipo.equals(segundoTipo)) {
                errorComparacionIlegal(claseActual, tablaClases);
                return set_type(TreeConstants.No_type);
            }
        }
        return set_type(TreeConstants.Bool);
    }

    private void errorComparacionIlegal(class_c claseActual, ClassTable tablaClases) {
        tablaClases.semantError(claseActual).println("Comparacion ilegal de tipos basicos.");
	}

}


/** Defines AST constructor 'leq'.
    <p>
    See <a href="TreeNode.html">TreeNode</a> for full documentation. */
class leq extends Expression {
    protected Expression e1;
    protected Expression e2;
    /** Creates "leq" AST node. 
      *
      * @param lineNumber the line in the source file from which this node came.
      * @param a0 initial value for e1
      * @param a1 initial value for e2
      */
    public leq(int lineNumber, Expression a1, Expression a2) {
        super(lineNumber);
        e1 = a1;
        e2 = a2;
    }
    public TreeNode copy() {
        return new leq(lineNumber, (Expression)e1.copy(), (Expression)e2.copy());
    }
    public void dump(PrintStream out, int n) {
        out.print(Utilities.pad(n) + "leq\n");
        e1.dump(out, n+2);
        e2.dump(out, n+2);
    }

    
    public void dump_with_types(PrintStream out, int n) {
        dump_line(out, n);
        out.println(Utilities.pad(n) + "_leq");
	e1.dump_with_types(out, n + 2);
	e2.dump_with_types(out, n + 2);
	dump_type(out, n);
    }
	
	@Override
	public Expression semant(ClassTable tablaClases, SymbolTable tablaSimbolos, class_c claseActual) {
		AbstractSymbol primerTipo = e1.semant(tablaClases, tablaSimbolos, claseActual).get_type();
        AbstractSymbol segundoTipo = e2.semant(tablaClases, tablaSimbolos, claseActual).get_type();

        if (TreeConstants.Int.equals(primerTipo) && TreeConstants.Int.equals(segundoTipo)) {
            return set_type(TreeConstants.Bool);
        } else {
            errorNoEsInt(primerTipo, segundoTipo, claseActual, tablaClases);
        }
		return set_type(TreeConstants.No_type);
	}
	
	private void errorNoEsInt(AbstractSymbol izquierda, AbstractSymbol derecha, class_c claseActual, ClassTable tablaClases) {
        tablaClases.semantError(claseActual).println("Los siguientes argumentos no son de tipo int: " + izquierda.toString() + 
                                                 " + " + derecha.toString());
	}

}


/** Defines AST constructor 'comp'.
    <p>
    See <a href="TreeNode.html">TreeNode</a> for full documentation. */
class comp extends Expression {
    protected Expression e1;
    /** Creates "comp" AST node. 
      *
      * @param lineNumber the line in the source file from which this node came.
      * @param a0 initial value for e1
      */
    public comp(int lineNumber, Expression a1) {
        super(lineNumber);
        e1 = a1;
    }
    public TreeNode copy() {
        return new comp(lineNumber, (Expression)e1.copy());
    }
    public void dump(PrintStream out, int n) {
        out.print(Utilities.pad(n) + "comp\n");
        e1.dump(out, n+2);
    }

    
    public void dump_with_types(PrintStream out, int n) {
        dump_line(out, n);
        out.println(Utilities.pad(n) + "_comp");
	e1.dump_with_types(out, n + 2);
	dump_type(out, n);
    }
	
	@Override
	public Expression semant(ClassTable tablaClases, SymbolTable tablaSimbolos, class_c claseActual) {
		AbstractSymbol tipoExpresion = e1.semant(tablaClases, tablaSimbolos, claseActual).get_type();
        if (TreeConstants.Bool.equals(tipoExpresion)) {
            return set_type(TreeConstants.Bool);
        } else {
            errorNoBool(tipoExpresion, claseActual, tablaClases);
        }
        return set_type(TreeConstants.No_type);
    }

    private void errorNoBool(AbstractSymbol tipoExpresion, class_c claseActual, ClassTable tablaClases) {
        tablaClases.semantError(claseActual).println("Argumento 'not' tiene tipo " + tipoExpresion.toString() + 
                                                 " en lugar de Bool.");
	}

}


/** Defines AST constructor 'int_const'.
    <p>
    See <a href="TreeNode.html">TreeNode</a> for full documentation. */
class int_const extends Expression {
    protected AbstractSymbol token;
    /** Creates "int_const" AST node. 
      *
      * @param lineNumber the line in the source file from which this node came.
      * @param a0 initial value for token
      */
    public int_const(int lineNumber, AbstractSymbol a1) {
        super(lineNumber);
        token = a1;
    }
    public TreeNode copy() {
        return new int_const(lineNumber, copy_AbstractSymbol(token));
    }
    public void dump(PrintStream out, int n) {
        out.print(Utilities.pad(n) + "int_const\n");
        dump_AbstractSymbol(out, n+2, token);
    }

    
    public void dump_with_types(PrintStream out, int n) {
        dump_line(out, n);
        out.println(Utilities.pad(n) + "_int");
	dump_AbstractSymbol(out, n + 2, token);
	dump_type(out, n);
    }
	
	@Override
	public Expression semant(ClassTable tablaClases, SymbolTable tablaSimbolos, class_c claseActual) {
		return set_type(TreeConstants.Int);
	}

}


/** Defines AST constructor 'bool_const'.
    <p>
    See <a href="TreeNode.html">TreeNode</a> for full documentation. */
class bool_const extends Expression {
    protected Boolean val;
    /** Creates "bool_const" AST node. 
      *
      * @param lineNumber the line in the source file from which this node came.
      * @param a0 initial value for val
      */
    public bool_const(int lineNumber, Boolean a1) {
        super(lineNumber);
        val = a1;
    }
    public TreeNode copy() {
        return new bool_const(lineNumber, copy_Boolean(val));
    }
    public void dump(PrintStream out, int n) {
        out.print(Utilities.pad(n) + "bool_const\n");
        dump_Boolean(out, n+2, val);
    }

    
    public void dump_with_types(PrintStream out, int n) {
        dump_line(out, n);
        out.println(Utilities.pad(n) + "_bool");
	dump_Boolean(out, n + 2, val);
	dump_type(out, n);
    }
	
	@Override
	public Expression semant(ClassTable tablaClases, SymbolTable tablaSimbolos, class_c claseActual) {
        return set_type(TreeConstants.Bool);
	}

}


/** Defines AST constructor 'string_const'.
    <p>
    See <a href="TreeNode.html">TreeNode</a> for full documentation. */
class string_const extends Expression {
    protected AbstractSymbol token;
    /** Creates "string_const" AST node. 
      *
      * @param lineNumber the line in the source file from which this node came.
      * @param a0 initial value for token
      */
    public string_const(int lineNumber, AbstractSymbol a1) {
        super(lineNumber);
        token = a1;
    }
    public TreeNode copy() {
        return new string_const(lineNumber, copy_AbstractSymbol(token));
    }
    public void dump(PrintStream out, int n) {
        out.print(Utilities.pad(n) + "string_const\n");
        dump_AbstractSymbol(out, n+2, token);
    }

    
    public void dump_with_types(PrintStream out, int n) {
        dump_line(out, n);
        out.println(Utilities.pad(n) + "_string");
	out.print(Utilities.pad(n + 2) + "\"");
	Utilities.printEscapedString(out, token.getString());
	out.println("\"");
	dump_type(out, n);
    }
	
	@Override
	public Expression semant(ClassTable tablaClases, SymbolTable tablaSimbolos, class_c claseActual) {
		return set_type(TreeConstants.Str);
	}

}


/** Defines AST constructor 'new_'.
    <p>
    See <a href="TreeNode.html">TreeNode</a> for full documentation. */
class new_ extends Expression {
    protected AbstractSymbol type_name;
    /** Creates "new_" AST node. 
      *
      * @param lineNumber the line in the source file from which this node came.
      * @param a0 initial value for type_name
      */
    public new_(int lineNumber, AbstractSymbol a1) {
        super(lineNumber);
        type_name = a1;
    }
    public TreeNode copy() {
        return new new_(lineNumber, copy_AbstractSymbol(type_name));
    }
    public void dump(PrintStream out, int n) {
        out.print(Utilities.pad(n) + "new_\n");
        dump_AbstractSymbol(out, n+2, type_name);
    }

    
    public void dump_with_types(PrintStream out, int n) {
        dump_line(out, n);
        out.println(Utilities.pad(n) + "_new");
	dump_AbstractSymbol(out, n + 2, type_name);
	dump_type(out, n);
    }

	@Override
    public Expression semant(ClassTable tablaClases, SymbolTable tablaSimbolos, class_c claseActual) {
        if (tablaClases.hasType(type_name)) {
            return set_type(type_name);
        }
        errorClaseIndefinida(claseActual, tablaClases);
        return set_type(TreeConstants.No_type);     
    }

    private void errorClaseIndefinida(class_c claseActual, ClassTable tablaClases) {
        tablaClases.semantError(claseActual).println("Se intentó definir un objeto con el tipo indefinido " + type_name.toString() + ".");
	}
	
}


/** Defines AST constructor 'isvoid'.
    <p>
    See <a href="TreeNode.html">TreeNode</a> for full documentation. */
class isvoid extends Expression {
    protected Expression e1;
    /** Creates "isvoid" AST node. 
      *
      * @param lineNumber the line in the source file from which this node came.
      * @param a0 initial value for e1
      */
    public isvoid(int lineNumber, Expression a1) {
        super(lineNumber);
        e1 = a1;
    }
    public TreeNode copy() {
        return new isvoid(lineNumber, (Expression)e1.copy());
    }
    public void dump(PrintStream out, int n) {
        out.print(Utilities.pad(n) + "isvoid\n");
        e1.dump(out, n+2);
    }

    
    public void dump_with_types(PrintStream out, int n) {
        dump_line(out, n);
        out.println(Utilities.pad(n) + "_isvoid");
	e1.dump_with_types(out, n + 2);
	dump_type(out, n);
    }
	
	@Override
	public Expression semant(ClassTable tablaClases, SymbolTable tablaSimbolos, class_c claseActual) {
		e1.semant(tablaClases, tablaSimbolos, claseActual);
		return set_type(TreeConstants.Bool);
	}

}


/** Defines AST constructor 'no_expr'.
    <p>
    See <a href="TreeNode.html">TreeNode</a> for full documentation. */
class no_expr extends Expression {
    /** Creates "no_expr" AST node. 
      *
      * @param lineNumber the line in the source file from which this node came.
      */
    public no_expr(int lineNumber) {
        super(lineNumber);
    }
    public TreeNode copy() {
        return new no_expr(lineNumber);
    }
    public void dump(PrintStream out, int n) {
        out.print(Utilities.pad(n) + "no_expr\n");
    }

    
    public void dump_with_types(PrintStream out, int n) {
        dump_line(out, n);
        out.println(Utilities.pad(n) + "_no_expr");
	dump_type(out, n);
    }
	
	@Override
	public Expression semant(ClassTable tablaClases, SymbolTable tablaSimbolos, class_c claseActual) {
		return set_type(TreeConstants.No_type);
	}

}


/** Defines AST constructor 'object'.
    <p>
    See <a href="TreeNode.html">TreeNode</a> for full documentation. */
class object extends Expression {
    protected AbstractSymbol name;
    /** Creates "object" AST node. 
      *
      * @param lineNumber the line in the source file from which this node came.
      * @param a0 initial value for name
      */
    public object(int lineNumber, AbstractSymbol a1) {
        super(lineNumber);
        name = a1;
    }
    public TreeNode copy() {
        return new object(lineNumber, copy_AbstractSymbol(name));
    }
    public void dump(PrintStream out, int n) {
        out.print(Utilities.pad(n) + "object\n");
        dump_AbstractSymbol(out, n+2, name);
    }

    
    public void dump_with_types(PrintStream out, int n) {
        dump_line(out, n);
        out.println(Utilities.pad(n) + "_object");
	dump_AbstractSymbol(out, n + 2, name);
	dump_type(out, n);
    }
	
	@Override
	public Expression semant(ClassTable tablaClases, SymbolTable tablaSimbolos, class_c claseActual) {
	if (tablaSimbolos.lookup(name) != null) {
            return set_type((AbstractSymbol) tablaSimbolos.lookup(name));
        } else {
            attr objetoAtributo = (attr) tablaClases.getFeature(name, claseActual.getName(), false);
            if (objetoAtributo != null) {
                return set_type(objetoAtributo.getType());
            }
            errorIdIndefinido(claseActual, tablaClases);
        }
        return set_type(TreeConstants.No_type);
    }

    private void errorIdIndefinido(class_c claseActual, ClassTable tablaClases) {
        tablaClases.semantError(claseActual).println("Identificador no declarado " + name.toString() + ".");
	}
}