package application;

import java.util.ArrayList;

import capacite.AttaqueCible;
import capacite.AttaqueHeros;
import capacite.AttaqueTotale;
import capacite.Charge;
import capacite.EffetPermanent;
import capacite.ImageMirroir;
import capacite.InvocationChien;
import capacite.InvocationServiteur;
import capacite.MarqueChasseur;
import capacite.Pioche;
import capacite.Provocation;
import carte.ICarte;
import carte.Serviteur;
import joueur.IJoueur;
import joueur.Joueur;
import carte.Sort;
import exception.HearthstoneException;
import heros.Heros;

public class Main {
	
	public static ArrayList<ICarte> CarteJaina(IJoueur proprietaire)
	{
		ArrayList<ICarte> cartesJaina = new ArrayList<ICarte> ();
		ICarte flamme =new Sort("Choc de Flamme ", 7 , proprietaire ,new AttaqueTotale("Attaque massive","inflige 4 pts de d�gats a tous les serviteurs adverses ", 4));
		cartesJaina.add(flamme);
		ICarte givre = new Sort ("Eclair de givre", 2, proprietaire, new AttaqueCible("Attaque de givre", "inflige 3 pts de d�gats au personnages cible", 3));
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
		ICarte tirarcanes = new Sort ("Tir des arcanes", 1, proprietaire, new AttaqueCible("Tir des arcanes", "inflige 2 pts de d�gats au personnage cible", 2));
		cartesRexxar.add(tirarcanes);
		ICarte chiens = new Sort ("Lachez les chiens", 3, proprietaire, new InvocationChien("invocation des chiens","invoque autant de chiens que l'adversaire a de cartes en jeu" )); 
		cartesRexxar.add(chiens);
		ICarte tuer = new Sort ("Ordre de tuer", 3, proprietaire, new AttaqueCible("Ordre de tuer", "Inflige 3 pts de degats au personnage cible", 3)); 
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
		
		ICarte golem = new Serviteur ( " Golem des moissons" , 3 , proprietaire , 2 , 3 , new InvocationServiteur ( "Golemisation " , " Invoque un Golem endomage 2/1" , new Serviteur ( " Serviteur de Golem " , 0 , proprietaire , 2 , 1 ,null)));
		cartesNeutres.add(golem);
		
		ICarte charge = new Sort("Charge" , 1 , proprietaire , new Charge());
		cartesNeutres.add(charge);
		
		ICarte attaque_mentale= new Sort ( " Attaque mentale " , 2 ,  proprietaire , new AttaqueHeros ( "Attaque mentale " , "Inflige 5 points de d�gats au h�ros " , 5));
		cartesNeutres.add(attaque_mentale);
		
		return cartesNeutres;
		
		
}
	
	
	public static void main(String[] args) throws HearthstoneException {
		/*Creation des 2 joueurs*/
		IJoueur joueur1=new Joueur("Joueur 1 " , new Heros("Rexxar" , new AttaqueCible ("Tir assur� ","Inflige 2 points de degats � la cible", 2)) );
		IJoueur joueur2=new Joueur ( "Joueur 2" , new Heros("Jaina",new AttaqueCible("Boule de feu","Inflige 1 point de degat � la cible", 1)));
		
		((Joueur) joueur1).setDeck(CarteRexxar(joueur1));
		((Joueur) joueur2).setDeck(CarteJaina(joueur2));
		
		((Joueur) joueur1).getDeck().addAll(CartesNeutres(joueur1));
		((Joueur) joueur2).getDeck().addAll(CartesNeutres(joueur2));
		
		for(ICarte carte  : ((Joueur) joueur1).getDeck()) {
			System.out.println(carte.toString());
		}
	}
}
