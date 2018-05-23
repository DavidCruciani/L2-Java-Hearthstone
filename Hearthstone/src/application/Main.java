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
	
	private	static Interface ihm = null;
	public final static Console es = new Console();
	
	public static ArrayList<ICarte> CarteJaina(IJoueur proprietaire)
	{
		ArrayList<ICarte> cartesJaina = new ArrayList<ICarte> ();
		ICarte flamme =new Sort("Choc de Flamme ", 7 , proprietaire ,new AttaqueTotale("Attaque massive","inflige 4 pts de dégats a tous les serviteurs adverses ", 4));
		cartesJaina.add(flamme);
		ICarte givre = new Sort ("Eclair de givre", 2, proprietaire, new AttaqueCibleSort("Attaque de givre", "inflige 3 pts de dégats au personnages cible", 3));
		cartesJaina.add(givre);
		ICarte arcanes = new Sort ("Intelligence des arcanes", 2, proprietaire, new Pioche("Pioche","Pioche deux cartes",2));
		cartesJaina.add(arcanes);
		ICarte mirroir = new Sort ("Image mirroir", 1, proprietaire, new ImageMirroir("Image Mirroir", "Invoque deux serviteurs de Jaina 0/+2 ayant provocation"));
		cartesJaina.add(mirroir);
		ICarte explosion = new Sort ("Explosion Pyrotechnique", 10, proprietaire, new AttaqueTotale("Explosion Pyrotechnique", "inflige 10 pts de dégats a tous les serviteurs adverses", 10));
		cartesJaina.add(explosion);
		
		return cartesJaina;
		
	}
	
	public static ArrayList<ICarte> CarteRexxar(IJoueur proprietaire)
	{
		ArrayList<ICarte> cartesRexxar = new ArrayList<ICarte> ();
		ICarte chasseur = new Sort ("Marque du Chasseur", 1, proprietaire, new MarqueChasseur("Marque du chaseur","Abaisse à 1 les points de vie du serviteur ciblé" ));
		cartesRexxar.add(chasseur);
		ICarte tirarcanes = new Sort ("Tir des arcanes", 1, proprietaire, new AttaqueCibleSort("Tir des arcanes", "inflige 2 pts de dégats au personnage cible", 2));
		cartesRexxar.add(tirarcanes);
		ICarte chiens = new Sort ("Lachez les chiens", 3, proprietaire, new InvocationChien("invocation des chiens","invoque autant de chiens que l'adversaire a de cartes en jeu" )); 
		cartesRexxar.add(chiens);
		ICarte tuer = new Sort ("Ordre de tuer", 3, proprietaire, new AttaqueCible("Ordre de tuer", "Inflige 3 pts de degats au personnage cible", 3)); 
		cartesRexxar.add(tuer);
		ICarte busard = new Serviteur("Busard affamé", 5, proprietaire, 3, 2, new Pioche("Pioche", "Pioche une carte",1) );
		cartesRexxar.add(busard);
		
		return cartesRexxar;
		
	}
	
	public static ArrayList<ICarte> CartesNeutres(IJoueur proprietaire)
	{
		ArrayList<ICarte> cartesNeutres = new ArrayList<ICarte> ();
		ICarte chasse_marree=new Serviteur ("Chasse marée murloc" , 2, proprietaire , 2 , 1 , new InvocationServiteur ("Cri de guerre" , "Invocation d'un serviteur 1/1" , new Serviteur ("Serviteur de Chasse-maree" , 0 , proprietaire , 1, 1,null)));
		cartesNeutres.add(chasse_marree);
		
		ICarte champion_hurlevent= new Serviteur ("Champion de Hurlevent" , 7 , proprietaire ,  6 ,6 , new EffetPermanent ( " Bonus de hurlevent " , "Effet sur les serviteurs donnant un bonus +1/+1 " , 1 , 1));
		cartesNeutres.add(champion_hurlevent);
		
		ICarte chef_raid = new Serviteur ("Chef de Raid" , 3 , proprietaire , 2 , 2 , new EffetPermanent ( " Bonus du chef de raid " , " Effet sur les serviteurs alliés donnant un bonus +1/0" , 1 , 0 ));  
		cartesNeutres.add(chef_raid);
		
		ICarte missiliere= new Serviteur ("La missilière témeraire " , 6 , proprietaire , 5 ,  2 , new Charge ());
		cartesNeutres.add(missiliere);
		
		ICarte ogre_magi= new Serviteur ( "L'ogre-magi" , 4 , proprietaire , 4 , 4 , new Provocation ());
		cartesNeutres.add(ogre_magi);
		
		ICarte archimage = new Serviteur ("Archimage" , 6 , proprietaire , 4 ,7 , new Provocation());
		cartesNeutres.add(archimage);
		
		ICarte gnome = new Serviteur ("Gnôme lépreux " , 1 ,proprietaire , 1 , 1 , new AttaqueHeros ( " Attaque du lépreux " , "Inflige 2 points de dégats au héros " , 2 ));
		cartesNeutres.add(gnome);
		
		ICarte golem = new Serviteur ( " Golem des moissons" , 3 , proprietaire , 2 , 3 , new InvocationServiteur ( "Golemisation " , " Invoque un Golem endomage 2/1" , new Serviteur ( " Serviteur de Golem " , 0 , proprietaire , 2 , 1 ,null)));
		cartesNeutres.add(golem);
		
		ICarte charge = new Sort("Charge" , 1 , proprietaire , new Charge());
		cartesNeutres.add(charge);
		
		ICarte attaque_mentale= new Sort ( " Attaque mentale " , 2 ,  proprietaire , new AttaqueHeros ( "Attaque mentale " , "Inflige 5 points de dégats au héros " , 5));
		cartesNeutres.add(attaque_mentale);
		
		return cartesNeutres;
		
		
}
	
	
	public static void main(String[] args) throws HearthstoneException {
		// Création des joueurs
		IJoueur joueur1=new Joueur("Joueur 1 " , new Heros("Rexxar" , new AttaqueHeros ("Tir assuré ","Inflige 2 points de degats à la cible", 2)) );
		IJoueur joueur2=new Joueur ( "Joueur 2" , new Heros("Jaina",new AttaqueCibleSort("Boule de feu","Inflige 1 point de degat à la cible", 1)));
		
		((Joueur) joueur1).setDeck(CarteRexxar(joueur1));
		((Joueur) joueur2).setDeck(CarteJaina(joueur2));
		
		((Joueur) joueur1).getDeck().addAll(CartesNeutres(joueur1));
		((Joueur) joueur2).getDeck().addAll(CartesNeutres(joueur2));
		
		ICarte flamme =new Sort("Choc de Flamme ", 7 , joueur1 ,new AttaqueTotale("Attaque massive","inflige 4 pts de dégats a tous les serviteurs adverses ", 4));
		((Joueur) joueur1).getMain().add(flamme);
		
		ICarte givre = new Sort ("Eclair de givre", 2, joueur1, new AttaqueCibleSort("Attaque de givre", "inflige 3 pts de dégats au personnages cible", 3));
		((Joueur) joueur1).getMain().add(givre);
		/*ICarte gnome = new Serviteur ("Gnôme lépreux " , 1 ,joueur1 , 1 , 1 , new AttaqueHeros ( " Attaque du lépreux " , "Inflige 2 points de dégats au héros " , 2 ));
		((Joueur) joueur1).getMain().add(gnome);*/
		
		ICarte gnome = new Serviteur ("Gnôme lépreux " , 1 ,joueur2 , 1 , 1 , new AttaqueHeros ( " Attaque du lépreux " , "Inflige 2 points de dégats au héros " , 2 ));
		
		ICarte golem = new Serviteur ( " Golem des moissons" , 3 , joueur2 , 2 , 3 , new InvocationServiteur ( "Golemisation " , " Invoque un Golem endomage 2/1" , new Serviteur ( " Serviteur de Golem " , 0 , joueur2 , 2 , 1 ,null)));
		((Joueur) joueur2).getJeu().add(gnome);
		((Joueur) joueur2).getJeu().add(golem);
		
		// Création plateau
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
		Plateau.plateau().setJoueurCourant(joueur1);
		
		// Création menu
		ihm = initialiserInterfaces();
		
		if (ihm==null) {
			System.out.println("L'application ne sais rien faire....");
			System.exit(0);
		}	
		
		while (true) {
			
			
			for (ICarte carte : joueur1.getMain()) {
				System.out.println("main1");
				System.out.println(carte.toString());
			}
			for (ICarte carte : joueur2.getMain()) {
				System.out.println("main2");
				System.out.println(carte.toString());
			}
			
			
			System.out.println(Plateau.plateau().toString());
			String choix = menu();
			try {
				ihm.interagir(choix, Plateau.plateau());
			} catch (Exception e) {
				e.printStackTrace();
			}
			
			
			for (ICarte carte : joueur1.getJeu()) {
				System.out.println("jeu");
				System.out.println(carte.toString());
			}
			for (ICarte carte : joueur2.getJeu()) {
				System.out.println("jeu2");
				System.out.println(carte.toString());
			}
			
			
		}
		
		
	}
	
	private static String menu() {
		ArrayList<String>	menu = new ArrayList<String>();
		Interface i = ihm;
		while (i != null) {
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
		monInterface = new InterfaceFinirTour(monInterface);
		monInterface = new InterfaceJouerCarte(monInterface);
		monInterface = new InterfaceUtiliserCarte(monInterface);
		monInterface = new InterfaceUtiliserPouvoir(monInterface);
		return monInterface;
	}
}
