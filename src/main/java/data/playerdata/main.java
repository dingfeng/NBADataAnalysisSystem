package data.playerdata;

import gnu.trove.map.TIntObjectMap;
import gnu.trove.map.hash.TIntObjectHashMap;

import java.io.BufferedReader;
import java.io.File;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

import po.PlayerPO;

public class main
{
	public static Random rand = new Random();
	public static void main(String[] args)
	{
		PlayerData player = new PlayerData("C:/NBAData/players");
		PlayerPO[] all_players = player.getAllPlayerData();
		for (PlayerPO po : all_players)
		{
			if (po.getPosition().contains("-"))
			System.out.println("name : "+po.getName()+"  position : "+po.getPosition());
		}
 	}
	
	public static long testTroveMap()
	{
		PlayerData player = new PlayerData("G:/NBAData/players");
		PlayerPO[] all_players = player.getAllPlayerData();
		
		TIntObjectMap<PlayerPO> map = new TIntObjectHashMap<PlayerPO>();
		for (PlayerPO p : all_players)
		{
			map.put(p.getName().hashCode(), p);
		}
		long start = System.currentTimeMillis();
		for ( int i = 0; i < 1000000; i++)
		{
			PlayerPO playesr = (PlayerPO) map.get(playernames[rand.nextInt(448)].hashCode());
		}
		long end = System.currentTimeMillis();
		long margin = end - start;
		return margin ;
	}
	
	public static long testUtilMap()
	{
		long start = System.currentTimeMillis();
		PlayerData_old data = new PlayerData_old();
		data.getAllPlayerData();
		long end = System.currentTimeMillis();
		long margin = end - start;
		return margin;
	}

