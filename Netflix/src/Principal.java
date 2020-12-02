import java.util.Scanner;

public class Principal {
// CONSTANTES
// Administrador
// Pwd
	static long SegSabado=7*3600;
	static long SegDomingo=5*3600;

	static int FinDeSemanaCompleto=-11;
	
	// Drama;
	// 0 Libre sabado y domingo
	// 1 Sabado ocupado, Domingo libre
	// 2 No hay libre
	//
	static String Pelis="Drama;0;0;A;3600;0;B;7200;Comedia;0;C;3600;0;D;7000";
	// Estado Peli
	// 0 sabado y domingo libre
	// 1 seleccionada
	// 2 cabe sabado
	// 3 cabe domingo
	// Recorrer para mostrar libres usa 2 y 3??? Aki me he quedado
	// Recorrer para mostrar seleccionadsa usa 1
	
	// Estado Drama -> Pelis.charAt(Pelis.indexOf(";",0)+1
	// Estado Comedia -> Pelis.charAt(Pelis.lastIndexOf("Comedia")+8)
	
	static Scanner sc = new Scanner(System.in);

// Nombres Pelis
// Genero Pelis
// DuracionPelis
	
// Static
	static long SegFaltanSabado=SegSabado;
	//static long SegFaltanSabado=0;
	static long SegFaltanDomingo=SegDomingo;
	static String PelisSelec="";
	static String PelisDisp="";

	public static void main(String[] args) {
		
		String cadena;
//		static String Pelis="Drama;0;0;A;3600;0;B;7200;Comedia;0;C;3600;0;D;7000";
		
	/*	System.out.println(Pelis.charAt(Pelis.lastIndexOf("Comedia")+8));
		System.out.println(Pelis.lastIndexOf("Comedia")+10);
		System.out.println(Pelis.indexOf("Comedia")+10);

		System.exit(0);
		*/// Mostrar Bienvenida con msje de saludo
		// Al pasar 3 segundos, aparecerá la pantalla de login
		MostrarBienvenida();
		
		MostrarLogin();
		
		// Mostrar Generos + tiempo restante + pelis escogidas
		/*0. Salir
		1. Drama
		2.- Comedia
		3.- Terror
		4.- Ciencia Ficción*/
		// AL selec pasa a
		while (true)
		{
			int generoSelec=MostrarGeneros();
			if (generoSelec==0)
				Salir();
		
			// Mostrar Pelis 
			// Selecciona peli
			// Puede volver a Géneros (selecciona -1)
			// 0. Salir
			// Al selec una peli volverá a Géneros- desapareciendo el de la peli escogida
			// si cabe, y si no avisa al user		
			// salvo que hayamos acabado en cuyo caso (FinDeSemanaCompleto) pasará a Resumen 
			int estado=MostrarPelis(generoSelec);
			if (estado==0)
				Salir();
			if (estado==FinDeSemanaCompleto)
			{
				// Mostrar Resumen
				// ¿Está de acuerod?
				// If not resetea y vuelve a Géneros 
				// If yes SALIR
				boolean deAcuerdo=MostrarResumen();
				if (deAcuerdo)
					Salir();
				Resetea();
			}
		}
		
		
	}
	static void MostrarBienvenida()
	{
		System.out.println("Bienvenida");
	}
	static void MostrarLogin()
	{
		System.out.println("Login");
	}
	static int MostrarGeneros()
	{
		System.out.println("Generos");		
		MostrarTiempoRestante();
		boolean DramaMostrado=false; 
		if (SegFaltanSabado != 0) // Sobra tiempo el sábado
		{
			// Si Drama libre el sabado y hay dramas que caben en ese tiempo
			if ((Pelis.charAt(Pelis.indexOf(";",0)+1) == '0') && (CabenPelis("sabado","Drama")))
				// Drama libre el sabado
			{
				//System.out.println(Pelis.charAt(Pelis.indexOf(";",0)+1));
				System.out.println("1. Drama");
				DramaMostrado=true;
			}
			// idem Comedia
			
		}
		else if (SegFaltanDomingo != 0) // Sobra tiempo el domingo
		{
			// si Drama libre el domingo y hay dramas que caben en ese tiempo
			if (((Pelis.charAt(Pelis.indexOf(";",0)+1) == '0') || (Pelis.charAt(Pelis.indexOf(";",0)+1) == '1'))&& (CabenPelis("domingo","Drama")))
				// Drama libre el domingo
			{
				if (!DramaMostrado)
					System.out.println("1. Drama");
			}
			// idem Comedia
			
		}
		System.out.println("Selecciona genero o 0 para salir ");
		String cadena=sc.nextLine();
		int opcion=Integer.parseInt(cadena);
		return opcion;
	}
	static int MostrarPelis(int genero)
	{
		// Mostrar Pelis 
		// Selecciona peli
		// 0. Volver a Géneros
		// Al selec una peli volverá a Géneros - desapareciendo el de la peli escogida
		// si cabe, y si no avisa al user
		// salvo que hayamos acabado en cuyo caso (FinDeSemanaCompleto) pasará a Resumen 
		
		System.out.println("Pelis");
		// MostrarPelisLibre(genero);
		String PelisLibres="";
	//	String generosS=ConvertirGeneroString(genero);
	//	PelisLibres=ConstruyeStringPelisLibres("",generoS);
		PelisLibres=ConstruyeStringPelisLibres("","Drama");
		System.out.println(PelisLibres);
		// Habría que ponerlo bonito 1. PeliA Duracion etc
		System.out.println("Selecciona peli, o 0 para salir, o -1 para ir a Generos");
		String cadena=sc.nextLine();
		int opcion=Integer.parseInt(cadena);
		if (opcion == 0)
			return -1; // quiere volver a generos
	
		//ActualizarGenerosPelisTiempoRestante(opcion, PelisLibres);
			
		if (opcion == FinDeSemanaCompleto) 
			return FinDeSemanaCompleto;
				
		return -1;
		
	}
	
