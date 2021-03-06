package application;

import java.util.ArrayList;

import capacite.*;
import carte.*;
import joueur.*;
import plateau.Plateau;
import menu.*;
import exception.*;
import heros.Heros;

public class Main {
	
	public static Interface ihm = null;
	public final static Console es = new Console();
	
	public static ArrayList<ICarte> CarteJaina(IJoueur proprietaire)
	{
		ArrayList<ICarte> cartesJaina = new ArrayList<ICarte> ();
		ICarte flamme =new Sort("Choc de Flamme ", 7 , proprietaire ,new AttaqueTotale("Attaque massive","inflige 4 pts de d�gats a tous les serviteurs adverses ", 4));
		cartesJaina.add(flamme);
		ICarte givre = new Sort ("Eclair de givre", 2, proprietaire, new AttaqueCibleSort("Attaque de givre", "inflige 3 pts de d�gats au personnages cible", 3));
		cartesJaina.add(givre);
		ICarte arcanes = new Sort ("Intelligence des arcanes", 2, proprietaire, new Pioche("Pioche","Pioche deux cartes",2));
		cartesJaina.add(arcanes);
		ICarte mirroir = new Sort ("Image mirroir", 1, proprietaire, new ImageMirroir("Image Mirroir", "Invoque deux serviteurs de Jaina 0/+2 ayant provocation"));
		cartesJaina.add(mirroir);
		ICarte explosion = new Sort ("Explosion Pyrotechnique", 10, proprietaire, new AttaqueTotale("Explosion Pyrotechnique", "inflige 10 pts de d�gats a tous les serviteurs adverses", 10));
		cartesJaina.add(explosion);
		
		return cartesJaina;
		
	}
	
	public static ArrayList<ICarte> CarteRexxar(IJoueur proprietaire)
	{
		ArrayList<ICarte> cartesRexxar = new ArrayList<ICarte> ();
		ICarte chasseur = new Sort ("Marque du Chasseur", 1, proprietaire, new MarqueChasseur("Marque du chaseur","Abaisse � 1 les points de vie du serviteur cibl�" ));
		cartesRexxar.add(chasseur);
		ICarte tirarcanes = new Sort ("Tir des arcanes", 1, proprietaire, new AttaqueCibleSort("Tir des arcanes", "inflige 2 pts de d�gats au personnage cible", 2));
		cartesRexxar.add(tirarcanes);
		ICarte chiens = new Sort ("Lachez les chiens", 3, proprietaire, new InvocationChien("invocation des chiens","invoque autant de chiens que l'adversaire a de cartes en jeu" )); 
		cartesRexxar.add(chiens);
		ICarte tuer = new Sort ("Ordre de tuer", 3, proprietaire, new AttaqueCibleSort("Ordre de tuer", "Inflige 3 pts de degats au personnage cible", 3)); 
		cartesRexxar.add(tuer);
		ICarte busard = new Serviteur("Busard affam�", 5, proprietaire, 3, 2, new Pioche("Pioche", "Pioche une carte",1) );
		cartesRexxar.add(busard);
		
		return cartesRexxar;
		
	}
	