	public static long test2()
	{
		PlayerData player = new PlayerData("G:/NBAData/players");
		PlayerPO[] all_players = player.getAllPlayerData();
		Map<String, PlayerPO> map = new HashMap<String, PlayerPO>();
		for (PlayerPO p : all_players)
		{
			map.put(p.getName(), p);
		}
		long start = System.currentTimeMillis();
		for (int i = 0; i < 1000000; i++)
		{
		PlayerPO playerpo = map.get(playernames[rand.nextInt(448)]);
		}
		long end = System.currentTimeMillis();
		return (end - start);
	}
	public static String[] playernames = new String[]{
		"Aaron Brooks",
		"Aaron Gray",
		"Adonis Thomas",
		"Al Harrington",
		"Al Horford",
		"Al Jefferson",
		"Al-Farouq Aminu",
		"Alan Anderson",
		"Alec Burks",
		"Alex Len",
		"Alexey Shved",
		"Alexis Ajinca",
		"Allen Crabbe",
		"Alonzo Gee",
		"Amar",
		"Amir Johnson",
		"Anderson Varejao",
		"Andray Blatche",
		"Andre Drummond",
		"Andre Iguodala",
		"Andre Miller",
		"Andre Roberson",
		"Andrea Bargnani",
		"Andrei Kirilenko",
		"Andrew Bogut",
		"Andrew Bynum",
		"Andrew Nicholson",
		"Anthony Bennett",
		"Anthony Davis",
		"Anthony Morrow",
		"Anthony Randolph",
		"Anthony Tolliver",
		"Archie Goodwin",
		"Arnett Moultrie",
		"Aron Baynes",
		"Arron Afflalo",
		"Austin Daye",
		"Austin Rivers",
		"Avery Bradley",
		"Ben McLemore",
		"Beno Udrih",
		"Bernard James",
		"Bismack Biyombo",
		"Blake Griffin",
		"Boris Diaw",
		"Bradley Beal",
		"Brandan Wright",
		"Brandon Bass",
		"Brandon Davies",
		"Brandon Jennings",
		"Brandon Knight",
		"Brandon Rush",
		"Brendan Haywood",
		"Brian Roberts",
		"Brook Lopez",
		"Byron Mullens",
		"Carl Landry",
		"Carlos Boozer",
		"Carlos Delfino",
		"Carmelo Anthony",
		"Caron Butler",
		"Carrick Felix",
		"Cartier Martin",
		"Casper Ware",
		"Chandler Parsons",
		"Channing Frye",
		"Charles Hayes",
		"Charlie Villanueva",
		"Chase Budinger",
		"Chauncey Billups",
		"Chris Andersen",
		"Chris Babb",
		"Chris Bosh",
		"Chris Copeland",
		"Chris Douglas-Roberts",
		"Chris Johnson",
		"Chris Kaman",
		"Chris Paul",
		"Chris Singleton",
		"Chris Wright",
		"CJ McCollum",
		"CJ Miles",
		"CJ Watson",
		"Cody Zeller",
		"Cole Aldrich",
		"Corey Brewer",
		"Cory Joseph",
		"Courtney Lee",
		"D",
		"Damian Lillard",
		"Damion James",
		"Danilo Gallinari",
		"Danny Granger",
		"Danny Green",
		"Dante Cunningham",
		"Darius Miller",
		"Darrell Arthur",
		"Darren Collison",
		"David Lee",
		"David West",
		"DeAndre Jordan",
		"DeJuan Blair",
		"DeMar DeRozan",
		"DeMarcus Cousins",
		"DeMarre Carroll",
		"Dennis Schroder",
		"Derek Fisher",
		"Deron Williams",
		"Derrick Favors",
		"Derrick Rose",
		"Derrick Williams",
		"Devin Harris",
		"Dewayne Dedmon",
		"Diante Garrett",
		"Dion Waiters",
		"Dionte Christmas",
		"Dirk Nowitzki",
		"DJ White",
		"Donald Sloan",
		"Donatas Motiejunas",
		"Dorell Wright",
		"Doron Lamb",
		"Draymond Green",
		"Drew Gooden",
		"Dwight Buycks",
		"Dwight Howard",
		"Dwyane Wade",
		"E",
		"Earl Watson",
		"Ed Davis",
		"Ekpe Udoh",
		"Elliot Williams",
		"Elton Brand",
		"Emeka Okafor",
		"Enes Kanter",
		"Eric Bledsoe",
		"Eric Gordon",
		"Erik Murphy",
		"Ersan Ilyasova",
		"Evan Fournier",
		"Evan Turner",
		"Festus Ezeli",
		"Francisco Garcia",
		"Gal Mekel",
		"Garrett Temple",
		"Gary Neal",
		"George Hill",
		"Gerald Green",
		"Gerald Henderson",
		"Gerald Wallace",
		"Giannis Antetokounmpo",
		"Gigi Datome",
		"Glen Davis",
		"Glen Rice Jr",
		"Goran Dragic",
		"Gordon Hayward",
		"Gorgui Dieng",
		"Grant Jerrett",
		"Greg Monroe",
		"Greg Oden",
		"Greg Smith",
		"Greivis Vasquez",
		"Gustavo Ayon",
		"Harrison Barnes",
		"Hasheem Thabeet",
		"Hedo Turkoglu",
		"Henry Sims",
		"Hilton Armstrong",
		"Hollis Thompson",
		"Ian Clark",
		"Ian Mahinmi",
		"Iman Shumpert",
		"Isaiah Canaan",
		"Isaiah Thomas",
		"Ish Smith",
		"J",
		"Jae Crowder",
		"Jamaal Franklin",
		"Jamal Crawford",
		"Jameer Nelson",
		"James Anderson",
		"James Harden",
		"James Johnson",
		"James Jones",
		"James Southerland",
		"Jan Vesely",
		"Jannero Pargo",
		"Jared Cunningham",
		"Jared Dudley",
		"Jared Sullinger",
		"Jarrett Jack",
		"Jarvis Varnado",
		"Jason Collins",
		"Jason Maxiell",
		"Jason Richardson",
		"Jason Smith",
		"Jason Terry",
		"Jason Thompson",
		"JaVale McGee",
		"Jeff Adrien",
		"Jeff Ayres",
		"Jeff Green",
		"Jeff Taylor",
		"Jeff Teague",
		"Jeff Withey",
		"Jeremy Evans",
		"Jeremy Lamb",
		"Jeremy Lin",
		"Jeremy Tyler",
		"Jermaine O",
		"Jerryd Bayless",
		"Jimmer Fredette",
		"Jimmy Butler",
		"JJ Hickson",
		"JJ Redick",
		"Joakim Noah",
		"Jodie Meeks",
		"Joe Johnson",
		"Joel Anthony",
		"Joel Freeland",
		"John Henson",
		"John Jenkins",
		"John Lucas III",
		"John Salmons",
		"John Wall",
		"Jon Leuer",
		"Jonas Jerebko",
		"Jonas Valanciunas",
		"Jordan Crawford",
		"Jordan Farmar",
		"Jordan Hamilton",
		"Jordan Hill",
		"Jorge Gutierrez",
		"Jose Calderon",
		"Jose Juan Barea",
		"Josh Harrellson",
		"Josh McRoberts",
		"Josh Powell",
		"Josh Smith",
		"Jrue Holiday",
		"Julyan Stone",
		"Justin Hamilton",
		"Kawhi Leonard",
		"Keith Bogans",
		"Kelly Olynyk",
		"Kemba Walker",
		"Kendall Marshall",
		"Kendrick Perkins",
		"Kenneth Faried",
		"Kent Bazemore",
		"Kentavious Caldwell-Pope",
		"Kenyon Martin",
		"Kevin Durant",
		"Kevin Garnett",
		"Kevin Love",
		"Kevin Martin",
		"Kevin Seraphin",
		"Khris Middleton",
		"Kirk Hinrich",
		"Klay Thompson",
		"Kobe Bryant",
		"Kosta Koufos",
		"Kris Humphries",
		"Kyle Korver",
		"Kyle Lowry",
		"Kyle O",
		"Kyle Singler",
		"Kyrie Irving",
		"Lamar Odom",
		"LaMarcus Aldridge",
		"Lance Stephenson",
		"Landry Fields",
		"Larry Sanders",
		"Lavoy Allen",
		"Leandro Barbosa",
		"LeBron James",
		"Lou Amundson",
		"Lou Williams",
		"Luc Mbah a Moute",
		"Luis Scola",
		"Luke Babbitt",
		"Luke Ridnour",
		"Luol Deng",
		"Malcolm Thomas",
		"Manu Ginobili",
		"Marc Gasol",
		"Marcin Gortat",
		"Marco Belinelli",
		"Marcus Morris",
		"Marcus Thornton",
		"Mario Chalmers",
		"Markieff Morris",
		"Marquis Teague",
		"Marreese Speights",
		"MarShon Brooks",
		"Martell Webster",
		"Marvin Williams",
		"Mason Plumlee",
		"Matt Barnes",
		"Matt Bonner",
		"Matthew Dellavedova",
		"Maurice Harkless",
		"Melvin Ely",
		"Meyers Leonard",
		"Michael Beasley",
		"Michael Carter-Williams",
		"Michael Kidd-Gilchrist",
		"Mike Conley",
		"Mike Dunleavy",
		"Mike James",
		"Mike Miller",
		"Mike Muscala",
		"Mike Scott",
		"Miles Plumlee",
		"Miroslav Raduljica",
		"Mirza Teletovic",
		"Mo Williams",
		"Monta Ellis",
		"Nando De Colo",
		"Nate Robinson",
		"Nate Wolters",
		"Nazr Mohammed",
		"Nemanja Nedovic",
		"Nene",
		"Nerlens Noel",
		"Nick Calathes",
		"Nick Collison",
		"Nick Young",
		"Nicolas Batum",
		"Nikola Pekovic",
		"Nikola Vucevic",
		"Norris Cole",
		"O",
		"Ognjen Kuzmic",
		"Omer Asik",
		"Omri Casspi",
		"Othyus Jeffers",
		"Otto Porter",
		"Pablo Prigioni",
		"Patrick Beverley",
		"Patrick Patterson",
		"Patty Mills",
		"Pau Gasol",
		"Paul George",
		"Paul Millsap",
		"Paul Pierce",
		"Pero Antic",
		"Perry Jones",
		"Peyton Siva",
		"Phil Pressey",
		"PJ Tucker",
		"Quincy Acy",
		"Quincy Miller",
		"Quincy Pondexter",
		"Rajon Rondo",
		"Ramon Sessions",
		"Randy Foye",
		"Rashard Lewis",
		"Rasual Butler",
		"Ray Allen",
		"Ray McCallum",
		"Raymond Felton",
		"Reggie Bullock",
		"Reggie Evans",
		"Reggie Jackson",
		"Richard Jefferson",
		"Ricky Ledo",
		"Ricky Rubio",
		"Robbie Hummel",
		"Robert Covington",
		"Robert Sacre",
		"Robin Lopez",
		"Rodney Stuckey",
		"Ronnie Brewer",
		"Ronnie Price",
		"Ronny Turiaf",
		"Roy Hibbert",
		"Rudy Gay",
		"Rudy Gobert",
		"Russell Westbrook",
		"Ryan Anderson",
		"Ryan Hollins",
		"Ryan Kelly",
		"Samuel Dalembert",
		"Scotty Hopson",
		"Serge Ibaka",
		"Sergey Karasev",
		"Shabazz Muhammad",
		"Shane Battier",
		"Shane Larkin",
		"Shannon Brown",
		"Shaun Livingston",
		"Shavlik Randolph",
		"Shawn Marion",
		"Shelvin Mack",
		"Solomon Hill",
		"Spencer Hawes",
		"Stephen Curry",
		"Steve Blake",
		"Steve Nash",
		"Steve Novak",
		"Steven Adams",
		"Taj Gibson",
		"Tayshaun Prince",
		"Terrence Jones",
		"Terrence Ross",
		"Thabo Sefolosha",
		"Thaddeus Young",
		"Thomas Robinson",
		"Tiago Splitter",
		"Tim Duncan",
		"Tim Hardaway Jr",
		"Timofey Mozgov",
		"Tobias Harris",
		"Toney Douglas",
		"Tony Allen",
		"Tony Mitchell",
		"Tony Parker",
		"Tony Snell",
		"Tony Wroten",
		"Toure Murry",
		"Travis Outlaw",
		"Trevor Ariza",
		"Trevor Booker",
		"Trey Burke",
		"Tristan Thompson",
		"Troy Daniels",
		"Ty Lawson",
		"Tyler Hansbrough",
		"Tyler Zeller",
		"Tyreke Evans",
		"Tyson Chandler",
		"Udonis Haslem",
		"Victor Claver",
		"Victor Oladipo",
		"Vince Carter",
		"Vitor Faverani",
		"Wayne Ellington",
		"Wesley Johnson",
		"Wesley Matthews",
		"Will Barton",
		"Will Bynum",
		"Willie Green",
		"Willie Reed",
		"Wilson Chandler",
		"Xavier Henry",
		"Zach Randolph",
		"Zaza Pachulia"
	};
}