	static boolean MostrarResumen()
	{
		System.out.println("Resumen");
		MostrarPelisSeleccionadas();
		System.out.println("true para aceptar o false para salir");
		String cadena=sc.nextLine();
		boolean opcion=Boolean.parseBoolean(cadena);
		return opcion;
	}
	static void Resetea()
	{
		System.out.println("Resetea");
	}
	static void Salir()
	{
		sc.close();
		System.exit(0);
	}
	static void MostrarPelisSeleccionadas()
	{
		// Recorrer Pelis 
		// Recorrer Dramas
		// Si 1 -> Mostrar ese titulo + Sabado
		// Si 2 -> Mostrar ese titulo + Domingo
		// idem resto
		//System.out.println("Pelis Seleccionadas: "+PelisSelec);
	}
	
	static void MostrarTiempoRestante()
	{
		System.out.println("Tiempo restante sabado: "+SegFaltanSabado);
		System.out.println("Tiempo restante domingo: "+SegFaltanDomingo);

	}
	static boolean CabenPelis(String dia, String genero)
	{
//		String PelisLibres=ConstruyeStringPelisLibres(dia,genero);
//		if (PelisLibres.equalsIgnoreCase(""))
//				return false;
		return true;
	}
//	System.out.println(Pelis.charAt(Pelis.indexOf(";",0)+1));
//	System.out.println(Pelis.charAt(Pelis.lastIndexOf("Comedia")+8));
	
	static String ConstruyeStringPelisLibres(String dia, String genero)
	{
//		static String Pelis="Drama;0;0;A;3600;0;B;7200;Comedia;0;C;3600;0;D;7000";

		String cadena="";
		if (dia.equalsIgnoreCase(""))
		{
			// me llama MostrarPelis 
			// tengo que averiguar si el sabado está libre ..
			if (SegFaltanSabado != 0)
			{
				if (genero.equalsIgnoreCase("Drama"))
				{
					int contador=Pelis.indexOf("Drama",0)+8; // o sea, 8
					
					while (contador<Pelis.indexOf("Comedia",0))
					{
						//Averiguar si peli libre, Averiguar si cabe y Actualziar PelisLibres					
						if (Pelis.charAt(contador)=='0')
						{
							cadena+=Pelis.substring(contador+2, Pelis.indexOf(";",Pelis.indexOf(";",contador+4)))+"\n";
							contador=Pelis.indexOf(";",contador+4)+1;
						}
						
					}
				}
			}
			return cadena;
		}
		if (dia.equalsIgnoreCase("sabado"))
		{
			//me llama CabenPelis
			return cadena;
		}
		if (dia.equalsIgnoreCase("domingo"))
		{
			//me llama CabenPelis
		}
		return cadena;
		
	}
}