	public static ArrayList<ICarte> CartesNeutres(IJoueur proprietaire)
	{
		ArrayList<ICarte> cartesNeutres = new ArrayList<ICarte> ();
		ICarte chasse_marree=new Serviteur ("Chasse mar�e murloc" , 2, proprietaire , 2 , 1 , new InvocationServiteur ("Cri de guerre" , "Invocation d'un serviteur 1/1" , new Serviteur ("Serviteur de Chasse-maree" , 0 , proprietaire , 1, 1,null)));
		cartesNeutres.add(chasse_marree);
		
		ICarte champion_hurlevent= new Serviteur ("Champion de Hurlevent" , 7 , proprietaire ,  6 ,6 , new EffetPermanent ( " Bonus de hurlevent " , "Effet sur les serviteurs donnant un bonus +1/+1 " , 1 , 1));
		cartesNeutres.add(champion_hurlevent);
		
		ICarte chef_raid = new Serviteur ("Chef de Raid" , 3 , proprietaire , 2 , 2 , new EffetPermanent ( " Bonus du chef de raid " , " Effet sur les serviteurs alli�s donnant un bonus +1/0" , 1 , 0 ));  
		cartesNeutres.add(chef_raid);
		
		ICarte missiliere= new Serviteur ("La missili�re t�meraire " , 6 , proprietaire , 5 ,  2 , new Charge ());
		cartesNeutres.add(missiliere);
		
		ICarte ogre_magi= new Serviteur ( "L'ogre-magi" , 4 , proprietaire , 4 , 4 , new Provocation ());
		cartesNeutres.add(ogre_magi);
		
		ICarte archimage = new Serviteur ("Archimage" , 6 , proprietaire , 4 ,7 , new Provocation());
		cartesNeutres.add(archimage);
		
		ICarte gnome = new Serviteur ("Gn�me l�preux " , 1 ,proprietaire , 1 , 1 , new AttaqueHeros ( " Attaque du l�preux " , "Inflige 2 points de d�gats au h�ros " , 2 ));
		cartesNeutres.add(gnome);
		
		ICarte golem = new Serviteur ( "Golem des moissons" , 3 , proprietaire , 2 , 3 , new InvocationServiteur ( "Golemisation " , " Invoque un Golem endomage 2/1" , new Serviteur ( " Serviteur de Golem " , 0 , proprietaire , 2 , 1 ,null)));
		cartesNeutres.add(golem);
		
		ICarte charge = new Sort("Charge" , 1 , proprietaire , new Charge());
		cartesNeutres.add(charge);
		
		ICarte attaque_mentale= new Sort ( " Attaque mentale " , 2 ,  proprietaire , new AttaqueHeros ( "Attaque mentale " , "Inflige 5 points de d�gats au h�ros " , 5));
		cartesNeutres.add(attaque_mentale);
		
		return cartesNeutres;
		
		
}
	
	
	public static void main(String[] args) throws HearthstoneException {
		// Cr�ation des joueurs
		System.out.println("Bonjour ");
		System.out.println("Il faut un pseudo pour le premier joueur : ");
		String rep = es.readLine();
		IJoueur joueur1=new Joueur(rep , new Heros("Rexxar" , new AttaqueHeros ("Tir assur� ","Inflige 2 points de degats au Hero adverse", 2)) );
		
		System.out.println("Il faut un pseudo pour le deuxieme joueur : ");
		rep = es.readLine();
		IJoueur joueur2=new Joueur ( rep , new Heros("Jaina",new AttaqueCibleSort("Boule de feu","Inflige 1 point de degat � la cible", 1)));
		
		while( joueur1.getPseudo().equals(joueur2.getPseudo()) ){
			System.out.println("\nIl faut un pseudo different pour le premier joueur : ");
			rep = es.readLine();
			joueur1=new Joueur(rep , new Heros("Rexxar" , new AttaqueHeros ("Tir assur� ","Inflige 2 points de degats au Hero adverse", 2)) );
			
			System.out.println("Il faut un pseudo pour le deuxieme joueur : ");
			rep = es.readLine();
			joueur2=new Joueur ( rep , new Heros("Jaina",new AttaqueCibleSort("Boule de feu","Inflige 1 point de degat � la cible", 1)));
		}
		
		try {
			((Joueur) joueur1).setDeck(CarteRexxar(joueur1));
		} catch (HearthstoneException e1) {
			e1.printStackTrace();
		}
		try {
			((Joueur) joueur2).setDeck(CarteJaina(joueur2));
		} catch (HearthstoneException e1) {
			e1.printStackTrace();
		}
		
		((Joueur) joueur1).getDeck().addAll(CartesNeutres(joueur1));
		((Joueur) joueur2).getDeck().addAll(CartesNeutres(joueur2));
		
		
		/*ICarte attaque_mentale= new Sort ( " Attaque mentale " , 2 ,  joueur1 , new AttaqueHeros ( "Attaque mentale " , "Inflige 5 points de d�gats au h�ros " , 5));
		((Joueur) joueur1).getMain().add(attaque_mentale);*/
		
		/*ICarte chasse_marree=new Serviteur ("Chasse mar�e murloc" , 2, joueur1 , 2 , 1 , new InvocationServiteur ("Cri de guerre" , "Invocation d'un serviteur 1/1" , new Serviteur ("Serviteur de Chasse-maree" , 0 , joueur1 , 1, 1,null)));
		((Joueur) joueur1).getMain().add(chasse_marree);*/
		
		//Test Attaque total
		/*ICarte flamme =new Sort("Choc de Flamme ", 7 , joueur1 ,new AttaqueTotale("Attaque massive","inflige 4 pts de d�gats a tous les serviteurs adverses ", 4));
		((Joueur) joueur1).getMain().add(flamme);
		ICarte golem = new Serviteur ( "Golem des moissons" , 3 , joueur2 , 2 , 3 , new InvocationServiteur ( "Golemisation " , " Invoque un Golem endomage 2/1" , new Serviteur ( " Serviteur de Golem " , 0 , joueur2 , 2 , 1 ,null)));
		ICarte gnome = new Serviteur ("Gn�me l�preux " , 1 ,joueur2 , 1 , 1 , new AttaqueHeros ( " Attaque du l�preux " , "Inflige 2 points de d�gats au h�ros " , 2 ));
		((Joueur) joueur2).getJeu().add(gnome);
		((Joueur) joueur2).getJeu().add(golem);*/
		
		
		//Test Charge
		/*ICarte missiliere= new Serviteur ("La missili�re t�meraire " , 6 , joueur1 , 5 ,  2 , new Charge ());
		((Joueur) joueur1).getMain().add(missiliere);
		ICarte charge = new Sort("Charge" , 1 , joueur1 , new Charge());
		((Joueur) joueur1).getMain().add(charge);
		ICarte champion_hurlevent= new Serviteur ("Champion de Hurlevent" , 7 , joueur1 ,  6 ,6 , new EffetPermanent ( " Bonus de hurlevent " , "Effet sur les serviteurs donnant un bonus +1/+1 " , 1 , 1));
		((Joueur) joueur1).getJeu().add(champion_hurlevent);*/
		
		//System.out.println("jeu joueur1 : " + ((Joueur) joueur1).getJeu().size());
		
		//Test Effet permanent
		/*ICarte mirroir = new Sort ("Image mirroir", 1, joueur1, new ImageMirroir("Image Mirroir", "Invoque deux serviteurs de Jaina 0/+2 ayant provocation"));
		((Joueur) joueur1).getMain().add(mirroir);
		ICarte champion_hurlevent= new Serviteur ("Champion de Hurlevent" , 7 , joueur1 ,  6 ,6 , new EffetPermanent ( " Bonus de hurlevent " , "Effet sur les serviteurs donnant un bonus +1/+1 " , 1 , 1));
		((Joueur) joueur1).getMain().add(champion_hurlevent);
		ICarte givre = new Sort ("Eclair de givre", 2, joueur2, new AttaqueCibleSort("Attaque de givre", "inflige 7 pts de d�gats au personnages cible", 7));
		((Joueur) joueur2).getMain().add(givre);*/
		
		//Test marque du chasseur
		/*ICarte chasseur = new Sort ("Marque du Chasseur", 1, joueur1, new MarqueChasseur("Marque du chaseur","Abaisse � 1 les points de vie du serviteur cibl�" ));
		((Joueur) joueur2).getMain().add(chasseur);*/
		
		//Test Pioche
		/*ICarte busard = new Serviteur("Busard affam�", 5, joueur1, 3, 2, new Pioche("Pioche", "Pioche une carte",1) );
		((Joueur) joueur1).getMain().add(busard);
		ICarte arcanes = new Sort ("Intelligence des arcanes", 2, joueur1, new Pioche("Pioche","Pioche deux cartes",2));
		((Joueur) joueur1).getMain().add(arcanes);*/
		
		
		/*ICarte golem = new Serviteur ( "Golem des moissons" , 3 , joueur2 , 2 , 3 , new InvocationServiteur ( "Golemisation " , " Invoque un Golem endomage 2/1" , new Serviteur ( " Serviteur de Golem " , 0 , joueur2 , 2 , 1 ,null)));
		//((Joueur) joueur2).getJeu().add(gnome);
		((Joueur) joueur2).getJeu().add(golem);*/
		
		// Cr�ation plateau
		try {
		Plateau.plateau().ajouterJoueur(joueur1);
		}
		catch (HearthstoneException e) {
			e.printStackTrace();
		}
		try {
			Plateau.plateau().ajouterJoueur(joueur2);
			}
			catch (HearthstoneException e) {
				e.printStackTrace();
			}
		
		Plateau.plateau().demarrerPartie();
		int alea=(int)(Math.random() * 2);
		//System.out.println("\n"+alea+"\n");
		if(alea==0) {
			try {
				joueur1.prendreTour();
			} catch (HearthstoneException e) {
				System.out.println(e.getMessage());
			}
		}
		if(alea==1) {
			try {
				joueur2.prendreTour();
			} catch (HearthstoneException e) {
				System.out.println(e.getMessage());
			}	
		}
		// Cr�ation menu
		ihm = initialiserInterfaces();
		
		if (ihm==null) {
			System.out.println("L'application ne sais rien faire....");
			System.exit(0);
		}	
		
		while (Plateau.plateau().estDemaree()) {
			
			/*
			for (ICarte carte : joueur1.getMain()) {
				System.out.println("main1");
				System.out.println(carte.toString());
			}
			for (ICarte carte : joueur2.getMain()) {
				System.out.println("main2");
				System.out.println(carte.toString());
			}
			*/
			
			System.out.println(Plateau.plateau().toString());
			
			//System.out.println("jeu joueur1 : " + ((Joueur) joueur1).getJeu().size());

			String choix = menu();
			System.out.println(choix);
			try {
				ihm.interagir(choix, Plateau.plateau());
			} catch (Exception e) {
				e.printStackTrace();
			}
			
			/*
			for (ICarte carte : joueur1.getJeu()) {
				System.out.println("jeu");
				System.out.println(carte.toString());
			}
			for (ICarte carte : joueur2.getJeu()) {
				System.out.println("jeu2");
				System.out.println(carte.toString());
			}*/
			
			
		}
		
		
	}
	
	private static String menu() {
		ArrayList<String>	menu = new ArrayList<String>();
		Interface i = ihm;
		while (i != null) {
			if (i.getDescription() != null)
				menu.add(i.getDescription());
			i = i.getSuivant();
		}
		
		int n = 1;
		for (String s : menu) {
			es.println(""+n+". "+s);
			n++;
		}
		
		es.println("");
		es.println("Votre choix : ");
		int choix = es.readInt();
		
		return menu.get(choix-1);
	}

	private static Interface initialiserInterfaces() {
		Interface monInterface = null;
		monInterface = new InterfaceQuitter(monInterface);
		monInterface = new InterfaceChargeSort(monInterface);
		monInterface = new InterfaceCharge(monInterface);
		monInterface = new InterfaceChasseur(monInterface);
		monInterface = new InterfaceFinirTour(monInterface);
		monInterface = new InterfaceJouerCarte(monInterface);
		monInterface = new InterfaceUtiliserCarte(monInterface);
		monInterface = new InterfaceUtiliserPouvoir(monInterface);
		return monInterface;
	}
}
